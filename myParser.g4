translationUnit :(InitialBlock|ArrayInitBlock|FunctionDefine)*;

FunctionDefine : TypeDef Identifier  LeftParen  Params  RightParen  LeftBrace FuncBody  RightBrace;

Params : (Param |Param( Comma Param)+)?;
Param  : TypeDef Identifier;

FuncBody : Body ReturnBlock;

Body : (Blocks | Func Semi)*;

Blocks : InitialBlock | ArrayInitBlock | AssignBlock | IfBlocks| ForBlock | ReturnBlock;

InitialBlock : TypeDef Identifier ( Assign  Expr)? ( Comma Identifier ( Assign  Expr)?)*  Semi;
ArrayInitBlock : TypeDef Identifier  LeftBracket  IntgerLiteral  RightBracket  Semi; 


AssignBlock : ArrayItem  Assign  Expr  Semi | Identifier  Assign  Expr  Semi;


IfBlocks : ifBlock ElifBlock* ElseBlock?;
ifBlock :  If   LeftParen Condition RightParen   LeftBrace Body  RightBrace;
ElifBlock :  Else   If   LeftParen  Condition  RightParen   LeftBrace Body  RightBrace;
ElseBlock :  Else   LeftBrace Body  RightBrace;

Condition :  Expr;

ForBlock :  For   LeftParen  For1Block?   Semi Condition  Semi For3Block?  RightParen   LeftBrace Body  RightBrace
            |For   LeftParen  For1Block?   Semi Condition  Semi For3Block?  RightParen  Semi;
For1Block :  Identifier  Assign  Expr ( Comma For1Block)?;
For3Block : Identifier  Assign  Expr ( Comma For3Block)?;

ReturnBlock : Return IntgerLiteral?  Semi |Return Identifier?  Semi;

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
    | Func;

TypeDef : Int| Double | Char;

ArrayRef : Identifier  LeftBracket  IntgerLiteral  RightBracket ; 

ArrayItem : Identifier  LeftBracket  Expr  RightBracket ;

Func : StrlenRef|PrintfRef |GetsRef| FuncRef;

StrlenRef : Strlen  LeftParen  Identifier  RightParen ;

PrintfRef : Printf  LeftParen  StringLiteral ( Comma Expr)*  RightParen 
            |Printf  LeftParen  Identifier ( Comma Expr)*  RightParen;

GetsRef : Gets  LeftParen  Identifier  RightParen ;

FuncRef : Identifier  LeftParen ((Literal|Identifier)( Comma(Literal|Identifier))*)?  RightParen ;

Literal : IntgerLiteral | FloatingLiteral | CharacterLiteral | StringLiteral;

IntgerLiteral : HexadecimalLiteral | DecimalLiteral | OctalLiteral | BinaryLiteral;