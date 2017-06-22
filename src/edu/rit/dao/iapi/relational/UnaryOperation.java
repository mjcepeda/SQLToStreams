package edu.rit.dao.iapi.relational;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationAlgebraOperation.
 */
public abstract class UnaryOperation {

	/** The table name. */
	protected String beanName;
	
	/**
	 * Instantiates a new unary operation.
	 *
	 * @param name the name
	 */
	public UnaryOperation(String name) {
		this.beanName = name;
	}
	
	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();
}
