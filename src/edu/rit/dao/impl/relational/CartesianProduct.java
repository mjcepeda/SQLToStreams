package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Class CartesianProduct.
 */
public class CartesianProduct extends BinaryOperation{

	/**
	 * Instantiates a new cartesian product.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public CartesianProduct(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(name, leftSource, rightSource);
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		// TODO MJCG What happens if both maps have the same columns name
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getLeftSource().getReturnVar() + ".get().flatMap(bean1 -> ");
		streamCode.append(getRightSource().getReturnVar() + ".get().map(bean2 -> {");
		streamCode.append("Map<String, Object> tmp = new java.util.HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }))");
		return streamCode.toString();
	}
	
	public String toString() {
		return "Cartesian Product\nbeanName: " + getReturnVar() + "\nleftSource: " + getLeftSource()
		+ "\nrightSource: " + getRightSource();
	}
}
