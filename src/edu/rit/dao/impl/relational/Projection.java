package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Class Projection.
 */
public class Projection extends UnaryOperation {

	/** The att names. */
	private List<String> attNames;

	
	/**
	 * Instantiates a new projection.
	 *
	 * @param name the name
	 * @param attNames the att names
	 * @param source the source
	 */
	public Projection(String name, List<String> attNames, RelationalAlgebra source) {
		super(name, source);
		this.attNames = attNames;
	}

	/**
	 * Perform.
	 *
	 * @return the string
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		streamCode.append("java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> ");
		streamCode.append(getReturnVar()).append(" = () ->");
		streamCode.append(getSource().getReturnVar());
		streamCode.append(".get().map(bean -> {");
		streamCode.append("Map<String, Object> tmp = new java.util.HashMap<>();");
		//streamCode.append("try {");
		for (String columnName : attNames) {
			streamCode.append("tmp.put(\"").append(columnName).append("\", bean.get(\"")
					.append(columnName).append("\"));");
		}
		//streamCode.append("} catch (IllegalAccessException | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {");
		//streamCode.append("System.err.println(e.getMessage());");
		//streamCode.append("return null; }");
		// if we do not return the next map, it gives us an error
		streamCode.append("return tmp; })");
		return streamCode.toString();
	}

	/* (non-Javadoc)
	 * @see edu.rit.dao.iapi.relational.RelationalAlgebra#toString()
	 */
	public String toString() {
		return "Projection\n\tbeanName: " + getReturnVar() + "\n\tcolumns: " + attNames
				+ "\n\tsource: " + getSource();
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
	 * @param attNames            the attNames to set
	 */
	public void setAttNames(List<String> attNames) {
		this.attNames = attNames;
	}
}
