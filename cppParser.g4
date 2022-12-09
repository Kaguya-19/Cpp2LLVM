/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Camilo Sanchez (Camiloasc1) 2020 Martin Mirchev (Marti2203)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * ****************************************************************************
 */
parser grammar CPP14Parser;
options {
	tokenVocab = CPP14Lexer;
}
/*Basic concepts*/

translationUnit: declarationseq? EOF;
/*Expressions*/

primaryExpression:
	literal+
	| This
	| LeftParen expression RightParen
	| idExpression;

idExpression: unqualifiedId | qualifiedId;

unqualifiedId:
	Identifier
	| conversionFunctionId
	| literalOperatorId
	| templateId;

qualifiedId: nestedNameSpecifier Template? unqualifiedId;

nestedNameSpecifier:
	theTypeName? Doublecolon
	| nestedNameSpecifier (
		Identifier
		| Template? simpleTemplateId
	) Doublecolon;

postfixExpression:
	primaryExpression
	| postfixExpression LeftBracket (expression | bracedInitList) RightBracket
	| postfixExpression LeftParen expressionList? RightParen
	| (simpleTypeSpecifier | typeNameSpecifier) (
		LeftParen expressionList? RightParen
		| bracedInitList
	)
	| postfixExpression (Dot | Arrow) (
		Template? idExpression
		| pseudoDestructorName
	)
	| postfixExpression (PlusPlus | MinusMinus)
	| (
		Dynamic_cast
		| Static_cast
		| Reinterpret_cast
		| Const_cast
	) Less theTypeId Greater LeftParen expression RightParen
	| typeIdOfTheTypeId LeftParen (expression | theTypeId) RightParen;
/*
 add a middle layer to eliminate duplicated function declarations
 */

typeIdOfTheTypeId: Typeid_;

expressionList: initializerList;

pseudoDestructorName:
	nestedNameSpecifier? (theTypeName Doublecolon)? Tilde theTypeName
	| nestedNameSpecifier Template simpleTemplateId Doublecolon Tilde theTypeName;

unaryExpression:
	postfixExpression
	| (PlusPlus | MinusMinus | unaryOperator | Sizeof) unaryExpression
	| Sizeof (
		LeftParen theTypeId RightParen
		| Ellipsis LeftParen Identifier RightParen
	)
	| Alignof LeftParen theTypeId RightParen
	| newExpression
	| deleteExpression;

unaryOperator: Or | Star | And | Plus | Tilde | Minus | Not;

newExpression:
	Doublecolon? New newPlacement? (
		newTypeId
		| (LeftParen theTypeId RightParen)
	) newInitializer?;

newPlacement: LeftParen expressionList RightParen;

newTypeId: typeSpecifierSeq newDeclarator?;

newDeclarator:
	pointerOperator newDeclarator?
	| noPointerNewDeclarator;

noPointerNewDeclarator:
	LeftBracket expression RightBracket
	| noPointerNewDeclarator LeftBracket constantExpression RightBracket;

newInitializer:
	LeftParen expressionList? RightParen
	| bracedInitList;

deleteExpression:
	Doublecolon? Delete (LeftBracket RightBracket)? castExpression;

castExpression:
	unaryExpression
	| LeftParen theTypeId RightParen castExpression;

pointerMemberExpression:
	castExpression ((DotStar | ArrowStar) castExpression)*;

multiplicativeExpression:
	pointerMemberExpression (
		(Star | Div | Mod) pointerMemberExpression
	)*;

additiveExpression:
	multiplicativeExpression (
		(Plus | Minus) multiplicativeExpression
	)*;


relationalExpression:
	additiveExpression (
		(Less | Greater | LessEqual | GreaterEqual) additiveExpression
	)*;

equalityExpression:
	relationalExpression (
		(Equal | NotEqual) relationalExpression
	)*;

andExpression: equalityExpression (And equalityExpression)*;

exclusiveOrExpression: andExpression (Caret andExpression)*;

inclusiveOrExpression:
	exclusiveOrExpression (Or exclusiveOrExpression)*;

logicalAndExpression:
	inclusiveOrExpression (AndAnd inclusiveOrExpression)*;

logicalOrExpression:
	logicalAndExpression (OrOr logicalAndExpression)*;

conditionalExpression:
	logicalOrExpression (
		Question expression Colon assignmentExpression
	)?;

assignmentExpression:
	conditionalExpression
	| logicalOrExpression assignmentOperator initializerClause;

assignmentOperator:
	Assign
	| StarAssign
	| DivAssign
	| ModAssign
	| PlusAssign
	| MinusAssign
	| AndAssign
	| XorAssign
	| OrAssign;

expression: assignmentExpression (Comma assignmentExpression)*;

constantExpression: conditionalExpression;
/*Statements*/

statement:
	labeledStatement
	| declarationStatement
	| ( expressionStatement
		| compoundStatement
		| selectionStatement
		| iterationStatement
		| jumpStatement
	);

labeledStatement:
	( Identifier
		| Case constantExpression
		| Default
	) Colon statement;

expressionStatement: expression? Semi;

compoundStatement: LeftBrace statementSeq? RightBrace;

statementSeq: statement+;

selectionStatement:
	If LeftParen condition RightParen statement (Else statement)?
	| Switch LeftParen condition RightParen statement;

condition:
	expression
	| declSpecifierSeq declarator (
		Assign initializerClause
		| bracedInitList
	);

iterationStatement:
	While LeftParen condition RightParen statement
	| Do statement While LeftParen expression RightParen Semi
	| For LeftParen (
		forInitStatement condition? Semi expression?
		| forRangeDeclaration Colon forRangeInitializer
	) RightParen statement;

forInitStatement: expressionStatement | simpleDeclaration;

forRangeDeclaration:
	declSpecifierSeq declarator;

forRangeInitializer: expression | bracedInitList;

jumpStatement:
	(
		Break
		| Continue
		| Return (expression | bracedInitList)?
		| Goto Identifier
	) Semi;

declarationStatement: blockDeclaration;
/*Declarations*/

declarationseq: declaration+;

declaration:
	blockDeclaration
	| functionDefinition
	| templateDeclaration
	| emptyDeclaration;

blockDeclaration:
	simpleDeclaration
	| staticAssertDeclaration;

simpleDeclaration:
	declSpecifierSeq? initDeclaratorList? Semi
	| declSpecifierSeq? initDeclaratorList Semi;

staticAssertDeclaration:
	Static_assert LeftParen constantExpression Comma StringLiteral RightParen Semi;

emptyDeclaration: Semi;

declSpecifier:
	Static
	| typeSpecifier
	| Typedef
	| Constexpr;
	
declSpecifierSeq: declSpecifier+?;

typedefName: Identifier;

typeSpecifier:
	trailingTypeSpecifier
	| classSpecifier;

trailingTypeSpecifier:
	simpleTypeSpecifier
	| typeNameSpecifier
	| cvQualifier;

typeSpecifierSeq: typeSpecifier+;

trailingTypeSpecifierSeq:
	trailingTypeSpecifier+;

simpleTypeLengthModifier:
	Short
	| Long;
	
simpleTypeSignednessModifier:
	Unsigned
	| Signed;

simpleTypeSpecifier:
	nestedNameSpecifier? theTypeName
	| nestedNameSpecifier Template simpleTemplateId
	| simpleTypeSignednessModifier
	| simpleTypeSignednessModifier? simpleTypeLengthModifier+
	| simpleTypeSignednessModifier? Char
	| simpleTypeSignednessModifier? Char16
	| simpleTypeSignednessModifier? Char32
	| simpleTypeSignednessModifier? Wchar
	| Bool
	| simpleTypeSignednessModifier? simpleTypeLengthModifier* Int
	| Float
	| simpleTypeLengthModifier? Double
	| Void
	| Auto;

theTypeName:
    typedefName
	| simpleTemplateId;

/*Declarators*/

initDeclaratorList: initDeclarator (Comma initDeclarator)*;

initDeclarator: declarator initializer?;

declarator:
	pointerDeclarator
	| noPointerDeclarator parametersAndQualifiers trailingReturnType;

pointerDeclarator: (pointerOperator Const?)* noPointerDeclarator;

noPointerDeclarator:
	declaratorid
	| noPointerDeclarator (
		parametersAndQualifiers
		| LeftBracket constantExpression? RightBracket
	)
	| LeftParen pointerDeclarator RightParen;

parametersAndQualifiers:
	LeftParen parameterDeclarationClause? RightParen cvqualifierseq? refqualifier?;

trailingReturnType:
	Arrow trailingTypeSpecifierSeq abstractDeclarator?;

pointerOperator:
	nestedNameSpecifier? Star Const?;

refqualifier: And | AndAnd;

declaratorid: Ellipsis? idExpression;

theTypeId: typeSpecifierSeq abstractDeclarator?;

abstractDeclarator:
	pointerAbstractDeclarator
	| noPointerAbstractDeclarator? parametersAndQualifiers trailingReturnType
	| abstractPackDeclarator;

pointerAbstractDeclarator:
	noPointerAbstractDeclarator
	| pointerOperator+ noPointerAbstractDeclarator?;

noPointerAbstractDeclarator:
	noPointerAbstractDeclarator (
		parametersAndQualifiers
		| noPointerAbstractDeclarator LeftBracket constantExpression? RightBracket
	)
	| parametersAndQualifiers
	| LeftBracket constantExpression? RightBracket
	| LeftParen pointerAbstractDeclarator RightParen;

abstractPackDeclarator:
	pointerOperator* noPointerAbstractPackDeclarator;

noPointerAbstractPackDeclarator:
	noPointerAbstractPackDeclarator (
		parametersAndQualifiers
		| LeftBracket constantExpression? RightBracket
	)
	| Ellipsis;

parameterDeclarationClause:
	parameterDeclarationList (Comma? Ellipsis)?;

parameterDeclarationList:
	parameterDeclaration (Comma parameterDeclaration)*;

parameterDeclaration:
	declSpecifierSeq (
		(declarator | abstractDeclarator?) (
			Assign initializerClause
		)?
	);

functionDefinition:
	declSpecifierSeq? declarator functionBody;

functionBody:
	constructorInitializer? compoundStatement
	| Assign (Default | Delete) Semi;

initializer:
	braceOrEqualInitializer
	| LeftParen expressionList RightParen;

braceOrEqualInitializer:
	Assign initializerClause
	| bracedInitList;

initializerClause: assignmentExpression | bracedInitList;

initializerList:
	initializerClause Ellipsis? (
		Comma initializerClause Ellipsis?
	)*;

bracedInitList: LeftBrace (initializerList Comma?)? RightBrace;

/*Overloading*/

literalOperatorId:
	Operator (
		StringLiteral Identifier
		| UserDefinedStringLiteral
	);
/*Templates*/

templateDeclaration:
	Template Less templateparameterList Greater declaration;

templateparameterList:
	templateParameter (Comma templateParameter)*;

templateParameter: typeParameter | parameterDeclaration;

typeParameter:
	(
		(Template Less templateparameterList Greater)? Class
		| Typename_
	) ((Ellipsis? Identifier?) | (Identifier? Assign theTypeId));

simpleTemplateId:
	templateName Less templateArgumentList? Greater;

templateId:
	simpleTemplateId
	| literalOperatorId Less templateArgumentList? Greater;

templateName: Identifier;

templateArgumentList:
	templateArgument Ellipsis? (Comma templateArgument Ellipsis?)*;

templateArgument: theTypeId | constantExpression | idExpression;

typeNameSpecifier:
	Typename_ nestedNameSpecifier (
		Identifier
		| Template? simpleTemplateId
	);

/*Lexer*/

theOperator:@
	New (LeftBracket RightBracket)?
	| Delete (LeftBracket RightBracket)?
	| Plus
	| Minus
	| Star
	| Div
	| Mod
	| Caret
	| And
	| Or
	| Tilde
	| Not
	| Assign
	| Greater
	| Less
	| GreaterEqual
	| PlusAssign
	| MinusAssign
	| StarAssign
	| ModAssign
	| XorAssign
	| AndAssign
	| OrAssign
	| Less Less
	| Greater Greater
	| Equal
	| NotEqual
	| LessEqual
	| AndAnd
	| OrOr
	| PlusPlus
	| MinusMinus
	| Comma
	| ArrowStar
	| Arrow
	| LeftParen RightParen
	| LeftBracket RightBracket;

literal:
	IntegerLiteral
	| CharacterLiteral
	| FloatingLiteral
	| StringLiteral
	| BooleanLiteral
	| PointerLiteral
	| UserDefinedLiteral;
	