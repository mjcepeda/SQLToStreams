package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Class Select.
 */
public class Select extends UnaryOperation{

	/** The att names. */
	private List<String> attNames;
	
	/**
	 * Instantiates a new select.
	 *
	 * @param name the name
	 * @param attNames the att names
	 */
	public Select(String name, List<String> attNames) {
		super(name);
		this.attNames=attNames;
	}
	
	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.UnaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append(beanName+".stream().filter(bean -> {");
		//TODO
		return streamCode.toString();
	}
	
	/**
	 * Gets the att names.
	 *
	 * @return the attNames
	 */
	public List<String> getAttNames() {
		return attNames;
	}

	/**
	 * Sets the att names.
	 *
	 * @param attNames the attNames to set
	 */
	public void setAttNames(List<String> attNames) {
		this.attNames = attNames;
	}
}
