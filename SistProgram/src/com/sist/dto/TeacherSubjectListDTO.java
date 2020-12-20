package com.sist.dto;

public class TeacherSubjectListDTO {

	private String seq;	// 과목목록의 시퀀스
	private String teachernum; // 선생님 번호
	private String subjectnum; // 과목 번호
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTeachernum() {
		return teachernum;
	}
	public void setTeachernum(String teachernum) {
		this.teachernum = teachernum;
	}
	public String getSubjectnum() {
		return subjectnum;
	}
	public void setSubjectnum(String subjectnum) {
		this.subjectnum = subjectnum;
	}
}
