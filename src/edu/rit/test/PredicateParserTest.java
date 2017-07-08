package edu.rit.test;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import edu.rit.dao.impl.parser.ExprLexer;
import edu.rit.dao.impl.parser.ExprParser;

public class PredicateParserTest {

	@Test
	public void test() {
		// String predicate = "AND(AND(#2="M",#7>50),#3="a")";
		// OR(#2="M",AND(#7>50,#3="a"))
		// AND(#2=\"M\",#7>50)
		// "AND(#10=#4,OR(#9=3,AND(#3=\"Pepe\",NOT(#7>50))))"
		ANTLRInputStream in = new ANTLRInputStream("AND(AND(#2=\"M\",#7>50),#3=\"a\")");
		ExprLexer lexer = new ExprLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExprParser parser = new ExprParser(tokens);

//		PredicateListener listener = new PredicateListener();
//		
//		ParserRuleContext context = parser.predicate();
//		ParseTreeWalker walker = new ParseTreeWalker();
//		walker.walk(listener, context);
//		PredicateVisitorImpl exprVisitor = new PredicateVisitorImpl();
//		exprVisitor.visit(listener, context);
//		PredicateListener listener = new PredicateListener();
//	    parser.predicate().enterRule(listener);

	}

}
