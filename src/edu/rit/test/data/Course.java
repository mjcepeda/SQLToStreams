package edu.rit.test.data;

/**
 * The Class Course.
 */
public class Course {
	
	/** The id. */
	private int id;
	
	/** The title. */
	private String title;
	
	/** The dept name. */
	private String dept_name;
	
	/** The credits. */
	private int credits;
	
	/**
	 * Instantiates a new course.
	 */
	public Course(){}
	
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the dept name.
	 *
	 * @return the dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}

	/**
	 * Sets the dept name.
	 *
	 * @param dept_name the dept_name to set
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	/**
	 * Gets the credits.
	 *
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credits.
	 *
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

}
