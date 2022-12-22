translationUnit :mFunction;

//结构体
mStructDef : mStruct  LeftBrace (structParam)+  RightBrace Semi;

//结构体中参数
structParam : (mType|mStruct) (Identifier|mArray) ( Comma (Identifier|mArray))*  Semi;

//函数
mFunction : (mType|Void|mStruct) Identifier  LeftParen  params  RightParen   LeftBrace funcBody  RightBrace;

//函数参数
params : (param ( Comma param)*)?;
param : mType Identifier;

//函数体
funcBody : body returnBlock;

//语句块/函数快
body : (block | func Semi)*;

//语句块
block : initialBlock | arrayInitBlock | structInitBlock | assignBlock | ifBlocks | whileBlock | forBlock | returnBlock;

//初始化语句
initialBlock : (mType) Identifier ( Assign  expr)? ( Comma Identifier ( Assign  expr)?)*  Semi;
arrayInitBlock : mType Identifier  LeftBracket  IntgerLiteral  RightBracket  Semi; 
structInitBlock : mStruct (Identifier|mArray) Semi;


//赋值语句
assignBlock : ((arrayItem|Identifier|structMember)  Assign )+  expr  Semi;


//if 语句
ifBlocks : ifBlock (elifBlock)* (elseBlock)?;
ifBlock :  If   LeftParen condition RightParen   LeftBrace body  RightBrace;
elifBlock :  Else   If   LeftParen  condition  RightParen   LeftBrace body  RightBrace;
elseBlock :  Else   LeftBrace body  RightBrace;

condition :  expr;

//while 语句
whileBlock :  While   LeftParen  condition  RightParen   LeftBrace body  RightBrace;

//for 语句
forBlock :  For   LeftParen  for1Block   Semi condition  Semi for3Block  RightParen  ( LeftBrace body  RightBrace| Semi);
for1Block :  Identifier  Assign  expr ( Comma for1Block)?|;
for3Block : Identifier  Assign  expr ( Comma for3Block)?|;

//return 语句
returnBlock : Return (IntgerLiteral|Identifier)?  Semi;

expr
    :  LeftParen  expr  RightParen                
    |  Not expr                  
    | expr  (Star | Div | Mod) expr    
    | expr  (Plus |  Minus ) expr   
    | expr  ( Equal  | NotEqual | Less | LessEqual | Greater | GreaterEqual) expr 
    | expr AndAnd expr             
    | expr OrOr expr             
    | arrayItem                  
    | structMember              
    | (  Minus )? IntgerLiteral                                   
    | (  Minus )? FloatingLiteral          
    | CharacterLiteral                       
    | StringLiteral                       
    | Identifier                         
    | func                                                           
    ;

mType : Int| Double | Char | String ;

mArray : Identifier  LeftBracket  IntgerLiteral  RightBracket ; 

mStruct : Struct Identifier;

structMember: (Identifier | arrayItem)Dot(Identifier | arrayItem);

arrayItem : Identifier  LeftBracket  expr  RightBracket ;


//函数
func : (strlenFunc | atoiFunc | printfFunc | scanfFunc | getsFunc | selfDefinedFunc);

//strlen
strlenFunc : Strlen  LeftParen  Identifier  RightParen ;

//atoi
atoiFunc : Atoi  LeftParen  Identifier  RightParen  ;

//printf
printfFunc 
    : Printf  LeftParen  (StringLiteral | Identifier) ( Comma expr)*  RightParen ;

//scanf
scanfFunc : Scanf  LeftParen  StringLiteral ( Comma(And)?(Identifier|arrayItem|structMember))*  RightParen ;

//gets
getsFunc : Gets  LeftParen  Identifier  RightParen ;

//Selfdefined
selfDefinedFunc : Identifier  LeftParen ((argument|Identifier)( Comma(argument|Identifier))*)?  RightParen ;

argument : IntgerLiteral | FloatingLiteral | CharacterLiteral | StringLiteral;

IntgerLiteral : HexadecimalLiteral | DecimalLiteral | OctalLiteral | BinaryLiteral;