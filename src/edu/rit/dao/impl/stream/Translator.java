package edu.rit.dao.impl.stream;

import java.util.List;

import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DatabaseImpl;

public class Translator {
	
	Database db = new DatabaseImpl();
	
	public void readFile(String fileName) {
		//open the file
		//read the query, input params and output param
		//
	}

	public void translate() {
		
	}
	
	public RelationalAlgebra getExecutionPlan(String query, List<?> objs){
		RelationalAlgebra root=null;
		
		return root;
	}
}
