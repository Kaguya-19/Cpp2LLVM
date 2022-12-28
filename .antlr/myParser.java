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
		Pop=141, Top=142, Cin=143, Cout=144, String=145, Length=146, Vector=147, 
		Find=148, Push_back=149, Strlen=150, Printf=151, Gets=152;
	public static final int
		RULE_translationUnit = 0, RULE_functionDefine = 1, RULE_params = 2, RULE_param = 3, 
		RULE_funcBody = 4, RULE_body = 5, RULE_blocks = 6, RULE_initialBlock = 7, 
		RULE_arrayInitBlock = 8, RULE_assignBlock = 9, RULE_ifBlocks = 10, RULE_ifBlock = 11, 
		RULE_elifBlock = 12, RULE_elseBlock = 13, RULE_condition = 14, RULE_forBlock = 15, 
		RULE_for1Block = 16, RULE_for3Block = 17, RULE_returnBlock = 18, RULE_expr = 19, 
		RULE_typeDef = 20, RULE_arrayRef = 21, RULE_arrayItem = 22, RULE_func = 23, 
		RULE_strlenRef = 24, RULE_printfRef = 25, RULE_getsRef = 26, RULE_funcRef = 27, 
		RULE_literal = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"translationUnit", "functionDefine", "params", "param", "funcBody", "body", 
			"blocks", "initialBlock", "arrayInitBlock", "assignBlock", "ifBlocks", 
			"ifBlock", "elifBlock", "elseBlock", "condition", "forBlock", "for1Block", 
			"for3Block", "returnBlock", "expr", "typeDef", "arrayRef", "arrayItem", 
			"func", "strlenRef", "printfRef", "getsRef", "funcRef", "literal"
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
			"'top'", "'cin'", "'cout'", "'string'", "'length'", "'vector'", "'find'", 
			"'push_back'", "'strlen'", "'printf'", "'gets'"
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
			"String", "Length", "Vector", "Find", "Push_back", "Strlen", "Printf", 
			"Gets"
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
		public List<InitialBlockContext> initialBlock() {
			return getRuleContexts(InitialBlockContext.class);
		}
		public InitialBlockContext initialBlock(int i) {
			return getRuleContext(InitialBlockContext.class,i);
		}
		public List<ArrayInitBlockContext> arrayInitBlock() {
			return getRuleContexts(ArrayInitBlockContext.class);
		}
		public ArrayInitBlockContext arrayInitBlock(int i) {
			return getRuleContext(ArrayInitBlockContext.class,i);
		}
		public List<FunctionDefineContext> functionDefine() {
			return getRuleContexts(FunctionDefineContext.class);
		}
		public FunctionDefineContext functionDefine(int i) {
			return getRuleContext(FunctionDefineContext.class,i);
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
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Double) | (1L << Int))) != 0)) {
				{
				setState(61);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(58);
					initialBlock();
					}
					break;
				case 2:
					{
					setState(59);
					arrayInitBlock();
					}
					break;
				case 3:
					{
					setState(60);
					functionDefine();
					}
					break;
				}
				}
				setState(65);
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

	public static class FunctionDefineContext extends ParserRuleContext {
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public FuncBodyContext funcBody() {
			return getRuleContext(FuncBodyContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public FunctionDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefine; }
	}

	public final FunctionDefineContext functionDefine() throws RecognitionException {
		FunctionDefineContext _localctx = new FunctionDefineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functionDefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			typeDef();
			setState(67);
			match(Identifier);
			setState(68);
			match(LeftParen);
			setState(69);
			params();
			setState(70);
			match(RightParen);
			setState(71);
			match(LeftBrace);
			setState(72);
			funcBody();
			setState(73);
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

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(75);
				param();
				}
				break;
			case 2:
				{
				setState(76);
				param();
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(77);
					match(Comma);
					setState(78);
					param();
					}
					}
					setState(81); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==Comma );
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

	public static class ParamContext extends ParserRuleContext {
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			typeDef();
			setState(86);
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

	public static class FuncBodyContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ReturnBlockContext returnBlock() {
			return getRuleContext(ReturnBlockContext.class,0);
		}
		public FuncBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcBody; }
	}

	public final FuncBodyContext funcBody() throws RecognitionException {
		FuncBodyContext _localctx = new FuncBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			body();
			setState(89);
			returnBlock();
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

	public static class BodyContext extends ParserRuleContext {
		public List<BlocksContext> blocks() {
			return getRuleContexts(BlocksContext.class);
		}
		public BlocksContext blocks(int i) {
			return getRuleContext(BlocksContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public List<TerminalNode> Semi() { return getTokens(myParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(myParser.Semi, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_body);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(95);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						setState(91);
						blocks();
						}
						break;
					case 2:
						{
						setState(92);
						func();
						setState(93);
						match(Semi);
						}
						break;
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class BlocksContext extends ParserRuleContext {
		public InitialBlockContext initialBlock() {
			return getRuleContext(InitialBlockContext.class,0);
		}
		public ArrayInitBlockContext arrayInitBlock() {
			return getRuleContext(ArrayInitBlockContext.class,0);
		}
		public AssignBlockContext assignBlock() {
			return getRuleContext(AssignBlockContext.class,0);
		}
		public IfBlocksContext ifBlocks() {
			return getRuleContext(IfBlocksContext.class,0);
		}
		public ForBlockContext forBlock() {
			return getRuleContext(ForBlockContext.class,0);
		}
		public ReturnBlockContext returnBlock() {
			return getRuleContext(ReturnBlockContext.class,0);
		}
		public BlocksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocks; }
	}

	public final BlocksContext blocks() throws RecognitionException {
		BlocksContext _localctx = new BlocksContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_blocks);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				initialBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				arrayInitBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				assignBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				ifBlocks();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(104);
				forBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(105);
				returnBlock();
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

	public static class InitialBlockContext extends ParserRuleContext {
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(myParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(myParser.Identifier, i);
		}
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public List<TerminalNode> Assign() { return getTokens(myParser.Assign); }
		public TerminalNode Assign(int i) {
			return getToken(myParser.Assign, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public InitialBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialBlock; }
	}

	public final InitialBlockContext initialBlock() throws RecognitionException {
		InitialBlockContext _localctx = new InitialBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_initialBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			typeDef();
			setState(109);
			match(Identifier);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(110);
				match(Assign);
				setState(111);
				expr(0);
				}
			}

			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(114);
				match(Comma);
				setState(115);
				match(Identifier);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(116);
					match(Assign);
					setState(117);
					expr(0);
					}
				}

				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
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

	public static class ArrayInitBlockContext extends ParserRuleContext {
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode IntegerLiteral() { return getToken(myParser.IntegerLiteral, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public ArrayInitBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitBlock; }
	}

	public final ArrayInitBlockContext arrayInitBlock() throws RecognitionException {
		ArrayInitBlockContext _localctx = new ArrayInitBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arrayInitBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			typeDef();
			setState(128);
			match(Identifier);
			setState(129);
			match(LeftBracket);
			setState(130);
			match(IntegerLiteral);
			setState(131);
			match(RightBracket);
			setState(132);
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

	public static class AssignBlockContext extends ParserRuleContext {
		public ArrayItemContext arrayItem() {
			return getRuleContext(ArrayItemContext.class,0);
		}
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public AssignBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignBlock; }
	}

	public final AssignBlockContext assignBlock() throws RecognitionException {
		AssignBlockContext _localctx = new AssignBlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignBlock);
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				arrayItem();
				setState(135);
				match(Assign);
				setState(136);
				expr(0);
				setState(137);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(Identifier);
				setState(140);
				match(Assign);
				setState(141);
				expr(0);
				setState(142);
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

	public static class IfBlocksContext extends ParserRuleContext {
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public List<ElifBlockContext> elifBlock() {
			return getRuleContexts(ElifBlockContext.class);
		}
		public ElifBlockContext elifBlock(int i) {
			return getRuleContext(ElifBlockContext.class,i);
		}
		public ElseBlockContext elseBlock() {
			return getRuleContext(ElseBlockContext.class,0);
		}
		public IfBlocksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlocks; }
	}

	public final IfBlocksContext ifBlocks() throws RecognitionException {
		IfBlocksContext _localctx = new IfBlocksContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifBlocks);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			ifBlock();
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(147);
					elifBlock();
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(153);
				elseBlock();
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

	public static class IfBlockContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(myParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ifBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(If);
			setState(157);
			match(LeftParen);
			setState(158);
			condition();
			setState(159);
			match(RightParen);
			setState(160);
			match(LeftBrace);
			setState(161);
			body();
			setState(162);
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

	public static class ElifBlockContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(myParser.Else, 0); }
		public TerminalNode If() { return getToken(myParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public ElifBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elifBlock; }
	}

	public final ElifBlockContext elifBlock() throws RecognitionException {
		ElifBlockContext _localctx = new ElifBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_elifBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(Else);
			setState(165);
			match(If);
			setState(166);
			match(LeftParen);
			setState(167);
			condition();
			setState(168);
			match(RightParen);
			setState(169);
			match(LeftBrace);
			setState(170);
			body();
			setState(171);
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

	public static class ElseBlockContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(myParser.Else, 0); }
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public ElseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseBlock; }
	}

	public final ElseBlockContext elseBlock() throws RecognitionException {
		ElseBlockContext _localctx = new ElseBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_elseBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(Else);
			setState(174);
			match(LeftBrace);
			setState(175);
			body();
			setState(176);
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

	public static class ConditionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			expr(0);
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

	public static class ForBlockContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(myParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(myParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(myParser.Semi, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TerminalNode LeftBrace() { return getToken(myParser.LeftBrace, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(myParser.RightBrace, 0); }
		public For1BlockContext for1Block() {
			return getRuleContext(For1BlockContext.class,0);
		}
		public For3BlockContext for3Block() {
			return getRuleContext(For3BlockContext.class,0);
		}
		public ForBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBlock; }
	}

	public final ForBlockContext forBlock() throws RecognitionException {
		ForBlockContext _localctx = new ForBlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_forBlock);
		int _la;
		try {
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(For);
				setState(181);
				match(LeftParen);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(182);
					for1Block();
					}
				}

				setState(185);
				match(Semi);
				setState(186);
				condition();
				setState(187);
				match(Semi);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(188);
					for3Block();
					}
				}

				setState(191);
				match(RightParen);
				setState(192);
				match(LeftBrace);
				setState(193);
				body();
				setState(194);
				match(RightBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				match(For);
				setState(197);
				match(LeftParen);
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(198);
					for1Block();
					}
				}

				setState(201);
				match(Semi);
				setState(202);
				condition();
				setState(203);
				match(Semi);
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(204);
					for3Block();
					}
				}

				setState(207);
				match(RightParen);
				setState(208);
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

	public static class For1BlockContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Comma() { return getToken(myParser.Comma, 0); }
		public For1BlockContext for1Block() {
			return getRuleContext(For1BlockContext.class,0);
		}
		public For1BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for1Block; }
	}

	public final For1BlockContext for1Block() throws RecognitionException {
		For1BlockContext _localctx = new For1BlockContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_for1Block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(Identifier);
			setState(213);
			match(Assign);
			setState(214);
			expr(0);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(215);
				match(Comma);
				setState(216);
				for1Block();
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

	public static class For3BlockContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(myParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Comma() { return getToken(myParser.Comma, 0); }
		public For3BlockContext for3Block() {
			return getRuleContext(For3BlockContext.class,0);
		}
		public For3BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for3Block; }
	}

	public final For3BlockContext for3Block() throws RecognitionException {
		For3BlockContext _localctx = new For3BlockContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_for3Block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(Identifier);
			setState(220);
			match(Assign);
			setState(221);
			expr(0);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(222);
				match(Comma);
				setState(223);
				for3Block();
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

	public static class ReturnBlockContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(myParser.Return, 0); }
		public TerminalNode Semi() { return getToken(myParser.Semi, 0); }
		public TerminalNode IntegerLiteral() { return getToken(myParser.IntegerLiteral, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public ReturnBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnBlock; }
	}

	public final ReturnBlockContext returnBlock() throws RecognitionException {
		ReturnBlockContext _localctx = new ReturnBlockContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_returnBlock);
		int _la;
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				match(Return);
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IntegerLiteral) {
					{
					setState(227);
					match(IntegerLiteral);
					}
				}

				setState(230);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(Return);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(232);
					match(Identifier);
					}
				}

				setState(235);
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public TerminalNode Not() { return getToken(myParser.Not, 0); }
		public TerminalNode IntegerLiteral() { return getToken(myParser.IntegerLiteral, 0); }
		public TerminalNode Minus() { return getToken(myParser.Minus, 0); }
		public TerminalNode FloatingLiteral() { return getToken(myParser.FloatingLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(myParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(myParser.StringLiteral, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public TerminalNode Star() { return getToken(myParser.Star, 0); }
		public TerminalNode Div() { return getToken(myParser.Div, 0); }
		public TerminalNode Mod() { return getToken(myParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(myParser.Plus, 0); }
		public TerminalNode Equal() { return getToken(myParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(myParser.NotEqual, 0); }
		public TerminalNode Less() { return getToken(myParser.Less, 0); }
		public TerminalNode Greater() { return getToken(myParser.Greater, 0); }
		public TerminalNode LessEqual() { return getToken(myParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(myParser.GreaterEqual, 0); }
		public TerminalNode AndAnd() { return getToken(myParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(myParser.OrOr, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(239);
				match(LeftParen);
				setState(240);
				expr(0);
				setState(241);
				match(RightParen);
				}
				break;
			case 2:
				{
				setState(243);
				match(Not);
				setState(244);
				expr(20);
				}
				break;
			case 3:
				{
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Minus) {
					{
					setState(245);
					match(Minus);
					}
				}

				setState(248);
				match(IntegerLiteral);
				}
				break;
			case 4:
				{
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Minus) {
					{
					setState(249);
					match(Minus);
					}
				}

				setState(252);
				match(FloatingLiteral);
				}
				break;
			case 5:
				{
				setState(253);
				match(CharacterLiteral);
				}
				break;
			case 6:
				{
				setState(254);
				match(StringLiteral);
				}
				break;
			case 7:
				{
				setState(255);
				match(Identifier);
				}
				break;
			case 8:
				{
				setState(256);
				func();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(300);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(298);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(259);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(260);
						match(Star);
						setState(261);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(262);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(263);
						match(Div);
						setState(264);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(265);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(266);
						match(Mod);
						setState(267);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(268);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(269);
						match(Plus);
						setState(270);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(271);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(272);
						match(Minus);
						setState(273);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(274);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(275);
						match(Equal);
						setState(276);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(277);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(278);
						match(NotEqual);
						setState(279);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(280);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(281);
						match(Less);
						setState(282);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(283);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(284);
						match(Greater);
						setState(285);
						expr(12);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(286);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(287);
						match(LessEqual);
						setState(288);
						expr(11);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(289);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(290);
						match(GreaterEqual);
						setState(291);
						expr(10);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(292);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(293);
						match(AndAnd);
						setState(294);
						expr(9);
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(295);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(296);
						match(OrOr);
						setState(297);
						expr(8);
						}
						break;
					}
					} 
				}
				setState(302);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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

	public static class TypeDefContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(myParser.Int, 0); }
		public TerminalNode Double() { return getToken(myParser.Double, 0); }
		public TerminalNode Char() { return getToken(myParser.Char, 0); }
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Double) | (1L << Int))) != 0)) ) {
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

	public static class ArrayRefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public TerminalNode IntegerLiteral() { return getToken(myParser.IntegerLiteral, 0); }
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public ArrayRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayRef; }
	}

	public final ArrayRefContext arrayRef() throws RecognitionException {
		ArrayRefContext _localctx = new ArrayRefContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arrayRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(Identifier);
			setState(306);
			match(LeftBracket);
			setState(307);
			match(IntegerLiteral);
			setState(308);
			match(RightBracket);
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

	public static class ArrayItemContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(myParser.LeftBracket, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(myParser.RightBracket, 0); }
		public ArrayItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayItem; }
	}

	public final ArrayItemContext arrayItem() throws RecognitionException {
		ArrayItemContext _localctx = new ArrayItemContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arrayItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(Identifier);
			setState(311);
			match(LeftBracket);
			setState(312);
			expr(0);
			setState(313);
			match(RightBracket);
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

	public static class FuncContext extends ParserRuleContext {
		public StrlenRefContext strlenRef() {
			return getRuleContext(StrlenRefContext.class,0);
		}
		public PrintfRefContext printfRef() {
			return getRuleContext(PrintfRefContext.class,0);
		}
		public GetsRefContext getsRef() {
			return getRuleContext(GetsRefContext.class,0);
		}
		public FuncRefContext funcRef() {
			return getRuleContext(FuncRefContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_func);
		try {
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Strlen:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				strlenRef();
				}
				break;
			case Printf:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				printfRef();
				}
				break;
			case Gets:
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
				getsRef();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(318);
				funcRef();
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

	public static class StrlenRefContext extends ParserRuleContext {
		public TerminalNode Strlen() { return getToken(myParser.Strlen, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public StrlenRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strlenRef; }
	}

	public final StrlenRefContext strlenRef() throws RecognitionException {
		StrlenRefContext _localctx = new StrlenRefContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_strlenRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(Strlen);
			setState(322);
			match(LeftParen);
			setState(323);
			match(Identifier);
			setState(324);
			match(RightParen);
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

	public static class PrintfRefContext extends ParserRuleContext {
		public TerminalNode Printf() { return getToken(myParser.Printf, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode StringLiteral() { return getToken(myParser.StringLiteral, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public PrintfRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printfRef; }
	}

	public final PrintfRefContext printfRef() throws RecognitionException {
		PrintfRefContext _localctx = new PrintfRefContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_printfRef);
		int _la;
		try {
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(326);
				match(Printf);
				setState(327);
				match(LeftParen);
				setState(328);
				match(StringLiteral);
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(329);
					match(Comma);
					setState(330);
					expr(0);
					}
					}
					setState(335);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(336);
				match(RightParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(337);
				match(Printf);
				setState(338);
				match(LeftParen);
				setState(339);
				match(Identifier);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(340);
					match(Comma);
					setState(341);
					expr(0);
					}
					}
					setState(346);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(347);
				match(RightParen);
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

	public static class GetsRefContext extends ParserRuleContext {
		public TerminalNode Gets() { return getToken(myParser.Gets, 0); }
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode Identifier() { return getToken(myParser.Identifier, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public GetsRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getsRef; }
	}

	public final GetsRefContext getsRef() throws RecognitionException {
		GetsRefContext _localctx = new GetsRefContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_getsRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(Gets);
			setState(351);
			match(LeftParen);
			setState(352);
			match(Identifier);
			setState(353);
			match(RightParen);
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

	public static class FuncRefContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(myParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(myParser.Identifier, i);
		}
		public TerminalNode LeftParen() { return getToken(myParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(myParser.RightParen, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(myParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(myParser.Comma, i);
		}
		public FuncRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcRef; }
	}

	public final FuncRefContext funcRef() throws RecognitionException {
		FuncRefContext _localctx = new FuncRefContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_funcRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(Identifier);
			setState(356);
			match(LeftParen);
			setState(371);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral))) != 0) || _la==Identifier) {
				{
				setState(359);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IntegerLiteral:
				case CharacterLiteral:
				case FloatingLiteral:
				case StringLiteral:
					{
					setState(357);
					literal();
					}
					break;
				case Identifier:
					{
					setState(358);
					match(Identifier);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(361);
					match(Comma);
					setState(364);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case IntegerLiteral:
					case CharacterLiteral:
					case FloatingLiteral:
					case StringLiteral:
						{
						setState(362);
						literal();
						}
						break;
					case Identifier:
						{
						setState(363);
						match(Identifier);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(370);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(373);
			match(RightParen);
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
		public TerminalNode FloatingLiteral() { return getToken(myParser.FloatingLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(myParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(myParser.StringLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << CharacterLiteral) | (1L << FloatingLiteral) | (1L << StringLiteral))) != 0)) ) {
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
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 10);
		case 10:
			return precpred(_ctx, 9);
		case 11:
			return precpred(_ctx, 8);
		case 12:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u009a\u017c\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\7\2@\n"+
		"\2\f\2\16\2C\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\6\4R\n\4\r\4\16\4S\5\4V\n\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\7"+
		"\7b\n\7\f\7\16\7e\13\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bm\n\b\3\t\3\t\3\t\3"+
		"\t\5\ts\n\t\3\t\3\t\3\t\3\t\5\ty\n\t\7\t{\n\t\f\t\16\t~\13\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u0093\n\13\3\f\3\f\7\f\u0097\n\f\f\f\16\f\u009a\13\f\3\f\5"+
		"\f\u009d\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\5\21\u00ba\n\21\3\21\3\21\3\21\3\21\5\21\u00c0\n\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\5\21\u00ca\n\21\3\21\3\21\3\21\3\21\5\21\u00d0"+
		"\n\21\3\21\3\21\3\21\5\21\u00d5\n\21\3\22\3\22\3\22\3\22\3\22\5\22\u00dc"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\5\23\u00e3\n\23\3\24\3\24\5\24\u00e7\n"+
		"\24\3\24\3\24\3\24\5\24\u00ec\n\24\3\24\5\24\u00ef\n\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\5\25\u00f9\n\25\3\25\3\25\5\25\u00fd\n\25\3"+
		"\25\3\25\3\25\3\25\3\25\5\25\u0104\n\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u012d\n\25\f\25\16\25\u0130\13\25\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\5\31\u0142\n\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\7\33\u014e\n\33\f\33\16\33\u0151\13\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\7\33\u0159\n\33\f\33\16\33\u015c\13\33\3\33\5\33\u015f\n\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\5\35\u016a\n\35\3\35\3\35\3\35\5\35"+
		"\u016f\n\35\7\35\u0171\n\35\f\35\16\35\u0174\13\35\5\35\u0176\n\35\3\35"+
		"\3\35\3\36\3\36\3\36\2\3(\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:\2\4\5\2\23\23\35\35,,\3\2\3\6\2\u019b\2A\3\2\2\2"+
		"\4D\3\2\2\2\6U\3\2\2\2\bW\3\2\2\2\nZ\3\2\2\2\fc\3\2\2\2\16l\3\2\2\2\20"+
		"n\3\2\2\2\22\u0081\3\2\2\2\24\u0092\3\2\2\2\26\u0094\3\2\2\2\30\u009e"+
		"\3\2\2\2\32\u00a6\3\2\2\2\34\u00af\3\2\2\2\36\u00b4\3\2\2\2 \u00d4\3\2"+
		"\2\2\"\u00d6\3\2\2\2$\u00dd\3\2\2\2&\u00ee\3\2\2\2(\u0103\3\2\2\2*\u0131"+
		"\3\2\2\2,\u0133\3\2\2\2.\u0138\3\2\2\2\60\u0141\3\2\2\2\62\u0143\3\2\2"+
		"\2\64\u015e\3\2\2\2\66\u0160\3\2\2\28\u0165\3\2\2\2:\u0179\3\2\2\2<@\5"+
		"\20\t\2=@\5\22\n\2>@\5\4\3\2?<\3\2\2\2?=\3\2\2\2?>\3\2\2\2@C\3\2\2\2A"+
		"?\3\2\2\2AB\3\2\2\2B\3\3\2\2\2CA\3\2\2\2DE\5*\26\2EF\7\u0083\2\2FG\7T"+
		"\2\2GH\5\6\4\2HI\7U\2\2IJ\7X\2\2JK\5\n\6\2KL\7Y\2\2L\5\3\2\2\2MV\5\b\5"+
		"\2NQ\5\b\5\2OP\7y\2\2PR\5\b\5\2QO\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2"+
		"\2TV\3\2\2\2UM\3\2\2\2UN\3\2\2\2UV\3\2\2\2V\7\3\2\2\2WX\5*\26\2XY\7\u0083"+
		"\2\2Y\t\3\2\2\2Z[\5\f\7\2[\\\5&\24\2\\\13\3\2\2\2]b\5\16\b\2^_\5\60\31"+
		"\2_`\7\177\2\2`b\3\2\2\2a]\3\2\2\2a^\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2"+
		"\2\2d\r\3\2\2\2ec\3\2\2\2fm\5\20\t\2gm\5\22\n\2hm\5\24\13\2im\5\26\f\2"+
		"jm\5 \21\2km\5&\24\2lf\3\2\2\2lg\3\2\2\2lh\3\2\2\2li\3\2\2\2lj\3\2\2\2"+
		"lk\3\2\2\2m\17\3\2\2\2no\5*\26\2or\7\u0083\2\2pq\7d\2\2qs\5(\25\2rp\3"+
		"\2\2\2rs\3\2\2\2s|\3\2\2\2tu\7y\2\2ux\7\u0083\2\2vw\7d\2\2wy\5(\25\2x"+
		"v\3\2\2\2xy\3\2\2\2y{\3\2\2\2zt\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2"+
		"}\177\3\2\2\2~|\3\2\2\2\177\u0080\7\177\2\2\u0080\21\3\2\2\2\u0081\u0082"+
		"\5*\26\2\u0082\u0083\7\u0083\2\2\u0083\u0084\7V\2\2\u0084\u0085\7\3\2"+
		"\2\u0085\u0086\7W\2\2\u0086\u0087\7\177\2\2\u0087\23\3\2\2\2\u0088\u0089"+
		"\5.\30\2\u0089\u008a\7d\2\2\u008a\u008b\5(\25\2\u008b\u008c\7\177\2\2"+
		"\u008c\u0093\3\2\2\2\u008d\u008e\7\u0083\2\2\u008e\u008f\7d\2\2\u008f"+
		"\u0090\5(\25\2\u0090\u0091\7\177\2\2\u0091\u0093\3\2\2\2\u0092\u0088\3"+
		"\2\2\2\u0092\u008d\3\2\2\2\u0093\25\3\2\2\2\u0094\u0098\5\30\r\2\u0095"+
		"\u0097\5\32\16\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3"+
		"\2\2\2\u0098\u0099\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009b"+
		"\u009d\5\34\17\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\27\3\2"+
		"\2\2\u009e\u009f\7*\2\2\u009f\u00a0\7T\2\2\u00a0\u00a1\5\36\20\2\u00a1"+
		"\u00a2\7U\2\2\u00a2\u00a3\7X\2\2\u00a3\u00a4\5\f\7\2\u00a4\u00a5\7Y\2"+
		"\2\u00a5\31\3\2\2\2\u00a6\u00a7\7\37\2\2\u00a7\u00a8\7*\2\2\u00a8\u00a9"+
		"\7T\2\2\u00a9\u00aa\5\36\20\2\u00aa\u00ab\7U\2\2\u00ab\u00ac\7X\2\2\u00ac"+
		"\u00ad\5\f\7\2\u00ad\u00ae\7Y\2\2\u00ae\33\3\2\2\2\u00af\u00b0\7\37\2"+
		"\2\u00b0\u00b1\7X\2\2\u00b1\u00b2\5\f\7\2\u00b2\u00b3\7Y\2\2\u00b3\35"+
		"\3\2\2\2\u00b4\u00b5\5(\25\2\u00b5\37\3\2\2\2\u00b6\u00b7\7\'\2\2\u00b7"+
		"\u00b9\7T\2\2\u00b8\u00ba\5\"\22\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\177\2\2\u00bc\u00bd\5\36\20\2\u00bd"+
		"\u00bf\7\177\2\2\u00be\u00c0\5$\23\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3"+
		"\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7U\2\2\u00c2\u00c3\7X\2\2\u00c3"+
		"\u00c4\5\f\7\2\u00c4\u00c5\7Y\2\2\u00c5\u00d5\3\2\2\2\u00c6\u00c7\7\'"+
		"\2\2\u00c7\u00c9\7T\2\2\u00c8\u00ca\5\"\22\2\u00c9\u00c8\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\177\2\2\u00cc\u00cd\5"+
		"\36\20\2\u00cd\u00cf\7\177\2\2\u00ce\u00d0\5$\23\2\u00cf\u00ce\3\2\2\2"+
		"\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\7U\2\2\u00d2\u00d3"+
		"\7\177\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00b6\3\2\2\2\u00d4\u00c6\3\2\2\2"+
		"\u00d5!\3\2\2\2\u00d6\u00d7\7\u0083\2\2\u00d7\u00d8\7d\2\2\u00d8\u00db"+
		"\5(\25\2\u00d9\u00da\7y\2\2\u00da\u00dc\5\"\22\2\u00db\u00d9\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc#\3\2\2\2\u00dd\u00de\7\u0083\2\2\u00de\u00df\7d\2"+
		"\2\u00df\u00e2\5(\25\2\u00e0\u00e1\7y\2\2\u00e1\u00e3\5$\23\2\u00e2\u00e0"+
		"\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3%\3\2\2\2\u00e4\u00e6\7:\2\2\u00e5\u00e7"+
		"\7\3\2\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00ef\7\177\2\2\u00e9\u00eb\7:\2\2\u00ea\u00ec\7\u0083\2\2\u00eb\u00ea"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\7\177\2\2"+
		"\u00ee\u00e4\3\2\2\2\u00ee\u00e9\3\2\2\2\u00ef\'\3\2\2\2\u00f0\u00f1\b"+
		"\25\1\2\u00f1\u00f2\7T\2\2\u00f2\u00f3\5(\25\2\u00f3\u00f4\7U\2\2\u00f4"+
		"\u0104\3\2\2\2\u00f5\u00f6\7c\2\2\u00f6\u0104\5(\25\26\u00f7\u00f9\7["+
		"\2\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u0104\7\3\2\2\u00fb\u00fd\7[\2\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0104\7\5\2\2\u00ff\u0104\7\4\2\2\u0100"+
		"\u0104\7\6\2\2\u0101\u0104\7\u0083\2\2\u0102\u0104\5\60\31\2\u0103\u00f0"+
		"\3\2\2\2\u0103\u00f5\3\2\2\2\u0103\u00f8\3\2\2\2\u0103\u00fc\3\2\2\2\u0103"+
		"\u00ff\3\2\2\2\u0103\u0100\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0102\3\2"+
		"\2\2\u0104\u012e\3\2\2\2\u0105\u0106\f\25\2\2\u0106\u0107\7\\\2\2\u0107"+
		"\u012d\5(\25\26\u0108\u0109\f\24\2\2\u0109\u010a\7]\2\2\u010a\u012d\5"+
		"(\25\25\u010b\u010c\f\23\2\2\u010c\u010d\7^\2\2\u010d\u012d\5(\25\24\u010e"+
		"\u010f\f\22\2\2\u010f\u0110\7Z\2\2\u0110\u012d\5(\25\23\u0111\u0112\f"+
		"\21\2\2\u0112\u0113\7[\2\2\u0113\u012d\5(\25\22\u0114\u0115\f\20\2\2\u0115"+
		"\u0116\7q\2\2\u0116\u012d\5(\25\21\u0117\u0118\f\17\2\2\u0118\u0119\7"+
		"r\2\2\u0119\u012d\5(\25\20\u011a\u011b\f\16\2\2\u011b\u011c\7e\2\2\u011c"+
		"\u012d\5(\25\17\u011d\u011e\f\r\2\2\u011e\u011f\7f\2\2\u011f\u012d\5("+
		"\25\16\u0120\u0121\f\f\2\2\u0121\u0122\7s\2\2\u0122\u012d\5(\25\r\u0123"+
		"\u0124\f\13\2\2\u0124\u0125\7t\2\2\u0125\u012d\5(\25\f\u0126\u0127\f\n"+
		"\2\2\u0127\u0128\7u\2\2\u0128\u012d\5(\25\13\u0129\u012a\f\t\2\2\u012a"+
		"\u012b\7v\2\2\u012b\u012d\5(\25\n\u012c\u0105\3\2\2\2\u012c\u0108\3\2"+
		"\2\2\u012c\u010b\3\2\2\2\u012c\u010e\3\2\2\2\u012c\u0111\3\2\2\2\u012c"+
		"\u0114\3\2\2\2\u012c\u0117\3\2\2\2\u012c\u011a\3\2\2\2\u012c\u011d\3\2"+
		"\2\2\u012c\u0120\3\2\2\2\u012c\u0123\3\2\2\2\u012c\u0126\3\2\2\2\u012c"+
		"\u0129\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f)\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0132\t\2\2\2\u0132+\3\2"+
		"\2\2\u0133\u0134\7\u0083\2\2\u0134\u0135\7V\2\2\u0135\u0136\7\3\2\2\u0136"+
		"\u0137\7W\2\2\u0137-\3\2\2\2\u0138\u0139\7\u0083\2\2\u0139\u013a\7V\2"+
		"\2\u013a\u013b\5(\25\2\u013b\u013c\7W\2\2\u013c/\3\2\2\2\u013d\u0142\5"+
		"\62\32\2\u013e\u0142\5\64\33\2\u013f\u0142\5\66\34\2\u0140\u0142\58\35"+
		"\2\u0141\u013d\3\2\2\2\u0141\u013e\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0140"+
		"\3\2\2\2\u0142\61\3\2\2\2\u0143\u0144\7\u0098\2\2\u0144\u0145\7T\2\2\u0145"+
		"\u0146\7\u0083\2\2\u0146\u0147\7U\2\2\u0147\63\3\2\2\2\u0148\u0149\7\u0099"+
		"\2\2\u0149\u014a\7T\2\2\u014a\u014f\7\6\2\2\u014b\u014c\7y\2\2\u014c\u014e"+
		"\5(\25\2\u014d\u014b\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u015f\7U"+
		"\2\2\u0153\u0154\7\u0099\2\2\u0154\u0155\7T\2\2\u0155\u015a\7\u0083\2"+
		"\2\u0156\u0157\7y\2\2\u0157\u0159\5(\25\2\u0158\u0156\3\2\2\2\u0159\u015c"+
		"\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2\2\2\u015c"+
		"\u015a\3\2\2\2\u015d\u015f\7U\2\2\u015e\u0148\3\2\2\2\u015e\u0153\3\2"+
		"\2\2\u015f\65\3\2\2\2\u0160\u0161\7\u009a\2\2\u0161\u0162\7T\2\2\u0162"+
		"\u0163\7\u0083\2\2\u0163\u0164\7U\2\2\u0164\67\3\2\2\2\u0165\u0166\7\u0083"+
		"\2\2\u0166\u0175\7T\2\2\u0167\u016a\5:\36\2\u0168\u016a\7\u0083\2\2\u0169"+
		"\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a\u0172\3\2\2\2\u016b\u016e\7y"+
		"\2\2\u016c\u016f\5:\36\2\u016d\u016f\7\u0083\2\2\u016e\u016c\3\2\2\2\u016e"+
		"\u016d\3\2\2\2\u016f\u0171\3\2\2\2\u0170\u016b\3\2\2\2\u0171\u0174\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0176\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0175\u0169\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177\u0178\7U\2\2\u01789\3\2\2\2\u0179\u017a\t\3\2\2\u017a;\3\2"+
		"\2\2&?ASUaclrx|\u0092\u0098\u009c\u00b9\u00bf\u00c9\u00cf\u00d4\u00db"+
		"\u00e2\u00e6\u00eb\u00ee\u00f8\u00fc\u0103\u012c\u012e\u0141\u014f\u015a"+
		"\u015e\u0169\u016e\u0172\u0175";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}