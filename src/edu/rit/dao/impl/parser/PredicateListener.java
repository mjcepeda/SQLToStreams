package edu.rit.dao.impl.parser;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import edu.rit.dao.impl.parser.ExprParser.AContext;
import edu.rit.dao.impl.parser.ExprParser.CContext;
import edu.rit.dao.impl.parser.ExprParser.ClauseContext;
import edu.rit.dao.impl.parser.ExprParser.ErrContext;
import edu.rit.dao.impl.parser.ExprParser.IdContext;
import edu.rit.dao.impl.parser.ExprParser.NContext;
import edu.rit.dao.impl.parser.ExprParser.OContext;
import edu.rit.dao.impl.parser.ExprParser.OpContext;
import edu.rit.dao.impl.parser.ExprParser.PredicateContext;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;

/**
 * The listener interface for receiving predicate events. The class that is
 * interested in processing a predicate event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addPredicateListener<code> method. When the predicate event
 * occurs, that object's appropriate method is invoked.
 *
 * @see PredicateEvent
 */
// TODO MJCG Try this name = 'A%'
public class PredicateListener extends ExprBaseListener {

	/** The Constant INDEX. */
	private static final String INDEX = "INDEX(0,-1)";

	/** The qualifiers. */
	private List<Qualifier> qualifiers;

	/** The predicate. */
	private StringBuilder predicate;

	/** The attributes order. */
	private Map<Integer, ColumnDescriptor> attributesOrder;

	/** The op. */
	private Integer op;

	/** The objs. */
	// objs size is 2
	private List<Object> objs;

	/**
	 * The Enum Classes.
	 */
	public enum Classes {

		/** The Clause ctx. */
		ClauseCtx(ClauseContext.class),
		/** The C ctx. */
		CCtx(CContext.class),
		/** The Err ctx. */
		ErrCtx(ErrContext.class),
		/** The AND ctx. */
		ANDCtx(AContext.class),
		/** The OR ctx. */
		ORCtx(OContext.class),
		/** The NOT ctx. */
		NOTCtx(NContext.class),
		/** The Id ctx. */
		IdCtx(IdContext.class),
		/** The Op ctx. */
		OpCtx(OpContext.class),
		/** The unknwon. */
		UNKNWON(null);

		// need a wrapper class to avoid compilation problem
		/**
		 * The Class Holder.
		 */
		// with referring static enum field within an initializer
		private static class Holder {

			/** The Constant map. */
			public static final IdentityHashMap<Class<?>, Classes> map = new IdentityHashMap<>();
		}

		/**
		 * Instantiates a new classes.
		 *
		 * @param targetClass
		 *            the target class
		 */
		Classes(Class<?> targetClass) {
			Holder.map.put(targetClass, this);
		}

		/**
		 * From class.
		 *
		 * @param cls
		 *            the cls
		 * @return the classes
		 */
		public static Classes fromClass(Class<?> cls) {
			Classes c = Holder.map.get(cls);
			return c != null ? c : UNKNWON;
		}
	}

	/**
	 * Instantiates a new predicate listener.
	 *
	 * @param attributesOrder
	 *            the attributes order
	 */
	public PredicateListener(Map<Integer, ColumnDescriptor> attributesOrder) {
		qualifiers = new ArrayList<Qualifier>();
		objs = new ArrayList<>();
		this.attributesOrder = attributesOrder;
		predicate = new StringBuilder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rit.dao.impl.parser.ExprBaseListener#enterPredicate(edu.rit.dao.impl.
	 * parser.ExprParser.PredicateContext)
	 */
	public void enterPredicate(PredicateContext ctx) {
		ParseTree child = ctx.getChild(0);
		this.predicate.append(parsePredicate(child.getChild(0)));
	}

	/**
	 * Parses the predicate.
	 *
	 * @param tree
	 *            the tree
	 * @return the string
	 */
	public String parsePredicate(ParseTree tree) {
		Class<?> c = tree.getClass();
		String left = null;
		String right = null;
		StringBuffer text = new StringBuffer();
		switch (Classes.fromClass(c)) {
		case ANDCtx:
			left = parsePredicate(tree.getChild(1).getChild(0));
			right = parsePredicate(tree.getChild(3).getChild(0));
			text.append("(").append(left).append(" && ").append(right).append(")");
			break;
		case ORCtx:
			left = parsePredicate(tree.getChild(1).getChild(0));
			right = parsePredicate(tree.getChild(3).getChild(0));
			text.append("(").append(left).append(" || ").append(right).append(")");
			break;
		case NOTCtx:
			left = parsePredicate(tree.getChild(1).getChild(0));
			text.append("!(").append(left).append(")");
			break;
		case CCtx:
			if (tree.getChild(0) instanceof IdContext) {
				parsePredicate(tree.getChild(0));
				parsePredicate(tree.getChild(1));
				parsePredicate(tree.getChild(2));
			} else {
				// ErrCtx
				parsePredicate(tree.getChild(0));
			}
			Qualifier q = new Qualifier();
			if (objs.size() > 1) {
				q.setColumnData((ColumnDescriptor) objs.get(0));
				// in predicate like this salary is null there is no operation
				if (op != null) {
					q.setOperator(op);
				}
				q.setParameterValue(objs.get(1));
				// q.setNegateOperation(negate);
				qualifiers.add(q);
				text.append(String.valueOf(qualifiers.size() - 1));
			}
			objs.clear();
			break;
		case ErrCtx:
			String txt = tree.getText();
			txt = txt.replaceAll("ISERR\\(#", "");
			txt = txt.replaceAll("\\)", "");
			objs.add(attributesOrder.get(Integer.valueOf(txt)));
			objs.add(null);
			break;
		case IdCtx:
			String aux = tree.getText();
			if (aux.startsWith("#")) {
				// index of the column
				aux = aux.replaceAll("#", "");
				objs.add(attributesOrder.get(Integer.valueOf(aux)));
			} else if (aux.equals(INDEX)) {
				objs.add(null);
			} else {
				objs.add(tree.getText());
			}
			break;
		case OpCtx:
			op = Operator.getOperator(tree.getText());
			break;
		}
		return text.toString();
	}

	/**
	 * Gets the qualifiers.
	 *
	 * @return the qualifiers
	 */
	public List<Qualifier> getQualifiers() {
		return qualifiers;
	}

	/**
	 * Gets the predicate.
	 *
	 * @return the predicate
	 */
	public StringBuilder getPredicate() {
		return predicate;
	}
}
