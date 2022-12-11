// Generated from d:\Working\Program\python\Cpp2LLVM\Cpp2LLVM\myParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class myParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IntegerLiteral=1, CharacterLiteral=2, FloatingLiteral=3, StringLiteral=4, 
		BooleanLiteral=5, PointerLiteral=6, MultiLineMacro=7, Directive=8, Alignas=9, 
		Alignof=10, Asm=11, Auto=12, Bool=13, Break=14, Case=15, Catch=16, Char=17, 
		Class=18, Const=19, Constexpr=20, Const_cast=21, Continue=22, Decltype=23, 
		Default=24, Delete=25, Do=26, Double=27, Dynamic_cast=28, Else=29, Enum=30, 
		Explicit=31, Export=32, Extern=33, False_=34, Final=35, Float=36, For=37, 
		Friend=38, Goto=39, If=40, Inline=41, Int=42, Long=43, Mutable=44, Namespace=45, 
		New=46, Noexcept=47, Nullptr=48, Operator=49, Override=50, Private=51, 
		Protected=52, Public=53, Register=54, Reinterpret_cast=55, Return=56, 
		Short=57, Signed=58, Sizeof=59, Static=60, Static_assert=61, Static_cast=62, 
		Struct=63, Switch=64, Template=65, This=66, Thread_local=67, Throw=68, 
		True_=69, Try=70, Typedef=71, Typeid_=72, Typename_=73, Union=74, Unsigned=75, 
		Using=76, Virtual=77, Void=78, Volatile=79, Wchar=80, While=81, LeftParen=82, 
		RightParen=83, LeftBracket=84, RightBracket=85, LeftBrace=86, RightBrace=87, 
		Plus=88, Minus=89, Star=90, Div=91, Mod=92, Caret=93, And=94, Or=95, Tilde=96, 
		Not=97, Assign=98, Less=99, Greater=100, PlusAssign=101, MinusAssign=102, 
		StarAssign=103, DivAssign=104, ModAssign=105, XorAssign=106, AndAssign=107, 
		OrAssign=108, LeftShiftAssign=109, RightShiftAssign=110, Equal=111, NotEqual=112, 
		LessEqual=113, GreaterEqual=114, AndAnd=115, OrOr=116, PlusPlus=117, MinusMinus=118, 
		Comma=119, ArrowStar=120, Arrow=121, Question=122, Colon=123, Doublecolon=124, 
		Semi=125, Dot=126, DotStar=127, Ellipsis=128, Identifier=129, DecimalLiteral=130, 
		OctalLiteral=131, HexadecimalLiteral=132, BinaryLiteral=133, Whitespace=134, 
		Newline=135, BlockComment=136, LineComment=137, Stack=138, Push=139, Empty=140, 
		Pop=141, Top=142, Cin=143, Cout=144, Endl=145, String=146, Length=147, 
		Vector=148, Find=149, Push_back=150;
	public static final int
		RULE_translationUnit = 0, RULE_primaryExpression = 1, RULE_idExpression = 2, 
		RULE_postfixExpression = 3, RULE_unaryExpression = 4, RULE_unaryOperator = 5, 
		RULE_newExpression = 6, RULE_newTypeId = 7, RULE_newDeclarator = 8, RULE_noPointerNewDeclarator = 9, 
		RULE_deleteExpression = 10, RULE_castExpression = 11, RULE_pointerMemberExpression = 12, 
		RULE_multiplicativeExpression = 13, RULE_additiveExpression = 14, RULE_shiftExpression = 15, 
		RULE_shiftOperator = 16, RULE_relationalExpression = 17, RULE_equalityExpression = 18, 
		RULE_andExpression = 19, RULE_exclusiveOrExpression = 20, RULE_inclusiveOrExpression = 21, 
		RULE_logicalAndExpression = 22, RULE_logicalOrExpression = 23, RULE_conditionalExpression = 24, 
		RULE_assignmentOperator = 25, RULE_expression = 26, RULE_constantExpression = 27, 
		RULE_statement = 28, RULE_labeledStatement = 29, RULE_expressionStatement = 30, 
		RULE_compoundStatement = 31, RULE_statementSeq = 32, RULE_selectionStatement = 33, 
		RULE_condition = 34, RULE_iterationStatement = 35, RULE_forInitStatement = 36, 
		RULE_jumpStatement = 37, RULE_declarationseq = 38, RULE_declaration = 39, 
		RULE_simpleDeclaration = 40, RULE_declSpecifier = 41, RULE_declSpecifierSeq = 42, 
		RULE_typedefName = 43, RULE_typeSpecifier = 44, RULE_trailingTypeSpecifier = 45, 
		RULE_typeSpecifierSeq = 46, RULE_simpleTypeLengthModifier = 47, RULE_simpleTypeSignednessModifier = 48, 
		RULE_simpleTypeSpecifier = 49, RULE_theTypeName = 50, RULE_initDeclaratorList = 51, 
		RULE_initDeclarator = 52, RULE_declarator = 53, RULE_pointerDeclarator = 54, 
		RULE_noPointerDeclarator = 55, RULE_parametersAndQualifiers = 56, RULE_pointerOperator = 57, 
		RULE_cvqualifierseq = 58, RULE_cvQualifier = 59, RULE_refqualifier = 60, 
		RULE_declaratorid = 61, RULE_theTypeId = 62, RULE_parameterDeclarationClause = 63, 
		RULE_parameterDeclarationList = 64, RULE_parameterDeclaration = 65, RULE_functionDefinition = 66, 
		RULE_functionBody = 67, RULE_initializer = 68, RULE_braceOrEqualInitializer = 69, 
		RULE_initializerClause = 70, RULE_initializerList = 71, RULE_bracedInitList = 72, 
		RULE_className = 73, RULE_classSpecifier = 74, RULE_classHead = 75, RULE_classHeadName = 76, 
		RULE_classKey = 77, RULE_memberSpecification = 78, RULE_memberdeclaration = 79, 
		RULE_memberDeclaratorList = 80, RULE_memberDeclarator = 81, RULE_baseSpecifierList = 82, 
		RULE_accessSpecifier = 83, RULE_memInitializerList = 84, RULE_memInitializer = 85, 
		RULE_meminitializerid = 86, RULE_operatorFunctionId = 87, RULE_literalOperatorId = 88, 
		RULE_templateDeclaration = 89, RULE_templateparameterList = 90, RULE_templateParameter = 91, 
		RULE_typeParameter = 92, RULE_simpleTemplateId = 93, RULE_templateId = 94, 
		RULE_templateName = 95, RULE_templateArgumentList = 96, RULE_templateArgument = 97, 
		RULE_explicitInstantiation = 98, RULE_theOperator = 99, RULE_literal = 100, 
		RULE_myId = 101, RULE_myClass = 102;
	private static String[] makeRuleNames() {
		return new String[] {
			"translationUnit", "primaryExpression", "idExpression", "postfixExpression", 
			"unaryExpression", "unaryOperator", "newExpression", "newTypeId", "newDeclarator", 
			"noPointerNewDeclarator", "deleteExpression", "castExpression", "pointerMemberExpression", 
			"multiplicativeExpression", "additiveExpression", "shiftExpression", 
			"shiftOperator", "relationalExpression", "equalityExpression", "andExpression", 
			"exclusiveOrExpression", "inclusiveOrExpression", "logicalAndExpression", 
			"logicalOrExpression", "conditionalExpression", "assignmentOperator", 
			"expression", "constantExpression", "statement", "labeledStatement", 
			"expressionStatement", "compoundStatement", "statementSeq", "selectionStatement", 
			"condition", "iterationStatement", "forInitStatement", "jumpStatement", 
			"declarationseq", "declaration", "simpleDeclaration", "declSpecifier", 
			"declSpecifierSeq", "typedefName", "typeSpecifier", "trailingTypeSpecifier", 
			"typeSpecifierSeq", "simpleTypeLengthModifier", "simpleTypeSignednessModifier", 
			"simpleTypeSpecifier", "theTypeName", "initDeclaratorList", "initDeclarator", 
			"declarator", "pointerDeclarator", "noPointerDeclarator", "parametersAndQualifiers", 
			"pointerOperator", "cvqualifierseq", "cvQualifier", "refqualifier", "declaratorid", 
			"theTypeId", "parameterDeclarationClause", "parameterDeclarationList", 
			"parameterDeclaration", "functionDefinition", "functionBody", "initializer", 
			"braceOrEqualInitializer", "initializerClause", "initializerList", "bracedInitList", 
			"className", "classSpecifier", "classHead", "classHeadName", "classKey", 
			"memberSpecification", "memberdeclaration", "memberDeclaratorList", "memberDeclarator", 
			"baseSpecifierList", "accessSpecifier", "memInitializerList", "memInitializer", 
			"meminitializerid", "operatorFunctionId", "literalOperatorId", "templateDeclaration", 
			"templateparameterList", "templateParameter", "typeParameter", "simpleTemplateId", 
			"templateId", "templateName", "templateArgumentList", "templateArgument", 
			"explicitInstantiation", "theOperator", "literal", "myId", "myClass"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "'alignas'", "'alignof'", 
			"'asm'", "'auto'", "'bool'", "'break'", "'case'", "'catch'", "'char'", 
			"'class'", "'const'", "'constexpr'", "'const_cast'", "'continue'", "'decltype'", 
			"'default'", "'delete'", "'do'", "'double'", "'dynamic_cast'", "'else'", 
			"'enum'", "'explicit'", "'export'", "'extern'", "'false'", "'final'", 
			"'float'", "'for'", "'friend'", "'goto'", "'if'", "'inline'", "'int'", 
			"'long'", "'mutable'", "'namespace'", "'new'", "'noexcept'", "'nullptr'", 
			"'operator'", "'override'", "'private'", "'protected'", "'public'", "'register'", 
			"'reinterpret_cast'", "'return'", "'short'", "'signed'", "'sizeof'", 
			"'static'", "'static_assert'", "'static_cast'", "'struct'", "'switch'", 
			"'template'", "'this'", "'thread_local'", "'throw'", "'true'", "'try'", 
			"'typedef'", "'typeid'", "'typename'", "'union'", "'unsigned'", "'using'", 
			"'virtual'", "'void'", "'volatile'", "'wchar_t'", "'while'", "'('", "')'", 
			"'['", "']'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'%'", "'^'", 
			"'&'", "'|'", "'~'", null, "'='", "'<'", "'>'", "'+='", "'-='", "'*='", 
			"'/='", "'%='", "'^='", "'&='", "'|='", "'<<='", "'>>='", "'=='", "'!='", 
			"'<='", "'>='", null, null, "'++'", "'--'", "','", "'->*'", "'->'", "'?'", 
			"':'", "'::'", "';'", "'.'", "'.*'", "'...'", null, null, null, null, 
			null, null, null, null, null, "'stack'", "'push'", "'empty'", "'pop'", 
			"'top'", "'cin'", "'cout'", "'endl'", "'string'", "'length'", "'vector'", 
			"'find'", "'push_back'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IntegerLiteral", "CharacterLiteral", "FloatingLiteral", "StringLiteral", 
			"BooleanLiteral", "PointerLiteral", "MultiLineMacro", "Directive", "Alignas", 
			"Alignof", "Asm", "Auto", "Bool", "Break", "Case", "Catch", "Char", "Class", 
			"Const", "Constexpr", "Const_cast", "Continue", "Decltype", "Default", 
			"Delete", "Do", "Double", "Dynamic_cast", "Else", "Enum", "Explicit", 
			"Export", "Extern", "False_", "Final", "Float", "For", "Friend", "Goto", 
			"If", "Inline", "Int", "Long", "Mutable", "Namespace", "New", "Noexcept", 
			"Nullptr", "Operator", "Override", "Private", "Protected", "Public", 
			"Register", "Reinterpret_cast", "Return", "Short", "Signed", "Sizeof", 
			"Static", "Static_assert", "Static_cast", "Struct", "Switch", "Template", 
			"This", "Thread_local", "Throw", "True_", "Try", "Typedef", "Typeid_", 
			"Typename_", "Union", "Unsigned", "Using", "Virtual", "Void", "Volatile", 
			"Wchar", "While", "LeftParen", "RightParen", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "Plus", "Minus", "Star", "Div", "Mod", "Caret", 
			"And", "Or", "Tilde", "Not", "Assign", "Less", "Greater", "PlusAssign", 
			"MinusAssign", "StarAssign", "DivAssign", "ModAssign", "XorAssign", "AndAssign", 
			"OrAssign", "LeftShiftAssign", "RightShiftAssign", "Equal", "NotEqual", 
			"LessEqual", "GreaterEqual", "AndAnd", "OrOr", "PlusPlus", "MinusMinus", 
			"Comma", "ArrowStar", "Arrow", "Question", "Colon", "Doublecolon", "Semi", 
			"Dot", "DotStar", "Ellipsis", "Identifier", "DecimalLiteral", "OctalLiteral", 
			"HexadecimalLiteral", "BinaryLiteral", "Whitespace", "Newline", "BlockComment", 
			"LineComment", "Stack", "Push", "Empty", "Pop", "Top", "Cin", "Cout", 
			"Endl", "String", "Length", "Vector", "Find", "Push_back"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "myParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public myParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TranslationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(myParser.EOF, 0); }
		public DeclarationseqContext declarationseq() {
			return getRuleContext(DeclarationseqContext.class,0);
		}
		public TranslationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationUnit; }
	}

	public final TranslationUnitContext translationUnit() throws RecognitionException {
		TranslationUnitContext _localctx = new TranslationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_translationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (Template - 65)) | (1L << (Typedef - 65)) | (1L << (Unsigned - 65)) | (1L << (Void - 65)) | (1L << (Volatile - 65)) | (1L << (LeftParen - 65)) | (1L << (Star - 65)) | (1L << (And - 65)) | (1L << (AndAnd - 65)) | (1L << (Semi - 65)) | (1L << (Ellipsis - 65)))) != 0) || ((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & ((1L << (Identifier - 129)) | (1L << (Stack - 129)) | (1L << (Push - 129)) | (1L << (Empty - 129)) | (1L << (Pop - 129)) | (1L << (Top - 129)) | (1L << (Cin - 129)) | (1L << (Cout - 129)) | (1L << (String - 129)) | (1L << (Length - 129)) | (1L << (Vector - 129)) | (1L << (Find - 129)) | (1L << (Push_back - 129)))) != 0)) {
				{
				setState(206);
				declarationseq();
				}
			}

			setState(209);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_primaryExpression);
		try {
			int _alt;
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case CharacterLiteral:
			case FloatingLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case PointerLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(211);
						literal();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(214); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(LeftParen);
				setState(217);
				expression();
				setState(218);
				match(RightParen);
				}
				break;
			case Operator:
			case Identifier:
			case Push:
			case Empty:
			case Pop:
			case Top:
			case Cin:
			case Cout:
			case Length:
			case Find:
			case Push_back:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				idExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public OperatorFunctionIdContext operatorFunctionId() {
			return getRuleContext(OperatorFunctionIdContext.class,0);
		}
		public LiteralOperatorIdContext literalOperatorId() {
			return getRuleContext(LiteralOperatorIdContext.class,0);
		}
		public TemplateIdContext templateId() {
			return getRuleContext(TemplateIdContext.class,0);
		}
		public MyIdContext myId() {
			return getRuleContext(MyIdContext.class,0);
		}
		public IdExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idExpression; }
	}

	public final IdExpressionContext idExpression() throws RecognitionException {
		IdExpressionContext _localctx = new IdExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_idExpression);
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				operatorFunctionId();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(225);
				literalOperatorId();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				templateId();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(227);
				myId();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostfixExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public SimpleTypeSpecifierContext simpleTypeSpecifier() {
			return getRuleContext(SimpleTypeSpecifierContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public InitializerListContext initializerList() {
			return getRuleContext(InitializerListContext.class,0);
		}
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(myParser.Dot, 0); }
		public TerminalNode Arrow() { return getToken(myParser.Arrow, 0); }
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TerminalNode Template() { return getToken(myParser.Template, 0); }
		public TerminalNode PlusPlus() { return getToken(myParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(myParser.MinusMinus, 0); }
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		return postfixExpression(0);
	}

	private PostfixExpressionContext postfixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, _parentState);
		PostfixExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_postfixExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(231);
				primaryExpression();
				}
				break;
			case 2:
				{
				setState(232);
				simpleTypeSpecifier();
				setState(239);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LeftParen:
					{
					setState(233);
					match(LeftParen);
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (LeftBrace - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
						{
						setState(234);
						initializerList();
						}
					}

					setState(237);
					match(RightParen);
					}
					break;
				case LeftBrace:
					{
					setState(238);
					bracedInitList();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(265);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(243);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(244);
						match(LeftBracket);
						setState(247);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case IntegerLiteral:
						case CharacterLiteral:
						case FloatingLiteral:
						case StringLiteral:
						case BooleanLiteral:
						case PointerLiteral:
						case Auto:
						case Bool:
						case Char:
						case Delete:
						case Double:
						case Float:
						case Int:
						case Long:
						case New:
						case Operator:
						case Short:
						case Signed:
						case Sizeof:
						case Unsigned:
						case Void:
						case LeftParen:
						case Plus:
						case Minus:
						case Star:
						case And:
						case Or:
						case Tilde:
						case Not:
						case PlusPlus:
						case MinusMinus:
						case Doublecolon:
						case Identifier:
						case Stack:
						case Push:
						case Empty:
						case Pop:
						case Top:
						case Cin:
						case Cout:
						case String:
						case Length:
						case Vector:
						case Find:
						case Push_back:
							{
							setState(245);
							expression();
							}
							break;
						case LeftBrace:
							{
							setState(246);
							bracedInitList();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(249);
						match(RightBracket);
						}
						break;
					case 2:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(251);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(252);
						match(LeftParen);
						setState(254);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (LeftBrace - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
							{
							setState(253);
							initializerList();
							}
						}

						setState(256);
						match(RightParen);
						}
						break;
					case 3:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(257);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(258);
						_la = _input.LA(1);
						if ( !(_la==Arrow || _la==Dot) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						{
						setState(260);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==Template) {
							{
							setState(259);
							match(Template);
							}
						}

						setState(262);
						idExpression();
						}
						}
						break;
					case 4:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(263);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(264);
						_la = _input.LA(1);
						if ( !(_la==PlusPlus || _la==MinusMinus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(myParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(myParser.MinusMinus, 0); }
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public TerminalNode Sizeof() { return getToken(myParser.Sizeof, 0); }
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public DeleteExpressionContext deleteExpression() {
			return getRuleContext(DeleteExpressionContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unaryExpression);
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				postfixExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PlusPlus:
					{
					setState(271);
					match(PlusPlus);
					}
					break;
				case MinusMinus:
					{
					setState(272);
					match(MinusMinus);
					}
					break;
				case Plus:
				case Minus:
				case Star:
				case And:
				case Or:
				case Tilde:
				case Not:
					{
					setState(273);
					unaryOperator();
					}
					break;
				case Sizeof:
					{
					setState(274);
					match(Sizeof);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(277);
				unaryExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				newExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(279);
				deleteExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperatorContext extends ParserRuleContext {
		public TerminalNode Or() { return getToken(myParser.Or, 0); }
		public TerminalNode Star() { return getToken(myParser.Star, 0); }
		public TerminalNode And() { return getToken(myParser.And, 0); }
		public TerminalNode Plus() { return getToken(myParser.Plus, 0); }
		public TerminalNode Tilde() { return getToken(myParser.Tilde, 0); }
		public TerminalNode Minus() { return getToken(myParser.Minus, 0); }
		public TerminalNode Not() { return getToken(myParser.Not, 0); }
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !(((((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & ((1L << (Plus - 88)) | (1L << (Minus - 88)) | (1L << (Star - 88)) | (1L << (And - 88)) | (1L << (Or - 88)) | (1L << (Tilde - 88)) | (1L << (Not - 88)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewExpressionContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(myParser.New, 0); }
		public NewTypeIdContext newTypeId() {
			return getRuleContext(NewTypeIdContext.class,0);
		}
		public TerminalNode Doublecolon() { return getToken(myParser.Doublecolon, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TheTypeIdContext theTypeId() {
			return getRuleContext(TheTypeIdContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_newExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Doublecolon) {
				{
				setState(284);
				match(Doublecolon);
				}
			}

			setState(287);
			match(New);
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Auto:
			case Bool:
			case Char:
			case Class:
			case Const:
			case Double:
			case Float:
			case Int:
			case Long:
			case Short:
			case Signed:
			case Struct:
			case Unsigned:
			case Void:
			case Volatile:
			case Identifier:
			case Stack:
			case String:
			case Vector:
				{
				setState(288);
				newTypeId();
				}
				break;
			case LeftParen:
				{
				{
				setState(289);
				match(LeftParen);
				setState(290);
				theTypeId();
				setState(291);
				match(RightParen);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewTypeIdContext extends ParserRuleContext {
		public TypeSpecifierSeqContext typeSpecifierSeq() {
			return getRuleContext(TypeSpecifierSeqContext.class,0);
		}
		public NewDeclaratorContext newDeclarator() {
			return getRuleContext(NewDeclaratorContext.class,0);
		}
		public NewTypeIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newTypeId; }
	}

	public final NewTypeIdContext newTypeId() throws RecognitionException {
		NewTypeIdContext _localctx = new NewTypeIdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_newTypeId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			typeSpecifierSeq();
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(296);
				newDeclarator();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewDeclaratorContext extends ParserRuleContext {
		public PointerOperatorContext pointerOperator() {
			return getRuleContext(PointerOperatorContext.class,0);
		}
		public NewDeclaratorContext newDeclarator() {
			return getRuleContext(NewDeclaratorContext.class,0);
		}
		public NoPointerNewDeclaratorContext noPointerNewDeclarator() {
			return getRuleContext(NoPointerNewDeclaratorContext.class,0);
		}
		public NewDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newDeclarator; }
	}

	public final NewDeclaratorContext newDeclarator() throws RecognitionException {
		NewDeclaratorContext _localctx = new NewDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_newDeclarator);
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Star:
			case And:
			case AndAnd:
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				pointerOperator();
				setState(301);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(300);
					newDeclarator();
					}
					break;
				}
				}
				break;
			case LeftBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				noPointerNewDeclarator(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoPointerNewDeclaratorContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public NoPointerNewDeclaratorContext noPointerNewDeclarator() {
			return getRuleContext(NoPointerNewDeclaratorContext.class,0);
		}
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public NoPointerNewDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noPointerNewDeclarator; }
	}

	public final NoPointerNewDeclaratorContext noPointerNewDeclarator() throws RecognitionException {
		return noPointerNewDeclarator(0);
	}

	private NoPointerNewDeclaratorContext noPointerNewDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NoPointerNewDeclaratorContext _localctx = new NoPointerNewDeclaratorContext(_ctx, _parentState);
		NoPointerNewDeclaratorContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_noPointerNewDeclarator, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(307);
			match(LeftBracket);
			setState(308);
			expression();
			setState(309);
			match(RightBracket);
			}
			_ctx.stop = _input.LT(-1);
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NoPointerNewDeclaratorContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_noPointerNewDeclarator);
					setState(311);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(312);
					match(LeftBracket);
					setState(313);
					constantExpression();
					setState(314);
					match(RightBracket);
					}
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DeleteExpressionContext extends ParserRuleContext {
		public TerminalNode Delete() { return getToken(myParser.Delete, 0); }
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public TerminalNode Doublecolon() { return getToken(myParser.Doublecolon, 0); }
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public DeleteExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteExpression; }
	}

	public final DeleteExpressionContext deleteExpression() throws RecognitionException {
		DeleteExpressionContext _localctx = new DeleteExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_deleteExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Doublecolon) {
				{
				setState(321);
				match(Doublecolon);
				}
			}

			setState(324);
			match(Delete);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftBracket) {
				{
				setState(325);
				match(LeftBracket);
				setState(326);
				match(RightBracket);
				}
			}

			setState(329);
			castExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CastExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TheTypeIdContext theTypeId() {
			return getRuleContext(TheTypeIdContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpression; }
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_castExpression);
		try {
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				unaryExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(LeftParen);
				setState(333);
				theTypeId();
				setState(334);
				match(RightParen);
				setState(335);
				castExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerMemberExpressionContext extends ParserRuleContext {
		public List<CastExpressionContext> castExpression() {
			return getRuleContexts(CastExpressionContext.class);
		}
		public CastExpressionContext castExpression(int i) {
			return getRuleContext(CastExpressionContext.class,i);
		}
		public List<TerminalNode> DotStar() { return getTokens(myParser.DotStar); }
		public TerminalNode DotStar(int i) {
			return getToken(myParser.DotStar, i);
		}
		public List<TerminalNode> ArrowStar() { return getTokens(myParser.ArrowStar); }
		public TerminalNode ArrowStar(int i) {
			return getToken(myParser.ArrowStar, i);
		}
		public PointerMemberExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerMemberExpression; }
	}

	public final PointerMemberExpressionContext pointerMemberExpression() throws RecognitionException {
		PointerMemberExpressionContext _localctx = new PointerMemberExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pointerMemberExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			castExpression();
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ArrowStar || _la==DotStar) {
				{
				{
				setState(340);
				_la = _input.LA(1);
				if ( !(_la==ArrowStar || _la==DotStar) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(341);
				castExpression();
				}
				}
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<PointerMemberExpressionContext> pointerMemberExpression() {
			return getRuleContexts(PointerMemberExpressionContext.class);
		}
		public PointerMemberExpressionContext pointerMemberExpression(int i) {
			return getRuleContext(PointerMemberExpressionContext.class,i);
		}
		public List<TerminalNode> Star() { return getTokens(myParser.Star); }
		public TerminalNode Star(int i) {
			return getToken(myParser.Star, i);
		}
		public List<TerminalNode> Div() { return getTokens(myParser.Div); }
		public TerminalNode Div(int i) {
			return getToken(myParser.Div, i);
		}
		public List<TerminalNode> Mod() { return getTokens(myParser.Mod); }
		public TerminalNode Mod(int i) {
			return getToken(myParser.Mod, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			pointerMemberExpression();
			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (Star - 90)) | (1L << (Div - 90)) | (1L << (Mod - 90)))) != 0)) {
				{
				{
				setState(348);
				_la = _input.LA(1);
				if ( !(((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (Star - 90)) | (1L << (Div - 90)) | (1L << (Mod - 90)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(349);
				pointerMemberExpression();
				}
				}
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(myParser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(myParser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(myParser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(myParser.Minus, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			multiplicativeExpression();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Plus || _la==Minus) {
				{
				{
				setState(356);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(357);
				multiplicativeExpression();
				}
				}
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShiftExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<ShiftOperatorContext> shiftOperator() {
			return getRuleContexts(ShiftOperatorContext.class);
		}
		public ShiftOperatorContext shiftOperator(int i) {
			return getRuleContext(ShiftOperatorContext.class,i);
		}
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftExpression; }
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_shiftExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			additiveExpression();
			setState(369);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(364);
					shiftOperator();
					setState(365);
					additiveExpression();
					}
					} 
				}
				setState(371);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShiftOperatorContext extends ParserRuleContext {
		public List<TerminalNode> Greater() { return getTokens(myParser.Greater); }
		public TerminalNode Greater(int i) {
			return getToken(myParser.Greater, i);
		}
		public List<TerminalNode> Less() { return getTokens(myParser.Less); }
		public TerminalNode Less(int i) {
			return getToken(myParser.Less, i);
		}
		public ShiftOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftOperator; }
	}

	public final ShiftOperatorContext shiftOperator() throws RecognitionException {
		ShiftOperatorContext _localctx = new ShiftOperatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_shiftOperator);
		try {
			setState(376);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Greater:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				match(Greater);
				setState(373);
				match(Greater);
				}
				break;
			case Less:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				match(Less);
				setState(375);
				match(Less);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<ShiftExpressionContext> shiftExpression() {
			return getRuleContexts(ShiftExpressionContext.class);
		}
		public ShiftExpressionContext shiftExpression(int i) {
			return getRuleContext(ShiftExpressionContext.class,i);
		}
		public List<TerminalNode> Less() { return getTokens(myParser.Less); }
		public TerminalNode Less(int i) {
			return getToken(myParser.Less, i);
		}
		public List<TerminalNode> Greater() { return getTokens(myParser.Greater); }
		public TerminalNode Greater(int i) {
			return getToken(myParser.Greater, i);
		}
		public List<TerminalNode> LessEqual() { return getTokens(myParser.LessEqual); }
		public TerminalNode LessEqual(int i) {
			return getToken(myParser.LessEqual, i);
		}
		public List<TerminalNode> GreaterEqual() { return getTokens(myParser.GreaterEqual); }
		public TerminalNode GreaterEqual(int i) {
			return getToken(myParser.GreaterEqual, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_relationalExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			shiftExpression();
			setState(383);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(379);
					_la = _input.LA(1);
					if ( !(((((_la - 99)) & ~0x3f) == 0 && ((1L << (_la - 99)) & ((1L << (Less - 99)) | (1L << (Greater - 99)) | (1L << (LessEqual - 99)) | (1L << (GreaterEqual - 99)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(380);
					shiftExpression();
					}
					} 
				}
				setState(385);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> Equal() { return getTokens(myParser.Equal); }
		public TerminalNode Equal(int i) {
			return getToken(myParser.Equal, i);
		}
		public List<TerminalNode> NotEqual() { return getTokens(myParser.NotEqual); }
		public TerminalNode NotEqual(int i) {
			return getToken(myParser.NotEqual, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_equalityExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			relationalExpression();
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Equal || _la==NotEqual) {
				{
				{
				setState(387);
				_la = _input.LA(1);
				if ( !(_la==Equal || _la==NotEqual) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(388);
				relationalExpression();
				}
				}
				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> And() { return getTokens(myParser.And); }
		public TerminalNode And(int i) {
			return getToken(myParser.And, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			equalityExpression();
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==And) {
				{
				{
				setState(395);
				match(And);
				setState(396);
				equalityExpression();
				}
				}
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> Caret() { return getTokens(myParser.Caret); }
		public TerminalNode Caret(int i) {
			return getToken(myParser.Caret, i);
		}
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusiveOrExpression; }
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_exclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			andExpression();
			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Caret) {
				{
				{
				setState(403);
				match(Caret);
				setState(404);
				andExpression();
				}
				}
				setState(409);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InclusiveOrExpressionContext extends ParserRuleContext {
		public List<ExclusiveOrExpressionContext> exclusiveOrExpression() {
			return getRuleContexts(ExclusiveOrExpressionContext.class);
		}
		public ExclusiveOrExpressionContext exclusiveOrExpression(int i) {
			return getRuleContext(ExclusiveOrExpressionContext.class,i);
		}
		public List<TerminalNode> Or() { return getTokens(myParser.Or); }
		public TerminalNode Or(int i) {
			return getToken(myParser.Or, i);
		}
		public InclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusiveOrExpression; }
	}

	public final InclusiveOrExpressionContext inclusiveOrExpression() throws RecognitionException {
		InclusiveOrExpressionContext _localctx = new InclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_inclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			exclusiveOrExpression();
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Or) {
				{
				{
				setState(411);
				match(Or);
				setState(412);
				exclusiveOrExpression();
				}
				}
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public List<InclusiveOrExpressionContext> inclusiveOrExpression() {
			return getRuleContexts(InclusiveOrExpressionContext.class);
		}
		public InclusiveOrExpressionContext inclusiveOrExpression(int i) {
			return getRuleContext(InclusiveOrExpressionContext.class,i);
		}
		public List<TerminalNode> AndAnd() { return getTokens(myParser.AndAnd); }
		public TerminalNode AndAnd(int i) {
			return getToken(myParser.AndAnd, i);
		}
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_logicalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			inclusiveOrExpression();
			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AndAnd) {
				{
				{
				setState(419);
				match(AndAnd);
				setState(420);
				inclusiveOrExpression();
				}
				}
				setState(425);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public List<LogicalAndExpressionContext> logicalAndExpression() {
			return getRuleContexts(LogicalAndExpressionContext.class);
		}
		public LogicalAndExpressionContext logicalAndExpression(int i) {
			return getRuleContext(LogicalAndExpressionContext.class,i);
		}
		public List<TerminalNode> OrOr() { return getTokens(myParser.OrOr); }
		public TerminalNode OrOr(int i) {
			return getToken(myParser.OrOr, i);
		}
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_logicalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			logicalAndExpression();
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OrOr) {
				{
				{
				setState(427);
				match(OrOr);
				setState(428);
				logicalAndExpression();
				}
				}
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public TerminalNode Question() { return getToken(myParser.Question, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Colon() { return getToken(myParser.Colon, 0); }
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_conditionalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			logicalOrExpression();
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Question) {
				{
				setState(435);
				match(Question);
				setState(436);
				expression();
				setState(437);
				match(Colon);
				setState(438);
				conditionalExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public TerminalNode StarAssign() { return getToken(myParser.StarAssign, 0); }
		public TerminalNode DivAssign() { return getToken(myParser.DivAssign, 0); }
		public TerminalNode ModAssign() { return getToken(myParser.ModAssign, 0); }
		public TerminalNode PlusAssign() { return getToken(myParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(myParser.MinusAssign, 0); }
		public TerminalNode RightShiftAssign() { return getToken(myParser.RightShiftAssign, 0); }
		public TerminalNode LeftShiftAssign() { return getToken(myParser.LeftShiftAssign, 0); }
		public TerminalNode AndAssign() { return getToken(myParser.AndAssign, 0); }
		public TerminalNode XorAssign() { return getToken(myParser.XorAssign, 0); }
		public TerminalNode OrAssign() { return getToken(myParser.OrAssign, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			_la = _input.LA(1);
			if ( !(((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & ((1L << (Assign - 98)) | (1L << (PlusAssign - 98)) | (1L << (MinusAssign - 98)) | (1L << (StarAssign - 98)) | (1L << (DivAssign - 98)) | (1L << (ModAssign - 98)) | (1L << (XorAssign - 98)) | (1L << (AndAssign - 98)) | (1L << (OrAssign - 98)) | (1L << (LeftShiftAssign - 98)) | (1L << (RightShiftAssign - 98)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<ConditionalExpressionContext> conditionalExpression() {
			return getRuleContexts(ConditionalExpressionContext.class);
		}
		public ConditionalExpressionContext conditionalExpression(int i) {
			return getRuleContext(ConditionalExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			conditionalExpression();
			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(445);
				match(Comma);
				setState(446);
				conditionalExpression();
				}
				}
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			conditionalExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public LabeledStatementContext labeledStatement() {
			return getRuleContext(LabeledStatementContext.class,0);
		}
		public SimpleDeclarationContext simpleDeclaration() {
			return getRuleContext(SimpleDeclarationContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_statement);
		try {
			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(454);
				labeledStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(455);
				simpleDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(461);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IntegerLiteral:
				case CharacterLiteral:
				case FloatingLiteral:
				case StringLiteral:
				case BooleanLiteral:
				case PointerLiteral:
				case Auto:
				case Bool:
				case Char:
				case Delete:
				case Double:
				case Float:
				case Int:
				case Long:
				case New:
				case Operator:
				case Short:
				case Signed:
				case Sizeof:
				case Unsigned:
				case Void:
				case LeftParen:
				case Plus:
				case Minus:
				case Star:
				case And:
				case Or:
				case Tilde:
				case Not:
				case PlusPlus:
				case MinusMinus:
				case Doublecolon:
				case Semi:
				case Identifier:
				case Stack:
				case Push:
				case Empty:
				case Pop:
				case Top:
				case Cin:
				case Cout:
				case String:
				case Length:
				case Vector:
				case Find:
				case Push_back:
					{
					setState(456);
					expressionStatement();
					}
					break;
				case LeftBrace:
					{
					setState(457);
					compoundStatement();
					}
					break;
				case If:
				case Switch:
					{
					setState(458);
					selectionStatement();
					}
					break;
				case Do:
				case For:
				case While:
					{
					setState(459);
					iterationStatement();
					}
					break;
				case Break:
				case Continue:
				case Return:
					{
					setState(460);
					jumpStatement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledStatementContext extends ParserRuleContext {
		public TerminalNode Colon() { return getToken(myParser.Colon, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode Case() { return getToken(myParser.Case, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode Default() { return getToken(myParser.Default, 0); }
		public LabeledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStatement; }
	}

	public final LabeledStatementContext labeledStatement() throws RecognitionException {
		LabeledStatementContext _localctx = new LabeledStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_labeledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(465);
				match(Identifier);
				}
				break;
			case Case:
				{
				setState(466);
				match(Case);
				setState(467);
				constantExpression();
				}
				break;
			case Default:
				{
				setState(468);
				match(Default);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(471);
			match(Colon);
			setState(472);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
				{
				setState(474);
				expression();
				}
			}

			setState(477);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public StatementSeqContext statementSeq() {
			return getRuleContext(StatementSeqContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			match(LeftBrace);
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Break) | (1L << Case) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Continue) | (1L << Default) | (1L << Delete) | (1L << Do) | (1L << Double) | (1L << Float) | (1L << For) | (1L << If) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Return) | (1L << Short) | (1L << Signed) | (1L << Sizeof) | (1L << Struct))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Switch - 64)) | (1L << (Typedef - 64)) | (1L << (Unsigned - 64)) | (1L << (Void - 64)) | (1L << (Volatile - 64)) | (1L << (While - 64)) | (1L << (LeftParen - 64)) | (1L << (LeftBrace - 64)) | (1L << (Plus - 64)) | (1L << (Minus - 64)) | (1L << (Star - 64)) | (1L << (And - 64)) | (1L << (Or - 64)) | (1L << (Tilde - 64)) | (1L << (Not - 64)) | (1L << (AndAnd - 64)) | (1L << (PlusPlus - 64)) | (1L << (MinusMinus - 64)) | (1L << (Doublecolon - 64)) | (1L << (Semi - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (Ellipsis - 128)) | (1L << (Identifier - 128)) | (1L << (Stack - 128)) | (1L << (Push - 128)) | (1L << (Empty - 128)) | (1L << (Pop - 128)) | (1L << (Top - 128)) | (1L << (Cin - 128)) | (1L << (Cout - 128)) | (1L << (String - 128)) | (1L << (Length - 128)) | (1L << (Vector - 128)) | (1L << (Find - 128)) | (1L << (Push_back - 128)))) != 0)) {
				{
				setState(480);
				statementSeq();
				}
			}

			setState(483);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementSeqContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementSeq; }
	}

	public final StatementSeqContext statementSeq() throws RecognitionException {
		StatementSeqContext _localctx = new StatementSeqContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_statementSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(485);
				statement();
				}
				}
				setState(488); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Break) | (1L << Case) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Continue) | (1L << Default) | (1L << Delete) | (1L << Do) | (1L << Double) | (1L << Float) | (1L << For) | (1L << If) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Return) | (1L << Short) | (1L << Signed) | (1L << Sizeof) | (1L << Struct))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Switch - 64)) | (1L << (Typedef - 64)) | (1L << (Unsigned - 64)) | (1L << (Void - 64)) | (1L << (Volatile - 64)) | (1L << (While - 64)) | (1L << (LeftParen - 64)) | (1L << (LeftBrace - 64)) | (1L << (Plus - 64)) | (1L << (Minus - 64)) | (1L << (Star - 64)) | (1L << (And - 64)) | (1L << (Or - 64)) | (1L << (Tilde - 64)) | (1L << (Not - 64)) | (1L << (AndAnd - 64)) | (1L << (PlusPlus - 64)) | (1L << (MinusMinus - 64)) | (1L << (Doublecolon - 64)) | (1L << (Semi - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (Ellipsis - 128)) | (1L << (Identifier - 128)) | (1L << (Stack - 128)) | (1L << (Push - 128)) | (1L << (Empty - 128)) | (1L << (Pop - 128)) | (1L << (Top - 128)) | (1L << (Cin - 128)) | (1L << (Cout - 128)) | (1L << (String - 128)) | (1L << (Length - 128)) | (1L << (Vector - 128)) | (1L << (Find - 128)) | (1L << (Push_back - 128)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(myParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(myParser.Else, 0); }
		public TerminalNode Switch() { return getToken(myParser.Switch, 0); }
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_selectionStatement);
		try {
			setState(505);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case If:
				enterOuterAlt(_localctx, 1);
				{
				setState(490);
				match(If);
				setState(491);
				match(LeftParen);
				setState(492);
				condition();
				setState(493);
				match(RightParen);
				setState(494);
				statement();
				setState(497);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(495);
					match(Else);
					setState(496);
					statement();
					}
					break;
				}
				}
				break;
			case Switch:
				enterOuterAlt(_localctx, 2);
				{
				setState(499);
				match(Switch);
				setState(500);
				match(LeftParen);
				setState(501);
				condition();
				setState(502);
				match(RightParen);
				setState(503);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclSpecifierSeqContext declSpecifierSeq() {
			return getRuleContext(DeclSpecifierSeqContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public InitializerClauseContext initializerClause() {
			return getRuleContext(InitializerClauseContext.class,0);
		}
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_condition);
		try {
			setState(515);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(507);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(508);
				declSpecifierSeq();
				setState(509);
				declarator();
				setState(513);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Assign:
					{
					setState(510);
					match(Assign);
					setState(511);
					initializerClause();
					}
					break;
				case LeftBrace:
					{
					setState(512);
					bracedInitList();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(myParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Do() { return getToken(myParser.Do, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public TerminalNode For() { return getToken(myParser.For, 0); }
		public ForInitStatementContext forInitStatement() {
			return getRuleContext(ForInitStatementContext.class,0);
		}
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_iterationStatement);
		int _la;
		try {
			setState(544);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				enterOuterAlt(_localctx, 1);
				{
				setState(517);
				match(While);
				setState(518);
				match(LeftParen);
				setState(519);
				condition();
				setState(520);
				match(RightParen);
				setState(521);
				statement();
				}
				break;
			case Do:
				enterOuterAlt(_localctx, 2);
				{
				setState(523);
				match(Do);
				setState(524);
				statement();
				setState(525);
				match(While);
				setState(526);
				match(LeftParen);
				setState(527);
				expression();
				setState(528);
				match(RightParen);
				setState(529);
				match(Semi);
				}
				break;
			case For:
				enterOuterAlt(_localctx, 3);
				{
				setState(531);
				match(For);
				setState(532);
				match(LeftParen);
				{
				setState(533);
				forInitStatement();
				setState(535);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof) | (1L << Struct))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (Typedef - 71)) | (1L << (Unsigned - 71)) | (1L << (Void - 71)) | (1L << (Volatile - 71)) | (1L << (LeftParen - 71)) | (1L << (Plus - 71)) | (1L << (Minus - 71)) | (1L << (Star - 71)) | (1L << (And - 71)) | (1L << (Or - 71)) | (1L << (Tilde - 71)) | (1L << (Not - 71)) | (1L << (PlusPlus - 71)) | (1L << (MinusMinus - 71)) | (1L << (Doublecolon - 71)) | (1L << (Identifier - 71)))) != 0) || ((((_la - 138)) & ~0x3f) == 0 && ((1L << (_la - 138)) & ((1L << (Stack - 138)) | (1L << (Push - 138)) | (1L << (Empty - 138)) | (1L << (Pop - 138)) | (1L << (Top - 138)) | (1L << (Cin - 138)) | (1L << (Cout - 138)) | (1L << (String - 138)) | (1L << (Length - 138)) | (1L << (Vector - 138)) | (1L << (Find - 138)) | (1L << (Push_back - 138)))) != 0)) {
					{
					setState(534);
					condition();
					}
				}

				setState(537);
				match(Semi);
				setState(539);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
					{
					setState(538);
					expression();
					}
				}

				}
				setState(541);
				match(RightParen);
				setState(542);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitStatementContext extends ParserRuleContext {
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SimpleDeclarationContext simpleDeclaration() {
			return getRuleContext(SimpleDeclarationContext.class,0);
		}
		public ForInitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInitStatement; }
	}

	public final ForInitStatementContext forInitStatement() throws RecognitionException {
		ForInitStatementContext _localctx = new ForInitStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_forInitStatement);
		try {
			setState(548);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(546);
				expressionStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(547);
				simpleDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public TerminalNode Break() { return getToken(myParser.Break, 0); }
		public TerminalNode Continue() { return getToken(myParser.Continue, 0); }
		public TerminalNode Return() { return getToken(myParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_jumpStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
				{
				setState(550);
				match(Break);
				}
				break;
			case Continue:
				{
				setState(551);
				match(Continue);
				}
				break;
			case Return:
				{
				setState(552);
				match(Return);
				setState(555);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IntegerLiteral:
				case CharacterLiteral:
				case FloatingLiteral:
				case StringLiteral:
				case BooleanLiteral:
				case PointerLiteral:
				case Auto:
				case Bool:
				case Char:
				case Delete:
				case Double:
				case Float:
				case Int:
				case Long:
				case New:
				case Operator:
				case Short:
				case Signed:
				case Sizeof:
				case Unsigned:
				case Void:
				case LeftParen:
				case Plus:
				case Minus:
				case Star:
				case And:
				case Or:
				case Tilde:
				case Not:
				case PlusPlus:
				case MinusMinus:
				case Doublecolon:
				case Identifier:
				case Stack:
				case Push:
				case Empty:
				case Pop:
				case Top:
				case Cin:
				case Cout:
				case String:
				case Length:
				case Vector:
				case Find:
				case Push_back:
					{
					setState(553);
					expression();
					}
					break;
				case LeftBrace:
					{
					setState(554);
					bracedInitList();
					}
					break;
				case Semi:
					break;
				default:
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(559);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationseqContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationseqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationseq; }
	}

	public final DeclarationseqContext declarationseq() throws RecognitionException {
		DeclarationseqContext _localctx = new DeclarationseqContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_declarationseq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(561);
				declaration();
				}
				}
				setState(564); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (Template - 65)) | (1L << (Typedef - 65)) | (1L << (Unsigned - 65)) | (1L << (Void - 65)) | (1L << (Volatile - 65)) | (1L << (LeftParen - 65)) | (1L << (Star - 65)) | (1L << (And - 65)) | (1L << (AndAnd - 65)) | (1L << (Semi - 65)) | (1L << (Ellipsis - 65)))) != 0) || ((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & ((1L << (Identifier - 129)) | (1L << (Stack - 129)) | (1L << (Push - 129)) | (1L << (Empty - 129)) | (1L << (Pop - 129)) | (1L << (Top - 129)) | (1L << (Cin - 129)) | (1L << (Cout - 129)) | (1L << (String - 129)) | (1L << (Length - 129)) | (1L << (Vector - 129)) | (1L << (Find - 129)) | (1L << (Push_back - 129)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public SimpleDeclarationContext simpleDeclaration() {
			return getRuleContext(SimpleDeclarationContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public TemplateDeclarationContext templateDeclaration() {
			return getRuleContext(TemplateDeclarationContext.class,0);
		}
		public ExplicitInstantiationContext explicitInstantiation() {
			return getRuleContext(ExplicitInstantiationContext.class,0);
		}
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_declaration);
		try {
			setState(571);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(566);
				simpleDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(567);
				functionDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(568);
				templateDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(569);
				explicitInstantiation();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(570);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleDeclarationContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public DeclSpecifierSeqContext declSpecifierSeq() {
			return getRuleContext(DeclSpecifierSeqContext.class,0);
		}
		public InitDeclaratorListContext initDeclaratorList() {
			return getRuleContext(InitDeclaratorListContext.class,0);
		}
		public SimpleDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleDeclaration; }
	}

	public final SimpleDeclarationContext simpleDeclaration() throws RecognitionException {
		SimpleDeclarationContext _localctx = new SimpleDeclarationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_simpleDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(573);
				declSpecifierSeq();
				}
				break;
			}
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 49)) & ~0x3f) == 0 && ((1L << (_la - 49)) & ((1L << (Operator - 49)) | (1L << (LeftParen - 49)) | (1L << (Star - 49)) | (1L << (And - 49)))) != 0) || ((((_la - 115)) & ~0x3f) == 0 && ((1L << (_la - 115)) & ((1L << (AndAnd - 115)) | (1L << (Ellipsis - 115)) | (1L << (Identifier - 115)) | (1L << (Push - 115)) | (1L << (Empty - 115)) | (1L << (Pop - 115)) | (1L << (Top - 115)) | (1L << (Cin - 115)) | (1L << (Cout - 115)) | (1L << (Length - 115)) | (1L << (Find - 115)) | (1L << (Push_back - 115)))) != 0)) {
				{
				setState(576);
				initDeclaratorList();
				}
			}

			setState(579);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclSpecifierContext extends ParserRuleContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TerminalNode Typedef() { return getToken(myParser.Typedef, 0); }
		public DeclSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declSpecifier; }
	}

	public final DeclSpecifierContext declSpecifier() throws RecognitionException {
		DeclSpecifierContext _localctx = new DeclSpecifierContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_declSpecifier);
		try {
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Auto:
			case Bool:
			case Char:
			case Class:
			case Const:
			case Double:
			case Float:
			case Int:
			case Long:
			case Short:
			case Signed:
			case Struct:
			case Unsigned:
			case Void:
			case Volatile:
			case Identifier:
			case Stack:
			case String:
			case Vector:
				enterOuterAlt(_localctx, 1);
				{
				setState(581);
				typeSpecifier();
				}
				break;
			case Typedef:
				enterOuterAlt(_localctx, 2);
				{
				setState(582);
				match(Typedef);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclSpecifierSeqContext extends ParserRuleContext {
		public List<DeclSpecifierContext> declSpecifier() {
			return getRuleContexts(DeclSpecifierContext.class);
		}
		public DeclSpecifierContext declSpecifier(int i) {
			return getRuleContext(DeclSpecifierContext.class,i);
		}
		public DeclSpecifierSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declSpecifierSeq; }
	}

	public final DeclSpecifierSeqContext declSpecifierSeq() throws RecognitionException {
		DeclSpecifierSeqContext _localctx = new DeclSpecifierSeqContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_declSpecifierSeq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(586); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(585);
					declSpecifier();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(588); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedefNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TypedefNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedefName; }
	}

	public final TypedefNameContext typedefName() throws RecognitionException {
		TypedefNameContext _localctx = new TypedefNameContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_typedefName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(590);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecifierContext extends ParserRuleContext {
		public TrailingTypeSpecifierContext trailingTypeSpecifier() {
			return getRuleContext(TrailingTypeSpecifierContext.class,0);
		}
		public ClassSpecifierContext classSpecifier() {
			return getRuleContext(ClassSpecifierContext.class,0);
		}
		public TypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecifier; }
	}

	public final TypeSpecifierContext typeSpecifier() throws RecognitionException {
		TypeSpecifierContext _localctx = new TypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_typeSpecifier);
		try {
			setState(594);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Auto:
			case Bool:
			case Char:
			case Const:
			case Double:
			case Float:
			case Int:
			case Long:
			case Short:
			case Signed:
			case Unsigned:
			case Void:
			case Volatile:
			case Identifier:
			case Stack:
			case String:
			case Vector:
				enterOuterAlt(_localctx, 1);
				{
				setState(592);
				trailingTypeSpecifier();
				}
				break;
			case Class:
			case Struct:
				enterOuterAlt(_localctx, 2);
				{
				setState(593);
				classSpecifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrailingTypeSpecifierContext extends ParserRuleContext {
		public SimpleTypeSpecifierContext simpleTypeSpecifier() {
			return getRuleContext(SimpleTypeSpecifierContext.class,0);
		}
		public CvQualifierContext cvQualifier() {
			return getRuleContext(CvQualifierContext.class,0);
		}
		public TrailingTypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trailingTypeSpecifier; }
	}

	public final TrailingTypeSpecifierContext trailingTypeSpecifier() throws RecognitionException {
		TrailingTypeSpecifierContext _localctx = new TrailingTypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_trailingTypeSpecifier);
		try {
			setState(598);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Auto:
			case Bool:
			case Char:
			case Double:
			case Float:
			case Int:
			case Long:
			case Short:
			case Signed:
			case Unsigned:
			case Void:
			case Identifier:
			case Stack:
			case String:
			case Vector:
				enterOuterAlt(_localctx, 1);
				{
				setState(596);
				simpleTypeSpecifier();
				}
				break;
			case Const:
			case Volatile:
				enterOuterAlt(_localctx, 2);
				{
				setState(597);
				cvQualifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecifierSeqContext extends ParserRuleContext {
		public List<TypeSpecifierContext> typeSpecifier() {
			return getRuleContexts(TypeSpecifierContext.class);
		}
		public TypeSpecifierContext typeSpecifier(int i) {
			return getRuleContext(TypeSpecifierContext.class,i);
		}
		public TypeSpecifierSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecifierSeq; }
	}

	public final TypeSpecifierSeqContext typeSpecifierSeq() throws RecognitionException {
		TypeSpecifierSeqContext _localctx = new TypeSpecifierSeqContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_typeSpecifierSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(600);
				typeSpecifier();
				}
				}
				setState(603); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (Volatile - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || _la==String || _la==Vector );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeLengthModifierContext extends ParserRuleContext {
		public TerminalNode Short() { return getToken(myParser.Short, 0); }
		public TerminalNode Long() { return getToken(myParser.Long, 0); }
		public SimpleTypeLengthModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypeLengthModifier; }
	}

	public final SimpleTypeLengthModifierContext simpleTypeLengthModifier() throws RecognitionException {
		SimpleTypeLengthModifierContext _localctx = new SimpleTypeLengthModifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_simpleTypeLengthModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			_la = _input.LA(1);
			if ( !(_la==Long || _la==Short) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeSignednessModifierContext extends ParserRuleContext {
		public TerminalNode Unsigned() { return getToken(myParser.Unsigned, 0); }
		public TerminalNode Signed() { return getToken(myParser.Signed, 0); }
		public SimpleTypeSignednessModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypeSignednessModifier; }
	}

	public final SimpleTypeSignednessModifierContext simpleTypeSignednessModifier() throws RecognitionException {
		SimpleTypeSignednessModifierContext _localctx = new SimpleTypeSignednessModifierContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_simpleTypeSignednessModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			_la = _input.LA(1);
			if ( !(_la==Signed || _la==Unsigned) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeSpecifierContext extends ParserRuleContext {
		public TheTypeNameContext theTypeName() {
			return getRuleContext(TheTypeNameContext.class,0);
		}
		public SimpleTypeSignednessModifierContext simpleTypeSignednessModifier() {
			return getRuleContext(SimpleTypeSignednessModifierContext.class,0);
		}
		public List<SimpleTypeLengthModifierContext> simpleTypeLengthModifier() {
			return getRuleContexts(SimpleTypeLengthModifierContext.class);
		}
		public SimpleTypeLengthModifierContext simpleTypeLengthModifier(int i) {
			return getRuleContext(SimpleTypeLengthModifierContext.class,i);
		}
		public TerminalNode Char() { return getToken(myParser.Char, 0); }
		public TerminalNode Bool() { return getToken(myParser.Bool, 0); }
		public TerminalNode Int() { return getToken(myParser.Int, 0); }
		public TerminalNode Float() { return getToken(myParser.Float, 0); }
		public TerminalNode Double() { return getToken(myParser.Double, 0); }
		public TerminalNode Void() { return getToken(myParser.Void, 0); }
		public TerminalNode Auto() { return getToken(myParser.Auto, 0); }
		public SimpleTypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypeSpecifier; }
	}

	public final SimpleTypeSpecifierContext simpleTypeSpecifier() throws RecognitionException {
		SimpleTypeSpecifierContext _localctx = new SimpleTypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_simpleTypeSpecifier);
		int _la;
		try {
			int _alt;
			setState(641);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(609);
				theTypeName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(610);
				simpleTypeSignednessModifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Signed || _la==Unsigned) {
					{
					setState(611);
					simpleTypeSignednessModifier();
					}
				}

				setState(615); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(614);
						simpleTypeLengthModifier();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(617); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(620);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Signed || _la==Unsigned) {
					{
					setState(619);
					simpleTypeSignednessModifier();
					}
				}

				setState(622);
				match(Char);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(623);
				match(Bool);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(625);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Signed || _la==Unsigned) {
					{
					setState(624);
					simpleTypeSignednessModifier();
					}
				}

				setState(630);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Long || _la==Short) {
					{
					{
					setState(627);
					simpleTypeLengthModifier();
					}
					}
					setState(632);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(633);
				match(Int);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(634);
				match(Float);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(636);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Long || _la==Short) {
					{
					setState(635);
					simpleTypeLengthModifier();
					}
				}

				setState(638);
				match(Double);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(639);
				match(Void);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(640);
				match(Auto);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TheTypeNameContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public TypedefNameContext typedefName() {
			return getRuleContext(TypedefNameContext.class,0);
		}
		public SimpleTemplateIdContext simpleTemplateId() {
			return getRuleContext(SimpleTemplateIdContext.class,0);
		}
		public TheTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theTypeName; }
	}

	public final TheTypeNameContext theTypeName() throws RecognitionException {
		TheTypeNameContext _localctx = new TheTypeNameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_theTypeName);
		try {
			setState(646);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(643);
				className();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(644);
				typedefName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(645);
				simpleTemplateId();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclaratorListContext extends ParserRuleContext {
		public List<InitDeclaratorContext> initDeclarator() {
			return getRuleContexts(InitDeclaratorContext.class);
		}
		public InitDeclaratorContext initDeclarator(int i) {
			return getRuleContext(InitDeclaratorContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public InitDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclaratorList; }
	}

	public final InitDeclaratorListContext initDeclaratorList() throws RecognitionException {
		InitDeclaratorListContext _localctx = new InitDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_initDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(648);
			initDeclarator();
			setState(653);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(649);
				match(Comma);
				setState(650);
				initDeclarator();
				}
				}
				setState(655);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclaratorContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public InitDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclarator; }
	}

	public final InitDeclaratorContext initDeclarator() throws RecognitionException {
		InitDeclaratorContext _localctx = new InitDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_initDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(656);
			declarator();
			setState(658);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 82)) & ~0x3f) == 0 && ((1L << (_la - 82)) & ((1L << (LeftParen - 82)) | (1L << (LeftBrace - 82)) | (1L << (Assign - 82)))) != 0)) {
				{
				setState(657);
				initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratorContext extends ParserRuleContext {
		public PointerDeclaratorContext pointerDeclarator() {
			return getRuleContext(PointerDeclaratorContext.class,0);
		}
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			pointerDeclarator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerDeclaratorContext extends ParserRuleContext {
		public NoPointerDeclaratorContext noPointerDeclarator() {
			return getRuleContext(NoPointerDeclaratorContext.class,0);
		}
		public List<PointerOperatorContext> pointerOperator() {
			return getRuleContexts(PointerOperatorContext.class);
		}
		public PointerOperatorContext pointerOperator(int i) {
			return getRuleContext(PointerOperatorContext.class,i);
		}
		public List<TerminalNode> Const() { return getTokens(myParser.Const); }
		public TerminalNode Const(int i) {
			return getToken(myParser.Const, i);
		}
		public PointerDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerDeclarator; }
	}

	public final PointerDeclaratorContext pointerDeclarator() throws RecognitionException {
		PointerDeclaratorContext _localctx = new PointerDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_pointerDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (Star - 90)) | (1L << (And - 90)) | (1L << (AndAnd - 90)))) != 0)) {
				{
				{
				setState(662);
				pointerOperator();
				setState(664);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Const) {
					{
					setState(663);
					match(Const);
					}
				}

				}
				}
				setState(670);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(671);
			noPointerDeclarator(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoPointerDeclaratorContext extends ParserRuleContext {
		public DeclaratoridContext declaratorid() {
			return getRuleContext(DeclaratoridContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public PointerDeclaratorContext pointerDeclarator() {
			return getRuleContext(PointerDeclaratorContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public NoPointerDeclaratorContext noPointerDeclarator() {
			return getRuleContext(NoPointerDeclaratorContext.class,0);
		}
		public ParametersAndQualifiersContext parametersAndQualifiers() {
			return getRuleContext(ParametersAndQualifiersContext.class,0);
		}
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public NoPointerDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noPointerDeclarator; }
	}

	public final NoPointerDeclaratorContext noPointerDeclarator() throws RecognitionException {
		return noPointerDeclarator(0);
	}

	private NoPointerDeclaratorContext noPointerDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NoPointerDeclaratorContext _localctx = new NoPointerDeclaratorContext(_ctx, _parentState);
		NoPointerDeclaratorContext _prevctx = _localctx;
		int _startState = 110;
		enterRecursionRule(_localctx, 110, RULE_noPointerDeclarator, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(679);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Operator:
			case Ellipsis:
			case Identifier:
			case Push:
			case Empty:
			case Pop:
			case Top:
			case Cin:
			case Cout:
			case Length:
			case Find:
			case Push_back:
				{
				setState(674);
				declaratorid();
				}
				break;
			case LeftParen:
				{
				setState(675);
				match(LeftParen);
				setState(676);
				pointerDeclarator();
				setState(677);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(692);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NoPointerDeclaratorContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_noPointerDeclarator);
					setState(681);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(688);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case LeftParen:
						{
						setState(682);
						parametersAndQualifiers();
						}
						break;
					case LeftBracket:
						{
						setState(683);
						match(LeftBracket);
						setState(685);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
							{
							setState(684);
							constantExpression();
							}
						}

						setState(687);
						match(RightBracket);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(694);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParametersAndQualifiersContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public ParameterDeclarationClauseContext parameterDeclarationClause() {
			return getRuleContext(ParameterDeclarationClauseContext.class,0);
		}
		public CvqualifierseqContext cvqualifierseq() {
			return getRuleContext(CvqualifierseqContext.class,0);
		}
		public RefqualifierContext refqualifier() {
			return getRuleContext(RefqualifierContext.class,0);
		}
		public ParametersAndQualifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametersAndQualifiers; }
	}

	public final ParametersAndQualifiersContext parametersAndQualifiers() throws RecognitionException {
		ParametersAndQualifiersContext _localctx = new ParametersAndQualifiersContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_parametersAndQualifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695);
			match(LeftParen);
			setState(697);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (Typedef - 71)) | (1L << (Unsigned - 71)) | (1L << (Void - 71)) | (1L << (Volatile - 71)) | (1L << (Identifier - 71)))) != 0) || ((((_la - 138)) & ~0x3f) == 0 && ((1L << (_la - 138)) & ((1L << (Stack - 138)) | (1L << (String - 138)) | (1L << (Vector - 138)))) != 0)) {
				{
				setState(696);
				parameterDeclarationClause();
				}
			}

			setState(699);
			match(RightParen);
			setState(701);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(700);
				cvqualifierseq();
				}
				break;
			}
			setState(704);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(703);
				refqualifier();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerOperatorContext extends ParserRuleContext {
		public TerminalNode And() { return getToken(myParser.And, 0); }
		public TerminalNode AndAnd() { return getToken(myParser.AndAnd, 0); }
		public TerminalNode Star() { return getToken(myParser.Star, 0); }
		public CvqualifierseqContext cvqualifierseq() {
			return getRuleContext(CvqualifierseqContext.class,0);
		}
		public PointerOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerOperator; }
	}

	public final PointerOperatorContext pointerOperator() throws RecognitionException {
		PointerOperatorContext _localctx = new PointerOperatorContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_pointerOperator);
		int _la;
		try {
			setState(711);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case And:
			case AndAnd:
				enterOuterAlt(_localctx, 1);
				{
				setState(706);
				_la = _input.LA(1);
				if ( !(_la==And || _la==AndAnd) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Star:
				enterOuterAlt(_localctx, 2);
				{
				setState(707);
				match(Star);
				setState(709);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(708);
					cvqualifierseq();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CvqualifierseqContext extends ParserRuleContext {
		public List<CvQualifierContext> cvQualifier() {
			return getRuleContexts(CvQualifierContext.class);
		}
		public CvQualifierContext cvQualifier(int i) {
			return getRuleContext(CvQualifierContext.class,i);
		}
		public CvqualifierseqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cvqualifierseq; }
	}

	public final CvqualifierseqContext cvqualifierseq() throws RecognitionException {
		CvqualifierseqContext _localctx = new CvqualifierseqContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_cvqualifierseq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(714); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(713);
					cvQualifier();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(716); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CvQualifierContext extends ParserRuleContext {
		public TerminalNode Const() { return getToken(myParser.Const, 0); }
		public TerminalNode Volatile() { return getToken(myParser.Volatile, 0); }
		public CvQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cvQualifier; }
	}

	public final CvQualifierContext cvQualifier() throws RecognitionException {
		CvQualifierContext _localctx = new CvQualifierContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_cvQualifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(718);
			_la = _input.LA(1);
			if ( !(_la==Const || _la==Volatile) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RefqualifierContext extends ParserRuleContext {
		public TerminalNode And() { return getToken(myParser.And, 0); }
		public TerminalNode AndAnd() { return getToken(myParser.AndAnd, 0); }
		public RefqualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refqualifier; }
	}

	public final RefqualifierContext refqualifier() throws RecognitionException {
		RefqualifierContext _localctx = new RefqualifierContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_refqualifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(720);
			_la = _input.LA(1);
			if ( !(_la==And || _la==AndAnd) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratoridContext extends ParserRuleContext {
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(myParser.Ellipsis, 0); }
		public DeclaratoridContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaratorid; }
	}

	public final DeclaratoridContext declaratorid() throws RecognitionException {
		DeclaratoridContext _localctx = new DeclaratoridContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_declaratorid);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(722);
				match(Ellipsis);
				}
			}

			setState(725);
			idExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TheTypeIdContext extends ParserRuleContext {
		public TypeSpecifierSeqContext typeSpecifierSeq() {
			return getRuleContext(TypeSpecifierSeqContext.class,0);
		}
		public TheTypeIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theTypeId; }
	}

	public final TheTypeIdContext theTypeId() throws RecognitionException {
		TheTypeIdContext _localctx = new TheTypeIdContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_theTypeId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(727);
			typeSpecifierSeq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterDeclarationClauseContext extends ParserRuleContext {
		public ParameterDeclarationListContext parameterDeclarationList() {
			return getRuleContext(ParameterDeclarationListContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(myParser.Ellipsis, 0); }
		public TerminalNode Comma() { return getToken(myParser.Comma, 0); }
		public ParameterDeclarationClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclarationClause; }
	}

	public final ParameterDeclarationClauseContext parameterDeclarationClause() throws RecognitionException {
		ParameterDeclarationClauseContext _localctx = new ParameterDeclarationClauseContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_parameterDeclarationClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			parameterDeclarationList();
			setState(734);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma || _la==Ellipsis) {
				{
				setState(731);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(730);
					match(Comma);
					}
				}

				setState(733);
				match(Ellipsis);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterDeclarationListContext extends ParserRuleContext {
		public List<ParameterDeclarationContext> parameterDeclaration() {
			return getRuleContexts(ParameterDeclarationContext.class);
		}
		public ParameterDeclarationContext parameterDeclaration(int i) {
			return getRuleContext(ParameterDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public ParameterDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclarationList; }
	}

	public final ParameterDeclarationListContext parameterDeclarationList() throws RecognitionException {
		ParameterDeclarationListContext _localctx = new ParameterDeclarationListContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_parameterDeclarationList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			parameterDeclaration();
			setState(741);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(737);
					match(Comma);
					setState(738);
					parameterDeclaration();
					}
					} 
				}
				setState(743);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterDeclarationContext extends ParserRuleContext {
		public DeclSpecifierSeqContext declSpecifierSeq() {
			return getRuleContext(DeclSpecifierSeqContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public InitializerClauseContext initializerClause() {
			return getRuleContext(InitializerClauseContext.class,0);
		}
		public ParameterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclaration; }
	}

	public final ParameterDeclarationContext parameterDeclaration() throws RecognitionException {
		ParameterDeclarationContext _localctx = new ParameterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_parameterDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(744);
			declSpecifierSeq();
			setState(745);
			declarator();
			setState(748);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(746);
				match(Assign);
				setState(747);
				initializerClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public DeclSpecifierSeqContext declSpecifierSeq() {
			return getRuleContext(DeclSpecifierSeqContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_functionDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(750);
				declSpecifierSeq();
				}
				break;
			}
			setState(753);
			declarator();
			setState(754);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public TerminalNode Default() { return getToken(myParser.Default, 0); }
		public TerminalNode Delete() { return getToken(myParser.Delete, 0); }
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_functionBody);
		int _la;
		try {
			setState(760);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBrace:
				enterOuterAlt(_localctx, 1);
				{
				setState(756);
				compoundStatement();
				}
				break;
			case Assign:
				enterOuterAlt(_localctx, 2);
				{
				setState(757);
				match(Assign);
				setState(758);
				_la = _input.LA(1);
				if ( !(_la==Default || _la==Delete) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(759);
				match(Semi);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitializerContext extends ParserRuleContext {
		public BraceOrEqualInitializerContext braceOrEqualInitializer() {
			return getRuleContext(BraceOrEqualInitializerContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public InitializerListContext initializerList() {
			return getRuleContext(InitializerListContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer; }
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_initializer);
		try {
			setState(767);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBrace:
			case Assign:
				enterOuterAlt(_localctx, 1);
				{
				setState(762);
				braceOrEqualInitializer();
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(763);
				match(LeftParen);
				setState(764);
				initializerList();
				setState(765);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BraceOrEqualInitializerContext extends ParserRuleContext {
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public InitializerClauseContext initializerClause() {
			return getRuleContext(InitializerClauseContext.class,0);
		}
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public BraceOrEqualInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braceOrEqualInitializer; }
	}

	public final BraceOrEqualInitializerContext braceOrEqualInitializer() throws RecognitionException {
		BraceOrEqualInitializerContext _localctx = new BraceOrEqualInitializerContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_braceOrEqualInitializer);
		try {
			setState(772);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Assign:
				enterOuterAlt(_localctx, 1);
				{
				setState(769);
				match(Assign);
				setState(770);
				initializerClause();
				}
				break;
			case LeftBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(771);
				bracedInitList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitializerClauseContext extends ParserRuleContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public InitializerClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializerClause; }
	}

	public final InitializerClauseContext initializerClause() throws RecognitionException {
		InitializerClauseContext _localctx = new InitializerClauseContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_initializerClause);
		try {
			setState(776);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case CharacterLiteral:
			case FloatingLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case PointerLiteral:
			case Auto:
			case Bool:
			case Char:
			case Delete:
			case Double:
			case Float:
			case Int:
			case Long:
			case New:
			case Operator:
			case Short:
			case Signed:
			case Sizeof:
			case Unsigned:
			case Void:
			case LeftParen:
			case Plus:
			case Minus:
			case Star:
			case And:
			case Or:
			case Tilde:
			case Not:
			case PlusPlus:
			case MinusMinus:
			case Doublecolon:
			case Identifier:
			case Stack:
			case Push:
			case Empty:
			case Pop:
			case Top:
			case Cin:
			case Cout:
			case String:
			case Length:
			case Vector:
			case Find:
			case Push_back:
				enterOuterAlt(_localctx, 1);
				{
				setState(774);
				conditionalExpression();
				}
				break;
			case LeftBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(775);
				bracedInitList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitializerListContext extends ParserRuleContext {
		public List<InitializerClauseContext> initializerClause() {
			return getRuleContexts(InitializerClauseContext.class);
		}
		public InitializerClauseContext initializerClause(int i) {
			return getRuleContext(InitializerClauseContext.class,i);
		}
		public List<TerminalNode> Ellipsis() { return getTokens(myParser.Ellipsis); }
		public TerminalNode Ellipsis(int i) {
			return getToken(myParser.Ellipsis, i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public InitializerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializerList; }
	}

	public final InitializerListContext initializerList() throws RecognitionException {
		InitializerListContext _localctx = new InitializerListContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_initializerList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(778);
			initializerClause();
			setState(780);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(779);
				match(Ellipsis);
				}
			}

			setState(789);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(782);
					match(Comma);
					setState(783);
					initializerClause();
					setState(785);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Ellipsis) {
						{
						setState(784);
						match(Ellipsis);
						}
					}

					}
					} 
				}
				setState(791);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BracedInitListContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public InitializerListContext initializerList() {
			return getRuleContext(InitializerListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(myParser.Comma, 0); }
		public BracedInitListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracedInitList; }
	}

	public final BracedInitListContext bracedInitList() throws RecognitionException {
		BracedInitListContext _localctx = new BracedInitListContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_bracedInitList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			match(LeftBrace);
			setState(797);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (LeftBrace - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
				{
				setState(793);
				initializerList();
				setState(795);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(794);
					match(Comma);
					}
				}

				}
			}

			setState(799);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public SimpleTemplateIdContext simpleTemplateId() {
			return getRuleContext(SimpleTemplateIdContext.class,0);
		}
		public MyClassContext myClass() {
			return getRuleContext(MyClassContext.class,0);
		}
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_className);
		try {
			setState(804);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(801);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(802);
				simpleTemplateId();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(803);
				myClass();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassSpecifierContext extends ParserRuleContext {
		public ClassHeadContext classHead() {
			return getRuleContext(ClassHeadContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public MemberSpecificationContext memberSpecification() {
			return getRuleContext(MemberSpecificationContext.class,0);
		}
		public ClassSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classSpecifier; }
	}

	public final ClassSpecifierContext classSpecifier() throws RecognitionException {
		ClassSpecifierContext _localctx = new ClassSpecifierContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_classSpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			classHead();
			setState(807);
			match(LeftBrace);
			setState(809);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Operator) | (1L << Private) | (1L << Protected) | (1L << Public) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (Template - 65)) | (1L << (Typedef - 65)) | (1L << (Unsigned - 65)) | (1L << (Void - 65)) | (1L << (Volatile - 65)) | (1L << (LeftParen - 65)) | (1L << (Star - 65)) | (1L << (And - 65)) | (1L << (AndAnd - 65)) | (1L << (Colon - 65)) | (1L << (Semi - 65)) | (1L << (Ellipsis - 65)))) != 0) || ((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & ((1L << (Identifier - 129)) | (1L << (Stack - 129)) | (1L << (Push - 129)) | (1L << (Empty - 129)) | (1L << (Pop - 129)) | (1L << (Top - 129)) | (1L << (Cin - 129)) | (1L << (Cout - 129)) | (1L << (String - 129)) | (1L << (Length - 129)) | (1L << (Vector - 129)) | (1L << (Find - 129)) | (1L << (Push_back - 129)))) != 0)) {
				{
				setState(808);
				memberSpecification();
				}
			}

			setState(811);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassHeadContext extends ParserRuleContext {
		public ClassKeyContext classKey() {
			return getRuleContext(ClassKeyContext.class,0);
		}
		public ClassHeadNameContext classHeadName() {
			return getRuleContext(ClassHeadNameContext.class,0);
		}
		public ClassHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classHead; }
	}

	public final ClassHeadContext classHead() throws RecognitionException {
		ClassHeadContext _localctx = new ClassHeadContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_classHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(813);
			classKey();
			setState(815);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & ((1L << (Identifier - 129)) | (1L << (Stack - 129)) | (1L << (String - 129)) | (1L << (Vector - 129)))) != 0)) {
				{
				setState(814);
				classHeadName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassHeadNameContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public ClassHeadNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classHeadName; }
	}

	public final ClassHeadNameContext classHeadName() throws RecognitionException {
		ClassHeadNameContext _localctx = new ClassHeadNameContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_classHeadName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(817);
			className();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassKeyContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(myParser.Class, 0); }
		public TerminalNode Struct() { return getToken(myParser.Struct, 0); }
		public ClassKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classKey; }
	}

	public final ClassKeyContext classKey() throws RecognitionException {
		ClassKeyContext _localctx = new ClassKeyContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_classKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(819);
			_la = _input.LA(1);
			if ( !(_la==Class || _la==Struct) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberSpecificationContext extends ParserRuleContext {
		public List<MemberdeclarationContext> memberdeclaration() {
			return getRuleContexts(MemberdeclarationContext.class);
		}
		public MemberdeclarationContext memberdeclaration(int i) {
			return getRuleContext(MemberdeclarationContext.class,i);
		}
		public List<AccessSpecifierContext> accessSpecifier() {
			return getRuleContexts(AccessSpecifierContext.class);
		}
		public AccessSpecifierContext accessSpecifier(int i) {
			return getRuleContext(AccessSpecifierContext.class,i);
		}
		public List<TerminalNode> Colon() { return getTokens(myParser.Colon); }
		public TerminalNode Colon(int i) {
			return getToken(myParser.Colon, i);
		}
		public MemberSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberSpecification; }
	}

	public final MemberSpecificationContext memberSpecification() throws RecognitionException {
		MemberSpecificationContext _localctx = new MemberSpecificationContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_memberSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(825); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(825);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Auto:
				case Bool:
				case Char:
				case Class:
				case Const:
				case Double:
				case Float:
				case Int:
				case Long:
				case Operator:
				case Short:
				case Signed:
				case Struct:
				case Template:
				case Typedef:
				case Unsigned:
				case Void:
				case Volatile:
				case LeftParen:
				case Star:
				case And:
				case AndAnd:
				case Colon:
				case Semi:
				case Ellipsis:
				case Identifier:
				case Stack:
				case Push:
				case Empty:
				case Pop:
				case Top:
				case Cin:
				case Cout:
				case String:
				case Length:
				case Vector:
				case Find:
				case Push_back:
					{
					setState(821);
					memberdeclaration();
					}
					break;
				case Private:
				case Protected:
				case Public:
					{
					setState(822);
					accessSpecifier();
					setState(823);
					match(Colon);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(827); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << Operator) | (1L << Private) | (1L << Protected) | (1L << Public) | (1L << Short) | (1L << Signed) | (1L << Struct))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (Template - 65)) | (1L << (Typedef - 65)) | (1L << (Unsigned - 65)) | (1L << (Void - 65)) | (1L << (Volatile - 65)) | (1L << (LeftParen - 65)) | (1L << (Star - 65)) | (1L << (And - 65)) | (1L << (AndAnd - 65)) | (1L << (Colon - 65)) | (1L << (Semi - 65)) | (1L << (Ellipsis - 65)))) != 0) || ((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & ((1L << (Identifier - 129)) | (1L << (Stack - 129)) | (1L << (Push - 129)) | (1L << (Empty - 129)) | (1L << (Pop - 129)) | (1L << (Top - 129)) | (1L << (Cin - 129)) | (1L << (Cout - 129)) | (1L << (String - 129)) | (1L << (Length - 129)) | (1L << (Vector - 129)) | (1L << (Find - 129)) | (1L << (Push_back - 129)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberdeclarationContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public DeclSpecifierSeqContext declSpecifierSeq() {
			return getRuleContext(DeclSpecifierSeqContext.class,0);
		}
		public MemberDeclaratorListContext memberDeclaratorList() {
			return getRuleContext(MemberDeclaratorListContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public TemplateDeclarationContext templateDeclaration() {
			return getRuleContext(TemplateDeclarationContext.class,0);
		}
		public MemberdeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberdeclaration; }
	}

	public final MemberdeclarationContext memberdeclaration() throws RecognitionException {
		MemberdeclarationContext _localctx = new MemberdeclarationContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_memberdeclaration);
		int _la;
		try {
			setState(839);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(830);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(829);
					declSpecifierSeq();
					}
					break;
				}
				setState(833);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 49)) & ~0x3f) == 0 && ((1L << (_la - 49)) & ((1L << (Operator - 49)) | (1L << (LeftParen - 49)) | (1L << (Star - 49)) | (1L << (And - 49)))) != 0) || ((((_la - 115)) & ~0x3f) == 0 && ((1L << (_la - 115)) & ((1L << (AndAnd - 115)) | (1L << (Colon - 115)) | (1L << (Ellipsis - 115)) | (1L << (Identifier - 115)) | (1L << (Push - 115)) | (1L << (Empty - 115)) | (1L << (Pop - 115)) | (1L << (Top - 115)) | (1L << (Cin - 115)) | (1L << (Cout - 115)) | (1L << (Length - 115)) | (1L << (Find - 115)) | (1L << (Push_back - 115)))) != 0)) {
					{
					setState(832);
					memberDeclaratorList();
					}
				}

				setState(835);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(836);
				functionDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(837);
				templateDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(838);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDeclaratorListContext extends ParserRuleContext {
		public List<MemberDeclaratorContext> memberDeclarator() {
			return getRuleContexts(MemberDeclaratorContext.class);
		}
		public MemberDeclaratorContext memberDeclarator(int i) {
			return getRuleContext(MemberDeclaratorContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public MemberDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaratorList; }
	}

	public final MemberDeclaratorListContext memberDeclaratorList() throws RecognitionException {
		MemberDeclaratorListContext _localctx = new MemberDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_memberDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(841);
			memberDeclarator();
			setState(846);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(842);
				match(Comma);
				setState(843);
				memberDeclarator();
				}
				}
				setState(848);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDeclaratorContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public BraceOrEqualInitializerContext braceOrEqualInitializer() {
			return getRuleContext(BraceOrEqualInitializerContext.class,0);
		}
		public TerminalNode Colon() { return getToken(myParser.Colon, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public MemberDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclarator; }
	}

	public final MemberDeclaratorContext memberDeclarator() throws RecognitionException {
		MemberDeclaratorContext _localctx = new MemberDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_memberDeclarator);
		int _la;
		try {
			setState(858);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(849);
				declarator();
				setState(851);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LeftBrace || _la==Assign) {
					{
					setState(850);
					braceOrEqualInitializer();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(854);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(853);
					match(Identifier);
					}
				}

				setState(856);
				match(Colon);
				setState(857);
				constantExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseSpecifierListContext extends ParserRuleContext {
		public List<ClassNameContext> className() {
			return getRuleContexts(ClassNameContext.class);
		}
		public ClassNameContext className(int i) {
			return getRuleContext(ClassNameContext.class,i);
		}
		public List<TerminalNode> Ellipsis() { return getTokens(myParser.Ellipsis); }
		public TerminalNode Ellipsis(int i) {
			return getToken(myParser.Ellipsis, i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public BaseSpecifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseSpecifierList; }
	}

	public final BaseSpecifierListContext baseSpecifierList() throws RecognitionException {
		BaseSpecifierListContext _localctx = new BaseSpecifierListContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_baseSpecifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(860);
			className();
			setState(862);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(861);
				match(Ellipsis);
				}
			}

			setState(871);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(864);
				match(Comma);
				setState(865);
				className();
				setState(867);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(866);
					match(Ellipsis);
					}
				}

				}
				}
				setState(873);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccessSpecifierContext extends ParserRuleContext {
		public TerminalNode Private() { return getToken(myParser.Private, 0); }
		public TerminalNode Protected() { return getToken(myParser.Protected, 0); }
		public TerminalNode Public() { return getToken(myParser.Public, 0); }
		public AccessSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessSpecifier; }
	}

	public final AccessSpecifierContext accessSpecifier() throws RecognitionException {
		AccessSpecifierContext _localctx = new AccessSpecifierContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_accessSpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(874);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Private) | (1L << Protected) | (1L << Public))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemInitializerListContext extends ParserRuleContext {
		public List<MemInitializerContext> memInitializer() {
			return getRuleContexts(MemInitializerContext.class);
		}
		public MemInitializerContext memInitializer(int i) {
			return getRuleContext(MemInitializerContext.class,i);
		}
		public List<TerminalNode> Ellipsis() { return getTokens(myParser.Ellipsis); }
		public TerminalNode Ellipsis(int i) {
			return getToken(myParser.Ellipsis, i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public MemInitializerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memInitializerList; }
	}

	public final MemInitializerListContext memInitializerList() throws RecognitionException {
		MemInitializerListContext _localctx = new MemInitializerListContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_memInitializerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(876);
			memInitializer();
			setState(878);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(877);
				match(Ellipsis);
				}
			}

			setState(887);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(880);
				match(Comma);
				setState(881);
				memInitializer();
				setState(883);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(882);
					match(Ellipsis);
					}
				}

				}
				}
				setState(889);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemInitializerContext extends ParserRuleContext {
		public MeminitializeridContext meminitializerid() {
			return getRuleContext(MeminitializeridContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public BracedInitListContext bracedInitList() {
			return getRuleContext(BracedInitListContext.class,0);
		}
		public InitializerListContext initializerList() {
			return getRuleContext(InitializerListContext.class,0);
		}
		public MemInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memInitializer; }
	}

	public final MemInitializerContext memInitializer() throws RecognitionException {
		MemInitializerContext _localctx = new MemInitializerContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_memInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(890);
			meminitializerid();
			setState(897);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
				{
				setState(891);
				match(LeftParen);
				setState(893);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (LeftParen - 75)) | (1L << (LeftBrace - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
					{
					setState(892);
					initializerList();
					}
				}

				setState(895);
				match(RightParen);
				}
				break;
			case LeftBrace:
				{
				setState(896);
				bracedInitList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeminitializeridContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public MeminitializeridContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_meminitializerid; }
	}

	public final MeminitializeridContext meminitializerid() throws RecognitionException {
		MeminitializeridContext _localctx = new MeminitializeridContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_meminitializerid);
		try {
			setState(901);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(899);
				className();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(900);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorFunctionIdContext extends ParserRuleContext {
		public TerminalNode Operator() { return getToken(myParser.Operator, 0); }
		public TheOperatorContext theOperator() {
			return getRuleContext(TheOperatorContext.class,0);
		}
		public OperatorFunctionIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorFunctionId; }
	}

	public final OperatorFunctionIdContext operatorFunctionId() throws RecognitionException {
		OperatorFunctionIdContext _localctx = new OperatorFunctionIdContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_operatorFunctionId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(903);
			match(Operator);
			setState(904);
			theOperator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralOperatorIdContext extends ParserRuleContext {
		public TerminalNode Operator() { return getToken(myParser.Operator, 0); }
		public TerminalNode StringLiteral() { return getToken(myParser.StringLiteral, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public LiteralOperatorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalOperatorId; }
	}

	public final LiteralOperatorIdContext literalOperatorId() throws RecognitionException {
		LiteralOperatorIdContext _localctx = new LiteralOperatorIdContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_literalOperatorId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(906);
			match(Operator);
			{
			setState(907);
			match(StringLiteral);
			setState(908);
			match(Identifier);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateDeclarationContext extends ParserRuleContext {
		public TerminalNode Template() { return getToken(myParser.Template, 0); }
		public TerminalNode Less() { return getToken(myParser.Less, 0); }
		public TemplateparameterListContext templateparameterList() {
			return getRuleContext(TemplateparameterListContext.class,0);
		}
		public TerminalNode Greater() { return getToken(myParser.Greater, 0); }
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TemplateDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateDeclaration; }
	}

	public final TemplateDeclarationContext templateDeclaration() throws RecognitionException {
		TemplateDeclarationContext _localctx = new TemplateDeclarationContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_templateDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(910);
			match(Template);
			setState(911);
			match(Less);
			setState(912);
			templateparameterList();
			setState(913);
			match(Greater);
			setState(914);
			declaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateparameterListContext extends ParserRuleContext {
		public List<TemplateParameterContext> templateParameter() {
			return getRuleContexts(TemplateParameterContext.class);
		}
		public TemplateParameterContext templateParameter(int i) {
			return getRuleContext(TemplateParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public TemplateparameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateparameterList; }
	}

	public final TemplateparameterListContext templateparameterList() throws RecognitionException {
		TemplateparameterListContext _localctx = new TemplateparameterListContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_templateparameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(916);
			templateParameter();
			setState(921);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(917);
				match(Comma);
				setState(918);
				templateParameter();
				}
				}
				setState(923);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateParameterContext extends ParserRuleContext {
		public TypeParameterContext typeParameter() {
			return getRuleContext(TypeParameterContext.class,0);
		}
		public ParameterDeclarationContext parameterDeclaration() {
			return getRuleContext(ParameterDeclarationContext.class,0);
		}
		public TemplateParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateParameter; }
	}

	public final TemplateParameterContext templateParameter() throws RecognitionException {
		TemplateParameterContext _localctx = new TemplateParameterContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_templateParameter);
		try {
			setState(926);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(924);
				typeParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(925);
				parameterDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(myParser.Class, 0); }
		public TerminalNode Typename_() { return getToken(myParser.Typename_, 0); }
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public TheTypeIdContext theTypeId() {
			return getRuleContext(TheTypeIdContext.class,0);
		}
		public TerminalNode Template() { return getToken(myParser.Template, 0); }
		public TerminalNode Less() { return getToken(myParser.Less, 0); }
		public TemplateparameterListContext templateparameterList() {
			return getRuleContext(TemplateparameterListContext.class,0);
		}
		public TerminalNode Greater() { return getToken(myParser.Greater, 0); }
		public TerminalNode Ellipsis() { return getToken(myParser.Ellipsis, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_typeParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Class:
			case Template:
				{
				setState(933);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Template) {
					{
					setState(928);
					match(Template);
					setState(929);
					match(Less);
					setState(930);
					templateparameterList();
					setState(931);
					match(Greater);
					}
				}

				setState(935);
				match(Class);
				}
				break;
			case Typename_:
				{
				setState(936);
				match(Typename_);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(950);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				{
				{
				setState(940);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(939);
					match(Ellipsis);
					}
				}

				setState(943);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(942);
					match(Identifier);
					}
				}

				}
				}
				break;
			case 2:
				{
				{
				setState(946);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(945);
					match(Identifier);
					}
				}

				setState(948);
				match(Assign);
				setState(949);
				theTypeId();
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTemplateIdContext extends ParserRuleContext {
		public TemplateNameContext templateName() {
			return getRuleContext(TemplateNameContext.class,0);
		}
		public TerminalNode Less() { return getToken(myParser.Less, 0); }
		public TerminalNode Greater() { return getToken(myParser.Greater, 0); }
		public TemplateArgumentListContext templateArgumentList() {
			return getRuleContext(TemplateArgumentListContext.class,0);
		}
		public SimpleTemplateIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTemplateId; }
	}

	public final SimpleTemplateIdContext simpleTemplateId() throws RecognitionException {
		SimpleTemplateIdContext _localctx = new SimpleTemplateIdContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_simpleTemplateId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(952);
			templateName();
			setState(953);
			match(Less);
			setState(955);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof) | (1L << Struct))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (Volatile - 75)) | (1L << (LeftParen - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
				{
				setState(954);
				templateArgumentList();
				}
			}

			setState(957);
			match(Greater);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateIdContext extends ParserRuleContext {
		public SimpleTemplateIdContext simpleTemplateId() {
			return getRuleContext(SimpleTemplateIdContext.class,0);
		}
		public TerminalNode Less() { return getToken(myParser.Less, 0); }
		public TerminalNode Greater() { return getToken(myParser.Greater, 0); }
		public OperatorFunctionIdContext operatorFunctionId() {
			return getRuleContext(OperatorFunctionIdContext.class,0);
		}
		public LiteralOperatorIdContext literalOperatorId() {
			return getRuleContext(LiteralOperatorIdContext.class,0);
		}
		public TemplateArgumentListContext templateArgumentList() {
			return getRuleContext(TemplateArgumentListContext.class,0);
		}
		public TemplateIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateId; }
	}

	public final TemplateIdContext templateId() throws RecognitionException {
		TemplateIdContext _localctx = new TemplateIdContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_templateId);
		int _la;
		try {
			setState(970);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(959);
				simpleTemplateId();
				}
				break;
			case Operator:
				enterOuterAlt(_localctx, 2);
				{
				setState(962);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
				case 1:
					{
					setState(960);
					operatorFunctionId();
					}
					break;
				case 2:
					{
					setState(961);
					literalOperatorId();
					}
					break;
				}
				setState(964);
				match(Less);
				setState(966);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral) | (1L << Auto) | (1L << Bool) | (1L << Char) | (1L << Class) | (1L << Const) | (1L << Delete) | (1L << Double) | (1L << Float) | (1L << Int) | (1L << Long) | (1L << New) | (1L << Operator) | (1L << Short) | (1L << Signed) | (1L << Sizeof) | (1L << Struct))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Unsigned - 75)) | (1L << (Void - 75)) | (1L << (Volatile - 75)) | (1L << (LeftParen - 75)) | (1L << (Plus - 75)) | (1L << (Minus - 75)) | (1L << (Star - 75)) | (1L << (And - 75)) | (1L << (Or - 75)) | (1L << (Tilde - 75)) | (1L << (Not - 75)) | (1L << (PlusPlus - 75)) | (1L << (MinusMinus - 75)) | (1L << (Doublecolon - 75)) | (1L << (Identifier - 75)) | (1L << (Stack - 75)))) != 0) || ((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (String - 139)) | (1L << (Length - 139)) | (1L << (Vector - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) {
					{
					setState(965);
					templateArgumentList();
					}
				}

				setState(968);
				match(Greater);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TemplateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateName; }
	}

	public final TemplateNameContext templateName() throws RecognitionException {
		TemplateNameContext _localctx = new TemplateNameContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_templateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(972);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateArgumentListContext extends ParserRuleContext {
		public List<TemplateArgumentContext> templateArgument() {
			return getRuleContexts(TemplateArgumentContext.class);
		}
		public TemplateArgumentContext templateArgument(int i) {
			return getRuleContext(TemplateArgumentContext.class,i);
		}
		public List<TerminalNode> Ellipsis() { return getTokens(myParser.Ellipsis); }
		public TerminalNode Ellipsis(int i) {
			return getToken(myParser.Ellipsis, i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public TemplateArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateArgumentList; }
	}

	public final TemplateArgumentListContext templateArgumentList() throws RecognitionException {
		TemplateArgumentListContext _localctx = new TemplateArgumentListContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_templateArgumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974);
			templateArgument();
			setState(976);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(975);
				match(Ellipsis);
				}
			}

			setState(985);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(978);
				match(Comma);
				setState(979);
				templateArgument();
				setState(981);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(980);
					match(Ellipsis);
					}
				}

				}
				}
				setState(987);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateArgumentContext extends ParserRuleContext {
		public TheTypeIdContext theTypeId() {
			return getRuleContext(TheTypeIdContext.class,0);
		}
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TemplateArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateArgument; }
	}

	public final TemplateArgumentContext templateArgument() throws RecognitionException {
		TemplateArgumentContext _localctx = new TemplateArgumentContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_templateArgument);
		try {
			setState(991);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(988);
				theTypeId();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(989);
				constantExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(990);
				idExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitInstantiationContext extends ParserRuleContext {
		public TerminalNode Template() { return getToken(myParser.Template, 0); }
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExplicitInstantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitInstantiation; }
	}

	public final ExplicitInstantiationContext explicitInstantiation() throws RecognitionException {
		ExplicitInstantiationContext _localctx = new ExplicitInstantiationContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_explicitInstantiation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			match(Template);
			setState(994);
			declaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TheOperatorContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(myParser.New, 0); }
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public TerminalNode Delete() { return getToken(myParser.Delete, 0); }
		public TerminalNode Plus() { return getToken(myParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(myParser.Minus, 0); }
		public TerminalNode Star() { return getToken(myParser.Star, 0); }
		public TerminalNode Div() { return getToken(myParser.Div, 0); }
		public TerminalNode Mod() { return getToken(myParser.Mod, 0); }
		public TerminalNode Caret() { return getToken(myParser.Caret, 0); }
		public TerminalNode And() { return getToken(myParser.And, 0); }
		public TerminalNode Or() { return getToken(myParser.Or, 0); }
		public TerminalNode Tilde() { return getToken(myParser.Tilde, 0); }
		public TerminalNode Not() { return getToken(myParser.Not, 0); }
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public List<TerminalNode> Greater() { return getTokens(myParser.Greater); }
		public TerminalNode Greater(int i) {
			return getToken(myParser.Greater, i);
		}
		public List<TerminalNode> Less() { return getTokens(myParser.Less); }
		public TerminalNode Less(int i) {
			return getToken(myParser.Less, i);
		}
		public TerminalNode GreaterEqual() { return getToken(myParser.GreaterEqual, 0); }
		public TerminalNode PlusAssign() { return getToken(myParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(myParser.MinusAssign, 0); }
		public TerminalNode StarAssign() { return getToken(myParser.StarAssign, 0); }
		public TerminalNode ModAssign() { return getToken(myParser.ModAssign, 0); }
		public TerminalNode XorAssign() { return getToken(myParser.XorAssign, 0); }
		public TerminalNode AndAssign() { return getToken(myParser.AndAssign, 0); }
		public TerminalNode OrAssign() { return getToken(myParser.OrAssign, 0); }
		public TerminalNode RightShiftAssign() { return getToken(myParser.RightShiftAssign, 0); }
		public TerminalNode LeftShiftAssign() { return getToken(myParser.LeftShiftAssign, 0); }
		public TerminalNode Equal() { return getToken(myParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(myParser.NotEqual, 0); }
		public TerminalNode LessEqual() { return getToken(myParser.LessEqual, 0); }
		public TerminalNode AndAnd() { return getToken(myParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(myParser.OrOr, 0); }
		public TerminalNode PlusPlus() { return getToken(myParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(myParser.MinusMinus, 0); }
		public TerminalNode Comma() { return getToken(myParser.Comma, 0); }
		public TerminalNode ArrowStar() { return getToken(myParser.ArrowStar, 0); }
		public TerminalNode Arrow() { return getToken(myParser.Arrow, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TheOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theOperator; }
	}

	public final TheOperatorContext theOperator() throws RecognitionException {
		TheOperatorContext _localctx = new TheOperatorContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_theOperator);
		try {
			setState(1047);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(996);
				match(New);
				setState(999);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
				case 1:
					{
					setState(997);
					match(LeftBracket);
					setState(998);
					match(RightBracket);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1001);
				match(Delete);
				setState(1004);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
				case 1:
					{
					setState(1002);
					match(LeftBracket);
					setState(1003);
					match(RightBracket);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1006);
				match(Plus);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1007);
				match(Minus);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1008);
				match(Star);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1009);
				match(Div);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1010);
				match(Mod);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1011);
				match(Caret);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1012);
				match(And);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1013);
				match(Or);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1014);
				match(Tilde);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1015);
				match(Not);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1016);
				match(Assign);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1017);
				match(Greater);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1018);
				match(Less);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1019);
				match(GreaterEqual);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1020);
				match(PlusAssign);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(1021);
				match(MinusAssign);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1022);
				match(StarAssign);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(1023);
				match(ModAssign);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(1024);
				match(XorAssign);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(1025);
				match(AndAssign);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(1026);
				match(OrAssign);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(1027);
				match(Less);
				setState(1028);
				match(Less);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(1029);
				match(Greater);
				setState(1030);
				match(Greater);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(1031);
				match(RightShiftAssign);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(1032);
				match(LeftShiftAssign);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(1033);
				match(Equal);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(1034);
				match(NotEqual);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(1035);
				match(LessEqual);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(1036);
				match(AndAnd);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(1037);
				match(OrOr);
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(1038);
				match(PlusPlus);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 34);
				{
				setState(1039);
				match(MinusMinus);
				}
				break;
			case 35:
				enterOuterAlt(_localctx, 35);
				{
				setState(1040);
				match(Comma);
				}
				break;
			case 36:
				enterOuterAlt(_localctx, 36);
				{
				setState(1041);
				match(ArrowStar);
				}
				break;
			case 37:
				enterOuterAlt(_localctx, 37);
				{
				setState(1042);
				match(Arrow);
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 38);
				{
				setState(1043);
				match(LeftParen);
				setState(1044);
				match(RightParen);
				}
				break;
			case 39:
				enterOuterAlt(_localctx, 39);
				{
				setState(1045);
				match(LeftBracket);
				setState(1046);
				match(RightBracket);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(myParser.IntegerLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(myParser.CharacterLiteral, 0); }
		public TerminalNode FloatingLiteral() { return getToken(myParser.FloatingLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(myParser.StringLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(myParser.BooleanLiteral, 0); }
		public TerminalNode PointerLiteral() { return getToken(myParser.PointerLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1049);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << PointerLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MyIdContext extends ParserRuleContext {
		public TerminalNode Length() { return getToken(myParser.Length, 0); }
		public TerminalNode Push() { return getToken(myParser.Push, 0); }
		public TerminalNode Empty() { return getToken(myParser.Empty, 0); }
		public TerminalNode Pop() { return getToken(myParser.Pop, 0); }
		public TerminalNode Top() { return getToken(myParser.Top, 0); }
		public TerminalNode Cin() { return getToken(myParser.Cin, 0); }
		public TerminalNode Cout() { return getToken(myParser.Cout, 0); }
		public TerminalNode Find() { return getToken(myParser.Find, 0); }
		public TerminalNode Push_back() { return getToken(myParser.Push_back, 0); }
		public MyIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_myId; }
	}

	public final MyIdContext myId() throws RecognitionException {
		MyIdContext _localctx = new MyIdContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_myId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1051);
			_la = _input.LA(1);
			if ( !(((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & ((1L << (Push - 139)) | (1L << (Empty - 139)) | (1L << (Pop - 139)) | (1L << (Top - 139)) | (1L << (Cin - 139)) | (1L << (Cout - 139)) | (1L << (Length - 139)) | (1L << (Find - 139)) | (1L << (Push_back - 139)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MyClassContext extends ParserRuleContext {
		public TerminalNode Stack() { return getToken(myParser.Stack, 0); }
		public TerminalNode Vector() { return getToken(myParser.Vector, 0); }
		public TerminalNode String() { return getToken(myParser.String, 0); }
		public MyClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_myClass; }
	}

	public final MyClassContext myClass() throws RecognitionException {
		MyClassContext _localctx = new MyClassContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_myClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1053);
			_la = _input.LA(1);
			if ( !(((((_la - 138)) & ~0x3f) == 0 && ((1L << (_la - 138)) & ((1L << (Stack - 138)) | (1L << (String - 138)) | (1L << (Vector - 138)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return postfixExpression_sempred((PostfixExpressionContext)_localctx, predIndex);
		case 9:
			return noPointerNewDeclarator_sempred((NoPointerNewDeclaratorContext)_localctx, predIndex);
		case 55:
			return noPointerDeclarator_sempred((NoPointerDeclaratorContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean postfixExpression_sempred(PostfixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean noPointerNewDeclarator_sempred(NoPointerNewDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean noPointerDeclarator_sempred(NoPointerDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0098\u0422\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\3\2\5\2\u00d2\n\2"+
		"\3\2\3\2\3\3\6\3\u00d7\n\3\r\3\16\3\u00d8\3\3\3\3\3\3\3\3\3\3\5\3\u00e0"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\5\4\u00e7\n\4\3\5\3\5\3\5\3\5\3\5\5\5\u00ee\n"+
		"\5\3\5\3\5\5\5\u00f2\n\5\5\5\u00f4\n\5\3\5\3\5\3\5\3\5\5\5\u00fa\n\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5\u0101\n\5\3\5\3\5\3\5\3\5\5\5\u0107\n\5\3\5\3\5"+
		"\3\5\7\5\u010c\n\5\f\5\16\5\u010f\13\5\3\6\3\6\3\6\3\6\3\6\5\6\u0116\n"+
		"\6\3\6\3\6\3\6\5\6\u011b\n\6\3\7\3\7\3\b\5\b\u0120\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u0128\n\b\3\t\3\t\5\t\u012c\n\t\3\n\3\n\5\n\u0130\n\n\3\n"+
		"\5\n\u0133\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u013f\n\13\f\13\16\13\u0142\13\13\3\f\5\f\u0145\n\f\3\f\3\f\3\f\5\f\u014a"+
		"\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0154\n\r\3\16\3\16\3\16\7\16"+
		"\u0159\n\16\f\16\16\16\u015c\13\16\3\17\3\17\3\17\7\17\u0161\n\17\f\17"+
		"\16\17\u0164\13\17\3\20\3\20\3\20\7\20\u0169\n\20\f\20\16\20\u016c\13"+
		"\20\3\21\3\21\3\21\3\21\7\21\u0172\n\21\f\21\16\21\u0175\13\21\3\22\3"+
		"\22\3\22\3\22\5\22\u017b\n\22\3\23\3\23\3\23\7\23\u0180\n\23\f\23\16\23"+
		"\u0183\13\23\3\24\3\24\3\24\7\24\u0188\n\24\f\24\16\24\u018b\13\24\3\25"+
		"\3\25\3\25\7\25\u0190\n\25\f\25\16\25\u0193\13\25\3\26\3\26\3\26\7\26"+
		"\u0198\n\26\f\26\16\26\u019b\13\26\3\27\3\27\3\27\7\27\u01a0\n\27\f\27"+
		"\16\27\u01a3\13\27\3\30\3\30\3\30\7\30\u01a8\n\30\f\30\16\30\u01ab\13"+
		"\30\3\31\3\31\3\31\7\31\u01b0\n\31\f\31\16\31\u01b3\13\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\5\32\u01bb\n\32\3\33\3\33\3\34\3\34\3\34\7\34\u01c2"+
		"\n\34\f\34\16\34\u01c5\13\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\5\36\u01d0\n\36\5\36\u01d2\n\36\3\37\3\37\3\37\3\37\5\37\u01d8\n\37"+
		"\3\37\3\37\3\37\3 \5 \u01de\n \3 \3 \3!\3!\5!\u01e4\n!\3!\3!\3\"\6\"\u01e9"+
		"\n\"\r\"\16\"\u01ea\3#\3#\3#\3#\3#\3#\3#\5#\u01f4\n#\3#\3#\3#\3#\3#\3"+
		"#\5#\u01fc\n#\3$\3$\3$\3$\3$\3$\5$\u0204\n$\5$\u0206\n$\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u021a\n%\3%\3%\5%\u021e\n"+
		"%\3%\3%\3%\5%\u0223\n%\3&\3&\5&\u0227\n&\3\'\3\'\3\'\3\'\3\'\5\'\u022e"+
		"\n\'\5\'\u0230\n\'\3\'\3\'\3(\6(\u0235\n(\r(\16(\u0236\3)\3)\3)\3)\3)"+
		"\5)\u023e\n)\3*\5*\u0241\n*\3*\5*\u0244\n*\3*\3*\3+\3+\5+\u024a\n+\3,"+
		"\6,\u024d\n,\r,\16,\u024e\3-\3-\3.\3.\5.\u0255\n.\3/\3/\5/\u0259\n/\3"+
		"\60\6\60\u025c\n\60\r\60\16\60\u025d\3\61\3\61\3\62\3\62\3\63\3\63\3\63"+
		"\5\63\u0267\n\63\3\63\6\63\u026a\n\63\r\63\16\63\u026b\3\63\5\63\u026f"+
		"\n\63\3\63\3\63\3\63\5\63\u0274\n\63\3\63\7\63\u0277\n\63\f\63\16\63\u027a"+
		"\13\63\3\63\3\63\3\63\5\63\u027f\n\63\3\63\3\63\3\63\5\63\u0284\n\63\3"+
		"\64\3\64\3\64\5\64\u0289\n\64\3\65\3\65\3\65\7\65\u028e\n\65\f\65\16\65"+
		"\u0291\13\65\3\66\3\66\5\66\u0295\n\66\3\67\3\67\38\38\58\u029b\n8\78"+
		"\u029d\n8\f8\168\u02a0\138\38\38\39\39\39\39\39\39\59\u02aa\n9\39\39\3"+
		"9\39\59\u02b0\n9\39\59\u02b3\n9\79\u02b5\n9\f9\169\u02b8\139\3:\3:\5:"+
		"\u02bc\n:\3:\3:\5:\u02c0\n:\3:\5:\u02c3\n:\3;\3;\3;\5;\u02c8\n;\5;\u02ca"+
		"\n;\3<\6<\u02cd\n<\r<\16<\u02ce\3=\3=\3>\3>\3?\5?\u02d6\n?\3?\3?\3@\3"+
		"@\3A\3A\5A\u02de\nA\3A\5A\u02e1\nA\3B\3B\3B\7B\u02e6\nB\fB\16B\u02e9\13"+
		"B\3C\3C\3C\3C\5C\u02ef\nC\3D\5D\u02f2\nD\3D\3D\3D\3E\3E\3E\3E\5E\u02fb"+
		"\nE\3F\3F\3F\3F\3F\5F\u0302\nF\3G\3G\3G\5G\u0307\nG\3H\3H\5H\u030b\nH"+
		"\3I\3I\5I\u030f\nI\3I\3I\3I\5I\u0314\nI\7I\u0316\nI\fI\16I\u0319\13I\3"+
		"J\3J\3J\5J\u031e\nJ\5J\u0320\nJ\3J\3J\3K\3K\3K\5K\u0327\nK\3L\3L\3L\5"+
		"L\u032c\nL\3L\3L\3M\3M\5M\u0332\nM\3N\3N\3O\3O\3P\3P\3P\3P\6P\u033c\n"+
		"P\rP\16P\u033d\3Q\5Q\u0341\nQ\3Q\5Q\u0344\nQ\3Q\3Q\3Q\3Q\5Q\u034a\nQ\3"+
		"R\3R\3R\7R\u034f\nR\fR\16R\u0352\13R\3S\3S\5S\u0356\nS\3S\5S\u0359\nS"+
		"\3S\3S\5S\u035d\nS\3T\3T\5T\u0361\nT\3T\3T\3T\5T\u0366\nT\7T\u0368\nT"+
		"\fT\16T\u036b\13T\3U\3U\3V\3V\5V\u0371\nV\3V\3V\3V\5V\u0376\nV\7V\u0378"+
		"\nV\fV\16V\u037b\13V\3W\3W\3W\5W\u0380\nW\3W\3W\5W\u0384\nW\3X\3X\5X\u0388"+
		"\nX\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\\7\\\u039a\n\\\f"+
		"\\\16\\\u039d\13\\\3]\3]\5]\u03a1\n]\3^\3^\3^\3^\3^\5^\u03a8\n^\3^\3^"+
		"\5^\u03ac\n^\3^\5^\u03af\n^\3^\5^\u03b2\n^\3^\5^\u03b5\n^\3^\3^\5^\u03b9"+
		"\n^\3_\3_\3_\5_\u03be\n_\3_\3_\3`\3`\3`\5`\u03c5\n`\3`\3`\5`\u03c9\n`"+
		"\3`\3`\5`\u03cd\n`\3a\3a\3b\3b\5b\u03d3\nb\3b\3b\3b\5b\u03d8\nb\7b\u03da"+
		"\nb\fb\16b\u03dd\13b\3c\3c\3c\5c\u03e2\nc\3d\3d\3d\3e\3e\3e\5e\u03ea\n"+
		"e\3e\3e\3e\5e\u03ef\ne\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3"+
		"e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3"+
		"e\3e\3e\5e\u041a\ne\3f\3f\3g\3g\3h\3h\3h\3\u024e\5\b\24pi\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^"+
		"`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090"+
		"\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8"+
		"\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0"+
		"\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\2\25\4\2{{\u0080\u0080\3\2"+
		"wx\4\2Z\\`c\4\2zz\u0081\u0081\3\2\\^\3\2Z[\4\2efst\3\2qr\4\2ddgp\4\2-"+
		"-;;\4\2<<MM\4\2``uu\4\2\25\25QQ\3\2\32\33\4\2\24\24AA\3\2\65\67\3\2\3"+
		"\b\5\2\u008d\u0092\u0095\u0095\u0097\u0098\5\2\u008c\u008c\u0094\u0094"+
		"\u0096\u0096\2\u048c\2\u00d1\3\2\2\2\4\u00df\3\2\2\2\6\u00e6\3\2\2\2\b"+
		"\u00f3\3\2\2\2\n\u011a\3\2\2\2\f\u011c\3\2\2\2\16\u011f\3\2\2\2\20\u0129"+
		"\3\2\2\2\22\u0132\3\2\2\2\24\u0134\3\2\2\2\26\u0144\3\2\2\2\30\u0153\3"+
		"\2\2\2\32\u0155\3\2\2\2\34\u015d\3\2\2\2\36\u0165\3\2\2\2 \u016d\3\2\2"+
		"\2\"\u017a\3\2\2\2$\u017c\3\2\2\2&\u0184\3\2\2\2(\u018c\3\2\2\2*\u0194"+
		"\3\2\2\2,\u019c\3\2\2\2.\u01a4\3\2\2\2\60\u01ac\3\2\2\2\62\u01b4\3\2\2"+
		"\2\64\u01bc\3\2\2\2\66\u01be\3\2\2\28\u01c6\3\2\2\2:\u01d1\3\2\2\2<\u01d7"+
		"\3\2\2\2>\u01dd\3\2\2\2@\u01e1\3\2\2\2B\u01e8\3\2\2\2D\u01fb\3\2\2\2F"+
		"\u0205\3\2\2\2H\u0222\3\2\2\2J\u0226\3\2\2\2L\u022f\3\2\2\2N\u0234\3\2"+
		"\2\2P\u023d\3\2\2\2R\u0240\3\2\2\2T\u0249\3\2\2\2V\u024c\3\2\2\2X\u0250"+
		"\3\2\2\2Z\u0254\3\2\2\2\\\u0258\3\2\2\2^\u025b\3\2\2\2`\u025f\3\2\2\2"+
		"b\u0261\3\2\2\2d\u0283\3\2\2\2f\u0288\3\2\2\2h\u028a\3\2\2\2j\u0292\3"+
		"\2\2\2l\u0296\3\2\2\2n\u029e\3\2\2\2p\u02a9\3\2\2\2r\u02b9\3\2\2\2t\u02c9"+
		"\3\2\2\2v\u02cc\3\2\2\2x\u02d0\3\2\2\2z\u02d2\3\2\2\2|\u02d5\3\2\2\2~"+
		"\u02d9\3\2\2\2\u0080\u02db\3\2\2\2\u0082\u02e2\3\2\2\2\u0084\u02ea\3\2"+
		"\2\2\u0086\u02f1\3\2\2\2\u0088\u02fa\3\2\2\2\u008a\u0301\3\2\2\2\u008c"+
		"\u0306\3\2\2\2\u008e\u030a\3\2\2\2\u0090\u030c\3\2\2\2\u0092\u031a\3\2"+
		"\2\2\u0094\u0326\3\2\2\2\u0096\u0328\3\2\2\2\u0098\u032f\3\2\2\2\u009a"+
		"\u0333\3\2\2\2\u009c\u0335\3\2\2\2\u009e\u033b\3\2\2\2\u00a0\u0349\3\2"+
		"\2\2\u00a2\u034b\3\2\2\2\u00a4\u035c\3\2\2\2\u00a6\u035e\3\2\2\2\u00a8"+
		"\u036c\3\2\2\2\u00aa\u036e\3\2\2\2\u00ac\u037c\3\2\2\2\u00ae\u0387\3\2"+
		"\2\2\u00b0\u0389\3\2\2\2\u00b2\u038c\3\2\2\2\u00b4\u0390\3\2\2\2\u00b6"+
		"\u0396\3\2\2\2\u00b8\u03a0\3\2\2\2\u00ba\u03ab\3\2\2\2\u00bc\u03ba\3\2"+
		"\2\2\u00be\u03cc\3\2\2\2\u00c0\u03ce\3\2\2\2\u00c2\u03d0\3\2\2\2\u00c4"+
		"\u03e1\3\2\2\2\u00c6\u03e3\3\2\2\2\u00c8\u0419\3\2\2\2\u00ca\u041b\3\2"+
		"\2\2\u00cc\u041d\3\2\2\2\u00ce\u041f\3\2\2\2\u00d0\u00d2\5N(\2\u00d1\u00d0"+
		"\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\2\2\3\u00d4"+
		"\3\3\2\2\2\u00d5\u00d7\5\u00caf\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00e0\3\2\2\2\u00da"+
		"\u00db\7T\2\2\u00db\u00dc\5\66\34\2\u00dc\u00dd\7U\2\2\u00dd\u00e0\3\2"+
		"\2\2\u00de\u00e0\5\6\4\2\u00df\u00d6\3\2\2\2\u00df\u00da\3\2\2\2\u00df"+
		"\u00de\3\2\2\2\u00e0\5\3\2\2\2\u00e1\u00e7\7\u0083\2\2\u00e2\u00e7\5\u00b0"+
		"Y\2\u00e3\u00e7\5\u00b2Z\2\u00e4\u00e7\5\u00be`\2\u00e5\u00e7\5\u00cc"+
		"g\2\u00e6\u00e1\3\2\2\2\u00e6\u00e2\3\2\2\2\u00e6\u00e3\3\2\2\2\u00e6"+
		"\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\7\3\2\2\2\u00e8\u00e9\b\5\1\2"+
		"\u00e9\u00f4\5\4\3\2\u00ea\u00f1\5d\63\2\u00eb\u00ed\7T\2\2\u00ec\u00ee"+
		"\5\u0090I\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\3\2\2"+
		"\2\u00ef\u00f2\7U\2\2\u00f0\u00f2\5\u0092J\2\u00f1\u00eb\3\2\2\2\u00f1"+
		"\u00f0\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00e8\3\2\2\2\u00f3\u00ea\3\2"+
		"\2\2\u00f4\u010d\3\2\2\2\u00f5\u00f6\f\7\2\2\u00f6\u00f9\7V\2\2\u00f7"+
		"\u00fa\5\66\34\2\u00f8\u00fa\5\u0092J\2\u00f9\u00f7\3\2\2\2\u00f9\u00f8"+
		"\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\7W\2\2\u00fc\u010c\3\2\2\2\u00fd"+
		"\u00fe\f\6\2\2\u00fe\u0100\7T\2\2\u00ff\u0101\5\u0090I\2\u0100\u00ff\3"+
		"\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u010c\7U\2\2\u0103"+
		"\u0104\f\4\2\2\u0104\u0106\t\2\2\2\u0105\u0107\7C\2\2\u0106\u0105\3\2"+
		"\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010c\5\6\4\2\u0109"+
		"\u010a\f\3\2\2\u010a\u010c\t\3\2\2\u010b\u00f5\3\2\2\2\u010b\u00fd\3\2"+
		"\2\2\u010b\u0103\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010f\3\2\2\2\u010d"+
		"\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\t\3\2\2\2\u010f\u010d\3\2\2\2"+
		"\u0110\u011b\5\b\5\2\u0111\u0116\7w\2\2\u0112\u0116\7x\2\2\u0113\u0116"+
		"\5\f\7\2\u0114\u0116\7=\2\2\u0115\u0111\3\2\2\2\u0115\u0112\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u011b\5\n"+
		"\6\2\u0118\u011b\5\16\b\2\u0119\u011b\5\26\f\2\u011a\u0110\3\2\2\2\u011a"+
		"\u0115\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b\13\3\2\2"+
		"\2\u011c\u011d\t\4\2\2\u011d\r\3\2\2\2\u011e\u0120\7~\2\2\u011f\u011e"+
		"\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0127\7\60\2\2"+
		"\u0122\u0128\5\20\t\2\u0123\u0124\7T\2\2\u0124\u0125\5~@\2\u0125\u0126"+
		"\7U\2\2\u0126\u0128\3\2\2\2\u0127\u0122\3\2\2\2\u0127\u0123\3\2\2\2\u0128"+
		"\17\3\2\2\2\u0129\u012b\5^\60\2\u012a\u012c\5\22\n\2\u012b\u012a\3\2\2"+
		"\2\u012b\u012c\3\2\2\2\u012c\21\3\2\2\2\u012d\u012f\5t;\2\u012e\u0130"+
		"\5\22\n\2\u012f\u012e\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0133\3\2\2\2"+
		"\u0131\u0133\5\24\13\2\u0132\u012d\3\2\2\2\u0132\u0131\3\2\2\2\u0133\23"+
		"\3\2\2\2\u0134\u0135\b\13\1\2\u0135\u0136\7V\2\2\u0136\u0137\5\66\34\2"+
		"\u0137\u0138\7W\2\2\u0138\u0140\3\2\2\2\u0139\u013a\f\3\2\2\u013a\u013b"+
		"\7V\2\2\u013b\u013c\58\35\2\u013c\u013d\7W\2\2\u013d\u013f\3\2\2\2\u013e"+
		"\u0139\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2"+
		"\2\2\u0141\25\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0145\7~\2\2\u0144\u0143"+
		"\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0149\7\33\2\2"+
		"\u0147\u0148\7V\2\2\u0148\u014a\7W\2\2\u0149\u0147\3\2\2\2\u0149\u014a"+
		"\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\5\30\r\2\u014c\27\3\2\2\2\u014d"+
		"\u0154\5\n\6\2\u014e\u014f\7T\2\2\u014f\u0150\5~@\2\u0150\u0151\7U\2\2"+
		"\u0151\u0152\5\30\r\2\u0152\u0154\3\2\2\2\u0153\u014d\3\2\2\2\u0153\u014e"+
		"\3\2\2\2\u0154\31\3\2\2\2\u0155\u015a\5\30\r\2\u0156\u0157\t\5\2\2\u0157"+
		"\u0159\5\30\r\2\u0158\u0156\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3"+
		"\2\2\2\u015a\u015b\3\2\2\2\u015b\33\3\2\2\2\u015c\u015a\3\2\2\2\u015d"+
		"\u0162\5\32\16\2\u015e\u015f\t\6\2\2\u015f\u0161\5\32\16\2\u0160\u015e"+
		"\3\2\2\2\u0161\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163"+
		"\35\3\2\2\2\u0164\u0162\3\2\2\2\u0165\u016a\5\34\17\2\u0166\u0167\t\7"+
		"\2\2\u0167\u0169\5\34\17\2\u0168\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\37\3\2\2\2\u016c\u016a\3\2\2"+
		"\2\u016d\u0173\5\36\20\2\u016e\u016f\5\"\22\2\u016f\u0170\5\36\20\2\u0170"+
		"\u0172\3\2\2\2\u0171\u016e\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2"+
		"\2\2\u0173\u0174\3\2\2\2\u0174!\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177"+
		"\7f\2\2\u0177\u017b\7f\2\2\u0178\u0179\7e\2\2\u0179\u017b\7e\2\2\u017a"+
		"\u0176\3\2\2\2\u017a\u0178\3\2\2\2\u017b#\3\2\2\2\u017c\u0181\5 \21\2"+
		"\u017d\u017e\t\b\2\2\u017e\u0180\5 \21\2\u017f\u017d\3\2\2\2\u0180\u0183"+
		"\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182%\3\2\2\2\u0183"+
		"\u0181\3\2\2\2\u0184\u0189\5$\23\2\u0185\u0186\t\t\2\2\u0186\u0188\5$"+
		"\23\2\u0187\u0185\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189"+
		"\u018a\3\2\2\2\u018a\'\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u0191\5&\24\2"+
		"\u018d\u018e\7`\2\2\u018e\u0190\5&\24\2\u018f\u018d\3\2\2\2\u0190\u0193"+
		"\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192)\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0194\u0199\5(\25\2\u0195\u0196\7_\2\2\u0196\u0198\5(\25"+
		"\2\u0197\u0195\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a"+
		"\3\2\2\2\u019a+\3\2\2\2\u019b\u0199\3\2\2\2\u019c\u01a1\5*\26\2\u019d"+
		"\u019e\7a\2\2\u019e\u01a0\5*\26\2\u019f\u019d\3\2\2\2\u01a0\u01a3\3\2"+
		"\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2-\3\2\2\2\u01a3\u01a1"+
		"\3\2\2\2\u01a4\u01a9\5,\27\2\u01a5\u01a6\7u\2\2\u01a6\u01a8\5,\27\2\u01a7"+
		"\u01a5\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2"+
		"\2\2\u01aa/\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ac\u01b1\5.\30\2\u01ad\u01ae"+
		"\7v\2\2\u01ae\u01b0\5.\30\2\u01af\u01ad\3\2\2\2\u01b0\u01b3\3\2\2\2\u01b1"+
		"\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\61\3\2\2\2\u01b3\u01b1\3\2\2"+
		"\2\u01b4\u01ba\5\60\31\2\u01b5\u01b6\7|\2\2\u01b6\u01b7\5\66\34\2\u01b7"+
		"\u01b8\7}\2\2\u01b8\u01b9\5\62\32\2\u01b9\u01bb\3\2\2\2\u01ba\u01b5\3"+
		"\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\63\3\2\2\2\u01bc\u01bd\t\n\2\2\u01bd"+
		"\65\3\2\2\2\u01be\u01c3\5\62\32\2\u01bf\u01c0\7y\2\2\u01c0\u01c2\5\62"+
		"\32\2\u01c1\u01bf\3\2\2\2\u01c2\u01c5\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3"+
		"\u01c4\3\2\2\2\u01c4\67\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c6\u01c7\5\62\32"+
		"\2\u01c79\3\2\2\2\u01c8\u01d2\5<\37\2\u01c9\u01d2\5R*\2\u01ca\u01d0\5"+
		"> \2\u01cb\u01d0\5@!\2\u01cc\u01d0\5D#\2\u01cd\u01d0\5H%\2\u01ce\u01d0"+
		"\5L\'\2\u01cf\u01ca\3\2\2\2\u01cf\u01cb\3\2\2\2\u01cf\u01cc\3\2\2\2\u01cf"+
		"\u01cd\3\2\2\2\u01cf\u01ce\3\2\2\2\u01d0\u01d2\3\2\2\2\u01d1\u01c8\3\2"+
		"\2\2\u01d1\u01c9\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d2;\3\2\2\2\u01d3\u01d8"+
		"\7\u0083\2\2\u01d4\u01d5\7\21\2\2\u01d5\u01d8\58\35\2\u01d6\u01d8\7\32"+
		"\2\2\u01d7\u01d3\3\2\2\2\u01d7\u01d4\3\2\2\2\u01d7\u01d6\3\2\2\2\u01d8"+
		"\u01d9\3\2\2\2\u01d9\u01da\7}\2\2\u01da\u01db\5:\36\2\u01db=\3\2\2\2\u01dc"+
		"\u01de\5\66\34\2\u01dd\u01dc\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01df\3"+
		"\2\2\2\u01df\u01e0\7\177\2\2\u01e0?\3\2\2\2\u01e1\u01e3\7X\2\2\u01e2\u01e4"+
		"\5B\"\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5"+
		"\u01e6\7Y\2\2\u01e6A\3\2\2\2\u01e7\u01e9\5:\36\2\u01e8\u01e7\3\2\2\2\u01e9"+
		"\u01ea\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01ebC\3\2\2\2"+
		"\u01ec\u01ed\7*\2\2\u01ed\u01ee\7T\2\2\u01ee\u01ef\5F$\2\u01ef\u01f0\7"+
		"U\2\2\u01f0\u01f3\5:\36\2\u01f1\u01f2\7\37\2\2\u01f2\u01f4\5:\36\2\u01f3"+
		"\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01fc\3\2\2\2\u01f5\u01f6\7B"+
		"\2\2\u01f6\u01f7\7T\2\2\u01f7\u01f8\5F$\2\u01f8\u01f9\7U\2\2\u01f9\u01fa"+
		"\5:\36\2\u01fa\u01fc\3\2\2\2\u01fb\u01ec\3\2\2\2\u01fb\u01f5\3\2\2\2\u01fc"+
		"E\3\2\2\2\u01fd\u0206\5\66\34\2\u01fe\u01ff\5V,\2\u01ff\u0203\5l\67\2"+
		"\u0200\u0201\7d\2\2\u0201\u0204\5\u008eH\2\u0202\u0204\5\u0092J\2\u0203"+
		"\u0200\3\2\2\2\u0203\u0202\3\2\2\2\u0204\u0206\3\2\2\2\u0205\u01fd\3\2"+
		"\2\2\u0205\u01fe\3\2\2\2\u0206G\3\2\2\2\u0207\u0208\7S\2\2\u0208\u0209"+
		"\7T\2\2\u0209\u020a\5F$\2\u020a\u020b\7U\2\2\u020b\u020c\5:\36\2\u020c"+
		"\u0223\3\2\2\2\u020d\u020e\7\34\2\2\u020e\u020f\5:\36\2\u020f\u0210\7"+
		"S\2\2\u0210\u0211\7T\2\2\u0211\u0212\5\66\34\2\u0212\u0213\7U\2\2\u0213"+
		"\u0214\7\177\2\2\u0214\u0223\3\2\2\2\u0215\u0216\7\'\2\2\u0216\u0217\7"+
		"T\2\2\u0217\u0219\5J&\2\u0218\u021a\5F$\2\u0219\u0218\3\2\2\2\u0219\u021a"+
		"\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021d\7\177\2\2\u021c\u021e\5\66\34"+
		"\2\u021d\u021c\3\2\2\2\u021d\u021e\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0220"+
		"\7U\2\2\u0220\u0221\5:\36\2\u0221\u0223\3\2\2\2\u0222\u0207\3\2\2\2\u0222"+
		"\u020d\3\2\2\2\u0222\u0215\3\2\2\2\u0223I\3\2\2\2\u0224\u0227\5> \2\u0225"+
		"\u0227\5R*\2\u0226\u0224\3\2\2\2\u0226\u0225\3\2\2\2\u0227K\3\2\2\2\u0228"+
		"\u0230\7\20\2\2\u0229\u0230\7\30\2\2\u022a\u022d\7:\2\2\u022b\u022e\5"+
		"\66\34\2\u022c\u022e\5\u0092J\2\u022d\u022b\3\2\2\2\u022d\u022c\3\2\2"+
		"\2\u022d\u022e\3\2\2\2\u022e\u0230\3\2\2\2\u022f\u0228\3\2\2\2\u022f\u0229"+
		"\3\2\2\2\u022f\u022a\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0232\7\177\2\2"+
		"\u0232M\3\2\2\2\u0233\u0235\5P)\2\u0234\u0233\3\2\2\2\u0235\u0236\3\2"+
		"\2\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237O\3\2\2\2\u0238\u023e"+
		"\5R*\2\u0239\u023e\5\u0086D\2\u023a\u023e\5\u00b4[\2\u023b\u023e\5\u00c6"+
		"d\2\u023c\u023e\7\177\2\2\u023d\u0238\3\2\2\2\u023d\u0239\3\2\2\2\u023d"+
		"\u023a\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023c\3\2\2\2\u023eQ\3\2\2\2"+
		"\u023f\u0241\5V,\2\u0240\u023f\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u0243"+
		"\3\2\2\2\u0242\u0244\5h\65\2\u0243\u0242\3\2\2\2\u0243\u0244\3\2\2\2\u0244"+
		"\u0245\3\2\2\2\u0245\u0246\7\177\2\2\u0246S\3\2\2\2\u0247\u024a\5Z.\2"+
		"\u0248\u024a\7I\2\2\u0249\u0247\3\2\2\2\u0249\u0248\3\2\2\2\u024aU\3\2"+
		"\2\2\u024b\u024d\5T+\2\u024c\u024b\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u024f"+
		"\3\2\2\2\u024e\u024c\3\2\2\2\u024fW\3\2\2\2\u0250\u0251\7\u0083\2\2\u0251"+
		"Y\3\2\2\2\u0252\u0255\5\\/\2\u0253\u0255\5\u0096L\2\u0254\u0252\3\2\2"+
		"\2\u0254\u0253\3\2\2\2\u0255[\3\2\2\2\u0256\u0259\5d\63\2\u0257\u0259"+
		"\5x=\2\u0258\u0256\3\2\2\2\u0258\u0257\3\2\2\2\u0259]\3\2\2\2\u025a\u025c"+
		"\5Z.\2\u025b\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025b\3\2\2\2\u025d"+
		"\u025e\3\2\2\2\u025e_\3\2\2\2\u025f\u0260\t\13\2\2\u0260a\3\2\2\2\u0261"+
		"\u0262\t\f\2\2\u0262c\3\2\2\2\u0263\u0284\5f\64\2\u0264\u0284\5b\62\2"+
		"\u0265\u0267\5b\62\2\u0266\u0265\3\2\2\2\u0266\u0267\3\2\2\2\u0267\u0269"+
		"\3\2\2\2\u0268\u026a\5`\61\2\u0269\u0268\3\2\2\2\u026a\u026b\3\2\2\2\u026b"+
		"\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u0284\3\2\2\2\u026d\u026f\5b"+
		"\62\2\u026e\u026d\3\2\2\2\u026e\u026f\3\2\2\2\u026f\u0270\3\2\2\2\u0270"+
		"\u0284\7\23\2\2\u0271\u0284\7\17\2\2\u0272\u0274\5b\62\2\u0273\u0272\3"+
		"\2\2\2\u0273\u0274\3\2\2\2\u0274\u0278\3\2\2\2\u0275\u0277\5`\61\2\u0276"+
		"\u0275\3\2\2\2\u0277\u027a\3\2\2\2\u0278\u0276\3\2\2\2\u0278\u0279\3\2"+
		"\2\2\u0279\u027b\3\2\2\2\u027a\u0278\3\2\2\2\u027b\u0284\7,\2\2\u027c"+
		"\u0284\7&\2\2\u027d\u027f\5`\61\2\u027e\u027d\3\2\2\2\u027e\u027f\3\2"+
		"\2\2\u027f\u0280\3\2\2\2\u0280\u0284\7\35\2\2\u0281\u0284\7P\2\2\u0282"+
		"\u0284\7\16\2\2\u0283\u0263\3\2\2\2\u0283\u0264\3\2\2\2\u0283\u0266\3"+
		"\2\2\2\u0283\u026e\3\2\2\2\u0283\u0271\3\2\2\2\u0283\u0273\3\2\2\2\u0283"+
		"\u027c\3\2\2\2\u0283\u027e\3\2\2\2\u0283\u0281\3\2\2\2\u0283\u0282\3\2"+
		"\2\2\u0284e\3\2\2\2\u0285\u0289\5\u0094K\2\u0286\u0289\5X-\2\u0287\u0289"+
		"\5\u00bc_\2\u0288\u0285\3\2\2\2\u0288\u0286\3\2\2\2\u0288\u0287\3\2\2"+
		"\2\u0289g\3\2\2\2\u028a\u028f\5j\66\2\u028b\u028c\7y\2\2\u028c\u028e\5"+
		"j\66\2\u028d\u028b\3\2\2\2\u028e\u0291\3\2\2\2\u028f\u028d\3\2\2\2\u028f"+
		"\u0290\3\2\2\2\u0290i\3\2\2\2\u0291\u028f\3\2\2\2\u0292\u0294\5l\67\2"+
		"\u0293\u0295\5\u008aF\2\u0294\u0293\3\2\2\2\u0294\u0295\3\2\2\2\u0295"+
		"k\3\2\2\2\u0296\u0297\5n8\2\u0297m\3\2\2\2\u0298\u029a\5t;\2\u0299\u029b"+
		"\7\25\2\2\u029a\u0299\3\2\2\2\u029a\u029b\3\2\2\2\u029b\u029d\3\2\2\2"+
		"\u029c\u0298\3\2\2\2\u029d\u02a0\3\2\2\2\u029e\u029c\3\2\2\2\u029e\u029f"+
		"\3\2\2\2\u029f\u02a1\3\2\2\2\u02a0\u029e\3\2\2\2\u02a1\u02a2\5p9\2\u02a2"+
		"o\3\2\2\2\u02a3\u02a4\b9\1\2\u02a4\u02aa\5|?\2\u02a5\u02a6\7T\2\2\u02a6"+
		"\u02a7\5n8\2\u02a7\u02a8\7U\2\2\u02a8\u02aa\3\2\2\2\u02a9\u02a3\3\2\2"+
		"\2\u02a9\u02a5\3\2\2\2\u02aa\u02b6\3\2\2\2\u02ab\u02b2\f\4\2\2\u02ac\u02b3"+
		"\5r:\2\u02ad\u02af\7V\2\2\u02ae\u02b0\58\35\2\u02af\u02ae\3\2\2\2\u02af"+
		"\u02b0\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b3\7W\2\2\u02b2\u02ac\3\2"+
		"\2\2\u02b2\u02ad\3\2\2\2\u02b3\u02b5\3\2\2\2\u02b4\u02ab\3\2\2\2\u02b5"+
		"\u02b8\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7q\3\2\2\2"+
		"\u02b8\u02b6\3\2\2\2\u02b9\u02bb\7T\2\2\u02ba\u02bc\5\u0080A\2\u02bb\u02ba"+
		"\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02bf\7U\2\2\u02be"+
		"\u02c0\5v<\2\u02bf\u02be\3\2\2\2\u02bf\u02c0\3\2\2\2\u02c0\u02c2\3\2\2"+
		"\2\u02c1\u02c3\5z>\2\u02c2\u02c1\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3s\3"+
		"\2\2\2\u02c4\u02ca\t\r\2\2\u02c5\u02c7\7\\\2\2\u02c6\u02c8\5v<\2\u02c7"+
		"\u02c6\3\2\2\2\u02c7\u02c8\3\2\2\2\u02c8\u02ca\3\2\2\2\u02c9\u02c4\3\2"+
		"\2\2\u02c9\u02c5\3\2\2\2\u02cau\3\2\2\2\u02cb\u02cd\5x=\2\u02cc\u02cb"+
		"\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02cc\3\2\2\2\u02ce\u02cf\3\2\2\2\u02cf"+
		"w\3\2\2\2\u02d0\u02d1\t\16\2\2\u02d1y\3\2\2\2\u02d2\u02d3\t\r\2\2\u02d3"+
		"{\3\2\2\2\u02d4\u02d6\7\u0082\2\2\u02d5\u02d4\3\2\2\2\u02d5\u02d6\3\2"+
		"\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02d8\5\6\4\2\u02d8}\3\2\2\2\u02d9\u02da"+
		"\5^\60\2\u02da\177\3\2\2\2\u02db\u02e0\5\u0082B\2\u02dc\u02de\7y\2\2\u02dd"+
		"\u02dc\3\2\2\2\u02dd\u02de\3\2\2\2\u02de\u02df\3\2\2\2\u02df\u02e1\7\u0082"+
		"\2\2\u02e0\u02dd\3\2\2\2\u02e0\u02e1\3\2\2\2\u02e1\u0081\3\2\2\2\u02e2"+
		"\u02e7\5\u0084C\2\u02e3\u02e4\7y\2\2\u02e4\u02e6\5\u0084C\2\u02e5\u02e3"+
		"\3\2\2\2\u02e6\u02e9\3\2\2\2\u02e7\u02e5\3\2\2\2\u02e7\u02e8\3\2\2\2\u02e8"+
		"\u0083\3\2\2\2\u02e9\u02e7\3\2\2\2\u02ea\u02eb\5V,\2\u02eb\u02ee\5l\67"+
		"\2\u02ec\u02ed\7d\2\2\u02ed\u02ef\5\u008eH\2\u02ee\u02ec\3\2\2\2\u02ee"+
		"\u02ef\3\2\2\2\u02ef\u0085\3\2\2\2\u02f0\u02f2\5V,\2\u02f1\u02f0\3\2\2"+
		"\2\u02f1\u02f2\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3\u02f4\5l\67\2\u02f4\u02f5"+
		"\5\u0088E\2\u02f5\u0087\3\2\2\2\u02f6\u02fb\5@!\2\u02f7\u02f8\7d\2\2\u02f8"+
		"\u02f9\t\17\2\2\u02f9\u02fb\7\177\2\2\u02fa\u02f6\3\2\2\2\u02fa\u02f7"+
		"\3\2\2\2\u02fb\u0089\3\2\2\2\u02fc\u0302\5\u008cG\2\u02fd\u02fe\7T\2\2"+
		"\u02fe\u02ff\5\u0090I\2\u02ff\u0300\7U\2\2\u0300\u0302\3\2\2\2\u0301\u02fc"+
		"\3\2\2\2\u0301\u02fd\3\2\2\2\u0302\u008b\3\2\2\2\u0303\u0304\7d\2\2\u0304"+
		"\u0307\5\u008eH\2\u0305\u0307\5\u0092J\2\u0306\u0303\3\2\2\2\u0306\u0305"+
		"\3\2\2\2\u0307\u008d\3\2\2\2\u0308\u030b\5\62\32\2\u0309\u030b\5\u0092"+
		"J\2\u030a\u0308\3\2\2\2\u030a\u0309\3\2\2\2\u030b\u008f\3\2\2\2\u030c"+
		"\u030e\5\u008eH\2\u030d\u030f\7\u0082\2\2\u030e\u030d\3\2\2\2\u030e\u030f"+
		"\3\2\2\2\u030f\u0317\3\2\2\2\u0310\u0311\7y\2\2\u0311\u0313\5\u008eH\2"+
		"\u0312\u0314\7\u0082\2\2\u0313\u0312\3\2\2\2\u0313\u0314\3\2\2\2\u0314"+
		"\u0316\3\2\2\2\u0315\u0310\3\2\2\2\u0316\u0319\3\2\2\2\u0317\u0315\3\2"+
		"\2\2\u0317\u0318\3\2\2\2\u0318\u0091\3\2\2\2\u0319\u0317\3\2\2\2\u031a"+
		"\u031f\7X\2\2\u031b\u031d\5\u0090I\2\u031c\u031e\7y\2\2\u031d\u031c\3"+
		"\2\2\2\u031d\u031e\3\2\2\2\u031e\u0320\3\2\2\2\u031f\u031b\3\2\2\2\u031f"+
		"\u0320\3\2\2\2\u0320\u0321\3\2\2\2\u0321\u0322\7Y\2\2\u0322\u0093\3\2"+
		"\2\2\u0323\u0327\7\u0083\2\2\u0324\u0327\5\u00bc_\2\u0325\u0327\5\u00ce"+
		"h\2\u0326\u0323\3\2\2\2\u0326\u0324\3\2\2\2\u0326\u0325\3\2\2\2\u0327"+
		"\u0095\3\2\2\2\u0328\u0329\5\u0098M\2\u0329\u032b\7X\2\2\u032a\u032c\5"+
		"\u009eP\2\u032b\u032a\3\2\2\2\u032b\u032c\3\2\2\2\u032c\u032d\3\2\2\2"+
		"\u032d\u032e\7Y\2\2\u032e\u0097\3\2\2\2\u032f\u0331\5\u009cO\2\u0330\u0332"+
		"\5\u009aN\2\u0331\u0330\3\2\2\2\u0331\u0332\3\2\2\2\u0332\u0099\3\2\2"+
		"\2\u0333\u0334\5\u0094K\2\u0334\u009b\3\2\2\2\u0335\u0336\t\20\2\2\u0336"+
		"\u009d\3\2\2\2\u0337\u033c\5\u00a0Q\2\u0338\u0339\5\u00a8U\2\u0339\u033a"+
		"\7}\2\2\u033a\u033c\3\2\2\2\u033b\u0337\3\2\2\2\u033b\u0338\3\2\2\2\u033c"+
		"\u033d\3\2\2\2\u033d\u033b\3\2\2\2\u033d\u033e\3\2\2\2\u033e\u009f\3\2"+
		"\2\2\u033f\u0341\5V,\2\u0340\u033f\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0343"+
		"\3\2\2\2\u0342\u0344\5\u00a2R\2\u0343\u0342\3\2\2\2\u0343\u0344\3\2\2"+
		"\2\u0344\u0345\3\2\2\2\u0345\u034a\7\177\2\2\u0346\u034a\5\u0086D\2\u0347"+
		"\u034a\5\u00b4[\2\u0348\u034a\7\177\2\2\u0349\u0340\3\2\2\2\u0349\u0346"+
		"\3\2\2\2\u0349\u0347\3\2\2\2\u0349\u0348\3\2\2\2\u034a\u00a1\3\2\2\2\u034b"+
		"\u0350\5\u00a4S\2\u034c\u034d\7y\2\2\u034d\u034f\5\u00a4S\2\u034e\u034c"+
		"\3\2\2\2\u034f\u0352\3\2\2\2\u0350\u034e\3\2\2\2\u0350\u0351\3\2\2\2\u0351"+
		"\u00a3\3\2\2\2\u0352\u0350\3\2\2\2\u0353\u0355\5l\67\2\u0354\u0356\5\u008c"+
		"G\2\u0355\u0354\3\2\2\2\u0355\u0356\3\2\2\2\u0356\u035d\3\2\2\2\u0357"+
		"\u0359\7\u0083\2\2\u0358\u0357\3\2\2\2\u0358\u0359\3\2\2\2\u0359\u035a"+
		"\3\2\2\2\u035a\u035b\7}\2\2\u035b\u035d\58\35\2\u035c\u0353\3\2\2\2\u035c"+
		"\u0358\3\2\2\2\u035d\u00a5\3\2\2\2\u035e\u0360\5\u0094K\2\u035f\u0361"+
		"\7\u0082\2\2\u0360\u035f\3\2\2\2\u0360\u0361\3\2\2\2\u0361\u0369\3\2\2"+
		"\2\u0362\u0363\7y\2\2\u0363\u0365\5\u0094K\2\u0364\u0366\7\u0082\2\2\u0365"+
		"\u0364\3\2\2\2\u0365\u0366\3\2\2\2\u0366\u0368\3\2\2\2\u0367\u0362\3\2"+
		"\2\2\u0368\u036b\3\2\2\2\u0369\u0367\3\2\2\2\u0369\u036a\3\2\2\2\u036a"+
		"\u00a7\3\2\2\2\u036b\u0369\3\2\2\2\u036c\u036d\t\21\2\2\u036d\u00a9\3"+
		"\2\2\2\u036e\u0370\5\u00acW\2\u036f\u0371\7\u0082\2\2\u0370\u036f\3\2"+
		"\2\2\u0370\u0371\3\2\2\2\u0371\u0379\3\2\2\2\u0372\u0373\7y\2\2\u0373"+
		"\u0375\5\u00acW\2\u0374\u0376\7\u0082\2\2\u0375\u0374\3\2\2\2\u0375\u0376"+
		"\3\2\2\2\u0376\u0378\3\2\2\2\u0377\u0372\3\2\2\2\u0378\u037b\3\2\2\2\u0379"+
		"\u0377\3\2\2\2\u0379\u037a\3\2\2\2\u037a\u00ab\3\2\2\2\u037b\u0379\3\2"+
		"\2\2\u037c\u0383\5\u00aeX\2\u037d\u037f\7T\2\2\u037e\u0380\5\u0090I\2"+
		"\u037f\u037e\3\2\2\2\u037f\u0380\3\2\2\2\u0380\u0381\3\2\2\2\u0381\u0384"+
		"\7U\2\2\u0382\u0384\5\u0092J\2\u0383\u037d\3\2\2\2\u0383\u0382\3\2\2\2"+
		"\u0384\u00ad\3\2\2\2\u0385\u0388\5\u0094K\2\u0386\u0388\7\u0083\2\2\u0387"+
		"\u0385\3\2\2\2\u0387\u0386\3\2\2\2\u0388\u00af\3\2\2\2\u0389\u038a\7\63"+
		"\2\2\u038a\u038b\5\u00c8e\2\u038b\u00b1\3\2\2\2\u038c\u038d\7\63\2\2\u038d"+
		"\u038e\7\6\2\2\u038e\u038f\7\u0083\2\2\u038f\u00b3\3\2\2\2\u0390\u0391"+
		"\7C\2\2\u0391\u0392\7e\2\2\u0392\u0393\5\u00b6\\\2\u0393\u0394\7f\2\2"+
		"\u0394\u0395\5P)\2\u0395\u00b5\3\2\2\2\u0396\u039b\5\u00b8]\2\u0397\u0398"+
		"\7y\2\2\u0398\u039a\5\u00b8]\2\u0399\u0397\3\2\2\2\u039a\u039d\3\2\2\2"+
		"\u039b\u0399\3\2\2\2\u039b\u039c\3\2\2\2\u039c\u00b7\3\2\2\2\u039d\u039b"+
		"\3\2\2\2\u039e\u03a1\5\u00ba^\2\u039f\u03a1\5\u0084C\2\u03a0\u039e\3\2"+
		"\2\2\u03a0\u039f\3\2\2\2\u03a1\u00b9\3\2\2\2\u03a2\u03a3\7C\2\2\u03a3"+
		"\u03a4\7e\2\2\u03a4\u03a5\5\u00b6\\\2\u03a5\u03a6\7f\2\2\u03a6\u03a8\3"+
		"\2\2\2\u03a7\u03a2\3\2\2\2\u03a7\u03a8\3\2\2\2\u03a8\u03a9\3\2\2\2\u03a9"+
		"\u03ac\7\24\2\2\u03aa\u03ac\7K\2\2\u03ab\u03a7\3\2\2\2\u03ab\u03aa\3\2"+
		"\2\2\u03ac\u03b8\3\2\2\2\u03ad\u03af\7\u0082\2\2\u03ae\u03ad\3\2\2\2\u03ae"+
		"\u03af\3\2\2\2\u03af\u03b1\3\2\2\2\u03b0\u03b2\7\u0083\2\2\u03b1\u03b0"+
		"\3\2\2\2\u03b1\u03b2\3\2\2\2\u03b2\u03b9\3\2\2\2\u03b3\u03b5\7\u0083\2"+
		"\2\u03b4\u03b3\3\2\2\2\u03b4\u03b5\3\2\2\2\u03b5\u03b6\3\2\2\2\u03b6\u03b7"+
		"\7d\2\2\u03b7\u03b9\5~@\2\u03b8\u03ae\3\2\2\2\u03b8\u03b4\3\2\2\2\u03b9"+
		"\u00bb\3\2\2\2\u03ba\u03bb\5\u00c0a\2\u03bb\u03bd\7e\2\2\u03bc\u03be\5"+
		"\u00c2b\2\u03bd\u03bc\3\2\2\2\u03bd\u03be\3\2\2\2\u03be\u03bf\3\2\2\2"+
		"\u03bf\u03c0\7f\2\2\u03c0\u00bd\3\2\2\2\u03c1\u03cd\5\u00bc_\2\u03c2\u03c5"+
		"\5\u00b0Y\2\u03c3\u03c5\5\u00b2Z\2\u03c4\u03c2\3\2\2\2\u03c4\u03c3\3\2"+
		"\2\2\u03c5\u03c6\3\2\2\2\u03c6\u03c8\7e\2\2\u03c7\u03c9\5\u00c2b\2\u03c8"+
		"\u03c7\3\2\2\2\u03c8\u03c9\3\2\2\2\u03c9\u03ca\3\2\2\2\u03ca\u03cb\7f"+
		"\2\2\u03cb\u03cd\3\2\2\2\u03cc\u03c1\3\2\2\2\u03cc\u03c4\3\2\2\2\u03cd"+
		"\u00bf\3\2\2\2\u03ce\u03cf\7\u0083\2\2\u03cf\u00c1\3\2\2\2\u03d0\u03d2"+
		"\5\u00c4c\2\u03d1\u03d3\7\u0082\2\2\u03d2\u03d1\3\2\2\2\u03d2\u03d3\3"+
		"\2\2\2\u03d3\u03db\3\2\2\2\u03d4\u03d5\7y\2\2\u03d5\u03d7\5\u00c4c\2\u03d6"+
		"\u03d8\7\u0082\2\2\u03d7\u03d6\3\2\2\2\u03d7\u03d8\3\2\2\2\u03d8\u03da"+
		"\3\2\2\2\u03d9\u03d4\3\2\2\2\u03da\u03dd\3\2\2\2\u03db\u03d9\3\2\2\2\u03db"+
		"\u03dc\3\2\2\2\u03dc\u00c3\3\2\2\2\u03dd\u03db\3\2\2\2\u03de\u03e2\5~"+
		"@\2\u03df\u03e2\58\35\2\u03e0\u03e2\5\6\4\2\u03e1\u03de\3\2\2\2\u03e1"+
		"\u03df\3\2\2\2\u03e1\u03e0\3\2\2\2\u03e2\u00c5\3\2\2\2\u03e3\u03e4\7C"+
		"\2\2\u03e4\u03e5\5P)\2\u03e5\u00c7\3\2\2\2\u03e6\u03e9\7\60\2\2\u03e7"+
		"\u03e8\7V\2\2\u03e8\u03ea\7W\2\2\u03e9\u03e7\3\2\2\2\u03e9\u03ea\3\2\2"+
		"\2\u03ea\u041a\3\2\2\2\u03eb\u03ee\7\33\2\2\u03ec\u03ed\7V\2\2\u03ed\u03ef"+
		"\7W\2\2\u03ee\u03ec\3\2\2\2\u03ee\u03ef\3\2\2\2\u03ef\u041a\3\2\2\2\u03f0"+
		"\u041a\7Z\2\2\u03f1\u041a\7[\2\2\u03f2\u041a\7\\\2\2\u03f3\u041a\7]\2"+
		"\2\u03f4\u041a\7^\2\2\u03f5\u041a\7_\2\2\u03f6\u041a\7`\2\2\u03f7\u041a"+
		"\7a\2\2\u03f8\u041a\7b\2\2\u03f9\u041a\7c\2\2\u03fa\u041a\7d\2\2\u03fb"+
		"\u041a\7f\2\2\u03fc\u041a\7e\2\2\u03fd\u041a\7t\2\2\u03fe\u041a\7g\2\2"+
		"\u03ff\u041a\7h\2\2\u0400\u041a\7i\2\2\u0401\u041a\7k\2\2\u0402\u041a"+
		"\7l\2\2\u0403\u041a\7m\2\2\u0404\u041a\7n\2\2\u0405\u0406\7e\2\2\u0406"+
		"\u041a\7e\2\2\u0407\u0408\7f\2\2\u0408\u041a\7f\2\2\u0409\u041a\7p\2\2"+
		"\u040a\u041a\7o\2\2\u040b\u041a\7q\2\2\u040c\u041a\7r\2\2\u040d\u041a"+
		"\7s\2\2\u040e\u041a\7u\2\2\u040f\u041a\7v\2\2\u0410\u041a\7w\2\2\u0411"+
		"\u041a\7x\2\2\u0412\u041a\7y\2\2\u0413\u041a\7z\2\2\u0414\u041a\7{\2\2"+
		"\u0415\u0416\7T\2\2\u0416\u041a\7U\2\2\u0417\u0418\7V\2\2\u0418\u041a"+
		"\7W\2\2\u0419\u03e6\3\2\2\2\u0419\u03eb\3\2\2\2\u0419\u03f0\3\2\2\2\u0419"+
		"\u03f1\3\2\2\2\u0419\u03f2\3\2\2\2\u0419\u03f3\3\2\2\2\u0419\u03f4\3\2"+
		"\2\2\u0419\u03f5\3\2\2\2\u0419\u03f6\3\2\2\2\u0419\u03f7\3\2\2\2\u0419"+
		"\u03f8\3\2\2\2\u0419\u03f9\3\2\2\2\u0419\u03fa\3\2\2\2\u0419\u03fb\3\2"+
		"\2\2\u0419\u03fc\3\2\2\2\u0419\u03fd\3\2\2\2\u0419\u03fe\3\2\2\2\u0419"+
		"\u03ff\3\2\2\2\u0419\u0400\3\2\2\2\u0419\u0401\3\2\2\2\u0419\u0402\3\2"+
		"\2\2\u0419\u0403\3\2\2\2\u0419\u0404\3\2\2\2\u0419\u0405\3\2\2\2\u0419"+
		"\u0407\3\2\2\2\u0419\u0409\3\2\2\2\u0419\u040a\3\2\2\2\u0419\u040b\3\2"+
		"\2\2\u0419\u040c\3\2\2\2\u0419\u040d\3\2\2\2\u0419\u040e\3\2\2\2\u0419"+
		"\u040f\3\2\2\2\u0419\u0410\3\2\2\2\u0419\u0411\3\2\2\2\u0419\u0412\3\2"+
		"\2\2\u0419\u0413\3\2\2\2\u0419\u0414\3\2\2\2\u0419\u0415\3\2\2\2\u0419"+
		"\u0417\3\2\2\2\u041a\u00c9\3\2\2\2\u041b\u041c\t\22\2\2\u041c\u00cb\3"+
		"\2\2\2\u041d\u041e\t\23\2\2\u041e\u00cd\3\2\2\2\u041f\u0420\t\24\2\2\u0420"+
		"\u00cf\3\2\2\2\u008d\u00d1\u00d8\u00df\u00e6\u00ed\u00f1\u00f3\u00f9\u0100"+
		"\u0106\u010b\u010d\u0115\u011a\u011f\u0127\u012b\u012f\u0132\u0140\u0144"+
		"\u0149\u0153\u015a\u0162\u016a\u0173\u017a\u0181\u0189\u0191\u0199\u01a1"+
		"\u01a9\u01b1\u01ba\u01c3\u01cf\u01d1\u01d7\u01dd\u01e3\u01ea\u01f3\u01fb"+
		"\u0203\u0205\u0219\u021d\u0222\u0226\u022d\u022f\u0236\u023d\u0240\u0243"+
		"\u0249\u024e\u0254\u0258\u025d\u0266\u026b\u026e\u0273\u0278\u027e\u0283"+
		"\u0288\u028f\u0294\u029a\u029e\u02a9\u02af\u02b2\u02b6\u02bb\u02bf\u02c2"+
		"\u02c7\u02c9\u02ce\u02d5\u02dd\u02e0\u02e7\u02ee\u02f1\u02fa\u0301\u0306"+
		"\u030a\u030e\u0313\u0317\u031d\u031f\u0326\u032b\u0331\u033b\u033d\u0340"+
		"\u0343\u0349\u0350\u0355\u0358\u035c\u0360\u0365\u0369\u0370\u0375\u0379"+
		"\u037f\u0383\u0387\u039b\u03a0\u03a7\u03ab\u03ae\u03b1\u03b4\u03b8\u03bd"+
		"\u03c4\u03c8\u03cc\u03d2\u03d7\u03db\u03e1\u03e9\u03ee\u0419";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}