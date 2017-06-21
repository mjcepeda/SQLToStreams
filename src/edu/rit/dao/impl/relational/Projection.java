package edu.rit.dao.impl.relational;

import java.util.List;

import edu.rit.dao.iapi.relational.RelationAlgebraOperation;
import edu.rit.dao.impl.store.access.ColumnDescriptor;

/**
 * The Class Projection.
 */
public class Projection extends RelationAlgebraOperation{

	/** The source. */
	private RelationAlgebraOperation source;
	
	/** The result description. */
	private List<ColumnDescriptor> resultDescription;
	
	/**
	 * Instantiates a new projection.
	 *
	 * @param table the table
	 * @param source the source
	 * @param resultDescription the result description
	 */
	public Projection(String table, RelationAlgebraOperation source, List<ColumnDescriptor> resultDescription) {
		super(table);
		this.source = source;
		this.resultDescription = resultDescription;
	}
	
}
