package edu.rit.dao.impl.relational;

import java.util.List;
import edu.rit.dao.iapi.relational.RelationAlgebraOperation;
import edu.rit.dao.impl.store.access.Qualifier;

/**
 * The Class Select.
 */
public class Select extends RelationAlgebraOperation{

	
	/** The qualifiers. */
	private List<Qualifier> qualifiers;
	
	/**
	 * Instantiates a new select.
	 *
	 * @param table the table
	 * @param qualifiers the qualifiers
	 */
	public Select(String table, List<Qualifier> qualifiers) {
		super(table);
		this.qualifiers=qualifiers;
	}
	
	/**
	 * Gets the qualifiers.
	 *
	 * @return the qualifiers
	 */
	public List<Qualifier> getQualifiers() {
		return qualifiers;
	}
	
	/**
	 * Sets the qualifiers.
	 *
	 * @param qualifiers the new qualifiers
	 */
	public void setQualifiers(List<Qualifier> qualifiers) {
		this.qualifiers = qualifiers;
	}
}
