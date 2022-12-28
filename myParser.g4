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
parser grammar myParser;
options {
	tokenVocab = CPP14Lexer;
}
/*Basic concepts*/
/*Expressions*/

translationUnit :(initialBlock|arrayInitBlock|functionDefine)*;

functionDefine : typeDef Identifier  LeftParen  params?  RightParen  LeftBrace funcBody  RightBrace;

params : param |param( Comma param)+;
param  : typeDef Identifier;

funcBody : body returnBlock;

body : (blocks | func Semi)*;

blocks : initialBlock | arrayInitBlock | assignBlock | ifBlocks| forBlock;

initialBlock : typeDef Identifier ( Assign  expr)? ( Comma Identifier ( Assign  expr)?)*  Semi;
arrayInitBlock : typeDef Identifier  LeftBracket  IntegerLiteral  RightBracket  Semi; 


assignBlock : arrayItem  Assign  expr  Semi | Identifier  Assign  expr  Semi;

ifBlocks : ifBlock elifBlock* elseBlock?;
ifBlock :  If   LeftParen condition RightParen   LeftBrace body  RightBrace;
elifBlock :  Else   If   LeftParen  condition  RightParen   LeftBrace body  RightBrace;
elseBlock :  Else   LeftBrace body  RightBrace;

condition :  expr;

forBlock :  For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen   LeftBrace body  RightBrace
            |For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen  Semi;
for1Block :  Identifier  Assign  expr ( Comma for1Block)?;
for3Block : Identifier  Assign  expr ( Comma for3Block)?;

returnBlock : Return IntegerLiteral?  Semi |Return Identifier?  Semi;

expr
    :  LeftParen  expr  RightParen                
    |  Not expr                  
    | expr  Star  expr
    | expr  Div  expr
    | expr  Mod  expr
    | expr  Plus  expr   
    | expr  Minus  expr
    | expr  Equal expr 
    | expr  NotEqual expr
    | expr  Less expr
    | expr  Greater expr
    | expr  LessEqual expr
    | expr  GreaterEqual expr
    | expr AndAnd expr             
    | expr OrOr expr                      
    | Minus? IntegerLiteral                                   
    | Minus? FloatingLiteral          
    | CharacterLiteral                       
    | StringLiteral                       
    | Identifier
    | arrayItem                         
    | func;

typeDef : Int| Double | Char | Float;

arrayItem : Identifier  LeftBracket  expr  RightBracket ;

func : strlenRef|printfRef |getsRef| funcRef;

strlenRef : Strlen  LeftParen  Identifier  RightParen ;

printfRef : Printf  LeftParen  StringLiteral ( Comma expr)*  RightParen 
            |Printf  LeftParen  Identifier ( Comma expr)*  RightParen;

getsRef : Gets  LeftParen  Identifier  RightParen ;

funcRef : Identifier  LeftParen ((literal|Identifier)( Comma(literal|Identifier))*)?  RightParen ;

literal : IntegerLiteral | FloatingLiteral | CharacterLiteral | StringLiteral;

// IntegerLiteral : HexadecimalLiteral | DecimalLiteral | OctalLiteral | BinaryLiteral;
