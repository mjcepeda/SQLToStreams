package edu.rit.dao.iapi;

import java.util.Map;

import adipe.translate.ra.Schema;
import edu.rit.dao.iapi.relational.RelationalAlgebra;

/**
 * The Interface Database.
 *
 * @author Maria J. Cepeda
 */
public interface Database {
	
	/**
	 * Creates the schema.
	 *
	 * @param schemaDescriptor the schema descriptor
	 * @return the schema
	 */
	public Schema createSchema(Map<String, Map<String, String>> schemaDescriptor);
	
	/**
	 * Gets the execution plan.
	 *
	 * @param query the query
	 * @param schema the schema
	 * @return the execution plan
	 * @throws Exception the exception
	 */
	public RelationalAlgebra getExecutionPlan(String query, Schema schema) throws Exception;
}
