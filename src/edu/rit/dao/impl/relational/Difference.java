package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationAlgebraOperation;

/**
 * The Class Difference.
 */
public class Difference extends BinaryOperation{

	/**
	 * Instantiates a new difference.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Difference(RelationAlgebraOperation leftSource, RelationAlgebraOperation rightSource) {
		super(leftSource, rightSource);
	}

}
