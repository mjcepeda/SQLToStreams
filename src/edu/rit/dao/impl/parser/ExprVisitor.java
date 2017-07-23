// Generated from Expr.g4 by ANTLR 4.3
package edu.rit.dao.impl.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(@NotNull ExprParser.PredicateContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA(@NotNull ExprParser.AContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(@NotNull ExprParser.OpContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC(@NotNull ExprParser.CContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#err}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErr(@NotNull ExprParser.ErrContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(@NotNull ExprParser.IdContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull ExprParser.ProgContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#n}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN(@NotNull ExprParser.NContext ctx);

	/**
	 * Visit a parse tree produced by {@link ExprParser#o}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitO(@NotNull ExprParser.OContext ctx);
}