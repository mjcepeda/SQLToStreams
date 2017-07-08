package edu.rit.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import adipe.translate.Queries;
import adipe.translate.Schemas;
import adipe.translate.TranslationException;
import adipe.translate.ra.Schema;
import adipe.translate.ra.Terms;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.parser.ExprLexer;
import edu.rit.dao.impl.parser.ExprParser;
import edu.rit.dao.impl.parser.PredicateListener;
import edu.rit.dao.impl.relational.CartesianProduct;
import edu.rit.dao.impl.relational.Difference;
import edu.rit.dao.impl.relational.Join;
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

public class DatabaseImpl implements Database {

	public static final String RELATION = "relation";
	public static final String GENJOIN = "genjoin";
	public static final String EQJOIN = "eqjoin";
	public static final String FILTER = "filter";
	public static final String PROJECTION = "project";
	// TODO MJCG Implement selectT operation
	public static final String SELECT = "select";
	public static final String DUPREM = "duprem";
	public static final String UNION = "union";
	public static final String DIFF = "diffset";
	public static final String CARTPROD = "cartprod";
	
	//index for every column
	private Map<Integer, String> attributesOrder;
	//contains the information about tables and column names
	private TreeMap schemaColumnNames;
	private int counter = 1;

	public Schema createSchema(Map<String, Map<String, String>> schemaDescriptor) {
		attributesOrder = new HashMap<>();
		counter = 1;
		StringBuilder sb = new StringBuilder();
		schemaDescriptor.entrySet().forEach(t -> sb.append(createTableStatement(t.getKey(), t.getValue())));
		return Schemas.fromDDL(sb.toString());
	}

	public RelationalAlgebra getExecutionPlan(String query, Schema schema) {
		RelationalAlgebra root = null;
		try {
			Term t = Queries.getRaOf(schema, query);
			System.out.println(Terms.indent(t));

			if (t != null) {
				// TODO MJCG Relocate this code
				Field field = Utils.getField(schema.getClass(), "instantiatedTables");
				Field fieldColumns = Utils.getField(schema.getClass(), "columnNames");
				TreeSet tree = (TreeSet) field.get(schema);
				schemaColumnNames = (TreeMap) fieldColumns.get(schema);
				Iterator it = tree.descendingIterator();
				// Object o = it.next();
				// Iterator it = tree.descendingIterator();
				while (it.hasNext()) {
					Object o = (Object) it.next();
					List<String> l = (ArrayList<String>) schemaColumnNames.get(o);
					for (String name : l) {
						attributesOrder.put(counter, name);
						counter++;
					}
				}
				//
				root = parse(schema, t);
			}
		} catch (RuntimeException | TranslationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return root;
	}

	private RelationalAlgebra parse(Schema schema, Term term) {
		return parseOperation(schema, term);
	}

	private RelationalAlgebra parseOperation(Schema schema, Term term) {
		RelationalAlgebra ra = null;
		RelationalAlgebra raSource = null;
		BinaryOperation bo = null;
		// get type of the relational algebra operation
		String type = term.getClass().getAnnotation(com.thoughtworks.xstream.annotations.XStreamAlias.class).value();
		switch (type.toLowerCase()) {
		case RELATION:
			String tableName = term.alias;
			ra = new TableAccess(tableName, null, tableName);
			break;
		case PROJECTION:
			// TODO MJCG Last operation to implement, the number for the columns
			// change inside every operation
			// try this query: select * from professor p inner join department d
			// on p.dept = d.id
			Field field = Utils.getField(term.getClass(), "indexesToProjectOn");
			List<String> attNames = null;
			try {
				// TODO MJGC More that one source?
				raSource = parseOperation(schema, getSource(term));
				int[] projectedColumns = (int[]) field.get(term);
				if (projectedColumns.length > 0) {
					attNames = new ArrayList<>();
					for (int index : projectedColumns) {
						attNames.add(attributesOrder.get(index));
					}
				}
				String varName = Utils.randomIdentifier("projection");
				ra = new Projection(varName, attNames, raSource);
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
			break;
		case DIFF:
			bo = binaryOperation(schema, term);
			ra = new Difference(Utils.randomIdentifier(DIFF), bo.getLeftSource(), bo.getRightSource());
			break;
		case CARTPROD:
			bo = binaryOperation(schema, term);
			ra = new CartesianProduct(Utils.randomIdentifier(CARTPROD), bo.getLeftSource(), bo.getRightSource());
			break;
		case EQJOIN:
			bo = binaryOperation(schema, term);
			Qualifier qualifier = new Qualifier();
			Field fieldIndex1 = Utils.getField(term.getClass(), "index1");
			Field fieldIndex2 = Utils.getField(term.getClass(), "index2");
			ColumnDescriptor column = new ColumnDescriptor();
			try {
				// creating the qualifier with the join
				List<String> columnsLeftSource = (ArrayList<String>) schemaColumnNames
						.get(bo.getLeftSource().getReturnVar());
				List<String> columnsRightSource = (ArrayList<String>) schemaColumnNames
						.get(bo.getRightSource().getReturnVar());
				int indexColunmLeft = (int) fieldIndex1.get(term);
				int indexColunmRight = (int) fieldIndex2.get(term);
				column.setName(columnsLeftSource.get(indexColunmLeft - 1));
				qualifier.setParameterValue(columnsRightSource.get(indexColunmRight - 1));
				qualifier.setColumnData(column);
				qualifier.setOperator(Operator.EQUALS);
				List<Qualifier> qualifiers = new ArrayList<>();
				qualifiers.add(qualifier);
				// updating attributes order
				List<String> attOrder = new ArrayList<>();
				attOrder.add(columnsLeftSource.get(indexColunmLeft - 1));
				for (int i = 0; i < columnsLeftSource.size(); ++i) {
					if (i + 1 != indexColunmLeft) {
						attOrder.add(columnsLeftSource.get(i));
					}
				}
				for (int i = 0; i < columnsRightSource.size(); ++i) {
					if (i + 1 != indexColunmRight) {
						attOrder.add(columnsRightSource.get(i));
					}
				}
				counter = 1;
				attributesOrder = new HashMap<>();
				for (String s : attOrder) {
					attributesOrder.put(counter, s);
					counter++;
				}

				ra = new Join(Utils.randomIdentifier(EQJOIN), bo.getLeftSource(), bo.getRightSource(), qualifiers);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			break;
		case GENJOIN:
			//Implement it like a cartesian product with a select
			bo = binaryOperation(schema, term);
			raSource = new CartesianProduct(Utils.randomIdentifier(CARTPROD), bo.getLeftSource(), bo.getRightSource());
			ra = new Select(Utils.randomIdentifier("select"), parsePredicate(term), raSource);
			break;
		case FILTER:
			raSource = parseOperation(schema, getSource(term));
			List<Qualifier> qualifiers = parsePredicate(term);
			ra = new Select(Utils.randomIdentifier("select"), qualifiers, raSource);
			break;
		default:
			// TODO MJCG Handle unknown class
			break;
		}
		return ra;
	}

	private BinaryOperation binaryOperation(Schema schema, Term term) {
		BinaryOperation bo = null;
		// two sources, left and right
		// Field sourceField = Utils.getField(term.getClass().getSuperclass(),
		// "xstreamTrick");
		// try {
		// TODO MJCG Check if superClass is TwoArgTerm
		TwoArgTerm twoTerms = (TwoArgTerm) term;
		// List<Term> sources = (List<Term>) sourceField.get(term);
		// if (sources != null && !sources.isEmpty()) {
		RelationalAlgebra leftSource = parseOperation(schema, twoTerms.getInputTerm1());
		RelationalAlgebra rightSource = parseOperation(schema, twoTerms.getInputTerm2());
		bo = new BinaryOperation(null, leftSource, rightSource) {

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String perform() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		// }
		/*
		 * } catch (IllegalArgumentException | IllegalAccessException e) {
		 * e.printStackTrace(); }
		 */
		return bo;
	}

	private Term getSource(Term term) {
		// TODO MJCG Check if superClass is OneArgTerm
		OneArgTerm oneAgr = (OneArgTerm) term;
		return oneAgr.getInputTerm();
		/*
		 * Term source = null; Field sourceField =
		 * Utils.getField(term.getClass().getSuperclass(), "xstreamTrick"); try
		 * { List<Term> sources = (List<Term>) sourceField.get(term); if
		 * (sources != null && !sources.isEmpty()) { source = sources.get(0); }
		 * } catch (IllegalArgumentException | IllegalAccessException e) {
		 * e.printStackTrace(); } return source;
		 */
	}

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

	private List<Qualifier> parsePredicate(Term term) {
		//creating the listener
		PredicateListener listener = new PredicateListener(attributesOrder);
		//getting the predicate value from the term object
		Field predicateField = Utils.getField(term.getClass(), "predicate");
		String predicate;
		try {
			predicate = (String) predicateField.get(term);
			//init the parser
			ANTLRInputStream in = new ANTLRInputStream(predicate);
			ExprLexer lexer = new ExprLexer(in);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExprParser parser = new ExprParser(tokens);
			//parsing the predicate tree
			ParserRuleContext context = parser.predicate();
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(listener, context);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return listener.getQualifiers();
	}
}
