// Generated from Expr.g4 by ANTLR 4.3
package edu.rit.dao.impl.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, INT=13, TEXT=14, NULL=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'"
	};
	public static final String[] ruleNames = {
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "INT", "TEXT", "NULL"
	};


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21e\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\5\16K\n\16\3\16\6\16N\n\16\r\16\16\16O\3\17\3\17\6\17T\n\17\r"+
		"\17\16\17U\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21\3\2\4\3\2\62;\4\2C\\c|g\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5$\3\2\2\2\7\'\3\2\2\2\t.\3\2"+
		"\2\2\13\63\3\2\2\2\r8\3\2\2\2\17:\3\2\2\2\21=\3\2\2\2\23A\3\2\2\2\25C"+
		"\3\2\2\2\27E\3\2\2\2\31G\3\2\2\2\33J\3\2\2\2\35Q\3\2\2\2\37Y\3\2\2\2!"+
		"\"\7>\2\2\"#\7?\2\2#\4\3\2\2\2$%\7>\2\2%&\7@\2\2&\6\3\2\2\2\'(\7K\2\2"+
		"()\7U\2\2)*\7G\2\2*+\7T\2\2+,\7T\2\2,-\7*\2\2-\b\3\2\2\2./\7C\2\2/\60"+
		"\7P\2\2\60\61\7F\2\2\61\62\7*\2\2\62\n\3\2\2\2\63\64\7P\2\2\64\65\7Q\2"+
		"\2\65\66\7V\2\2\66\67\7*\2\2\67\f\3\2\2\289\7+\2\29\16\3\2\2\2:;\7@\2"+
		"\2;<\7?\2\2<\20\3\2\2\2=>\7Q\2\2>?\7T\2\2?@\7*\2\2@\22\3\2\2\2AB\7>\2"+
		"\2B\24\3\2\2\2CD\7.\2\2D\26\3\2\2\2EF\7?\2\2F\30\3\2\2\2GH\7@\2\2H\32"+
		"\3\2\2\2IK\7%\2\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\t\2\2\2ML\3\2\2\2NO"+
		"\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\34\3\2\2\2QS\7$\2\2RT\t\3\2\2SR\3\2\2\2"+
		"TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7$\2\2X\36\3\2\2\2YZ\7K\2\2"+
		"Z[\7P\2\2[\\\7F\2\2\\]\7G\2\2]^\7Z\2\2^_\7*\2\2_`\7\62\2\2`a\7.\2\2ab"+
		"\7/\2\2bc\7\63\2\2cd\7+\2\2d \3\2\2\2\6\2JOU\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}