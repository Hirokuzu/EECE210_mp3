package ca.ubc.ece.eece210.mp3;

// Generated from Catalogue.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CatalogueLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		START_GENRE=1, END_GENRE=2, START_ALBUM=3, END_ALBUM=4, START_NAME=5, 
		END_NAME=6, START_PERF=7, END_PERF=8, START_SONG=9, END_SONG=10, TEXT=11, 
		WS=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'"
	};
	public static final String[] ruleNames = {
		"START_GENRE", "END_GENRE", "START_ALBUM", "END_ALBUM", "START_NAME", 
		"END_NAME", "START_PERF", "END_PERF", "START_SONG", "END_SONG", "TEXT", 
		"WS"
	};


	public CatalogueLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Catalogue.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16\u0080\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\6\fv\n\f\r\f\16\fw\3\r\6\r"+
		"{\n\r\r\r\16\r|\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\3\2\4\5\2\13\f>>@@\3\2\13\f\u0081\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2"+
		"\2\2\5#\3\2\2\2\7,\3\2\2\2\t\64\3\2\2\2\13=\3\2\2\2\rD\3\2\2\2\17L\3\2"+
		"\2\2\21X\3\2\2\2\23e\3\2\2\2\25l\3\2\2\2\27u\3\2\2\2\31z\3\2\2\2\33\34"+
		"\7>\2\2\34\35\7i\2\2\35\36\7g\2\2\36\37\7p\2\2\37 \7t\2\2 !\7g\2\2!\""+
		"\7@\2\2\"\4\3\2\2\2#$\7>\2\2$%\7\61\2\2%&\7i\2\2&\'\7g\2\2\'(\7p\2\2("+
		")\7t\2\2)*\7g\2\2*+\7@\2\2+\6\3\2\2\2,-\7>\2\2-.\7c\2\2./\7n\2\2/\60\7"+
		"d\2\2\60\61\7w\2\2\61\62\7o\2\2\62\63\7@\2\2\63\b\3\2\2\2\64\65\7>\2\2"+
		"\65\66\7\61\2\2\66\67\7c\2\2\678\7n\2\289\7d\2\29:\7w\2\2:;\7o\2\2;<\7"+
		"@\2\2<\n\3\2\2\2=>\7>\2\2>?\7p\2\2?@\7c\2\2@A\7o\2\2AB\7g\2\2BC\7@\2\2"+
		"C\f\3\2\2\2DE\7>\2\2EF\7\61\2\2FG\7p\2\2GH\7c\2\2HI\7o\2\2IJ\7g\2\2JK"+
		"\7@\2\2K\16\3\2\2\2LM\7>\2\2MN\7r\2\2NO\7g\2\2OP\7t\2\2PQ\7h\2\2QR\7q"+
		"\2\2RS\7t\2\2ST\7o\2\2TU\7g\2\2UV\7t\2\2VW\7@\2\2W\20\3\2\2\2XY\7>\2\2"+
		"YZ\7\61\2\2Z[\7r\2\2[\\\7g\2\2\\]\7t\2\2]^\7h\2\2^_\7q\2\2_`\7t\2\2`a"+
		"\7o\2\2ab\7g\2\2bc\7t\2\2cd\7@\2\2d\22\3\2\2\2ef\7>\2\2fg\7u\2\2gh\7q"+
		"\2\2hi\7p\2\2ij\7i\2\2jk\7@\2\2k\24\3\2\2\2lm\7>\2\2mn\7\61\2\2no\7u\2"+
		"\2op\7q\2\2pq\7p\2\2qr\7i\2\2rs\7@\2\2s\26\3\2\2\2tv\n\2\2\2ut\3\2\2\2"+
		"vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\30\3\2\2\2y{\t\3\2\2zy\3\2\2\2{|\3\2\2"+
		"\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\b\r\2\2\177\32\3\2\2\2\5\2w|\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}