package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class Difference.
 */
public class Difference extends BinaryOperation{

	/**
	 * Instantiates a new difference.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Difference(String leftName, String rightName, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(leftName, rightName, leftSource, rightSource);
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(getLeftBeanName() + ".stream().filter(bean -> !");
		streamCode.append(getRightBeanName() + ".contains(bean))");
		return streamCode.toString();
	}

}
