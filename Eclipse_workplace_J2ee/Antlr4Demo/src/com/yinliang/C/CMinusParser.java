package com.yinliang.C;

// Generated from CMinus.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CMinusParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__15=1, T__14=2, T__13=3, T__12=4, T__11=5, T__10=6, T__9=7, T__8=8, 
		T__7=9, T__6=10, T__5=11, T__4=12, T__3=13, T__2=14, T__1=15, T__0=16, 
		ID=17, INT=18, WS=19;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "','", "'+'", "'*'", "'for'", "'('", "'if'", "'int'", 
		"'='", "'!='", "';'", "'{'", "'else'", "'=='", "'}'", "'char'", "ID", 
		"INT", "WS"
	};
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_variable = 2, RULE_type = 3, 
		RULE_function = 4, RULE_formalParameter = 5, RULE_block = 6, RULE_stat = 7, 
		RULE_ifStat = 8, RULE_forStat = 9, RULE_assignStat = 10, RULE_expr = 11, 
		RULE_condExpr = 12, RULE_aexpr = 13, RULE_mexpr = 14, RULE_atom = 15;
	public static final String[] ruleNames = {
		"program", "declaration", "variable", "type", "function", "formalParameter", 
		"block", "stat", "ifStat", "forStat", "assignStat", "expr", "condExpr", 
		"aexpr", "mexpr", "atom"
	};

	@Override
	public String getGrammarFileName() { return "CMinus.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CMinusParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); declaration();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__8 || _la==T__0 );
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
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(39);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(37); variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(38); function();
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CMinusParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); type();
			setState(42); match(ID);
			setState(43); match(T__5);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==T__0) ) {
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

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CMinusParser.ID, 0); }
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); type();
			setState(48); match(ID);
			setState(49); match(T__10);
			setState(58);
			_la = _input.LA(1);
			if (_la==T__8 || _la==T__0) {
				{
				setState(50); formalParameter();
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__14) {
					{
					{
					setState(51); match(T__14);
					setState(52); formalParameter();
					}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(60); match(T__15);
			setState(61); block();
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

	public static class FormalParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CMinusParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); type();
			setState(64); match(ID);
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

	public static class BlockContext extends ParserRuleContext {
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(T__4);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8 || _la==T__0) {
				{
				{
				setState(67); variable();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__10) | (1L << T__9) | (1L << T__5) | (1L << T__4) | (1L << ID) | (1L << INT))) != 0)) {
				{
				{
				setState(73); stat();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79); match(T__1);
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

	public static class StatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignStatContext assignStat() {
			return getRuleContext(AssignStatContext.class,0);
		}
		public IfStatContext ifStat() {
			return getRuleContext(IfStatContext.class,0);
		}
		public ForStatContext forStat() {
			return getRuleContext(ForStatContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stat);
		try {
			setState(91);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81); forStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82); ifStat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83); expr();
				setState(84); match(T__5);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(86); block();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(87); assignStat();
				setState(88); match(T__5);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(90); match(T__5);
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

	public static class IfStatContext extends ParserRuleContext {
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public IfStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitIfStat(this);
		}
	}

	public final IfStatContext ifStat() throws RecognitionException {
		IfStatContext _localctx = new IfStatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ifStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(T__9);
			setState(94); match(T__10);
			setState(95); expr();
			setState(96); match(T__15);
			setState(97); stat();
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(98); match(T__3);
				setState(99); stat();
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

	public static class ForStatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<AssignStatContext> assignStat() {
			return getRuleContexts(AssignStatContext.class);
		}
		public AssignStatContext assignStat(int i) {
			return getRuleContext(AssignStatContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterForStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitForStat(this);
		}
	}

	public final ForStatContext forStat() throws RecognitionException {
		ForStatContext _localctx = new ForStatContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_forStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(T__11);
			setState(103); match(T__10);
			setState(104); assignStat();
			setState(105); match(T__5);
			setState(106); expr();
			setState(107); match(T__5);
			setState(108); assignStat();
			setState(109); match(T__15);
			setState(110); block();
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

	public static class AssignStatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(CMinusParser.ID, 0); }
		public AssignStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitAssignStat(this);
		}
	}

	public final AssignStatContext assignStat() throws RecognitionException {
		AssignStatContext _localctx = new AssignStatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(ID);
			setState(113); match(T__7);
			setState(114); expr();
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
		public CondExprContext condExpr() {
			return getRuleContext(CondExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); condExpr();
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

	public static class CondExprContext extends ParserRuleContext {
		public List<AexprContext> aexpr() {
			return getRuleContexts(AexprContext.class);
		}
		public AexprContext aexpr(int i) {
			return getRuleContext(AexprContext.class,i);
		}
		public CondExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterCondExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitCondExpr(this);
		}
	}

	public final CondExprContext condExpr() throws RecognitionException {
		CondExprContext _localctx = new CondExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_condExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); aexpr();
			setState(121);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__2) {
				{
				setState(119);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(120); aexpr();
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

	public static class AexprContext extends ParserRuleContext {
		public List<MexprContext> mexpr() {
			return getRuleContexts(MexprContext.class);
		}
		public MexprContext mexpr(int i) {
			return getRuleContext(MexprContext.class,i);
		}
		public AexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterAexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitAexpr(this);
		}
	}

	public final AexprContext aexpr() throws RecognitionException {
		AexprContext _localctx = new AexprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_aexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); mexpr();
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(124); match(T__13);
				setState(125); mexpr();
				}
				}
				setState(130);
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

	public static class MexprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public MexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterMexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitMexpr(this);
		}
	}

	public final MexprContext mexpr() throws RecognitionException {
		MexprContext _localctx = new MexprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_mexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); atom();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(132); match(T__12);
				setState(133); atom();
				}
				}
				setState(138);
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

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CMinusParser.INT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(CMinusParser.ID, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CMinusListener ) ((CMinusListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_atom);
		try {
			setState(145);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(139); match(ID);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(140); match(INT);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(141); match(T__10);
				setState(142); expr();
				setState(143); match(T__15);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\u0096\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2"+
		"$\n\2\r\2\16\2%\3\3\3\3\5\3*\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\7\68\n\6\f\6\16\6;\13\6\5\6=\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\b\3\b\7\bG\n\b\f\b\16\bJ\13\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t^\n\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\ng\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16|\n\16\3\17\3\17\3\17\7\17\u0081"+
		"\n\17\f\17\16\17\u0084\13\17\3\20\3\20\3\20\7\20\u0089\n\20\f\20\16\20"+
		"\u008c\13\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0094\n\21\3\21\2\2\22"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\4\4\2\n\n\22\22\4\2\f\f\20"+
		"\20\u0096\2#\3\2\2\2\4)\3\2\2\2\6+\3\2\2\2\b/\3\2\2\2\n\61\3\2\2\2\fA"+
		"\3\2\2\2\16D\3\2\2\2\20]\3\2\2\2\22_\3\2\2\2\24h\3\2\2\2\26r\3\2\2\2\30"+
		"v\3\2\2\2\32x\3\2\2\2\34}\3\2\2\2\36\u0085\3\2\2\2 \u0093\3\2\2\2\"$\5"+
		"\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'*\5\6\4\2"+
		"(*\5\n\6\2)\'\3\2\2\2)(\3\2\2\2*\5\3\2\2\2+,\5\b\5\2,-\7\23\2\2-.\7\r"+
		"\2\2.\7\3\2\2\2/\60\t\2\2\2\60\t\3\2\2\2\61\62\5\b\5\2\62\63\7\23\2\2"+
		"\63<\7\b\2\2\649\5\f\7\2\65\66\7\4\2\2\668\5\f\7\2\67\65\3\2\2\28;\3\2"+
		"\2\29\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<\64\3\2\2\2<=\3\2\2\2="+
		">\3\2\2\2>?\7\3\2\2?@\5\16\b\2@\13\3\2\2\2AB\5\b\5\2BC\7\23\2\2C\r\3\2"+
		"\2\2DH\7\16\2\2EG\5\6\4\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IN\3"+
		"\2\2\2JH\3\2\2\2KM\5\20\t\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ"+
		"\3\2\2\2PN\3\2\2\2QR\7\21\2\2R\17\3\2\2\2S^\5\24\13\2T^\5\22\n\2UV\5\30"+
		"\r\2VW\7\r\2\2W^\3\2\2\2X^\5\16\b\2YZ\5\26\f\2Z[\7\r\2\2[^\3\2\2\2\\^"+
		"\7\r\2\2]S\3\2\2\2]T\3\2\2\2]U\3\2\2\2]X\3\2\2\2]Y\3\2\2\2]\\\3\2\2\2"+
		"^\21\3\2\2\2_`\7\t\2\2`a\7\b\2\2ab\5\30\r\2bc\7\3\2\2cf\5\20\t\2de\7\17"+
		"\2\2eg\5\20\t\2fd\3\2\2\2fg\3\2\2\2g\23\3\2\2\2hi\7\7\2\2ij\7\b\2\2jk"+
		"\5\26\f\2kl\7\r\2\2lm\5\30\r\2mn\7\r\2\2no\5\26\f\2op\7\3\2\2pq\5\16\b"+
		"\2q\25\3\2\2\2rs\7\23\2\2st\7\13\2\2tu\5\30\r\2u\27\3\2\2\2vw\5\32\16"+
		"\2w\31\3\2\2\2x{\5\34\17\2yz\t\3\2\2z|\5\34\17\2{y\3\2\2\2{|\3\2\2\2|"+
		"\33\3\2\2\2}\u0082\5\36\20\2~\177\7\5\2\2\177\u0081\5\36\20\2\u0080~\3"+
		"\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\35\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u008a\5 \21\2\u0086\u0087\7\6\2"+
		"\2\u0087\u0089\5 \21\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\37\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u0094\7\23\2\2\u008e\u0094\7\24\2\2\u008f\u0090\7\b\2\2\u0090\u0091\5"+
		"\30\r\2\u0091\u0092\7\3\2\2\u0092\u0094\3\2\2\2\u0093\u008d\3\2\2\2\u0093"+
		"\u008e\3\2\2\2\u0093\u008f\3\2\2\2\u0094!\3\2\2\2\16%)9<HN]f{\u0082\u008a"+
		"\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}