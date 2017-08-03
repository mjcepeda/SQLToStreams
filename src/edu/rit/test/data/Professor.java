package edu.rit.test.data;

public class Professor {

	public int id;
	public String name;
	public String lastName;
	public int age;
	public String gender; // M - male, F - female
	public Integer salary;
	public int dept;
	
	public Professor(){}
	
	public Professor(String name, String lastName, int dept, int id, Integer salary) {
		this.name = name;
		this.lastName = lastName;
		this.dept= dept;
		this.id = id;
		this.salary=salary;
	}
	
	
	public Professor(String name, String lastName, int age, String gender, int dept, int id, Integer salary) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.dept= dept;
		this.id = id;
		this.salary=salary;
	}
	
	public String toString() {
		return "Id: " + id + ", Name: " + name + " Last Name: " + lastName;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/**
	 * @return the dept
	 */
	public int getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(int dept) {
		this.dept = dept;
	}
}
