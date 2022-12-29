translation_unit
	: external_declaration
	| translation_unit external_declaration
	;

primary_expression
	: Identifier
	| IntegerLiteral
    | FloatingLiteral
    | CharacterLiteral
	| StringLiteral
	| LeftParen expression RightParen
	;

postfix_expression
	: primary_expression
	| postfix_expression LeftBracket expression RightBracket
	| postfix_expression LeftParen RightParen
	| postfix_expression LeftParen argument_expression_list RightParen;

argument_expression_list
	: assignment_expression
	| argument_expression_list Comma assignment_expression
	;

unary_expression
	: postfix_expression
	| Minus cast_expression
	;

cast_expression
	: unary_expression
	| LeftParen type_name RightParen cast_expression
	;

multiplicative_expression
	: cast_expression
	| multiplicative_expression Star cast_expression
	| multiplicative_expression Div cast_expression
	;

additive_expression
	: multiplicative_expression
	| additive_expression Plus multiplicative_expression
	| additive_expression Minus multiplicative_expression
	;


relational_expression
	: additive_expression
	| relational_expression Less additive_expression
	| relational_expression Greater additive_expression
	| relational_expression LessEqual additive_expression
	| relational_expression GreaterEqual additive_expression
	;

equality_expression
	: relational_expression
	| equality_expression Equal relational_expression
	| equality_expression NotEqual relational_expression
	;

logical_and_expression
	: equality_expression
	| logical_and_expression AndAnd equality_expression
	;

logical_or_expression
	: logical_and_expression
	| logical_or_expression OrOr logical_and_expression
	;

assignment_expression
	: logical_or_expression
	| unary_expression Assign assignment_expression
	;

expression
	: assignment_expression
	| expression Comma assignment_expression
	;

constant_expression
	: logical_and_expression
	;

declaration
	: declaration_specifiers Semi
	| declaration_specifiers init_declarator_list Semi
	;

declaration_specifiers
	:  type_specifier
	| type_specifier declaration_specifiers
	;

init_declarator_list
	: init_declarator
	| init_declarator_list Comma init_declarator
	;

init_declarator
	: direct_declarator
	| direct_declarator Assign initializer
	;

type_specifier
	: Char
	| Int
	| Double
	;

specifier_qualifier_list
	: type_specifier specifier_qualifier_list
	| type_specifier
	;

direct_declarator
	: Identifier
	| LeftParen direct_declarator RightParen
	| direct_declarator LeftBracket constant_expression RightBracket
	| direct_declarator LeftBracket RightBracket
	| direct_declarator LeftParen parameter_list RightParen
	| direct_declarator LeftParen identifier_list RightParen
	| direct_declarator LeftParen RightParen
	;

parameter_list
	: parameter_declaration
	| parameter_list Comma parameter_declaration
	;

parameter_declaration
	: declaration_specifiers direct_declarator
	| declaration_specifiers direct_abstract_declarator
	| declaration_specifiers
	;

identifier_list
	: Identifier
	| identifier_list Comma Identifier
	;

type_name
	: specifier_qualifier_list
	| specifier_qualifier_list direct_abstract_declarator
	;

direct_abstract_declarator
	: LeftParen direct_abstract_declarator RightParen
	| LeftBracket RightBracket
	| LeftBracket constant_expression RightBracket
	| direct_abstract_declarator LeftBracket RightBracket
	| direct_abstract_declarator LeftBracket constant_expression RightBracket
	| LeftParen RightParen
	| LeftParen parameter_list RightParen
	| direct_abstract_declarator LeftParen RightParen
	| direct_abstract_declarator LeftParen parameter_list RightParen
	;

initializer
	: assignment_expression
	| LeftBrace initializer_list RightBrace
	| LeftBrace initializer_list Comma RightBrace
	;

initializer_list
	: initializer
	| initializer_list Comma initializer
	;

statement
	: compound_statement
	| expression_statement
	| selection_statement
	| iteration_statement
	| jump_statement
	;


compound_statement
	: LeftBrace RightBrace
	| LeftBrace statement_list RightBrace
	| LeftBrace declaration_list RightBrace
	| LeftBrace declaration_list statement_list RightBrace
	;

declaration_list
	: declaration
	| declaration_list declaration
	;

statement_list
	: statement
	| statement_list statement
	;

expression_statement
	: Semi
	| expression Semi
	;

selection_statement
	: If LeftParen expression RightParen statement Else statement
	;

iteration_statement:
	 For LeftParen expression_statement expression_statement RightParen statement
	| For LeftParen expression_statement expression_statement expression RightParen statement
	;

jump_statement
	:  Return Semi
	| Return expression Semi
	;



external_declaration
	: function_definition
	| declaration
	;

function_definition
	: declaration_specifiers direct_declarator declaration_list compound_statement
	| declaration_specifiers direct_declarator compound_statement
	| direct_declarator declaration_list compound_statement
	| direct_declarator compound_statement
	;