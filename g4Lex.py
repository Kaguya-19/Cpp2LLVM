import re
import sys
from lex import Lexer, Token    

class G4Lexer(Lexer):
    '''
    A Lexer for ANTLR4 grammar files.
    '''
    
    optr_dict = { '+': 'PLUS', '*': 'STAR', '?': 'QUESTION', 
                  '|': 'OR', '(': 'LeftParen', ')': 'RPAREN',';': 'SEMICOLON',':':'COLON'}
    OPTR = re.compile(r'[+*?|():;]')    
    
    def scan(self):
        tokens = []
        while self.skip() != None:
            if Lexer.NONDIGIT.match(self.current_char):
                token = self.scan_identifier()
            elif G4Lexer.OPTR.match(self.current_char):
                token = self.scan_operator()
            else:
                # print(self.text)
                # print(self.current_char)
                # print(tokens)
                self.error()
            
            if token:
                # print(token)
                tokens.append(token)
            else:
                self.error()
        tokens.append(Token('EOF',None,self.pos,self.pos))
        return tokens
    
    def scan_operator(self):
        result = ''
        while self.current_char is not None and G4Lexer.OPTR.match(self.current_char):
            result += self.current_char
            self.advance()
        while result:
            if result in G4Lexer.optr_dict:
                return Token(G4Lexer.optr_dict[result], result, self.old_pos, self.pos)
            result = result[:-1]
            self.advance(-1)
        return None
        
if __name__ == '__main__':
    lexer = G4Lexer(sys.stdin.read())
    tokens = lexer.scan()
    for token in tokens:
        print(token)