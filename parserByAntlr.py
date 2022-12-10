import sys
from antlr4 import *
from CPP14Lexer import CPP14Lexer
from CPP14Parser import CPP14Parser
 

def main():
    lexer = CPP14Lexer(StdinStream())
    stream = CommonTokenStream(lexer)
    parser = CPP14Parser(stream)
    tree = parser.translationUnit()
    print(tree.toStringTree(recog=parser))
 
if __name__ == '__main__':
    main()
    
