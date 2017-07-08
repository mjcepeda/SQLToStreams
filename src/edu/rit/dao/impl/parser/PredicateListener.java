package edu.rit.dao.impl.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;

public class PredicateListener extends ExprBaseListener {

	private List<Qualifier> qualifiers;
	private Map<Integer, String> attributesOrder;

	private Integer op;
	// objs size is 2
	private List<Object> objs;

	public PredicateListener(Map<Integer,String>attributesOrder)
	{
		qualifiers = new ArrayList<Qualifier>();
		objs = new ArrayList<>();
		this.attributesOrder = attributesOrder;
	}

	@Override
	public void exitC(@NotNull ExprParser.CContext ctx) {
		Qualifier q = new Qualifier();
		q.setColumnData((ColumnDescriptor)objs.get(0));
		q.setOperator(op);
		q.setParameterValue(objs.get(1));
		qualifiers.add(q);
		objs.clear();
		op = null;
	}

	@Override
	public void enterId(@NotNull ExprParser.IdContext ctx) {
		String text = ctx.getText();
		// index of the column
		if (text.startsWith("#")) {
			text = text.replaceAll("#", "");
			//TODO MJCG Add the type of the data
			ColumnDescriptor column = new ColumnDescriptor();
			column.setName(attributesOrder.get(Integer.valueOf(text)));
			objs.add(column);
		} else {
			objs.add(ctx.getText());
		}
	}

	@Override
	public void enterOp(@NotNull ExprParser.OpContext ctx) {
		op = Operator.getOperator(ctx.getText());
	}

	/**
	 * @return the qualifiers
	 */
	public List<Qualifier> getQualifiers() {
		return qualifiers;
	}
}
