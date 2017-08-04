package edu.rit.dao.impl.stream;

import java.util.IdentityHashMap;
import java.util.List;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;

/**
 * The Class ExecutionPlanParser.
 */
public class ExecutionPlanParser {

	/**
	 * The Enum Classes.
	 */
	public enum Classes {

		/** The Unary class. */
		UnaryClass(UnaryOperation.class),
		/** The Binary class. */
		BinaryClass(BinaryOperation.class),

		/** The unknwon. */
		UNKNWON(null);

		// need a wrapper class to avoid compilation problem
		// with referring static enum field within an initializer
		/**
		 * The Class Holder.
		 */
		private static class Holder {

			/** The Constant map. */
			public static final IdentityHashMap<Class<?>, Classes> map = new IdentityHashMap<>();
		}

		/**
		 * Instantiates a new classes.
		 *
		 * @param targetClass
		 *            the target class
		 */
		Classes(Class<?> targetClass) {
			Holder.map.put(targetClass, this);
		}

		/**
		 * From class.
		 *
		 * @param cls
		 *            the cls
		 * @return the classes
		 */
		public static Classes fromClass(Class<?> cls) {
			Classes c = Holder.map.get(cls);
			return c != null ? c : UNKNWON;
		}
	}

	/**
	 * Parser.
	 *
	 * @param plan
	 *            the plan
	 * @param stmts
	 *            the stmts
	 */
	public void parser(RelationalAlgebra plan, List<String> stmts) {
		Class<? extends RelationalAlgebra> c = plan.getClass();
		Class<?> superClass = c.getSuperclass();
		//adding to the list the java source code with the stream operations
		//associated with this relational algebra operation
		stmts.add(plan.perform());
		switch (Classes.fromClass(superClass)) {
		case UnaryClass:
			//parsing the source relational algebra operation
			UnaryOperation unaryOP = (UnaryOperation) plan;
			if (unaryOP.getSource() != null) {
				parser(unaryOP.getSource(), stmts);
			}
			break;
		case BinaryClass:
			BinaryOperation binaryOP = (BinaryOperation) plan;
			//binary operations have two sources
			//parsing left source relational algebra operation
			if (binaryOP.getLeftSource() != null) {
				parser(binaryOP.getLeftSource(), stmts);
			}
			//parsing right source
			if (binaryOP.getRightSource() != null) {
				parser(binaryOP.getRightSource(), stmts);
			}
			break;
		default:
			break;
		}
	}
}
