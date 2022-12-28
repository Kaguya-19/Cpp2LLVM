from typing import List, Union, Optional, Tuple

from llvmlite import ir


class DeclarationSpecifiers:
    """
    对应文法中的 declarationSpecifiers，表示能够作用在 declarator 上的各种修饰符，包括:
     - typeSpecifier            : int | double | ...        (存储为 ir.Type)
     - typeQualifier            : const                     (存储为 str)
     - storageClassQualifier    : extern | static           (存储为 str)
     - functionSpecifier        : __stdcall | __cdecl | ... (存储为 str)
    """

    def __init__(self):
        self.specifiers: List[Tuple[str, Union[str, ir.Type]]] = []
        pass

    def append_type_specifier(self, specifier: ir.Type):
        self.specifiers.append(("type", specifier))

    def append_type_qualifier(self, qualifier: str):
        self.specifiers.append(("type_qualifier", qualifier))

    def append_storage_class_specifier(self, qualifier: str):
        self.specifiers.append(("storage", qualifier))

    def append_function_specifier(self, specifier: str):
        self.specifiers.append(("function_specifier", specifier))

    def append(self, typ: str, val: Union[str, ir.Type]):
        if typ == "type":
            self.append_type_specifier(val)
        if typ == "type_qualifier":
            self.append_type_qualifier(val)
        if typ == "storage":
            self.append_storage_class_specifier(val)
        if typ == "function_specifier":
            self.append_function_specifier(val)

    def get_function_specifiers(self):
        return map(lambda x: x[1], filter(lambda x: x[0] == "function_specifier", self.specifiers))

    def get_type(self):
        for (typ, val) in self.specifiers:
            if typ == "type":
                return val
        return None

    def is_typedef(self):
        return ("storage", "typedef") in self.specifiers

    def is_extern(self):
        return ("storage", "extern") in self.specifiers

    def is_static(self):
        return ("storage", "static") in self.specifiers

    def is_extern(self):
        for (typ, val) in self.specifiers:
            if typ == "storage" and val == "extern":
                return True
        return False


class ParameterList:

    def __init__(self, parameters: List[Tuple[ir.Type, Optional[str]]], var_arg: bool, calling_convention: str = ''):
        self.parameters = parameters
        self.var_arg = var_arg
        self.arg_list = [param[0] for param in self.parameters]
        self.calling_convention = calling_convention

    def __getitem__(self, item: int):
        return self.parameters[item]

    def __len__(self):
        return len(self.parameters)

