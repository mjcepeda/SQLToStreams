package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class Intersect.
 */
public class Intersect extends BinaryOperation {

	/**
	 * Instantiates a new intersect.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 */
	public Intersect(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
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
		streamCode.append(getLeftSource().getReturnVar() + ".stream().filter(");
		streamCode.append(getRightSource().getReturnVar() + "::contains)");
		return streamCode.toString();
	}

	// TODO MJCG
	public String toString() {
		return "";
	}
}
