package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;

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
	public Intersect(String leftSource, String rightSource) {
		super(leftSource, rightSource);
	}
	
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(getLeftSource() + ".stream().filter(");
		streamCode.append(getRightSource() + "::contains).collect(Collectors.toSet());");
		return streamCode.toString();
	}

}
