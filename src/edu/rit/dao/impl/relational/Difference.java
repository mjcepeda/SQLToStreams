package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;

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
	public Difference(String leftSource, String rightSource) {
		super(leftSource, rightSource);
	}
	
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(getLeftSource() + ".stream().filter(bean -> !");
		streamCode.append(getRightSource() + ".contains(bean)).collect(Collectors.toSet());");
		return streamCode.toString();
	}

}
