package edu.rit.test.data;

public class Department {

	private int id;
	private String code;
	private String deptName;
	
	public Department() {}
	
	public Department(int id, String code, String name) {
		this.id = id;
		this.code = code;
		this.deptName= name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	
}
