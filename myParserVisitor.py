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


    # Visit a parse tree produced by myParser#functionDefine.
    def visitFunctionDefine(self, ctx:myParser.FunctionDefineContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#params.
    def visitParams(self, ctx:myParser.ParamsContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#param.
    def visitParam(self, ctx:myParser.ParamContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#funcBody.
    def visitFuncBody(self, ctx:myParser.FuncBodyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#body.
    def visitBody(self, ctx:myParser.BodyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#blocks.
    def visitBlocks(self, ctx:myParser.BlocksContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#initialBlock.
    def visitInitialBlock(self, ctx:myParser.InitialBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#arrayInitBlock.
    def visitArrayInitBlock(self, ctx:myParser.ArrayInitBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#assignBlock.
    def visitAssignBlock(self, ctx:myParser.AssignBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#ifBlocks.
    def visitIfBlocks(self, ctx:myParser.IfBlocksContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#ifBlock.
    def visitIfBlock(self, ctx:myParser.IfBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#elifBlock.
    def visitElifBlock(self, ctx:myParser.ElifBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#elseBlock.
    def visitElseBlock(self, ctx:myParser.ElseBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#condition.
    def visitCondition(self, ctx:myParser.ConditionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#forBlock.
    def visitForBlock(self, ctx:myParser.ForBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#for1Block.
    def visitFor1Block(self, ctx:myParser.For1BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#for3Block.
    def visitFor3Block(self, ctx:myParser.For3BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#returnBlock.
    def visitReturnBlock(self, ctx:myParser.ReturnBlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#expr.
    def visitExpr(self, ctx:myParser.ExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#typeDef.
    def visitTypeDef(self, ctx:myParser.TypeDefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#arrayItem.
    def visitArrayItem(self, ctx:myParser.ArrayItemContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#func.
    def visitFunc(self, ctx:myParser.FuncContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#strlenRef.
    def visitStrlenRef(self, ctx:myParser.StrlenRefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#printfRef.
    def visitPrintfRef(self, ctx:myParser.PrintfRefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#getsRef.
    def visitGetsRef(self, ctx:myParser.GetsRefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#funcRef.
    def visitFuncRef(self, ctx:myParser.FuncRefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by myParser#literal.
    def visitLiteral(self, ctx:myParser.LiteralContext):
        return self.visitChildren(ctx)



del myParser