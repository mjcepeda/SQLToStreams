package edu.rit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.squareup.javapoet.MethodSpec;

import adipe.translate.ra.Schema;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DatabaseImpl;
import edu.rit.dao.impl.store.access.UserDTO;
import edu.rit.dao.impl.stream.CodeGenerator;
import edu.rit.dao.impl.stream.ExecutionPlanParser;
import edu.rit.test.data.DataSet;
import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import edu.rit.utils.Utils;

public class DatabaseImplTest {

	Database db = new DatabaseImpl();

	@Test
	public void test() {
		// creating dataset
		DataSet dt = new DataSet();
		// creating schema description
		UserDTO prof = new UserDTO("professor", new Professor());
		UserDTO dept = new UserDTO("department", new Department(0, null, null));
		UserDTO prof2 = new UserDTO("professor2", new Professor());
		UserDTO prof3 = new UserDTO("professor3", new Professor());
		List<UserDTO> l = new ArrayList<>();
		l.add(prof);
		l.add(dept);
		l.add(prof2);
		l.add(prof3);
		Map<String, Map<String, String>> schemaDescriptor = Utils.schemaDescriptor(l);
		// creating db schema
		Schema schema = db.createSchema(schemaDescriptor);
		//String query = "select name, lastName, code from professor p, department d where d.code='CSCI' and age > 40";
		//String query = "select name, lastName from professor p inner join department d on p.dept = d.id";
		//String query = "select name, lastName from professor p where lastName = 'Parker'";
		//String query = "select name, code from professor p, department d where d.id = p.dept";
		String query = "select p.name, p.lastName from professor p inner join department d on d.id = p.dept and code='CSCI' and age > 50";
		//String query = "select name || lastName from professor p"; //Create SelectT term
		//TODO MJCG Create a map with column types and pass this map as an argument to getExecutionPlan
		//we need this information for Select operation
		RelationalAlgebra plan = db.getExecutionPlan(query, schema);
		System.out.println(plan);
		CodeGenerator generator = new CodeGenerator();
		MethodSpec method2 = generator.createMethod2("test", getStreamCode(plan), "professor", "department");
		try {
			generator.createClass("edu.rit.test.stream", "StreamQuery", method2);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		String returnStmt = "return " + plan.getReturnVar() + ".get()";
		stmts.add(returnStmt);
		return stmts;
	}
}
