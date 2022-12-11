# Generated from CPP14Parser.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .CPP14Parser import CPP14Parser
else:
    from CPP14Parser import CPP14Parser

# This class defines a complete generic visitor for a parse tree produced by CPP14Parser.

class CPP14ParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by CPP14Parser#translationUnit.
    def visitTranslationUnit(self, ctx:CPP14Parser.TranslationUnitContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#primaryExpression.
    def visitPrimaryExpression(self, ctx:CPP14Parser.PrimaryExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#idExpression.
    def visitIdExpression(self, ctx:CPP14Parser.IdExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#postfixExpression.
    def visitPostfixExpression(self, ctx:CPP14Parser.PostfixExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#unaryExpression.
    def visitUnaryExpression(self, ctx:CPP14Parser.UnaryExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#unaryOperator.
    def visitUnaryOperator(self, ctx:CPP14Parser.UnaryOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#newExpression.
    def visitNewExpression(self, ctx:CPP14Parser.NewExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#newTypeId.
    def visitNewTypeId(self, ctx:CPP14Parser.NewTypeIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#newDeclarator.
    def visitNewDeclarator(self, ctx:CPP14Parser.NewDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#noPointerNewDeclarator.
    def visitNoPointerNewDeclarator(self, ctx:CPP14Parser.NoPointerNewDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#deleteExpression.
    def visitDeleteExpression(self, ctx:CPP14Parser.DeleteExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#castExpression.
    def visitCastExpression(self, ctx:CPP14Parser.CastExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#pointerMemberExpression.
    def visitPointerMemberExpression(self, ctx:CPP14Parser.PointerMemberExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#multiplicativeExpression.
    def visitMultiplicativeExpression(self, ctx:CPP14Parser.MultiplicativeExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#additiveExpression.
    def visitAdditiveExpression(self, ctx:CPP14Parser.AdditiveExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#shiftExpression.
    def visitShiftExpression(self, ctx:CPP14Parser.ShiftExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#shiftOperator.
    def visitShiftOperator(self, ctx:CPP14Parser.ShiftOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#relationalExpression.
    def visitRelationalExpression(self, ctx:CPP14Parser.RelationalExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#equalityExpression.
    def visitEqualityExpression(self, ctx:CPP14Parser.EqualityExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#andExpression.
    def visitAndExpression(self, ctx:CPP14Parser.AndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#exclusiveOrExpression.
    def visitExclusiveOrExpression(self, ctx:CPP14Parser.ExclusiveOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#inclusiveOrExpression.
    def visitInclusiveOrExpression(self, ctx:CPP14Parser.InclusiveOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#logicalAndExpression.
    def visitLogicalAndExpression(self, ctx:CPP14Parser.LogicalAndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#logicalOrExpression.
    def visitLogicalOrExpression(self, ctx:CPP14Parser.LogicalOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#conditionalExpression.
    def visitConditionalExpression(self, ctx:CPP14Parser.ConditionalExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#assignmentOperator.
    def visitAssignmentOperator(self, ctx:CPP14Parser.AssignmentOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#expression.
    def visitExpression(self, ctx:CPP14Parser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#constantExpression.
    def visitConstantExpression(self, ctx:CPP14Parser.ConstantExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#statement.
    def visitStatement(self, ctx:CPP14Parser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#labeledStatement.
    def visitLabeledStatement(self, ctx:CPP14Parser.LabeledStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#expressionStatement.
    def visitExpressionStatement(self, ctx:CPP14Parser.ExpressionStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#compoundStatement.
    def visitCompoundStatement(self, ctx:CPP14Parser.CompoundStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#statementSeq.
    def visitStatementSeq(self, ctx:CPP14Parser.StatementSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#selectionStatement.
    def visitSelectionStatement(self, ctx:CPP14Parser.SelectionStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#condition.
    def visitCondition(self, ctx:CPP14Parser.ConditionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#iterationStatement.
    def visitIterationStatement(self, ctx:CPP14Parser.IterationStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#forInitStatement.
    def visitForInitStatement(self, ctx:CPP14Parser.ForInitStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#jumpStatement.
    def visitJumpStatement(self, ctx:CPP14Parser.JumpStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declarationseq.
    def visitDeclarationseq(self, ctx:CPP14Parser.DeclarationseqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declaration.
    def visitDeclaration(self, ctx:CPP14Parser.DeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#simpleDeclaration.
    def visitSimpleDeclaration(self, ctx:CPP14Parser.SimpleDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declSpecifier.
    def visitDeclSpecifier(self, ctx:CPP14Parser.DeclSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declSpecifierSeq.
    def visitDeclSpecifierSeq(self, ctx:CPP14Parser.DeclSpecifierSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#typedefName.
    def visitTypedefName(self, ctx:CPP14Parser.TypedefNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#typeSpecifier.
    def visitTypeSpecifier(self, ctx:CPP14Parser.TypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#trailingTypeSpecifier.
    def visitTrailingTypeSpecifier(self, ctx:CPP14Parser.TrailingTypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#typeSpecifierSeq.
    def visitTypeSpecifierSeq(self, ctx:CPP14Parser.TypeSpecifierSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#simpleTypeLengthModifier.
    def visitSimpleTypeLengthModifier(self, ctx:CPP14Parser.SimpleTypeLengthModifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#simpleTypeSignednessModifier.
    def visitSimpleTypeSignednessModifier(self, ctx:CPP14Parser.SimpleTypeSignednessModifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#simpleTypeSpecifier.
    def visitSimpleTypeSpecifier(self, ctx:CPP14Parser.SimpleTypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#theTypeName.
    def visitTheTypeName(self, ctx:CPP14Parser.TheTypeNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#initDeclaratorList.
    def visitInitDeclaratorList(self, ctx:CPP14Parser.InitDeclaratorListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#initDeclarator.
    def visitInitDeclarator(self, ctx:CPP14Parser.InitDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declarator.
    def visitDeclarator(self, ctx:CPP14Parser.DeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#pointerDeclarator.
    def visitPointerDeclarator(self, ctx:CPP14Parser.PointerDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#noPointerDeclarator.
    def visitNoPointerDeclarator(self, ctx:CPP14Parser.NoPointerDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#parametersAndQualifiers.
    def visitParametersAndQualifiers(self, ctx:CPP14Parser.ParametersAndQualifiersContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#pointerOperator.
    def visitPointerOperator(self, ctx:CPP14Parser.PointerOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#cvqualifierseq.
    def visitCvqualifierseq(self, ctx:CPP14Parser.CvqualifierseqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#cvQualifier.
    def visitCvQualifier(self, ctx:CPP14Parser.CvQualifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#refqualifier.
    def visitRefqualifier(self, ctx:CPP14Parser.RefqualifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#declaratorid.
    def visitDeclaratorid(self, ctx:CPP14Parser.DeclaratoridContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#theTypeId.
    def visitTheTypeId(self, ctx:CPP14Parser.TheTypeIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#parameterDeclarationClause.
    def visitParameterDeclarationClause(self, ctx:CPP14Parser.ParameterDeclarationClauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#parameterDeclarationList.
    def visitParameterDeclarationList(self, ctx:CPP14Parser.ParameterDeclarationListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#parameterDeclaration.
    def visitParameterDeclaration(self, ctx:CPP14Parser.ParameterDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#functionDefinition.
    def visitFunctionDefinition(self, ctx:CPP14Parser.FunctionDefinitionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#functionBody.
    def visitFunctionBody(self, ctx:CPP14Parser.FunctionBodyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#initializer.
    def visitInitializer(self, ctx:CPP14Parser.InitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#braceOrEqualInitializer.
    def visitBraceOrEqualInitializer(self, ctx:CPP14Parser.BraceOrEqualInitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#initializerClause.
    def visitInitializerClause(self, ctx:CPP14Parser.InitializerClauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#initializerList.
    def visitInitializerList(self, ctx:CPP14Parser.InitializerListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#bracedInitList.
    def visitBracedInitList(self, ctx:CPP14Parser.BracedInitListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#className.
    def visitClassName(self, ctx:CPP14Parser.ClassNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#classSpecifier.
    def visitClassSpecifier(self, ctx:CPP14Parser.ClassSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#classHead.
    def visitClassHead(self, ctx:CPP14Parser.ClassHeadContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#classHeadName.
    def visitClassHeadName(self, ctx:CPP14Parser.ClassHeadNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#classKey.
    def visitClassKey(self, ctx:CPP14Parser.ClassKeyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memberSpecification.
    def visitMemberSpecification(self, ctx:CPP14Parser.MemberSpecificationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memberdeclaration.
    def visitMemberdeclaration(self, ctx:CPP14Parser.MemberdeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memberDeclaratorList.
    def visitMemberDeclaratorList(self, ctx:CPP14Parser.MemberDeclaratorListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memberDeclarator.
    def visitMemberDeclarator(self, ctx:CPP14Parser.MemberDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#baseSpecifierList.
    def visitBaseSpecifierList(self, ctx:CPP14Parser.BaseSpecifierListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#accessSpecifier.
    def visitAccessSpecifier(self, ctx:CPP14Parser.AccessSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memInitializerList.
    def visitMemInitializerList(self, ctx:CPP14Parser.MemInitializerListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#memInitializer.
    def visitMemInitializer(self, ctx:CPP14Parser.MemInitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#meminitializerid.
    def visitMeminitializerid(self, ctx:CPP14Parser.MeminitializeridContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#operatorFunctionId.
    def visitOperatorFunctionId(self, ctx:CPP14Parser.OperatorFunctionIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#literalOperatorId.
    def visitLiteralOperatorId(self, ctx:CPP14Parser.LiteralOperatorIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateDeclaration.
    def visitTemplateDeclaration(self, ctx:CPP14Parser.TemplateDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateparameterList.
    def visitTemplateparameterList(self, ctx:CPP14Parser.TemplateparameterListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateParameter.
    def visitTemplateParameter(self, ctx:CPP14Parser.TemplateParameterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#typeParameter.
    def visitTypeParameter(self, ctx:CPP14Parser.TypeParameterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#simpleTemplateId.
    def visitSimpleTemplateId(self, ctx:CPP14Parser.SimpleTemplateIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateId.
    def visitTemplateId(self, ctx:CPP14Parser.TemplateIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateName.
    def visitTemplateName(self, ctx:CPP14Parser.TemplateNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateArgumentList.
    def visitTemplateArgumentList(self, ctx:CPP14Parser.TemplateArgumentListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#templateArgument.
    def visitTemplateArgument(self, ctx:CPP14Parser.TemplateArgumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#explicitInstantiation.
    def visitExplicitInstantiation(self, ctx:CPP14Parser.ExplicitInstantiationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#theOperator.
    def visitTheOperator(self, ctx:CPP14Parser.TheOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#literal.
    def visitLiteral(self, ctx:CPP14Parser.LiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#myId.
    def visitMyId(self, ctx:CPP14Parser.MyIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by CPP14Parser#myClass.
    def visitMyClass(self, ctx:CPP14Parser.MyClassContext):
        return self.visitChildren(ctx)



del CPP14Parser