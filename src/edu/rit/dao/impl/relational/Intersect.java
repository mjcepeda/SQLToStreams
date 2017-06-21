package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationAlgebraOperation;

/**
 * The Class Intersect.
 */
public class Intersect extends BinaryOperation{

	/**
	 * Instantiates a new intersect.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Intersect(RelationAlgebraOperation leftSource, RelationAlgebraOperation rightSource) {
		super(leftSource, rightSource);
	}

}
