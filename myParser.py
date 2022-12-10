from lex import Token

class Parser():
    tokenID = ['Alignas', 'Alignof', 'Asm', 'Auto', 'Bool', 'Break', 'Case', 'Catch', 'Char', 'Char16_t', 'Char32_t', 'Class', 'Const', 'Constexpr', 'Const_cast', 'Continue', 'Decltype', 'Default', 'Delete', 'Do', 
                'Double', 'Dynamic_cast', 'Else', 'Enum', 'Explicit', 'Export', 'Extern', 'Float', 'For', 'Friend', 'Goto', 'If', 'Inline', 'Int', 'Long', 'Mutable', 'Namespace', 'New', 'Noexcept', 'Nullptr', 'Operator',
                'Private', 'Register', 'Reinterpret_cast', 'Return', 'Short', 'Signed', 'Sizeof', 'Static', 'Static_assert', 'Static_cast', 'Struct', 'Switch', 'Template', 'This', 'Thread_local', 'Throw', 'Try', 'Typedef', 'Typeid', 'Typename', 
                'Union', 'Unsigned', 'Using', 'Virtual', 'Void', 'Volatile', 'Wchar_t', 'While', 'Stack', 'push', 'empty', 'pop', 'top', 'Istream', 'Ostream', 'Istream_cin', 'Ostream_cout', 'Ostream_endl',
                'String', 'Length', 'Vector', 'Find', 'Push_Back']
    LiteralTokenID = ['ID', 'FloatingLiteral', 'HexadecimalLiteral', 'DecimalLiteral', 'OctalLiteral', 'BinaryLiteral', 'CharacterLiteral', 'BooleanLiteral', 'StringLiteral', 'MultiLineMacro', 'Directive']
    optTokenID = ['LeftParen', 'RPAREN', 'LBRACE', 'RBRACE', 'LeftBracket', 'RBRACKET', 'Semi', 'COMMA', 'DOT', 'PLUS', 'MINUS', 'MUL', 'DIV', 'MOD', 'INC', 'DEC', 'LSHIFT', 'RSHIFT', 'LT', 'LTE', 'GT', 'GTE', 'EQ', 'NEQ', 'AND', 'XOR', 'OR', 'LAND', 'LOR', 'NOT', 'Tilde',
                'TERNARY', 'COLON', 'ASSIGN', 'ADD_ASSIGN', 'SUB_ASSIGN', 'MUL_ASSIGN', 'DIV_ASSIGN', 'MOD_ASSIGN', 'LSHIFT_ASSIGN', 'RSHIFT_ASSIGN', 'AND_ASSIGN', 'XOR_ASSIGN', 'OR_ASSIGN', 'ARROW', 'DOT_STAR', 'ELLIPSIS' , 'SCOPE']

    def __init__(self, tokens: list[Token]):
        self.tokens = tokens
        self.parseTree = {}
        self.tokenIter = iter(self.tokens)
        self.currentToken = self.tokenIter
        self.lookahead = next(self.tokenIter)
        pass

    def getNextToken(self):
        try:
            self.currentToken = self.lookahead
            self.lookahead = next(self.tokenIter)
        except StopIteration:
            # return EOF
            pass

    def matchToken(self, tokenType):
        self.getNextToken()
        if tokenType != self.currentToken.type:
            print('syntax error')
            exit(0)
        pass


    def parse(self):
        
        pass

    
