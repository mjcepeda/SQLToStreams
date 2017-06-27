package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.store.access.Qualifier;

/**
 * The Class Join.
 */
public class Join extends BinaryOperation {

	/**
	 * The qualifiers. Contain the fields used for joining the tuples
	 **/
	private List<Qualifier> qualifiers;

	/**
	 * Instantiates a new join.
	 *
	 * @param leftSource
	 *            the left source
	 * @param rightSource
	 *            the right source
	 * @param qualifiers
	 *            the qualifiers
	 */
	public Join(String name, RelationalAlgebra leftSource, RelationalAlgebra rightSource, List<Qualifier> qualifiers) {
		super(name, leftSource, rightSource);
		this.qualifiers = qualifiers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.relational.BinaryOperation#perform()
	 */
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		// TODO MJCG What happens if both maps have the same columns name, to I
		// need to do the rename
		// or I will get a rename operation from Derby?
		streamCode.append("Stream<Map<String,Object>> ");
		streamCode.append(getReturnVar()).append(" = ");
		streamCode.append(getLeftSource().getReturnVar()).append(".flatMap(bean1 ->");
		streamCode.append(getRightSource().getReturnVar());
		if (qualifiers != null) {
			for (Qualifier qualifier : qualifiers) {
				streamCode.append(".filter(bean2 -> Objects.equals(bean1.get(\"" + qualifier.getColumnData().getName()
						+ "\"), bean2.get(\"" + qualifier.getParameterValue() + "\"))).map(bean2 -> {");
				streamCode.append(
						"Map<String, Object> tmp = new HashMap<String, Object>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; })");
			}
		}
		streamCode.append(")");
		return streamCode.toString();
	}
}
