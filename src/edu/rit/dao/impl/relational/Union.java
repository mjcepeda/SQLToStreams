package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class Union.
 */
public class Union extends BinaryOperation {

	/**
	 * Instantiates a new union.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 */
	public Union(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(name, leftSource, rightSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append("Stream.concat(");
		streamCode.append(getLeftSource().getReturnVar() + ".stream(), ");
		streamCode.append(getRightSource().getReturnVar() + ".stream())");
		return streamCode.toString();
	}

	public String toString() {
		return "Union\nbeanName: " + getReturnVar() + "\nleftSource: " + getLeftSource()
				+ "\nrightSource: " + getRightSource();
	}
}
