package edu.rit.dao.iapi.relational;

/**
 * The Class BinaryOperation.
 */
public abstract class BinaryOperation{

	/** The left source. */
	private String leftSource;

	/** The right source. */
	private String rightSource;

	/**
	 * Instantiates a new binary operation.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 */
	public BinaryOperation(String leftSource, String rightSource) {
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
	 * @return the leftSource
	 */
	public String getLeftSource() {
		return leftSource;
	}

	/**
	 * @param leftSource the leftSource to set
	 */
	public void setLeftSource(String leftSource) {
		this.leftSource = leftSource;
	}

	/**
	 * @return the rightSource
	 */
	public String getRightSource() {
		return rightSource;
	}

	/**
	 * @param rightSource the rightSource to set
	 */
	public void setRightSource(String rightSource) {
		this.rightSource = rightSource;
	}
}
