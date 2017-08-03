package edu.rit.test.data;

public class Section {
	
	private String courseId;
	//private int professorId;
	private String secId;
	private String semester;
	private int year;
	
	public Section() {}
	
	public Section(String courseId, String semester,int year, String secId) {
		this.courseId = courseId;
		//this.professorId = professorId;
		this.semester = semester;
		this.year = year;
		this.secId = secId;
	}
	
	public String toString() {
		return "CourseID: " + courseId + ", semester: " + semester + ", year: " + year;
	}
	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
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
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the secId
	 */
	public String getSecId() {
		return secId;
	}
	/**
	 * @param secId the secId to set
	 */
	public void setSecId(String secId) {
		this.secId = secId;
	}
	
}
