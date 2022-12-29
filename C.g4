primaryExpression
    :   Identifier
    |   IntegerLiteral
    |   FloatingLiteral
    |   CharacterLiteral
    |   StringLiteral+
    |   LeftParen expression RightParen
    ;

postfixExpression
    :
    primaryExpression
    (LeftBracket expression RightBracket
    | LeftParen argumentExpressionList? RightParen
    )*
    ;

argumentExpressionList
    :   assignmentExpression (',' assignmentExpression)*
    ;

unaryExpression
    :
    postfixExpression
    |   unaryOperator castExpression
    ;

unaryOperator
    :    Minus| Not
    ;

castExpression
    :    unaryExpression
    |   IntegerLiteral // for
    ;

multiplicativeExpression
    :   castExpression ((Star|Div) castExpression)*
    ;

additiveExpression
    :   multiplicativeExpression (('+'|Minus) multiplicativeExpression)*
    ;

relationalExpression
    :   additiveExpression (('<'|'>'|'<='|'>=') additiveExpression)*
    ;

equalityExpression
    :   relationalExpression (('=='| '!=') relationalExpression)*
    ;

logicalAndExpression
    :   equalityExpression ('&&' equalityExpression)*
    ;

logicalOrExpression
    :   logicalAndExpression ( '||' logicalAndExpression)*
    ;


assignmentExpression
    :   logicalOrExpression
    |   unaryExpression '=' assignmentExpression
    |   IntegerLiteral // for
    ;

expression
    :   assignmentExpression (',' assignmentExpression)*
    ;

constantExpression
    :   logicalOrExpression
    ;

declaration
    :   declarationSpecifiers initDeclaratorList? ';'
    ;

declarationSpecifiers
    :   declarationSpecifier+
    ;

declarationSpecifiers2
    :   declarationSpecifier+
    ;

declarationSpecifier
    :   typeSpecifier
    ;

initDeclaratorList
    :   initDeclarator (',' initDeclarator)*
    ;

initDeclarator
    :   declarator ('=' initializer)?
    ;


typeSpecifier
    :   'char'
    |   'int'
    |   'double'
    ;

declarator
    :   directDeclarator
    ;

directDeclarator
    :   Identifier
    |   LeftParen declarator RightParen
    |   directDeclarator LeftBracket  assignmentExpression? RightBracket
    |   directDeclarator LeftParen parameterTypeList RightParen 
    |   directDeclarator LeftParen identifierList? RightParen
    ;

parameterTypeList
    :   parameterList (',' '...')?
    ;

parameterList
    :   parameterDeclaration (',' parameterDeclaration)*
    ;

parameterDeclaration
    :   declarationSpecifiers declarator
    |   declarationSpecifiers2 abstractDeclarator?
    ;

identifierList
    :   Identifier (',' Identifier)*
    ;


abstractDeclarator
    :   directAbstractDeclarator
    ;

directAbstractDeclarator
    :   LeftParen abstractDeclarator RightParen
    |   LeftBracket assignmentExpression? RightBracket
    |   LeftParen parameterTypeList? RightParen 
    |   directAbstractDeclarator LeftBracket  assignmentExpression? RightBracket
    |   directAbstractDeclarator LeftParen parameterTypeList? RightParen
    ;

initializer
    :   assignmentExpression
    |   '{' initializerList ','? '}'
    ;

initializerList
    :   designation? initializer (',' designation? initializer)*
    ;

designation
    :   designatorList '='
    ;

designatorList
    :   designator+
    ;

designator
    :   LeftBracket constantExpression RightBracket
    ;



statement
    :   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    ;


compoundStatement
    :   '{' blockItemList? '}'
    ;

blockItemList
    :   blockItem+
    ;

blockItem
    :   statement
    |   declaration
    ;

expressionStatement
    :   expression? ';'
    ;

selectionStatement
    :   'if' LeftParen expression RightParen statement ('else' statement)?
    ;

iterationStatement
    :   For LeftParen forCondition RightParen statement
    ;


forCondition
	:   (forDeclaration | expression?) ';' forExpression? ';' forExpression?
	;

forDeclaration
    :   declarationSpecifiers initDeclaratorList?
    ;

forExpression
    :   assignmentExpression (',' assignmentExpression)*
    ;

jumpStatement
    :   
     'return' expression? ';'
    ;


translationUnit
    :   externalDeclaration+
    ;

externalDeclaration
    :   functionDefinition
    |   declaration
    |   ';' // stray ;
    ;

functionDefinition
    :   declarationSpecifiers? declarator declarationList? compoundStatement
    ;

declarationList
    :   declaration+
    ;