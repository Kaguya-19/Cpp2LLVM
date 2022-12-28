grammar C;

// Parser rules

compilationUnit : externalDeclaration* EOF ;

externalDeclaration
    : functionDefinition
    | declaration
    | ';' // stray ;
    ;

constant
    : IntegerConstant
    | FloatingConstant
    | CharacterConstant
    ;

primaryExpression
    : Identifier
    | StringLiteral+
    | constant
    | '(' expression ')'
    ;

postfixExpression
    : primaryExpression                                   # postfixExpression_1
    | postfixExpression '[' expression ']'                # postfixExpression_2
    | postfixExpression '(' argumentExpressionList? ')'   # postfixExpression_3
    | postfixExpression '.' Identifier                    # postfixExpression_4
    | postfixExpression '->' Identifier                   # postfixExpression_5
    | postfixExpression '++'                              # postfixExpression_6
    | postfixExpression '--'                              # postfixExpression_7
    ;

argumentExpressionList
    : assignmentExpression
    | argumentExpressionList ',' assignmentExpression
    ;

unaryExpression
    : postfixExpression                       # unaryExpression_1
    | '++' unaryExpression                    # unaryExpression_2
    | '--' unaryExpression                    # unaryExpression_3
    | unaryOperator castExpression            # unaryExpression_4
    | 'sizeof' unaryExpression                # unaryExpression_5
    | 'sizeof' '(' typeName ')'               # unaryExpression_6
    ;

unaryOperator
    : '&' | '*' | '+' | '-' | '~' | '!'
    ;

castExpression
    : '(' typeName ')' castExpression         # castExpression_1
    | unaryExpression                         # castExpression_2
    ;

multiplicativeExpression
    : castExpression                                  # multiplicativeExpression_1
    | multiplicativeExpression '*' castExpression     # multiplicativeExpression_2
    | multiplicativeExpression '/' castExpression     # multiplicativeExpression_3
    | multiplicativeExpression '%' castExpression     # multiplicativeExpression_4
    ;

additiveExpression
    : multiplicativeExpression                            # additiveExpression_1
    | additiveExpression '+' multiplicativeExpression     # additiveExpression_2
    | additiveExpression '-' multiplicativeExpression     # additiveExpression_3
    ;

shiftExpression
    : additiveExpression                          # shiftExpression_1
    | shiftExpression '<<' additiveExpression     # shiftExpression_2
    | shiftExpression '>>' additiveExpression     # shiftExpression_3
    ;

relationalExpression
    : shiftExpression                                 # relationalExpression_1
    | relationalExpression '<' shiftExpression        # relationalExpression_2
    | relationalExpression '>' shiftExpression        # relationalExpression_3
    | relationalExpression '<=' shiftExpression       # relationalExpression_4
    | relationalExpression '>=' shiftExpression       # relationalExpression_5
    ;

equalityExpression
    : relationalExpression                            # equalityExpression_1
    | equalityExpression '==' relationalExpression    # equalityExpression_2
    | equalityExpression '!=' relationalExpression    # equalityExpression_3
    ;

andExpression
    : equalityExpression                              # andExpression_1
    | andExpression '&' equalityExpression            # andExpression_2
    ;

exclusiveOrExpression
    : andExpression                                   # exclusiveOrExpression_1
    | exclusiveOrExpression '^' andExpression         # exclusiveOrExpression_2
    ;

inclusiveOrExpression
    : exclusiveOrExpression                           # inclusiveOrExpression_1
    | inclusiveOrExpression '|' exclusiveOrExpression # inclusiveOrExpression_2
    ;

logicalAndExpression
    : inclusiveOrExpression
    | logicalAndExpression '&&' inclusiveOrExpression
    ;

logicalOrExpression
    : logicalAndExpression
    | logicalOrExpression '||' logicalAndExpression
    ;

conditionalExpression
    : logicalOrExpression ('?' expression ':' conditionalExpression)?
    ;

assignmentExpression
    : conditionalExpression                                       # assignmentExpression_1
    | unaryExpression assignmentOperator assignmentExpression     # assignmentExpression_2
    ;

assignmentOperator
    : '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
    ;

expression
    : assignmentExpression                    # expression_1
    | expression ',' assignmentExpression     # expression_2
    ;

constantExpression
    : conditionalExpression
    ;

statement
    : compoundStatement
    | expressionStatement
    | selectionStatement
    | jumpStatement
    | iterationStatement
    ;

compoundStatement : '{' blockItemList? '}' ;

blockItemList : (statement | declaration)+ ;

expressionStatement : expression? ';' ;

selectionStatement
    : 'if' '(' expression ')' statement ('else' statement)?
    | 'switch' '(' expression ')' statement
    ;

iterationStatement
    : 'while' '(' expression ')' statement
    | 'do' statement 'while' '(' expression ')' ';'
    | 'for' '(' first=forInitialization ';' second=forExpression? ';' third=forExpression? ')' statement
    ;

forExpression : expression ;

forInitialization : expression? | forDeclaration ;

jumpStatement
    : 'continue' ';'
    | 'break' ';'
    | 'return' expression? ';'
    ;

// The full definition is quite complex
typeName : typeSpecifier ;

pointer
    : qualifiedPointer
    | pointer qualifiedPointer
    ;

qualifiedPointer : '*' typeQualifierList? ;

typeQualifierList : typeQualifier+ ;

typeQualifier : 'const' | 'volatile' ;

// simplified to typeSpecifier
functionDefinition
    : declarationSpecifiers declarator compoundStatement
    ;

declaration : forDeclaration ';' ;

// It may be empty. Is it right?
forDeclaration
    : declarationSpecifiers initDeclaratorList
    | declarationSpecifiers
    ;

initDeclaratorList
    : initDeclarator
    | initDeclaratorList ',' initDeclarator
    ;

initDeclarator
    : declarator
    | declarator '=' initializer
    ;

initializer
    : assignmentExpression
    | '{' initializerList '}'
    | '{' initializerList ',' '}'
    ;

initializerList
    : initializer
    | initializerList ',' initializer
    ;

declarationSpecifiers: declarationSpecifier+ ;

declarationSpecifier
    : storageClassSpecifier
    | typeSpecifier
    | typeQualifier
    | functionSpecifier
    ;

storageClassSpecifier
    : 'typedef'
    | 'extern'
    | 'static'
    ;

functionSpecifier
    :   ('inline'
    |   '__stdcall'
    |   '__cdecl')
    |   '__declspec' '(' Identifier ')'
    ;

parameterTypeList
    :
    | parameterList
    | parameterList ',' '...'
    ;

parameterList
    : parameterDeclaration
    | parameterList ',' parameterDeclaration
    ;

parameterDeclaration
    : declarationSpecifiers declarator
    ;

directDeclarator
    : Identifier                                      # directDeclarator_1
    | '(' declarator ')'                              # directDeclarator_2
    | directDeclarator '[' assignmentExpression? ']'  # directDeclarator_3
    | directDeclarator '(' parameterTypeList ')'      # directDeclarator_4
    ;

declarator
    : pointer? directDeclarator
    ;

typeSpecifier
    : primitiveType           # typeSpecifier_1
    | typedefName             # typeSpecifier_2
    | typeSpecifier pointer   # typeSpecifier_3
    | structSpecifier         # typeSpecifier_4
    | enumSpecifier           # typeSpecifier_5
    ;

structSpecifier
    :   'struct' Identifier? '{' structDeclarationList? '}' # structSpecifier_1
    |   'struct' Identifier                                 # structSpecifier_2
    ;

structDeclarationList
    :   structDeclaration
    |   structDeclarationList structDeclaration
    ;

structDeclaration
    :   declarationSpecifiers structDeclaratorList ';'
    |   declarationSpecifiers ';'
    ;

structDeclaratorList
    :   declarator
    |   structDeclaratorList ',' declarator
    ;

enumSpecifier
    :   'enum' Identifier? '{' enumeratorList '}'
    |   'enum' Identifier? '{' enumeratorList ',' '}'
    |   'enum' Identifier
    ;

enumeratorList
    :   enumerator
    |   enumeratorList ',' enumerator
    ;

enumerator
    :   Identifier
    |   Identifier '=' constantExpression
    ;

primitiveType
    : 'void'
    | 'char'
    | 'short'
    | 'int'
    | 'long' 'long'
    | 'long'
    | 'float'
    | 'double'
    | 'signed'
    | 'unsigned'
    ;

typedefName : Identifier ;

// Lexer rules

Identifier : Nondigit (Nondigit | Digit)* ;

IntegerConstant
    : [1-9] Digit*
    | '0' OctalDigit*
    | ('0x' | '0X') HexadecimalDigit+
    ;

FloatingConstant : Digit+ '.' Digit+ ;

CharacterConstant : '\'' CChar '\'' ;

StringLiteral : '"' SChar* '"' ;

fragment
Nondigit : [a-zA-Z_] ;

fragment
Digit : [0-9] ;

fragment
OctalDigit : [0-7] ;

fragment
HexadecimalDigit : [0-9a-fA-F] ;

fragment
EscapeSequence
    : SimpleEscapeSequence
    | OctalEscapeSequence
    | HexadecimalEscapeSequence
    ;

fragment
SimpleEscapeSequence
    : '\\' ['"?abfnrtv\\]
    ;

fragment
OctalEscapeSequence
    : '\\' OctalDigit
    | '\\' OctalDigit OctalDigit
    | '\\' OctalDigit OctalDigit OctalDigit
    ;

fragment
HexadecimalEscapeSequence
    : '\\x' HexadecimalDigit+
    ;

fragment
CChar
    : ~['\\\r\n]
    | EscapeSequence
    ;

fragment
SChar
    : ~["\\\r\n]
    | EscapeSequence
    ;

Whitespace : [ \t]+ -> skip ;

Newline : ('\r' '\n'? | '\n') -> skip ;

BlockComment : '/*' .*? '*/' -> skip ;

LineComment : '//' ~[\r\n]* -> skip ;
