package edu.rit.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import adipe.translate.ra.Schema;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DatabaseImpl;
import edu.rit.dao.impl.store.access.UserDTO;
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
		//String query = "select * from professor p inner join department d on p.dept = d.id";
		//String query = "select name, lastName from professor p";
		//String query = "select name, code from professor p, department d where d.id = p.dept";
		String query = "select p.name, p.lastName from professor p inner join professor2 d on d.lastName = p.lastName and d.name <> p.name and p.age>30";
		RelationalAlgebra plan = db.getExecutionPlan(query, schema);
		System.out.println(plan);
	}

}
