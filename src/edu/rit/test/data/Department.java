package edu.rit.test.data;

/**
 * The Class Department.
 */
public class Department {

	/** The id. */
	private int id;
	
	/** The code. */
	private String code;
	
	/** The dept name. */
	private String deptName;
	
	/**
	 * Instantiates a new department.
	 */
	public Department() {}
	
	/**
	 * Instantiates a new department.
	 *
	 * @param id the id
	 * @param code the code
	 * @param name the name
	 */
	public Department(int id, String code, String name) {
		this.id = id;
		this.code = code;
		this.deptName= name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "name: " + deptName + " , code: " + code;
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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the dept name.
	 *
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	
}
