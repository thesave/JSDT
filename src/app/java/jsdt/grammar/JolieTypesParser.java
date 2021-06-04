// Generated from JolieTypes.g4 by ANTLR 4.9.2
package jsdt.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JolieTypesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		Single=25, MaybeSingle=26, Multi=27, PositiveIntegerLiteral=28, Identifier=29, 
		WS=30, COMMENT=31, LINE_COMMENT=32;
	public static final int
		RULE_typesOrInterfaces = 0, RULE_interfaceDeclaration = 1, RULE_oneWays = 2, 
		RULE_requestResponses = 3, RULE_throwTypeList = 4, RULE_identifierOrNativeType = 5, 
		RULE_typeDeclaration = 6, RULE_nativeType = 7, RULE_nodes = 8, RULE_subNodes = 9, 
		RULE_typeChoice = 10, RULE_cardinality = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"typesOrInterfaces", "interfaceDeclaration", "oneWays", "requestResponses", 
			"throwTypeList", "identifierOrNativeType", "typeDeclaration", "nativeType", 
			"nodes", "subNodes", "typeChoice", "cardinality"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'interface'", "'Interface'", "'{'", "'oneWay'", "'OneWay'", "':'", 
			"','", "'requestResponse'", "'RequestResponse'", "'}'", "'('", "')'", 
			"'throws'", "'type'", "'void'", "'bool'", "'int'", "'long'", "'string'", 
			"'byte'", "'any'", "'undefined'", "'.'", "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "Single", "MaybeSingle", "Multi", "PositiveIntegerLiteral", "Identifier", 
			"WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "JolieTypes.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JolieTypesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TypesOrInterfacesContext extends ParserRuleContext {
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public List<InterfaceDeclarationContext> interfaceDeclaration() {
			return getRuleContexts(InterfaceDeclarationContext.class);
		}
		public InterfaceDeclarationContext interfaceDeclaration(int i) {
			return getRuleContext(InterfaceDeclarationContext.class,i);
		}
		public TypesOrInterfacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesOrInterfaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterTypesOrInterfaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitTypesOrInterfaces(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitTypesOrInterfaces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesOrInterfacesContext typesOrInterfaces() throws RecognitionException {
		TypesOrInterfacesContext _localctx = new TypesOrInterfacesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_typesOrInterfaces);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(26);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__13:
					{
					setState(24);
					typeDeclaration();
					}
					break;
				case T__0:
				case T__1:
					{
					setState(25);
					interfaceDeclaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__13))) != 0) );
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

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public List<OneWaysContext> oneWays() {
			return getRuleContexts(OneWaysContext.class);
		}
		public OneWaysContext oneWays(int i) {
			return getRuleContext(OneWaysContext.class,i);
		}
		public List<RequestResponsesContext> requestResponses() {
			return getRuleContexts(RequestResponsesContext.class);
		}
		public RequestResponsesContext requestResponses(int i) {
			return getRuleContext(RequestResponsesContext.class,i);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitInterfaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitInterfaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(31);
			match(Identifier);
			setState(32);
			match(T__2);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3 || _la==T__4) {
				{
				setState(33);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(34);
				match(T__5);
				setState(35);
				oneWays();
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(36);
					match(T__6);
					setState(37);
					oneWays();
					}
					}
					setState(42);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__8) {
				{
				setState(45);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(46);
				match(T__5);
				setState(47);
				requestResponses();
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(48);
					match(T__6);
					setState(49);
					requestResponses();
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(57);
			match(T__9);
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

	public static class OneWaysContext extends ParserRuleContext {
		public Token operation;
		public IdentifierOrNativeTypeContext oneWayType;
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public IdentifierOrNativeTypeContext identifierOrNativeType() {
			return getRuleContext(IdentifierOrNativeTypeContext.class,0);
		}
		public OneWaysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneWays; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterOneWays(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitOneWays(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitOneWays(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneWaysContext oneWays() throws RecognitionException {
		OneWaysContext _localctx = new OneWaysContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_oneWays);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			((OneWaysContext)_localctx).operation = match(Identifier);
			setState(60);
			match(T__10);
			setState(61);
			((OneWaysContext)_localctx).oneWayType = identifierOrNativeType();
			setState(62);
			match(T__11);
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

	public static class RequestResponsesContext extends ParserRuleContext {
		public Token operation;
		public IdentifierOrNativeTypeContext requestType;
		public IdentifierOrNativeTypeContext responseType;
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public List<IdentifierOrNativeTypeContext> identifierOrNativeType() {
			return getRuleContexts(IdentifierOrNativeTypeContext.class);
		}
		public IdentifierOrNativeTypeContext identifierOrNativeType(int i) {
			return getRuleContext(IdentifierOrNativeTypeContext.class,i);
		}
		public ThrowTypeListContext throwTypeList() {
			return getRuleContext(ThrowTypeListContext.class,0);
		}
		public RequestResponsesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requestResponses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterRequestResponses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitRequestResponses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitRequestResponses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequestResponsesContext requestResponses() throws RecognitionException {
		RequestResponsesContext _localctx = new RequestResponsesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_requestResponses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			((RequestResponsesContext)_localctx).operation = match(Identifier);
			setState(65);
			match(T__10);
			setState(66);
			((RequestResponsesContext)_localctx).requestType = identifierOrNativeType();
			setState(67);
			match(T__11);
			setState(68);
			match(T__10);
			setState(69);
			((RequestResponsesContext)_localctx).responseType = identifierOrNativeType();
			setState(70);
			match(T__11);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(71);
				match(T__12);
				setState(72);
				throwTypeList();
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

	public static class ThrowTypeListContext extends ParserRuleContext {
		public IdentifierOrNativeTypeContext identifierOrNativeType() {
			return getRuleContext(IdentifierOrNativeTypeContext.class,0);
		}
		public ThrowTypeListContext throwTypeList() {
			return getRuleContext(ThrowTypeListContext.class,0);
		}
		public ThrowTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterThrowTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitThrowTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitThrowTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowTypeListContext throwTypeList() throws RecognitionException {
		ThrowTypeListContext _localctx = new ThrowTypeListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_throwTypeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			identifierOrNativeType();
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(76);
				match(T__6);
				setState(77);
				throwTypeList();
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

	public static class IdentifierOrNativeTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public NativeTypeContext nativeType() {
			return getRuleContext(NativeTypeContext.class,0);
		}
		public IdentifierOrNativeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierOrNativeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterIdentifierOrNativeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitIdentifierOrNativeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitIdentifierOrNativeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierOrNativeTypeContext identifierOrNativeType() throws RecognitionException {
		IdentifierOrNativeTypeContext _localctx = new IdentifierOrNativeTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_identifierOrNativeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(80);
				match(Identifier);
				}
				break;
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
				{
				setState(81);
				nativeType();
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

	public static class TypeDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public NativeTypeContext nativeType() {
			return getRuleContext(NativeTypeContext.class,0);
		}
		public NodesContext nodes() {
			return getRuleContext(NodesContext.class,0);
		}
		public TypeChoiceContext typeChoice() {
			return getRuleContext(TypeChoiceContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typeDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__13);
			setState(85);
			match(Identifier);
			setState(86);
			match(T__5);
			setState(87);
			nativeType();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(88);
				nodes();
				}
			}

			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(91);
				typeChoice();
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

	public static class NativeTypeContext extends ParserRuleContext {
		public NativeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nativeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterNativeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitNativeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitNativeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NativeTypeContext nativeType() throws RecognitionException {
		NativeTypeContext _localctx = new NativeTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_nativeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
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

	public static class NodesContext extends ParserRuleContext {
		public List<SubNodesContext> subNodes() {
			return getRuleContexts(SubNodesContext.class);
		}
		public SubNodesContext subNodes(int i) {
			return getRuleContext(SubNodesContext.class,i);
		}
		public NodesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterNodes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitNodes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitNodes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodesContext nodes() throws RecognitionException {
		NodesContext _localctx = new NodesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nodes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__2);
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				subNodes();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__22 || _la==Identifier );
			setState(102);
			match(T__9);
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

	public static class SubNodesContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public NativeTypeContext nativeType() {
			return getRuleContext(NativeTypeContext.class,0);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public NodesContext nodes() {
			return getRuleContext(NodesContext.class,0);
		}
		public TypeChoiceContext typeChoice() {
			return getRuleContext(TypeChoiceContext.class,0);
		}
		public SubNodesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subNodes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterSubNodes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitSubNodes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitSubNodes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubNodesContext subNodes() throws RecognitionException {
		SubNodesContext _localctx = new SubNodesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_subNodes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(104);
				match(T__22);
				}
			}

			setState(107);
			match(Identifier);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Single) | (1L << MaybeSingle) | (1L << Multi))) != 0)) {
				{
				setState(108);
				cardinality();
				}
			}

			setState(111);
			match(T__5);
			setState(112);
			nativeType();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(113);
				nodes();
				}
			}

			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(116);
				typeChoice();
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

	public static class TypeChoiceContext extends ParserRuleContext {
		public NativeTypeContext nativeType() {
			return getRuleContext(NativeTypeContext.class,0);
		}
		public NodesContext nodes() {
			return getRuleContext(NodesContext.class,0);
		}
		public TypeChoiceContext typeChoice() {
			return getRuleContext(TypeChoiceContext.class,0);
		}
		public TypeChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterTypeChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitTypeChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitTypeChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeChoiceContext typeChoice() throws RecognitionException {
		TypeChoiceContext _localctx = new TypeChoiceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__23);
			setState(120);
			nativeType();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(121);
				nodes();
				}
			}

			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(124);
				typeChoice();
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

	public static class CardinalityContext extends ParserRuleContext {
		public TerminalNode Single() { return getToken(JolieTypesParser.Single, 0); }
		public TerminalNode MaybeSingle() { return getToken(JolieTypesParser.MaybeSingle, 0); }
		public TerminalNode Multi() { return getToken(JolieTypesParser.Multi, 0); }
		public CardinalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardinality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitCardinality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitCardinality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cardinality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Single) | (1L << MaybeSingle) | (1L << Multi))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u0084\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\6\2\35\n\2\r\2\16\2\36\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\5\3.\n\3\3\3\3\3\3\3\3\3\3\3\7\3"+
		"\65\n\3\f\3\16\38\13\3\5\3:\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\5\6Q\n\6\3\7\3\7\5\7U"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\5\b\\\n\b\3\b\5\b_\n\b\3\t\3\t\3\n\3\n\6\ne\n"+
		"\n\r\n\16\nf\3\n\3\n\3\13\5\13l\n\13\3\13\3\13\5\13p\n\13\3\13\3\13\3"+
		"\13\5\13u\n\13\3\13\5\13x\n\13\3\f\3\f\3\f\5\f}\n\f\3\f\5\f\u0080\n\f"+
		"\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\7\3\2\3\4\3\2\6\7"+
		"\3\2\n\13\3\2\21\30\3\2\33\35\2\u0089\2\34\3\2\2\2\4 \3\2\2\2\6=\3\2\2"+
		"\2\bB\3\2\2\2\nM\3\2\2\2\fT\3\2\2\2\16V\3\2\2\2\20`\3\2\2\2\22b\3\2\2"+
		"\2\24k\3\2\2\2\26y\3\2\2\2\30\u0081\3\2\2\2\32\35\5\16\b\2\33\35\5\4\3"+
		"\2\34\32\3\2\2\2\34\33\3\2\2\2\35\36\3\2\2\2\36\34\3\2\2\2\36\37\3\2\2"+
		"\2\37\3\3\2\2\2 !\t\2\2\2!\"\7\37\2\2\"-\7\5\2\2#$\t\3\2\2$%\7\b\2\2%"+
		"*\5\6\4\2&\'\7\t\2\2\')\5\6\4\2(&\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2"+
		"\2+.\3\2\2\2,*\3\2\2\2-#\3\2\2\2-.\3\2\2\2.9\3\2\2\2/\60\t\4\2\2\60\61"+
		"\7\b\2\2\61\66\5\b\5\2\62\63\7\t\2\2\63\65\5\b\5\2\64\62\3\2\2\2\658\3"+
		"\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29/\3\2\2\29"+
		":\3\2\2\2:;\3\2\2\2;<\7\f\2\2<\5\3\2\2\2=>\7\37\2\2>?\7\r\2\2?@\5\f\7"+
		"\2@A\7\16\2\2A\7\3\2\2\2BC\7\37\2\2CD\7\r\2\2DE\5\f\7\2EF\7\16\2\2FG\7"+
		"\r\2\2GH\5\f\7\2HK\7\16\2\2IJ\7\17\2\2JL\5\n\6\2KI\3\2\2\2KL\3\2\2\2L"+
		"\t\3\2\2\2MP\5\f\7\2NO\7\t\2\2OQ\5\n\6\2PN\3\2\2\2PQ\3\2\2\2Q\13\3\2\2"+
		"\2RU\7\37\2\2SU\5\20\t\2TR\3\2\2\2TS\3\2\2\2U\r\3\2\2\2VW\7\20\2\2WX\7"+
		"\37\2\2XY\7\b\2\2Y[\5\20\t\2Z\\\5\22\n\2[Z\3\2\2\2[\\\3\2\2\2\\^\3\2\2"+
		"\2]_\5\26\f\2^]\3\2\2\2^_\3\2\2\2_\17\3\2\2\2`a\t\5\2\2a\21\3\2\2\2bd"+
		"\7\5\2\2ce\5\24\13\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh\3\2\2\2"+
		"hi\7\f\2\2i\23\3\2\2\2jl\7\31\2\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mo\7\37"+
		"\2\2np\5\30\r\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\b\2\2rt\5\20\t\2su\5"+
		"\22\n\2ts\3\2\2\2tu\3\2\2\2uw\3\2\2\2vx\5\26\f\2wv\3\2\2\2wx\3\2\2\2x"+
		"\25\3\2\2\2yz\7\32\2\2z|\5\20\t\2{}\5\22\n\2|{\3\2\2\2|}\3\2\2\2}\177"+
		"\3\2\2\2~\u0080\5\26\f\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\27\3\2\2"+
		"\2\u0081\u0082\t\6\2\2\u0082\31\3\2\2\2\24\34\36*-\669KPT[^fkotw|\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}