package com.sist.dto;
//학생별 성적
public class StudentScoreListDTO {
	
	private String seq; //번호
	private String studentName;//학생명
	private String scoreSeq;//성적번호
	private String attendance;//출결점수
	private String practice;//실기점수
	private String writer;//필기점수
	private String registate;//수강상태
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getScoreSeq() {
		return scoreSeq;
	}
	public void setScoreSeq(String scoreSeq) {
		this.scoreSeq = scoreSeq;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegistate() {
		return registate;
	}
	public void setRegistate(String registate) {
		this.registate = registate;
	}
	

}
