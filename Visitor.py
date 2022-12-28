import sys, json
from .LRParser import ParserTree
from llvmlite import ir

double = ir.DoubleType()
int1 = ir.IntType(1)
int32 = ir.IntType(32)
int8 = ir.IntType(8)
void = ir.VoidType()

class SemanticError(Exception):
    """语义错误基类"""
    def __init__(self, msg, ctx=None):
        super().__init__()
        if ctx:
            self.line = ctx.start.line  #错误出现位置
            self.column = ctx.start.column
        else:
            self.line = 0
            self.column = 0
        self.msg = msg

    def __str__(self):
        return "SemanticError: " + str(self.line) + ":" + str(self.column) + " " + self.msg

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
            self.visitProg(tree)
        elif type == 'MFunction':
            self.visitMFunction(tree)
        elif type == 'Params':
            self.visitParams(tree)
        elif type == 'Param':
            self.visitParam(tree)
        elif type == 'FuncBody':
            self.visitFuncBody(tree)
        elif type == 'Body':
            self.visitBody(tree)
        elif type == 'Func':
            self.visitFunc(tree)
        elif type == 'StrlenFunc':
            self.visitStrlenFunc(tree)
        elif type == 'PrintfFunc':
            self.visitPrintfFunc(tree)
        elif type == 'ScanfFunc':
            self.visitScanfFunc(tree)
        elif type == 'GetsFunc':
            self.visitGetsFunc(tree)
        elif type == 'SelfDefinedFunc':
            self.visitSelfDefinedFunc(tree)
        elif type == 'Block':
            self.visitBlock(tree)
        elif type == 'InitialBlock':
            self.visitInitialBlock(tree)
        elif type == 'ArrayInitBlock':
            self.visitArrayInitBlock(tree)
        elif type == 'AssignBlock':
            self.visitAssignBlock(tree)
        elif type == 'Condition':
            self.visitCondition(tree)
        elif type == 'IfBlocks':
            self.visitIfBlocks(tree)
        elif type == 'IfBlock':
            self.visitIfBlock(tree)
        elif type == 'ElifBlock':
            self.visitElifBlock(tree)
        
        # fill from here
        pass

    def get_result(self):
        return self.result


    def visitProg(self, tree):
        '''
        语法规则：prog :(include)* (initialBlock|arrayInitBlock|structInitBlock|mStructDef|mFunction)*;
        描述：代码主文件
        返回：无
        '''
        for i in range(tree.getChildCount()):
            self.visit(tree.getChild(i))

    #函数相关函数
    def visitMFunction(self, tree):

        '''
        语法规则：mFunction : (mType|mVoid|mStruct) mID '(' params ')' '{' funcBody '}';
        描述：函数的定义
        返回：无
        '''
        #获取返回值类型
        ReturnType = self.visit(tree.getChild(0)) # mtype
        
        #获取函数名 todo
        FunctionName = tree.getChild(1).getText() # func name
        
        #获取参数列表
        ParameterList = self.visit(tree.getChild(3)) # func params

        #根据返回值，函数名称和参数生成llvm函数
        ParameterTypeList = []
        for i in range(len(ParameterList)):
            ParameterTypeList.append(ParameterList[i]['type'])
        LLVMFunctionType = ir.FunctionType(ReturnType, ParameterTypeList)
        LLVMFunction = ir.Function(self.Module, LLVMFunctionType, name = FunctionName)

        #存储函数的变量        
        for i in range(len(ParameterList)):
            LLVMFunction.args[i].name = ParameterList[i]['IDname']

        #存储函数的block
        TheBlock = LLVMFunction.append_basic_block(name = FunctionName + '.entry')

        #判断重定义，存储函数
        if FunctionName in self.Functions:
            raise SemanticError(ctx=tree,msg="函数重定义错误！")
        else:
            self.Functions[FunctionName] = LLVMFunction

        TheBuilder = ir.IRBuilder(TheBlock)
        self.Blocks.append(TheBlock)
        self.Builders.append(TheBuilder)

        #进一层
        self.CurrentFunction = FunctionName
        self.SymbolTable.EnterScope()

        #存储函数的变量
        VariableList = {}
        for i in range(len(ParameterList)):
            NewVariable = TheBuilder.alloca(ParameterList[i]['type'])
            TheBuilder.store(LLVMFunction.args[i], NewVariable)
            TheVariable = {}
            TheVariable["Type"] = ParameterList[i]['type']
            TheVariable["Name"] = NewVariable
            TheResult = self.SymbolTable.AddItem(ParameterList[i]['IDname'], TheVariable)
            if TheResult["result"] != "success":
                raise SemanticError(ctx=tree,msg=TheResult["reason"])

        #处理函数body
        self.visit(tree.getChild(6)) # func body

        #处理完毕，退一层
        self.CurrentFunction = ''
        self.Blocks.pop()
        self.Builders.pop()
        self.SymbolTable.QuitScope()
        return

    def visitParams(self, tree):
        '''
        语法规则：params : param (','param)* |;
        描述：函数的参数列表
        返回：处理后的函数参数列表
        '''
        Length = tree.getChildCount()
        if (Length == 0):
            return []
        ParameterList = []
        i = 0
        while i < Length:
            NewParameter = self.visit(tree.getChild(i))
            ParameterList.append(NewParameter)
            i += 2
        return ParameterList

    def visitParam(self, tree):
        '''
        语法规则：param : mType mID;
        描述：返回函数参数
        返回：一个字典，字典的Type是类型，Name是参数名
        '''
        Type = self.visit(tree.getChild(0))
        IDname = tree.getChild(1).getText()
        Result = {'type': Type, 'IDname': IDname}
        return Result

    def visitFuncBody(self, tree):
        '''
        语法规则：funcBody : body returnBlock;
        描述：函数体
        返回：无
        '''
        self.SymbolTable.EnterScope()
        for index in range(tree.getChildCount()):
            self.visit(tree.getChild(index))
        self.SymbolTable.QuitScope()
        return

    def visitBody(self, tree):
        '''
        语法规则：body : (block | func';')*;
        描述：语句块/函数块
        返回：无
        '''
        for i in range(tree.getChildCount()):
            self.visit(tree.getChild(i))
            if self.Blocks[-1].is_terminated:
                break
        return

    #调用函数相关函数
    def visitFunc(self, tree):
        '''
        语法规则：func : (strlenFunc | atoiFunc | printfFunc | scanfFunc | getsFunc | selfDefinedFunc);
        描述：函数
        返回：无
        '''
        return self.visit(tree.getChild(0))

    def visitStrlenFunc(self, tree):
        '''
        语法规则：strlenFunc : 'strlen' '(' mID ')';
        描述：strlen函数
        返回：函数返回值
        '''
        if 'strlen' in self.Functions:
            strlen = self.Functions['strlen']
        else:
            strlenType = ir.FunctionType(int32, [ir.PointerType(int8)], var_arg = False)
            strlen = ir.Function(self.Module, strlenType, name = "strlen")
            self.Functions['strlen'] = strlen

        TheBuilder = self.Builders[-1]
        zero = ir.Constant(int32, 0)

        #加载变量
        PreviousNeedLoad = self.WhetherNeedLoad
        self.WhetherNeedLoad = False
        res = self.visit(tree.getChild(2))
        self.WhetherNeedLoad = PreviousNeedLoad

        Arguments = TheBuilder.gep(res['name'], [zero, zero], inbounds = True)
        ReturnVariableName = TheBuilder.call(strlen, [Arguments])

        Result = {'type': int32, 'name': ReturnVariableName}
        return Result

    def visitPrintfFunc(self, tree):
        '''
        语法规则：printfFunc : 'printf' '(' (mSTRING | mID) (','expr)* ')';
        描述：printf函数
        返回：函数返回值
        '''        
        if 'printf' in self.Functions:
            printf = self.Functions['printf']
        else:
            printfType = ir.FunctionType(int32, [ir.PointerType(int8)], var_arg = True)
            printf = ir.Function(self.Module, printfType, name = "printf")
            self.Functions['printf'] = printf

        TheBuilder = self.Builders[-1]
        zero = ir.Constant(int32, 0)

        #就一个变量
        if tree.getChildCount() == 4:
            ParameterInfo = self.visit(tree.getChild(2)) 
            Argument = TheBuilder.gep(ParameterInfo['name'], [zero, zero], inbounds = True)
            ReturnVariableName = TheBuilder.call(printf, [Argument])
        else:
            ParameterInfo = self.visit(tree.getChild(2))
            Arguments = [TheBuilder.gep(ParameterInfo['name'], [zero, zero], inbounds = True)]

            Length = tree.getChildCount()
            i = 4
            while i < Length - 1:
                OneParameter = self.visit(tree.getChild(i))
                Arguments.append(OneParameter['name'])
                i += 2
            ReturnVariableName = TheBuilder.call(printf, Arguments)
        Result = {'type': int32, 'name': ReturnVariableName}
        return Result


    def visitScanfFunc(self, tree):
        '''
        语法规则：scanfFunc : 'scanf' '(' mSTRING (','('&')?(mID|arrayItem|structMember))* ')';
        描述：scanf函数
        返回：函数返回值
        '''        
        if 'scanf' in self.Functions:
            scanf = self.Functions['scanf']
        else:
            scanfType = ir.FunctionType(int32, [ir.PointerType(int8)], var_arg = True)
            scanf = ir.Function(self.Module, scanfType, name="scanf")
            self.Functions['scanf'] = scanf

        TheBuilder = self.Builders[-1]
        zero = ir.Constant(int32, 0)
        ParameterList = self.visit(tree.getChild(2)) # MString
        Arguments = [TheBuilder.gep(ParameterList['name'], [zero, zero], inbounds = True)]

        Length = tree.getChildCount()
        i = 4
        while i < Length - 1:
            if tree.getChild(i).getText() == '&':
                #读取变量
                PreviousNeedLoad = self.WhetherNeedLoad
                self.WhetherNeedLoad = False
                TheParameter = self.visit(tree.getChild(i + 1))
                self.WhetherNeedLoad = PreviousNeedLoad
                Arguments.append(TheParameter['name'])
                i += 3
            else:
                PreviousNeedLoad = self.WhetherNeedLoad
                self.WhetherNeedLoad = True
                TheParameter = self.visit(tree.getChild(i))
                self.WhetherNeedLoad = PreviousNeedLoad
                Arguments.append(TheParameter['name'])
                i += 2

        ReturnVariableName = TheBuilder.call(scanf, Arguments)
        Result = {'type': int32, 'name': ReturnVariableName}
        return Result


    def visitGetsFunc(self, tree):
        '''
        语法规则：getsFunc : 'gets' '(' mID ')';
        描述：gets函数
        返回：函数返回值
        '''        
        if 'gets' in self.Functions:
            gets = self.Functions['gets']
        else:
            getsType = ir.FunctionType(int32, [], var_arg = True)
            gets = ir.Function(self.Module, getsType, name = "gets")
            self.Functions['gets'] = gets

        TheBuilder = self.Builders[-1]
        zero = ir.Constant(int32, 0)

        PreviousNeedLoad = self.WhetherNeedLoad
        self.WhetherNeedLoad = False
        ParameterInfo = self.visit(tree.getChild(2))
        self.WhetherNeedLoad = PreviousNeedLoad

        Arguments = [TheBuilder.gep(ParameterInfo['name'], [zero, zero], inbounds = True)]
        ReturnVariableName = TheBuilder.call(gets, Arguments)
        Result = {'type': int32, 'name': ReturnVariableName}
        return Result


    def visitSelfDefinedFunc(self, tree):
        '''
        语法规则：selfDefinedFunc : mID '('((argument|mID)(','(argument|mID))*)? ')';
        描述：自定义函数
        返回：函数返回值
        '''
        TheBuilder = self.Builders[-1]
        FunctionName = tree.getChild(0).getText() # func name
        if FunctionName in self.Functions:
            TheFunction = self.Functions[FunctionName]

            Length = tree.getChildCount()
            ParameterList = []
            i = 2
            while i < Length - 1:
                TheParameter = self.visit(tree.getChild(i))
                TheParameter = self.assignConvert(TheParameter, TheFunction.args[i // 2 - 1].type)
                ParameterList.append(TheParameter['name'])
                i += 2
            ReturnVariableName = TheBuilder.call(TheFunction, ParameterList)
            Result = {'type': TheFunction.function_type.return_type, 'name': ReturnVariableName}
            return Result
        else:
            raise SemanticError(ctx=tree,msg="函数未定义！")

    #语句块相关函数
    def visitBlock(self, tree):
        '''
        语法规则：block : initialBlock | arrayInitBlock | structInitBlock | assignBlock | ifBlocks | whileBlock | forBlock | returnBlock;
        描述：语句块
        返回：无
        '''
        for i in range(tree.getChildCount()):
            self.visit(tree.getChild(i))
        return

    def visitInitialBlock(self, tree):
        '''
        语法规则：initialBlock : (mType) mID ('=' expr)? (',' mID ('=' expr)?)* ';';
        描述：初始化语句块
        返回：无
        '''
        #初始化全局变量
        ParameterType = self.visit(tree.getChild(0))
        Length = tree.getChildCount()
        
        i = 1
        while i < Length:
            IDname = tree.getChild(i).getText()
            if self.SymbolTable.JudgeWhetherGlobal() == True:   
                NewVariable = ir.GlobalVariable(self.Module, ParameterType, name = IDname)
                NewVariable.linkage = 'internal'
            else:
                TheBuilder = self.Builders[-1]
                NewVariable = TheBuilder.alloca(ParameterType, name = IDname)
            TheVariable = {}
            TheVariable["Type"] = ParameterType
            TheVariable["Name"] = NewVariable
            TheResult = self.SymbolTable.AddItem(IDname, TheVariable)
            if TheResult["result"] != "success":
                raise SemanticError(ctx=tree,msg=TheResult["reason"])

            if tree.getChild(i + 1).getText() != '=':
                i += 2
            else:
                #初始化
                Value = self.visit(tree.getChild(i + 2))
                if self.SymbolTable.JudgeWhetherGlobal() == True:   
                    #全局变量
                    NewVariable.initializer = ir.Constant(Value['type'], Value['name'].constant)
                    #print(Value['name'].constant)
                else:
                    #局部变量，可能有强制类型转换
                    Value = self.assignConvert(Value, ParameterType)
                    TheBuilder = self.Builders[-1]
                    TheBuilder.store(Value['name'], NewVariable)
                i += 4
        return

    def visitArrayInitBlock(self, tree):
        '''
        语法规则：arrayInitBlock : mType mID '[' mINT ']'';'; 
        描述：数组初始化块
        返回：无
        '''
        Type = self.visit(tree.getChild(0))
        IDname = tree.getChild(1).getText()
        Length = int(tree.getChild(3).getText())

        if self.SymbolTable.JudgeWhetherGlobal() == True:   
            #全局变量
            NewVariable = ir.GlobalVariable(self.Module, ir.ArrayType(Type, Length), name = IDname)
            NewVariable.linkage = 'internal'
        else:
            TheBuilder = self.Builders[-1]
            NewVariable = TheBuilder.alloca(ir.ArrayType(Type, Length), name = IDname)

        TheVariable = {}
        TheVariable["Type"] = ir.ArrayType(Type, Length)
        TheVariable["Name"] = NewVariable
        TheResult = self.SymbolTable.AddItem(IDname, TheVariable)
        if TheResult["result"] != "success":
            raise SemanticError(ctx=tree,msg=TheResult["reason"])
        return

    def visitAssignBlock(self, tree):
        '''
        语法规则：assignBlock : ((arrayItem|mID|structMember) '=')+  expr ';';
        描述：赋值语句块
        返回：无
        '''
        TheBuilder = self.Builders[-1]
        Length = tree.getChildCount()
        IDname = tree.getChild(0).getText()
        if not '[' in IDname and self.SymbolTable.JudgeExist(IDname) == False:
            raise SemanticError(ctx=tree,msg="变量未定义！")

        #待赋值结果 
        ValueToBeAssigned = self.visit(tree.getChild(Length - 2))

        i = 0
        Result = {'type': ValueToBeAssigned['type'], 'name': ValueToBeAssigned['name']}
        #遍历全部左边变量赋值
        while i < Length - 2:
            PreviousNeedLoad = self.WhetherNeedLoad
            self.WhetherNeedLoad = False
            TheVariable = self.visit(tree.getChild(i))
            self.WhetherNeedLoad = PreviousNeedLoad

            TheValueToBeAssigned = ValueToBeAssigned
            TheValueToBeAssigned = self.assignConvert(TheValueToBeAssigned, TheVariable['type'])
            TheBuilder.store(TheValueToBeAssigned['name'], TheVariable['name'])
            if i > 0:
                ReturnVariable = TheBuilder.load(TheVariable['name'])
                Result = {'type': TheVariable['type'], 'name': ReturnVariable}
            i += 2
        return Result

    #TODO
    def visitCondition(self, tree):
        '''
        语法规则：condition :  expr;
        描述：判断条件
        返回：无
        '''
        result = self.visit(tree.getChild(0))
        return self.toBoolean(result, notFlag=False)

    def visitIfBlocks(self, tree):
        '''
        语法规则：ifBlocks : ifBlock (elifBlock)* (elseBlock)?;
        描述：if语句块
        返回：无
        '''
        #增加两个block，对应If分支和If结束后的分支
        TheBuilder = self.Builders[-1]
        IfBlock = TheBuilder.append_basic_block()
        EndifBlock = TheBuilder.append_basic_block()
        TheBuilder.branch(IfBlock)

        #载入IfBlock
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(IfBlock)
        self.Builders.append(ir.IRBuilder(IfBlock))

        tmp = self.EndifBlock
        self.EndifBlock = EndifBlock
        Length = tree.getChildCount()
        for i in range(Length):
            self.visit(tree.getChild(i))  #分别处理每个if ,elseif, else块
        self.EndifBlock = tmp

        #结束后导向EndIf块
        blockTemp = self.Blocks.pop()
        builderTemp = self.Builders.pop()
        if not blockTemp.is_terminated:
            builderTemp.branch(EndifBlock)

        self.Blocks.append(EndifBlock)
        self.Builders.append(ir.IRBuilder(EndifBlock))
        return


    def visitIfBlock(self, tree):
        '''
        语法规则：ifBlock : 'if' '(' condition ')' '{' body '}';
        描述：单一if语句块
        返回：无
        '''
        #在If块中，有True和False两种可能的导向
        self.SymbolTable.EnterScope()
        TheBuilder = self.Builders[-1]
        TrueBlock = TheBuilder.append_basic_block()
        FalseBlock = TheBuilder.append_basic_block()

        #根据condition结果转向某个代码块
        result = self.visit(tree.getChild(2))
        TheBuilder.cbranch(result['name'], TrueBlock, FalseBlock)

        #如果condition为真，处理TrueBlock,即body部分
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(TrueBlock)
        self.Builders.append(ir.IRBuilder(TrueBlock))
        self.visit(tree.getChild(5)) # body

        if not self.Blocks[-1].is_terminated:
            self.Builders[-1].branch(self.EndifBlock)

        #处理condition为假的代码部分
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(FalseBlock)
        self.Builders.append(ir.IRBuilder(FalseBlock))
        self.SymbolTable.QuitScope()
        return


    def visitElifBlock(self, tree):
        '''
        语法规则：elifBlock : 'else' 'if' '(' condition ')' '{' body '}';
        描述：单一elseif语句块
        返回：无
        '''
        #在ElseIf块中，有True和False两种可能的导向
        self.SymbolTable.EnterScope()
        TheBuilder = self.Builders[-1]
        TrueBlock = TheBuilder.append_basic_block()
        FalseBlock = TheBuilder.append_basic_block()

        #根据condition结果转向某个代码块
        result = self.visit(tree.getChild(3))
        TheBuilder.cbranch(result['name'], TrueBlock, FalseBlock)
        
        #如果condition为真，处理TrueBlock,即body部分
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(TrueBlock)
        self.Builders.append(ir.IRBuilder(TrueBlock))
        self.visit(tree.getChild(6)) # body

        if not self.Blocks[-1].is_terminated:
            self.Builders[-1].branch(self.EndifBlock)
        
        #处理condition为假的代码部分
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(FalseBlock)
        self.Builders.append(ir.IRBuilder(FalseBlock))
        self.SymbolTable.QuitScope()
        return