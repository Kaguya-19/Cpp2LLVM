import sys
from antlr4 import *
from CPP14Lexer import CPP14Lexer
 

def main():
    lexer = CPP14Lexer(StdinStream())
    stream = CommonTokenStream(lexer)
    print(stream.getTokens())
 
if __name__ == '__main__':
    main()