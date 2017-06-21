package edu.rit.dao.iapi.relational;

/**
 * The Class BinaryOperation.
 */
public class BinaryOperation extends RelationAlgebraOperation {

	/** The left source. */
	private RelationAlgebraOperation leftSource;

	/** The right source. */
	private RelationAlgebraOperation rightSource;

	/**
	 * Instantiates a new binary operation.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 */
	public BinaryOperation(RelationAlgebraOperation leftSource, RelationAlgebraOperation rightSource) {
		super(null);
		this.leftSource = leftSource;
		this.rightSource = rightSource;
	}

	/**
	 * @return the leftSource
	 */
	public RelationAlgebraOperation getLeftSource() {
		return leftSource;
	}

	/**
	 * @param leftSource the leftSource to set
	 */
	public void setLeftSource(RelationAlgebraOperation leftSource) {
		this.leftSource = leftSource;
	}

	/**
	 * @return the rightSource
	 */
	public RelationAlgebraOperation getRightSource() {
		return rightSource;
	}

	/**
	 * @param rightSource the rightSource to set
	 */
	public void setRightSource(RelationAlgebraOperation rightSource) {
		this.rightSource = rightSource;
	}
}
