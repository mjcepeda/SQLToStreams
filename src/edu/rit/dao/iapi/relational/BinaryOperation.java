package edu.rit.dao.iapi.relational;

/**
 * The Class BinaryOperation.
 */
public abstract class BinaryOperation extends RelationalAlgebra{

	/** The left source. */
	//private String leftBeanName;

	/** The right source. */
	//private String rightBeanName;

	/** The left source. */
	private RelationalAlgebra leftSource;
	
	/** The right source. */
	private RelationalAlgebra rightSource;
	
	/**
	 * Instantiates a new binary operation.
	 *
	 * @param leftBeanName the left bean name
	 * @param rightBeanName the right bean name
	 * @param leftSource            the left source
	 * @param rightSource            the right source
	 */
	public BinaryOperation(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(name);
		this.leftSource = leftSource;
		this.rightSource = rightSource;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public abstract String perform();

	/**
	 * Gets the left source.
	 *
	 * @return the leftSource
	 */
	public RelationalAlgebra getLeftSource() {
		return leftSource;
	}

	/**
	 * Sets the left source.
	 *
	 * @param leftSource the leftSource to set
	 */
	public void setLeftSource(RelationalAlgebra leftSource) {
		this.leftSource = leftSource;
	}

	/**
	 * Gets the right source.
	 *
	 * @return the rightSource
	 */
	public RelationalAlgebra getRightSource() {
		return rightSource;
	}

	/**
	 * Sets the right source.
	 *
	 * @param rightSource the rightSource to set
	 */
	public void setRightSource(RelationalAlgebra rightSource) {
		this.rightSource = rightSource;
	}
	
}
