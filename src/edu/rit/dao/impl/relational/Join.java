package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.impl.store.access.Qualifier;

/**
 * The Class Join.
 */
public class Join extends BinaryOperation{

	/** The qualifiers. 
	 *  Contain the fields used for joining the tuples
	 **/
	private List<Qualifier> qualifiers;
	
	/**
	 * Instantiates a new join.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 * @param qualifiers the qualifiers
	 */
	public Join(String leftSource, String rightSource, List<Qualifier> qualifiers) {
		super(leftSource, rightSource);
		this.qualifiers = qualifiers;
	}
	
	public String perform() {
		StringBuilder streamCode = new StringBuilder();
		//TODO MJCG 
		return streamCode.toString();
	}
}
