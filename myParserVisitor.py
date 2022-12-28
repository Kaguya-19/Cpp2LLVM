# Generated from myParser.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .myParser import myParser
else:
    from myParser import myParser

# This class defines a complete generic visitor for a parse tree produced by myParser.

class myParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by myParser#translation_unit.
    def visitTranslation_unit(self, ctx:myParser.Translation_unitContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#primary_expression.
    def visitPrimary_expression(self, ctx:myParser.Primary_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#postfix_expression.
    def visitPostfix_expression(self, ctx:myParser.Postfix_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#argument_expression_list.
    def visitArgument_expression_list(self, ctx:myParser.Argument_expression_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#unary_expression.
    def visitUnary_expression(self, ctx:myParser.Unary_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#cast_expression.
    def visitCast_expression(self, ctx:myParser.Cast_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#multiplicative_expression.
    def visitMultiplicative_expression(self, ctx:myParser.Multiplicative_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#additive_expression.
    def visitAdditive_expression(self, ctx:myParser.Additive_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#relational_expression.
    def visitRelational_expression(self, ctx:myParser.Relational_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#equality_expression.
    def visitEquality_expression(self, ctx:myParser.Equality_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#logical_and_expression.
    def visitLogical_and_expression(self, ctx:myParser.Logical_and_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#logical_or_expression.
    def visitLogical_or_expression(self, ctx:myParser.Logical_or_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#assignment_expression.
    def visitAssignment_expression(self, ctx:myParser.Assignment_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#expression.
    def visitExpression(self, ctx:myParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#constant_expression.
    def visitConstant_expression(self, ctx:myParser.Constant_expressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declaration.
    def visitDeclaration(self, ctx:myParser.DeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declaration_specifiers.
    def visitDeclaration_specifiers(self, ctx:myParser.Declaration_specifiersContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#init_declarator_list.
    def visitInit_declarator_list(self, ctx:myParser.Init_declarator_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#init_declarator.
    def visitInit_declarator(self, ctx:myParser.Init_declaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#type_specifier.
    def visitType_specifier(self, ctx:myParser.Type_specifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#specifier_qualifier_list.
    def visitSpecifier_qualifier_list(self, ctx:myParser.Specifier_qualifier_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#direct_declarator.
    def visitDirect_declarator(self, ctx:myParser.Direct_declaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parameter_list.
    def visitParameter_list(self, ctx:myParser.Parameter_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parameter_declaration.
    def visitParameter_declaration(self, ctx:myParser.Parameter_declarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#identifier_list.
    def visitIdentifier_list(self, ctx:myParser.Identifier_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#type_name.
    def visitType_name(self, ctx:myParser.Type_nameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#direct_abstract_declarator.
    def visitDirect_abstract_declarator(self, ctx:myParser.Direct_abstract_declaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initializer.
    def visitInitializer(self, ctx:myParser.InitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initializer_list.
    def visitInitializer_list(self, ctx:myParser.Initializer_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#statement.
    def visitStatement(self, ctx:myParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#compound_statement.
    def visitCompound_statement(self, ctx:myParser.Compound_statementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declaration_list.
    def visitDeclaration_list(self, ctx:myParser.Declaration_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#statement_list.
    def visitStatement_list(self, ctx:myParser.Statement_listContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#expression_statement.
    def visitExpression_statement(self, ctx:myParser.Expression_statementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#selection_statement.
    def visitSelection_statement(self, ctx:myParser.Selection_statementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#iteration_statement.
    def visitIteration_statement(self, ctx:myParser.Iteration_statementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#jump_statement.
    def visitJump_statement(self, ctx:myParser.Jump_statementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#external_declaration.
    def visitExternal_declaration(self, ctx:myParser.External_declarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#function_definition.
    def visitFunction_definition(self, ctx:myParser.Function_definitionContext):
        return self.visitChildren(ctx)



del myParser