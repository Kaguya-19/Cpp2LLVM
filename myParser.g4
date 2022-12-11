// functionBody:
    
translationUnit: literal;

literal:
	IntegerLiteral;
	// | CharacterLiteral
	// | FloatingLiteral
	// | StringLiteral
	// | BooleanLiteral
	// | Nullptr;
	// | UserDefinedLiteral;

IntegerLiteral:
	DecimalLiteral?;
	// | OctalLiteral
	// | HexadecimalLiteral
	// | BinaryLiteral;