import sys
from antlr4 import *
from CLexer import CLexer
from CParser import CParser
from CVisitor import CVisitor
from llvmlite import ir


# Type
CHAR_TYPE = ir.IntType(8)
INT_TYPE = ir.IntType(32)
FLOAT_TYPE = ir.FloatType()
DOUBLE_TYPE = ir.DoubleType()
VOID_TYPE = ir.VoidType()


# Type
BASE_TYPE = 0
ARRAY_TYPE = 1
FUNCTION_TYPE = 2
STRUCT_TYPE = 3

class SymbolTable: # symbol table for each level
    def __init__(self, parent=None):
        self.parent = parent
        self.table = {}
        self.children = None
        
    def insert(self, name, type=BASE_TYPE, value=None):
        # if name in self.table.keys():
        #     raise SemanticError("redefinition of " + name)
        self.table[name] = {"type": type, "value": value}        

    def addLevel(self):
        self.children = SymbolTable(self)
        return self.children # return the child level

    def exitLevel(self):
        return self.parent # return the parent level

    def getType(self, name):
        if name in self.table.keys():
            return self.table[name]['type']
        if self.parent:
            return self.parent.getType(name)
        else:
            return None
    
    def getValue(self, name):
        if name in self.table.keys():
            return self.table[name]['value']
        if self.parent:
            return self.parent.getValue(name)
        else:
            return None

        
class StructTable:
    def __init__(self):
        self.table = {}
        
    def insert(self, name, ptr, paramList):
        self.table[name] = {"ptr": ptr, "paramList": paramList}

    def getPtr(self, name):
        if name in self.table.keys():
            return self.table[name]['ptr']
        raise SemanticError("param name not found")

    def getParamRank(self, name, paramName):
        if name not in self.table.keys():
            raise SemanticError("param name not found")
        for i,_ in enumerate(self.table[name]['paramList']):
            if self.table[name]['paramList'][i]['name'] == paramName:
                return i
        

class SemanticError(Exception):
    def __init__(self, msg, ctx=None):
        super().__init__()
        if ctx:
            self.line = ctx.start.line
            self.column = ctx.start.column
        else:
            self.line = 0
            self.column = 0
        self.msg = msg

    def __str__(self):
        return "SemanticError: " + str(self.line) + ":" + str(self.column) + " " + self.msg

class myCVisitor(CVisitor):
    def __init__(self):
        super().__init__()
        self.module = ir.Module(name="main")
        self.module.triple = "x86_64-pc-linux-gnu"
        self.module.data_layout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
        

        self.builder = None
        self.mySymbolTable = SymbolTable()
        self.continueTo = None
        self.breakTo = None
        self.myStructTable = StructTable()
        
    def castType(self,destType,value):
        srcType = value.type
        if destType == srcType:
            return value
        if srcType == INT_TYPE:
            if destType == FLOAT_TYPE:
                return self.builder.sitofp(value, destType)
            elif destType == DOUBLE_TYPE:
                return self.builder.sitofp(value, destType)
            elif destType == CHAR_TYPE:
                return self.builder.trunc(value, destType)
            elif type(destType) == ir.PointerType:
                return self.builder.inttoptr(value, destType)
        elif srcType == FLOAT_TYPE:
            if destType == INT_TYPE:
                return self.builder.fptosi(value, destType)
            elif destType == DOUBLE_TYPE:
                return self.builder.fpext(value, destType)
            elif destType == CHAR_TYPE:
                return self.builder.trunc(self.builder.fptosi(value, INT_TYPE), destType)
        elif srcType == DOUBLE_TYPE:
            if destType == INT_TYPE:
                return self.builder.fptosi(value, destType)
            elif destType == FLOAT_TYPE:
                return self.builder.fptrunc(value, destType)
            elif destType == CHAR_TYPE:
                return self.builder.trunc(self.builder.fptosi(value, INT_TYPE), destType)
        elif srcType == CHAR_TYPE:
            if destType == INT_TYPE:
                return self.builder.sext(value, destType)
            elif destType == FLOAT_TYPE:
                return self.builder.sitofp(value, destType)
            elif destType == DOUBLE_TYPE:
                return self.builder.sitofp(value, destType)
             #TODO: don't make sense
        # if type(srcType) == ir.ArrayType:
        #     if type(destType) == ir.PointerType:
        #         return self.builder.gep(value, [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, 0)], inbounds=True)
        # if type(srcType) == ir.PointerType:
        #     if type(destType) == ir.ArrayType:
        #         return self.builder.gep(value, [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, 0)], inbounds=True)
        
        
    
    def visitPrimaryExpression(self, ctx: CParser.PrimaryExpressionContext):
        '''
            primaryExpression
            :   Identifier
            |   Constant
            |   StringLiteral+
            |   '(' expression ')'
            ;
        '''
        if len(ctx.children) == 1:
            if ctx.Identifier():
                tempIdentification = ctx.Identifier().getText()
                identificationValue = self.mySymbolTable.getValue(tempIdentification)
                if identificationValue is None:
                    raise SemanticError("undefined variable " + tempIdentification, ctx)
                return identificationValue, True
            elif ctx.Constant():
                tempConst = ctx.Constant().getText()
                if tempConst.isdigit():
                    return ir.Constant(INT_TYPE, int(tempConst)), False
                elif tempConst[0] == "'":
                    tempConst = eval(tempConst)
                    return ir.Constant(CHAR_TYPE, ord(tempConst)), False
                else:
                    return ir.Constant(FLOAT_TYPE, float(tempConst)), False
            elif ctx.StringLiteral():
                tmpStrLiteral = ctx.StringLiteral()[0].getText()
                # '\n' -> '
                tmpStrLiteral = eval(tmpStrLiteral)
                # to utf8
                llvmStr = [ir.Constant(CHAR_TYPE, ord(c)) for c in tmpStrLiteral]
                llvmStr.append(ir.Constant(CHAR_TYPE, 0))
                tempAddr = self.builder.alloca(ir.ArrayType(CHAR_TYPE, len(llvmStr)))
                self.builder.store(ir.Constant(ir.ArrayType(CHAR_TYPE, len(llvmStr)), llvmStr), tempAddr)
                tempAddr = self.builder.bitcast(tempAddr, ir.PointerType(CHAR_TYPE))
                return tempAddr, False
        else:
            return self.visit(ctx.children[1]), False        

    def visitPostfixExpression(self, ctx: CParser.PostfixExpressionContext):
        '''
            postfixExpression
            :   primaryExpression
            |   postfixExpression '[' expression ']'
            |   postfixExpression '(' argumentExpressionList? ')'
            |   postfixExpression '.' Identifier
            |   postfixExpression '->' Identifier
            |   postfixExpression '++'
            |   postfixExpression '--'
            ;
        '''
        if len(ctx.children) == 1:
            primary_expr = self.visit(ctx.children[0])
            return primary_expr
        else:
            postfixExpr, postfixExprPtr = self.visit(ctx.children[0])
            if postfixExprPtr:
                if ctx.children[1].getText() == '[': #array
                    baseAddr = self.builder.load(postfixExpr)
                    arrayType = self.builder.load(baseAddr).type
                    ptrType = ir.PointerType(ir.ArrayType(arrayType, 0))
                    postfixExprPtr = self.builder.bitcast(baseAddr, ptrType)
                    postfixExpressionindex = self.visit(ctx.children[2])
                    postfixExpressionIndexs = [ir.Constant(INT_TYPE, 0), postfixExpressionindex]
                    postfixExprPtr = self.builder.gep(postfixExprPtr, postfixExpressionIndexs)
                    return postfixExprPtr, True
                elif ctx.children[1].getText() == '(': #function
                    if ctx.argumentExpressionList():
                        arg_list = self.visit(ctx.argumentExpressionList())
                    else:
                        arg_list = []
                    return self.builder.call(postfixExpr, arg_list), False
                elif ctx.children[1].getText() == '.': #struct
                    postfixExpr = ctx.children[0]
                    identifier = ctx.children[2]
                    postfixExprPtr = self.mySymbolTable.getValue(postfixExpr.getText())
                    idenIndex = self.myStructTable.getParamRank(postfixExprPtr.type.pointee.name
                                                                              , identifier.getText())
                
                    idenIndexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, idenIndex)]
                    postfixExprPtr = self.builder.gep(postfixExprPtr
                                                                      , idenIndexs)
                    return postfixExprPtr, True
                elif ctx.children[1].getText() == '->': #struct pointer
                    postfixExpr = ctx.children[0]
                    identifier = ctx.children[2]
                    postfixExprPtr = self.mySymbolTable.getValue(postfixExpr.getText())
                    idenIndex = self.myStructTable.getParamRank(postfixExprPtr.type.pointee.pointee.name
                                                                              , identifier.getText())
                
                    idenIndexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, idenIndex)]
                    postfixExprPtr = self.builder.gep(self.builder.load(postfixExprPtr)
                                                                      , idenIndexs)
                    return postfixExprPtr, True
                elif ctx.children[1].getText() == '++': #++
                    postfixExprPtr = postfixExpr
                    postfixExpr = self.builder.load(postfixExprPtr)
                    self.builder.store(postfixExpr, postfixExprPtr)
                    edited = self.builder.add(postfixExpr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(edited, postfixExprPtr)
                    return postfixExpr, False
                elif ctx.children[1].getText() == '--':
                    postfixExprPtr = postfixExpr
                    postfixExpr = self.builder.load(postfixExprPtr)
                    self.builder.store(postfixExpr, postfixExprPtr)
                    edited = self.builder.sub(postfixExpr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(edited, postfixExprPtr)
                    return postfixExpr, False
        

    def visitArgumentExpressionList(self, ctx: CParser.ArgumentExpressionListContext):
        '''
            argumentExpressionList
            :   assignmentExpression
            |   argumentExpressionList ',' assignmentExpression
            ;
        '''
        resultArg = [] #list of llvm value
        if ctx.argumentExpressionList():
            resultArg = self.visit(ctx.argumentExpressionList())
        resultArg.append(self.visit(ctx.assignmentExpression()))
        return resultArg

    def visitAssignmentExpression(self, ctx: CParser.AssignmentExpressionContext):
        '''
            assignmentExpression
            :   conditionalExpression
            |   unaryExpression assignmentOperator assignmentExpression
            ;   
        '''
        if len(ctx.children) == 1:
            condition = self.visit(ctx.children[0]) 
            return condition
        elif len(ctx.children) == 3:
            unaryExpr, unaryExprPtr = self.visit(ctx.children[0])
            origin = self.builder.load(unaryExpr)
            if unaryExprPtr is True:
                assignOptr = self.visit(ctx.children[1])
                assignExpr = self.visit(ctx.children[2])
                if assignOptr == '=':
                    assignExpr = self.castType(origin.type, assignExpr)
                    return self.builder.store(assignExpr, unaryExpr)
                elif assignOptr == '<<=':
                    edited = self.builder.shl(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '>>=':
                    edited = self.builder.ashr(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '&=':
                    edited = self.builder.and_(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '^=':
                    edited = self.builder.xor(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '|=':
                    edited = self.builder.or_(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '*=':
                    edited = self.builder.mul(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '/=':
                    edited = self.builder.sdiv(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '+=':
                    edited = self.builder.add(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '-=':
                    edited = self.builder.sub(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)
                elif assignOptr == '%=':
                    edited = self.builder.srem(origin, assignExpr)
                    return self.builder.store(edited, unaryExpr)

    def visitInclusiveOrExpression(self, ctx: CParser.InclusiveOrExpressionContext):
        '''
            inclusiveOrExpression
            :   exclusiveOrExpression
            |   inclusiveOrExpression '|' exclusiveOrExpression
            ;
        '''
        myExcExpr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return myExcExpr
        elif len(ctx.children) == 3:
            myIncExpr = self.visit(ctx.children[0])
            return self.builder.or_(myIncExpr, myExcExpr)

    def visitExclusiveOrExpression(self, ctx: CParser.ExclusiveOrExpressionContext):
        '''
            exclusiveOrExpression
            :   andExpression
            |   exclusiveOrExpression '^' andExpression
            ;
        '''
        myExpr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return myExpr
        elif len(ctx.children) == 3:
            isExcExpr = self.visit(ctx.children[0])
            return self.builder.xor(isExcExpr, myExpr)

    def visitAndExpression(self, ctx: CParser.AndExpressionContext):
        '''
            andExpression
            :   equalityExpression
            |   andExpression '&' equalityExpression
            ;
        '''
        equExpr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return equExpr
        elif len(ctx.children) == 3:
            andExpr = self.visit(ctx.children[0])
            return self.builder.and_(andExpr, equExpr)
        
    def visitLogicalAndExpression(self, ctx: CParser.LogicalAndExpressionContext):
            '''
            logicalAndExpression
            :   inclusiveOrExpression
            |   logicalAndExpression '&&' inclusiveOrExpression
            ;
            '''
            incExpr = self.visit(ctx.children[len(ctx.children) - 1])
            if len(ctx.children) == 1:
                return incExpr
            elif len(ctx.children) == 3:
                andExpr = self.visit(ctx.children[0])
            if incExpr.type != FLOAT_TYPE:
                incExpr = self.builder.icmp_signed(cmpop='!=', lhs=incExpr
                                                                   , rhs=ir.Constant(INT_TYPE, 0))
            else:
                incExpr = self.builder.fcmp_ordered(cmpop='!=', lhs=incExpr
                                                                    , rhs=ir.Constant(FLOAT_TYPE, 0))
            if andExpr.type != FLOAT_TYPE:
                andExpr = self.builder.icmp_signed(cmpop='!=', lhs=andExpr
                                                                  , rhs=ir.Constant(INT_TYPE, 0))
            else:
                andExpr = self.builder.fcmp_ordered(cmpop='!=', lhs=andExpr
                                                                   , rhs=ir.Constant(FLOAT_TYPE, 0))
            return self.builder.and_(andExpr, incExpr)

    def visitLogicalOrExpression(self, ctx: CParser.LogicalOrExpressionContext):
        '''
            logicalOrExpression
            :   logicalAndExpression
            |   logicalOrExpression '||' logicalAndExpression
            ;
        '''
        andExpr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return andExpr
        elif len(ctx.children) == 3:
            orExpr = self.visit(ctx.children[0])
            if orExpr.type != FLOAT_TYPE:
                orExpr = self.builder.icmp_signed(cmpop='!=', lhs=orExpr
                                                                 , rhs=ir.Constant(INT_TYPE, 0))
            else:
                orExpr = self.builder.fcmp_ordered(cmpop='!=', lhs=orExpr
                                                                  , rhs=ir.Constant(FLOAT_TYPE, 0))
            if andExpr.type != FLOAT_TYPE:
                andExpr = self.builder.icmp_signed(cmpop='!=', lhs=andExpr
                                                                  , rhs=ir.Constant(INT_TYPE, 0))
            else:
                andExpr = self.builder.fcmp_ordered(cmpop='!=', lhs=andExpr
                                                                   , rhs=ir.Constant(FLOAT_TYPE, 0))
            return self.builder.or_(orExpr, andExpr)
        
    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        '''
            conditionalExpression
            :   logicalOrExpression ('?' expression ':' conditionalExpression)?
            ;
        '''
        orExpr = self.visit(ctx.children[0])
        if len(ctx.children) == 1:
            return orExpr
        elif len(ctx.children) == 5:
            expr = self.visit(ctx.children[2])
            conditionExpr = self.visit(ctx.children[4])
            return self.builder.select(orExpr, expr, conditionExpr)
        
    def visitEqualityExpression(self, ctx: CParser.EqualityExpressionContext):
        '''
            equalityExpression
            :   relationalExpression
            |   equalityExpression '==' relationalExpression
            |   equalityExpression '!=' relationalExpression
            ;
        '''
        relExpr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return relExpr
        elif len(ctx.children) == 3:
            equExpr = self.visit(ctx.children[0])
            optrExpr = ctx.children[1]
            if equExpr.type == FLOAT_TYPE or relExpr.type == FLOAT_TYPE:
                if equExpr.type != FLOAT_TYPE:
                    equExpr = self.builder.sitofp(equExpr, FLOAT_TYPE)
                if relExpr.type != FLOAT_TYPE:
                    relExpr = self.builder.sitofp(relExpr, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=optrExpr.getText(), lhs=equExpr
                                                 , rhs=relExpr)
            else:
                return self.builder.icmp_signed(cmpop=optrExpr.getText(), lhs=equExpr
                                                , rhs=relExpr)


    def visitRelationalExpression(self, ctx: CParser.RelationalExpressionContext):
        '''
            relationalExpression
            :   shiftExpression
            |   relationalExpression '<' shiftExpression
            |   relationalExpression '>' shiftExpression
            |   relationalExpression '<=' shiftExpression
            |   relationalExpression '>=' shiftExpression
            ;
        '''
        shiftExpr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return shiftExpr
        elif len(ctx.children) == 3:
            relExpr = self.visit(ctx.children[0])
            optrExpr = ctx.children[1]
            if relExpr.type == FLOAT_TYPE or shiftExpr.type == FLOAT_TYPE:
                if shiftExpr.type != FLOAT_TYPE:
                    shiftExpr = self.builder.sitofp(shiftExpr, FLOAT_TYPE)
                if relExpr.type != FLOAT_TYPE:
                    relExpr = self.builder.sitofp(relExpr, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=optrExpr.getText(), lhs=relExpr
                                                 , rhs=shiftExpr)
            else:
                return self.builder.icmp_signed(cmpop=optrExpr.getText(), lhs=relExpr
                                                , rhs=shiftExpr)


    def visitShiftExpression(self, ctx: CParser.ShiftExpressionContext):
        '''
            shiftExpression
            :   additiveExpression
            |   shiftExpression '<<' additiveExpression
            |   shiftExpression '>>' additiveExpression
            ;
        '''
        addExpr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return addExpr
        elif len(ctx.children) == 3:
            shiftExpr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '<<':
                return self.builder.shl(shiftExpr, addExpr)
            elif ctx.children[1].getText() == '>>':
                return self.builder.ashr(shiftExpr, addExpr)


    def visitAdditiveExpression(self, ctx: CParser.AdditiveExpressionContext):
        '''
            additiveExpression
            :   multiplicativeExpression
            |   additiveExpression '+' multiplicativeExpression
            |   additiveExpression '-' multiplicativeExpression
            ;
        '''
        mulExpr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return mulExpr
        elif len(ctx.children) == 3:
            addExpr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '+':
                if addExpr.type == FLOAT_TYPE or mulExpr.type == FLOAT_TYPE:
                    return self.builder.fadd(addExpr, mulExpr)
                return self.builder.add(addExpr, mulExpr)
            elif ctx.children[1].getText() == '-':
                if addExpr.type == FLOAT_TYPE or mulExpr.type == FLOAT_TYPE:
                    return self.builder.fsub(addExpr, mulExpr)
                return self.builder.sub(addExpr, mulExpr)


    def visitMultiplicativeExpression(self, ctx: CParser.MultiplicativeExpressionContext):
        '''
            multiplicativeExpression
            :   castExpression
            |   multiplicativeExpression '*' castExpression
            |   multiplicativeExpression '/' castExpression
            |   multiplicativeExpression '%' castExpression
            ;
        '''
        castExpr, _ = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return castExpr
        elif len(ctx.children) == 3:
            mulExpr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '*':
                # if mulExpr.type == FLOAT_TYPE or castExpr.type == FLOAT_TYPE:
                #     return self.builder.fmul(mulExpr, castExpr)
                return self.builder.mul(mulExpr, castExpr)
            elif ctx.children[1].getText() == '/':
                # if mulExpr.type == FLOAT_TYPE or castExpr.type == FLOAT_TYPE:
                #     return self.builder.fdiv(mulExpr, castExpr)
                return self.builder.sdiv(mulExpr, castExpr)
            elif ctx.children[1].getText() == '%':
                return self.builder.srem(mulExpr, castExpr)


    def visitCastExpression(self, ctx: CParser.CastExpressionContext):
        '''
            castExpression
            :   '(' typeName ')' castExpression
            |   unaryExpression
            |   DigitSequence // for
            ;
        '''
        if len(ctx.children) == 1:
            unaryExpr, unaryExprPtr = self.visit(ctx.children[0])
            if unaryExprPtr is True:
                unaryExprPtr = unaryExpr
                unaryExpr = self.builder.load(unaryExprPtr)
            return unaryExpr, unaryExprPtr
        elif len(ctx.children) == 4 or len(ctx.children) == 5:
            castExpr, cast_expr_pointer = self.visit(ctx.children[len(ctx.children)-1])
            typeName = self.visit(ctx.children[len(ctx.children)-3])
            castExpr = self.builder.bitcast(castExpr, typeName)
            return castExpr, cast_expr_pointer

    def visitUnaryExpression(self, ctx: CParser.UnaryExpressionContext):
        '''
            unaryExpression
            :   postfixExpression
            |   '++' unaryExpression
            |   '--' unaryExpression
            |   unaryOperator castExpression
            ;
        '''
        if len(ctx.children) == 1:
            postfixExpr, _ = self.visit(ctx.children[0])
            return postfixExpr, _
        elif len(ctx.children) == 2:
            optr = ctx.children[0]
            if optr.getText() == '++':
                unaryExpr, unaryExprPtr = self.visit(ctx.children[1])
                if unaryExprPtr is True:
                    unaryExprPtr = unaryExpr
                    unaryExpr = self.builder.load(unaryExprPtr)
                    unaryExpr = self.builder.add(unaryExpr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unaryExpr, unaryExprPtr)
                return unaryExpr, unaryExprPtr
            elif optr.getText() == '--':
                unaryExpr, unaryExprPtr = self.visit(ctx.children[1])
                if unaryExprPtr is True:
                    unaryExprPtr = unaryExpr
                    unaryExpr = self.builder.load(unaryExprPtr)
                    unaryExpr = self.builder.sub(unaryExpr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unaryExpr, unaryExprPtr)
                return unaryExpr, unaryExprPtr
            else:
                unaryExpr, unaryExprPtr = self.visit(ctx.children[1])
                if optr.getText() == '&':
                    return unaryExprPtr, False
                elif optr.getText() == '*':
                    if unaryExprPtr is True:
                        unaryExprPtr = unaryExpr
                        unaryExpr = self.builder.load(unaryExprPtr)
                    return unaryExpr, unaryExprPtr
                elif optr.getText() == '+':
                    return unaryExpr, False
                elif optr.getText() == '-':
                    return self.builder.neg(unaryExpr), False
                elif optr.getText() == '~':
                    return self.builder.not_(unaryExpr), False
                elif optr.getText() == '!':
                    if unaryExpr.type != FLOAT_TYPE:
                        return self.builder.icmp_signed(cmpop='==', lhs=unaryExpr
                                                        , rhs=ir.Constant(INT_TYPE, 0)), False
                    else:
                        return self.builder.fcmp_ordered(cmpop='==', lhs=unaryExpr
                                                         , rhs=ir.Constant(FLOAT_TYPE, 0)), False


    def visitFunctionDefinition(self, ctx):
        '''
            functionDefinition
            :   declarationSpecifiers? declarator declarationList? compoundStatement
            ;
        '''
        funcType = self.visit(ctx.declarationSpecifiers())
        funcName, params = self.visit(ctx.declarator())
        llvmType = ir.FunctionType(funcType, [param[0] for param in params])
        llvmFunc = ir.Function(self.module, llvmType, name=funcName)
        self.mySymbolTable.insert(funcName, FUNCTION_TYPE, llvmFunc)
        self.mySymbolTable = self.mySymbolTable.addLevel()
        
        func_block = llvmFunc.append_basic_block(name= funcName+"entry")
        self.builder = ir.IRBuilder(func_block)
        
        for i, param in enumerate(params):
            paramType, paramName = param
            addr = self.builder.alloca(paramType, name=paramName)
            self.builder.store(llvmFunc.args[i], addr)
            self.mySymbolTable.insert(paramName, paramType, addr)
        self.visit(ctx.compoundStatement())
        if funcType == VOID_TYPE:
            self.builder.ret_void()
        self.mySymbolTable = self.mySymbolTable.exitLevel()

    def visitDeclarator(self, ctx: CParser.DeclaratorContext):  
        '''
            declarator
            :   pointer? directDeclarator
            ;
        '''
        return self.visit(ctx.directDeclarator())

    def visitDirectDeclarator(self, ctx: CParser.DirectDeclaratorContext):  
        '''
            directDeclarator
            :   Identifier
            |   '(' declarator ')'
            |   directDeclarator '[' typeQualifierList? assignmentExpression? ']'
            |   directDeclarator '(' parameterTypeList ')'
            |   directDeclarator '(' identifierList? ')'
            ;
        '''
        idenName = self.visit(ctx.getChild(0))
        if ctx.Identifier():
            self.mySymbolTable.insert(idenName, (BASE_TYPE, None))
            return idenName
        elif ctx.declarator():
            return self.visit(ctx.declarator())
        elif ctx.children[1].getText() == '[':
            length = self.visit(ctx.assignmentExpression())
            btype = (ARRAY_TYPE, length)
            self.mySymbolTable.insert(idenName, type=btype)
            return idenName
        elif ctx.children[1].getText() == '(':
            btype = (FUNCTION_TYPE, None)
            self.mySymbolTable.insert(idenName, btype)
            params = self.visit(ctx.parameterTypeList()) if ctx.parameterTypeList() else []
            return idenName, params

    def visitTypeSpecifier(self, ctx: CParser.TypeSpecifierContext):
        '''
            typeSpecifier
            :   ('void'
            |   'char'
            |   'short'
            |   'int'
            |   'long'
            |   'float'
            |   'double'
            |   'signed'
            |   'unsigned')
            |   structOrUnionSpecifier
            |   typedefName
            |   typeSpecifier pointer
            ;
        '''  

        if ctx.pointer():
            type = self.visit(ctx.typeSpecifier())
            if type == VOID_TYPE:
                type = INT_TYPE
            return ir.PointerType(type)
        elif ctx.structOrUnionSpecifier():
            return self.visit(ctx.structOrUnionSpecifier())
        elif ctx.typedefName():
            return self.visit(ctx.typedefName())
        
        if ctx.Void():
            return VOID_TYPE
        elif ctx.Char():
            return CHAR_TYPE
        elif ctx.Int():
            return INT_TYPE
        elif ctx.Float():
            return FLOAT_TYPE
        elif ctx.Double():
            return DOUBLE_TYPE


    def visitStructOrUnionSpecifier(self, ctx: CParser.StructOrUnionSpecifierContext): 
        '''
            structOrUnionSpecifier
            :   structOrUnion Identifier? '{' structDeclarationList '}'
            |   structOrUnion Identifier
            ;
        '''  
        if ctx.structDeclarationList():
            structName = ctx.Identifier().getText()
            if self.mySymbolTable.getValue(structName):
                raise SemanticError("Redefinition of struct '{}'".format(structName))
            else:
                decList = self.visit(ctx.structDeclarationList())
                paramList, typeList = [], []
                for dec in decList:
                    paramList.append({'name': dec[1], 'type': dec[0]})
                    typeList.append(dec[0])
                tempStruct = ir.global_context.get_identified_type(name=structName)
                tempStruct.set_body(*typeList)
                self.myStructTable.insert(structName, tempStruct, paramList) # insert struct to symbol table
                return tempStruct
        else:
            structName = ctx.Identifier().getText()
            tempStruct = ir.global_context.get_identified_type(name=structName)
            return tempStruct

    def visitTypedefName(self, ctx: CParser.TypedefNameContext):  
        '''
            typedefName
            :   Identifier
            ;
        '''
        return ctx.getText()

    def visitStructDeclarationList(self, ctx: CParser.StructDeclarationListContext):  
        '''
            structDeclaratorList
            :   structDeclarator
            |   structDeclaratorList ',' structDeclarator
            ;
        '''
        decList = self.visit(ctx.structDeclarationList()) if ctx.structDeclarationList() else []
        decList.append(self.visit(ctx.structDeclaration()))
        return decList

    def visitStructDeclaration(self, ctx: CParser.StructDeclarationContext):  
        '''
            structDeclaration
            :   specifierQualifierList structDeclaratorList? ';'
            ;
        '''
        return self.visit(ctx.specifierQualifierList())

    def visitStructDeclaratorList(self, ctx: CParser.StructDeclaratorListContext): 
        '''
            structDeclaratorList
            :   structDeclarator
            |   structDeclaratorList ',' structDeclarator
            ;
        ''' 
        return self.visit(ctx.structDeclarator())

    def visitStructDeclarator(self, ctx: CParser.StructDeclaratorContext): 
        '''
            structDeclarator
            :   declarator
            |   declarator? ':' constantExpression
            ;
        '''  
        return self.visit(ctx.declarator())

    def visitSpecifierQualifierList(self, ctx: CParser.SpecifierQualifierListContext):  
        '''
            specifierQualifierList
            :   typeSpecifier specifierQualifierList?
            |   typeQualifier specifierQualifierList?
            ;
        '''
        if not ctx.specifierQualifierList():
            return self.visit(ctx.typeSpecifier())
        else:
            return ( self.visit(ctx.children[0]),self.visit(ctx.children[1]))

    def visitStructOrUnion(self, ctx: CParser.StructOrUnionContext):   
        '''
            structOrUnion
            :   'struct'
            |   'union'
            ;
        '''
        return ctx.getText()

    def visitDeclarationSpecifiers(self, ctx):  
        '''
            declarationSpecifiers
            :   declarationSpecifier+
            ;
        '''
        return self.visit(ctx.children[-1]) # we don't care about the storage class

    def visitDeclarationSpecifier(self, ctx: CParser.DeclarationSpecifierContext):  
        '''
            declarationSpecifier
            :   storageClassSpecifier
            |   typeSpecifier
            |   typeQualifier
            ;
        '''
        return self.visit(ctx.children[0])

    def visitDeclaration(self, ctx):  
        '''
            declaration
            :   declarationSpecifiers initDeclaratorList ';'
            | 	declarationSpecifiers ';'
            ;
        '''
        _type = self.visit(ctx.declarationSpecifiers())
        if not ctx.initDeclaratorList():
            return ''

        decList = self.visit(ctx.initDeclaratorList())
        for name, init_val in decList:
            # function declaration
            if isinstance(name, tuple):
                funcName, func_params = name
                args = [arg for arg, _ in func_params]
                funcType = ir.FunctionType(_type, args, var_arg=True)
                func = ir.Function(self.module, funcType, name=funcName)
                self.mySymbolTable.insert(funcName, type=(FUNCTION_TYPE, None), value=func)
            # struct declaration
            elif type(_type) == ir.types.IdentifiedStructType:
                ptr_struct = self.myStructTable.getPtr(_type.name)
                self.mySymbolTable.insert(name, type=(STRUCT_TYPE, None), value=self.builder.alloca(ptr_struct))
            else:
                self.saveSymbol(name, init_val, _type, ctx=ctx)

    def saveSymbol(self, name, init_val, _type, ctx=None):
        '''
            declaration
            :   declarationSpecifiers initDeclaratorList ';'
            ;
        '''
        myType = self.mySymbolTable.getType(name)
        # array declaration
        if myType[0] == ARRAY_TYPE:
            length = myType[1]
            arr_type = ir.ArrayType(_type, length.constant)

            if self.builder:
                value = self.builder.alloca(arr_type, name=name)
            else:
                value = ir.GlobalValue(self.module, arr_type, name=name)

            if init_val:
                l = len(init_val)
                if l > length.constant:
                    raise SemanticError("Array length is too long", ctx)

                for i in range(l):
                    indexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, i)]
                    ptr = self.builder.gep(value, indexs)
                    self.builder.store(init_val[i], ptr)
            value = self.builder.bitcast(value, ir.PointerType(_type))
            tempPtr = self.builder.alloca(value.type)
            self.builder.store(value, tempPtr)
            value = tempPtr
            self.mySymbolTable.insert(name, myType, value)
        else:
            if self.builder:
                value = self.builder.alloca(_type, name=name)
            else:
                value = ir.GlobalValue(self.module, _type, name=name)

            if init_val:
                self.builder.store(init_val, value)

            self.mySymbolTable.insert(name, myType, value)

    def visitCompoundStatement(self, ctx):
        '''
            compoundStatement
            :   '{' blockItemList? '}'
            ;
        '''
        for i in ctx.children:
            self.visit(i)

    def visitBlockItem(self, ctx):  
        '''
            blockItem
            :   statement
            |   declaration
            ;
        '''
        return self.visit(ctx.getChild(0))

    def visitInitDeclaratorList(self, ctx):  
        '''
            initDeclaratorList
            :   initDeclarator
            |   initDeclaratorList ',' initDeclarator
            ;
        '''
        decList = self.visit(ctx.initDeclaratorList()) if ctx.initDeclaratorList() else []
        decList.append(self.visit(ctx.initDeclarator()))
        return decList

    def visitInitDeclarator(self, ctx):  
        '''
            initDeclarator
            :   declarator
            |   declarator '=' initializer
            ;
        ''' 
        if ctx.initializer():
            return self.visit(ctx.declarator()), self.visit(ctx.initializer())
        else:
            return self.visit(ctx.declarator()), None

    def visitInitializer(self, ctx):   
        '''
            initializer
            :   assignmentExpression
            |   '{' initializerList '}'
            |   '{' initializerList ',' '}'
            ;
        '''
        if ctx.assignmentExpression():
            return self.visit(ctx.assignmentExpression())
        elif ctx.initializerList():
            return self.visit(ctx.initializerList())

    def visitInitializerList(self, ctx: CParser.InitializerListContext):  
        '''
            initializerList
            :   designation? initializer
            |   initializerList ',' designation? initializer
            ;
        '''
        initList = [self.visit(ctx.initializer())]
        if ctx.initializerList():
            initList = self.visit(ctx.initializerList()) + initList
        return initList

    def visitParameterTypeList(self, ctx: CParser.ParameterTypeListContext): 
        '''
            parameterTypeList
            :   parameterList
            |   parameterList ',' '...'
            ;
        ''' 
        if ctx.parameterList():
            return self.visit(ctx.parameterList())

    def visitParameterList(self, ctx: CParser.ParameterListContext):  
        '''
            parameterList
            :   parameterDeclaration
            |   parameterList ',' parameterDeclaration
            ;
        '''
        paramList = self.visit(ctx.parameterList()) if ctx.parameterList() else []
        temp_param = self.visit(ctx.parameterDeclaration())
        paramList.append(temp_param)
        return paramList

    def visitParameterDeclaration(self, ctx: CParser.ParameterDeclarationContext): 
        '''
            parameterDeclaration
            :   declarationSpecifiers declarator
            |   declarationSpecifiers2 abstractDeclarator?
            ;
        ''' 
        return (self.visit(ctx.declarationSpecifiers()), self.visit(ctx.declarator()))

    def visitTerminal(self, node):  
        return node.getText()

    def visitJumpStatement(self, ctx:CParser.JumpStatementContext):
        '''
            jumpStatement
            :   continueStatement
            |   breakStatement
            |   returnStatement
            ;
        '''
        if ctx.breakStatement():
            self.visitBreakStatement(ctx.breakStatement())
        elif ctx.returnStatement():
            self.visitReturnStatement(ctx.returnStatement())
        elif ctx.continueStatement():
            self.visitContinueStatement(ctx.continueStatement())

    def visitContinueStatement(self, ctx: CParser.ContinueStatementContext):
        '''
            continueStatement
            :   'continue' ';'
            ;
        '''
        if self.continueTo is not None:
            self.builder.branch(self.continueTo)
        else:
            raise SemanticError("Wrong continue!\n", ctx)

    def visitBreakStatement(self, ctx: CParser.BreakStatementContext):
        '''
            breakStatement
            :   'break' ';'
            ;
        '''
        if self.breakTo is not None:
            self.builder.branch(self.breakTo)
        else:
            raise SemanticError("Wrong break!\n", ctx)

    def visitReturnStatement(self, ctx: CParser.ReturnStatementContext):
        '''
            returnStatement
            :   'return' expression? ';'
            ;
        '''
        if ctx.expression():
            self.builder.ret(self.visit(ctx.expression()))
        else:
            self.builder.ret_void()

    def visitIterationStatement(self, ctx:CParser.IterationStatementContext):
        '''
            iterationStatement
            :   whileStatement
            |   doWhileStatement
            |   forStatement
            ;
        '''
        if ctx.whileStatement():
            self.visitWhileStatement(ctx.whileStatement())
        elif ctx.forStatement():
            self.visitForStatement(ctx.forStatement())
        elif ctx.doWhileStatement():
            self.visitDoWhileStatement(ctx.doWhileStatement())

    def visitWhileStatement(self, ctx:CParser.WhileStatementContext):
        '''
            whileStatement
            :   While '(' expression ')' statement
            ;
        '''
        self.mySymbolTable = self.mySymbolTable.addLevel()

        blockName = self.builder.block.name
        condBlock = self.builder.append_basic_block(name='cond'.format(blockName))
        statBlock = self.builder.append_basic_block(name='stat'.format(blockName))
        quitBlock = self.builder.append_basic_block(name='quit'.format(blockName))

        lastContinueTo = self.continueTo
        lastBreakTo = self.breakTo
        self.continueTo = condBlock
        self.breakTo = quitBlock

        # The condition expression of While
        self.builder.branch(condBlock)
        self.builder.position_at_start(condBlock)
        expression = self.visit(ctx.expression())

        # Judge if jump to statement or quit
        self.builder.cbranch(expression, statBlock, quitBlock)

        # The statement of While
        self.builder.position_at_start(statBlock)
        self.visit(ctx.statement())

        # Jump back to cond
        self.builder.branch(condBlock)

        # The quit block
        self.builder.position_at_start(quitBlock)

        self.continueTo = lastContinueTo
        self.breakTo = lastBreakTo
        self.mySymbolTable = self.mySymbolTable.exitLevel()

    def visitDoWhileStatement(self, ctx:CParser.DoWhileStatementContext):
        '''
            doWhileStatement
            :   Do statement While '(' expression ')' ';'
            ;
        '''
        self.mySymbolTable = self.mySymbolTable.addLevel()

        blockName = self.builder.block.name
        statBlock = self.builder.append_basic_block(name='stat'.format(blockName))
        condBlock = self.builder.append_basic_block(name='cond'.format(blockName))
        quitBlock = self.builder.append_basic_block(name='quit'.format(blockName))

        lastContinueTo = self.continueTo
        lastBreakTo = self.breakTo
        self.continueTo = condBlock
        self.breakTo = quitBlock

        # The statement of While
        self.builder.branch(statBlock)
        self.builder.position_at_start(statBlock)
        self.visit(ctx.statement())

        # The condition expression of While
        self.builder.branch(condBlock)
        self.builder.position_at_start(condBlock)
        expr = self.visit(ctx.expression())

        # Judge if jump to statement or quit
        self.builder.cbranch(expr, statBlock, quitBlock)

        # The quit block
        self.builder.position_at_start(quitBlock)

        self.continueTo = lastContinueTo
        self.breakTo = lastBreakTo
        self.mySymbolTable = self.mySymbolTable.exitLevel()

    def visitForStatement(self, ctx:CParser.ForStatementContext):
        '''
            forStatement
            :   For '(' forCondition ')' statement
            ;
        '''
        self.mySymbolTable = self.mySymbolTable.addLevel()

        blockName = self.builder.block.name
        condBlock = self.builder.append_basic_block(name='cond'.format(blockName))
        statBlock = self.builder.append_basic_block(name='stat'.format(blockName))
        quitBlock = self.builder.append_basic_block(name='quit'.format(blockName))

        lastContinueTo = self.continueTo
        lastBreakTo = self.breakTo
        self.continueTo = condBlock
        self.breakTo = quitBlock

        condExpr, op_expr = self.visit(ctx.forCondition())

        # The condition of For
        self.builder.branch(condBlock)
        self.builder.position_at_start(condBlock)
        condValue = self.visit(condExpr)

        self.builder.cbranch(condValue, statBlock, quitBlock)
        self.builder.position_at_start(statBlock)
        self.visit(ctx.statement())

        if op_expr:
            self.visit(op_expr)

        # come back to the cond
        self.builder.branch(condBlock)

        # quit block
        self.builder.position_at_start(quitBlock)
        self.continueTo = lastContinueTo
        self.breakTo = lastBreakTo

        self.mySymbolTable = self.mySymbolTable.exitLevel()

    def visitForCondition(self, ctx: CParser.ForConditionContext):
        '''
            forCondition
            :   forDeclaration ';' forExpression? ';' forExpression?
            |   expression? ';' forExpression? ';' forExpression?
            ;
        '''
        if ctx.forDeclaration():
            self.visit(ctx.forDeclaration())
        elif ctx.expression():
            self.visit(ctx.expression())
        return ctx.forExpression(0), ctx.forExpression(1)

    def visitForDeclaration(self, ctx: CParser.ForDeclarationContext):
        '''
            forDeclaration
            :   declarationSpecifiers initDeclaratorList
            | 	declarationSpecifiers
            ;
        '''
        type = self.visit(ctx.declarationSpecifiers())
        decList = self.visit(ctx.initDeclaratorList())

        for name, init_val in decList:
            self.saveSymbol(name, init_val, type)

    def visitSelectionStatement(self, ctx: CParser.SelectionStatementContext):
        '''
            selectionStatement
            :   ifStatement
        '''
        if ctx.ifStatement():
            self.visitIfStatement(ctx.ifStatement())

    def visitIfStatement(self, ctx: CParser.IfStatementContext):
        '''
            ifStatement
            :   'if' '(' expression ')' statement ('else' statement)?
            ;
        '''
        if len(ctx.statement()) > 1:  # else or elif exist
            self.mySymbolTable.addLevel()

            blockName = self.builder.block.name
            condBlock = self.builder.append_basic_block(name='cond'.format(blockName))
            statBlock = self.builder.append_basic_block(name='stat'.format(blockName))
            else_block = self.builder.append_basic_block(name='else'.format(blockName))
            quitBlock = self.builder.append_basic_block(name='quit'.format(blockName))

            # condition block
            self.builder.branch(condBlock)
            self.builder.position_at_start(condBlock)
            condition_value = self.visit(ctx.expression())
            self.builder.cbranch(condition_value, statBlock, else_block)

            # if block
            self.builder.position_at_start(statBlock)
            self.mySymbolTable.addLevel()
            self.visit(ctx.statement()[0])
            self.mySymbolTable.exitLevel()

            # if quit block
            try:
                self.builder.branch(quitBlock)
            except:
                pass
            self.builder.position_at_start(quitBlock)

            # else block
            self.builder.position_at_start(else_block)
            self.mySymbolTable.addLevel()
            self.visit(ctx.statement()[1])
            self.mySymbolTable.exitLevel()

            # else quit
            try:
                self.builder.branch(quitBlock)
            except:
                pass
            self.builder.position_at_start(quitBlock)

            self.mySymbolTable.exitLevel()

        else:  # no else
            self.mySymbolTable.addLevel()

            blockName = self.builder.block.name
            condBlock = self.builder.append_basic_block(name='cond'.format(blockName))
            statBlock = self.builder.append_basic_block(name='stat'.format(blockName))
            quitBlock = self.builder.append_basic_block(name='quit'.format(blockName))

            # condition block
            self.builder.branch(condBlock)
            self.builder.position_at_start(condBlock)
            condition_value = self.visit(ctx.expression())
            self.builder.cbranch(condition_value, statBlock, quitBlock)

            # statement block
            self.builder.position_at_start(statBlock)
            self.mySymbolTable.addLevel()
            self.visit(ctx.statement()[0])
            self.mySymbolTable.exitLevel()

            # quit block
            try:
                self.builder.branch(quitBlock)
            except:
                pass
            self.builder.position_at_start(quitBlock)

            self.mySymbolTable.exitLevel()

    def print(self):
        return self.module.__str__()



def main(argv):
    input = FileStream(argv[1])
    lexer = CLexer(input)
    stream = CommonTokenStream(lexer)
    stream.fill()

    parser = CParser(stream)
    tree = parser.compilationUnit()

    visitor = myCVisitor()
    visitor.visit(tree)

    with open(argv[2], 'w', encoding='utf-8') as f:
        f.write(visitor.print())


if __name__ == '__main__':
    main(sys.argv)