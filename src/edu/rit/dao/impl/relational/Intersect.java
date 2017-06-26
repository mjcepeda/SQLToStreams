package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class Intersect.
 */
public class Intersect extends BinaryOperation{

	/**
	 * Instantiates a new intersect.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Intersect(String leftName, String rightName, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(leftName, rightName, leftSource, rightSource);
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(getLeftBeanName() + ".stream().filter(");
		streamCode.append(getRightBeanName() + "::contains)");
		return streamCode.toString();
	}

}
