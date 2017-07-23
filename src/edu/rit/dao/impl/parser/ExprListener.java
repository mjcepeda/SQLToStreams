// Generated from Expr.g4 by ANTLR 4.3
package edu.rit.dao.impl.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull ExprParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull ExprParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#a}.
	 * @param ctx the parse tree
	 */
	void enterA(@NotNull ExprParser.AContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#a}.
	 * @param ctx the parse tree
	 */
	void exitA(@NotNull ExprParser.AContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(@NotNull ExprParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(@NotNull ExprParser.OpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#c}.
	 * @param ctx the parse tree
	 */
	void enterC(@NotNull ExprParser.CContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#c}.
	 * @param ctx the parse tree
	 */
	void exitC(@NotNull ExprParser.CContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#err}.
	 * @param ctx the parse tree
	 */
	void enterErr(@NotNull ExprParser.ErrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#err}.
	 * @param ctx the parse tree
	 */
	void exitErr(@NotNull ExprParser.ErrContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull ExprParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull ExprParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull ExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull ExprParser.ProgContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#n}.
	 * @param ctx the parse tree
	 */
	void enterN(@NotNull ExprParser.NContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#n}.
	 * @param ctx the parse tree
	 */
	void exitN(@NotNull ExprParser.NContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#o}.
	 * @param ctx the parse tree
	 */
	void enterO(@NotNull ExprParser.OContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#o}.
	 * @param ctx the parse tree
	 */
	void exitO(@NotNull ExprParser.OContext ctx);
}