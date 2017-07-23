package edu.rit.dao.impl.store.access;

import java.util.List;

public class ClassDescriptor {

	String absoluteName;
	
	List<MethodDescriptor> methods;

	
	public String getAbsoluteName() {
		return absoluteName;
	}

	
	public void setAbsoluteName(String simpleName) {
		this.absoluteName = simpleName;
	}

	/**
	 * @return the methods
	 */
	public List<MethodDescriptor> getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(List<MethodDescriptor> methods) {
		this.methods = methods;
	}
}
