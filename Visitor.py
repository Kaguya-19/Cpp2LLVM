import sys, json
from .LRParser import ParserTree
from llvmlite import ir

double = ir.DoubleType()
int1 = ir.IntType(1)
int32 = ir.IntType(32)
int8 = ir.IntType(8)
void = ir.VoidType()

class Visitor:
    def __init__(self, tree):
        
        self.tree = tree
        
         #控制llvm生成
        self.Module = ir.Module()
        self.Module.triple = "x86_64-pc-linux-gnu" # llvm.Target.from_default_triple()
        self.Module.data_layout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128" # llvm.create_mcjit_compiler(backing_mod, target_machine)
        
        #语句块
        self.Blocks = []

        #待生成的llvm语句块
        self.Builders = []

        #函数列表
        self.Functions = dict()

        #结构体列表
        self.Structure = Structure()

        #当前所在函数
        self.CurrentFunction = ''
        self.Constants = 0

        #这个变量是否需要加载
        self.WhetherNeedLoad = True
        
        #endif块
        self.EndifBlock = None

        #符号表
        self.SymbolTable = SymbolTable()
        
    def visit(self,node):
        tree = self.tree
        tree.current_node = node
        self.visit_node(tree)

    def visit_node(self, tree):
        # TODO:
        type = tree.current_node.type
        if type == 'Prog':
            for i in range(tree.getChildCount()):
                self.visit(tree.getChild(i))
        # fill from here
        pass

    def get_result(self):
        return self.result