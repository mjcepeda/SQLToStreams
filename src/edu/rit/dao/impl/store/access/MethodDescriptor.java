package edu.rit.dao.impl.store.access;

import java.util.List;

/**
 * The Class MethodDescriptor.
 */
public class MethodDescriptor {
	
	/** The method name. */
	String methodName;
	
	/** The input params. */
	List<String> inputParams;
	
	/** The output param. */
	String outputParam;
	
	/** The query. */
	String query;

	/**
	 * Gets the method name.
	 *
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Sets the method name.
	 *
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Gets the input params.
	 *
	 * @return the inputParams
	 */
	public List<String> getInputParams() {
		return inputParams;
	}

	/**
	 * Sets the input params.
	 *
	 * @param inputParams the inputParams to set
	 */
	public void setInputParams(List<String> inputParams) {
		this.inputParams = inputParams;
	}

	/**
	 * Gets the output param.
	 *
	 * @return the outputParam
	 */
	public String getOutputParam() {
		return outputParam;
	}

	/**
	 * Sets the output param.
	 *
	 * @param outputParam the outputParam to set
	 */
	public void setOutputParam(String outputParam) {
		this.outputParam = outputParam;
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
}
