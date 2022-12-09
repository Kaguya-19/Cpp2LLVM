from lex import Token

class Parser():
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
