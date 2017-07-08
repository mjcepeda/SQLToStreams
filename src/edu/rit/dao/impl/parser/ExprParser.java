// Generated from Expr.g4 by ANTLR 4.3
package edu.rit.dao.impl.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, INT=12, TEXT=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'<='", "'<>'", "'AND('", "'NOT('", "')'", "'>='", "'OR('", 
		"'<'", "','", "'='", "'>'", "INT", "TEXT"
	};
	public static final int
		RULE_prog = 0, RULE_predicate = 1, RULE_a = 2, RULE_o = 3, RULE_n = 4, 
		RULE_c = 5, RULE_id = 6, RULE_op = 7;
	public static final String[] ruleNames = {
		"prog", "predicate", "a", "o", "n", "c", "id", "op"
	};

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitProg(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitProg(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16); predicate();
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

	public static class PredicateContext extends ParserRuleContext {
		public OContext o() {
			return getRuleContext(OContext.class,0);
		}
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public NContext n() {
			return getRuleContext(NContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPredicate(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPredicate(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_predicate);
		try {
			setState(23);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(18); a();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(19); o();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(20); n();
				}
				break;
			case INT:
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(21); c();
				}
				break;
			case EOF:
			case T__6:
			case T__2:
				enterOuterAlt(_localctx, 5);
				{
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

	public static class AContext extends ParserRuleContext {
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public AContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitA(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitA(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final AContext a() throws RecognitionException {
		AContext _localctx = new AContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_a);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); match(T__8);
			setState(26); predicate();
			setState(27); match(T__2);
			setState(28); predicate();
			setState(29); match(T__6);
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

	public static class OContext extends ParserRuleContext {
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public OContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_o; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterO(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitO(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitO(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final OContext o() throws RecognitionException {
		OContext _localctx = new OContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_o);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); match(T__4);
			setState(32); predicate();
			setState(33); match(T__2);
			setState(34); predicate();
			setState(35); match(T__6);
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

	public static class NContext extends ParserRuleContext {
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public NContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_n; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitN(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitN(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final NContext n() throws RecognitionException {
		NContext _localctx = new NContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_n);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); match(T__7);
			setState(38); c();
			setState(39); match(T__6);
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

	public static class CContext extends ParserRuleContext {
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public CContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitC(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitC(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final CContext c() throws RecognitionException {
		CContext _localctx = new CContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_c);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); id();
			setState(42); op();
			setState(43); id();
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(ExprParser.TEXT, 0); }
		public TerminalNode INT() { return getToken(ExprParser.INT, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitId(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitId(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==TEXT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitOp(this);
		}
//		@Override
//		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
//			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitOp(this);
//			else return visitor.visitChildren(this);
//		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__9) | (1L << T__5) | (1L << T__3) | (1L << T__1) | (1L << T__0))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17\64\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\32\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f"+
		"\16\20\2\4\3\2\16\17\6\2\3\4\b\b\n\n\f\r/\2\22\3\2\2\2\4\31\3\2\2\2\6"+
		"\33\3\2\2\2\b!\3\2\2\2\n\'\3\2\2\2\f+\3\2\2\2\16/\3\2\2\2\20\61\3\2\2"+
		"\2\22\23\5\4\3\2\23\3\3\2\2\2\24\32\5\6\4\2\25\32\5\b\5\2\26\32\5\n\6"+
		"\2\27\32\5\f\7\2\30\32\3\2\2\2\31\24\3\2\2\2\31\25\3\2\2\2\31\26\3\2\2"+
		"\2\31\27\3\2\2\2\31\30\3\2\2\2\32\5\3\2\2\2\33\34\7\5\2\2\34\35\5\4\3"+
		"\2\35\36\7\13\2\2\36\37\5\4\3\2\37 \7\7\2\2 \7\3\2\2\2!\"\7\t\2\2\"#\5"+
		"\4\3\2#$\7\13\2\2$%\5\4\3\2%&\7\7\2\2&\t\3\2\2\2\'(\7\6\2\2()\5\f\7\2"+
		")*\7\7\2\2*\13\3\2\2\2+,\5\16\b\2,-\5\20\t\2-.\5\16\b\2.\r\3\2\2\2/\60"+
		"\t\2\2\2\60\17\3\2\2\2\61\62\t\3\2\2\62\21\3\2\2\2\3\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}