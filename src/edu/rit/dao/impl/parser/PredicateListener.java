package edu.rit.dao.impl.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import edu.rit.dao.impl.parser.ExprParser.AContext;
import edu.rit.dao.impl.parser.ExprParser.ErrContext;
import edu.rit.dao.impl.parser.ExprParser.NContext;
import edu.rit.dao.impl.parser.ExprParser.OContext;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;

public class PredicateListener extends ExprBaseListener {

	private static final String INDEX = "INDEX(0,-1)";
	private List<Qualifier> qualifiers;
	private Map<Integer, ColumnDescriptor> attributesOrder;
	private List<List<Qualifier>> predicate;

	private Integer op;
	private boolean negate;
	private boolean and = false;
	private boolean or = false;
	// objs size is 2
	private List<Object> objs;

	// TODO MJCG Include is null in the grammar
	// is null ISERR(#6)
	// =null #6=INDEX(0,-1)
	public PredicateListener(Map<Integer, ColumnDescriptor> attributesOrder) {
		predicate = new ArrayList<>();
		qualifiers = new ArrayList<Qualifier>();
		objs = new ArrayList<>();
		this.attributesOrder = attributesOrder;
		and = false;
	}

	@Override
	public void enterA(AContext ctx) {
		// qualifiers = new ArrayList<>();
		and = true;
	}

	@Override
	public void enterO(OContext ctx) {
		or = true;
	}

	@Override
	public void enterN(NContext ctx) {
		negate = true;
	}

	@Override
	public void enterErr(ErrContext ctx) {
		String text = ctx.getText();
		text = text.replaceAll("ISERR\\(#", "");
		text = text.replaceAll("\\)", "");
		objs.add(attributesOrder.get(Integer.valueOf(text)));
		objs.add(null);

	}

	@Override
	public void enterOp(@NotNull ExprParser.OpContext ctx) {
		op = Operator.getOperator(ctx.getText());
	}

	@Override
	public void enterId(@NotNull ExprParser.IdContext ctx) {
		String text = ctx.getText();
		if (text.startsWith("#")) {
			// index of the column
			text = text.replaceAll("#", "");
			objs.add(attributesOrder.get(Integer.valueOf(text)));
		} else if (text.equals(INDEX)) {
			objs.add(null);
		} else {
			objs.add(ctx.getText());
		}
	}

	@Override
	public void exitA(@NotNull ExprParser.AContext ctx) {
		and = false;
	}

	@Override
	public void exitO(@NotNull ExprParser.OContext ctx) {
		predicate.add(qualifiers);
		or = false;
	}

	@Override
	public void exitC(@NotNull ExprParser.CContext ctx) {
		Qualifier q = new Qualifier();
		if (objs.size() > 1) {
			q.setColumnData((ColumnDescriptor) objs.get(0));
			// in predicate like this salary is null there is no operation
			if (op != null) {
				q.setOperator(op);
			}
			q.setParameterValue(objs.get(1));
			// if and expression, push the qualifiers into predicate
			// if (and && !or) {
			// negate = !negate;
			// //predicate.add(qualifiers);
			// //qualifiers = new ArrayList<>();
			// }
			q.setNegateOperation(negate);
			qualifiers.add(q);
		}
		objs.clear();
		op = null;
		negate = false;
	}

	/**
	 * @return the qualifiers
	 */
	public List<Qualifier> getQualifiers() {
		return qualifiers;
	}

	/**
	 * @return the predicate
	 */
	public List<List<Qualifier>> getPredicate() {
		predicate.add(qualifiers);
		return predicate;
	}

}
