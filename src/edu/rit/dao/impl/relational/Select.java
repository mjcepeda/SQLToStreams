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

	/** The att names. */
	// TODO - MJGC Do I need this?
	private List<String> attNames;

	private List<Qualifier> qualifiers;

	/**
	 * Instantiates a new select.
	 *
	 * @param name
	 *            the name
	 * @param attNames
	 *            the att names
	 */
	public Select(String name, List<Qualifier> qualifiers, RelationalAlgebra source) {
		super(name, source);
		this.qualifiers = qualifiers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.UnaryOperation#perform()
	 */
	public String perform() {
		// Use Predicate class??
		// TODO Method in progress
		// TODO MJCG Right now, we use the value specified in the SQL statement
		// (i.e "Maria") in the filter operation,
		// another idea would be create an input parameter as the signature
		// method (i.e name)
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getSource().getReturnVar()).append(".get().filter(bean -> ");

		// TODO MJCG Think about how to store the boolean expression and, or
		// right now only support and expressions
		for (Qualifier q : qualifiers) {
			// if it is not the first elements, include the and operator
			if (!q.equals(qualifiers.stream().findFirst().get())) {
				streamCode.append(" && ");
			}
			// TODO MJCG Think if I need to use here BeanUtils
			StringBuilder predicate = new StringBuilder();
			predicate.append("((Comparable)bean.get(\"").append(q.getColumnData().getName()).append("\")").append(").compareTo(");
			if (q.getParameterValue() instanceof ColumnDescriptor) {
				//comparing two attributes
				predicate.append("bean.get(\"").append(((ColumnDescriptor)q.getParameterValue()).getName()).append("\"))");
			} else {
				//comparing attribute against a value
				predicate.append(q.getParameterValue()).append(")");
			}			
			predicate.append(getOperator(q.getOperator()));
			// checking negation expression
			if (q.getNegateOperation() != null && q.getNegateOperation().equals(Boolean.TRUE)) {
				streamCode.append("!(").append(predicate).append(")");
			} else {
				streamCode.append(predicate);
			}
		}
		streamCode.append(")");
		return streamCode.toString();
	}

	public String toString() {
		return "Select\nbeanName: " + getReturnVar() + "\n\tcolumns: " + qualifiers + "\nsource: " + getSource();
	}

	private String getOperator(int operator) {
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

	/**
	 * Gets the att names.
	 *
	 * @return the attNames
	 */
	public List<String> getAttNames() {
		return attNames;
	}

	/**
	 * Sets the att names.
	 *
	 * @param attNames
	 *            the attNames to set
	 */
	public void setAttNames(List<String> attNames) {
		this.attNames = attNames;
	}
}
