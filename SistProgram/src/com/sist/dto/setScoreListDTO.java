package com.sist.dto;

public class setScoreListDTO {
	//담당과목별 배점리스트 조회
	
	
	private String subjectNum;//과목번호
	private String subjectName;//과목명
	private String bookName;//책이름
	private String attendance;//출석배점
	private String write;//필기배점
	private String practice;//실기배점
	private String subjectStartdate;//과목개강일
	private String subjectEnddate;	//과목종강일
	private String courseName;//과정명
	private String courseStartdate;//과정개강일
	private String courseEnddate;//과정종강일
	private String roomName;//강의실명
	private String scoreStatus; //성적등록여부 
	
		
	public String getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getSubjectStartdate() {
		return subjectStartdate;
	}
	public void setSubjectStartdate(String subjectStartdate) {
		this.subjectStartdate = subjectStartdate;
	}
	public String getSubjectEnddate() {
		return subjectEnddate;
	}
	public void setSubjectEnddate(String subjectEnddate) {
		this.subjectEnddate = subjectEnddate;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseStartdate() {
		return courseStartdate;
	}
	public void setCourseStartdate(String courseStartdate) {
		this.courseStartdate = courseStartdate;
	}
	public String getCourseEnddate() {
		return courseEnddate;
	}
	public void setCourseEnddate(String courseEnddate) {
		this.courseEnddate = courseEnddate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getScoreStatus() {
		return scoreStatus;
	}
	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	
	
}
