// Generated from JolieTypes.g4 by ANTLR 4.9.2
package grammar;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, Single=15, MaybeSingle=16, 
		Multi=17, PositiveIntegerLiteral=18, Identifier=19, WS=20, COMMENT=21, 
		LINE_COMMENT=22;
	public static final int
		RULE_typeDeclaration = 0, RULE_nativeType = 1, RULE_nodes = 2, RULE_subNodes = 3, 
		RULE_typeChoice = 4, RULE_nativeTypeOrIdentifier = 5, RULE_cardinality = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"typeDeclaration", "nativeType", "nodes", "subNodes", "typeChoice", "nativeTypeOrIdentifier", 
			"cardinality"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'type'", "':'", "'void'", "'bool'", "'int'", "'long'", "'string'", 
			"'byte'", "'any'", "'undefined'", "'{'", "'}'", "'.'", "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "Single", "MaybeSingle", "Multi", "PositiveIntegerLiteral", 
			"Identifier", "WS", "COMMENT", "LINE_COMMENT"
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
		enterRule(_localctx, 0, RULE_typeDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);
			setState(15);
			match(Identifier);
			setState(16);
			match(T__1);
			setState(17);
			nativeType();
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(18);
				nodes();
				}
			}

			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(21);
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
		enterRule(_localctx, 2, RULE_nativeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
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
		enterRule(_localctx, 4, RULE_nodes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__10);
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				subNodes();
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__12 || _la==Identifier );
			setState(32);
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

	public static class SubNodesContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public NativeTypeOrIdentifierContext nativeTypeOrIdentifier() {
			return getRuleContext(NativeTypeOrIdentifierContext.class,0);
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
		enterRule(_localctx, 6, RULE_subNodes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(34);
				match(T__12);
				}
			}

			setState(37);
			match(Identifier);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Single) | (1L << MaybeSingle) | (1L << Multi))) != 0)) {
				{
				setState(38);
				cardinality();
				}
			}

			setState(41);
			match(T__1);
			setState(42);
			nativeTypeOrIdentifier();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(43);
				nodes();
				}
			}

			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(46);
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
		public NativeTypeOrIdentifierContext nativeTypeOrIdentifier() {
			return getRuleContext(NativeTypeOrIdentifierContext.class,0);
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
		enterRule(_localctx, 8, RULE_typeChoice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(T__13);
			setState(50);
			nativeTypeOrIdentifier();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(51);
				nodes();
				}
			}

			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(54);
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

	public static class NativeTypeOrIdentifierContext extends ParserRuleContext {
		public NativeTypeContext nativeType() {
			return getRuleContext(NativeTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JolieTypesParser.Identifier, 0); }
		public NativeTypeOrIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nativeTypeOrIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).enterNativeTypeOrIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JolieTypesListener ) ((JolieTypesListener)listener).exitNativeTypeOrIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JolieTypesVisitor ) return ((JolieTypesVisitor<? extends T>)visitor).visitNativeTypeOrIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NativeTypeOrIdentifierContext nativeTypeOrIdentifier() throws RecognitionException {
		NativeTypeOrIdentifierContext _localctx = new NativeTypeOrIdentifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nativeTypeOrIdentifier);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				nativeType();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(Identifier);
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
		enterRule(_localctx, 12, RULE_cardinality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\5\2\26"+
		"\n\2\3\2\5\2\31\n\2\3\3\3\3\3\4\3\4\6\4\37\n\4\r\4\16\4 \3\4\3\4\3\5\5"+
		"\5&\n\5\3\5\3\5\5\5*\n\5\3\5\3\5\3\5\5\5/\n\5\3\5\5\5\62\n\5\3\6\3\6\3"+
		"\6\5\6\67\n\6\3\6\5\6:\n\6\3\7\3\7\5\7>\n\7\3\b\3\b\3\b\2\2\t\2\4\6\b"+
		"\n\f\16\2\4\3\2\5\f\3\2\21\23\2D\2\20\3\2\2\2\4\32\3\2\2\2\6\34\3\2\2"+
		"\2\b%\3\2\2\2\n\63\3\2\2\2\f=\3\2\2\2\16?\3\2\2\2\20\21\7\3\2\2\21\22"+
		"\7\25\2\2\22\23\7\4\2\2\23\25\5\4\3\2\24\26\5\6\4\2\25\24\3\2\2\2\25\26"+
		"\3\2\2\2\26\30\3\2\2\2\27\31\5\n\6\2\30\27\3\2\2\2\30\31\3\2\2\2\31\3"+
		"\3\2\2\2\32\33\t\2\2\2\33\5\3\2\2\2\34\36\7\r\2\2\35\37\5\b\5\2\36\35"+
		"\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\16\2\2#\7\3"+
		"\2\2\2$&\7\17\2\2%$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\')\7\25\2\2(*\5\16\b"+
		"\2)(\3\2\2\2)*\3\2\2\2*+\3\2\2\2+,\7\4\2\2,.\5\f\7\2-/\5\6\4\2.-\3\2\2"+
		"\2./\3\2\2\2/\61\3\2\2\2\60\62\5\n\6\2\61\60\3\2\2\2\61\62\3\2\2\2\62"+
		"\t\3\2\2\2\63\64\7\20\2\2\64\66\5\f\7\2\65\67\5\6\4\2\66\65\3\2\2\2\66"+
		"\67\3\2\2\2\679\3\2\2\28:\5\n\6\298\3\2\2\29:\3\2\2\2:\13\3\2\2\2;>\5"+
		"\4\3\2<>\7\25\2\2=;\3\2\2\2=<\3\2\2\2>\r\3\2\2\2?@\t\3\2\2@\17\3\2\2\2"+
		"\f\25\30 %).\61\669=";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}