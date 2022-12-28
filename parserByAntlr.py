import sys
from antlr4 import *
from CPP14Lexer import CPP14Lexer
# from CPP14Parser import CPP14Parser
# from CPP14ParserVisitor import CPP14ParserVisitor
from myParser import myParser
 
def toDict(tree):
    '''Converts an ANTLR tree to a dictionary.'''
    d = { 'name': type(tree).__name__ } 
    if hasattr(tree, 'children'):
        d['children'] = [toDict(c) for c in tree.children]
    elif hasattr(tree, 'symbol'):
        d['symbol'] = tree.symbol.text
    return d
    
    

def main(argv=sys.argv):
    lexer = CPP14Lexer(StdinStream())
    stream = CommonTokenStream(lexer)
    
    for token in stream.tokens:
        print(token)
    
    # parser = CPP14Parser(stream)
    parser = myParser(stream)
    tree = parser.translationUnit()
    dictTree = toDict(tree)
    import json
    file_name = 'tree.json'
    if len(argv) > 1:
        file_name = argv[1]
    # save the tree as a json file
    with open(file_name, 'w') as f:
        json.dump(dictTree, f,indent=1)
    
if __name__ == '__main__':
    main()
    
