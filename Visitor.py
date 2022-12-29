import sys, json
from .LRParser import ParserTree
from llvmlite import ir

double = ir.DoubleType()
int1 = ir.IntType(1)
int32 = ir.IntType(32)
int8 = ir.IntType(8)
void = ir.VoidType()

class SymbolTable:
    def __init__(self):
        self.Table = [{}]
        self.CurrentLevel = 0
        
    def Insert(self, key, value):
        if key in self.Table[self.CurrentLevel]:
            raise Exception("Redefine", key)
        self.Table[self.CurrentLevel][key] = value

    def Get(self, key):
        i = self.CurrentLevel
        while i >= 0:
            if key in self.Table[i]:
                return self.Table[i][key]
            i -= 1
        return None


    def isIn(self, item):
        i = self.CurrentLevel
        while i >= 0:
            if item in self.Table[i]:
                return True
            i -= 1
        return False        

    def addLevel(self):
        self.CurrentLevel += 1
        self.Table.append({})

    def minusLevel(self):
        if self.CurrentLevel == 0:
            raise Exception("Wrong Level")
        self.Table.pop()
        self.CurrentLevel -= 1
    
    def isGlobal(self):
        return self.CurrentLevel == 0

class Visitor:
    def __init__(self, tree):
        
        self.tree = tree
        self.SymbolTable = SymbolTable()

        self.Module = ir.Module()
        self.Module.triple = sys.platform
        self.Module.data_layout = 'e-m:e-i64:64-f80:128-n8:16:32:64-S128'        
        
        #语句块
        self.Blocks = []

        #待生成的llvm语句块
        self.Builders = []

        #函数列表
        self.Functions = dict()

        #当前所在函数
        self.CurrentFunction = ''
        self.Constants = 0

        #这个变量是否需要加载
        self.WhetherNeedLoad = True
        
        #endif块
        self.EndifBlock = None
        
        
    def visit(self,node):
        tree = self.tree
        tree.current_node = node
        self.visit_node(tree)

    def visit_node(self, tree):
        try:
            eval('self.visit_' + tree.current_node.type + '(tree)')
        except AttributeError:
            for i in range(tree.getChildCount()):
                self.visit(tree.getChild(i))

    def get_result(self):
        return self.result

    # def visit_translation_unit(self, tree):
    #     for i in range(tree.getChildCount()):
    #         self.visit(tree.getChild(i))
    
    # def visit_primary_expression(self, tree):
    #     if tree.getChildCount() == 1:
    #         return self.visit(tree.getChild(0))
    #     else:
    #         return self.visit(tree.getChild(1))
            
    # def visit_postfix_expression(self, tree):
    #     if tree.getChildCount() == 1:
    #         return self.visit(tree.getChild(0)) # primary_expression
    #     if tree.getChildCount() == 3:
    #         return self.visit(tree.getChild(0))
    #     if tree.getChildCount() == 4:
    #         self.visit(tree.getChild(0)) 
    #         self.visit(tree.getChild(2)) # expression
    #         return 
            
    # def visit_argument_expression_list(self, tree):
    #     if tree.getChildCount() == 1:
    #         self.visit(tree.getChild(0))
    #     else:
    #         self.visit(tree.getChild(0)) 
    #         self.visit(tree.getChild(2))
            
    # def visit_compound_statement(self, tree):
    #     if tree.getChildCount() == 2:
    #         return
    #     else:
    #         self.visit(tree.getChild(1))
        
        
    # def visit_function_definition(self, tree):
    #     ReturnType = self.visit(tree.getChild(0))
    #     FunctionName = tree.getChild(1).getText()
    #     ParameterList = self.visit(tree.getChild(3))
    #     ParameterTypeList = []
    #     for i in range(len(ParameterList)):
    #         ParameterTypeList.append(ParameterList[i]['type'])
            
    
    def visit_translationUnit(self, tree):
        for i in range(tree.getChildCount()):
            self.visit(tree.getChild(i))

    #函数相关函数
    def visit_functionDefine(self, tree):
    
        '''
        语法规则：mFunction : (mType|mVoid|mStruct) mID '(' params ')' '{' funcBody '}';
        描述：函数的定义
        返回：无
        '''
        ReturnType = self.visit(tree.getChild(0)) # mtype
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
            raise Exception("函数重定义错误！")
        else:
            self.Functions[FunctionName] = LLVMFunction

        TheBuilder = ir.IRBuilder(TheBlock)
        self.Blocks.append(TheBlock)
        self.Builders.append(TheBuilder)

        #进一层
        self.CurrentFunction = FunctionName
        self.SymbolTable.addLevel()

        #存储函数的变量
        VariableList = {}
        for i in range(len(ParameterList)):
            NewVariable = TheBuilder.alloca(ParameterList[i]['type'])
            TheBuilder.store(LLVMFunction.args[i], NewVariable)
            TheVariable = {}
            TheVariable["Type"] = ParameterList[i]['type']
            TheVariable["Name"] = NewVariable
            self.SymbolTable.Insert(ParameterList[i]['IDname'], TheVariable)


        #处理函数body
        self.visit(tree.getChild(6)) # func body

        #处理完毕，退一层
        self.CurrentFunction = ''
        self.Blocks.pop()
        self.Builders.pop()
        self.SymbolTable.minusLevel()
        return

    def visit_params(self, tree):
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

    def visit_param(self, tree):
        '''
        语法规则：param : mType mID;
        描述：返回函数参数
        返回：一个字典，字典的Type是类型，Name是参数名
        '''
        Type = self.visit(tree.getChild(0))
        IDname = tree.getChild(1).getText()
        Result = {'type': Type, 'IDname': IDname}
        return Result

    def visit_funcBody(self, tree):
        '''
        语法规则：funcBody : body returnBlock;
        描述：函数体
        返回：无
        '''
        self.SymbolTable.addLevel()
        for index in range(tree.getChildCount()):
            self.visit(tree.getChild(index))
        self.SymbolTable.minusLevel()
        return

    def visit_body(self, tree):
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

    #语句块相关函数
    def visit_blocks(self, tree):
        '''
        语法规则：block : initialBlock | arrayInitBlock | structInitBlock | assignBlock | ifBlocks | whileBlock | forBlock | returnBlock;
        描述：语句块
        返回：无
        '''
        for i in range(tree.getChildCount()):
            self.visit(tree.getChild(i))
        return

    def visit_initialBlock(self, tree):
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
            if self.SymbolTable.isGlobal() == True:   
                NewVariable = ir.GlobalVariable(self.Module, ParameterType, name = IDname)
                NewVariable.linkage = 'internal'
            else:
                TheBuilder = self.Builders[-1]
                NewVariable = TheBuilder.alloca(ParameterType, name = IDname)
            TheVariable = {}
            TheVariable["Type"] = ParameterType
            TheVariable["Name"] = NewVariable
            self.SymbolTable.Insert(IDname, TheVariable)


            if tree.getChild(i + 1).getText() != '=':
                i += 2
            else:
                #初始化
                Value = self.visit(tree.getChild(i + 2))
                if self.SymbolTable.isGlobal() == True:   
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

    def visit_arrayInitBlock(self, tree):
        '''
        语法规则：arrayInitBlock : mType mID '[' mINT ']'';'; 
        描述：数组初始化块
        返回：无
        '''
        Type = self.visit(tree.getChild(0))
        IDname = tree.getChild(1).getText()
        Length = int(tree.getChild(3).getText())

        if self.SymbolTable.isGlobal() == True:   
            #全局变量
            NewVariable = ir.GlobalVariable(self.Module, ir.ArrayType(Type, Length), name = IDname)
            NewVariable.linkage = 'internal'
        else:
            TheBuilder = self.Builders[-1]
            NewVariable = TheBuilder.alloca(ir.ArrayType(Type, Length), name = IDname)

        TheVariable = {}
        TheVariable["Type"] = ir.ArrayType(Type, Length)
        TheVariable["Name"] = NewVariable
        self.SymbolTable.Insert(IDname, TheVariable)
        return

    def visit_assignBlock(self, tree):
        '''
        语法规则：assignBlock : ((arrayItem|mID|structMember) '=')+  expr ';';
        描述：赋值语句块
        返回：无
        '''
        TheBuilder = self.Builders[-1]
        Length = tree.getChildCount()
        IDname = tree.getChild(0).getText()
        if not '[' in IDname and self.SymbolTable.isIn(IDname) == False:
            raise Exception("变量未定义！")

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

    def visit_condition(self, tree):
        '''
        语法规则：condition :  expr;
        描述：判断条件
        返回：无
        '''
        result = self.visit(tree.getChild(0))
        return self.toBoolean(result, notFlag=False)

    def visit_ifBlocks(self, tree):
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


    def visit_ifBlock(self, tree):
        '''
        语法规则：ifBlock : 'if' '(' condition ')' '{' body '}';
        描述：单一if语句块
        返回：无
        '''
        #在If块中，有True和False两种可能的导向
        self.SymbolTable.addLevel()
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
        self.SymbolTable.minusLevel()
        return


    def visit_elifBlock(self, tree):
        '''
        语法规则：elifBlock : 'else' 'if' '(' condition ')' '{' body '}';
        描述：单一elseif语句块
        返回：无
        '''
        #在ElseIf块中，有True和False两种可能的导向
        self.SymbolTable.addLevel()
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
        self.SymbolTable.minusLevel()
        return

    def visit_elseBlock(self, tree):
        '''
        语法规则：elseBlock : 'else' '{' body '}';
        描述：单一else语句块
        返回：无
        '''
        #Else分块直接处理body内容
        self.SymbolTable.addLevel()
        self.visit(tree.getChild(2)) # body
        self.SymbolTable.minusLevel()
        return

    def visit_forBlock(self, tree):
        '''
        语法规则：forBlock : 'for' '(' for1Block  ';' condition ';' for3Block ')' ('{' body '}'|';');
        描述：for语句块
        返回：无
        '''
        self.SymbolTable.addLevel()
        
        #for循环首先初始化局部变量
        self.visit(tree.getChild(2))
        #for循环的三种block
        TheBuilder = self.Builders[-1]
        ForCondition = TheBuilder.append_basic_block()
        ForBody = TheBuilder.append_basic_block()
        ForEnd = TheBuilder.append_basic_block()
       
        #判断condition
        TheBuilder.branch(ForCondition)
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(ForCondition)
        self.Builders.append(ir.IRBuilder(ForCondition))
                
        #根据condition结果决定跳转到body或者结束
        result = self.visit(tree.getChild(4)) # condition block
        self.Builders[-1].cbranch(result['name'], ForBody, ForEnd)
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(ForBody)
        self.Builders.append(ir.IRBuilder(ForBody))

        #处理body
        if (tree.getChildCount() == 11):
            self.visit(tree.getChild(9)) # main body
        
        #处理step语句
        self.visit(tree.getChild(6)) # step block
        
        #一次循环后重新判断condition
        self.Builders[-1].branch(ForCondition)

        #结束循环
        self.Blocks.pop()
        self.Builders.pop()
        self.Blocks.append(ForEnd)
        self.Builders.append(ir.IRBuilder(ForEnd))
        self.SymbolTable.minusLevel()
        return

    def visit_for1Block(self, tree):
        '''
        语法规则：for1Block :  mID '=' expr (',' for1Block)?|;
        描述：for语句块的第一个参数
        返回：无
        '''
        #初始化参数为空
        Length = tree.getChildCount()
        if Length == 0:
            return

        TmpNeedLoad = self.WhetherNeedLoad
        self.WhetherNeedLoad = False
        result0 = self.visit(tree.getChild(0)) # mID
        self.WhetherNeedLoad = TmpNeedLoad
        
        #访问表达式
        result1 = self.visit(tree.getChild(2)) # expr
        result1 = self.assignConvert(result1, result0['type'])
        self.Builders[-1].store(result1['name'], result0['name'])

        if Length > 3:
            self.visit(tree.getChild(4))
        return


    def visit_for3Block(self, tree):
        '''
        语法规则：for3Block : mID '=' expr (',' for3Block)?|;
        描述：for语句块的第三个参数
        返回：无
        '''
        Length = tree.getChildCount()
        if Length == 0:
            return
            
        TmpNeedLoad = self.WhetherNeedLoad
        self.WhetherNeedLoad = False
        result0 = self.visit(tree.getChild(0))
        self.WhetherNeedLoad = TmpNeedLoad

        result1 = self.visit(tree.getChild(2))
        result1 = self.assignConvert(result1, result0['type'])
        self.Builders[-1].store(result1['name'], result0['name'])

        if Length > 3:
            self.visit(tree.getChild(4))
        return


    def visit_returnBlock(self, tree):
        '''
        语法规则：returnBlock : 'return' (mINT|mID)? ';';
        描述：return语句块
        返回：无
        '''
        #返回空
        if tree.getChildCount() == 2:
            RealReturnValue = self.Builders[-1].ret_void()
            JudgeTruth = False
            return {
                    'type': void,
                    'const': JudgeTruth,
                    'name': RealReturnValue
            }

        #访问返回值
        ReturnIndex = self.visit(tree.getChild(1))
        RealReturnValue = self.Builders[-1].ret(ReturnIndex['name'])
        JudgeTruth = False
        return {
                'type': void,
                'const': JudgeTruth,
                'name': RealReturnValue
        }


    #运算和表达式求值，类型转换相关函数
    def assignConvert(self, CalcIndex, DType):
        if (CalcIndex['type'] == DType):
            return CalcIndex
        if self.isInteger(CalcIndex['type']) and self.isInteger(DType):
            if (CalcIndex['type'] == int1):
                CalcIndex = self.convertIIZ(CalcIndex, DType)
            else:
                CalcIndex = self.convertIIS(CalcIndex, DType)
        elif self.isInteger(CalcIndex['type']) and DType == double:
            CalcIndex = self.convertIDS(CalcIndex)
        elif self.isInteger(DType) and CalcIndex['type'] == double:
            CalcIndex = self.convertDIS(CalcIndex)
        return CalcIndex
    
    def convertIIZ(self, CalcIndex, DType):
        Builder = self.Builders[-1]
        ConfirmedVal = Builder.zext(CalcIndex['name'], DType)
        JudgeReg = False
        return {
                'type': DType,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    def convertIIS(self, CalcIndex, DType):
        Builder = self.Builders[-1]
        ConfirmedVal = Builder.sext(CalcIndex['name'], DType)
        JudgeReg = False
        return {
                'type': DType,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    def convertDIS(self, CalcIndex, DType):
        Builder = self.Builders[-1]
        ConfirmedVal = Builder.fptosi(CalcIndex['name'], DType)
        JudgeReg = False
        return {
                'type': DType,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    def convertDIU(self, CalcIndex, DType):
        Builder = self.Builders[-1]
        ConfirmedVal = Builder.fptoui(CalcIndex['name'], DType)
        JudgeReg = False
        return {
                'type': DType,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    def convertIDS(self, CalcIndex):
        Builder = self.Builders[-1]
        ConfirmedVal = Builder.sitofp(CalcIndex['name'], double)
        JudgeReg = False
        return {
                'type': double,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    def convertIDU(self, CalcIndex):
        Builder = self.Builders[-1]
        JudgeReg = False
        ConfirmedVal = Builder.uitofp(CalcIndex['name'], double)
        return {
                'type': double,
                'const': JudgeReg,
                'name': ConfirmedVal
        }

    # 类型转换至布尔型
    def toBoolean(self, ManipulateIndex, notFlag = True):
        Builder = self.Builders[-1]
        if notFlag:
            OperationChar = '=='
        else:
            OperationChar = '!='
        if ManipulateIndex['type'] == int8 or ManipulateIndex['type'] == int32:
            RealReturnValue = Builder.icmp_signed(OperationChar, ManipulateIndex['name'], ir.Constant(ManipulateIndex['type'], 0))
            return {
                    'tpye': int1,
                    'const': False,
                    'name': RealReturnValue
            }
        elif ManipulateIndex['type'] == double:
            RealReturnValue = Builder.fcmp_ordered(OperationChar, ManipulateIndex['name'], ir.Constant(double, 0))
            return {
                    'tpye': int1,
                    'const': False,
                    'name': RealReturnValue
            }
        return ManipulateIndex

    def isInteger(self, typ):
        ReturnValue = 'width'
        return hasattr(typ, ReturnValue)

    def exprConvert(self, Index1, Index2):
        if Index1['type'] == Index2['type']:
            return Index1, Index2
        if self.isInteger(Index1['type']) and self.isInteger(Index2['type']):
            if Index1['type'].width < Index2['type'].width:
                if Index1['type'].width == 1:
                    Index1 = self.convertIIZ(Index1, Index2['type'])
                else:
                    Index1 = self.convertIIS(Index1, Index2['type'])
            else:
                if Index2['type'].width == 1:
                    Index2 = self.convertIIZ(Index2, Index1['type'])
                else:
                    Index2 = self.convertIIS(Index2, Index1['type'])
        elif self.isInteger(Index1['type']) and Index2['type'] == double:
            Index1 = self.convertIDS(Index1, Index2['type'])
        elif self.isInteger(Index2['type']) and Index1['type'] == double:
            Index2 = self.convertIDS(Index2, Index1['type'])
        else:
            raise Exception("类型不匹配")
        return Index1, Index2

    
    def visit_primaryExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit(tree.getChild(1))
        return ReturnValue
    
    def visit_postfixExpr(self, tree):
        return self.visit(tree.getChild(0))
    
    def visit_unaryExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 2:
            nodetype = tree.getChild(0).type
            if nodetype == 'Minus':
                ReturnValue = self.visit_MinusLiteral(tree)
            elif nodetype == 'Not':
                ReturnValue = self.visit_Not(tree)
        return ReturnValue
    
    def visit_multExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit_MulDiv(tree)
        return ReturnValue
    
    def visit_addExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit_AddSub(tree)
        return ReturnValue
    
    def visit_relaExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.self.visit_relop(tree)
        return ReturnValue
    
    def visit_equalExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit_eqop(tree)
        return ReturnValue
    
    def visit_andExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit_AndAnd(tree)
        return ReturnValue
    
    def visit_orExpr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        elif chs == 3:
            ReturnValue = self.visit_OrOr(tree)
        return ReturnValue
    
            
    def visit_expr(self, tree):
        chs = tree.getChildCount()
        if chs == 1:
            ReturnValue = self.visit(tree.getChild(0))
        return ReturnValue
            
    
    # def visit_expr(self, tree):
    #     chs = tree.getChildCount()
    #     if chs == 1:
    #         nodetype = tree.getChild(0).type
    #         if nodetype == 'arrayItem':
    #             ReturnValue = self.visit_Array_exp(tree)
    #         ReturnValue = self.visit(tree.getChild(0))
    #     elif chs == 2:
    #         nodetype = tree.getChild(0).type
    #         if nodetype == 'Minus':
    #             ReturnValue = self.visit_MinusLiteral(tree)
    #         elif nodetype == 'Not':
    #             ReturnValue = self.visit_Not(tree)
    #     elif chs == 3:
    #         nodetype = tree.getChild(0).type
    #         if nodetype in {'Star', 'Div', 'Mod'}:
    #             ReturnValue = self.visit_MulDiv(tree)
    #         elif nodetype in {'Plus', 'Minus'}:
    #             ReturnValue = self.visit_AddSub(tree)
    #         elif nodetype in {'Equal', 'NotEqual', 'Less',
    #          'Greater', 'LessEqual', 'GreaterEqual'}:
    #             ReturnValue = self.visit_relop(tree)
    #         elif nodetype == 'AndAnd':
    #             ReturnValue = self.visit_AndAnd(tree)
    #         elif nodetype == 'OrOr':
    #             ReturnValue = self.visit_OrOr(tree)
    #         elif nodetype == 'LeftParen':
    #             ReturnValue = self.visit_Parens(tree)
    #     else:
    #         # raise error
    #         pass

    #     return ReturnValue

    def visit_Parens(self, tree):
        '''
        语法规则：expr : '(' expr ')'
        描述：括号
        返回：无
        '''
        return self.visit(tree.getChild(1))

    def visit_Not(self, tree):
        '''
        语法规则：expr :  op='!' expr
        描述：非运算
        返回：无
        '''
        RealReturnValue = self.visit(tree.getChild(1))
        RealReturnValue = self.toBoolean(RealReturnValue, notFlag = True)
        # res 未返回
        return self.visitChildren(tree)

    def visit_MulDiv(self, tree):
        '''
        语法规则：expr : expr op=('*' | '/' | '%') expr
        描述：乘除
        返回：无
        '''
        Builder = self.Builders[-1]
        Index1 = self.visit(tree.getChild(0))
        Index2 = self.visit(tree.getChild(2))
        Index1, Index2 = self.exprConvert(Index1, Index2)
        JudgeReg = False
        if tree.getChild(1).getText() == '*':
            RealReturnValue = Builder.mul(Index1['name'], Index2['name'])
        elif tree.getChild(1).getText() == '/':
            RealReturnValue = Builder.sdiv(Index1['name'], Index2['name'])
        elif tree.getChild(1).getText() == '%':
            RealReturnValue = Builder.srem(Index1['name'], Index2['name'])
        return {
                'type': Index1['type'],
                'const': JudgeReg,
                'name': RealReturnValue
        }


    def visit_AddSub(self, tree):
        '''
        语法规则：expr op=('+' | '-') expr 
        描述：加减
        返回：无
        '''
        Builder = self.Builders[-1]
        Index1 = self.visit(tree.getChild(0))
        Index2 = self.visit(tree.getChild(2))
        Index1, Index2 = self.exprConvert(Index1, Index2)
        JudgeReg = False
        if tree.getChild(1).getText() == '+':
            RealReturnValue = Builder.add(Index1['name'], Index2['name'])
        elif tree.getChild(1).getText() == '-':
            RealReturnValue = Builder.sub(Index1['name'], Index2['name'])
        return {
                'type': Index1['type'],
                'const': JudgeReg,
                'name': RealReturnValue
        }


# Minus
    def visit_MinusLiteral(self, tree):
        '''
        语法规则：(op='-')? (mINT|mDOUBLE)
        描述：int类型
        返回：无
        '''
        if tree.getChild(0).getText() == '-':
            IndexMid = self.visit(tree.getChild(1))
            Builder = self.Builders[-1]
            RealReturnValue = Builder.neg(IndexMid['name'])
            return {
                    'type': IndexMid['type'],
                    'name': RealReturnValue
            }
        return self.visit(tree.getChild(0))


    def visit_AndAnd(self, tree):
        '''
        语法规则：expr : expr '&&' expr 
        描述：且运算
        返回：无
        '''
        Index1 = self.visit(tree.getChild(0))
        Index1 = self.toBoolean(Index1, notFlag=False)
        Index2 = self.visit(tree.getChild(2))
        Index2 = self.toBoolean(Index2, notFlag=False)
        Builder = self.Builders[-1]
        JudgeReg = False
        RealReturnValue = Builder.and_(Index1['name'], Index2['name'])
        return {
                'type': Index1['type'],
                'const': JudgeReg,
                'name': RealReturnValue
        }

    def visit_OrOr(self, tree):
        '''
        语法规则：expr : expr '||' expr 
        描述：或运算
        返回：无
        '''
        Index1 = self.visit(tree.getChild(0))
        Index1 = self.toBoolean(Index1, notFlag=False)
        Index2 = self.visit(tree.getChild(2))
        Index2 = self.toBoolean(Index2, notFlag=False)
        Builder = self.Builders[-1]
        RealReturnValue = Builder.or_(Index1['name'], Index2['name'])
        return {
                'type': Index1['type'],
                'const': False,
                'name': RealReturnValue
        }


    def visit_arrayItem(self, tree):
        '''
        语法规则：mArray : mID '[' mINT ']'; 
        描述：数组类型
        返回：无
        '''
        return {
            'IDname': tree.getChild(0).getText(),
            'length': int(tree.getChild(2).getText())
        }

    def visit_relop(self, tree):
        '''
        语法规则：expr : expr op=('==' | '!=' | '<' | '<=' | '>' | '>=') expr
        描述：比较
        返回：无
        '''
        Builder = self.Builders[-1]
        Index1 = self.visit(tree.getChild(0))
        Index2 = self.visit(tree.getChild(2))
        Index1, Index2 = self.exprConvert(Index1, Index2)
        OperationChar = tree.getChild(1).getText()
        JudgeReg = False
        if Index1['type'] == double:
            RealReturnValue = Builder.fcmp_ordered(OperationChar, Index1['name'], Index2['name'])
        elif self.isInteger(Index1['type']):
            RealReturnValue = Builder.icmp_signed(OperationChar, Index1['name'], Index2['name'])
        return {
                'type': int1,
                'const': JudgeReg,
                'name': RealReturnValue
        }

    #变量和变量类型相关函数
    def visit_typeDef(self, tree):
        '''
        语法规则：mType : 'int'| 'double'| 'char'| 'string';
        描述：类型主函数
        返回：无
        '''
        if tree.getText() == 'Int':
            return int32
        if tree.getText() == 'Char':
            return int8
        if tree.getText() == 'Double':
            return double
        return void

    def visit_Array_exp(self, tree):
        '''
        语法规则：expr : arrayItem 
        描述：数组元素
        返回：无
        '''
        TempRequireLoad = self.WhetherNeedLoad
        self.WhetherNeedLoad = False
        res = self.visit(tree.getChild(0)) # mID
        # print("res is", res)
        JudgeReg = False
        self.WhetherNeedLoad = TempRequireLoad
        
        if isinstance(res['type'], ir.types.ArrayType):
            Builder = self.Builders[-1]

            TempRequireLoad = self.WhetherNeedLoad
            self.WhetherNeedLoad = True
            IndexRe1 = self.visit(tree.getChild(2)) # subscript
            self.WhetherNeedLoad = TempRequireLoad
            
            Int32Zero = ir.Constant(int32, 0)
            RealReturnValue = Builder.gep(res['name'], [Int32Zero, IndexRe1['name']], inbounds=True)
            if self.WhetherNeedLoad:
                RealReturnValue = Builder.load(RealReturnValue)
            return {
                    'type': res['type'].element,
                    'const': JudgeReg,
                    'name': RealReturnValue,
                    'struct_name': res['struct_name'] if 'struct_name' in res else None
            }
        else:   # error!
            raise Exception("类型错误")

#调用函数相关函数
    def visit_func(self, tree):
        '''
        语法规则：func : (strlenFunc | atoiFunc | printfFunc | scanfFunc | getsFunc | selfDefinedFunc);
        描述：函数
        返回：无
        '''
        return self.visit(tree.getChild(0))

    def visit_strlenRef(self, tree):
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

    def visit_printfRef(self, tree):
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


    def visit_getsRef(self, tree):
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


    def visit_funcRef(self, tree):
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
            raise Exception("函数未定义！")


    def visit_literal(self, tree):
        '''
        语法规则：argument : mINT | mDOUBLE | mCHAR | mSTRING;
        描述：函数参数
        返回：无
        '''
        return self.visit(tree.getChild(0))

    def visit_Identifier(self, tree):
        '''
        语法规则：mID : ID;
        描述：ID
        返回：无
        '''
        IDname = tree.getText()
        JudgeReg = False
        if self.SymbolTable.isIn(IDname) != True:
           return {
                'type': int32,
                'const': JudgeReg,
                'name': ir.Constant(int32, None)
            }
        Builder = self.Builders[-1]
        TheItem = self.SymbolTable.Get(IDname)
        # print(TheItem)
        if TheItem != None:
            if self.WhetherNeedLoad:
                ReturnValue = Builder.load(TheItem["Name"])
                return {
                    "type" : TheItem["Type"],
                    "const" : JudgeReg,
                    "name" : ReturnValue,
                    "struct_name" : TheItem["StructName"] if "StructName" in TheItem else None
                }
            else:
                return {
                    "type" : TheItem["Type"],
                    "const" : JudgeReg,
                    "name" : TheItem["Name"],
                    "struct_name" : TheItem["StructName"] if "StructName" in TheItem else None
                }
        else:
            return {
                'type': void,
                'const': JudgeReg,
                'name': ir.Constant(void, None)
            }

    def visit_IntegerLiteral(self, tree):
        '''
        语法规则：mINT : INT;
        描述：int
        返回：无
        '''
        JudgeReg = True
        return {
                'type': int32,
                'const': JudgeReg,
                'name': ir.Constant(int32, int(tree.getText()))
        }

    def visit_FloatingLiteral(self, tree):
        '''
        语法规则：mDOUBLE : DOUBLE;
        描述：double
        返回：无
        '''
        JudgeReg = True
        return {
                'type': double,
                'const': JudgeReg,
                'name': ir.Constant(double, float(tree.getText()))
        }

    def visit_CharacterLiteral(self, tree):
        '''
        语法规则：mCHAR : CHAR;
        描述：char
        返回：无
        '''
        JudgeReg = True
        return {
                'type': int8,
                'const': JudgeReg,
                'name': ir.Constant(int8, ord(tree.getText()[1]))
        }

    def visit_StringLiteral(self, tree):
        '''
        语法规则：mSTRING : STRING;
        描述：string
        返回：无
        '''
        MarkIndex = self.Constants
        self.Constants += 1
        ProcessIndex = tree.getText().replace('\\n', '\n')
        ProcessIndex = ProcessIndex[1:-1]
        ProcessIndex += '\0'
        Len = len(bytearray(ProcessIndex, 'utf-8'))
        JudgeReg = False
        RealReturnValue = ir.GlobalVariable(self.Module, ir.ArrayType(int8, Len), ".str%d"%MarkIndex)
        RealReturnValue.global_constant = True
        RealReturnValue.initializer = ir.Constant(ir.ArrayType(int8, Len), bytearray(ProcessIndex, 'utf-8'))
        return {
                'type': ir.ArrayType(int8, Len),
                'const': JudgeReg,
                'name': RealReturnValue
        }

    def save(self, filename):
        """
        保存到文件
        描述：文件名含后缀
        返回：无
        """
        with open(filename, "w") as f:
            f.write(repr(self.Module))

def generate(parserTree,output_filename):

    v = Visitor()
    v.visit(parserTree)
    v.save(output_filename)
    
