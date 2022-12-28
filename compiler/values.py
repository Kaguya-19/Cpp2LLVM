from typing import List, Tuple, Optional

from llvmlite import ir


class TypedValue:

    def __init__(self, ir_value: ir.Value, typ: ir.Type, constant: bool, name: str = None, lvalue_ptr: bool = False):
        """
        初始化
        @param ir_value:    llvm ir 中对应的值
        @param typ:         值的实际类型 (这与 ir_value.type 是不同的。比如一个 alloca 的 i32 局部变量，ir_value 的 type 会是 i32*，而这个字段是 i32
        @param constant:    值是否是常量
        @param name:        值的名称 (可以为 None，默认为 None)
        @param lvalue_ptr:  值是否是一个左值 (这意味着 ir_value 的类型会是 typ.as_pointer()，需要用 load 和 store 访问。默认为 False)
        """
        self.type = typ
        self.constant = constant
        self.ir_value = ir_value
        self.name = name
        self.lvalue_ptr = lvalue_ptr

    def is_named(self) -> bool:
        return self.name is not None


def const_value(value: ir.Constant, name: str = None) -> TypedValue:
    """
    返回一个常量值.

    Args:
        value (ir.Constant): ir 常量值.
        name (str): 常量名 .

    Returns:
        TypedValue: 常量值的 TypedValue 封装. 右值, constant
    """
    return TypedValue(value, value.type, constant=True, name=name, lvalue_ptr=False)


class ElementNamedLiteralStructType(ir.LiteralStructType):
    """
    成员具名结构体类类型封装
    """

    def __init__(self, elems: List[ir.Type], names: List[str], packed=False):
        """
        *elems* is a sequence of types to be used as members.
        *names* is a sequence of names to be used for name lookup.
        *packed* controls the use of packed layout.
        """
        ir.LiteralStructType.__init__(self, elems, packed)
        self.names = tuple(names)
        self.lookup_cache = {}

    def get_element_by_name(self, name: str) -> Optional[Tuple[List[int], ir.Type]]:
        """
        寻找名字对应的下标. 找不到时返回 None.
        @param name: 名字
        @return: 下标
        """
        for i in range(len(self.names)):
            if self.names[i] == name:
                return [i], self.elements[i]
        try:
            return self.lookup_cache[name]
        except KeyError:
            pass
        for i in range(len(self.names)):
            if self.names[i] is None:
                elem: ElementNamedLiteralStructType = self.elements[i]
                result = elem.get_element_by_name(name)
                if result is not None:
                    result = ([i] + result[0], result[1])
                    self.lookup_cache[name] = result
                    return result
        self.lookup_cache[name] = None
        return None
