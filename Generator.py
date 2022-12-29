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
        if len(ctx.children) == 1:
            if ctx.Identifier():
                identifier = ctx.Identifier().getText()
                identifier_value = self.symbol_table.getValue(identifier)
                if identifier_value is None:
                    raise SemanticError("identifier not found", ctx)
                return identifier_value, True
            elif ctx.Constant():
                constant = ctx.Constant().getText()
                if constant.isdigit():
                    return ir.Constant(INT_TYPE, int(constant)), False
                elif constant[0] == "'":
                    constant = eval(constant)
                    return ir.Constant(CHAR_TYPE, ord(constant)), False
                else:
                    return ir.Constant(FLOAT_TYPE, float(constant)), False
            elif ctx.StringLiteral():
                string_literal = ctx.StringLiteral()[0].getText()
                # '\n' -> '
                string_literal = eval(string_literal)
                # to utf8
                string_llvm = [ir.Constant(CHAR_TYPE, ord(c)) for c in string_literal]
                string_llvm.append(ir.Constant(CHAR_TYPE, 0))
                addr = self.builder.alloca(ir.ArrayType(CHAR_TYPE, len(string_llvm)))
                self.builder.store(ir.Constant(ir.ArrayType(CHAR_TYPE, len(string_llvm)), string_llvm), addr)
                addr = self.builder.bitcast(addr, ir.PointerType(CHAR_TYPE))
                return addr, False
        else:
            return self.visit(ctx.children[1]), False        

    def visitPostfixExpression(self, ctx: CParser.PostfixExpressionContext):
        if len(ctx.children) == 1:
            primary_expression = self.visit(ctx.children[0])
            return primary_expression
        else:
            postfix_expression, postfix_expression_pointer = self.visit(ctx.children[0])
            if postfix_expression_pointer is True:
                if len(ctx.children) == 2:
                    postfix_operator = ctx.children[1]
                    if postfix_operator.getText() == '++':
                        postfix_expression_pointer = postfix_expression
                        postfix_expression = self.builder.load(postfix_expression_pointer)
                        self.builder.store(postfix_expression, postfix_expression_pointer)
                        new_postfix_expression = self.builder.add(postfix_expression, ir.Constant(INT_TYPE, 1))
                        self.builder.store(new_postfix_expression, postfix_expression_pointer)
                        return postfix_expression, postfix_expression_pointer
                    elif postfix_operator.getText() == '--':
                        postfix_expression_pointer = postfix_expression
                        postfix_expression = self.builder.load(postfix_expression_pointer)
                        self.builder.store(postfix_expression, postfix_expression_pointer)
                        new_postfix_expression = self.builder.sub(postfix_expression, ir.Constant(INT_TYPE, 1))
                        self.builder.store(new_postfix_expression, postfix_expression_pointer)
                        return postfix_expression, postfix_expression_pointer
                    else:
                        raise Exception()
                elif len(ctx.children) == 3:
                    postfix_operator = ctx.children[1]
                    if postfix_operator.getText() == '(':
                        argument_expression_list = []
                        return self.builder.call(postfix_expression, argument_expression_list), False
                    elif postfix_operator.getText() == '.':
                        postfix_expression_pointer, identifier_indices \
                            = self.handlePostfixExpressionInstance(ctx, postfix_operator)
                        postfix_expression_pointer = self.builder.gep(ptr=postfix_expression_pointer
                                                                      , indices=identifier_indices)
                        return postfix_expression_pointer, True
                    elif postfix_operator.getText() == '->':
                        postfix_expression_pointer, identifier_indices \
                            = self.handlePostfixExpressionInstance(ctx, postfix_operator)
                        postfix_expression_pointer = self.builder.gep(ptr=self.builder.load(postfix_expression_pointer)
                                                                      , indices=identifier_indices)
                        return postfix_expression_pointer, True
                    else:
                        raise Exception()
                elif len(ctx.children) == 4:
                    postfix_operator = ctx.children[1]
                    if postfix_operator.getText() == '[':
                        postfix_expression = self.builder.load(postfix_expression)
                        postfix_expression_array_type = self.builder.load(postfix_expression).type
                        postfix_expression_pointer_type = ir.PointerType(ir.ArrayType(postfix_expression_array_type, 0))
                        postfix_expression_pointer = self.builder.bitcast(postfix_expression, postfix_expression_pointer_type)
                        postfix_expression_index = self.visit(ctx.children[2])
                        postfix_expression_indices = [ir.Constant(INT_TYPE, 0), postfix_expression_index]
                        postfix_expression_pointer = self.builder.gep(ptr=postfix_expression_pointer, indices=postfix_expression_indices)
                        return postfix_expression_pointer, True
                    elif postfix_operator.getText() == '(':
                        argument_expression_list = self.visit(ctx.children[2])
                        return self.builder.call(postfix_expression, argument_expression_list), False
                    else:
                        raise Exception()
                else:
                    raise Exception()
            else:
                raise Exception()

        raise Exception()
        
    def handlePostfixExpressionInstance(self, ctx, postfix_operator):
        postfix_expression = ctx.children[0]
        identifier = ctx.children[2]
        postfix_expression_pointer = self.symbol_table.getValue(postfix_expression.getText())
        if postfix_operator.getText() == '.':
            identifier_index = self.struct_table.getParamRank(postfix_expression_pointer.type.pointee.name
                                                                  , identifier.getText())
        elif postfix_operator.getText() == '->':
            identifier_index = self.struct_table.getParamRank(postfix_expression_pointer.type.pointee.pointee.name
                                                                  , identifier.getText())
        else:
            raise Exception()
        identifier_indexs = [ir.Constant(INT_TYPE, 0), ir.Constant(INT_TYPE, identifier_index)]
        return postfix_expression_pointer, identifier_indexs   

    def visitArgumentExpressionList(self, ctx: CParser.ArgumentExpressionListContext):
        result_arg = []
        if ctx.argumentExpressionList():
            result_arg = self.visit(ctx.argumentExpressionList())
        result_arg.append(self.visit(ctx.assignmentExpression()))
        return result_arg

    def visitAssignmentExpression(self, ctx: CParser.AssignmentExpressionContext):
        if len(ctx.children) == 1:
            conditional_expression = self.visit(ctx.children[0])
            return conditional_expression
        elif len(ctx.children) == 3:
            unary_expression, unary_expression_pointer = self.visit(ctx.children[0])
            origin = self.builder.load(unary_expression)
            if unary_expression_pointer is True:
                assignment_operator = self.visit(ctx.children[1])
                assignment_expression = self.visit(ctx.children[2])
                if assignment_operator == '=':
                    return self.builder.store(assignment_expression, unary_expression)
                elif assignment_operator == '<<=':
                    edited = self.builder.shl(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '>>=':
                    edited = self.builder.ashr(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '&=':
                    edited = self.builder.and_(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '^=':
                    edited = self.builder.xor(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '|=':
                    edited = self.builder.or_(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '*=':
                    edited = self.builder.mul(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '/=':
                    edited = self.builder.sdiv(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '+=':
                    edited = self.builder.add(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '-=':
                    edited = self.builder.sub(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
                elif assignment_operator == '%=':
                    edited = self.builder.srem(origin, assignment_expression)
                    return self.builder.store(edited, unary_expression)
            else:
                raise Exception()

    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        logical_or_expression = self.visit(ctx.children[0])
        if len(ctx.children) == 1:
            return logical_or_expression
        elif len(ctx.children) == 5:
            operator_expression_questionmark = ctx.children[1]
            expression = self.visit(ctx.children[2])
            operator_expression_colon = ctx.children[3]
            conditional_expression = self.visit(ctx.children[4])
            if operator_expression_questionmark.getText() != '?' or operator_expression_colon.getText() != ':':
                raise Exception()
            else:
                raise Exception()
        else:
            raise Exception()


    def visitLogicalAndExpression(self, ctx: CParser.LogicalAndExpressionContext):
        inclusive_or_expression = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return inclusive_or_expression
        elif len(ctx.children) == 3:
            logical_and_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if operator_expression.getText() != '&&':
                raise Exception()
            else:
                if inclusive_or_expression.type != FLOAT_TYPE:
                    inclusive_or_expression = self.builder.icmp_signed(cmpop='!=', lhs=inclusive_or_expression
                                                                       , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    inclusive_or_expression = self.builder.fcmp_ordered(cmpop='!=', lhs=inclusive_or_expression
                                                                        , rhs=ir.Constant(FLOAT_TYPE, 0))
                if logical_and_expression.type != FLOAT_TYPE:
                    logical_and_expression = self.builder.icmp_signed(cmpop='!=', lhs=logical_and_expression
                                                                      , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    logical_and_expression = self.builder.fcmp_ordered(cmpop='!=', lhs=logical_and_expression
                                                                       , rhs=ir.Constant(FLOAT_TYPE, 0))
                return self.builder.and_(logical_and_expression, inclusive_or_expression)
        else:
            raise Exception()

    def visitInclusiveOrExpression(self, ctx: CParser.InclusiveOrExpressionContext):
        exclusive_or_expression = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return exclusive_or_expression
        elif len(ctx.children) == 3:
            inclusive_or_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if operator_expression.getText() != '|':
                raise Exception()
            else:
                return self.builder.or_(inclusive_or_expression, exclusive_or_expression)
        else:
            raise Exception()

    def visitExclusiveOrExpression(self, ctx: CParser.ExclusiveOrExpressionContext):
        and_expression = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return and_expression
        elif len(ctx.children) == 3:
            exclusive_or_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if operator_expression.getText() != '^':
                raise Exception()
            else:
                return self.builder.xor(exclusive_or_expression, and_expression)
        else:
            raise Exception()

    def visitAndExpression(self, ctx: CParser.AndExpressionContext):
        equality_expression = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return equality_expression
        elif len(ctx.children) == 3:
            and_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if operator_expression.getText() != '&':
                raise Exception()
            else:
                return self.builder.and_(and_expression, equality_expression)
        else:
            raise Exception()

    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        logical_or_expression = self.visit(ctx.children[0])
        if len(ctx.children) == 1:
            return logical_or_expression
        elif len(ctx.children) == 5:
            operator_expression_questionmark = ctx.children[1]
            expression = self.visit(ctx.children[2])
            operator_expression_colon = ctx.children[3]
            conditional_expression = self.visit(ctx.children[4])
            if operator_expression_questionmark.getText() != '?' or operator_expression_colon.getText() != ':':
                raise Exception()
            else:
                return expression
        else:
            raise Exception()

    def visitLogicalOrExpression(self, ctx: CParser.LogicalOrExpressionContext):
        logical_and_expression = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return logical_and_expression
        elif len(ctx.children) == 3:
            logical_or_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if operator_expression.getText() != '||':
                raise Exception()
            else:
                if logical_or_expression.type != FLOAT_TYPE:
                    logical_or_expression = self.builder.icmp_signed(cmpop='!=', lhs=logical_or_expression
                                                                     , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    logical_or_expression = self.builder.fcmp_ordered(cmpop='!=', lhs=logical_or_expression
                                                                      , rhs=ir.Constant(FLOAT_TYPE, 0))
                if logical_and_expression.type != FLOAT_TYPE:
                    logical_and_expression = self.builder.icmp_signed(cmpop='!=', lhs=logical_and_expression
                                                                      , rhs=ir.Constant(INT_TYPE, 0))
                else:
                    logical_and_expression = self.builder.fcmp_ordered(cmpop='!=', lhs=logical_and_expression
                                                                       , rhs=ir.Constant(FLOAT_TYPE, 0))
                return self.builder.or_(logical_or_expression, logical_and_expression)
        else:
            raise Exception()

    def visitEqualityExpression(self, ctx: CParser.EqualityExpressionContext):
        relational_expression = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return relational_expression
        elif len(ctx.children) == 3:
            equality_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if equality_expression.type == FLOAT_TYPE or relational_expression.type == FLOAT_TYPE:
                if equality_expression.type != FLOAT_TYPE:
                    equality_expression = self.builder.sitofp(equality_expression, FLOAT_TYPE)
                if relational_expression.type != FLOAT_TYPE:
                    relational_expression = self.builder.sitofp(relational_expression, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=operator_expression.getText(), lhs=equality_expression
                                                 , rhs=relational_expression)
            else:
                return self.builder.icmp_signed(cmpop=operator_expression.getText(), lhs=equality_expression
                                                , rhs=relational_expression)
        else:
            raise Exception()


    def visitRelationalExpression(self, ctx: CParser.RelationalExpressionContext):
        shift_expression = self.visit(ctx.children[len(ctx.children)-1])
        shift_type = shift_expression.type
        if len(ctx.children) == 1:
            return shift_expression
        elif len(ctx.children) == 3:
            relational_expression = self.visit(ctx.children[0])
            operator_expression = ctx.children[1]
            if relational_expression.type == FLOAT_TYPE or shift_expression.type == FLOAT_TYPE:
                if shift_expression.type != FLOAT_TYPE:
                    shift_expression = self.builder.sitofp(shift_expression, FLOAT_TYPE)
                if relational_expression.type != FLOAT_TYPE:
                    relational_expression = self.builder.sitofp(relational_expression, FLOAT_TYPE)
                return self.builder.fcmp_ordered(cmpop=operator_expression.getText(), lhs=relational_expression
                                                 , rhs=shift_expression)
            else:
                return self.builder.icmp_signed(cmpop=operator_expression.getText(), lhs=relational_expression
                                                , rhs=shift_expression)
        else:
            raise Exception()


    def visitShiftExpression(self, ctx: CParser.ShiftExpressionContext):
        additive_expression = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return additive_expression
        elif len(ctx.children) == 3:
            shift_expression = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '<<':
                return self.builder.shl(shift_expression, additive_expression)
            elif ctx.children[1].getText() == '>>':
                return self.builder.ashr(shift_expression, additive_expression)
        else:
            raise Exception()

    def visitAdditiveExpression(self, ctx: CParser.AdditiveExpressionContext):
        multiplicative_expression = self.visit(ctx.children[len(ctx.children)-1])
        if len(ctx.children) == 1:
            return multiplicative_expression
        elif len(ctx.children) == 3:
            additive_expression = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '+':
                return self.builder.add(additive_expression, multiplicative_expression)
            elif ctx.children[1].getText() == '-':
                return self.builder.sub(additive_expression, multiplicative_expression)
        else:
            raise Exception()

    def visitMultiplicativeExpression(self, ctx: CParser.MultiplicativeExpressionContext):
        cast_expression, _ = self.visit(ctx.children[len(ctx.children) - 1])
        if len(ctx.children) == 1:
            return cast_expression
        elif len(ctx.children) == 3:
            multiplicative_expression = self.visit(ctx.children[0])
            if ctx.children[1].getText() == '*':
                return self.builder.mul(multiplicative_expression, cast_expression)
            elif ctx.children[1].getText() == '/':
                return self.builder.sdiv(multiplicative_expression, cast_expression)
            elif ctx.children[1].getText() == '%':
                return self.builder.srem(multiplicative_expression, cast_expression)
        else:
            raise Exception()

    def visitCastExpression(self, ctx: CParser.CastExpressionContext):
        if len(ctx.children) == 1:
            unary_expression, unary_expression_pointer = self.visit(ctx.children[0])
            if unary_expression_pointer is True:
                unary_expression_pointer = unary_expression
                unary_expression = self.builder.load(unary_expression_pointer)
            return unary_expression, unary_expression_pointer
        elif len(ctx.children) == 4 or len(ctx.children) == 5:
            if ctx.children[len(ctx.children)-4] != '(' or ctx.children[len(ctx.children)-2] != ')':
                raise Exception()
            else:
                cast_expression, cast_expression_pointer = self.visit(ctx.children[len(ctx.children)-1])
                type_name = self.visit(ctx.children[len(ctx.children)-3])
                cast_expression = self.builder.bitcast(cast_expression, type_name)
                return cast_expression, cast_expression_pointer

    def visitUnaryExpression(self, ctx: CParser.UnaryExpressionContext):
        if len(ctx.children) == 1:
            postfix_expression, _ = self.visit(ctx.children[0])
            return postfix_expression, _
        elif len(ctx.children) == 2:
            operator = ctx.children[0]
            if operator.getText() == '++':
                unary_expression, unary_expression_pointer = self.visit(ctx.children[1])
                if unary_expression_pointer is True:
                    unary_expression_pointer = unary_expression
                    unary_expression = self.builder.load(unary_expression_pointer)
                    unary_expression = self.builder.add(unary_expression, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unary_expression, unary_expression_pointer)
                return unary_expression, unary_expression_pointer
            elif operator.getText() == '--':
                unary_expression, unary_expression_pointer = self.visit(ctx.children[1])
                if unary_expression_pointer is True:
                    unary_expression_pointer = unary_expression
                    unary_expression = self.builder.load(unary_expression_pointer)
                    unary_expression = self.builder.sub(unary_expression, ir.Constant(INT_TYPE, 1))
                    self.builder.store(unary_expression, unary_expression_pointer)
                return unary_expression, unary_expression_pointer
            elif operator.getText() == 'sizeof':
                raise Exception()
            elif operator.getText() == '&&':
                raise Exception()
            else:
                unary_expression, unary_expression_pointer = self.visit(ctx.children[1])
                if operator.getText() == '&':
                    return unary_expression_pointer, False
                elif operator.getText() == '*':
                    if unary_expression_pointer is True:
                        unary_expression_pointer = unary_expression
                        unary_expression = self.builder.load(unary_expression_pointer)
                    return unary_expression, unary_expression_pointer
                elif operator.getText() == '+':
                    return unary_expression, False
                elif operator.getText() == '-':
                    return self.builder.neg(unary_expression), False
                elif operator.getText() == '~':
                    return self.builder.not_(unary_expression), False
                elif operator.getText() == '!':
                    if unary_expression.type != FLOAT_TYPE:
                        return self.builder.icmp_signed(cmpop='==', lhs=unary_expression
                                                        , rhs=ir.Constant(INT_TYPE, 0)), False
                    else:
                        return self.builder.fcmp_ordered(cmpop='==', lhs=unary_expression
                                                         , rhs=ir.Constant(FLOAT_TYPE, 0)), False
        else:
            raise Exception()


    def visitFunctionDefinition(self, ctx):
        funcType = self.visit(ctx.declarationSpecifiers())
        funcName, params = self.visit(ctx.declarator())
        llvmType = ir.FunctionType(funcType, [param[0] for param in params])
        llvmFunc = ir.Function(self.module, llvmType, name=funcName)
        self.symbol_table.insert(funcName, FUNCTION_TYPE, llvmFunc)
        self.symbol_table = self.symbol_table.addLevel()
        
        block = llvmFunc.append_basic_block(name= funcName+"entry")
        self.builder = ir.IRBuilder(block)
        
        for i, param in enumerate(params):
            param_type, param_name = param
            address = self.builder.alloca(param_type, name=param_name)
            self.builder.store(llvmFunc.args[i], address)
            self.symbol_table.insert(param_name, param_type, address)
        self.visit(ctx.compoundStatement())
        if not self.builder.block.is_terminated:
            self.builder.ret_void()
        self.symbol_table = self.symbol_table.exitLevel()

    def visitDeclarator(self, ctx: CParser.DeclaratorContext):  
        return self.visit(ctx.directDeclarator())

    def visitDirectDeclarator(self, ctx: CParser.DirectDeclaratorContext):  
        name = self.visit(ctx.getChild(0))
        if ctx.Identifier():
            self.symbol_table.insert(name, (BASE_TYPE, None))
            return name
        elif ctx.children[1].getText() == '[':
            length = self.visit(ctx.assignmentExpression())
            btype = (ARRAY_TYPE, length)
            self.symbol_table.insert(name, type=btype)
            return name
        elif ctx.children[1].getText() == '(':
            btype = (FUNCTION_TYPE, None)
            self.symbol_table.insert(name, btype)
            params = self.visit(ctx.parameterTypeList()) if ctx.parameterTypeList() else []
            return name, params

    def visitTypeSpecifier(self, ctx: CParser.TypeSpecifierContext):  
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
        if ctx.structDeclarationList():
            struct_name = ctx.Identifier().getText()
            if self.symbol_table.getValue(struct_name):
                raise SemanticError("redefinition", ctx)
            else:
                dec_list = self.visit(ctx.structDeclarationList())
                paramList, typeList = [], []
                for dec in dec_list:
                    paramList.append({'name': dec['name'], 'type': dec['type']})
                    typeList.append(dec['type'])
                new_struct = ir.global_context.get_identified_type(name=struct_name)
                new_struct.set_body(*typeList)
                self.struct_table.insert(struct_name, new_struct, paramList)
                return new_struct
        else:
            struct_name = ctx.Identifier().getText()
            new_struct = ir.global_context.get_identified_type(name=struct_name)
            return new_struct

    def visitTypedefName(self, ctx: CParser.TypedefNameContext):  
        return ctx.getText()

    def visitStructDeclarationList(self, ctx: CParser.StructDeclarationListContext):  
        dec_list = self.visit(ctx.structDeclarationList()) if ctx.structDeclarationList() else []
        dec_list.append(self.visit(ctx.structDeclaration()))
        return dec_list

    def visitStructDeclaration(self, ctx: CParser.StructDeclarationContext):  
        return self.visit(ctx.specifierQualifierList())

    def visitStructDeclaratorList(self, ctx: CParser.StructDeclaratorListContext):  
        return self.visit(ctx.structDeclarator())

    def visitStructDeclarator(self, ctx: CParser.StructDeclaratorContext):   
        return self.visit(ctx.declarator())

    def visitSpecifierQualifierList(self, ctx: CParser.SpecifierQualifierListContext):  
        if not ctx.specifierQualifierList():
            return self.visit(ctx.typeSpecifier())
        else:
            return {'type': self.visit(ctx.children[0]),
                    'name': self.visit(ctx.children[1])}

    def visitStructOrUnion(self, ctx: CParser.StructOrUnionContext):   
        return ctx.getText()

    def visitDeclarationSpecifiers(self, ctx):  
        return self.visit(ctx.children[-1])

    def visitDeclarationSpecifier(self, ctx: CParser.DeclarationSpecifierContext):  
        return self.visit(ctx.children[0])

    def visitDeclaration(self, ctx):  
        _type = self.visit(ctx.declarationSpecifiers())
        if not ctx.initDeclaratorList():
            return ''

        declarator_list = self.visit(ctx.initDeclaratorList())
        for name, init_val in declarator_list:
            # system function declaration
            if isinstance(name, tuple):
                func_name, func_params = name
                args = [arg for arg, _ in func_params]
                fnty = ir.FunctionType(_type, args, var_arg=True)
                func = ir.Function(self.module, fnty, name=func_name)
                self.symbol_table.insert(func_name, type=(FUNCTION_TYPE, None), value=func)
            # struct declaration
            elif type(_type) == ir.types.IdentifiedStructType:
                ptr_struct = self.struct_table.getPtr(_type.name)
                self.symbol_table.insert(name, type=(STRUCT_TYPE, None), value=self.builder.alloca(ptr_struct))
            else:
                self.variableDeclaration(name, init_val, _type, ctx=ctx)

    def variableDeclaration(self, name, init_val, _type, ctx=None):
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
                    ptr = self.builder.gep(ptr=value, indices=indexs)
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
        for i in ctx.children:
            self.visit(i)

    def visitBlockItem(self, ctx):  
        return self.visit(ctx.getChild(0))

    def visitInitDeclaratorList(self, ctx):  
        dec_list = self.visit(ctx.initDeclaratorList()) if ctx.initDeclaratorList() else []
        dec_list.append(self.visit(ctx.initDeclarator()))
        return dec_list

    def visitInitDeclarator(self, ctx):   
        if ctx.initializer():
            return self.visit(ctx.declarator()), self.visit(ctx.initializer())
        else:
            return self.visit(ctx.declarator()), None

    def visitInitializer(self, ctx):   
        if ctx.assignmentExpression():
            return self.visit(ctx.assignmentExpression())
        elif ctx.initializerList():
            return self.visit(ctx.initializerList())

    def visitInitializerList(self, ctx: CParser.InitializerListContext):  
        init_list = [self.visit(ctx.initializer())]
        if ctx.initializerList():
            init_list = self.visit(ctx.initializerList()) + init_list
        return init_list

    def visitParameterTypeList(self, ctx: CParser.ParameterTypeListContext):  
        if ctx.parameterList():
            return self.visit(ctx.parameterList())

    def visitParameterList(self, ctx: CParser.ParameterListContext):  
        paramList = self.visit(ctx.parameterList()) if ctx.parameterList() else []
        new_param = self.visit(ctx.parameterDeclaration())
        paramList.append(new_param)
        return paramList

    def visitParameterDeclaration(self, ctx: CParser.ParameterDeclarationContext):  
        return [self.visit(ctx.declarationSpecifiers()), self.visit(ctx.declarator())]

    def visitTerminal(self, node):  
        return node.getText()

    def visitJumpStatement(self, ctx:CParser.JumpStatementContext):
        if ctx.breakStatement():
            self.visitBreakStatement(ctx.breakStatement())
        elif ctx.returnStatement():
            self.visitReturnStatement(ctx.returnStatement())
        elif ctx.continueStatement():
            self.visitContinueStatement(ctx.continueStatement())

    def visitContinueStatement(self, ctx: CParser.ContinueStatementContext):
        if self.continue_to is not None:
            self.builder.branch(self.continue_to)
        else:
            raise SemanticError("No way to continue!\n", ctx)

    def visitBreakStatement(self, ctx: CParser.BreakStatementContext):
        if self.break_to is not None:
            self.builder.branch(self.break_to)
        else:
            raise SemanticError("No way to break!\n", ctx)

    def visitReturnStatement(self, ctx: CParser.ReturnStatementContext):
        if ctx.expression():
            self.builder.ret(self.visit(ctx.expression()))
        else:
            self.builder.ret_void()

    def visitIterationStatement(self, ctx:CParser.IterationStatementContext):
        if ctx.whileStatement():
            self.visitWhileStatement(ctx.whileStatement())
        elif ctx.forStatement():
            self.visitForStatement(ctx.forStatement())
        elif ctx.doWhileStatement():
            self.visitDoWhileStatement(ctx.doWhileStatement())

    def visitWhileStatement(self, ctx:CParser.WhileStatementContext):
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        lst_continue_to = self.continue_to
        lst_break_to = self.break_to
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

        self.continue_to = lst_continue_to
        self.break_to = lst_break_to
        self.symbol_table = self.symbol_table.exitLevel()

    def visitDoWhileStatement(self, ctx:CParser.DoWhileStatementContext):
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        lst_continue_to = self.continue_to
        lst_break_to = self.break_to
        self.continue_to = cond_block
        self.break_to = quit_block

        # The statement of While
        self.builder.branch(stat_block)
        self.builder.position_at_start(stat_block)
        self.visit(ctx.statement())

        # The condition expression of While
        self.builder.branch(cond_block)
        self.builder.position_at_start(cond_block)
        expression = self.visit(ctx.expression())

        # Judge if jump to statement or quit
        self.builder.cbranch(expression, stat_block, quit_block)

        # The quit block
        self.builder.position_at_start(quit_block)

        self.continue_to = lst_continue_to
        self.break_to = lst_break_to
        self.symbol_table = self.symbol_table.exitLevel()

    def visitForStatement(self, ctx:CParser.ForStatementContext):
        self.symbol_table = self.symbol_table.addLevel()

        block_name = self.builder.block.name
        cond_block = self.builder.append_basic_block(name='cond'.format(block_name))
        stat_block = self.builder.append_basic_block(name='stat'.format(block_name))
        quit_block = self.builder.append_basic_block(name='quit'.format(block_name))

        lst_continue_to = self.continue_to
        lst_break_to = self.break_to
        self.continue_to = cond_block
        self.break_to = quit_block

        condition_expression, op_expression = self.visit(ctx.forCondition())

        # The condition of For
        self.builder.branch(cond_block)
        self.builder.position_at_start(cond_block)
        condition_value = self.visit(condition_expression)

        self.builder.cbranch(condition_value, stat_block, quit_block)
        self.builder.position_at_start(stat_block)
        self.visit(ctx.statement())

        if op_expression:
            self.visit(op_expression)

        # come back to the cond
        self.builder.branch(cond_block)

        # quit block
        self.builder.position_at_start(quit_block)
        self.continue_to = lst_continue_to
        self.break_to = lst_break_to

        self.symbol_table = self.symbol_table.exitLevel()

    def visitForCondition(self, ctx: CParser.ForConditionContext):
        
        if ctx.forDeclaration():
            self.visit(ctx.forDeclaration())
        elif ctx.expression():
            self.visit(ctx.expression())
        return ctx.forExpression(0), ctx.forExpression(1)

    def visitForDeclaration(self, ctx: CParser.ForDeclarationContext):
        
        _type = self.visit(ctx.declarationSpecifiers())
        declarator_list = self.visit(ctx.initDeclaratorList())

        for name, init_val in declarator_list:
            self.variableDeclaration(name, init_val, _type)

    def visitSelectionStatement(self, ctx: CParser.SelectionStatementContext):
        
        if ctx.ifStatement():
            self.visitIfStatement(ctx.ifStatement())
        elif ctx.switchStatement():
            self.visitSwitchStatement(ctx.switchStatement())

    def visitIfStatement(self, ctx: CParser.IfStatementContext):
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

    def visitSwitchStatement(self, ctx: CParser.SwitchStatementContext):
    
        block_name = self.builder.block.name
        head_block = self.builder.append_basic_block(name="head".format(block_name))
        stat_block = self.builder.append_basic_block(name="head".format(block_name))
        quit_block = self.builder.append_basic_block(name="quit".format(block_name))

        lst_break_to = self.break_to
        self.break_to = quit_block
        lst_switch_val = self.switch_val

        self.builder.branch(head_block)
        self.builder.position_at_start(head_block)
        self.switch_val = self.visit(ctx.expression())

        self.builder.branch(stat_block)
        self.builder.position_at_start(stat_block)
        self.symbol_table.addLevel()
        self.visit(ctx.statement())
        self.symbol_table.exitLevel()

        self.builder.branch(quit_block)
        self.builder.position_at_start(quit_block)

        self.switch_val = lst_switch_val
        self.break_to = lst_break_to

    def visitLabeledStatement(self, ctx:CParser.LabeledStatementContext):
        if ctx.Case():
            if self.switch_val:
                if self.switch_val != self.visit(ctx.constantExpression()):
                    self.visit(ctx.statement())
            else:
                raise SemanticError("No switch value!\n", ctx)
        elif ctx.Default():
            self.visit(ctx.statement())

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