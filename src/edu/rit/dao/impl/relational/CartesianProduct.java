package edu.rit.dao.impl.relational;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationAlgebraOperation;

/**
 * The Class CartesianProduct.
 */
public class CartesianProduct extends BinaryOperation{

	public CartesianProduct(RelationAlgebraOperation leftSource, RelationAlgebraOperation rightSource) {
		super(leftSource, rightSource);
	}
	
}
