package com.yinliang.C;

// Generated from CMinus.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CMinusParser}.
 */
public interface CMinusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CMinusParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull CMinusParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull CMinusParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#forStat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(@NotNull CMinusParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#forStat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(@NotNull CMinusParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull CMinusParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull CMinusParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(@NotNull CMinusParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(@NotNull CMinusParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(@NotNull CMinusParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(@NotNull CMinusParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#aexpr}.
	 * @param ctx the parse tree
	 */
	void enterAexpr(@NotNull CMinusParser.AexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#aexpr}.
	 * @param ctx the parse tree
	 */
	void exitAexpr(@NotNull CMinusParser.AexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull CMinusParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull CMinusParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull CMinusParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull CMinusParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull CMinusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull CMinusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull CMinusParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull CMinusParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull CMinusParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull CMinusParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void enterCondExpr(@NotNull CMinusParser.CondExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void exitCondExpr(@NotNull CMinusParser.CondExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull CMinusParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull CMinusParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(@NotNull CMinusParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(@NotNull CMinusParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull CMinusParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull CMinusParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMinusParser#mexpr}.
	 * @param ctx the parse tree
	 */
	void enterMexpr(@NotNull CMinusParser.MexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMinusParser#mexpr}.
	 * @param ctx the parse tree
	 */
	void exitMexpr(@NotNull CMinusParser.MexprContext ctx);
}