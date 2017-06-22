package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;

/**
 * The Class Union.
 */
public class Union extends BinaryOperation{

	/**
	 * Instantiates a new union.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Union(String leftSource, String rightSource) {
		super(leftSource, rightSource);
	}
	
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("Stream.concat(");
		streamCode.append(getLeftSource() + ".stream(), ");
		streamCode.append(getRightSource() + ".stream()).distinct().collect(Collectors.toSet());");
		return streamCode.toString();
	}

}
