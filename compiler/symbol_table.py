from typing import Dict, List, Union, Optional, Tuple

from llvmlite import ir

from .values import TypedValue
from .utils import Result


class SymbolTable:
    """
    符号表类
    """

    def __init__(self):
        """
        建立符号表.
        """
        # table：table[i]是一个字典，存着key，value组
        self.table: List[Dict[str, Union[TypedValue, ir.Type, ir.Function]]] = [{}]
        self.current_level: int = 0

    def get_item(self, item: str) -> Optional[Union[TypedValue, ir.Type]]:
        """
        从符号表中获取元素.

        Args:
            item (str): 待获取的元素的 key

        Returns:
            str: 成功返回元素，失败返回 None
        """
        for i in range(self.current_level, -1, -1):
            if item in self.table[i]:
                return self.table[i][item]
        return None

    def add_item(self, key: str, value: Union[TypedValue, ir.Type, ir.Function]) -> Result[None]:
        """
        向符号表中添加元素.

        Args:
            key (str): 待添加的 key
            value (TypedValue): 待添加的 value

        Returns:
            Optional[str]: 如果出现了异常，返回具体错误信息，否则返回 None
        """
        if key in self.table[self.current_level]:
            return Result(False, message="redefinition")
        self.table[self.current_level][key] = value
        return Result(True, value=None)

    def exist(self, item: str) -> bool:
        """
        判断元素是否在符号表里，包括局部和全局.

        Args:
            item (str): 待判断的元素

        Returns:
            bool: 如果表里有，true，否则 false
        """
        return self.get_item(item) is not None

    def enter_scope(self) -> None:
        """
        进入一个新的作用域，增加一层.

        Returns:
            None
        """
        self.current_level += 1
        self.table.append({})

    def quit_scope(self) -> None:
        """
        退出一个作用域，退出一层.

        Returns:
            None
        """
        if self.current_level == 0:
            return
        self.table.pop(-1)
        self.current_level -= 1

    def is_global(self) -> bool:
        """
        判断当前作用域是否是全局作用域.

        Returns:
            bool
        """
        return len(self.table) == 1
