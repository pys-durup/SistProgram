package com.sist.dto;

public class TeacherScheduleDTO {
	


	private String courseNum; //번호
	private String teacherName; // 과정교사명
	private String courseName; // 과정명
	private String subjectNum; // 과목번호
	private String Subject; // 과목명
	private String book; // 책이름
	private String room; // 강의실
	private String startDate; // 개강일
	private String endDate; // 종강일
	private String totalStudent; // 수강생수
	private String courseStatus; // 수강상태
	
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
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
	public String getTotalStudent() {
		return totalStudent;
	}
	public void setTotalStudent(String totalStudent) {
		this.totalStudent = totalStudent;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	
	
	
	
	

}
