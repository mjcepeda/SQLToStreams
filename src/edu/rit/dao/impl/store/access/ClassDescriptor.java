package edu.rit.dao.impl.store.access;

import java.util.List;

/**
 * The Class ClassDescriptor.
 */
public class ClassDescriptor {

	/** The absolute name. */
	String absoluteName;
	
	/** The methods. */
	List<MethodDescriptor> methods;

	
	/**
	 * Gets the absolute name.
	 *
	 * @return the absolute name
	 */
	public String getAbsoluteName() {
		return absoluteName;
	}

	
	/**
	 * Sets the absolute name.
	 *
	 * @param simpleName the new absolute name
	 */
	public void setAbsoluteName(String simpleName) {
		this.absoluteName = simpleName;
	}

	/**
	 * Gets the methods.
	 *
	 * @return the methods
	 */
	public List<MethodDescriptor> getMethods() {
		return methods;
	}

	/**
	 * Sets the methods.
	 *
	 * @param methods the methods to set
	 */
	public void setMethods(List<MethodDescriptor> methods) {
		this.methods = methods;
	}
}
