package edu.rit.dao.iapi.relational;

import java.util.Map;

import edu.rit.dao.impl.store.access.ColumnDescriptor;

public abstract class RelationalAlgebra {

	private String returnVar;
	
	private Map<Integer, ColumnDescriptor> attOrder;
	
	public RelationalAlgebra(String returnVar) {
		this.returnVar = returnVar;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();
	
	public abstract String toString();

	/**
	 * @return the returnVar
	 */
	public String getReturnVar() {
		return returnVar;
	}

	/**
	 * @return the attOrder
	 */
	public Map<Integer, ColumnDescriptor> getAttOrder() {
		return attOrder;
	}

	/**
	 * @param attOrder the attOrder to set
	 */
	public void setAttOrder(Map<Integer, ColumnDescriptor> attOrder) {
		this.attOrder = attOrder;
	}
}
