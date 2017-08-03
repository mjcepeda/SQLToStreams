package edu.rit.test.data;

/**
 * The Class Professor.
 */
public class Professor {

	/** The id. */
	public int id;
	
	/** The name. */
	public String name;
	
	/** The last name. */
	public String lastName;
	
	/** The age. */
	public int age;
	
	/** The gender. */
	public String gender; // M - male, F - female
	
	/** The salary. */
	public Integer salary;
	
	/** The dept. */
	public int dept;
	
	/**
	 * Instantiates a new professor.
	 */
	public Professor(){}
	
	/**
	 * Instantiates a new professor.
	 *
	 * @param name the name
	 * @param lastName the last name
	 * @param dept the dept
	 * @param id the id
	 * @param salary the salary
	 */
	public Professor(String name, String lastName, int dept, int id, Integer salary) {
		this.name = name;
		this.lastName = lastName;
		this.dept= dept;
		this.id = id;
		this.salary=salary;
	}
	
	
	/**
	 * Instantiates a new professor.
	 *
	 * @param name the name
	 * @param lastName the last name
	 * @param age the age
	 * @param gender the gender
	 * @param dept the dept
	 * @param id the id
	 * @param salary the salary
	 */
	public Professor(String name, String lastName, int age, String gender, int dept, int id, Integer salary) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.dept= dept;
		this.id = id;
		this.salary=salary;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Id: " + id + ", Name: " + name + " Last Name: " + lastName;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}
	
	/**
	 * Sets the salary.
	 *
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/**
	 * Gets the dept.
	 *
	 * @return the dept
	 */
	public int getDept() {
		return dept;
	}

	/**
	 * Sets the dept.
	 *
	 * @param dept the dept to set
	 */
	public void setDept(int dept) {
		this.dept = dept;
	}
}
