package com.sist.dto;

public class ScoreAndSubjectDTO {
	
	private String seq; //번호
	private String attendance; //출결점수
	private String practice; //실기점수
	private String write; //필기점수 
	private String makeSubjectNum; //개설과목번호
	private String regiNum; //수강번호
	private String name; // tblSubject의 과목 name
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getMakeSubjectNum() {
		return makeSubjectNum;
	}
	public void setMakeSubjectNum(String makeSubjectNum) {
		this.makeSubjectNum = makeSubjectNum;
	}
	public String getRegiNum() {
		return regiNum;
	}
	public void setRegiNum(String regiNum) {
		this.regiNum = regiNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
