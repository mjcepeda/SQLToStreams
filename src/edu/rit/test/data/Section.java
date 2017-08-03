package edu.rit.test.data;

/**
 * The Class Section.
 */
public class Section {
	
	/** The course id. */
	private String courseId;

	//private int professorId;

	/** The sec id. */
	private String secId;
	
	/** The semester. */
	private String semester;
	
	/** The year. */
	private int year;
	
	/**
	 * Instantiates a new section.
	 */
	public Section() {}
	
	/**
	 * Instantiates a new section.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param year the year
	 * @param secId the sec id
	 */
	public Section(String courseId, String semester,int year, String secId) {
		this.courseId = courseId;
		//this.professorId = professorId;
		this.semester = semester;
		this.year = year;
		this.secId = secId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "CourseID: " + courseId + ", semester: " + semester + ", year: " + year;
	}
	
	/**
	 * Gets the course id.
	 *
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	
	/**
	 * Sets the course id.
	 *
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
//	/**
//	 * @return the professorId
//	 */
//	public int getProfessorId() {
//		return professorId;
//	}
//	/**
//	 * @param professorId the professorId to set
//	 */
//	public void setProfessorId(int professorId) {
//		this.professorId = professorId;
//	}
	/**
 * Gets the semester.
 *
 * @return the semester
 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * Sets the semester.
	 *
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Sets the year.
	 *
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the sec id.
	 *
	 * @return the secId
	 */
	public String getSecId() {
		return secId;
	}
	
	/**
	 * Sets the sec id.
	 *
	 * @param secId the secId to set
	 */
	public void setSecId(String secId) {
		this.secId = secId;
	}
	
}
