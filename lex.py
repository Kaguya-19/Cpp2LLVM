import re
import sys

class Token():
    '''
    Token class for the lexer
    '''
    def __init__(self, type, value, begin_pos, end_pos):
        self.type = type
        self.value = value
        self.begin_pos = begin_pos
        self.end_pos = end_pos
        
    def __repr__(self):
        return 'Token<{0}, {1}, {2}, {3}>'.format(self.type, self.value, self.begin_pos, self.end_pos)
        

class Lexer():
    '''
    A Lexer for C++
    '''
    
    key_dict = { 'alignas': 'Alignas', 'alignof': 'Alignof', 'asm': 'Asm', 'auto': 'Auto', 'bool': 'Bool', 'break': 'Break', 'case': 'Case', 'catch': 'Catch', 'char': 'Char', 'char16_t': 'Char16_t', 'char32_t': 'Char32_t', 'class': 'Class', 'const': 'Const', 'constexpr': 'Constexpr', 'const_cast': 'Const_cast', 'continue': 'Continue', 'decltype': 'Decltype', 'default': 'Default', 'delete': 'Delete', 'do': 'Do', 'double': 'Double', 'dynamic_cast': 'Dynamic_cast', 'else': 'Else', 'enum': 'Enum', 'explicit': 'Explicit', 'export': 'Export', 'extern': 'Extern', 'false': 'BooleanLiteral', 'float': 'Float', 'for': 'For', 'friend': 'Friend', 'goto': 'Goto', 'if': 'If', 'inline': 'Inline', 'int': 'Int', 'long': 'Long', 'mutable': 'Mutable', 'namespace': 'Namespace', 'new': 'New', 'noexcept': 'Noexcept', 'nullptr': 'Nullptr', 'operator': 'Operator',
                'private': 'Private', 'register': 'Register', 'reinterpret_cast': 'Reinterpret_cast', 'return': 'Return', 'short': 'Short', 'signed': 'Signed', 'sizeof': 'Sizeof', 'static': 'Static', 'static_assert': 'Static_assert', 'static_cast': 'Static_cast', 'struct': 'Struct', 'switch': 'Switch', 'template': 'Template', 'this': 'This', 'thread_local': 'Thread_local', 'throw': 'Throw', 'true': 'BooleanLiteral', 'try': 'Try', 'typedef': 'Typedef', 'typeid': 'Typeid', 'typename': 'Typename', 'union': 'Union', 'unsigned': 'Unsigned', 'using': 'Using', 'virtual': 'Virtual', 'void': 'Void', 'volatile': 'Volatile', 'wchar_t': 'Wchar_t', 'while': 'While' }
    my_key_dict = {'stack':'Stack','push':'push','empty':'empty','pop':'pop','top':'top',
                   'istream':'Istream','ostream':'Ostream','cin':'Istream_cin','cout':'Ostream_cout','endl':'Ostream_endl',
                   'string':'String','length':'Length','vector':'Vector','find':'Find','push_back':'Push_Back'} #将系统库函数定义为关键字
    key_dict.update(my_key_dict)
    optr_dict = { '(': 'LPAREN', ')': 'RPAREN', '{': 'LBRACE', '}': 'RBRACE', '[': 'LBRACKET', ']': 'RBRACKET', ';': 'SEMI', ',': 'COMMA', '.': 'DOT', '+': 'PLUS', '-': 'MINUS', '*': 'MUL', '/': 'DIV', '%': 'MOD', '++': 'INC', '--': 'DEC', '<<': 'LSHIFT', '>>': 'RSHIFT', '<': 'LT', '<=': 'LTE', '>': 'GT', '>=': 'GTE', '==': 'EQ', '!=': 'NEQ', '&': 'AND', '^': 'XOR', '|': 'OR', '&&': 'LAND', '||': 'LOR', '!': 'NOT', '~': 'BNOT', '?': 'TERNARY', ':': 'COLON', '=': 'ASSIGN', '+=': 'ADD_ASSIGN', '-=': 'SUB_ASSIGN', '*=': 'MUL_ASSIGN', '/=': 'DIV_ASSIGN', '%=': 'MOD_ASSIGN', '<<=': 'LSHIFT_ASSIGN', '>>=': 'RSHIFT_ASSIGN', '&=': 'AND_ASSIGN', '^=': 'XOR_ASSIGN', '|=': 'OR_ASSIGN', '->': 'ARROW', '.*': 'DOT_STAR', '...': 'ELLIPSIS' , '::': 'SCOPE' }
    DIGIT = re.compile(r'\d')
    NONDIGIT = re.compile(r'[a-zA-Z_]')
    IDPARRERN = re.compile(r'[a-zA-Z0-9_]*')
    OCT = re.compile(r'[0-7]')
    HEX = re.compile(r'[0-9a-fA-F]')
    BIN = re.compile(r'[01]+')
    OPTR = re.compile(r'[<>=!+\-*/%&|^~?:.,;()\[\]{}]')
    
    DecimalLiteral = re.compile(r'^([1-9]\d*|0)')
    OctalLiteral = re.compile(r'^(0[0-7]*)')
    HexadecimalLiteral = re.compile(r'^(0[xX][0-9a-fA-F]+)')
    BinaryLiteral = re.compile(r'^(0[bB][01]+)')
    CharacterLiteral = re.compile(r"^('(\\.|[^\\'])')")
    FloatingLiteral = re.compile(r'^(\d+\.\d+|\d+\.\d+[eE][+-]?\d+|\d+[eE][+-]?\d+)')
    StringLiteral = re.compile(r'^("(\\.|[^\\"])*")') #TODO: Add support for the syntax
    MultiLineMacro = re.compile(r'^(#\s*define\s+([a-zA-Z_][a-zA-Z0-9_]*)\s*(.*))') #TODO: preprocess the macro 
    Directive = re.compile(r'^(#\s*([a-zA-Z_][a-zA-Z0-9_]*)\s*(.*))')
    Skip = re.compile(r'^(\s+|//[^\r\n]*|/\*[\s\S]*?\*/)')
    
    
    def __init__(self, text):
        self.text = text
        self.pos = 0
        self.old_pos = 0
        self.current_char = self.text[0]
    
    def error(self):
        raise Exception('Invalid character {} at position {}'.format(self.current_char,self.pos))

    def advance(self,length = 1):
        self.pos += length
        if self.pos > len(self.text) - 1:
            self.current_char = None
        else:
            self.current_char = self.text[self.pos]
    
    def advance_word(self,parrern):
        str = re.match(parrern,self.text[self.pos:])
        if(str):
            # print(str.group())
            # print(len(str.group()))
            self.advance(len(str.group()))
            return str.group()
        return None
    
    def skip(self):
        self.old_pos = self.pos
        while self.advance_word(Lexer.Skip):
            self.old_pos = self.pos
            continue
        return self.current_char
        
    
    def scan(self):
        tokens = []
        while self.skip() != None:
            if Lexer.NONDIGIT.match(self.current_char):
                token = self.scan_identifier()
            elif Lexer.DIGIT.match(self.current_char):
                token = self.scan_number()
            elif Lexer.OPTR.match(self.current_char):
                token = self.scan_operator()
            elif self.current_char == "'":
                token = self.scan_character()
            elif self.current_char == '"':
                token = self.scan_string()
            elif self.current_char == '#':
                token = self.scan_directive()
            else:
                # print(self.text)
                # print(self.current_char)
                # print(tokens)
                self.error()
            
            if token:
                print(token)
                tokens.append(token)
            else:
                self.error()
        return tokens
    
    def scan_identifier(self):
        result = ''
        next_pos = self.advance_word(Lexer.IDPARRERN)
        if next_pos:
            result = next_pos
        else:
            return None
            
        # while self.current_char is not None and (Lexer.NONDIGIT.match(self.current_char) or Lexer.DIGIT.match(self.current_char)):
        #     result += self.current_char
        #     self.advance()
        if result in Lexer.key_dict:
            return Token(Lexer.key_dict[result], result, self.old_pos, self.pos)
        else:
            return Token('ID', result, self.old_pos, self.pos)
    
    def scan_number(self):
        result = ''
        # while self.current_char is not None and Lexer.DIGIT.match(self.current_char):
        #     result += self.current_char
        #     self.advance()
        # if self.current_char == '.': #floating point
        #     result += self.current_char
        #     self.advance()
        #     while self.current_char is not None and Lexer.DIGIT.match(self.current_char):
        #         result += self.current_char
        #         self.advance()
        #     if self.current_char == 'e' or self.current_char == 'E': #exponent
        #         result += self.current_char
        #         self.advance()
        #         if self.current_char == '+' or self.current_char == '-':
        #             result += self.current_char
        #             self.advance()
        #         while self.current_char is not None and Lexer.DIGIT.match(self.current_char):
        #             result += self.current_char
        #             self.advance()
        # elif self.current_char == 'e' or self.current_char == 'E':
        #     result += self.current_char
        #     self.advance()
        #     if self.current_char == '+' or self.current_char == '-':
        #             result += self.current_char
        #             self.advance()
        #     while self.current_char is not None and Lexer.DIGIT.match(self.current_char):
        #         result += self.current_char
        #         self.advance()
        # elif result == '0':
        #     if self.current_char == 'x' or self.current_char == 'X':
        #         result += self.current_char
        #         self.advance()
        #         while self.current_char is not None and Lexer.HEX.match(self.current_char):
        #             result += self.current_char
        #             self.advance()
        #     elif self.current_char == 'b' or self.current_char == 'B':
        #         result += self.current_char
        #         self.advance()
        #         while self.current_char is not None and Lexer.BIN.match(self.current_char):
        #             result += self.current_char
        #             self.advance()
        #     else:
        #         while self.current_char is not None and Lexer.OCT.match(self.current_char):
        #             result += self.current_char
        #             self.advance()
        
        # if Lexer.DecimalLiteral.match(result):
        #     return Token('DecimalLiteral', result, self.old_pos, self.pos)
        # elif Lexer.OctalLiteral.match(result):
        #     return Token('OctalLiteral', result, self.old_pos, self.pos)
        # elif Lexer.HexadecimalLiteral.match(result):
        #     return Token('HexadecimalLiteral', result, self.old_pos, self.pos)
        # elif Lexer.BinaryLiteral.match(result):
        #     return Token('BinaryLiteral', result, self.old_pos, self.pos)
        # elif Lexer.FloatingLiteral.match(result):
        #     return Token('FloatingLiteral', result, self.old_pos, self.pos)
        # else:
        #     return None
        result = self.advance_word(Lexer.FloatingLiteral)
        if result:
            return Token('FloatingLiteral', result, self.old_pos, self.pos)
        
        result = self.advance_word(Lexer.HexadecimalLiteral)
        if result:
            return Token('HexadecimalLiteral', result, self.old_pos, self.pos)
        
        result = self.advance_word(Lexer.DecimalLiteral)
        if result:
            return Token('DecimalLiteral', result, self.old_pos, self.pos)
        
        result = self.advance_word(Lexer.OctalLiteral)
        if result:
            return Token('OctalLiteral', result, self.old_pos, self.pos)
        
        result = self.advance_word(Lexer.BinaryLiteral)
        if result:
            return Token('BinaryLiteral', result, self.old_pos, self.pos)
        
        return None        
        
    def scan_operator(self):
        result = ''
        while self.current_char is not None and Lexer.OPTR.match(self.current_char):
            result += self.current_char
            self.advance()
        while result:
            if result in Lexer.optr_dict:
                return Token(Lexer.optr_dict[result], result, self.old_pos, self.pos)
            result = result[:-1]
            self.advance(-1)
        return None
    
    def scan_character(self):
        result = self.advance_word(Lexer.CharacterLiteral)
        if result:
            return Token('CharacterLiteral', result, self.old_pos, self.pos)
        # self.advance()
        # result = '\''
        # while self.current_char is not None:
        #     if self.current_char == "'":
        #         if result[-1] != '\\':
        #             break
        #     result += self.current_char
        #     self.advance()
        # result += self.current_char
        # self.advance()
        # if Lexer.CharacterLiteral.match(result):
        #     return Token('CharacterLiteral', result, self.old_pos, self.pos)
        
    def scan_string(self):
        result = self.advance_word(Lexer.StringLiteral)
        if result:
            return Token('StringLiteral', result, self.old_pos, self.pos)
        # self.advance()
        # result = '"'
        # while self.current_char is not None:
        #     if self.current_char == '"':
        #         if result[-1] != '\\':
        #             break
        #     result += self.current_char
        #     self.advance()
        # result += self.current_char
        # self.advance()
        # if Lexer.StringLiteral.match(result):
        #     return Token('StringLiteral', result, self.old_pos, self.pos)
        
    def scan_directive(self):
        
        result = self.advance_word(Lexer.MultiLineMacro)
        if result:
            return Token('MultiLineMacro', result, self.old_pos, self.pos)
        
        result = self.advance_word(Lexer.Directive)
        if result:
            return Token('Directive', result, self.old_pos, self.pos)

        return None
        
        
if __name__ == '__main__':
    lexer = Lexer(sys.stdin.read())
    tokens = lexer.scan()
    for token in tokens:
        print(token)