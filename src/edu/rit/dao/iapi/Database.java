package edu.rit.dao.iapi;

import java.util.List;
import java.util.Map;

import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Interface Database.
 *
 * @author Maria J. Cepeda
 */
public interface Database {

	/**
	 * Creates the DB.
	 */
	public void createDB();
	
	/**
	 * Creates the table.
	 *
	 * @param tableName the table name
	 * @param columnsDescMap the columns desc map
	 * @return true, if successful
	 */
	public boolean createTable(String tableName, Map<String, String> columnsDescMap);
	
	/**
	 * Insert data.
	 *
	 * @param tableName the table name
	 * @param dataList the data list
	 * @return the int[]
	 */
	public int[] insertData(String tableName, List<Map<String, Object>> dataList);
	
	/**
	 * Shutdown.
	 */
	public void shutdown();
	
	/**
	 * Drop DB.
	 */
	public void dropDB();
	
	/**
	 * Gets the execution plan.
	 *
	 * @param query the query
	 * @return the execution plan
	 */
	public UnaryOperation getExecutionPlan(String query);
}
