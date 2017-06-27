package edu.rit.test.data;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	
	public List<Professor> professors;
	public List<Department> departments;
	
	public DataSet() {
		professors = new ArrayList<Professor>();
		departments = new ArrayList<Department>();
		fillProfessors();
		fillDepartments();
	}
	
	private void fillProfessors() {
		professors.add(new Professor("Charles", "Dawson", 34, "M", 1,1));
		professors.add(new Professor("Robert", "Alton", 44, "M", 1,2));
		professors.add(new Professor("Elizabeth", "Smith", 38, "F", 1,3));
		professors.add(new Professor("Jane", "Doe", 48, "F", 2,4));
		professors.add(new Professor("Luca", "Rossina", 55, "M", 2,5));
		professors.add(new Professor("Ashwin", "Ranesh", 48, "M", 2,6));
		professors.add(new Professor("Andrea", "Mangerini", 60, "F", 1,7));
	}
	
	private void fillDepartments() {
		departments.add(new Department(1, "CSCI", "Computer Science"));
		departments.add(new Department(2, "CPET", "Computer Engineering"));
	}

	/**
	 * @return the professors
	 */
	public List<Professor> getProfessors() {
		return professors;
	}

	/**
	 * @return the departments
	 */
	public List<Department> getDepartments() {
		return departments;
	}
	
	
}
