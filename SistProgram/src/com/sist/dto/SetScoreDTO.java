package com.sist.dto;

public class SetScoreDTO {
	
	private String seq; //번호
	private String attendance; //출결배점
	private String write; //필기배점
	private String practice; //실기배점
	private String makeSubjectNum; //개설과목번호
	
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
	public String getMakeSubjectNum() {
		return makeSubjectNum;
	}
	public void setMakeSubjectNum(String makeSubjectNum) {
		this.makeSubjectNum = makeSubjectNum;
	}
	
}
