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
		professors.add(new Professor("Maria", "cepeda", 34, "F", 1,1));
		professors.add(new Professor("Jane", "Doe", 48, "F", 2,2));
		professors.add(new Professor("Luca", "Rossina", 55, "M", 2,3));
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
