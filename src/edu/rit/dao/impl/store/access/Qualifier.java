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
}
