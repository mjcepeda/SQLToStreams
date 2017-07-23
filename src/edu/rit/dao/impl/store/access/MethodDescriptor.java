package edu.rit.dao.impl.store.access;

import java.util.List;

public class MethodDescriptor {
	
	String methodName;
	
	List<String> inputParams;
	
	String outputParam;
	
	String query;

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the inputParams
	 */
	public List<String> getInputParams() {
		return inputParams;
	}

	/**
	 * @param inputParams the inputParams to set
	 */
	public void setInputParams(List<String> inputParams) {
		this.inputParams = inputParams;
	}

	/**
	 * @return the outputParam
	 */
	public String getOutputParam() {
		return outputParam;
	}

	/**
	 * @param outputParam the outputParam to set
	 */
	public void setOutputParam(String outputParam) {
		this.outputParam = outputParam;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
}
