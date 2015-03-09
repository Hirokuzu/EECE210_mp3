package ca.ubc.ece.eece210.mp3;

// Generated from Catalogue.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CatalogueParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		START_GENRE=1, END_GENRE=2, START_ALBUM=3, END_ALBUM=4, START_NAME=5, 
		END_NAME=6, START_PERF=7, END_PERF=8, START_SONG=9, END_SONG=10, TEXT=11, 
		WS=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'<genre>'", "'</genre>'", "'<album>'", "'</album>'", "'<name>'", 
		"'</name>'", "'<performer>'", "'</performer>'", "'<song>'", "'</song>'", 
		"TEXT", "WS"
	};
	public static final int
		RULE_root = 0, RULE_name = 1, RULE_catalogue = 2, RULE_genre = 3, RULE_album = 4, 
		RULE_performer = 5, RULE_song = 6;
	public static final String[] ruleNames = {
		"root", "name", "catalogue", "genre", "album", "performer", "song"
	};

	@Override
	public String getGrammarFileName() { return "Catalogue.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CatalogueParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CatalogueParser.EOF, 0); }
		public CatalogueContext catalogue() {
			return getRuleContext(CatalogueContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); catalogue();
			setState(15); match(EOF);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode END_NAME() { return getToken(CatalogueParser.END_NAME, 0); }
		public TerminalNode TEXT() { return getToken(CatalogueParser.TEXT, 0); }
		public TerminalNode START_NAME() { return getToken(CatalogueParser.START_NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); match(START_NAME);
			setState(18); match(TEXT);
			setState(19); match(END_NAME);
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

	public static class CatalogueContext extends ParserRuleContext {
		public GenreContext genre(int i) {
			return getRuleContext(GenreContext.class,i);
		}
		public AlbumContext album(int i) {
			return getRuleContext(AlbumContext.class,i);
		}
		public List<AlbumContext> album() {
			return getRuleContexts(AlbumContext.class);
		}
		public List<GenreContext> genre() {
			return getRuleContexts(GenreContext.class);
		}
		public CatalogueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catalogue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterCatalogue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitCatalogue(this);
		}
	}

	public final CatalogueContext catalogue() throws RecognitionException {
		CatalogueContext _localctx = new CatalogueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_catalogue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==START_GENRE || _la==START_ALBUM) {
				{
				setState(23);
				switch (_input.LA(1)) {
				case START_GENRE:
					{
					setState(21); genre();
					}
					break;
				case START_ALBUM:
					{
					setState(22); album();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(27);
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

	public static class GenreContext extends ParserRuleContext {
		public GenreContext genre(int i) {
			return getRuleContext(GenreContext.class,i);
		}
		public AlbumContext album(int i) {
			return getRuleContext(AlbumContext.class,i);
		}
		public List<AlbumContext> album() {
			return getRuleContexts(AlbumContext.class);
		}
		public List<GenreContext> genre() {
			return getRuleContexts(GenreContext.class);
		}
		public TerminalNode START_GENRE() { return getToken(CatalogueParser.START_GENRE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode END_GENRE() { return getToken(CatalogueParser.END_GENRE, 0); }
		public GenreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genre; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterGenre(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitGenre(this);
		}
	}

	public final GenreContext genre() throws RecognitionException {
		GenreContext _localctx = new GenreContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_genre);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); match(START_GENRE);
			{
			setState(29); name();
			}
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==START_GENRE || _la==START_ALBUM) {
				{
				setState(32);
				switch (_input.LA(1)) {
				case START_GENRE:
					{
					setState(30); genre();
					}
					break;
				case START_ALBUM:
					{
					setState(31); album();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37); match(END_GENRE);
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

	public static class AlbumContext extends ParserRuleContext {
		public PerformerContext performer() {
			return getRuleContext(PerformerContext.class,0);
		}
		public SongContext song(int i) {
			return getRuleContext(SongContext.class,i);
		}
		public TerminalNode START_ALBUM() { return getToken(CatalogueParser.START_ALBUM, 0); }
		public List<SongContext> song() {
			return getRuleContexts(SongContext.class);
		}
		public TerminalNode END_ALBUM() { return getToken(CatalogueParser.END_ALBUM, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public AlbumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_album; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterAlbum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitAlbum(this);
		}
	}

	public final AlbumContext album() throws RecognitionException {
		AlbumContext _localctx = new AlbumContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_album);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); match(START_ALBUM);
			{
			setState(40); name();
			}
			{
			setState(41); performer();
			}
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42); song();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==START_SONG );
			setState(47); match(END_ALBUM);
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

	public static class PerformerContext extends ParserRuleContext {
		public TerminalNode END_PERF() { return getToken(CatalogueParser.END_PERF, 0); }
		public TerminalNode TEXT() { return getToken(CatalogueParser.TEXT, 0); }
		public TerminalNode START_PERF() { return getToken(CatalogueParser.START_PERF, 0); }
		public PerformerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_performer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterPerformer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitPerformer(this);
		}
	}

	public final PerformerContext performer() throws RecognitionException {
		PerformerContext _localctx = new PerformerContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_performer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(START_PERF);
			setState(50); match(TEXT);
			setState(51); match(END_PERF);
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

	public static class SongContext extends ParserRuleContext {
		public TerminalNode START_SONG() { return getToken(CatalogueParser.START_SONG, 0); }
		public TerminalNode TEXT() { return getToken(CatalogueParser.TEXT, 0); }
		public TerminalNode END_SONG() { return getToken(CatalogueParser.END_SONG, 0); }
		public SongContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_song; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).enterSong(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CatalogueListener ) ((CatalogueListener)listener).exitSong(this);
		}
	}

	public final SongContext song() throws RecognitionException {
		SongContext _localctx = new SongContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_song);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(START_SONG);
			setState(54); match(TEXT);
			setState(55); match(END_SONG);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\7\4\32\n\4\f\4\16\4\35\13\4\3\5\3\5\3\5\3\5\7\5#\n\5\f\5\16\5"+
		"&\13\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6.\n\6\r\6\16\6/\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\29\2\20\3\2\2\2\4\23\3"+
		"\2\2\2\6\33\3\2\2\2\b\36\3\2\2\2\n)\3\2\2\2\f\63\3\2\2\2\16\67\3\2\2\2"+
		"\20\21\5\6\4\2\21\22\7\2\2\3\22\3\3\2\2\2\23\24\7\7\2\2\24\25\7\r\2\2"+
		"\25\26\7\b\2\2\26\5\3\2\2\2\27\32\5\b\5\2\30\32\5\n\6\2\31\27\3\2\2\2"+
		"\31\30\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\7\3\2\2\2"+
		"\35\33\3\2\2\2\36\37\7\3\2\2\37$\5\4\3\2 #\5\b\5\2!#\5\n\6\2\" \3\2\2"+
		"\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'(\7"+
		"\4\2\2(\t\3\2\2\2)*\7\5\2\2*+\5\4\3\2+-\5\f\7\2,.\5\16\b\2-,\3\2\2\2."+
		"/\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\6\2\2\62\13\3\2"+
		"\2\2\63\64\7\t\2\2\64\65\7\r\2\2\65\66\7\n\2\2\66\r\3\2\2\2\678\7\13\2"+
		"\289\7\r\2\29:\7\f\2\2:\17\3\2\2\2\7\31\33\"$/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}