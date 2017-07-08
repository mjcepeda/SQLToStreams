package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class Difference.
 */
public class Difference extends BinaryOperation {

	/**
	 * Instantiates a new difference.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 */
	public Difference(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(name, leftSource, rightSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getLeftSource().getReturnVar() + ".stream().filter(bean -> !");
		streamCode.append(getRightSource().getReturnVar() + ".contains(bean))");
		return streamCode.toString();
	}

	public String toString() {
		return "Difference\nbeanName: " + getReturnVar() + "\nleftSource: " + getLeftSource()
		+ "\nrightSource: " + getRightSource();
	}
}
