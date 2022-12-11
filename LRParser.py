#  a LR(1) parser for C++
from g4Lex import G4Lexer
from lex import Lexer, Token 
import sys

class Parser():
    '''
    A LR(1) parser for C++
    '''
    def __init__(self, grammar_file, tokens=[]):
        self.grammar = self.read_grammar_g4(grammar_file)
        self.tokens = tokens
        self.nonterminals = list(self.grammar.keys())
        self.action = {}
        self.goto = {}
        self.build_table()
        
    def read_grammar_g4(self, filename):
        
        def read_error(value):
            raise Exception('Error word'+str(value))
            pass
        '''
        Read a grammar from a file in the ANTLR4 format.
        '''
        # Read the grammar file
        with open(filename, 'r') as f:
            file = f.read()
            
        g4Tokens = G4Lexer(file).scan()
            
        # Parse the grammar
        self.grammar = {}
        self.tokens = []
        state = 'start'
        parenNum = 0
        
        # a regex for python
        for token in g4Tokens:
            if token.type == 'EOF':
                # print(self.grammar)
                return self.grammar
            if state == 'start':
                if token.type == 'Identifier':
                    state = 'lhs'
                    lhs = token.value
                    self.grammar[lhs] = []
                    rhs = []
                    parenNum = 0
                    parenList = {}
                    orList = {}
                else:
                    read_error(token.value)
            elif state == 'lhs':
                if token.type == 'COLON':
                    state = 'rhs'
                else:
                    read_error(token.value)
            
            elif state == 'rhs':
                if token.type == 'LeftParen':
                    parenNum += 1
                    parenList[parenNum] = []
                    hasOr = {parenNum: False}
                    state = 'inParen'
                elif token.type == 'Identifier':
                    rhs.append(token.value)
                elif token.type == 'SEMICOLON':
                    self.grammar[lhs].append(rhs)
                    state = 'start'
                elif token.type == 'OR':
                    self.grammar[lhs].append(rhs)
                    rhs = []
                elif token.type == 'PLUS' or token.type == 'STAR' or token.type == 'QUESTION':
                    # add a new grammar rule and change the priveous value
                    last = rhs[-1]
                    if isinstance(last, list):
                        pass
                    elif isinstance(last, str):
                        last = [last]
                    if token.type == 'PLUS':
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+']]
                        rhs[-1] = str(last)+'_+'
                    elif token.type == 'STAR':
                        self.grammar[str(last)+'_*'] = [[str(last)+'_*'],last, ['']]
                        rhs[-1] = str(last)+'_*'
                    elif token.type == 'QUESTION':
                        self.grammar[str(last)+'_?'] = [last, ['']]
                        rhs[-1] = str(last)+'_?'
                else:
                    read_error(token.value) 
            
            elif state == 'inParen':
                if token.type == 'LeftParen':
                    parenNum += 1
                    hasOr[parenNum] = False
                    parenList[parenNum] = []
                    orList[parenNum] = []
                elif token.type == 'RPAREN':
                    if hasOr[parenNum]:
                        orList[parenNum].append(parenList[parenNum])
                        self.grammar[str(orList[parenNum])+'_or'] = orList[parenNum]
                        parenList[parenNum] = [str(orList[parenNum])+'_or']
                    parenNum -= 1
                    if parenNum == 0:
                        state = 'rhs'
                        for value in parenList[1]:
                            rhs.append(value)
                    else:
                        for value in parenList[parenNum+1]:
                            parenList[parenNum].append(value)
                elif token.type == 'Identifier':
                    parenList[parenNum].append(token.value)
                elif token.type == 'PLUS' or token.type == 'STAR' or token.type == 'QUESTION':
                    last = parenList[parenNum][-1]
                    if isinstance(last, list):
                        pass
                    elif isinstance(last, str):
                        last = [last]
                    if token.type == 'PLUS':
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+']]
                        parenList[parenNum][-1] = str(last)+'_+'
                    elif token.type == 'STAR':
                        self.grammar[str(last)+'_*'] = [[str(last)+'_*'],last, ['']]
                        parenList[parenNum][-1] = str(last)+'_*'
                    elif token.type == 'QUESTION':
                        self.grammar[str(last)+'_?'] = [last, ['']]
                        parenList[parenNum][-1] = str(last)+'_?'
                elif token.type == 'OR':
                    if hasOr[parenNum]:
                        orList[parenNum].append(parenList[parenNum])
                    else:
                        hasOr[parenNum] = True
                        orList[parenNum] = [parenList[parenNum]]
                    parenList[parenNum] = []
                else:
                    read_error(token.value)
            
        
    def build_table(self):
        '''
        Build the action and goto tables.
        '''
        # Build the first and follow sets
        self.first = {}
        self.follow = {}
        self.start = 'translationUnit'
        
        # augment the grammar
        self.grammar['S\''] = [[self.start]]
        
        # compute the first and follow sets
        self.compute_first_total()
        self.compute_follow_total()
        
        # Build the parsing table
        self.compute_states()
        self.build_action()
        self.build_goto()
        
    
    def compute_first_total(self):
        '''
        Compute the first sets for the grammar.
        '''
        def compute_first(self, lhs):
            changed = False
            # if isinstance(lhs, list):
            #     print(lhs)
            
            if lhs in self.first:
                return False
            self.first[lhs] = set()
            rhs = self.grammar[lhs]
            for rule in rhs:
                for i,token in enumerate(rule):
                    if token in self.tokens:
                        if token not in self.first[lhs]:
                            self.first[lhs].add(token)
                            changed = True
                    else:
                        if compute_first(self,token):
                            changed = True

                        if self.first[token] - self.first[lhs]:
                            self.first[lhs] |= self.first[token] - set([''])
                            changed = True
                    if '' not in self.first[token]:
                        break
                    if i == len(rule)-1:
                        if '' not in self.first[lhs]:
                            self.first[lhs].add('')
                            changed = True
            return changed
        
        # Initialize the first sets
        for token in self.tokens:
            self.first[token] = set([token])
                
        # Compute the first sets
        changed = True
        while changed:
            changed = False
            for lhs, _ in self.grammar.items():
                if compute_first(self,lhs):
                    changed = True
        
        print(self.first)
                    
    def compute_follow_total(self):       
        '''
        Compute the follow sets for the grammar.
        '''
        def compute_follow(self, lhs,rhs):
            if lhs not in self.follow:
                self.follow[lhs] = set()
            changed = False
            for rule in rhs:
                # For each token in the rule
                for i,token in enumerate(rule):
                    # If the token is a non-terminal
                    if token not in self.tokens:
                        first = set()
                        # Get the first set of the next token
                        if i == len(rule) - 1:
                            first = set([''])
                        else:
                            j = i + 1
                            while j < len(rule):
                                first |= self.first[rule[j]]
                                if '' not in self.first[rule[j]]:
                                    first -= set([''])
                                    break
                        
                        if token not in self.follow:
                            self.follow[token] = set()
                        
                        # Add the first set to the follow set
                        if first - self.follow[token]:
                            self.follow[token] |= (first - set(['']))
                            changed = True
                            
                        # If the first set contains epsilon, add the follow set of the rule
                        if '' in first and self.follow[lhs] - self.follow[token]:
                            self.follow[token] |= self.follow[lhs]
                            changed = True
                        compute_follow(self,token,self.grammar[token])
            return changed
            
        
        # Initialize the follow sets
        for token in self.tokens:
            self.follow[token] = set()
            
        # Add the '#' symbol to the follow set of the start symbol
        self.follow[self.start] = set()
        self.follow[self.start].add('#')
        
        # Compute the follow sets
        compute_follow(self,self.start,self.grammar[self.start])
        
        # print(self.follow)
    
    def compute_closure(self, I):
        '''
        Compute the closure of a set of items.
        '''
        changed = True
        J = I
        while changed:
            changed = False
            for item in J:
                if item[1] < len(item[0]):
                    token = item[0][item[1]] # item[1] is the dot position
                    if token not in self.tokens:
                        for rule in self.grammar[token]:
                            if (rule,0) not in J:
                                J.append((rule,0))
                                changed = True
        return J
    
    def compute_go(self, I, X):
        '''
        Compute the goto of a set of items.
        '''
        J = []
        for item in I:
            if item[1] < len(item[0]):
                token = item[0][item[1]]
                if token == X:
                    if (item[0],item[1]+1) not in J:
                        J.append((item[0],item[1]+1))
        return self.compute_closure(J)
                
    
    def compute_states(self):
        '''
        Compute the states of the LR(0) automaton.
        '''
        # Initialize the states
        self.states = []
        self.states.append(self.compute_closure([(self.grammar[self.start][0],0)]))
        
        # Compute the states
        changed = True
        while changed:
            changed = False
            for state in self.states:
                for token in self.tokens:
                    go = self.compute_go(state,token)
                    if go and go not in self.states:
                        self.states.append(go)
                        changed = True
                        
        # print(self.states)
                               
    def build_action(self):
        '''
        Build the action table for LR(1) parsing.
        '''
        # Initialize the action table
        
        self.action = {}
        for i,state in enumerate(self.states):
            self.action[str(state)] = {}
            for token in self.tokens:
                self.action[str(state)][token] = None
            self.action[str(state)]['#'] = None
        
        # Build the action table
        for i,state in enumerate(self.states):
            for item in state:
                if item[1] == len(item[0]):
                    if item[0][0] == self.start:
                        self.action[str(state)]['#'] = ('acc',None)
                    else:
                        for follow in self.follow[item[0][0]]:
                            self.action[str(state)][follow] = ('r',item[0])
                else:
                    token = item[0][item[1]]
                    if token in self.tokens:
                        self.action[str(state)][token] = ('s',self.compute_go(state,token))
        
        # print(self.action)
    
    def build_goto(self):
        '''
        Build the goto table for LR(1) parsing.
        '''
        # Initialize the goto table
        self.goto = {}
        for state in self.states:
            self.goto[str(state)] = {}
            for token in self.nonterminals:
                self.goto[str(state)][token] = None
        
        # Build the goto table
        for state in self.states:
            for token in self.nonterminals:
                goto = self.compute_go(state,token)
                if goto:
                    self.goto[str(state)][token] = goto
    
    def parse(self, tokens):
        '''
        Parse a list of tokens using the LR(1) automaton and return the parse tree.
        '''
        # Initialize the stack
        stack = []
        stack.append((self.states[0],'$'))
    
        # Parse the tokens
        i = 0
        while i < len(tokens):
            state = stack[-1][0]
            token = tokens[i]
            if self.action[str(state)][token.type] is None:
                return None
            if self.action[str(state)][token.type][0] == 's':
                stack.append((self.action[str(state)][token.type][1],token))
                i += 1
            elif self.action[str(state)][token.type][0] == 'r':
                rule = self.action[str(state)][token.type][1]
                for j in range(len(rule)-1):
                    stack.pop()
                state = stack[-1][0]
                stack.append((self.goto[str(state)][rule[0]],rule))
            elif self.action[str(state)][token.type][0] == 'acc':
                # Return the parse tree
                return stack[-1][1]
            else:
                raise Exception('Syntax error')
            
            
    
if __name__ == '__main__':
    # Initialize the parser
    lexer = Lexer(sys.stdin.read())
    tokens = lexer.scan()
    parser = Parser('./myParser.g4', Lexer.cpp_tokens)
    # parser = Parser('./cppParser.g4', Lexer.cpp_tokens) 
    # Parse the tokens
    print(parser.parse(tokens))