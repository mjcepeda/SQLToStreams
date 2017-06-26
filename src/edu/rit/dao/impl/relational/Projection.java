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
	 * @param name
	 *            the name
	 * @param attNames
	 *            the att names
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
		//TODO MJCG Limitations: name || id
		//TODO MJCG Do I really need BeanUtils anymore? It is suppose that the entire process works only with maps
		StringBuilder streamCode = new StringBuilder();
		// streamCode.append(getBeanName()+".stream()"
		streamCode.append(".map(bean -> {");
		streamCode.append("Map<String, Object> tmp = new HashMap<>();");
		streamCode.append("try {");
		for (String columnName : attNames) {
			streamCode.append("tmp.put(\"").append(columnName).append("\", BeanUtils.getProperty(bean, \"")
					.append(columnName).append("\"));");
		}
		streamCode.append("} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {");
		streamCode.append("System.err.println(e.getMessage());");
		streamCode.append("return null; }");
		//if we do not return the next map, it gives us an error
		streamCode.append("return tmp; })");
		return streamCode.toString();
	}

	/**
	 * @return the attNames
	 */
	public List<String> getAttNames() {
		return attNames;
	}

	/**
	 * @param attNames
	 *            the attNames to set
	 */
	public void setAttNames(List<String> attNames) {
		this.attNames = attNames;
	}
}
