package edu.rit.test.data;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	
	public List<Professor> professors;
	public List<Department> departments;
	public List<Course> courses;
	public List<Section> sections;
	
	public DataSet() {
		professors = new ArrayList<Professor>();
		departments = new ArrayList<Department>();
		courses = new ArrayList<Course>();
		sections = new ArrayList<>();
		fillProfessors();
		fillDepartments();
		fillSections();
		//fillCourses();
	}
	
	private void fillProfessors() {
		professors.add(new Professor("Charles", "Dawson", 34, "M", 1,1, 45000));
		professors.add(new Professor("Robert", "Alton", 44, "M", 1,2, 50000));
		professors.add(new Professor("Elizabeth", "Smith", 38, "F", 1,3, 45000));
		professors.add(new Professor("Jane", "Doe", 48, "F", 2,4, 55000));
		professors.add(new Professor("Luca", "Rossina", 55, "M", 2,5, 0));
		professors.add(new Professor("Ashwin", "Ranesh", 48, "M", 2,6, 68000));
		professors.add(new Professor("Andrea", "Mangerini", 60, "F", 1,7, 89000));
	}
	
	private void fillDepartments() {
		departments.add(new Department(1, "CSCI", "Computer Science"));
		departments.add(new Department(2, "CPET", "Computer Engineering"));
	}
	
	private void fillSections() {
		sections.add(new Section("BIO-101", "Fall", 2009, "1"));
		sections.add(new Section("BIO-301", "Summer", 2010, "1"));
		sections.add(new Section("CS-101", "Fall", 2009, "1"));
		sections.add(new Section("CS-101", "Spring", 2010, "1"));
		sections.add(new Section("CS-190", "Fall", 2009, "1"));
		sections.add(new Section("CS-190", "Spring", 2009, "1"));
		sections.add(new Section("CS-319", "Fall", 2010, "1"));
		sections.add(new Section("CS-319", "Spring", 2010, "1"));
	}

//	private void fillCourses() {
//		courses.add(new Course(1, "620", 1));
//		courses.add(new Course(2, "630", 1));
//	}
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

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}
	
}
