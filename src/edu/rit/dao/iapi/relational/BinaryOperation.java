package edu.rit.dao.iapi.relational;

/**
 * The Class BinaryOperation.
 */
public abstract class BinaryOperation extends RelationalAlgebra{

	/** The left source. */
	private String leftBeanName;

	/** The right source. */
	private String rightBeanName;

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
	public BinaryOperation(String leftBeanName, String rightBeanName, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		this.leftBeanName = leftBeanName;
		this.rightBeanName = rightBeanName;
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
	 * Gets the left bean name.
	 *
	 * @return the leftBeanName
	 */
	public String getLeftBeanName() {
		return leftBeanName;
	}

	/**
	 * Sets the left bean name.
	 *
	 * @param leftBeanName the leftBeanName to set
	 */
	public void setLeftBeanName(String leftBeanName) {
		this.leftBeanName = leftBeanName;
	}

	/**
	 * Gets the right bean name.
	 *
	 * @return the rightBeanName
	 */
	public String getRightBeanName() {
		return rightBeanName;
	}

	/**
	 * Sets the right bean name.
	 *
	 * @param rightBeanName the rightBeanName to set
	 */
	public void setRightBeanName(String rightBeanName) {
		this.rightBeanName = rightBeanName;
	}

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
