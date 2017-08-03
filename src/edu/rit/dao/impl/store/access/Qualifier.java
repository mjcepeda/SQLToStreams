package edu.rit.dao.impl.store.access;

/**
 * The Class Qualifier.
 */
public class Qualifier {

	/** The column data. */
	private ColumnDescriptor columnData;
	
	/** The operator. */
	private int operator;
	
	/** The parameter value. */
	private Object parameterValue;
	
	/** The negate operation. */
	private Boolean negateOperation;

	/**
	 * Gets the column data.
	 *
	 * @return the column data
	 */
	public ColumnDescriptor getColumnData() {
		return columnData;
	}
	
	/**
	 * Sets the column data.
	 *
	 * @param columnData the new column data
	 */
	public void setColumnData(ColumnDescriptor columnData) {
		this.columnData = columnData;
	}
	
	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	public int getOperator() {
		return operator;
	}
	
	/**
	 * Sets the operator.
	 *
	 * @param operator the new operator
	 */
	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	/**
	 * Gets the parameter value.
	 *
	 * @return the parameter value
	 */
	public Object getParameterValue() {
		return parameterValue;
	}
	
	/**
	 * Sets the parameter value.
	 *
	 * @param parameterValue the new parameter value
	 */
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * Gets the negate operation.
	 *
	 * @return the negateOperation
	 */
	public Boolean getNegateOperation() {
		return negateOperation;
	}

	/**
	 * Sets the negate operation.
	 *
	 * @param negateOperation the negateOperation to set
	 */
	public void setNegateOperation(Boolean negateOperation) {
		this.negateOperation = negateOperation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		return columnData.getName() + Operator.getOperator(operator) + parameterValue;
	}
}
