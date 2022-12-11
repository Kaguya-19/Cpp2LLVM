# Generated from myParser.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .myParser import myParser
else:
    from myParser import myParser

# This class defines a complete generic visitor for a parse tree produced by myParser.

class myParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by myParser#translationUnit.
    def visitTranslationUnit(self, ctx:myParser.TranslationUnitContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#primaryExpression.
    def visitPrimaryExpression(self, ctx:myParser.PrimaryExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#idExpression.
    def visitIdExpression(self, ctx:myParser.IdExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#postfixExpression.
    def visitPostfixExpression(self, ctx:myParser.PostfixExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#unaryExpression.
    def visitUnaryExpression(self, ctx:myParser.UnaryExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#unaryOperator.
    def visitUnaryOperator(self, ctx:myParser.UnaryOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#newExpression.
    def visitNewExpression(self, ctx:myParser.NewExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#newTypeId.
    def visitNewTypeId(self, ctx:myParser.NewTypeIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#newDeclarator.
    def visitNewDeclarator(self, ctx:myParser.NewDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#noPointerNewDeclarator.
    def visitNoPointerNewDeclarator(self, ctx:myParser.NoPointerNewDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#deleteExpression.
    def visitDeleteExpression(self, ctx:myParser.DeleteExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#castExpression.
    def visitCastExpression(self, ctx:myParser.CastExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#pointerMemberExpression.
    def visitPointerMemberExpression(self, ctx:myParser.PointerMemberExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#multiplicativeExpression.
    def visitMultiplicativeExpression(self, ctx:myParser.MultiplicativeExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#additiveExpression.
    def visitAdditiveExpression(self, ctx:myParser.AdditiveExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#shiftExpression.
    def visitShiftExpression(self, ctx:myParser.ShiftExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#shiftOperator.
    def visitShiftOperator(self, ctx:myParser.ShiftOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#relationalExpression.
    def visitRelationalExpression(self, ctx:myParser.RelationalExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#equalityExpression.
    def visitEqualityExpression(self, ctx:myParser.EqualityExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#andExpression.
    def visitAndExpression(self, ctx:myParser.AndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#exclusiveOrExpression.
    def visitExclusiveOrExpression(self, ctx:myParser.ExclusiveOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#inclusiveOrExpression.
    def visitInclusiveOrExpression(self, ctx:myParser.InclusiveOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#logicalAndExpression.
    def visitLogicalAndExpression(self, ctx:myParser.LogicalAndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#logicalOrExpression.
    def visitLogicalOrExpression(self, ctx:myParser.LogicalOrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#conditionalExpression.
    def visitConditionalExpression(self, ctx:myParser.ConditionalExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#assignmentOperator.
    def visitAssignmentOperator(self, ctx:myParser.AssignmentOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#expression.
    def visitExpression(self, ctx:myParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#constantExpression.
    def visitConstantExpression(self, ctx:myParser.ConstantExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#statement.
    def visitStatement(self, ctx:myParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#labeledStatement.
    def visitLabeledStatement(self, ctx:myParser.LabeledStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#expressionStatement.
    def visitExpressionStatement(self, ctx:myParser.ExpressionStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#compoundStatement.
    def visitCompoundStatement(self, ctx:myParser.CompoundStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#statementSeq.
    def visitStatementSeq(self, ctx:myParser.StatementSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#selectionStatement.
    def visitSelectionStatement(self, ctx:myParser.SelectionStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#condition.
    def visitCondition(self, ctx:myParser.ConditionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#iterationStatement.
    def visitIterationStatement(self, ctx:myParser.IterationStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#forInitStatement.
    def visitForInitStatement(self, ctx:myParser.ForInitStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#jumpStatement.
    def visitJumpStatement(self, ctx:myParser.JumpStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declarationseq.
    def visitDeclarationseq(self, ctx:myParser.DeclarationseqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declaration.
    def visitDeclaration(self, ctx:myParser.DeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#simpleDeclaration.
    def visitSimpleDeclaration(self, ctx:myParser.SimpleDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declSpecifier.
    def visitDeclSpecifier(self, ctx:myParser.DeclSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declSpecifierSeq.
    def visitDeclSpecifierSeq(self, ctx:myParser.DeclSpecifierSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#typedefName.
    def visitTypedefName(self, ctx:myParser.TypedefNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#typeSpecifier.
    def visitTypeSpecifier(self, ctx:myParser.TypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#trailingTypeSpecifier.
    def visitTrailingTypeSpecifier(self, ctx:myParser.TrailingTypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#typeSpecifierSeq.
    def visitTypeSpecifierSeq(self, ctx:myParser.TypeSpecifierSeqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#simpleTypeLengthModifier.
    def visitSimpleTypeLengthModifier(self, ctx:myParser.SimpleTypeLengthModifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#simpleTypeSignednessModifier.
    def visitSimpleTypeSignednessModifier(self, ctx:myParser.SimpleTypeSignednessModifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#simpleTypeSpecifier.
    def visitSimpleTypeSpecifier(self, ctx:myParser.SimpleTypeSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#theTypeName.
    def visitTheTypeName(self, ctx:myParser.TheTypeNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initDeclaratorList.
    def visitInitDeclaratorList(self, ctx:myParser.InitDeclaratorListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initDeclarator.
    def visitInitDeclarator(self, ctx:myParser.InitDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declarator.
    def visitDeclarator(self, ctx:myParser.DeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#pointerDeclarator.
    def visitPointerDeclarator(self, ctx:myParser.PointerDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#noPointerDeclarator.
    def visitNoPointerDeclarator(self, ctx:myParser.NoPointerDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parametersAndQualifiers.
    def visitParametersAndQualifiers(self, ctx:myParser.ParametersAndQualifiersContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#pointerOperator.
    def visitPointerOperator(self, ctx:myParser.PointerOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#cvqualifierseq.
    def visitCvqualifierseq(self, ctx:myParser.CvqualifierseqContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#cvQualifier.
    def visitCvQualifier(self, ctx:myParser.CvQualifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#refqualifier.
    def visitRefqualifier(self, ctx:myParser.RefqualifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#declaratorid.
    def visitDeclaratorid(self, ctx:myParser.DeclaratoridContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#theTypeId.
    def visitTheTypeId(self, ctx:myParser.TheTypeIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parameterDeclarationClause.
    def visitParameterDeclarationClause(self, ctx:myParser.ParameterDeclarationClauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parameterDeclarationList.
    def visitParameterDeclarationList(self, ctx:myParser.ParameterDeclarationListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#parameterDeclaration.
    def visitParameterDeclaration(self, ctx:myParser.ParameterDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#functionDefinition.
    def visitFunctionDefinition(self, ctx:myParser.FunctionDefinitionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#functionBody.
    def visitFunctionBody(self, ctx:myParser.FunctionBodyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initializer.
    def visitInitializer(self, ctx:myParser.InitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#braceOrEqualInitializer.
    def visitBraceOrEqualInitializer(self, ctx:myParser.BraceOrEqualInitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initializerClause.
    def visitInitializerClause(self, ctx:myParser.InitializerClauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initializerList.
    def visitInitializerList(self, ctx:myParser.InitializerListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#bracedInitList.
    def visitBracedInitList(self, ctx:myParser.BracedInitListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#className.
    def visitClassName(self, ctx:myParser.ClassNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#classSpecifier.
    def visitClassSpecifier(self, ctx:myParser.ClassSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#classHead.
    def visitClassHead(self, ctx:myParser.ClassHeadContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#classHeadName.
    def visitClassHeadName(self, ctx:myParser.ClassHeadNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#classKey.
    def visitClassKey(self, ctx:myParser.ClassKeyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memberSpecification.
    def visitMemberSpecification(self, ctx:myParser.MemberSpecificationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memberdeclaration.
    def visitMemberdeclaration(self, ctx:myParser.MemberdeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memberDeclaratorList.
    def visitMemberDeclaratorList(self, ctx:myParser.MemberDeclaratorListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memberDeclarator.
    def visitMemberDeclarator(self, ctx:myParser.MemberDeclaratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#baseSpecifierList.
    def visitBaseSpecifierList(self, ctx:myParser.BaseSpecifierListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#accessSpecifier.
    def visitAccessSpecifier(self, ctx:myParser.AccessSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memInitializerList.
    def visitMemInitializerList(self, ctx:myParser.MemInitializerListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#memInitializer.
    def visitMemInitializer(self, ctx:myParser.MemInitializerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#meminitializerid.
    def visitMeminitializerid(self, ctx:myParser.MeminitializeridContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#operatorFunctionId.
    def visitOperatorFunctionId(self, ctx:myParser.OperatorFunctionIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#literalOperatorId.
    def visitLiteralOperatorId(self, ctx:myParser.LiteralOperatorIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateDeclaration.
    def visitTemplateDeclaration(self, ctx:myParser.TemplateDeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateparameterList.
    def visitTemplateparameterList(self, ctx:myParser.TemplateparameterListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateParameter.
    def visitTemplateParameter(self, ctx:myParser.TemplateParameterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#typeParameter.
    def visitTypeParameter(self, ctx:myParser.TypeParameterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#simpleTemplateId.
    def visitSimpleTemplateId(self, ctx:myParser.SimpleTemplateIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateId.
    def visitTemplateId(self, ctx:myParser.TemplateIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateName.
    def visitTemplateName(self, ctx:myParser.TemplateNameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateArgumentList.
    def visitTemplateArgumentList(self, ctx:myParser.TemplateArgumentListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#templateArgument.
    def visitTemplateArgument(self, ctx:myParser.TemplateArgumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#explicitInstantiation.
    def visitExplicitInstantiation(self, ctx:myParser.ExplicitInstantiationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#theOperator.
    def visitTheOperator(self, ctx:myParser.TheOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#literal.
    def visitLiteral(self, ctx:myParser.LiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#myId.
    def visitMyId(self, ctx:myParser.MyIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#myClass.
    def visitMyClass(self, ctx:myParser.MyClassContext):
        return self.visitChildren(ctx)



del myParser