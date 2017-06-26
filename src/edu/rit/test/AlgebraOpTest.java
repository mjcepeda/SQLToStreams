package edu.rit.test;

import java.util.ArrayList;
import java.util.List;

import edu.rit.dao.impl.relational.CartesianProduct;
import edu.rit.dao.impl.relational.Difference;
import edu.rit.dao.impl.relational.Intersect;
import edu.rit.dao.impl.relational.Join;
import edu.rit.dao.impl.relational.Projection;
import edu.rit.dao.impl.relational.Select;
import edu.rit.dao.impl.relational.Union;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;
import edu.rit.dao.impl.stream.ExecutionPlanParser;

public class AlgebraOpTest {

	public static void main(String args[]) {
		//projection();
		//union();
		//intersect();
		//difference();
		//cartesianProduct();
		//join();
		select();
	}
	
	private static void projection() {
		List<String> columnNames = new ArrayList<>();
		columnNames.add("name");
		columnNames.add("lastName");
		String tableName="professor";
		Union union = new Union("professor", "teacher", null, null);
		Projection p = new Projection(tableName, columnNames, union);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(p);
		System.out.println(code.toString());
	}
	
	private static void union() {
		Union p = new Union("professor", "teacher", null, null);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(p);
		System.out.println(code.toString());
	}
	
	private static void intersect() {
		Intersect p = new Intersect("professor", "teacher", null, null);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(p);
		System.out.println(code.toString());
	}
	
	private static void difference() {
		Difference p = new Difference("professor", "teacher", null, null);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(p);
		System.out.println(code.toString());
	}
	
	private static void cartesianProduct() {
		CartesianProduct cp = new CartesianProduct("professorsTemp", "deptTemp",  null, null);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(cp);
		System.out.println(code.toString());
	}
	
	private static void join() {
		Qualifier q = new Qualifier();
		ColumnDescriptor column = new ColumnDescriptor();
		column.setName("dept");
		q.setParameterValue("code");
		q.setColumnData(column);
		List<Qualifier> l = new ArrayList<>();
		l.add(q);
		Join cp = new Join("professorsTemp", "deptTemp",  null, null, l);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(cp);
		System.out.println(code.toString());
	}

	private static void select() {
		Qualifier q = new Qualifier();
		ColumnDescriptor column = new ColumnDescriptor();
		column.setName("age");
		q.setParameterValue(34);
		q.setColumnData(column);
		q.setOperator(Operator.GREQ);
		Qualifier q2 = new Qualifier();
		ColumnDescriptor column2 = new ColumnDescriptor();
		column2.setName("gender");
		q2.setParameterValue("M");
		q2.setColumnData(column2);
		q2.setOperator(Operator.EQUALS);
		List<Qualifier> l = new ArrayList<>();
		l.add(q);
		l.add(q2);
		Select cp = new Select("professorsTemp", l, null);
		ExecutionPlanParser parser = new ExecutionPlanParser();
		String code = parser.parser(cp);
		System.out.println(code.toString());
	}
}
