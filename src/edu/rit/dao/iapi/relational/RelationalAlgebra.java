package edu.rit.dao.iapi.relational;

import java.util.Map;

import edu.rit.dao.impl.store.access.ColumnDescriptor;

/**
 * The Class RelationalAlgebra.
 */
public abstract class RelationalAlgebra {

	/** The return var. */
	private String returnVar;
	
	/** The att order. */
	private Map<Integer, ColumnDescriptor> attOrder;
	
	/**
	 * Instantiates a new relational algebra.
	 *
	 * @param returnVar the return var
	 */
	public RelationalAlgebra(String returnVar) {
		this.returnVar = returnVar;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * Gets the return var.
	 *
	 * @return the returnVar
	 */
	public String getReturnVar() {
		return returnVar;
	}

	/**
	 * Gets the att order.
	 *
	 * @return the attOrder
	 */
	public Map<Integer, ColumnDescriptor> getAttOrder() {
		return attOrder;
	}

	/**
	 * Sets the att order.
	 *
	 * @param attOrder the attOrder to set
	 */
	public void setAttOrder(Map<Integer, ColumnDescriptor> attOrder) {
		this.attOrder = attOrder;
	}
}
