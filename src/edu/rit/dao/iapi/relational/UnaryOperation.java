package edu.rit.dao.iapi.relational;

/**
 * The Class RelationAlgebraOperation.
 */
public abstract class UnaryOperation extends RelationalAlgebra{

	/** The table name. */
	//private String beanName;
	
	/** The source. */
	private RelationalAlgebra source;
	
	/**
	 * Instantiates a new unary operation.
	 *
	 * @param name the name
	 */
	public UnaryOperation(String name, RelationalAlgebra source) {
		super(name);
		this.source = source;
	}
	
	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();

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
