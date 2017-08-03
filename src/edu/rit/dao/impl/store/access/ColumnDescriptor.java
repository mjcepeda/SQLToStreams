package edu.rit.dao.impl.store.access;

/**
 * The Class ColumnDescriptor.
 */
public class ColumnDescriptor {

	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The table name. */
	private String tableName;
	
	/** The alias. */
	private String alias;
	
	/**
	 *  The type.
	 *
	 * @return the string
	 */
	//private DataType type;
	
	
	public String toString() {
		return "name: " + name + ", alias: " + alias;
	}
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * Sets the table name.
	 *
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
//	public DataType getType() {
//		return type;
//	}
//	
//	/**
//	 * Sets the type.
//	 *
//	 * @param type the new type
//	 */
//	public void setType(DataType type) {
//		this.type = type;
//	}
}
