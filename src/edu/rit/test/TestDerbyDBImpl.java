package edu.rit.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import adipe.translate.Queries;
import adipe.translate.Schemas;
import adipe.translate.TranslationException;
import adipe.translate.ra.Schema;
import adipe.translate.ra.Terms;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DerbyDBImpl;
import edu.rit.dao.impl.stream.CodeGenerator;
import edu.rit.test.data.DataSet;
import ra.Term;

public class TestDerbyDBImpl {

	
	static Map<String, String> profTable = new HashMap<String, String>() {
		{
			put("name", "varchar(255)");
			put("lastName", "varchar(255)");
			put("age", "int");
			put("id", "int");
			put("dept", "int");
			put("salary", "int");
			put("gender", "varchar(1)");
		}
	};

	static Map<String, String> deptTable = new HashMap<String, String>() {
		{
			put("code", "varchar(255)");
			put("deptName", "varchar(255)");
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

	

	public static void main(String[] args) {
		Map<String, Map<String, String>> schema = new HashMap<>();
		schema.put("professor", profTable);
		schema.put("department", deptTable);
		schema.put("course", courseTable);
		schema.put("section", sectionTable);
		schema.put("teaches", teachesTable);

		String query = "select * from professor p inner join department d on p.dept=d.id";// and p.name=d.code";
		RelationalAlgebra plan = executeQuery(schema, query);

	}

	// MJCG GenericResultDescription
	public static RelationalAlgebra executeQuery(Map<String, Map<String, String>> schema, String query) {
		RelationalAlgebra plan = null;
		DerbyDBImpl db = new DerbyDBImpl();
		db.createDB();
		schema.entrySet().forEach(t -> db.createTable(t.getKey(), t.getValue()));
		StringBuilder sb = new StringBuilder();
		schema.entrySet().forEach(t -> sb.append(createTable(t.getKey(), t.getValue())));
		
		//schema.entrySet().forEach(t -> db.insertData(t.getKey(), t.getValue()));
		DataSet dt = new DataSet();
		CodeGenerator cg = new CodeGenerator();
		db.insertData("professor", cg.objectToMaps(dt.getProfessors()));
		db.insertData("department", cg.objectToMaps(dt.getDepartments()));

		Term translated;
		try {
			Schema sch = Schemas.fromDDL(sb.toString());
			translated = Queries.getRaOf(sch, query);
			String type = translated.getClass().getAnnotation(com.thoughtworks.xstream.annotations.XStreamAlias.class).value();
			System.out.println(type);
			System.out.println(Terms.indent(translated)); 
		} catch (ParseCancellationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TranslationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		plan = db.getExecutionPlan(query);
		db.shutdown();
		db.dropDB();
		// ExecutionPlanParser.parser(plan);
		return plan;
	}
	
	private static String createTable(String tableName, Map<String, String> columnsDescMap) {
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
}
