package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Class TableAccess.
 */
public class TableAccess extends UnaryOperation{
	
	/** The table name. */
	private String tableName;
	
	/**
	 * Instantiates a new table access.
	 *
	 * @param beanName the bean name
	 * @param source the source
	 * @param tableName the table name
	 */
	public TableAccess(String beanName, RelationalAlgebra source, String tableName) {
		super(beanName, source);
		this.tableName = tableName;
	}

	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.UnaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () -> ").append(tableName).append("ToMap(");
		streamCode.append(tableName).append(").stream()");
		return streamCode.toString();
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.RelationalAlgebra#toString()
	 */
	public String toString() {
		return "TableAccess\n\tbeanName: " + getReturnVar() + "\n\ttableName: " + tableName
				+ "\n\tsource: " + getSource();
	}
	
	/**
	 * Gets the table name.
	 *
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}	
}
