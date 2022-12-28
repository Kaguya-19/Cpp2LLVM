#  a LR(1) parser for C++
from g4Lex import G4Lexer
from lex import Lexer, Token 
import sys,json
# from treelib import Tree, Node

class Parser():
    '''
    A LR(1) parser for C++
    '''
    def __init__(self, grammar_file, tokens=[],start='translationUnit',load_state=False,load_table=False):
        self.grammar = self.read_grammar_g4(grammar_file)
        self.tokens = tokens
        self.nonterminals = list(self.grammar.keys()) 
        self.action = {}
        self.goto = {}
        self.start = start
        self.load_state = load_state
        self.load_table = load_table
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
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+', *last]]
                        rhs[-1] = str(last)+'_+'
                    elif token.type == 'STAR':
                        # STAR = (+)?
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+', *last]]
                        self.grammar[str(last)+'_*'] = [[str(last)+'_+'], ['']]
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
                    self.grammar[str(parenList[parenNum])+'_paren'] = [parenList[parenNum]]
                    if parenNum == 1:
                        state = 'rhs'
                        rhs.append(str(parenList[parenNum])+'_paren')
                        
                    else:
                        parenList[parenNum-1].append(str(parenList[parenNum])+'_paren')
                    parenNum -= 1
                elif token.type == 'Identifier':
                    parenList[parenNum].append(token.value)
                elif token.type == 'PLUS' or token.type == 'STAR' or token.type == 'QUESTION':
                    last = parenList[parenNum][-1]
                    if isinstance(last, list):
                        pass
                    elif isinstance(last, str):
                        last = [last]
                    if token.type == 'PLUS':
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+', *last]]
                        parenList[parenNum][-1] = str(last)+'_+'
                    elif token.type == 'STAR':
                        self.grammar[str(last)+'_+'] = [last,[str(last)+'_+', *last]]
                        self.grammar[str(last)+'_*'] = [[str(last)+'_+'], ['']]
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
        
            
            
    def delete_epsilon(self):
        '''
        Delete the epsilon rules in the grammar.
        '''
        # compute the nullable set
        nullable = {}
        for key in self.grammar:
            nullable[key] = False
        for key in self.tokens:
            nullable[key] = False
        nullable[''] = True
        changed = True
        while changed:
            changed = False
            for key in self.grammar:
                for rule in self.grammar[key]:
                    if all([nullable[x] for x in rule]):
                        if not nullable[key]:
                            changed = True
                            nullable[key] = True
                            
        # delete the epsilon rules
        trash = []
        for key in self.grammar:
            self.grammar[key] = [x for x in self.grammar[key] if x != ['']]
            if self.grammar[key] == []:
                trash.append(key)
        for key in trash:
            del self.grammar[key]
        for key in self.grammar:
            self.grammar[key] = [x for x in self.grammar[key] if x not in trash]
                
        # add the new rules with the nullable 
        for key in self.grammar:
            for rule in self.grammar[key]:
                if any([nullable[x] for x in rule]):
                    # get the index of the nullables
                    nullables = [i for i in range(len(rule)) if nullable[rule[i]]]
                    for i in range(2**len(nullables)):
                        new_rule = []
                        for j in range(len(nullables)):
                            if i & (1 << j): # if the jth bit is 1
                                new_rule.append(rule[j])
                        if new_rule and new_rule not in self.grammar[key]:
                            self.grammar[key].append(new_rule)
        
                   
                                              

        
    def build_table(self):
        '''
        Build the action and goto tables.
        '''
        # Build the first and follow sets
        self.first = {}
        # self.follow = {}

        
        # augment the grammar
        self.grammar['S\''] = [[self.start]]
        
        # self.delete_epsilon()
        
        # compute the first and follow sets
        self.compute_first_total()
        # self.compute_follow_total()
        
        # Build the parsing table
        if self.load_state:
            self.load_states()
        else:
            self.compute_states()
            self.save_state()
        if self.load_table:
            self.load_action()
            self.load_goto()
        else:
            self.build_action()
            self.save_action()
            self.build_goto()
            self.save_goto()
        
    
    def compute_first_total(self):
        '''
        Compute the first sets for the grammar.
        '''
        def compute_first(self, lhs):
            changed = False
            # if isinstance(lhs, list):
            #     print(lhs)
            
            if lhs in self.first or lhs == '':
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
                        if token == '':
                            continue
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
        
        # print(self.first)
                    
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
                        
                        # if token not in self.follow:
                        #     self.follow[token] = set()
                        
                        # Add the first set to the follow set
                        if (first - set([''])) - self.follow[token]:
                            self.follow[token] |= (first - set(['']))
                            changed = True
                            
                        # If the first set contains epsilon, add the follow set of the rule
                        if '' in first and self.follow[lhs] - self.follow[token]:
                            self.follow[token] |= self.follow[lhs]
                            changed = True
                        # compute_follow(self,token,self.grammar[token])
            return changed
            
        
        # Initialize the follow sets
        for token in self.nonterminals:
            self.follow[token] = set()
            
        # Add the 'EOF' symbol to the follow set of the start symbol
        self.follow['S\''] = set()
        self.follow['S\''].add('EOF')
        
        # Compute the follow sets
        changed = True
        while changed:
            changed = False
            for lhs, rhs in self.grammar.items():
                if compute_follow(self,lhs,rhs):
                    changed = True
        
        # print(self.follow)
    
    def compute_closure(self, I):
        '''
        Compute the closure of LR(1) items.
        '''
        changed = True
        J = I
        while changed:
            changed = False
            for item in J:
                if item['dot'] < len(item['rhs']):
                    token = item['rhs'][item['dot']]
                    if token == '':
                        continue
                    if token not in self.tokens:
                        # compute the first(\beta a) set
                        bs = set()
                        j = item['dot'] + 1
                        while j < len(item['rhs']):
                            bs |= self.first[item['rhs'][j]] - set([''])
                            if '' not in self.first[item['rhs'][j]]:
                                break
                            j += 1
                        if j == len(item['rhs']):
                            bs.add(item['lookahead']) # lookahead is terminal
                        for rule in self.grammar[token]:
                            for b in bs:
                                if {'lhs':token,'rhs':rule,'lookahead':b,'dot':0} not in J:
                                    J.append({'lhs':token,'rhs':rule,'lookahead':b,'dot':0})
                                    changed = True            

        return J
    
    def compute_go(self, I, X):
        '''
        Compute the go of a set of items.
        '''
        J = []
        for item in I:
            if item['rhs'] == ['']:
                continue
            if item['dot'] < len(item['rhs']):
                token = item['rhs'][item['dot']]
                if token == X:
                    if {'lhs':item['lhs'],'rhs':item['rhs'],'lookahead':item['lookahead'],'dot':item['dot']+1} not in J:
                        J.append({'lhs':item['lhs'],'rhs':item['rhs'],'lookahead':item['lookahead'],'dot':item['dot']+1})
        # print(J)
        return self.compute_closure(J)
                
    
    def compute_states(self):
        '''
        Compute the states of the LR(1) automaton.
        '''
        # Initialize the states
        self.states = []
        self.states.append(self.compute_closure([{'lhs':'S\'','rhs':[self.start],'lookahead':'EOF','dot':0}]))
        
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
                for token in self.nonterminals:
                    go = self.compute_go(state,token)
                    if go and go not in self.states:
                        self.states.append(go)
                        changed = True
                        

                               
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
            # for token in self.nonterminals:
            #     self.action[str(state)][token] = None
            self.action[str(state)]['EOF'] = None
            self.action[str(state)]['S\''] = None
        
        # Build the action table
        
        for _,state in enumerate(self.states):
            for item in state:
                # handle epsilon
                # if item['rhs'] == ['']:
                #     self.action[str(state)][item['lookahead']] = ('r',item)
                #     continue
                
                if item['dot'] == len(item['rhs']):
                    if item['lhs'] == 'S\'' and item['lookahead'] == 'EOF' and item['rhs'] == [self.start]:
                        self.action[str(state)]['EOF'] = ('acc',None)
                    else:
                        self.action[str(state)][item['lookahead']] = ('r',{'lhs':item['lhs'],'rhs':item['rhs']})
                else:
                    token = item['rhs'][item['dot']]
                    if token in self.tokens:
                        self.action[str(state)][token] = ('s',self.compute_go(state,token))
                        # self.action[str(state)][token] = ('s',self.states[self.states.index(self.compute_go(state,token))]) # use index to make sure the state is in the list
        
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
                    # print('goto:',goto)
                    finded = False
                    for the_state in self.states:
                        i = 0
                        findedGoto = True
                        for i,item in enumerate(goto):
                            if item not in the_state:
                                findedGoto = False
                                break
                        if i == len(goto) - 1 and findedGoto:
                            finded = True
                            break
                    # print('the_state:',the_state)
                    if not finded:
                        print('ERROR:can\'t find',goto)
                        return 
                    self.goto[str(state)][token] = the_state
    
    
    def parse(self, tokens):
        '''
        Parse a list of tokens using the LR(1) automaton and return the parse tree.
        '''
        # Initialize the stack
        stack = []
        stack.append(self.states[0])
        parserTree = ParserTree()
        # parserTree = Tree()
        # root_node = Node(tag=('S\'','S\''))
        # parserTree.create_node(root_node)
        # last_node = root_node.identifier
        # Parse the tokens
        i = 0
        # print('Grammar:')
        # for key in self.grammar:
        #     print(key,':',self.grammar[key])
        # print('STATES:')
        # for state in self.states:
        #     print(state)
        # print('action:')
        # for key in self.action:
        #     print(key,':',self.action[key])
        # print('goto:')
        # for key in self.goto:
        #     print(key,':',self.goto[key])
        # print(tokens)
        while i < len(tokens):
            token = tokens[i]
            state = stack[-1]
            if self.action[str(state)][token.type] == None:
                finded = False
                for state_ in state:
                    if state_['rhs'] == [''] and state_['lookahead'] == token.type: # handle epsilon
                        finded = True
                        stack.append(self.goto[str(state)][state_['lhs']])
                        parserTree.add_parent(state_['lhs'])
                        break
                if not finded:
                    print('ERROR: unexpected token',token.type,'in state',state)
                    return
            elif self.action[str(state)][token.type][0] == 's':
                parserTree.add_child(token.type,token.value)
                stack.append(self.action[str(state)][token.type][1])
                print(token.type,token.value)
                i += 1
                # print('shift',token.type)
            elif self.action[str(state)][token.type][0] == 'r':
                item = self.action[str(state)][token.type][1]
                parent = parserTree.add_parent(item['lhs'])   
                for _,r in enumerate(item['rhs'][::-1]):
                    stack.pop()
                    parserTree.add_child_to_parent(parent,r)
                parserTree.end_add(parent)
                
                stack.append(self.goto[str(stack[-1])][item['lhs']])
                print('reduce',item['lhs'],'->',item['rhs'])
            elif self.action[str(state)][token.type][0] == 'acc':
                parserTree.add_parent('S\'')
                parserTree.end()
                return parserTree
            else:
                print('ERROR: unknown action',self.action[str(state)][token.type])
                return
            # print('STACK',stack)
        print('ERROR: unexpected EOF')
        return

    def save_action(self):
        with open('action.json','w') as f:
            json.dump(self.action,f)
    def save_goto(self):
        with open('goto.json','w') as f:
            json.dump(self.goto,f)
    def save_state(self):
        with open('state.json','w') as f:
            for state in self.states:
                # print in one line
                json.dump({'state':state},f)
                f.write('\r')
                
    def load_action(self):
        with open('action.json','r') as f:
            
            self.action = json.load(f)
    
    def load_goto(self):
        with open('goto.json','r') as f:
            self.goto = json.load(f)
            
    def load_states(self):
        with open('state.json','r') as f:
            self.states = []
            for line in f:
                self.states.append(json.loads(line)['state'])

class ParserTree:
    class Node:
        def __init__(self, type, children=None, value=None, parent=None):
            self.type = type
            self.children = children
            self.value = value
            self.current_node = None
        
        def __dict__(self):
            dic = {'type':self.type}
            if self.value:
                dic['value'] = self.value
            if self.children:
                dic['children'] = []
                for child in self.children:
                    dic['children'].append(child.__dict__())
            return dic
        
        def __str__(self):
            return json.dumps(self.__dict__(),indent=1)
        
        def getText(self):
            return self.value
        
        def delStar(self):
            change = False
            if self.children:
                for child in self.children:
                    type = child.type
                    if type.endswith('_?') or type.endswith('_*') or type.endswith('_+') or type.endswith('_paren') or type.endswith('_or'):
                        self.children.remove(child)
                        change = True
                        self.children.extend(child.children)
                    child.delStar()
            return change
            
                
    
    def __init__(self):
        self.stack = []
        
    def __dict__(self):
        return self.root.__dict__()
    
    def __str__(self):
        return json.dumps(self.__dict__(),indent=1)
    
    def add_child(self, child_type, child_value=None):     
        child = self.Node(child_type, value=child_value)
        self.stack.append(child)
        
    def add_parent(self, parent_type):
        parent = self.Node(parent_type, children=[])
        return parent
        
    def add_child_to_parent(self, parent,type):
        if self.stack:
            if self.stack[-1].type == type:
                parent.children.append(self.stack.pop())
        
        
        
    def end_add(self, parent):
        parent.children.reverse()
        self.stack.append(parent)
        # print(parent)
        
    def end(self):
        self.root = self.stack.pop()
        self.current_node = self.root
        while self.root.delStar():
            pass
        
        
    def getChild(self, index):
        return self.current_node.children[index]
    
    def getCurrentNode(self):
        return self.current_node
    
    def getChildCount(self):
        return len(self.current_node.children)
    
    def getText(self):
        return self.current_node.getText()
        
        
if __name__ == '__main__':
    # Initialize the parser
    lexer = Lexer(sys.stdin.read())
    tokens = lexer.scan()
    parser = Parser('./myParser.g4', Lexer.cpp_tokens,start='translationUnit')
    # parser = Parser('./cppParser.g4', Lexer.cpp_tokens,start='translationUnit')
    # Parse the tokens
    print(parser.parse(tokens))