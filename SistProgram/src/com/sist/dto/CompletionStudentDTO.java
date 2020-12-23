package com.sist.dto;

public class CompletionStudentDTO {
	
	private String studentNum; //학생번호
	private String studentName; //학생이름
	private String teacherName; //교사이름
	private String courseNum; //과정번호
	private String courseName; //과정이름
	private String startDate; //개강일
	private String endDate; //종강일
	private String registate; //수강상태
	
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegistate() {
		return registate;
	}
	public void setRegistate(String registate) {
		this.registate = registate;
	}

}
