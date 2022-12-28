from antlr4 import ParserRuleContext
from antlr4.error.ErrorListener import ErrorListener


class CompilationError(Exception):
    def __init__(self, msg: str, line: int, column: int):
        self.msg = msg
        self.line = line
        self.column = column

    def __str__(self):
        return self.msg


class SemanticError(CompilationError):
    def __init__(self, msg: str, ctx: ParserRuleContext = None):
        if ctx:
            line = ctx.start.line
            column = ctx.start.column
        else:
            line = 0
            column = 0
        msg = f'Semantic Error: {msg}'
        super().__init__(msg, line, column)


class ParserError(CompilationError):
    def __init__(self, msg: str, line: int, column: int):
        msg = f'Syntax Error: {msg}'
        super().__init__(msg, line, column)


class ParserErrorListener(ErrorListener):
    def syntaxError(self, recognizer, offending_symbol, line, column, msg, e):
        raise ParserError(msg, line, column)
