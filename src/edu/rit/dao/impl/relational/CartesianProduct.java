package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.utils.Utils;

/**
 * The Class CartesianProduct.
 */
public class CartesianProduct extends BinaryOperation{

	
	/**
	 * Instantiates a new cartesian product.
	 *
	 * @param name the name
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
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getLeftSource().getReturnVar() + ".get().flatMap(bean1 -> ");
		streamCode.append(getRightSource().getReturnVar() + ".get().map(bean2 -> {");
		//renaming attributes from the left source
		for (ColumnDescriptor c: getLeftSource().getAttOrder().values()) {
			if (c.getAlias() != null) {
				String newName = Utils.randomIdentifier(c.getName());
				streamCode.append(" Object ").append(newName).append(" = bean1.get(\"").append(c.getName()).append("\");");
				streamCode.append(" bean1.remove(\"").append(c.getName()).append("\");");
				streamCode.append(" bean1.put(\"").append(c.getAlias()).append("\", ").append(newName).append(");");
			}
		}
		//renaming attributes from the right source
		for (ColumnDescriptor c: getRightSource().getAttOrder().values()) {
			if (c.getAlias() != null) {
				String newName = Utils.randomIdentifier(c.getName());
				streamCode.append(" Object ").append(newName).append(" = bean2.get(\"").append(c.getName()).append("\");");
				streamCode.append(" bean2.remove(\"").append(c.getName()).append("\");");
				streamCode.append(" bean2.put(\"").append(c.getAlias()).append("\", ").append(newName).append(");");
			}
		}
		//creating new map for merging both sources
		streamCode.append("Map<String, Object> tmp = new java.util.HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }))");
		return streamCode.toString();
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.RelationalAlgebra#toString()
	 */
	public String toString() {
		return "Cartesian Product\nbeanName: " + getReturnVar() + "\nleftSource: " + getLeftSource()
		+ "\nrightSource: " + getRightSource();
	}
}
