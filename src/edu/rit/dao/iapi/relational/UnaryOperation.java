package edu.rit.dao.iapi.relational;

/**
 * The Class RelationAlgebraOperation.
 */
public abstract class UnaryOperation extends RelationalAlgebra{

	/** The table name. */
	private String beanName;
	
	/** The source. */
	private RelationalAlgebra source;
	
	/**
	 * Instantiates a new unary operation.
	 *
	 * @param name the name
	 */
	public UnaryOperation(String name, RelationalAlgebra source) {
		this.beanName = name;
		this.source = source;
	}
	
	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return the source
	 */
	public RelationalAlgebra getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(RelationalAlgebra source) {
		this.source = source;
	}
}
