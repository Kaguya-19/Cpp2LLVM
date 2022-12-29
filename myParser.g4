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

ifBlocks : ifBlock elseBlock;
ifBlock :  If   LeftParen condition RightParen   LeftBrace body  RightBrace;
elseBlock :  Else   LeftBrace body  RightBrace;

condition :  expr;

forBlock :  For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen   LeftBrace body  RightBrace
            |For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen  Semi;
for1Block :  Identifier  Assign  expr ( Comma for1Block)?;
for3Block : Identifier  Assign  expr ( Comma for3Block)?;

returnBlock : Return IntegerLiteral?  Semi |Return Identifier?  Semi;


primaryExpr: Identifier | IntegerLiteral | FloatingLiteral | CharacterLiteral | StringLiteral | LeftParen expr RightParen;
postfixExpr: primaryExpr | arrayItem | func;
unaryExpr: postfixExpr | Minus multExpr | Not multExpr;
castExpr: unaryExpr ;
multExpr: castExpr | multExpr Star castExpr | multExpr Div castExpr;
addExpr: multExpr | addExpr Plus multExpr | addExpr Minus multExpr;
relaExpr: addExpr | relaExpr Less addExpr | relaExpr Greater addExpr | relaExpr LessEqual addExpr | relaExpr GreaterEqual addExpr;
equalExpr: relaExpr | equalExpr Equal relaExpr | equalExpr NotEqual relaExpr;
andExpr: equalExpr | andExpr AndAnd equalExpr;
orExpr: andExpr | orExpr OrOr andExpr;
// assignExpr: orExpr | unaryExpr Assign assignExpr;
expr: orExpr;

// expr
//     :  LeftParen  expr  RightParen                
//     |  Not expr                  
//     | expr  Star  expr
//     | expr  Div  expr
//     | expr  Mod  expr
//     | expr  Plus  expr   
//     | expr  Minus  expr
//     | expr  Equal expr 
//     | expr  NotEqual expr
//     | expr  Less expr
//     | expr  Greater expr
//     | expr  LessEqual expr
//     | expr  GreaterEqual expr
//     | expr AndAnd expr             
//     | expr OrOr expr                      
//     | Minus? IntegerLiteral                                   
//     | Minus? FloatingLiteral          
//     | CharacterLiteral                       
//     | StringLiteral                       
//     | Identifier
//     | arrayItem                         
//     | func;

typeDef : Int | Char | Double;

arrayItem : Identifier  LeftBracket  expr  RightBracket ;

func : strlenRef|printfRef |getsRef| funcRef;

strlenRef : Strlen  LeftParen  Identifier  RightParen ;

printfRef : Printf  LeftParen  StringLiteral ( Comma expr)*  RightParen 
            |Printf  LeftParen  Identifier ( Comma expr)*  RightParen;

getsRef : Gets  LeftParen  Identifier  RightParen ;

funcRef : Identifier  LeftParen (primaryExpr( Comma primaryExpr)*)?  RightParen;
