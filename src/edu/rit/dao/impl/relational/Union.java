package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationAlgebraOperation;

/**
 * The Class Union.
 */
public class Union extends BinaryOperation{

	/**
	 * Instantiates a new union.
	 *
	 * @param leftSource the left source
	 * @param rightSource the right source
	 */
	public Union(RelationAlgebraOperation leftSource, RelationAlgebraOperation rightSource) {
		super(leftSource, rightSource);
	}

}
