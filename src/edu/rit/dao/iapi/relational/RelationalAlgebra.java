package edu.rit.dao.iapi.relational;

public abstract class RelationalAlgebra {

	private String returnVar;
	
	//TODO MJCG Create an attributeOrder i.e [name, lastName, age, gender]

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
}
