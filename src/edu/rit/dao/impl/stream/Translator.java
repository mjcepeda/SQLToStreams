package edu.rit.dao.impl.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DatabaseImpl;

public class Translator {
	
	Database db = new DatabaseImpl();
	
	public void readFile(String fileName) {
		//open the file
		File file = new File(fileName);
		//creating scanner instance to read file
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()){
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
