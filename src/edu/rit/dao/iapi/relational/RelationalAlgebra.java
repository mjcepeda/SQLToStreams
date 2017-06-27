package edu.rit.dao.iapi.relational;

public abstract class RelationalAlgebra {

	private String returnVar;

	public RelationalAlgebra(String returnVar) {
		this.returnVar = returnVar;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();

	/**
	 * @return the returnVar
	 */
	public String getReturnVar() {
		return returnVar;
	}
}
