package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Class Projection.
 */
public class Projection extends UnaryOperation{

	
	/** The att names. */
	private List<String> attNames;
	
	/**
	 * Instantiates a new projection.
	 *
	 * @param name the name
	 * @param attNames the att names
	 */
	public Projection(String name, List<String> attNames) {
		super(name);
		this.attNames = attNames;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(beanName+".stream().map(bean -> {");
		streamCode.append("Map<String, Object> tmp = new HashMap<>();");
		for (String columnName: attNames) {
			streamCode.append("tmp.put(\"").append(columnName).append("\", BeanUtils.getProperty(bean, \"").append(columnName).append("\"));");
		}
		streamCode.append("return tmp; }).collect(Collectors.toSet());");
		return streamCode.toString();	
	}

	/**
	 * @return the attNames
	 */
	public List<String> getAttNames() {
		return attNames;
	}

	/**
	 * @param attNames the attNames to set
	 */
	public void setAttNames(List<String> attNames) {
		this.attNames = attNames;
	}
}
