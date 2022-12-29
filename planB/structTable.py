from .Errors import SemanticError

class StructTable:
    def __init__(self):
        self.struct_table = {}

    def get_ptr(self, name):
        if name not in self.struct_table.keys():
            raise SemanticError("struct not found")
        return self.struct_table[name]['ptr']

    def get_param_indice(self, name, param_name):
        if name not in self.struct_table.keys():
            raise SemanticError("param name not found")
        return self.struct_table[name]['param_list'].index(param_name)

    def get_param_type(self, name, param_name):
        if name not in self.struct_table.keys():
            raise SemanticError("param name not found")
        idx = self.struct_table[name]['param_list'].index(param_name)
        return self.struct_table[name]['type_list'][idx]

    def insert(self, name, ptr, param_list, type_list):
        self.struct_table[name] = {"ptr": ptr, "param_list": param_list, "type_list": type_list}

    def show_table(self):
        print('name\t\tptr\t\ttype\t\tparam')
        for name in self.struct_table:
            struct = self.struct_table[name]
            ptr = struct['ptr']
            type = struct['type_list']
            param = struct['param_list']
            print(f'{name}\t\t{ptr}\t\t{type}\t\t{param}')
