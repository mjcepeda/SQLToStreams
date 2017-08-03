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

	/** The qualifiers. */
	private List<Qualifier> qualifiers;

	/** The predicate. */
	private String predicate;

	/**
	 * Instantiates a new select.
	 *
	 * @param name
	 *            the name
	 * @param qualifiers
	 *            the qualifiers
	 * @param predicate
	 *            the predicate
	 * @param source
	 *            the source
	 */
	public Select(String name, List<Qualifier> qualifiers, String predicate, RelationalAlgebra source) {
		super(name, source);
		this.qualifiers = qualifiers;
		this.predicate = predicate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.UnaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getSource().getReturnVar()).append(".get().filter(bean -> ");

		int counter = 0;
		for (Qualifier q : qualifiers) {

			String attName = q.getColumnData().getAlias() != null ? q.getColumnData().getAlias()
					: q.getColumnData().getName();
			StringBuilder clause = new StringBuilder();
			if (q.getParameterValue() != null) {
				// ObjectUtils is safe null comparison
				clause.append("org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get(\"").append(attName)
						.append("\"),");
				if (q.getParameterValue() instanceof ColumnDescriptor) {
					String value = ((ColumnDescriptor) q.getParameterValue()).getAlias() != null
							? ((ColumnDescriptor) q.getParameterValue()).getAlias()
							: ((ColumnDescriptor) q.getParameterValue()).getName();
					// comparing two attributes
					clause.append("(Comparable)bean.get(\"").append(value).append("\"))");
				} else {
					// comparing attribute against a value
					clause.append("(Comparable)").append(q.getParameterValue()).append(")");
				}
				clause.append(getOperatorForCompareTo(q.getOperator()));
			} else {
				// comparing whether the attribute is null
				clause.append("bean.get(\"").append(attName).append("\")").append(getOperator(q.getOperator()))
						.append("null");
			}

			predicate = predicate.replace(String.valueOf(counter), clause.toString());
			counter++;
		}
		streamCode.append(predicate.toString());
		streamCode.append(")");

		return streamCode.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.RelationalAlgebra#toString()
	 */
	public String toString() {
		return "Select\nbeanName: " + getReturnVar() + "\n\tcolumns: " + qualifiers + "\nsource: " + getSource();
	}

	/**
	 * Gets the operator for compare to.
	 *
	 * @param operator
	 *            the operator
	 * @return the operator for compare to
	 */
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

	/**
	 * Gets the operator.
	 *
	 * @param operator
	 *            the operator
	 * @return the operator
	 */
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
