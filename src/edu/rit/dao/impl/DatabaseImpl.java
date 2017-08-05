package edu.rit.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.collections.CollectionUtils;

import adipe.translate.Queries;
import adipe.translate.Schemas;
import adipe.translate.TranslationException;
import adipe.translate.ra.Schema;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.parser.ExprLexer;
import edu.rit.dao.impl.parser.ExprParser;
import edu.rit.dao.impl.parser.PredicateListener;
import edu.rit.dao.impl.relational.CartesianProduct;
import edu.rit.dao.impl.relational.Difference;
import edu.rit.dao.impl.relational.Projection;
import edu.rit.dao.impl.relational.Select;
import edu.rit.dao.impl.relational.TableAccess;
import edu.rit.dao.impl.relational.Union;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;
import edu.rit.utils.Utils;
import ra.OneArgTerm;
import ra.Term;
import ra.TwoArgTerm;

/**
 * The Class DatabaseImpl.
 */
public class DatabaseImpl implements Database {

	// TODO Implement selectT operation

	/** The Constant RELATION. */
	public static final String RELATION = "relation";

	/** The Constant GENJOIN. */
	public static final String GENJOIN = "genjoin";

	/** The Constant EQJOIN. */
	public static final String EQJOIN = "eqjoin";

	/** The Constant FILTER. */
	public static final String FILTER = "filter";

	/** The Constant PROJECTION. */
	public static final String PROJECTION = "project";

	/** The Constant SELECT. */
	public static final String SELECT = "select";

	/** The Constant DUPREM. */
	public static final String DUPREM = "duprem";

	/** The Constant UNION. */
	public static final String UNION = "union";

	/** The Constant DIFF. */
	public static final String DIFF = "diffset";

	/** The Constant CARTPROD. */
	public static final String CARTPROD = "cartprod";

	/** The Constant REFERENCE. */
	public static final String REFERENCE = "reference";

	/** The attributes order. */
	// index for every column
	private Map<Integer, String> attributesOrder;

	/** The schema column names. */
	// contains the information about tables and column names
	private TreeMap schemaColumnNames;

	/** The counter. */
	private int counter = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.Database#createSchema(java.util.Map)
	 */
	public Schema createSchema(Map<String, Map<String, String>> schemaDescriptor) {
		attributesOrder = new HashMap<>();
		counter = 1;
		StringBuilder sb = new StringBuilder();
		schemaDescriptor.entrySet().forEach(t -> sb.append(createTableStatement(t.getKey(), t.getValue())));
		return Schemas.fromDDL(sb.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rit.dao.iapi.Database#getExecutionPlan(java.lang.String,
	 * adipe.translate.ra.Schema)
	 */
	public RelationalAlgebra getExecutionPlan(String query, Schema schema) throws Exception {
		RelationalAlgebra root = null;
		Term t = Queries.getRaOf(schema, query);
		// System.out.println(Terms.indent(t));
		if (t != null) {
			// getting the map with the information about columns name from
			// the schema
			Field fieldColumns = Utils.getField(schema.getClass(), "columnNames");
			schemaColumnNames = (TreeMap) fieldColumns.get(schema);
			// parsing the query
			root = parse(schema, t);
		}
		return root;
	}

	/**
	 * Parses the.
	 *
	 * @param schema
	 *            the schema
	 * @param term
	 *            the term
	 * @return the relational algebra
	 * @throws Exception
	 *             the exception
	 */
	private RelationalAlgebra parse(Schema schema, Term term) throws Exception {
		return parseOperation(schema, term);
	}

	/**
	 * Parses the operation.
	 *
	 * @param schema
	 *            the schema
	 * @param term
	 *            the term
	 * @return the relational algebra
	 * @throws Exception
	 *             the exception
	 */
	private RelationalAlgebra parseOperation(Schema schema, Term term) throws Exception {
		RelationalAlgebra ra = null;
		RelationalAlgebra raSource = null;
		BinaryOperation bo = null;
		Map<Integer, ColumnDescriptor> attsOrder = null;
		PredicateListener listener = null;
		int order = 1;
		// get relational algebra operation type
		String type = term.getClass().getAnnotation(com.thoughtworks.xstream.annotations.XStreamAlias.class).value();
		switch (type.toLowerCase()) {
		// check how it works
		case REFERENCE:
			Field refer = Utils.getField(term.getClass(), "refersTo");
			try {
				term.alias = String.valueOf(refer.get(term));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		case RELATION:
			String tableName = term.alias;
			ra = new TableAccess(Utils.randomIdentifier(tableName), null, tableName);
			List<String> l = (ArrayList<String>) schemaColumnNames.get(tableName);
			attsOrder = new HashMap<>();
			order = 1;
			for (String s : l) {
				ColumnDescriptor c = new ColumnDescriptor();
				c.setTableName(tableName);
				c.setId(order);
				c.setName(s);
				attsOrder.put(order, c);
				order++;
			}
			ra.setAttOrder(attsOrder);
			break;
		case PROJECTION:
			Field field = Utils.getField(term.getClass(), "indexesToProjectOn");
			List<String> attNames = null;
			try {
				raSource = parseOperation(schema, getSource(term));
				int[] projectedColumns = (int[]) field.get(term);
				attsOrder = new HashMap<>();
				order = 1;
				if (projectedColumns.length > 0) {
					attNames = new ArrayList<>();
					for (int index : projectedColumns) {
						// using alias
						ColumnDescriptor c = raSource.getAttOrder().get(index);
						String name = c.getAlias() != null ? c.getAlias() : c.getName();
						attNames.add(name);
						attsOrder.put(order, raSource.getAttOrder().get(index));
						order++;
					}
				}
				ra = new Projection(Utils.randomIdentifier("projection"), attNames, raSource);
				ra.setAttOrder(attsOrder);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			break;
		case DUPREM:
			ra = parseOperation(schema, getSource(term));
			break;
		case UNION:
			bo = binaryOperation(schema, term);
			ra = new Union(Utils.randomIdentifier(UNION), bo.getLeftSource(), bo.getRightSource());
			ra.setAttOrder(bo.getAttOrder());
			break;
		case DIFF:
			bo = binaryOperation(schema, term);
			ra = new Difference(Utils.randomIdentifier(DIFF), bo.getLeftSource(), bo.getRightSource());
			ra.setAttOrder(bo.getAttOrder());
			break;
		case CARTPROD:
			bo = binaryOperation(schema, term);
			checkingDuplicateAttNames(bo);
			ra = new CartesianProduct(Utils.randomIdentifier(CARTPROD), bo.getLeftSource(), bo.getRightSource());
			ra.setAttOrder(bo.getAttOrder());
			break;
		case EQJOIN:
			bo = binaryOperation(schema, term);
			checkingDuplicateAttNames(bo);
			Qualifier qualifier = new Qualifier();
			Field fieldIndex1 = Utils.getField(term.getClass(), "index1");
			Field fieldIndex2 = Utils.getField(term.getClass(), "index2");
			ColumnDescriptor column = new ColumnDescriptor();
			try {
				// creating the predicate with the matching fields
				int indexColunmLeft = (int) fieldIndex1.get(term);
				int indexColunmRight = (int) fieldIndex2.get(term);
				column = bo.getLeftSource().getAttOrder().get(indexColunmLeft);
				qualifier.setParameterValue(bo.getRightSource().getAttOrder().get(indexColunmRight));
				qualifier.setColumnData(column);
				qualifier.setOperator(Operator.EQUALS);
				List<Qualifier> qualifiers = new ArrayList<>();
				qualifiers.add(qualifier);

				// updating attributes order
				List<ColumnDescriptor> list = new ArrayList<>();
				list.add(bo.getLeftSource().getAttOrder().get(indexColunmLeft));
				for (int i = 1; i <= bo.getLeftSource().getAttOrder().size(); ++i) {
					if (i != indexColunmLeft) {
						list.add(bo.getLeftSource().getAttOrder().get(i));
					}
				}
				// in Join clause the indexColumnRight is not returned
				// list.add(bo.getRightSource().getAttOrder().get(indexColunmRight));
				for (int i = 1; i <= bo.getRightSource().getAttOrder().size(); ++i) {
					if (i != indexColunmRight) {
						list.add(bo.getRightSource().getAttOrder().get(i));
					}
				}
				order = 1;
				attsOrder = new HashMap<>();
				for (ColumnDescriptor s : list) {
					attsOrder.put(order, s);
					order++;
				}
				// join operation is a cartesian product and a select
				raSource = new CartesianProduct(Utils.randomIdentifier(CARTPROD), bo.getLeftSource(),
						bo.getRightSource());
				raSource.setAttOrder(attsOrder);
				// in this case the predicate only contains the join clause that
				// it is an expression like this #3=#5, we replace that
				// expression for 0
				ra = new Select(Utils.randomIdentifier(SELECT), qualifiers, "0", raSource);
				ra.setAttOrder(raSource.getAttOrder());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			break;
		case GENJOIN:
			// Implement it as a cartesian product with a select
			bo = binaryOperation(schema, term);
			checkingDuplicateAttNames(bo);
			raSource = new CartesianProduct(Utils.randomIdentifier(CARTPROD), bo.getLeftSource(), bo.getRightSource());
			raSource.setAttOrder(bo.getAttOrder());
			listener = parsePredicate(term, raSource.getAttOrder());
			ra = new Select(Utils.randomIdentifier(SELECT), listener.getQualifiers(),
					listener.getPredicate().toString(), raSource);
			ra.setAttOrder(raSource.getAttOrder());
			break;
		case FILTER:
			raSource = parseOperation(schema, getSource(term));
			listener = parsePredicate(term, raSource.getAttOrder());
			ra = new Select(Utils.randomIdentifier(SELECT), listener.getQualifiers(),
					listener.getPredicate().toString(), raSource);
			ra.setAttOrder(raSource.getAttOrder());
			break;
		default:
			throw new Exception("No implemented parser for SQLToAlgebra object " + type);
		}
		return ra;
	}

	/**
	 * Binary operation.
	 *
	 * @param schema
	 *            the schema
	 * @param term
	 *            the term
	 * @return the binary operation
	 * @throws Exception
	 *             the exception
	 */
	private BinaryOperation binaryOperation(Schema schema, Term term) throws Exception {
		BinaryOperation bo = null;
		// two sources, left and right
		if (TwoArgTerm.class.isAssignableFrom(term.getClass())) {
			TwoArgTerm twoTerms = (TwoArgTerm) term;
			RelationalAlgebra leftSource = parseOperation(schema, twoTerms.getInputTerm1());
			RelationalAlgebra rightSource = parseOperation(schema, twoTerms.getInputTerm2());
			if (leftSource != null && rightSource != null) {
				bo = new BinaryOperation(null, leftSource, rightSource) {

					@Override
					public String toString() {
						return null;
					}

					@Override
					public String perform() {
						return null;
					}
				};
				// updating map of attributes order
				updateAttsOrder(bo);
			}
		}
		return bo;
	}

	/**
	 * Gets the source.
	 *
	 * @param term
	 *            the term
	 * @return the source
	 */
	private Term getSource(Term term) {
		Term t = null;
		// Check whether superClass is OneArgTerm
		if (OneArgTerm.class.isAssignableFrom(term.getClass())) {
			OneArgTerm oneAgr = (OneArgTerm) term;
			t = oneAgr.getInputTerm();
		}
		return t;
	}

	/**
	 * Creates the table statement.
	 *
	 * @param tableName
	 *            the table name
	 * @param columnsDescMap
	 *            the columns desc map
	 * @return the string
	 */
	private String createTableStatement(String tableName, Map<String, String> columnsDescMap) {
		StringBuilder sql = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
		for (Iterator<String> iter = columnsDescMap.keySet().iterator(); iter.hasNext();) {
			String columnName = iter.next();
			sql.append(columnName);
			sql.append(" ").append(columnsDescMap.get(columnName));
			if (iter.hasNext()) {
				sql.append(",");
			}
		}
		sql.append(");");
		return sql.toString();
	}

	/**
	 * Parses the predicate.
	 *
	 * @param term
	 *            the term
	 * @param attsOrder
	 *            the atts order
	 * @return the predicate listener
	 */
	private PredicateListener parsePredicate(Term term, Map<Integer, ColumnDescriptor> attsOrder) {
		// creating the listener
		PredicateListener listener = new PredicateListener(attsOrder);
		// getting the predicate value from the term object
		Field predicateField = Utils.getField(term.getClass(), "predicate");
		String predicate;
		try {
			predicate = (String) predicateField.get(term);
			// init the parser
			ANTLRInputStream in = new ANTLRInputStream(predicate);
			ExprLexer lexer = new ExprLexer(in);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExprParser parser = new ExprParser(tokens);
			// parsing the predicate tree
			ParserRuleContext context = parser.predicate();
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(listener, context);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return listener;
	}

	/**
	 * Checking duplicate att names.
	 *
	 * @param bo
	 *            the bo
	 */
	private void checkingDuplicateAttNames(BinaryOperation bo) {
		// checking whether there are duplicates attributes name
		Map<String, Integer> mapLeftSource = new HashMap<>();
		bo.getLeftSource().getAttOrder().forEach((k, v) -> mapLeftSource.put(v.getName(), k));
		Map<String, Integer> mapRightSource = new HashMap<>();
		bo.getRightSource().getAttOrder().forEach((k, v) -> mapRightSource.put(v.getName(), k));
		Collection<String> intersection = CollectionUtils.intersection(mapLeftSource.keySet(), mapRightSource.keySet());
		for (String attName : intersection) {
			// renaming duplicates adding as a prefix the table name
			renamingAttribute(attName, mapLeftSource, bo.getLeftSource().getAttOrder());
			renamingAttribute(attName, mapRightSource, bo.getRightSource().getAttOrder());
		}
	}

	/**
	 * Renaming attribute.
	 *
	 * @param attName
	 *            the att name
	 * @param tmpMap
	 *            the tmp map
	 * @param attsMap
	 *            the atts map
	 */
	private void renamingAttribute(String attName, Map<String, Integer> tmpMap,
			Map<Integer, ColumnDescriptor> attsMap) {
		Integer id = tmpMap.get(attName);
		ColumnDescriptor c = attsMap.get(id);
		c.setAlias(c.getTableName() + "_" + c.getName());
	}

	/**
	 * Update atts order.
	 *
	 * @param bo
	 *            the bo
	 */
	private void updateAttsOrder(BinaryOperation bo) {
		// updating attributes order of both sources
		int index = 1;
		Map<Integer, ColumnDescriptor> attsOrder = new HashMap<>();
		for (ColumnDescriptor c : bo.getLeftSource().getAttOrder().values()) {
			attsOrder.put(index, c);
			index++;
		}
		for (ColumnDescriptor c : bo.getRightSource().getAttOrder().values()) {
			attsOrder.put(index, c);
			index++;
		}
		bo.setAttOrder(attsOrder);
	}
}
