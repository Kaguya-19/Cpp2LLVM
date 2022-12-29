import sys
from antlr4 import *
from CLexer import CLexer
from CParser import CParser
from CVisitor import CVisitor
from llvmlite import ir

CHAR_TYPE = ir.IntType(8)
INT_TYPE = ir.IntType(32)
FLOAT_TYPE = ir.FloatType()
DOUBLE_TYPE = ir.DoubleType()
VOID_TYPE = ir.VoidType()
BOOL_TYPE = ir.IntType(1)

BASE_TYPE = 0
ARRAY_TYPE = 1
FUNCTION_TYPE = 2
STRUCT_TYPE = 3

class SymbolTable:
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
        return self.children

    def exitLevel(self):
        return self.parent

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

    def getParamRank(self, name, param_name):
        if name not in self.table.keys():
            raise SemanticError("param name not found")
        for i,_ in enumerate(self.table[name]['paramList']):
            if self.table[name]['paramList'][i]['name'] == param_name:
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
        self.symbol_table = SymbolTable()
        self.continue_to = None
        self.break_to = None
        self.switch_val = None
        self.struct_table = StructTable()
    
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
                temp_iden = ctx.Identifier().getText()
                iden_value = self.symbol_table.getValue(temp_iden)
                if iden_value is None:
                    raise SemanticError("identifier not found", ctx)
                return iden_value, True
            elif ctx.Constant():
                temp_const = ctx.Constant().getText()
                if temp_const.isdigit():
                    return ir.Constant(INT_TYPE, int(temp_const)), False
                elif temp_const[0] == "'":
                    temp_const = eval(temp_const)
                    return ir.Constant(CHAR_TYPE, ord(temp_const)), False
                else:
                    return ir.Constant(FLOAT_TYPE, float(temp_const)), False
            elif ctx.StringLiteral():
                temp_str_literal = ctx.StringLiteral()[0].getText()
                # '\n' -> '
                temp_str_literal = eval(temp_str_literal)
                # to utf8
                llvm_str = [ir.Constant(CHAR_TYPE, ord(c)) for c in temp_str_literal]
                llvm_str.append(ir.Constant(CHAR_TYPE, 0))
                temp_addr = self.builder.alloca(ir.ArrayType(CHAR_TYPE, len(llvm_str)))
                self.builder.store(ir.Constant(ir.ArrayType(CHAR_TYPE, len(llvm_str)), llvm_str), temp_addr)
                temp_addr = self.builder.bitcast(temp_addr, ir.PointerType(CHAR_TYPE))
                return temp_addr, False
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
            |   '(' typeName ')' '{' initializerList '}'
            |   '(' typeName ')' '{' initializerList ',' '}'
            ;
        '''
        if len(ctx.children) == 1:
            primary_expr = self.visit(ctx.children[0])
            return primary_expr
        else:
            postfix_expr, postfix_expr_ptr = self.visit(ctx.children[0])
            if postfix_expr_ptr:
                if ctx.children[1].getText() == '[': #array
                    baseAddr = self.builder.load(postfix_expr)
                    arrayType = self.builder.load(baseAddr).type
                    ptr_type = ir.PointerType(ir.ArrayType(arrayType, 0))
                    postfix_expr_ptr = self.builder.bitcast(baseAddr, ptr_type)
                    postfix_expression_index = self.visit(ctx.children[2])
                    postfix_expression_indexs = [ir.Constant(INT_TYPE, 0), postfix_expression_index]
                    postfix_expr_ptr = self.builder.gep(postfix_expr_ptr, postfix_expression_indexs)
                    return postfix_expr_ptr, True
                elif ctx.children[1].getText() == '(': #function
                    if ctx.argumentExpressionList():
                        arg_list = self.visit(ctx.argumentExpressionList())
                    else:
                        arg_list = []
                    return self.builder.call(postfix_expr, arg_list), False
                elif ctx.children[1].getText() == '.': #struct
                    postfix_expr = ctx.children[0]
                    identifier = ctx.children[2]
                    postfix_expr_ptr = self.symbol_table.getValue(postfix_expr.getText())
                    iden_index = self.struct_table.getParamRank(postfix_expr_ptr.type.pointee.name
                                                                              , identifier.getText())
                
                    iden_indexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, iden_index)]
                    postfix_expr_ptr = self.builder.gep(postfix_expr_ptr
                                                                      , iden_indexs)
                    return postfix_expr_ptr, True
                elif ctx.children[1].getText() == '->': #struct pointer
                    postfix_expr = ctx.children[0]
                    identifier = ctx.children[2]
                    postfix_expr_ptr = self.symbol_table.getValue(postfix_expr.getText())
                    iden_index = self.struct_table.getParamRank(postfix_expr_ptr.type.pointee.pointee.name
                                                                              , identifier.getText())
                
                    iden_indexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, iden_index)]
                    postfix_expr_ptr = self.builder.gep(self.builder.load(postfix_expr_ptr)
                                                                      , iden_indexs)
                    return postfix_expr_ptr, True
                elif ctx.children[1].getText() == '++': #++
                    postfix_expr_ptr = postfix_expr
                    postfix_expr = self.builder.load(postfix_expr_ptr)
                    self.builder.store(postfix_expr, postfix_expr_ptr)
                    temp_expr = self.builder.add(postfix_expr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(temp_expr, postfix_expr_ptr)
                    return postfix_expr, postfix_expr_ptr
                elif ctx.children[1].getText() == '--':
                    postfix_expr_ptr = postfix_expr
                    postfix_expr = self.builder.load(postfix_expr_ptr)
                    self.builder.store(postfix_expr, postfix_expr_ptr)
                    temp_expr = self.builder.sub(postfix_expr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(temp_expr, postfix_expr_ptr)
                    return postfix_expr, postfix_expr_ptr

        raise Exception()
        

    def visitArgumentExpressionList(self, ctx: CParser.ArgumentExpressionListContext):
        '''
            argumentExpressionList
            :   assignmentExpression
            |   argumentExpressionList ',' assignmentExpression
            ;
        '''
        result_arg = []
        if ctx.argumentExpressionList():
            result_arg = self.visit(ctx.argumentExpressionList())
        result_arg.append(self.visit(ctx.assignmentExpression()))
        return result_arg

    def visitAssignmentExpression(self, ctx: CParser.AssignmentExpressionContext):
        '''
            assignmentExpression
            :   conditionalExpression
            |   unaryExpression assignmentOperator assignmentExpression
            |   DigitSequence // for
            ;   
        '''
        if len(ctx.children) == 1:
            condition = self.visit(ctx.children[0])
            return condition
        elif len(ctx.children) == 3:
            unary_expr, unary_expr_ptr = self.visit(ctx.children[0])
            origin = self.builder.load(unary_expr)
            if unary_expr_ptr is True:
                assign_optr = self.visit(ctx.children[1])
                assign_expr = self.visit(ctx.children[2])
                if assign_optr == '=':
                    return self.builder.store(assign_expr, unary_expr)
                elif assign_optr == '<<=':
                    edited = self.builder.shl(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '>>=':
                    edited = self.builder.ashr(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '&=':
                    edited = self.builder.and_(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '^=':
                    edited = self.builder.xor(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '|=':
                    edited = self.builder.or_(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '*=':
                    edited = self.builder.mul(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '/=':
                    edited = self.builder.sdiv(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '+=':
                    edited = self.builder.add(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '-=':
                    edited = self.builder.sub(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
                elif assign_optr == '%=':
                    edited = self.builder.srem(origin, assign_expr)
                    return self.builder.store(edited, unary_expr)
            else:
                raise Exception()

    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        '''
            conditionalExpression
            :   logicalOrExpression ('?' expression ':' conditionalExpression)?
            ;
        '''
        oror_expr = self.visit(ctx.children[0])
        if len(ctx.children) == 1:
            return oror_expr
        elif len(ctx.children) == 5:
            questionmark = ctx.children[1]
            expr = self.visit(ctx.children[2])
            colon = ctx.children[3]
            condition_expr = self.visit(ctx.children[4])
            if questionmark.getText() != '?' or colon.getText() != ':':
                raise Exception()
            else:
                raise Exception()
        else:
            raise Exception()


    def visitLogicalAndExpression(self, ctx: CParser.LogicalAndExpressionContext):
        '''
            logicalAndExpression
            :   inclusiveOrExpression
            |   logicalAndExpression '&&' inclusiveOrExpression
            ;
        '''
        inc_or_expr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return inc_or_expr
        elif len(ctx.children) == 3:
            andand_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if optr_expr.getText() != '&&':
                raise Exception()
            else:
                if inc_or_expr.type != FLOAT_TYPE:
                    inc_or_expr = self.builder.icmp_signed(cmpop='!=', lhs=inc_or_expr
                                                                       , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    inc_or_expr = self.builder.fcmp_ordered(cmpop='!=', lhs=inc_or_expr
                                                                        , rhs=ir.Constant(FLOAT_TYPE, 0))
                if andand_expr.type != FLOAT_TYPE:
                    andand_expr = self.builder.icmp_signed(cmpop='!=', lhs=andand_expr
                                                                      , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    andand_expr = self.builder.fcmp_ordered(cmpop='!=', lhs=andand_expr
                                                                       , rhs=ir.Constant(FLOAT_TYPE, 0))
                return self.builder.and_(andand_expr, inc_or_expr)
        else:
            raise Exception()

    def visitInclusiveOrExpression(self, ctx: CParser.InclusiveOrExpressionContext):
        '''
            inclusiveOrExpression
            :   exclusiveOrExpression
            |   inclusiveOrExpression '|' exclusiveOrExpression
            ;
        '''
        exc_or_expr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return exc_or_expr
        elif len(ctx.children) == 3:
            inc_or_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if optr_expr.getText() != '|':
                raise Exception()
            else:
                return self.builder.or_(inc_or_expr, exc_or_expr)
        else:
            raise Exception()

    def visitExclusiveOrExpression(self, ctx: CParser.ExclusiveOrExpressionContext):
        '''
            exclusiveOrExpression
            :   andExpression
            |   exclusiveOrExpression '^' andExpression
            ;
        '''
        and_expr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return and_expr
        elif len(ctx.children) == 3:
            exc_or_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if optr_expr.getText() != '^':
                raise Exception()
            else:
                return self.builder.xor(exc_or_expr, and_expr)
        else:
            raise Exception()

    def visitAndExpression(self, ctx: CParser.AndExpressionContext):
        '''
            andExpression
            :   equalityExpression
            |   andExpression '&' equalityExpression
            ;
        '''
        equ_expr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return equ_expr
        elif len(ctx.children) == 3:
            and_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if optr_expr.getText() != '&':
                raise Exception()
            else:
                return self.builder.and_(and_expr, equ_expr)
        else:
            raise Exception()

    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        '''
            conditionalExpression
            :   logicalOrExpression ('?' expression ':' conditionalExpression)?
            ;
        '''
        oror_expr = self.visit(ctx.children[0])
        if len(ctx.children) == 1:
            return oror_expr
        elif len(ctx.children) == 5:
            questionmark = ctx.children[1]
            expr = self.visit(ctx.children[2])
            operator_expr_colon = ctx.children[3]
            cond_expr = self.visit(ctx.children[4])
            if questionmark.getText() != '?' or operator_expr_colon.getText() != ':':
                raise Exception()
            else:
                return expr
        else:
            raise Exception()

    def visitLogicalOrExpression(self, ctx: CParser.LogicalOrExpressionContext):
        '''
            logicalOrExpression
            :   logicalAndExpression
            |   logicalOrExpression '||' logicalAndExpression
            ;
        '''
        andand_expr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return andand_expr
        elif len(ctx.children) == 3:
            oror_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if optr_expr.getText() != '||':
                raise Exception()
            else:
                if oror_expr.type != FLOAT_TYPE:
                    oror_expr = self.builder.icmp_signed(cmpop='!=', lhs=oror_expr
                                                                     , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    oror_expr = self.builder.fcmp_ordered(cmpop='!=', lhs=oror_expr
                                                                      , rhs=ir.Constant(FLOAT_TYPE, 0))
                if andand_expr.type != FLOAT_TYPE:
                    andand_expr = self.builder.icmp_signed(cmpop='!=', lhs=andand_expr
                                                                      , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    andand_expr = self.builder.fcmp_ordered(cmpop='!=', lhs=andand_expr
                                                                       , rhs=ir.Constant(FLOAT_TYPE, 0))
                return self.builder.or_(oror_expr, andand_expr)
        else:
            raise Exception()

    def visitEqualityExpression(self, ctx: CParser.EqualityExpressionContext):
        '''
            equalityExpression
            :   relationalExpression
            |   equalityExpression '==' relationalExpression
            |   equalityExpression '!=' relationalExpression
            ;
        '''
        rel_expr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return rel_expr
        elif len(ctx.children) == 3:
            equ_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if equ_expr.type == FLOAT_TYPE or rel_expr.type == FLOAT_TYPE:
                if equ_expr.type != FLOAT_TYPE:
                    equ_expr = self.builder.sitofp(equ_expr, FLOAT_TYPE)
                if rel_expr.type != FLOAT_TYPE:
                    rel_expr = self.builder.sitofp(rel_expr, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=optr_expr.getText(), lhs=equ_expr
                                                 , rhs=rel_expr)
            else:
                return self.builder.icmp_signed(cmpop=optr_expr.getText(), lhs=equ_expr
                                                , rhs=rel_expr)
        else:
            raise Exception()


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
        shift_expr = self.visit(ctx.children[len(ctx.children)-1])
        shift_type = shift_expr.type
        if len(ctx.children) == 1:
            return shift_expr
        elif len(ctx.children) == 3:
            rel_expr = self.visit(ctx.children[0])
            optr_expr = ctx.children[1]
            if rel_expr.type == FLOAT_TYPE or shift_expr.type == FLOAT_TYPE:
                if shift_expr.type != FLOAT_TYPE:
                    shift_expr = self.builder.sitofp(shift_expr, FLOAT_TYPE)
                if rel_expr.type != FLOAT_TYPE:
                    rel_expr = self.builder.sitofp(rel_expr, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=optr_expr.getText(), lhs=rel_expr
                                                 , rhs=shift_expr)
            else:
                return self.builder.icmp_signed(cmpop=optr_expr.getText(), lhs=rel_expr
                                                , rhs=shift_expr)
        else:
            raise Exception()


    def visitShiftExpression(self, ctx: CParser.ShiftExpressionContext):
        '''
            shiftExpression
            :   additiveExpression
            |   shiftExpression '<<' additiveExpression
            |   shiftExpression '>>' additiveExpression
            ;
        '''
        add_expr = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return add_expr
        elif len(ctx.children) == 3:
            shift_expr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '<<':
                return self.builder.shl(shift_expr, add_expr)
            elif ctx.children[1].getText() == '>>':
                return self.builder.ashr(shift_expr, add_expr)
        else:
            raise Exception()

    def visitAdditiveExpression(self, ctx: CParser.AdditiveExpressionContext):
        '''
            additiveExpression
            :   multiplicativeExpression
            |   additiveExpression '+' multiplicativeExpression
            |   additiveExpression '-' multiplicativeExpression
            ;
        '''
        mul_expr = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return mul_expr
        elif len(ctx.children) == 3:
            add_expr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '+':
                return self.builder.add(add_expr, mul_expr)
            elif ctx.children[1].getText() == '-':
                return self.builder.sub(add_expr, mul_expr)
        else:
            raise Exception()

    def visitMultiplicativeExpression(self, ctx: CParser.MultiplicativeExpressionContext):
        '''
            multiplicativeExpression
            :   castExpression
            |   multiplicativeExpression '*' castExpression
            |   multiplicativeExpression '/' castExpression
            |   multiplicativeExpression '%' castExpression
            ;
        '''
        cast_expr, _ = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return cast_expr
        elif len(ctx.children) == 3:
            mul_expr = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '*':
                return self.builder.mul(mul_expr, cast_expr)
            elif ctx.children[1].getText() == '/':
                return self.builder.sdiv(mul_expr, cast_expr)
            elif ctx.children[1].getText() == '%':
                return self.builder.srem(mul_expr, cast_expr)
        else:
            raise Exception()

    def visitCastExpression(self, ctx: CParser.CastExpressionContext):
        '''
            castExpression
            :   '(' typeName ')' castExpression
            |   unaryExpression
            |   DigitSequence // for
            ;
        '''
        if len(ctx.children) == 1:
            unary_expr, unary_expr_ptr = self.visit(ctx.children[0])
            if unary_expr_ptr is True:
                unary_expr_ptr = unary_expr
                unary_expr = self.builder.load(unary_expr_ptr)
            return unary_expr, unary_expr_ptr
        elif len(ctx.children) == 4 or len(ctx.children) == 5:
            if ctx.children[len(ctx.children)-4] != '(' or ctx.children[len(ctx.children)-2] != ')':
                raise Exception()
            else:
                cast_expr, cast_expr_pointer = self.visit(ctx.children[len(ctx.children)-1])
                type_name = self.visit(ctx.children[len(ctx.children)-3])
                cast_expr = self.builder.bitcast(cast_expr, type_name)
                return cast_expr, cast_expr_pointer

    def visitUnaryExpression(self, ctx: CParser.UnaryExpressionContext):
        '''
            unaryExpression
            :   postfixExpression
            |   '++' unaryExpression
            |   '--' unaryExpression
            |   unaryOperator castExpression
            |   'sizeof' unaryExpression
            |   'sizeof' '(' typeName ')'
            ;
        '''
        if len(ctx.children) == 1:
            postfix_expr, _ = self.visit(ctx.children[0])
            return postfix_expr, _
        elif len(ctx.children) == 2:
            optr = ctx.children[0]
            if optr.getText() == '++':
                unary_expr, unary_expr_ptr = self.visit(ctx.children[1])
                if unary_expr_ptr is True:
                    unary_expr_ptr = unary_expr
                    unary_expr = self.builder.load(unary_expr_ptr)
                    unary_expr = self.builder.add(unary_expr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unary_expr, unary_expr_ptr)
                return unary_expr, unary_expr_ptr
            elif optr.getText() == '--':
                unary_expr, unary_expr_ptr = self.visit(ctx.children[1])
                if unary_expr_ptr is True:
                    unary_expr_ptr = unary_expr
                    unary_expr = self.builder.load(unary_expr_ptr)
                    unary_expr = self.builder.sub(unary_expr, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unary_expr, unary_expr_ptr)
                return unary_expr, unary_expr_ptr
            elif optr.getText() == 'sizeof':
                raise Exception()
            elif optr.getText() == '&&':
                raise Exception()
            else:
                unary_expr, unary_expr_ptr = self.visit(ctx.children[1])
                if optr.getText() == '&':
                    return unary_expr_ptr, False
                elif optr.getText() == '*':
                    if unary_expr_ptr is True:
                        unary_expr_ptr = unary_expr
                        unary_expr = self.builder.load(unary_expr_ptr)
                    return unary_expr, unary_expr_ptr
                elif optr.getText() == '+':
                    return unary_expr, False
                elif optr.getText() == '-':
                    return self.builder.neg(unary_expr), False
                elif optr.getText() == '~':
                    return self.builder.not_(unary_expr), False
                elif optr.getText() == '!':
                    if unary_expr.type != FLOAT_TYPE:
                        return self.builder.icmp_signed(cmpop='==', lhs=unary_expr
                                                        , rhs=ir.Constant(INT_TYPE, 0)), False
                    else:
                        return self.builder.fcmp_ordered(cmpop='==', lhs=unary_expr
                                                         , rhs=ir.Constant(FLOAT_TYPE, 0)), False
        else:
            raise Exception()


    def visitFunctionDefinition(self, ctx):
        '''
            functionDefinition
            :   declarationSpecifiers? declarator declarationList? compoundStatement
            ;
        '''
        func_type = self.visit(ctx.declarationSpecifiers())
        func_name, params = self.visit(ctx.declarator())
        llvm_type = ir.FunctionType(func_type, [param[0] for param in params])
        llvm_func = ir.Function(self.module, llvm_type, name=func_name)
        self.symbol_table.insert(func_name, FUNCTION_TYPE, llvm_func)
        self.symbol_table = self.symbol_table.addLevel()
        
        func_block = llvm_func.append_basic_block(name= func_name+"entry")
        self.builder = ir.IRBuilder(func_block)
        
        for i, param in enumerate(params):
            param_type, param_name = param
            addr = self.builder.alloca(param_type, name=param_name)
            self.builder.store(llvm_func.args[i], addr)
            self.symbol_table.insert(param_name, param_type, addr)
        self.visit(ctx.compoundStatement())
        if not self.builder.block.is_terminated:
            self.builder.ret_void()
        self.symbol_table = self.symbol_table.exitLevel()

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
        iden_name = self.visit(ctx.getChild(0))
        if ctx.Identifier():
            self.symbol_table.insert(iden_name, (BASE_TYPE, None))
            return iden_name
        elif ctx.declarator():
            return self.visit(ctx.declarator())
        elif ctx.children[1].getText() == '[':
            length = self.visit(ctx.assignmentExpression())
            btype = (ARRAY_TYPE, length)
            self.symbol_table.insert(iden_name, type=btype)
            return iden_name
        elif ctx.children[1].getText() == '(':
            btype = (FUNCTION_TYPE, None)
            self.symbol_table.insert(iden_name, btype)
            params = self.visit(ctx.parameterTypeList()) if ctx.parameterTypeList() else []
            return iden_name, params

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
            |   enumSpecifier
            |   typedefName
            |   typeSpecifier pointer
            ;
        '''  
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
        if ctx.pointer():
            type = self.visit(ctx.typeSpecifier())
            return ir.PointerType(type)
        elif ctx.structOrUnionSpecifier():
            return self.visit(ctx.structOrUnionSpecifier())
        elif ctx.typedefName():
            return self.visit(ctx.typedefName())


    def visitStructOrUnionSpecifier(self, ctx: CParser.StructOrUnionSpecifierContext): 
        '''
            structOrUnionSpecifier
            :   structOrUnion Identifier? '{' structDeclarationList '}'
            |   structOrUnion Identifier
            ;
        '''  
        if ctx.structDeclarationList():
            struct_name = ctx.Identifier().getText()
            if self.symbol_table.getValue(struct_name):
                raise SemanticError("redefinition", ctx)
            else:
                dec_list = self.visit(ctx.structDeclarationList())
                param_list, type_list = [], []
                for dec in dec_list:
                    param_list.append({'name': dec['name'], 'type': dec['type']})
                    type_list.append(dec['type'])
                temp_struct = ir.global_context.get_identified_type(name=struct_name)
                temp_struct.set_body(*type_list)
                self.struct_table.insert(struct_name, temp_struct, param_list)
                return temp_struct
        else:
            struct_name = ctx.Identifier().getText()
            temp_struct = ir.global_context.get_identified_type(name=struct_name)
            return temp_struct

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
        dec_list = self.visit(ctx.structDeclarationList()) if ctx.structDeclarationList() else []
        dec_list.append(self.visit(ctx.structDeclaration()))
        return dec_list

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
            return {'type': self.visit(ctx.children[0]),
                    'name': self.visit(ctx.children[1])}

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

        dec_list = self.visit(ctx.initDeclaratorList())
        for name, init_val in dec_list:
            # system function declaration
            if isinstance(name, tuple):
                func_name, func_params = name
                args = [arg for arg, _ in func_params]
                func_type = ir.FunctionType(_type, args, var_arg=True)
                func = ir.Function(self.module, func_type, name=func_name)
                self.symbol_table.insert(func_name, type=(FUNCTION_TYPE, None), value=func)
            # struct declaration
            elif type(_type) == ir.types.IdentifiedStructType:
                ptr_struct = self.struct_table.getPtr(_type.name)
                self.symbol_table.insert(name, type=(STRUCT_TYPE, None), value=self.builder.alloca(ptr_struct))
            else:
                self.variableDeclaration(name, init_val, _type, ctx=ctx)

    def variableDeclaration(self, name, init_val, _type, ctx=None):
        '''
            declaration
            :   declarationSpecifiers initDeclaratorList ';'
            ;
        '''
        myType = self.symbol_table.getType(name)
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
                    raise SemanticError("length of initialization exceed length of array", ctx)

                for i in range(l):
                    indexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, i)]
                    ptr = self.builder.gep(value, indexs)
                    self.builder.store(init_val[i], ptr)
            value = self.builder.bitcast(value, ir.PointerType(_type))
            temp_ptr = self.builder.alloca(value.type)
            self.builder.store(value, temp_ptr)
            value = temp_ptr
            self.symbol_table.insert(name, type=myType, value=value)
        else:
            if self.builder:
                value = self.builder.alloca(_type, name=name)
            else:
                value = ir.GlobalValue(self.module, _type, name=name)

            if init_val:
                self.builder.store(init_val, value)

            self.symbol_table.insert(name, type=myType, value=value)

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
        dec_list = self.visit(ctx.initDeclaratorList()) if ctx.initDeclaratorList() else []
        dec_list.append(self.visit(ctx.initDeclarator()))
        return dec_list

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
        init_list = [self.visit(ctx.initializer())]
        if ctx.initializerList():
            init_list = self.visit(ctx.initializerList()) + init_list
        return init_list

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
        param_list = self.visit(ctx.parameterList()) if ctx.parameterList() else []
        temp_param = self.visit(ctx.parameterDeclaration())
        param_list.append(temp_param)
        return param_list

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
        if self.continue_to is not None:
            self.builder.branch(self.continue_to)
        else:
            raise SemanticError("No way to continue!\n", ctx)

    def visitBreakStatement(self, ctx: CParser.BreakStatementContext):
        '''
            breakStatement
            :   'break' ';'
            ;
        '''
        if self.break_to is not None:
            self.builder.branch(self.break_to)
        else:
            raise SemanticError("No way to break!\n", ctx)

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
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        last_continue_to = self.continue_to
        last_break_to = self.break_to
        self.continue_to = cond_block
        self.break_to = quit_block

        # The condition expression of While
        self.builder.branch(cond_block)
        self.builder.position_at_start(cond_block)
        expression = self.visit(ctx.expression())

        # Judge if jump to statement or quit
        self.builder.cbranch(expression, stat_block, quit_block)

        # The statement of While
        self.builder.position_at_start(stat_block)
        self.visit(ctx.statement())

        # Jump back to cond
        self.builder.branch(cond_block)

        # The quit block
        self.builder.position_at_start(quit_block)

        self.continue_to = last_continue_to
        self.break_to = last_break_to
        self.symbol_table = self.symbol_table.exitLevel()

    def visitDoWhileStatement(self, ctx:CParser.DoWhileStatementContext):
        '''
            doWhileStatement
            :   Do statement While '(' expression ')' ';'
            ;
        '''
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        last_continue_to = self.continue_to
        last_break_to = self.break_to
        self.continue_to = cond_block
        self.break_to = quit_block

        # The statement of While
        self.builder.branch(stat_block)
        self.builder.position_at_start(stat_block)
        self.visit(ctx.statement())

        # The condition expression of While
        self.builder.branch(cond_block)
        self.builder.position_at_start(cond_block)
        expr = self.visit(ctx.expression())

        # Judge if jump to statement or quit
        self.builder.cbranch(expr, stat_block, quit_block)

        # The quit block
        self.builder.position_at_start(quit_block)

        self.continue_to = last_continue_to
        self.break_to = last_break_to
        self.symbol_table = self.symbol_table.exitLevel()

    def visitForStatement(self, ctx:CParser.ForStatementContext):
        '''
            forStatement
            :   For '(' forCondition ')' statement
            ;
        '''
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        last_continue_to = self.continue_to
        last_break_to = self.break_to
        self.continue_to = cond_block
        self.break_to = quit_block

        cond_expr, op_expr = self.visit(ctx.forCondition())

        # The condition of For
        self.builder.branch(cond_block)
        self.builder.position_at_start(cond_block)
        cond_value = self.visit(cond_expr)

        self.builder.cbranch(cond_value, stat_block, quit_block)
        self.builder.position_at_start(stat_block)
        self.visit(ctx.statement())

        if op_expr:
            self.visit(op_expr)

        # come back to the cond
        self.builder.branch(cond_block)

        # quit block
        self.builder.position_at_start(quit_block)
        self.continue_to = last_continue_to
        self.break_to = last_break_to

        self.symbol_table = self.symbol_table.exitLevel()

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
        dec_list = self.visit(ctx.initDeclaratorList())

        for name, init_val in dec_list:
            self.variableDeclaration(name, init_val, type)

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
            self.symbol_table.addLevel()

            block_name = self.builder.block.name
            cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
            stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
            else_block = self.builder.append_basic_block(name='else'.format(block_name))
            quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

            # condition block
            self.builder.branch(cond_block)
            self.builder.position_at_start(cond_block)
            condition_value = self.visit(ctx.expression())
            self.builder.cbranch(condition_value, stat_block, else_block)

            # if block
            self.builder.position_at_start(stat_block)
            self.symbol_table.addLevel()
            self.visit(ctx.statement()[0])
            self.symbol_table.exitLevel()

            # if quit block
            try:
                self.builder.branch(quit_block)
            except:
                pass
            self.builder.position_at_start(quit_block)

            # else block
            self.builder.position_at_start(else_block)
            self.symbol_table.addLevel()
            self.visit(ctx.statement()[1])
            self.symbol_table.exitLevel()

            # else quit
            try:
                self.builder.branch(quit_block)
            except:
                pass
            self.builder.position_at_start(quit_block)

            self.symbol_table.exitLevel()

        else:  # no else
            self.symbol_table.addLevel()

            block_name = self.builder.block.name
            cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
            stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
            quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

            # condition block
            self.builder.branch(cond_block)
            self.builder.position_at_start(cond_block)
            condition_value = self.visit(ctx.expression())
            self.builder.cbranch(condition_value, stat_block, quit_block)

            # statement block
            self.builder.position_at_start(stat_block)
            self.symbol_table.addLevel()
            self.visit(ctx.statement()[0])
            self.symbol_table.exitLevel()

            # quit block
            try:
                self.builder.branch(quit_block)
            except:
                pass
            self.builder.position_at_start(quit_block)

            self.symbol_table.exitLevel()

    def output(self):
        return repr(self.module)



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
        f.write(visitor.output())


if __name__ == '__main__':
    main(sys.argv)