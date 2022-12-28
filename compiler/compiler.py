from antlr4 import *
from llvmlite import ir, binding

import re
import os
from typing import Dict, List, Union, Optional, Tuple, Any

from .errors import CompilationError, SemanticError, ParserErrorListener
from .symbol_table import SymbolTable
from .values import TypedValue, const_value, ElementNamedLiteralStructType
from .elements import ParameterList, DeclarationSpecifiers
from .parser.CLexer import CLexer
from .parser.CParser import CParser
from .parser.CVisitor import CVisitor
from preprocessor import preprocess
from preprocessor.errors import MacroError

double = ir.DoubleType()
int1 = ir.IntType(1)
int16 = ir.IntType(16)
int32 = ir.IntType(32)
int64 = ir.IntType(64)
int8 = ir.IntType(8)
void = ir.VoidType()
size_t = int64

int_types = [int1, int8, int16, int32, int64]

int32_zero = ir.Constant(int32, 0)


class Visitor(CVisitor):
    """
    语义分析，生成 llvm
    """

    def __init__(self, target: str = 'x86_64-pc-linux-gnu'):
        super().__init__()

        # llvm 配置
        self.module: ir.Module = ir.Module()
        self.module.triple = target
        data_layout = 'e-m:e-i64:64-f80:128-n8:16:32:64-S128'
        self.module.data_layout = data_layout

        self.target_data = binding.create_target_data(data_layout)

        # 当前 llvm block 的语句生成器，在任何 visit 后都可能被改变
        self.builder: Optional[ir.IRBuilder] = None

        # 用于控制语句的标号
        self.continue_blocks: ir.BasicBlock = []
        self.break_blocks: ir.BasicBlock = []

        # 函数列表 Dict[名称, 是否有定义]
        self.functions: Dict[str, ir.Function] = dict()

        # 当前所在函数名
        self.current_function: Optional[ir.Function] = None
        self.constants = 0

        # 符号表
        self.symbol_table: SymbolTable = SymbolTable()
        self.symbol_table.add_item("short", int16)
        self.symbol_table.add_item("int", int32)
        self.symbol_table.add_item("long", int32)
        self.symbol_table.add_item("longlong", int64)
        self.symbol_table.add_item("double", double)
        self.symbol_table.add_item("char", int8)
        self.symbol_table.add_item("void", void)
        self.symbol_table.add_item("bool", int1)

        # 字符串常量表
        self.string_constants: Dict[str, TypedValue] = {}

    def is_global_scope(self) -> bool:
        """
        当前是否在全局作用域下.
        """
        return self.symbol_table.is_global()

    def visitFunctionDefinition(self, ctx: CParser.FunctionDefinitionContext) -> None:
        """
        函数定义.

        语法规则：
            functionDefinition
                :   declarationSpecifiers declarator compoundStatement
                ;

        Args:
            ctx (CParser.FunctionDefinitionContext):

        Returns:
            None
        """
        specifiers: DeclarationSpecifiers = self.visit(ctx.declarationSpecifiers())
        ret_type: ir.Type = specifiers.get_type()
        if ret_type is None:
            ret_type = int32
            print(f"Return type not specified, assuming int.")
        declarator_func = self.visit(ctx.declarator())
        function_name, function_type, parameter_list = declarator_func(ret_type, specifiers)
        parameter_list: ParameterList

        # 判断重定义，存储函数
        if function_name in self.functions:
            llvm_function = self.functions[function_name]
            if parameter_list.calling_convention and parameter_list.calling_convention != llvm_function.calling_convention:
                raise SemanticError("Calling convention not identical: "
                                    f"previous {llvm_function.calling_convention} "
                                    f"new {parameter_list.calling_convention}", ctx)
            if len(llvm_function.blocks) > 0:
                raise SemanticError('函数重定义: ' + function_name, ctx)
        else:
            llvm_function = ir.Function(self.module, function_type, name=function_name)
            result = self.symbol_table.add_item(function_name, TypedValue(ir_value=llvm_function,
                                                                          typ=llvm_function.type,
                                                                          constant=False,
                                                                          name=function_name,
                                                                          lvalue_ptr=False))
            if not result.success:
                raise SemanticError('函数重定义: ' + function_name, ctx)

        # 函数的参数名
        for i in range(len(parameter_list)):
            typ, name = parameter_list[i]
            llvm_function.args[i].name = name

        # 函数 block
        alloca_block: ir.Block = llvm_function.append_basic_block(name=f'{function_name}.entry')
        block: ir.Block = llvm_function.append_basic_block(name=f'{function_name}.code')
        self.functions[function_name] = llvm_function

        self.builder = ir.IRBuilder(block)

        # 进入函数作用域
        self.current_function = llvm_function
        self.symbol_table.enter_scope()

        # 存储函数的变量
        for i in range(len(parameter_list)):
            func_arg = llvm_function.args[i]
            variable = self.builder.alloca(func_arg.type)
            self.builder.store(func_arg, variable)
            result = self.symbol_table.add_item(func_arg.name, TypedValue(ir_value=variable,
                                                                          typ=func_arg.type,
                                                                          constant=False,
                                                                          lvalue_ptr=True,
                                                                          name=parameter_list[i][1]))
            if not result.success:
                raise SemanticError(result.message, ctx)

        # 处理函数体
        self.visit(ctx.compoundStatement())  # funcBody
        if not self.builder.basic_block.is_terminated:
            if function_name == 'main':
                self.builder.ret(int32_zero)
            else:
                self.builder.ret_void()

        # 处理完毕，退出函数作用域
        ir.IRBuilder(alloca_block).branch(block)
        self.current_function = ''
        self.builder = None
        self.symbol_table.quit_scope()
        return

    def visitDirectDeclarator_1(self, ctx: CParser.DirectDeclarator_1Context):
        """
        directDeclarator : Identifier
        """
        identifier = ctx.getText()
        return lambda typ, _: (identifier, typ, None)

    def visitDirectDeclarator_2(self, ctx: CParser.DirectDeclarator_2Context):
        """
        directDeclarator : '(' declarator ')'
        """
        return self.visit(ctx.declarator())

    def visitDirectDeclarator_3(self, ctx: CParser.DirectDeclarator_3Context):
        """
        directDeclarator : directDeclarator '[' assignmentExpression? ']'
        """
        arr_len = 0
        if ctx.assignmentExpression() is not None:
            exp_const: ir.Constant = self.visit(ctx.assignmentExpression()).ir_value
            if isinstance(exp_const, ir.Constant):
                arr_len = exp_const.constant
            else:
                raise SemanticError("数组的长度必须是常量表达式", ctx)
        declarator_func = self.visit(ctx.directDeclarator())

        def create_arr_ret(typ: ir.Type, specifiers):
            typ1 = ir.ArrayType(typ, arr_len)
            return declarator_func(typ1, specifiers)

        return create_arr_ret

    def visitDirectDeclarator_4(self, ctx: CParser.DirectDeclarator_4Context):
        """
        directDeclarator : directDeclarator '(' parameterTypeList ')'
        """
        declarator_func = self.visit(ctx.directDeclarator())
        parameter_list = self.visit(ctx.parameterTypeList())

        def create_func_ret(typ: ir.Type, specifiers: DeclarationSpecifiers):
            typ1 = ir.FunctionType(typ, parameter_list.arg_list, parameter_list.var_arg)
            identifier2, typ2, _ = declarator_func(typ1, specifiers)
            for func_spec in specifiers.get_function_specifiers():
                if func_spec == "__cdecl":
                    parameter_list.calling_convention = "ccc"
                elif func_spec == "__stdcall":
                    parameter_list.calling_convention = "cc 65"
            return identifier2, typ2, parameter_list

        return create_func_ret

    def visitParameterTypeList(self, ctx: CParser.ParameterTypeListContext) -> ParameterList:
        """
        Parameter type list
        语法规则: parameterTypeList : | parameterList | parameterList ',' '...' ;
        Return: ParameterList: 参数列表
        """
        if ctx.parameterList():
            return ParameterList(self.visit(ctx.parameterList()), ctx.getChildCount() == 3)
        return ParameterList([], False)

    def visitParameterList(self, ctx: CParser.ParameterListContext) -> List[Tuple[ir.Type, Optional[str]]]:
        """
        Parameters

        语法规则:
            parameterList
                :   parameterDeclaration
                |   parameterList ',' parameterDeclaration
                ;
        Returns:
            List[Tuple[ir.Type, Optional[str]]]: (类型, 可选名称) 的列表
        """
        if ctx.parameterList() is not None:
            prev_list = self.visit(ctx.parameterList())
        else:
            prev_list = []
        param = self.visit(ctx.parameterDeclaration())
        prev_list.append(param)
        return prev_list

    def visitParameterDeclaration(self, ctx: CParser.ParameterDeclarationContext) -> Tuple[
        ir.Type, Optional[str]]:
        """
        Parameter declaration
        语法规则: parameterDeclaration : declarationSpecifiers declarator ;
        """
        specifiers: DeclarationSpecifiers = self.visit(ctx.declarationSpecifiers())
        base_type = specifiers.get_type()
        if base_type is None:
            raise SemanticError("未指定类型", ctx)
        declarator_func = self.visit(ctx.declarator())
        identifier, typ, parameter_list = declarator_func(base_type, specifiers)
        if isinstance(typ, ir.ArrayType):
            # decay
            typ: ir.ArrayType
            typ = typ.element.as_pointer()
        return typ, identifier

    def visitDeclarationSpecifiers(self, ctx: CParser.DeclarationSpecifiersContext) -> DeclarationSpecifiers:
        """
        声明限定符列表.

        语法规则：
            declarationSpecifiers: declarationSpecifier*;

        Args:
            ctx (CParser.DeclarationSpecifiersContext):

        Returns:
            List[Tuple[str, Any]]: 限定符列表
        """
        specifiers = DeclarationSpecifiers()
        for i in range(ctx.getChildCount()):
            typ, val = self.visit(ctx.getChild(i))
            specifiers.append(typ, val)
        return specifiers

    def visitDeclarationSpecifier(self, ctx: CParser.DeclarationSpecifierContext) -> (str, Union[str, ir.Type]):
        """
        声明限定符

        语法规则:
            declarationSpecifier
                :   storageClassSpecifier
                |   typeSpecifier
                |   typeQualifier
                |   functionSpecifier
                ;
        """
        if ctx.storageClassSpecifier():
            return "storage", ctx.getText()
        if ctx.typeSpecifier():
            return "type", self.visit(ctx.typeSpecifier())
        if ctx.typeQualifier():
            return "type_qualifier", self.visit(ctx.typeQualifier())
        if ctx.functionSpecifier():
            return "function_specifier", self.visit(ctx.functionSpecifier())
        raise SemanticError('impossible')

    def visitDeclarator(self, ctx: CParser.DeclaratorContext):
        """
        描述符.

        语法规则：
            declarator
                :   pointer? directDeclarator
                ;

        Args:
            ctx (CParser.DeclaratorContext):

        Returns:
            lambda typ : (str, ir.Type, Optional[ParameterList])
                str: 标识符
                ir.Type: 类型
                Optional[ParameterList]: 参数列表 (对于函数声明/定义有效)
        """
        declarator_func = self.visit(ctx.directDeclarator())
        if ctx.pointer():
            pointer = self.visit(ctx.pointer())

            def pointer_declarator(typ: ir.Type, specifier):
                typ1 = typ
                for _ in pointer:
                    typ1 = typ1.as_pointer()
                identifier1, typ1, parameter_list1 = declarator_func(typ1, specifier)
                return identifier1, typ1, parameter_list1

            return pointer_declarator
        return declarator_func

    def visitForDeclaration(self, ctx: CParser.ForDeclarationContext) -> None:
        """
        声明.

        语法规则：
            forDeclaration
                :   declarationSpecifiers initDeclaratorList ';'
                |   declarationSpecifiers ';'
                ;

        Args:
            ctx (CParser.DeclarationContext):

        Returns:
            None
        """
        specifiers: DeclarationSpecifiers = self.visit(ctx.declarationSpecifiers())
        if ctx.initDeclaratorList():
            init_decl_list = self.visit(ctx.initDeclaratorList())
        else:
            init_decl_list = []
        base_type = specifiers.get_type()
        if base_type is None:
            raise SemanticError("Unspecified declarator type", ctx)
        for decl, initializer in init_decl_list:
            identifier, typ, parameter_list = decl(base_type, specifiers)
            if specifiers.is_typedef():
                if initializer is not None:
                    raise SemanticError("Illegal initializer (only variables can be initialized)", ctx)
                result = self.symbol_table.add_item(identifier, typ)
                if not result.success:
                    raise SemanticError("Symbol redefined: " + identifier, ctx)
            else:
                if self.is_global_scope():
                    if self.symbol_table.exist(identifier):
                        raise SemanticError("Symbol redefined: " + identifier, ctx)
                    if isinstance(typ, ir.FunctionType):
                        variable = ir.Function(self.module, typ, identifier)
                        self.functions[identifier] = variable
                        self.symbol_table.add_item(identifier, TypedValue(ir_value=variable,
                                                                          typ=variable.type,
                                                                          constant=False,
                                                                          name=identifier,
                                                                          lvalue_ptr=False))
                    else:
                        variable = ir.GlobalVariable(self.module, typ, identifier)
                        self.symbol_table.add_item(identifier, TypedValue(ir_value=variable,
                                                                          typ=typ,
                                                                          constant=False,
                                                                          name=identifier,
                                                                          lvalue_ptr=True))
                        if specifiers.is_extern():
                            if initializer is not None:
                                raise SemanticError("External variable cannot be initialized.", ctx)
                        else:
                            if specifiers.is_static():
                                variable.linkage = "internal"
                            if initializer is not None:
                                variable.initializer = self.create_initializer_list(typ, initializer, True, ctx)
                            else:
                                variable.initializer = ir.Constant(typ, None)
                else:
                    if specifiers.is_static():
                        variable = ir.GlobalVariable(self.module, typ, f"{self.current_function.name}.{identifier}")
                        variable.initializer = ir.Constant(typ, None)
                        variable.linkage = "internal"
                    else:
                        variable = ir.IRBuilder(self.current_function.blocks[0]).alloca(typ, 1)
                    result = self.symbol_table.add_item(identifier, TypedValue(ir_value=variable,
                                                                               typ=typ,
                                                                               constant=False,
                                                                               name=identifier,
                                                                               lvalue_ptr=True))
                    if not result.success:
                        raise SemanticError("Symbol redefined: " + identifier, ctx)
                    if initializer:
                        if not isinstance(initializer, TypedValue):
                            value = self.create_initializer_list(typ, initializer, True, ctx)
                        else:
                            value = self.convert_type(initializer, typ, ctx=ctx)
                        self.builder.store(value, variable)

    def visitInitializerList(self, ctx: CParser.InitializerListContext) -> List[Union[TypedValue, list]]:
        """
        initializerList
            : initializer
            | initializerList ',' initializer
            ;
        """
        if ctx.initializerList():
            initializers = self.visit(ctx.initializerList())
        else:
            initializers = []
        initializers.append(self.visit(ctx.initializer()))
        return initializers

    def visitInitializer(self, ctx: CParser.InitializerContext) -> Union[TypedValue, list]:
        """
        initializer
            : assignmentExpression
            | '{' initializerList '}'
            | '{' initializerList ',' '}'
            ;
        """
        if ctx.initializerList():
            return self.visit(ctx.initializerList())
        else:
            return self.visit(ctx.assignmentExpression())

    def visitInitDeclarator(self, ctx: CParser.InitDeclaratorContext):
        """
        初始化声明.

        语法规则:
            initDeclarator
                :   declarator
                |   declarator '=' initializer
                ;
        Returns:
            lambda typ : (str, ir.Type, Optional[ParameterList])
                str: 标识符
                ir.Type: 类型
                Optional[ParameterList]: 参数列表 (对于函数声明/定义有效)
            initializer: ir.Value
        """
        declarator = self.visit(ctx.declarator())
        initializer_ctx = ctx.initializer()
        if initializer_ctx is not None:
            initializer = self.visit(ctx.initializer())
        else:
            initializer = None
        return declarator, initializer

    def visitInitDeclaratorList(self, ctx: CParser.InitDeclaratorListContext) -> List[tuple]:
        """
        初始化声明列表.

        语法规则:
            initDeclaratorList
                :   initDeclarator
                |   initDeclaratorList ',' initDeclarator
                ;
        Returns:
            List[tuple]: 一串初始化声明，列表的成员定义见 Generator#visitInitDeclarator
        """
        init_declarator_list_ctx = ctx.initDeclaratorList()
        if init_declarator_list_ctx is not None:
            init_declarator_list = self.visit(ctx.initDeclaratorList())
        else:
            init_declarator_list = []
        init_declarator_list.append(self.visit(ctx.initDeclarator()))
        return init_declarator_list

    def visitQualifiedPointer(self, ctx: CParser.QualifiedPointerContext) -> Dict[str, bool]:
        """
        指针修饰符

        语法规则：
            pointer : '*' typeQualifierList?

        Args:
            ctx (CParser.QualifiedPointerContext):

        Returns:
            {
                'const': bool 是否为 const
                'volatile': bool 是否为 volatile
            }
        """
        qualifiers = []
        if ctx.getChildCount() > 1:
            qualifiers = self.visit(ctx.getChild(1))
        return {
            'const': 'const' in qualifiers,
            'volatile': 'volatile' in qualifiers,
        }

    def visitPointer(self, ctx: CParser.PointerContext) -> List[Dict[str, bool]]:
        """
        指针修饰符列表

        语法规则：
            pointer : qualifiedPointer | pointer qualifiedPointer

        Args:
            ctx (CParser.PointerContext):

        Returns:
            {
                'const': bool 是否为 const
                'volatile': bool 是否为 volatile
            }[]
        """
        if ctx.getChildCount() > 1:
            pointers: List[Dict[str, bool]] = self.visit(ctx.getChild(0))
            pointers.append(self.visit(ctx.getChild(1)))
            return pointers
        else:
            return [self.visit(ctx.getChild(0))]

    def visitTypeQualifierList(self, ctx: CParser.TypeQualifierListContext) -> List[str]:
        """
        修饰符列表

        语法规则：
            typeQualifierList : typeQualifier+

        Args:
            ctx (CParser.TypeQualifierListContext):

        Returns:
            List[str]: 修饰符列表
        """
        qualifiers = []
        for i in range(ctx.getChildCount()):
            qualifiers.append(self.visit(ctx.getChild(i)))
        return qualifiers

    def visitTypeQualifier(self, ctx: CParser.TypeQualifierContext) -> str:
        """
        修饰符

        语法规则：
            typeQualifier : 'const' | 'volatile' ;

        Args:
            ctx (CParser.TypeQualifierContext):

        Returns:
            str: 修饰符
        """
        return ctx.getText()

    def visitTypeSpecifier_1(self, ctx: CParser.TypeSpecifier_3Context) -> ir.Type:
        # typeSpecifier : primitiveType
        return self.visit(ctx.primitiveType())

    def visitTypeSpecifier_2(self, ctx: CParser.TypeSpecifier_2Context) -> ir.Type:
        # typeSpecifier : typedefName
        type_id: str = ctx.getText()
        actual_type = self.symbol_table.get_item(type_id)
        if actual_type is None:
            raise SemanticError("Undefined type: " + type_id, ctx)
        if not isinstance(actual_type, ir.Type):
            raise SemanticError("Invalid type: " + type_id, ctx)
        return actual_type

    def visitTypeSpecifier_3(self, ctx: CParser.TypeSpecifier_1Context) -> ir.Type:
        # typeSpecifier : typeSpecifier pointer
        base_type: ir.Type = self.visit(ctx.typeSpecifier())
        pointer_list: list = self.visit(ctx.pointer())
        for _ in pointer_list:  # const 之类的访问修饰符被省略
            base_type = base_type.as_pointer()
        return base_type

    def visitEnumSpecifier(self, ctx: CParser.EnumSpecifierContext) -> ir.Type:
        """
        enumSpecifier
            :   'enum' Identifier? '{' enumeratorList '}'
            |   'enum' Identifier? '{' enumeratorList ',' '}'
            |   'enum' Identifier
            ;
        """
        identifier_ctx = ctx.Identifier()
        if identifier_ctx is not None:
            identifier = f'enum {identifier_ctx.getText()}'
        else:
            identifier = None

        if ctx.enumeratorList():
            enumerator_list = self.visit(ctx.enumeratorList())
            current: int = 0
            for (name, constant) in enumerator_list:
                if constant is not None:
                    constant: ir.Constant
                    current = constant.constant
                result = self.symbol_table.add_item(name, const_value(ir.Constant(size_t, current), name=name))
                if not result.success:
                    raise SemanticError("Symbol redefined: " + identifier, ctx)
                current += 1
            item = self.symbol_table.get_item(identifier)
            if item is not None:
                raise SemanticError("Symbol redefined: " + identifier, ctx)
            self.symbol_table.add_item(identifier, size_t)
            return size_t
        else:
            item = self.symbol_table.get_item(identifier)
            if item is None:
                raise SemanticError("Undefined identifier: " + identifier, ctx)
            return item

    def visitEnumeratorList(self, ctx: CParser.EnumeratorListContext) -> List[
            Tuple[str, Optional[ir.Constant]]]:
        """
        enumeratorList
            :   enumerator
            |   enumeratorList ',' enumerator
            ;
        """
        if ctx.enumeratorList():
            enumerators = self.visit(ctx.enumeratorList())
        else:
            enumerators = []
        enumerators.append(self.visit(ctx.enumerator()))
        return enumerators

    def visitEnumerator(self, ctx: CParser.EnumeratorContext) -> Tuple[str, Optional[ir.Constant]]:
        """
        enumerator
            :   Identifier
            |   Identifier '=' constantExpression
            ;
        """
        identifier = ctx.Identifier().getText()
        if ctx.constantExpression():
            constant: TypedValue = self.visit(ctx.constantExpression())
            if not self.is_ir_constant(constant) or not constant.type in int_types:
                raise SemanticError(f"Enum initializer must be a constant integral: {constant.ir_value}", ctx)
            constant: ir.Value = constant.ir_value
        else:
            constant: Optional[ir.Constant] = None
        return identifier, constant

    def visitCompoundStatement(self, ctx: CParser.CompoundStatementContext) -> None:
        self.symbol_table.enter_scope()
        self.visitChildren(ctx)
        self.symbol_table.quit_scope()

    def visitSelectionStatement(self, ctx: CParser.SelectionStatementContext) -> None:
        """
        selectionStatement
            : 'if' '(' expression ')' statement ('else' statement)?
            | 'switch' '(' expression ')' statement
            ;
        """
        kw = ctx.getChild(0).getText()
        if kw == 'if':
            has_else = len(ctx.statement()) > 1
            block_true = self.builder.append_basic_block(name='if.then')
            blocks = [block_true]
            if has_else:
                block_false = self.builder.append_basic_block(name='if.else')
                block_end = self.builder.append_basic_block(name='if.end')
                blocks.append(block_false)
            else:
                block_end = self.builder.append_basic_block(name='if.end')
                block_false = block_end
            cond = self.ir_bool(self.visit(ctx.expression()))
            self.builder.cbranch(cond, block_true, block_false)
            for i, block in enumerate(blocks):
                self.builder = ir.IRBuilder(block)
                self.visit(ctx.statement()[i])
                if not self.builder.basic_block.is_terminated:
                    self.builder.branch(block_end)
            self.builder = ir.IRBuilder(block_end)
            return
        if kw == 'switch':
            raise SemanticError("Not implemented", ctx)
        raise SemanticError('impossible')

    def visitIterationStatement(self, ctx: CParser.SelectionStatementContext) -> None:
        """
        iterationStatement
            : 'while' '(' expression ')' statement
            | 'do' statement 'while' '(' expression ')' ';'
            | 'for' '(' forCondition ')' statement
            ;
        """
        kw = ctx.getChild(0).getText()
        if ctx.first:
            self.symbol_table.enter_scope()
            self.visit(ctx.first)
        block_cond = self.builder.append_basic_block(name='loop.cond')
        block_body = self.builder.append_basic_block(name='loop.body')
        block_end = self.builder.append_basic_block(name='loop.end')
        # Terminate last block
        if kw == 'do':
            self.builder.branch(block_body)
        else:
            self.builder.branch(block_cond)
        # Build blocks
        self.builder = ir.IRBuilder(block_cond)
        if kw == 'for':
            cond_ctx = ctx.second
        else:
            cond_ctx = ctx.expression()
        if cond_ctx:
            cond = self.ir_bool(self.visit(cond_ctx))
            self.builder.cbranch(cond, block_body, block_end)
        else:
            self.builder.branch(block_body)
        if ctx.third:
            block_update = self.builder.append_basic_block(name='loop.update')
            self.builder = ir.IRBuilder(block_update)
            self.visit(ctx.third)
            self.builder.branch(block_cond)
            self.continue_blocks.append(block_update)
        else:
            self.continue_blocks.append(block_cond)
        self.break_blocks.append(block_end)
        self.builder = ir.IRBuilder(block_body)
        self.visit(ctx.statement())
        if not self.builder.basic_block.is_terminated:
            self.builder.branch(self.continue_blocks[-1])
        self.continue_blocks.pop()
        self.break_blocks.pop()
        if ctx.first:
            self.symbol_table.quit_scope()
        self.builder = ir.IRBuilder(block_end)

    def visitJumpStatement(self, ctx: CParser.JumpStatementContext) -> None:
        """
        jumpStatement
            : 'continue' ';'
            | 'break' ';'
            | 'return' expression? ';'
            ;
        """
        kw = ctx.getChild(0).getText()
        if kw == 'continue':
            if not self.continue_blocks:
                raise SemanticError('Nothing to continue', ctx)
            self.builder.branch(self.continue_blocks[-1])
            return
        if kw == 'break':
            if not self.break_blocks:
                raise SemanticError('Nothing to break', ctx)
            self.builder.branch(self.break_blocks[-1])
            return
        if kw == 'return':
            ret_value_ctx = ctx.expression()
            ret_value = None
            if ret_value_ctx is not None:
                ret_type = self.current_function.function_type.return_type
                ret_value = self.convert_type(self.visit(ret_value_ctx), ret_type, ctx=ctx)
                self.builder.ret(ret_value)
            else:
                self.builder.ret_void()
            return
        raise SemanticError('impossible')

    def visitStructSpecifier_1(self, ctx: CParser.StructSpecifier_1Context) -> ElementNamedLiteralStructType:
        # structSpecifier : 'struct' Identifier? '{' structDeclarationList '}'
        identifier_ctx = ctx.Identifier()
        if identifier_ctx is not None:
            identifier = f'struct {identifier_ctx.getText()}'
            item = self.symbol_table.get_item(identifier)
            if item is not None:
                raise SemanticError("Symbol redefined: " + identifier, ctx)
        else:
            identifier = None
        struct_member_list = self.visit(ctx.structDeclarationList())
        type_list = [member[0] for member in struct_member_list]
        name_list = [member[1] for member in struct_member_list]
        struct_type = ElementNamedLiteralStructType(type_list, name_list)
        if identifier:
            self.symbol_table.add_item(identifier, struct_type)
        return struct_type

    def visitStructSpecifier_2(self, ctx: CParser.StructSpecifier_2Context) -> ElementNamedLiteralStructType:
        # structSpecifier : 'struct' Identifier
        identifier = f'struct {ctx.Identifier().getText()}'
        item = self.symbol_table.get_item(identifier)
        if item is None:
            raise SemanticError("Undefined identifier: " + identifier, ctx)
        return item

    def visitStructDeclarationList(self, ctx: CParser.StructDeclarationListContext) -> List[
        Tuple[ir.Type, str]]:
        """
        语法规则：
            structDeclarationList
                :   structDeclaration
                |   structDeclarationList structDeclaration
                ;
        """
        if ctx.structDeclarationList():
            prev_list = self.visit(ctx.structDeclarationList())
        else:
            prev_list = []
        prev_list += self.visit(ctx.structDeclaration())
        return prev_list

    def visitStructDeclaration(self, ctx: CParser.StructDeclarationContext) -> List[Tuple[ir.Type, str]]:
        # structDeclaration : declarationSpecifiers structDeclaratorList? ';' ;
        specifiers: DeclarationSpecifiers = self.visit(ctx.declarationSpecifiers())
        if ctx.structDeclaratorList():
            decl_list = self.visit(ctx.structDeclaratorList())
        else:
            decl_list = None
        base_type: ir.Type = specifiers.get_type()
        if base_type is None:
            raise SemanticError("Unspecified declarator type", ctx)
        member_list = []
        if decl_list is not None:
            for decl in decl_list:
                identifier, typ, parameter_list = decl(base_type, specifiers)
                if specifiers.is_typedef():
                    raise SemanticError("Illegal typedef here", ctx)
                member_list.append((typ, identifier))
        else:
            if isinstance(base_type, ElementNamedLiteralStructType):
                member_list.append((base_type, None))
            else:
                # todo: warning
                print("Declaration does not declare anything.")
        return member_list

    def visitStructDeclaratorList(self, ctx: CParser.StructDeclaratorListContext):
        """
        语法规则:
            structDeclaratorList
                :   declarator
                |   structDeclaratorList ',' declarator
                ;
        """
        if ctx.structDeclaratorList():
            prev_list = self.visit(ctx.structDeclaratorList())
        else:
            prev_list = []
        prev_list.append(self.visit(ctx.declarator()))
        return prev_list

    @staticmethod
    def is_ir_constant(value: TypedValue) -> bool:
        return isinstance(value.ir_value, ir.Constant)

    def load_lvalue(self, lvalue_ptr: TypedValue) -> ir.Value:
        """
        从左值地址加载出一个值.
        如果是数组，不会被加载. 不要试图加载数组.

        :param lvalue_ptr: 待加载的值
        :type lvalue_ptr: TypedValue
        :return: 如果这个值是右值，返回原始值，否则返回加载后的值
        :rtype: ir.Value
        """
        if not lvalue_ptr.lvalue_ptr or isinstance(lvalue_ptr.type, ir.ArrayType):
            return lvalue_ptr.ir_value
        return self.builder.load(lvalue_ptr.ir_value)

    def store_lvalue(self, value: ir.Value, lvalue_ptr: TypedValue, new_type: ir.Type = None) -> None:
        """
        将值存入左值地址处.

        :param value: 待存储的值
        :type value: ir.Value
        :param lvalue_ptr: 左值的地址(的 TypedValue)
        :type lvalue_ptr: TypedValue
        :param new_type: 如果不为 None，则替换旧类型
        :type new_type: ir.Type
        :return: 如果 lvalue_ptr 是右值，则替换原来的 value，存储 value 至 lvalue_ptr 处
        :rtype: None
        """
        if not lvalue_ptr.lvalue_ptr:
            lvalue_ptr.ir_value = value
        else:
            self.builder.store(value, lvalue_ptr.ir_value)

        if new_type is not None:
            lvalue_ptr.type = new_type

    def str_constant(self, str_value: str) -> TypedValue:
        if str_value in self.string_constants:
            return self.string_constants[str_value]
        str_bytes = bytearray(str_value + "\0", "utf-8")
        variable_name = ".str" + str(len(self.string_constants))
        arr_type = ir.ArrayType(int8, len(str_bytes))
        ir_value = ir.GlobalVariable(self.module, arr_type, variable_name)
        ir_value.initializer = ir.Constant(ir.ArrayType(int8, len(str_bytes)), str_bytes)
        typed_value = TypedValue(ir_value, typ=arr_type, constant=True)
        self.string_constants[str_value] = typed_value
        return typed_value

    def typed_bool(self, value: TypedValue, negation: bool = False) -> TypedValue:
        # 根据值是否为 0 转为 TypedValue(int1)
        if negation:
            op = '=='
        else:
            op = '!='
        rvalue = self.load_lvalue(value)
        new_v1, new_v2, new_type = self.bit_extend(rvalue, int32_zero)
        if new_type == double:
            result: ir.Instruction = self.builder.fcmp_ordered(op, new_v1, new_v2)
        else:
            result: ir.Instruction = self.builder.icmp_signed(op, new_v1, new_v2)
        return TypedValue(result, int1, constant=False, name=None, lvalue_ptr=False)

    def ir_bool(self, value: TypedValue, negation: bool = False) -> int1:
        # 根据值是否为 0 转为 int1
        return self.typed_bool(value, negation=negation).ir_value

    def bit_extend(self, value1, value2, ctx=None) -> Tuple[Any, Any, ir.Type]:
        """
        将 value1 和 value2 拓展成相同的位数, 都为 ir.Value.
        目前为方便起见，若有浮点数，都拓展为 Double

        :param value1:
        :type value1:
        :param value2:
        :type value2:
        :param ctx: 用于报错
        :return:
        :rtype:
        """
        if isinstance(value1.type, ir.types.DoubleType) and not isinstance(value2.type, ir.types.DoubleType):
            new_v1 = value1
            new_v2 = self.builder.sitofp(value2, double)
            return new_v1, new_v2, double
        elif not isinstance(value1.type, ir.types.DoubleType) and isinstance(value2.type, ir.types.DoubleType):
            new_v1 = self.builder.sitofp(value1, double)
            new_v2 = value2
            return new_v1, new_v2, double
        elif isinstance(value1.type, ir.types.DoubleType) and isinstance(value2.type, ir.types.DoubleType):
            return value1, value2, double
        elif isinstance(value1.type, ir.types.IntType) and isinstance(value2.type, ir.types.IntType):
            new_type = value1.type if value1.type.width >= value2.type.width else value2.type
            new_v1 = self.builder.sext(value1, new_type)
            new_v2 = self.builder.sext(value2, new_type)
            return new_v1, new_v2, new_type

        raise SemanticError('Bit extend error.', ctx)

    def decay(self, value: TypedValue, ctx=None) -> TypedValue:
        if not isinstance(value.type, ir.ArrayType):
            if value.type.is_pointer:
                return value
            raise SemanticError("Cannot decay a non-array type.", ctx)
        pointer = self.builder.gep(value.ir_value, [int32_zero, int32_zero], inbounds=False)
        return TypedValue(ir_value=pointer, typ=value.type.element.as_pointer(), constant=False, lvalue_ptr=False)

    def create_initializer_list(self, target_type: ir.Type,
                                initializer_list: Union[TypedValue, List[Union[TypedValue, list]]],
                                need_constant: bool, ctx=None) -> ir.Value:
        """
        根据所需要的类型，创建对应的 literal_array, literal_struct, ir.Constant, ir.Value.
        """
        if isinstance(target_type, ir.ArrayType):
            # 创建 array
            element_type = target_type.element
            array_elements = []
            if len(initializer_list) > target_type.count:
                raise SemanticError("Invalid initializer: element count not match", ctx)
            for initializer in initializer_list:
                array_elements.append(self.create_initializer_list(element_type, initializer, need_constant, ctx))
            if len(initializer_list) < target_type.count:
                # 用 0 补足剩下的空间
                zero_initializer = ir.Constant(element_type, None)
                array_elements += ([zero_initializer] * (target_type.count - len(initializer_list)))
            return ir.Constant.literal_array(array_elements)
        elif isinstance(target_type, ElementNamedLiteralStructType):
            # 创建 struct
            if len(initializer_list) > len(target_type.elements):
                raise SemanticError("Invalid initializer: element count not match", ctx)
            struct_elements = []
            for i in range(len(initializer_list)):
                element_type = target_type.elements[i]
                initializer = initializer_list[i]
                struct_elements.append(self.create_initializer_list(element_type, initializer, need_constant, ctx))
            for i in range(len(initializer_list), len(target_type.elements)):
                element_type = target_type.elements[i]
                struct_elements.append(ir.Constant(element_type, None))
            return ir.Constant.literal_struct(struct_elements)
        else:
            # 非聚合类型
            if not isinstance(initializer_list, TypedValue):
                raise SemanticError("Cannot initialize a non aggregate element with an aggregate literal.", ctx)
            if initializer_list.constant:
                if need_constant:
                    assert isinstance(initializer_list.ir_value, ir.Constant)
                    if initializer_list.type != target_type:
                        return ir.Constant(target_type, initializer_list.ir_value.constant)
                    else:
                        return initializer_list.ir_value
                else:
                    return self.load_lvalue(initializer_list)
            else:
                if need_constant:
                    raise SemanticError("Constant value required for constant initializer.", ctx)
                else:
                    return self.convert_type(initializer_list, target_type, ctx=ctx)

    def convert_type(self, value: TypedValue, type: ir.Type, ctx=None) -> ir.Value:
        """
        将 ir_value 转为 type 类型.

        :param value:
        :type value:
        :param type:
        :type type:
        :param ctx:
        :type ctx:
        :return: 转换后的 ir.Value
        :rtype:
        """
        ir_value = self.load_lvalue(value)
        if value.type == type:
            return ir_value
        elif value.type in int_types:
            if type in int_types:
                if type.width <= value.type.width:
                    return self.builder.trunc(ir_value, type)
                else:
                    return self.builder.sext(ir_value, type)
            elif type == double:
                return self.builder.sitofp(ir_value, type)
            elif type.is_pointer:
                return self.builder.inttoptr(ir_value, type)
            else:
                raise SemanticError('Not supported type conversion.', ctx)
        elif value.type == double:
            if type in int_types:
                return self.builder.fptosi(ir_value, type)
            elif type == double:
                return value.ir_value
            elif type.is_pointer:
                raise SemanticError('Illegal type conversion.', ctx)
            else:
                raise SemanticError('Not supported type conversion.', ctx)
        elif value.type.is_pointer:
            if type in int_types:
                return self.builder.ptrtoint(ir_value, type)
            elif type == double:
                raise SemanticError('Illegal type conversion.', ctx)
            elif type.is_pointer:
                return self.builder.bitcast(ir_value, type)
            else:
                raise SemanticError('Not supported type conversion.', ctx)
        elif isinstance(value.type, ir.ArrayType):
            if type.is_pointer:
                type: ir.PointerType
                value_type: ir.ArrayType = value.type
                if type.pointee != value_type.element:
                    raise SemanticError(f'Invalid conversion from {value_type} to {type}.', ctx)
                return self.builder.gep(ir_value, [int32_zero, int32_zero], inbounds=False)
            else:
                raise SemanticError('Not supported type conversion.', ctx)
        else:
            raise SemanticError('Not supported type conversion.', ctx)

    @staticmethod
    def unescape(s: str) -> str:
        return s.encode('utf-8').decode('unicode_escape')

    def visitConstant(self, ctx: CParser.ConstantContext) -> TypedValue:
        text = ctx.getText()
        if ctx.IntegerConstant():
            if re.fullmatch('0[0-7]*', text):
                value = int(text, 8)
            else:
                value = int(text, 0)
            return const_value(ir.Constant(int32, value))
        if ctx.FloatingConstant():
            return const_value(ir.Constant(double, float(text)))
        if ctx.CharacterConstant():
            c = self.unescape(text[1:-1])
            if len(c) != 1:
                raise SemanticError('multi-character character constant is not supported', ctx)
            return const_value(ir.Constant(int8, ord(c)))
        raise SemanticError('impossible')

    def visitPrimaryExpression(self, ctx: CParser.PrimaryExpressionContext) -> TypedValue:
        """
        Primary Expression
        """
        if ctx.Identifier():
            identifier = ctx.getText()
            item = self.symbol_table.get_item(identifier)
            if item is None:
                raise SemanticError("Undefined identifier: " + identifier, ctx)
            return item
        if ctx.StringLiteral():
            str_result = ""
            for childCtx in ctx.StringLiteral():
                str_result += self.unescape(childCtx.getText()[1:-1])
            return self.str_constant(str_result)
        if ctx.constant():
            return self.visit(ctx.constant())
        if ctx.expression():
            return self.visit(ctx.expression())
        raise SemanticError('impossible')

    def visitPrimitiveType(self, ctx: CParser.PrimitiveTypeContext) -> ir.Type:
        """
        Primitive type
        """
        return self.symbol_table.get_item(ctx.getText())

    def visitPostfixExpression_2(self, ctx: CParser.PostfixExpression_2Context) -> TypedValue:
        # postfixExpression '[' expression ']'
        v1: TypedValue = self.visit(ctx.getChild(0))
        is_pointer = isinstance(v1.type, ir.types.PointerType)
        is_array = isinstance(v1.type, ir.types.ArrayType)
        if not is_array and not is_pointer:
            raise SemanticError("Postfix Expression is not a array or pointer.", ctx)
        v2: TypedValue = self.visit(ctx.getChild(2))
        # 数组地址
        base_ptr = self.load_lvalue(v1)
        # 偏移
        offset = self.load_lvalue(v2)
        result: ir.Instruction
        if is_pointer:
            result = self.builder.gep(base_ptr, [offset], inbounds=False)
            result_type = v1.type.pointee
        elif is_array:
            result = self.builder.gep(base_ptr, [int32_zero, offset], inbounds=True)
            result_type = v1.type.element
        else:
            raise SemanticError("Postfix expression(#2) is not a array or pointer.", ctx)
        return TypedValue(result, result_type, constant=False, name=None, lvalue_ptr=True)

    def visitPostfixExpression_3(self, ctx: CParser.PostfixExpression_3Context) -> TypedValue:
        # postfixExpression '(' argumentExpressionList? ')'
        # 函数调用
        func_value: TypedValue = self.visit(ctx.postfixExpression())
        if func_value is None:
            raise SemanticError('Function not declared', ctx)
        func_pointer = self.load_lvalue(func_value)
        if not func_pointer.type.is_pointer:
            raise SemanticError('Invalid function.', ctx)
        func_type: ir.FunctionType = func_pointer.type.pointee
        if ctx.argumentExpressionList():
            argument_expressions = self.visit(ctx.argumentExpressionList())
        else:
            argument_expressions = []
        func_args = []
        func_arg_count = len(func_type.args)
        if func_type.var_arg:
            if len(argument_expressions) < func_arg_count:
                raise SemanticError("Too few arguments", ctx)
        else:
            if len(argument_expressions) != func_arg_count:
                raise SemanticError("Incorrect number of arguments", ctx)
        for i in range(len(argument_expressions)):
            if i < func_arg_count:
                func_args.append(self.convert_type(argument_expressions[i], func_type.args[i], ctx=ctx))
            else:
                if isinstance(argument_expressions[i].type, ir.ArrayType):
                    func_args.append(self.convert_type(argument_expressions[i],
                                                       argument_expressions[i].type.element.as_pointer(),
                                                       ctx=ctx))
                else:
                    func_args.append(self.load_lvalue(argument_expressions[i]))
        ret_value = self.builder.call(func_pointer, func_args)
        return TypedValue(ir_value=ret_value, typ=ret_value.type, constant=False, name=None, lvalue_ptr=False)

    def visitPostfixExpression_4(self, ctx: CParser.PostfixExpression_4Context) -> TypedValue:
        # postfixExpression '.' Identifier
        v1: TypedValue = self.visit(ctx.getChild(0))
        if not v1.lvalue_ptr:
            raise SemanticError('Postfix Expression(#2) needs lvalue.', ctx)
        rvalue: ir.NamedValue = self.load_lvalue(v1)
        if not isinstance(rvalue.type, ElementNamedLiteralStructType):
            raise SemanticError("Postfix expression(#4) is not literal struct.", ctx)
        member_name = ctx.Identifier().getText()
        ls_type: ElementNamedLiteralStructType = rvalue.type
        try:
            member_index, member_type = ls_type.get_element_by_name(member_name)
            member_index = [ir.Constant(int32, idx) for idx in member_index]
        except ValueError:
            raise SemanticError("Postfix expression(#4) has not such attribute.", ctx)
        # 获得地址
        result = self.builder.gep(v1.ir_value, [int32_zero] + member_index, inbounds=False)
        return TypedValue(result, member_type, constant=False, name=None, lvalue_ptr=True)

    def visitPostfixExpression_5(self, ctx: CParser.PostfixExpression_5Context) -> TypedValue:
        # postfixExpression '->' Identifier
        v1: TypedValue = self.visit(ctx.getChild(0))
        rvalue: ir.NamedValue = self.load_lvalue(v1)  # 这里事实上获得一个指针
        if not isinstance(rvalue.type, ir.types.PointerType):
            raise SemanticError("Postfix expression(#5) is not pointer.", ctx)
        # 转到结构体类型
        pointee_type = v1.type.pointee
        member_name = ctx.Identifier().getText()
        ls_type: ElementNamedLiteralStructType = pointee_type
        try:
            member_index, member_type = ls_type.get_element_by_name(member_name)
            member_index = [ir.Constant(int32, idx) for idx in member_index]
        except ValueError:
            raise SemanticError("Postfix expression(#5) has not such attribute.", ctx)
        # 获得地址
        result = self.builder.gep(rvalue, [int32_zero] + member_index, inbounds=False)
        return TypedValue(result, member_type, constant=False, name=None, lvalue_ptr=True)

    def visitPostfixExpression_6(self, ctx: CParser.PostfixExpression_6Context) -> TypedValue:
        # postfixExpression '++'
        v1: TypedValue = self.visit(ctx.getChild(0))
        rvalue = self.load_lvalue(v1)
        if v1.type.is_pointer:
            result = self.builder.gep(rvalue, [ir.Constant(int64, 1)], inbounds=False)
        else:
            result = self.builder.add(rvalue, ir.Constant(v1.type, 1))
        self.store_lvalue(result, v1)
        return TypedValue(rvalue, v1.type, constant=False, name=None, lvalue_ptr=False)

    def visitPostfixExpression_7(self, ctx: CParser.PostfixExpression_7Context) -> TypedValue:
        # postfixExpression '--'
        v1: TypedValue = self.visit(ctx.getChild(0))
        rvalue = self.load_lvalue(v1)
        if v1.type.is_pointer:
            result = self.builder.gep(rvalue, [ir.Constant(int64, -1)], inbounds=False)
        else:
            result = self.builder.sub(rvalue, ir.Constant(v1.type, 1))
        self.store_lvalue(result, v1)
        return TypedValue(rvalue, v1.type, constant=False, name=None, lvalue_ptr=False)

    def visitArgumentExpressionList(self, ctx: CParser.ArgumentExpressionListContext) -> List[TypedValue]:
        """
        语法规则:
        argumentExpressionList
            :   assignmentExpression
            |   argumentExpressionList ',' assignmentExpression
            ;
        @return: expression 的计算结果列表
        """
        arg_expr_list_ctx = ctx.argumentExpressionList()
        if arg_expr_list_ctx is not None:
            arg_expr_list = self.visit(ctx.argumentExpressionList())
        else:
            arg_expr_list = []
        arg_expr_list.append(self.visit(ctx.assignmentExpression()))
        return arg_expr_list

    def visitUnaryExpression_1(self, ctx: CParser.UnaryExpression_1Context) -> TypedValue:
        # postfixExpression
        return self.visit(ctx.getChild(0))

    def visitUnaryExpression_2(self, ctx: CParser.UnaryExpression_2Context) -> TypedValue:
        # '++' unaryExpression
        v1: TypedValue = self.visit(ctx.unaryExpression())
        rvalue = self.load_lvalue(v1)
        if v1.type.is_pointer:
            result = self.builder.gep(rvalue, [ir.Constant(int64, 1)], inbounds=False)
        else:
            result = self.builder.add(rvalue, ir.Constant(v1.type, 1))
        self.store_lvalue(result, v1)
        return v1

    def visitUnaryExpression_3(self, ctx: CParser.UnaryExpression_3Context) -> TypedValue:
        # '--' unaryExpression
        v1 = self.visit(ctx.unaryExpression())
        rvalue = self.load_lvalue(v1)
        if v1.type.is_pointer:
            result = self.builder.gep(rvalue, [ir.Constant(int64, -1)], inbounds=False)
        else:
            result = self.builder.sub(rvalue, ir.Constant(v1.type, 1))
        self.store_lvalue(result, v1)
        return v1

    def visitUnaryExpression_4(self, ctx: CParser.UnaryExpression_4Context) -> TypedValue:
        # unaryOperator castExpression
        op: str = ctx.unaryOperator().getText()
        v2: TypedValue = self.visit(ctx.castExpression())
        # '&' | '*' | '+' | '-' | '~' | '!'
        if op == '&':
            # 必须对左值取地址
            if not v2.lvalue_ptr:
                raise SemanticError('Operator & needs a lvalue.', ctx)
            return TypedValue(v2.ir_value, v2.type.as_pointer(), constant=False, name=None, lvalue_ptr=False)
        elif op == '*':
            pointer_type: ir.PointerType = v2.type
            if not pointer_type.is_pointer:
                raise SemanticError('Operator * needs a pointer.', ctx)
            if v2.lvalue_ptr:
                # v2 是存放指针类型的左值时
                ptr_value = self.load_lvalue(v2)
                return TypedValue(ptr_value, pointer_type.pointee, constant=False, name=None, lvalue_ptr=True)
            else:
                return TypedValue(v2.ir_value, pointer_type.pointee, constant=False, name=None, lvalue_ptr=True)
        elif op == '+':
            return v2
        elif op == '-':
            if self.is_ir_constant(v2):
                return const_value(ir.Constant(v2.type, -v2.ir_value.constant))
            rvalue = self.load_lvalue(v2)
            result: ir.Instruction = self.builder.neg(rvalue)
            return TypedValue(result, v2.type, constant=False, name=None, lvalue_ptr=False)
        elif op == '~':
            rvalue = self.load_lvalue(v2)
            result: ir.Instruction = self.builder.not_(rvalue)
            return TypedValue(result, v2.type, constant=False, name=None, lvalue_ptr=False)
        elif op == '!':
            return self.typed_bool(v2, negation=True)

    def visitUnaryExpression_5(self, ctx: CParser.UnaryExpression_5Context) -> TypedValue:
        # 'sizeof' unaryExpression
        # TODO 在不实际计算 expression 值的情况下完成
        expression = self.visit(ctx.unaryExpression())
        if isinstance(expression, ir.Type):
            size = expression.get_abi_size(self.target_data)
        else:
            expression: TypedValue
            size = expression.type.get_abi_size(self.target_data)
        return const_value(ir.Constant(size_t, size))

    def visitUnaryExpression_6(self, ctx: CParser.UnaryExpression_6Context) -> TypedValue:
        # 'sizeof' '(' typeName ')'
        typ: ir.Type = self.visit(ctx.typeName())
        return const_value(ir.Constant(size_t, typ.get_abi_size(self.target_data)))

    def visitCastExpression_1(self, ctx: CParser.CastExpression_1Context) -> TypedValue:
        # '(' typeName ')' castExpression
        base_type = self.visit(ctx.typeName())
        value = self.convert_type(self.visit(ctx.castExpression()), base_type, ctx=ctx)
        return TypedValue(ir_value=value, typ=base_type, constant=False, name=None, lvalue_ptr=False)

    def visitCastExpression_2(self, ctx: CParser.CastExpression_2Context) -> TypedValue:
        # unaryExpression

        return self.visitChildren(ctx)

    def visitMultiplicativeExpression_2(self, ctx: CParser.MultiplicativeExpression_2Context):
        # multiplicativeExpression '*' castExpression
        v1: TypedValue = self.visit(ctx.multiplicativeExpression())
        v2: TypedValue = self.visit(ctx.castExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            result = self.builder.fmul(new_rvalue1, new_rvalue2)
        else:
            result = self.builder.mul(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitMultiplicativeExpression_3(self, ctx: CParser.MultiplicativeExpression_3Context):
        # multiplicativeExpression '/' castExpression
        v1: TypedValue = self.visit(ctx.multiplicativeExpression())
        v2: TypedValue = self.visit(ctx.castExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            result = self.builder.fdiv(new_rvalue1, new_rvalue2)
        else:
            result = self.builder.sdiv(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitMultiplicativeExpression_4(self, ctx: CParser.MultiplicativeExpression_4Context):
        # multiplicativeExpression '%' castExpression
        v1: TypedValue = self.visit(ctx.multiplicativeExpression())
        v2: TypedValue = self.visit(ctx.castExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            result = self.builder.frem(new_rvalue1, new_rvalue2)
        else:
            result = self.builder.srem(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitAdditiveExpression_2(self, ctx: CParser.AdditiveExpression_2Context):
        # additiveExpression '+' multiplicativeExpression
        v1: TypedValue = self.visit(ctx.additiveExpression())
        v2: TypedValue = self.visit(ctx.multiplicativeExpression())
        if isinstance(v1.type, ir.ArrayType):
            v1 = self.decay(v1)
        if isinstance(v2.type, ir.ArrayType):
            v2 = self.decay(v2)
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        if v2.type.is_pointer and v1.type in int_types:
            result = self.builder.gep(rvalue2, [rvalue1], inbounds=False)
            new_type = v2.type
        elif v1.type.is_pointer and v2.type in int_types:
            result = self.builder.gep(rvalue1, [rvalue2], inbounds=False)
            new_type = v1.type
        else:
            new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
            if new_type == double:
                result = self.builder.fadd(new_rvalue1, new_rvalue2)
            else:
                result = self.builder.add(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitAdditiveExpression_3(self, ctx: CParser.AdditiveExpression_3Context):
        # additiveExpression '-' multiplicativeExpression
        v1: TypedValue = self.visit(ctx.additiveExpression())
        v2: TypedValue = self.visit(ctx.multiplicativeExpression())
        if isinstance(v1.type, ir.ArrayType):
            v1 = self.decay(v1)
        if isinstance(v2.type, ir.ArrayType):
            v2 = self.decay(v2)
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        if v1.type.is_pointer and v2.type in int_types:
            result = self.builder.gep(rvalue1, [self.builder.neg(rvalue2)], inbounds=False)
            new_type = v1.type
        else:
            new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
            builder = self.builder
            if new_type == double:
                result = builder.fsub(new_rvalue1, new_rvalue2)
            else:
                result = builder.sub(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitShiftExpression_2(self, ctx: CParser.ShiftExpression_2Context):
        # shiftExpression '<<' additiveExpression
        v1: TypedValue = self.visit(ctx.shiftExpression())
        v2: TypedValue = self.visit(ctx.additiveExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            raise SemanticError('Bitwise shifting is only available to integer.', ctx)
        else:
            result = self.builder.shl(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitShiftExpression_3(self, ctx: CParser.ShiftExpression_3Context):
        # shiftExpression '>>' additiveExpression
        v1: TypedValue = self.visit(ctx.shiftExpression())
        v2: TypedValue = self.visit(ctx.additiveExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            raise SemanticError('Bitwise shifting is only available to integer.', ctx)
        else:
            result = self.builder.ashr(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def _relational_expression(self, op: str, ctx: ParserRuleContext):
        v1: TypedValue = self.visit(ctx.getChild(0))
        v2: TypedValue = self.visit(ctx.getChild(2))
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            result = self.builder.fcmp_ordered(op, new_rvalue1, new_rvalue2)
        else:
            result = self.builder.add(new_rvalue1, new_rvalue2)
            result = self.builder.icmp_signed(op, new_rvalue1, new_rvalue2)
        return TypedValue(result, int1, constant=False, name=None, lvalue_ptr=False)

    def visitRelationalExpression_2(self, ctx: CParser.RelationalExpression_2Context):
        # relationalExpression '<' shiftExpression
        return self._relational_expression('<', ctx)

    def visitRelationalExpression_3(self, ctx: CParser.RelationalExpression_3Context):
        # relationalExpression '>' shiftExpression
        return self._relational_expression('>', ctx)

    def visitRelationalExpression_4(self, ctx: CParser.RelationalExpression_4Context):
        # relationalExpression '<=' shiftExpression
        return self._relational_expression('<=', ctx)

    def visitRelationalExpression_5(self, ctx: CParser.RelationalExpression_5Context):
        # relationalExpression '>=' shiftExpression
        return self._relational_expression('>=', ctx)

    def visitEqualityExpression_2(self, ctx: CParser.EqualityExpression_2Context):
        # equalityExpression '==' relationalExpression
        return self._relational_expression('==', ctx)

    def visitEqualityExpression_3(self, ctx: CParser.EqualityExpression_3Context):
        # equalityExpression '!=' relationalExpression
        return self._relational_expression('!=', ctx)

    def visitAndExpression_2(self, ctx: CParser.AndExpression_2Context):
        # andExpression '&' equalityExpression
        v1: TypedValue = self.visit(ctx.andExpression())
        v2: TypedValue = self.visit(ctx.equalityExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            raise SemanticError('Bitwise operation is only available to integer.', ctx)
        else:
            result = self.builder.and_(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitExclusiveOrExpression_2(self, ctx: CParser.ExclusiveOrExpression_2Context):
        # exclusiveOrExpression '^' andExpression
        v1: TypedValue = self.visit(ctx.exclusiveOrExpression())
        v2: TypedValue = self.visit(ctx.andExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            raise SemanticError('Bitwise operation is only available to integer.', ctx)
        else:
            result = self.builder.xor(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitInclusiveOrExpression_2(self, ctx: CParser.InclusiveOrExpression_2Context):
        # inclusiveOrExpression '|' exclusiveOrExpression
        v1: TypedValue = self.visit(ctx.inclusiveOrExpression())
        v2: TypedValue = self.visit(ctx.exclusiveOrExpression())
        rvalue1 = self.load_lvalue(v1)
        rvalue2 = self.load_lvalue(v2)
        new_rvalue1, new_rvalue2, new_type = self.bit_extend(rvalue1, rvalue2, ctx)
        if new_type == double:
            raise SemanticError('Bitwise operation is only available to integer.', ctx)
        else:
            result = self.builder.or_(new_rvalue1, new_rvalue2)
        return TypedValue(result, new_type, constant=False, name=None, lvalue_ptr=False)

    def visitLogicalAndExpression(self, ctx: CParser.LogicalAndExpressionContext):
        """
        logicalAndExpression
            : inclusiveOrExpression
            | logicalAndExpression '&&' inclusiveOrExpression
            ;
        """
        return self._visitLogicalExpression(ctx)

    def visitLogicalOrExpression(self, ctx: CParser.LogicalOrExpressionContext):
        """
        logicalOrExpression
            : logicalAndExpression
            | logicalOrExpression '||' logicalAndExpression
            ;
        """
        return self._visitLogicalExpression(ctx)

    def _visitLogicalExpression(self, ctx: ParserRuleContext):
        if ctx.getChildCount() == 1:
            return self.visitChildren(ctx)

        block_rhs = self.builder.append_basic_block(name='logical.rhs')
        block_end = self.builder.append_basic_block(name='logical.end')

        value_entry = self.ir_bool(self.visit(ctx.getChild(0)))
        op = ctx.getChild(1).getText()
        if op == '&&':
            self.builder.cbranch(value_entry, block_rhs, block_end)
        elif op == '||':
            self.builder.cbranch(value_entry, block_end, block_rhs)
        block_entry = self.builder.basic_block

        self.builder = ir.IRBuilder(block_rhs)
        value_rhs = self.ir_bool(self.visit(ctx.getChild(2)))
        self.builder.branch(block_end)
        block_rhs = self.builder.basic_block

        self.builder = ir.IRBuilder(block_end)
        result = self.builder.phi(int1)
        result.add_incoming(value_entry, block_entry)
        result.add_incoming(value_rhs, block_rhs)
        return TypedValue(result, int1, constant=False, name=None, lvalue_ptr=False)

    def visitConditionalExpression(self, ctx: CParser.ConditionalExpressionContext):
        # logicalOrExpression ('?' expression ':' conditionalExpression)?
        first = self.visit(ctx.logicalOrExpression())
        if ctx.getChildCount() == 1:
            return first

        block_true = self.builder.append_basic_block(name='ternary.true')
        block_false = self.builder.append_basic_block(name='ternary.false')
        block_end = self.builder.append_basic_block(name='ternary.end')

        cond = self.ir_bool(first)
        self.builder.cbranch(cond, block_true, block_false)

        self.builder = ir.IRBuilder(block_true)
        typed_value_true = self.visit(ctx.expression())
        if isinstance(typed_value_true.type, ir.ArrayType):
            typed_value_true = self.decay(typed_value_true)
        value_true = self.load_lvalue(typed_value_true)
        self.builder.branch(block_end)
        block_true = self.builder.basic_block

        self.builder = ir.IRBuilder(block_false)
        typed_value_false = self.visit(ctx.conditionalExpression())
        if isinstance(typed_value_false.type, ir.ArrayType):
            typed_value_false = self.decay(typed_value_false)
        value_false = self.load_lvalue(typed_value_false)
        self.builder.branch(block_end)
        block_false = self.builder.basic_block

        if typed_value_true.type != typed_value_false.type:
            raise SemanticError("Type not identical.", ctx)

        self.builder = ir.IRBuilder(block_end)
        result = self.builder.phi(typed_value_true.type)
        result.add_incoming(value_true, block_true)
        result.add_incoming(value_false, block_false)
        return TypedValue(result, typed_value_true.type, constant=False, name=None, lvalue_ptr=False)

    def visitAssignmentExpression_2(self, ctx: CParser.AssignmentExpression_2Context):
        # unaryExpression assignmentOperator assignmentExpression
        v1: TypedValue = self.visit(ctx.unaryExpression())
        op: str = ctx.assignmentOperator().getText()
        v3: TypedValue = self.visit(ctx.assignmentExpression())
        # lhs 必须为左值
        if not v1.lvalue_ptr:
            raise SemanticError('Assignment needs a lvalue at left.', ctx)
        rvalue1 = self.load_lvalue(v1)
        rvalue3 = self.load_lvalue(v3)
        builder = self.builder
        # '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
        if op == '=':
            result = self.convert_type(v3, v1.type, ctx=ctx)
        elif op == '*=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type != double:
                result = self.builder.mul(rvalue1, tmp)
            else:
                result = self.builder.fmul(rvalue1, tmp)
        elif op == '/=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type != double:
                result = self.builder.sdiv(rvalue1, tmp)
            else:
                result = self.builder.fdiv(rvalue1, tmp)
        elif op == '%=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type != double:
                result = self.builder.srem(rvalue1, tmp)
            else:
                result = self.builder.frem(rvalue1, tmp)
        elif op == '+=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type != double:
                result = self.builder.add(rvalue1, tmp)
            else:
                result = self.builder.fadd(rvalue1, tmp)
        elif op == '-=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type != double:
                result = self.builder.sub(rvalue1, tmp)
            else:
                result = self.builder.fsub(rvalue1, tmp)
        elif op == '<<=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type == double:
                raise SemanticError('Floating point number cannot shift bits.', ctx)
            result = self.builder.shl(rvalue1, tmp)
        elif op == '>>=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type == double:
                raise SemanticError('Floating point number cannot shift bits.', ctx)
            result = self.builder.ashr(rvalue1, tmp)
        elif op == '&=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type == double:
                raise SemanticError('Floating point number cannot shift bits.', ctx)
            result = self.builder.and_(rvalue1, tmp)
        elif op == '^=':
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type == double:
                raise SemanticError('Floating point number cannot shift bits.', ctx)
            result = self.builder.xor(rvalue1, tmp)
        else:  # op == '|='
            tmp = self.convert_type(v3, v1.type, ctx=ctx)
            if v1.type == double:
                raise SemanticError('Floating point number cannot shift bits.', ctx)
            result = self.builder.or_(rvalue1, tmp)
        self.store_lvalue(result, v1)
        return

    def save(self, filename: str) -> None:
        """
        保存分析结果到文件
        :param filename: 文件名含后缀
        :return: None
        """
        with open(filename, 'w') as f:
            f.write(repr(self.module))


def compile(input_file: str, output_file: str, target_arch: str, include_dirs: List[str],
             macros: Dict[str, str]):
    """
    将C代码文件转成IR代码文件
    :param input_file: C代码文件
    :param output_file: IR代码文件
    :param macro_list: 宏
    :param include_dirs: 头文件目录
    :param target_arch: 目标架构
    :return: 生成是否成功
    """
    def as_pointer(self: ir.Type, addrspace=0):
        if isinstance(self, ir.VoidType):
            return int8.as_pointer(addrspace)
        return ir.PointerType(self, addrspace)

    ir.Type.as_pointer = as_pointer

    includes = [os.getcwd(), *include_dirs]
    macros = macros.copy()

    try:
        processed_text = preprocess(input_file, includes, macros)
    except MacroError as e:
        print(str(e))
        return False

    processed_lines = processed_text.splitlines()
    lexer = CLexer(InputStream(processed_text))
    stream = CommonTokenStream(lexer)
    parser = CParser(stream)
    parser.removeErrorListeners()
    errorListener = ParserErrorListener()
    parser.addErrorListener(errorListener)

    try:
        tree = parser.compilationUnit()
        visitor = Visitor(target_arch)
        visitor.visit(tree)
    except CompilationError as e:
        print(str(e))
        i = e.line - 1
        if i in range(len(processed_lines)):
            print(processed_lines[i])
        return False

    visitor.save(output_file)
    return True
