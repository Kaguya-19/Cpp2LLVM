translationUnit :(InitialBlock|ArrayInitBlock|FunctionDefine)*;


FunctionDefine : TypeDef Identifier  LeftParen  Params  RightParen  LeftBrace FuncBody  RightBrace;


Params : (Param  ( Comma Param )*)?;
Param  : TypeDef Identifier;


FuncBody : Body returnBlock;

Body : (Blocks | FuncRef Semi)*;

Blocks : InitialBlock | ArrayInitBlock | AssignBlock | ifBlocks| forBlock | returnBlock;

InitialBlock : TypeDef Identifier ( Assign  Expr)? ( Comma Identifier ( Assign  Expr)?)*  Semi;
ArrayInitBlock : TypeDef Identifier  LeftBracket  IntgerLiteral  RightBracket  Semi; 


AssignBlock : arrayItem  Assign  Expr  Semi | Identifier  Assign  Expr  Semi;


ifBlocks : ifBlock elifBlock* elseBlock?;
ifBlock :  If   LeftParen condition RightParen   LeftBrace Body  RightBrace;
elifBlock :  Else   If   LeftParen  condition  RightParen   LeftBrace Body  RightBrace;
elseBlock :  Else   LeftBrace Body  RightBrace;

condition :  Expr;

forBlock :  For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen   LeftBrace Body  RightBrace
            |For   LeftParen  for1Block?   Semi condition  Semi for3Block?  RightParen  Semi;
for1Block :  Identifier  Assign  Expr ( Comma for1Block)?;
for3Block : Identifier  Assign  Expr ( Comma for3Block)?;

returnBlock : Return IntgerLiteral?  Semi |Return Identifier?  Semi;

Expr
    :  LeftParen  Expr  RightParen                
    |  Not Expr                  
    | Expr  Star  Expr
    | Expr  Div  Expr
    | Expr  Mod  Expr
    | Expr  Plus  Expr   
    | Expr  Minus  Expr
    | Expr  Equal Expr 
    | Expr  NotEqual Expr
    | Expr  Less Expr
    | Expr  Greater Expr
    | Expr  LessEqual Expr
    | Expr  GreaterEqual Expr
    | Expr AndAnd Expr             
    | Expr OrOr Expr                      
    | Minus? IntgerLiteral                                   
    | Minus? FloatingLiteral          
    | CharacterLiteral                       
    | StringLiteral                       
    | Identifier                         
    | FuncRef;

TypeDef : Int| Double | Char | String ;

ArrayRef : Identifier  LeftBracket  IntgerLiteral  RightBracket ; 

arrayItem : Identifier  LeftBracket  Expr  RightBracket ;

FuncRef : StrlenRef|PrintfRef |GetsRef| FuncRef;

StrlenRef : Strlen  LeftParen  Identifier  RightParen ;

PrintfRef : Printf  LeftParen  StringLiteral ( Comma Expr)*  RightParen 
            |Printf  LeftParen  Identifier ( Comma Expr)*  RightParen;

GetsRef : Gets  LeftParen  Identifier  RightParen ;

FuncRef : Identifier  LeftParen ((Literal|Identifier)( Comma(Literal|Identifier))*)?  RightParen ;

Literal : IntgerLiteral | FloatingLiteral | CharacterLiteral | StringLiteral;

IntgerLiteral : HexadecimalLiteral | DecimalLiteral | OctalLiteral | BinaryLiteral;