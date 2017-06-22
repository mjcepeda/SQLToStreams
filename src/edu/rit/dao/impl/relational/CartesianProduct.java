package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;

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
	public CartesianProduct(String leftSource, String rightSource) {
		super(leftSource, rightSource);
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("List<Map<String,Object>> l = new ArrayList<>();");
		streamCode.append(getLeftSource() + ".forEach(bean1 -> {");
		streamCode.append(getRightSource() + ".forEach(bean2 -> {");
		streamCode.append("Map<String, Object> newBean = new HashMap<>();");
		streamCode.append("Map<String, Object> descbean1 = beanProperties(bean1);");
		streamCode.append("Map<String, Object> descbean2 = beanProperties(bean2);");
		streamCode.append("newBean.putAll(descbean1);");
		streamCode.append("newBean.putAll(descbean2);");
		streamCode.append("l.add(newBean);");
		streamCode.append("});});");
		streamCode.append("return l;");
		return streamCode.toString();
	}
}
