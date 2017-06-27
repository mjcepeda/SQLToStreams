package edu.rit.dao.impl.stream;

import java.util.IdentityHashMap;
import java.util.List;

import edu.rit.dao.iapi.relational.BinaryOperation;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.iapi.relational.UnaryOperation;

public class ExecutionPlanParser {

	public enum Classes {
		UnaryClass(UnaryOperation.class), BinaryClass(BinaryOperation.class),
		// etc...
		UNKNWON(null);

		// need a wrapper class to avoid compilation problem
		// with referring static enum field within an initializer
		private static class Holder {
			public static final IdentityHashMap<Class<?>, Classes> map = new IdentityHashMap<>();
		}

		Classes(Class<?> targetClass) {
			Holder.map.put(targetClass, this);
		}

		public static Classes fromClass(Class<?> cls) {
			Classes c = Holder.map.get(cls);
			return c != null ? c : UNKNWON;
		}
	}

	public void parser(RelationalAlgebra plan, List<String> stmts) {
		Class<? extends RelationalAlgebra> c = plan.getClass();
		Class<?> superClass = c.getSuperclass();
//		StringBuilder code = new StringBuilder();
		stmts.add(plan.perform());
		switch (Classes.fromClass(superClass)) {
		case UnaryClass:
			UnaryOperation unaryOP = (UnaryOperation) plan;
			if (unaryOP.getSource() != null) {
				parser(unaryOP.getSource(), stmts);
			}
			break;
		case BinaryClass:
			BinaryOperation binaryOP = (BinaryOperation) plan;

			if (binaryOP.getLeftSource() != null) {
				parser(binaryOP.getLeftSource(), stmts);
			}
			if (binaryOP.getRightSource() != null) {
				parser(binaryOP.getRightSource(), stmts);
			}
			break;
		default:
			// TODO MJCG Handle unknown class
			break;
		}
	}

	// TODO MJCG Change this method to add maybe the type of Collector for
	// generating
	public String generateCollector(boolean distinct) {
		String code = ".collect(Collectors.toList());";
		if (distinct) {
			code = ".collect(Collectors.toSet());";
		}
		return code;
	}
}
