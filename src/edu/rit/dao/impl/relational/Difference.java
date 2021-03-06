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
	 * @param name
	 *            the name
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
		streamCode.append("List<Map<String,Object>> list").append(getRightSource().getReturnVar()).append(" = ")
				.append(getRightSource().getReturnVar()).append(".get().collect(java.util.stream.Collectors.toList());");
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getLeftSource().getReturnVar() + ".get().filter(bean -> !");
		// TODO MJCG Create the list only once
		streamCode.append("list" + getRightSource().getReturnVar() + ".contains(bean))");
		return streamCode.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.RelationalAlgebra#toString()
	 */
	public String toString() {
		return "Difference\nbeanName: " + getReturnVar() + "\nleftSource: " + getLeftSource() + "\nrightSource: "
				+ getRightSource();
	}
}
