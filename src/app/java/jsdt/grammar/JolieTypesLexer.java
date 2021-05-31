// Generated from JolieTypes.g4 by ANTLR 4.9.2
package jsdt.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JolieTypesLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "Single", 
			"MaybeSingle", "Multi", "PositiveIntegerLiteral", "NonZeroDigits", "Digits", 
			"Identifier", "JavaLetter", "JavaLetterOrDigit", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'interface'", "'Interface'", "'{'", "'oneWay'", "'OneWay'", "':'", 
			"'requestResponse'", "'RequestResponse'", "'}'", "'('", "')'", "'throws'", 
			"','", "'type'", "'void'", "'bool'", "'int'", "'long'", "'string'", "'byte'", 
			"'any'", "'undefined'", "'.'", "'|'"
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


	public JolieTypesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JolieTypes.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 31:
			return JavaLetter_sempred((RuleContext)_localctx, predIndex);
		case 32:
			return JavaLetterOrDigit_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean JavaLetter_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return Character.isJavaIdentifierStart(_input.LA(-1));
		case 1:
			return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean JavaLetterOrDigit_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return Character.isJavaIdentifierPart(_input.LA(-1));
		case 3:
			return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u0138\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\5\33\u00e3\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\5\34\u00f2\n\34\3\35\3\35\3\35\7\35\u00f7\n\35\f\35\16"+
		"\35\u00fa\13\35\5\35\u00fc\n\35\3\36\3\36\3\37\3\37\3 \3 \7 \u0104\n "+
		"\f \16 \u0107\13 \3!\3!\3!\3!\3!\3!\5!\u010f\n!\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\5\"\u0117\n\"\3#\6#\u011a\n#\r#\16#\u011b\3#\3#\3$\3$\3$\3$\7$\u0124"+
		"\n$\f$\16$\u0127\13$\3$\3$\3$\3$\3$\3%\3%\3%\3%\7%\u0132\n%\f%\16%\u0135"+
		"\13%\3%\3%\3\u0125\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\2=\2?\37A\2C\2E G!I\"\3\2\13\3\2\63;\3\2\62;\6\2&&C"+
		"\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7\2&&\62"+
		";C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u0140\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2?\3\2\2\2\2E\3\2\2\2\2G\3"+
		"\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5U\3\2\2\2\7_\3\2\2\2\ta\3\2\2\2\13h\3\2"+
		"\2\2\ro\3\2\2\2\17q\3\2\2\2\21\u0081\3\2\2\2\23\u0091\3\2\2\2\25\u0093"+
		"\3\2\2\2\27\u0095\3\2\2\2\31\u0097\3\2\2\2\33\u009e\3\2\2\2\35\u00a0\3"+
		"\2\2\2\37\u00a5\3\2\2\2!\u00aa\3\2\2\2#\u00af\3\2\2\2%\u00b3\3\2\2\2\'"+
		"\u00b8\3\2\2\2)\u00bf\3\2\2\2+\u00c4\3\2\2\2-\u00c8\3\2\2\2/\u00d2\3\2"+
		"\2\2\61\u00d4\3\2\2\2\63\u00d6\3\2\2\2\65\u00e2\3\2\2\2\67\u00f1\3\2\2"+
		"\29\u00fb\3\2\2\2;\u00fd\3\2\2\2=\u00ff\3\2\2\2?\u0101\3\2\2\2A\u010e"+
		"\3\2\2\2C\u0116\3\2\2\2E\u0119\3\2\2\2G\u011f\3\2\2\2I\u012d\3\2\2\2K"+
		"L\7k\2\2LM\7p\2\2MN\7v\2\2NO\7g\2\2OP\7t\2\2PQ\7h\2\2QR\7c\2\2RS\7e\2"+
		"\2ST\7g\2\2T\4\3\2\2\2UV\7K\2\2VW\7p\2\2WX\7v\2\2XY\7g\2\2YZ\7t\2\2Z["+
		"\7h\2\2[\\\7c\2\2\\]\7e\2\2]^\7g\2\2^\6\3\2\2\2_`\7}\2\2`\b\3\2\2\2ab"+
		"\7q\2\2bc\7p\2\2cd\7g\2\2de\7Y\2\2ef\7c\2\2fg\7{\2\2g\n\3\2\2\2hi\7Q\2"+
		"\2ij\7p\2\2jk\7g\2\2kl\7Y\2\2lm\7c\2\2mn\7{\2\2n\f\3\2\2\2op\7<\2\2p\16"+
		"\3\2\2\2qr\7t\2\2rs\7g\2\2st\7s\2\2tu\7w\2\2uv\7g\2\2vw\7u\2\2wx\7v\2"+
		"\2xy\7T\2\2yz\7g\2\2z{\7u\2\2{|\7r\2\2|}\7q\2\2}~\7p\2\2~\177\7u\2\2\177"+
		"\u0080\7g\2\2\u0080\20\3\2\2\2\u0081\u0082\7T\2\2\u0082\u0083\7g\2\2\u0083"+
		"\u0084\7s\2\2\u0084\u0085\7w\2\2\u0085\u0086\7g\2\2\u0086\u0087\7u\2\2"+
		"\u0087\u0088\7v\2\2\u0088\u0089\7T\2\2\u0089\u008a\7g\2\2\u008a\u008b"+
		"\7u\2\2\u008b\u008c\7r\2\2\u008c\u008d\7q\2\2\u008d\u008e\7p\2\2\u008e"+
		"\u008f\7u\2\2\u008f\u0090\7g\2\2\u0090\22\3\2\2\2\u0091\u0092\7\177\2"+
		"\2\u0092\24\3\2\2\2\u0093\u0094\7*\2\2\u0094\26\3\2\2\2\u0095\u0096\7"+
		"+\2\2\u0096\30\3\2\2\2\u0097\u0098\7v\2\2\u0098\u0099\7j\2\2\u0099\u009a"+
		"\7t\2\2\u009a\u009b\7q\2\2\u009b\u009c\7y\2\2\u009c\u009d\7u\2\2\u009d"+
		"\32\3\2\2\2\u009e\u009f\7.\2\2\u009f\34\3\2\2\2\u00a0\u00a1\7v\2\2\u00a1"+
		"\u00a2\7{\2\2\u00a2\u00a3\7r\2\2\u00a3\u00a4\7g\2\2\u00a4\36\3\2\2\2\u00a5"+
		"\u00a6\7x\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7f\2\2"+
		"\u00a9 \3\2\2\2\u00aa\u00ab\7d\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7q\2"+
		"\2\u00ad\u00ae\7n\2\2\u00ae\"\3\2\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7"+
		"p\2\2\u00b1\u00b2\7v\2\2\u00b2$\3\2\2\2\u00b3\u00b4\7n\2\2\u00b4\u00b5"+
		"\7q\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7\7i\2\2\u00b7&\3\2\2\2\u00b8\u00b9"+
		"\7u\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7k\2\2\u00bc"+
		"\u00bd\7p\2\2\u00bd\u00be\7i\2\2\u00be(\3\2\2\2\u00bf\u00c0\7d\2\2\u00c0"+
		"\u00c1\7{\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7g\2\2\u00c3*\3\2\2\2\u00c4"+
		"\u00c5\7c\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7{\2\2\u00c7,\3\2\2\2\u00c8"+
		"\u00c9\7w\2\2\u00c9\u00ca\7p\2\2\u00ca\u00cb\7f\2\2\u00cb\u00cc\7g\2\2"+
		"\u00cc\u00cd\7h\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0"+
		"\7g\2\2\u00d0\u00d1\7f\2\2\u00d1.\3\2\2\2\u00d2\u00d3\7\60\2\2\u00d3\60"+
		"\3\2\2\2\u00d4\u00d5\7~\2\2\u00d5\62\3\2\2\2\u00d6\u00d7\7]\2\2\u00d7"+
		"\u00d8\7\63\2\2\u00d8\u00d9\7.\2\2\u00d9\u00da\7\63\2\2\u00da\u00db\7"+
		"_\2\2\u00db\64\3\2\2\2\u00dc\u00dd\7]\2\2\u00dd\u00de\7\62\2\2\u00de\u00df"+
		"\7.\2\2\u00df\u00e0\7\63\2\2\u00e0\u00e3\7_\2\2\u00e1\u00e3\7A\2\2\u00e2"+
		"\u00dc\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\66\3\2\2\2\u00e4\u00e5\7]\2\2"+
		"\u00e5\u00e6\59\35\2\u00e6\u00e7\7.\2\2\u00e7\u00e8\59\35\2\u00e8\u00e9"+
		"\7_\2\2\u00e9\u00f2\3\2\2\2\u00ea\u00eb\7]\2\2\u00eb\u00ec\59\35\2\u00ec"+
		"\u00ed\7.\2\2\u00ed\u00ee\7,\2\2\u00ee\u00ef\7_\2\2\u00ef\u00f2\3\2\2"+
		"\2\u00f0\u00f2\7,\2\2\u00f1\u00e4\3\2\2\2\u00f1\u00ea\3\2\2\2\u00f1\u00f0"+
		"\3\2\2\2\u00f28\3\2\2\2\u00f3\u00fc\7\62\2\2\u00f4\u00f8\5;\36\2\u00f5"+
		"\u00f7\5=\37\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb"+
		"\u00f3\3\2\2\2\u00fb\u00f4\3\2\2\2\u00fc:\3\2\2\2\u00fd\u00fe\t\2\2\2"+
		"\u00fe<\3\2\2\2\u00ff\u0100\t\3\2\2\u0100>\3\2\2\2\u0101\u0105\5A!\2\u0102"+
		"\u0104\5C\"\2\u0103\u0102\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0105\u0106\3\2\2\2\u0106@\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010f"+
		"\t\4\2\2\u0109\u010a\n\5\2\2\u010a\u010f\6!\2\2\u010b\u010c\t\6\2\2\u010c"+
		"\u010d\t\7\2\2\u010d\u010f\6!\3\2\u010e\u0108\3\2\2\2\u010e\u0109\3\2"+
		"\2\2\u010e\u010b\3\2\2\2\u010fB\3\2\2\2\u0110\u0117\t\b\2\2\u0111\u0112"+
		"\n\5\2\2\u0112\u0117\6\"\4\2\u0113\u0114\t\6\2\2\u0114\u0115\t\7\2\2\u0115"+
		"\u0117\6\"\5\2\u0116\u0110\3\2\2\2\u0116\u0111\3\2\2\2\u0116\u0113\3\2"+
		"\2\2\u0117D\3\2\2\2\u0118\u011a\t\t\2\2\u0119\u0118\3\2\2\2\u011a\u011b"+
		"\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011e\b#\2\2\u011eF\3\2\2\2\u011f\u0120\7\61\2\2\u0120\u0121\7,\2\2\u0121"+
		"\u0125\3\2\2\2\u0122\u0124\13\2\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3"+
		"\2\2\2\u0125\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0128\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0128\u0129\7,\2\2\u0129\u012a\7\61\2\2\u012a\u012b\3\2"+
		"\2\2\u012b\u012c\b$\2\2\u012cH\3\2\2\2\u012d\u012e\7\61\2\2\u012e\u012f"+
		"\7\61\2\2\u012f\u0133\3\2\2\2\u0130\u0132\n\n\2\2\u0131\u0130\3\2\2\2"+
		"\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0136"+
		"\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u0137\b%\2\2\u0137J\3\2\2\2\r\2\u00e2"+
		"\u00f1\u00f8\u00fb\u0105\u010e\u0116\u011b\u0125\u0133\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}