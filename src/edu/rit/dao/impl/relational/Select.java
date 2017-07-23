package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;

/**
 * The Class Select.
 */
public class Select extends UnaryOperation {

	private List<List<Qualifier>> predicate;

	/**
	 * Instantiates a new select.
	 *
	 * @param name
	 *            the name
	 * @param attNames
	 *            the att names
	 */
	public Select(String name, List<List<Qualifier>> predicate, RelationalAlgebra source) {
		super(name, source);
		this.predicate = predicate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.UnaryOperation#perform()
	 */
	public String perform() {
		// Use Predicate class??
		// TODO Method in progress
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getSource().getReturnVar()).append(".get()");

		// TODO MJCG Think about how to store the boolean expression and, or
		// right now only support and expressions
		for (List<Qualifier> l : predicate) {
			streamCode.append(".filter(bean -> ");
			for (Qualifier q : l) {
				String attName = q.getColumnData().getAlias() != null ? q.getColumnData().getAlias(): q.getColumnData().getName(); 
				// if it is not the first elements, include the and operator
				if (!q.equals(l.stream().findFirst().get())) {
					streamCode.append(" || ");
				}
				// TODO MJCG Include possible null in ParameterValue INDEX(0,-1)
				StringBuilder predicate = new StringBuilder();
				if (q.getParameterValue() != null) {
					predicate.append("(bean.get(\"").append(attName).append("\") != null ? ");
					predicate.append("((Comparable)bean.get(\"").append(attName).append("\")")
							.append(").compareTo(");
					if (q.getParameterValue() instanceof ColumnDescriptor) {
						// comparing two attributes
						predicate.append("bean.get(\"").append(((ColumnDescriptor) q.getParameterValue()).getName())
								.append("\"))");
					} else {
						// comparing attribute against a value
						predicate.append(q.getParameterValue()).append(")");
					}
					predicate.append(getOperatorForCompareTo(q.getOperator()));
					predicate.append(" : false)");
				} else {
					predicate.append("bean.get(\"").append(attName).append("\")").append(getOperator(q.getOperator())).append("null");
				}
				// checking negation expression
				if (q.getNegateOperation() != null && q.getNegateOperation().equals(Boolean.TRUE)) {
					streamCode.append("!(").append(predicate).append(")");
				} else {
					streamCode.append(predicate);
				}
			}
			streamCode.append(")");
		}
		
		return streamCode.toString();
	}

	public String toString() {
		return "Select\nbeanName: " + getReturnVar() + "\n\tcolumns: " + predicate + "\nsource: " + getSource();
	}

	private String getOperatorForCompareTo(int operator) {
		String operatorCode = null;
		switch (operator) {
		case Operator.EQUALS:
			operatorCode = "==0";
			break;
		case Operator.DISTINCT:
			operatorCode = "!=0";
			break;
		case Operator.GRE:
			operatorCode = ">0";
			break;
		case Operator.GREQ:
			operatorCode = ">=0";
			break;
		case Operator.LESS:
			operatorCode = "<0";
			break;
		case Operator.LEQ:
			operatorCode = "<=0";
			break;
		}
		return operatorCode;
	}
	
	private String getOperator(int operator) {
		String operatorCode = null;
		switch (operator) {
		case Operator.EQUALS:
			operatorCode = "==";
			break;
		case Operator.DISTINCT:
			operatorCode = "!=";
			break;
		case Operator.GRE:
			operatorCode = ">";
			break;
		case Operator.GREQ:
			operatorCode = ">=";
			break;
		case Operator.LESS:
			operatorCode = "<";
			break;
		case Operator.LEQ:
			operatorCode = "<=";
			break;
		}
		return operatorCode;
	}


}
