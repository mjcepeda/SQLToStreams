package edu.rit.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DerbyDBImpl;

public class TestDerbyDBImpl {

	static Map<String, Object> prof1 = new HashMap<String, Object>() {
		{
			put("name", "Maria");
			put("lastName", "Cepeda");
			put("age", 34);
			put("gender", "F");
		}
	};
	static Map<String, Object> prof2 = new HashMap<String, Object>() {
		{
			put("name", "Jane");
			put("lastName", "Doe");
			put("age", 48);
			put("gender", "F");
		}
	};
	static Map<String, Object> prof3 = new HashMap<String, Object>() {
		{
			put("name", "Luca");
			put("lastName", "Rossina");
			put("age", 55);
			put("gender", "M");
		}
	};

	static Map<String, Object> prof4 = new HashMap<String, Object>() {
		{
			put("name", "David");
			put("lastName", "Smith");
			put("age", 18);
			put("gender", "M");
		}
	};
	static Map<String, Object> prof5 = new HashMap<String, Object>() {
		{
			put("name", "Andrea");
			put("lastName", "Cottello");
			put("age", 55);
			put("gender", "M");
		}
	};

	static Map<String, Object> prof6 = new HashMap<String, Object>() {
		{
			put("name", "Maria");
			put("lastName", "Cepeda");
			put("age", 34);
			put("gender", "F");
		}
	};

	static Map<String, String> profTable = new HashMap<String, String>() {
		{
			put("name", "varchar(255)");
			put("lastName", "varchar(255)");
			put("age", "int");
			put("id", "int");
			put("idDept", "int");
			put("salary", "int");
		}
	};

	static Map<String, String> deptTable = new HashMap<String, String>() {
		{
			put("name", "varchar(255)");
			put("building", "varchar(255)");
			put("budget", "int");
			put("id", "int");
		}
	};

	static Map<String, String> courseTable = new HashMap<String, String>() {
		{
			put("title", "varchar(255)");
			put("credits", "int");
			put("idDept", "int");
			put("id", "int");
		}
	};

	static Map<String, String> sectionTable = new HashMap<String, String>() {
		{
			put("semester", "varchar(255)");
			put("building", "varchar(255)");
			put("room", "int");
			// put("year", "int");
			put("idCourse", "int");
			put("id", "int");
		}
	};

	static Map<String, String> teachesTable = new HashMap<String, String>() {
		{
			put("semester", "varchar(255)");
			// put("year", "int");
			put("idSection", "int");
			put("idCourse", "int");
			put("id", "int");
		}
	};

	public static final List<Map<String, Object>> professorsTemp = Stream.of(prof1, prof2, prof3, prof4, prof5, prof6)
			.collect(Collectors.toList());

	public static void main(String[] args) {
		Map<String, Map<String, String>> schema = new HashMap<>();
		schema.put("professor", profTable);
		schema.put("department", deptTable);
		schema.put("course", courseTable);
		schema.put("section", sectionTable);
		schema.put("teaches", teachesTable);

		String query = "select * from professor p, department d where p.idDept=d.id and (p.name='Maria' or d.building='1')";
		RelationalAlgebra plan = executeQuery(schema, query);

	}

	// MJCG GenericResultDescription
	public static RelationalAlgebra executeQuery(Map<String, Map<String, String>> schema, String query) {
		RelationalAlgebra plan = null;
		Database db = new DerbyDBImpl();
		db.createDB();
		schema.entrySet().forEach(t -> db.createTable(t.getKey(), t.getValue()));
		/*
		 * db.createTable("professor", profTable); db.createTable("department",
		 * deptTable); db.createTable("course", courseTable);
		 * db.createTable("section", sectionTable); db.createTable("teaches",
		 * teachesTable);
		 */
		// db.insertData("professor", professorsTemp);

		plan = db.getExecutionPlan(query);
		db.shutdown();
		db.dropDB();
		// ExecutionPlanParser.parser(plan);
		return plan;
	}

}
