package edu.rit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import com.squareup.javapoet.MethodSpec;

import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.relational.Join;
import edu.rit.dao.impl.relational.Projection;
import edu.rit.dao.impl.relational.Select;
import edu.rit.dao.impl.relational.TableAccess;
import edu.rit.dao.impl.store.access.ColumnDescriptor;
import edu.rit.dao.impl.store.access.Operator;
import edu.rit.dao.impl.store.access.Qualifier;
import edu.rit.dao.impl.stream.CodeGenerator;
import edu.rit.dao.impl.stream.ExecutionPlanParser;
import edu.rit.test.data.DataSet;
import edu.rit.test.stream.StreamQuery;

public class CodeGeneratorTest {

	//@Ignore
	@Test
	/**
	 * Testing the generated query using Stream API
	 */
	public void testQuery() {
		StreamQuery st = new StreamQuery();
		CodeGenerator cg = new CodeGenerator();
		// create the data
		DataSet data = new DataSet();
		// get user input parameters and transform that to List<Map<String,
		// Object>>
		List<Map<String, Object>> mapProfessors = cg.objectToMaps(data.professors);
		List<Map<String, Object>> mapDepartments = cg.objectToMaps(data.departments);
		// execute the query
		System.out.println("Executing query 1: select * from professors where age >= 34 and gender='M'");
		Stream<Map<String,Object>> st1= st.testSelect(mapProfessors);
		st1.forEach(System.out::println);
		System.out.println("Executing query 2: select name, lastName from professors, departments where dept = departments.id and departments.code='CSCI'");
		Stream<Map<String,Object>> st2= st.testProjection(mapProfessors, mapDepartments);
		st2.forEach(System.out::println);
	}

	//@Test
	@Ignore
	public void testCodeGeneration() {
		// create the data
		DataSet data = new DataSet();
		// QUERY : Select * from professors where age >= 34 and gender='M'
		// create the query execution plan
		RelationalAlgebra planQuery1 = createPlan();
		// get the stream code for the first query
		List<String> query1 = getStreamCode(planQuery1);
		// QUERY: Select name, lastName from professors, department where dept =
		// department.id and department.code='CSCI'
		// create the execution plan query
		RelationalAlgebra planQuery2 = createPlanProjection();
		// get the stream code for the second query
		List<String> query2 = getStreamCode(planQuery2);
		// get user input parameters
		List<List<?>> inputParams = new ArrayList<>();
		inputParams.add(data.getProfessors());

		CodeGenerator generator = new CodeGenerator();
		MethodSpec method1 = generator.createMethod2("testSelect", query1, "professors");
		MethodSpec method2 = generator.createMethod2("testProjection", query2, 
				"professors", "departments");
		try {
			generator.createClass("edu.rit.test.stream", "StreamQuery", method1, method2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create query execution plan for Select * from professors where age >=34
	 * and gender ='F'
	 * 
	 * @return
	 */
	private RelationalAlgebra createPlan() {
		Qualifier q = new Qualifier();
		ColumnDescriptor column = new ColumnDescriptor();
		column.setName("age");
		q.setParameterValue(34);
		q.setColumnData(column);
		q.setOperator(Operator.GREQ);
		Qualifier q2 = new Qualifier();
		ColumnDescriptor column2 = new ColumnDescriptor();
		column2.setName("gender");
		q2.setParameterValue("F");
		q2.setColumnData(column2);
		q2.setOperator(Operator.EQUALS);
		List<Qualifier> l = new ArrayList<>();
		l.add(q);
		l.add(q2);
		Select cp = new Select("p", l, new TableAccess("professorsStream", null, "professors"));
		return cp;
	}

	/**
	 * Create query execution plan for Select name, lastName from professors,
	 * department where dept = department.id and department.code='CSCI'
	 * 
	 * @return
	 */
	private RelationalAlgebra createPlanProjection() {
		// creating select plan
		Qualifier q = new Qualifier();
		ColumnDescriptor columnSelect = new ColumnDescriptor();
		columnSelect.setName("code");
		q.setParameterValue("CSCI");
		q.setColumnData(columnSelect);
		q.setOperator(Operator.EQUALS);
		List<Qualifier> l = new ArrayList<>();
		l.add(q);
		Select selectPlan = new Select("s", l, new TableAccess("departmentsStream", null, "departments"));

		// creating join plan
		Qualifier q2 = new Qualifier();
		ColumnDescriptor column = new ColumnDescriptor();
		column.setName("dept");
		q2.setParameterValue("id");
		q2.setColumnData(column);
		List<Qualifier> l2 = new ArrayList<>();
		l2.add(q2);
		Join join = new Join("pd",new TableAccess("professorsStream", null, "professors"), selectPlan, l2);

		// creating projection plan
		List<String> columnNames = new ArrayList<>();
		columnNames.add("name");
		columnNames.add("lastName");
		String tableName = "professor";
		Projection p = new Projection("p", columnNames, join);
		return p;
	}

	/**
	 * Translate the query execution plan into Stream Java code
	 * 
	 * @param plan
	 * @return
	 */
	private static List<String> getStreamCode(RelationalAlgebra plan) {
		ExecutionPlanParser parser = new ExecutionPlanParser();
		List<String> stmts = new ArrayList<>();
		parser.parser(plan, stmts);
		Collections.reverse(stmts);
		String returnStmt = "return " + plan.getReturnVar();
		stmts.add(returnStmt);
		return stmts;
	}
}
