package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;

public class TableAccess extends UnaryOperation{
	
	private String tableName;
	
	public TableAccess(String beanName, RelationalAlgebra source, String tableName) {
		super(beanName, source);
		this.tableName = tableName;
	}

	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () -> userObjToMap(");
		streamCode.append(tableName).append(").stream()");
		return streamCode.toString();
	}
	
	public String toString() {
		return "TableAccess\n\tbeanName: " + getReturnVar() + "\n\ttableName: " + tableName
				+ "\n\tsource: " + getSource();
	}
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}	
}
