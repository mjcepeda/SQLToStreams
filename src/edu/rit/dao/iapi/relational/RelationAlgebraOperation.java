package edu.rit.dao.iapi.relational;

//TODO MJCG Reevaluate if all relation algebra operations need tableTable
/**
 * The Class RelationAlgebraOperation.
 */
public abstract class RelationAlgebraOperation {

	/** The table name. */
	private String tableName;
	
	public RelationAlgebraOperation(String table) {
		this.tableName = table;
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
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName(){
		return tableName;
	};
}
