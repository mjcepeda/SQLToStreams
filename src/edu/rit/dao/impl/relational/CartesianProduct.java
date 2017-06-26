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
	public CartesianProduct(String leftName, String rightName, RelationalAlgebra leftSource, RelationalAlgebra rightSource) {
		super(leftName, rightName, leftSource, rightSource);
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(getLeftBeanName() + ".stream().flatMap(bean1 -> ");
		streamCode.append(getRightBeanName() + ".stream().map(bean2 -> {");
		streamCode.append("Map<String, Object> tmp = new HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }))");
		return streamCode.toString();
	}
}
