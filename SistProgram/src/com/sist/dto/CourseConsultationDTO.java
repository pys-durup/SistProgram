package com.sist.dto;

public class CourseConsultationDTO {

	
	private String seq;
	private String consultDate;
	private String teacherNum;	
	private String content;
	private String makeSubjectNum;
	private String resonNum;
	private String regiNum;
	private String reason; //상담 사유 테이블 필드 1개 추가.  
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getConsultDate() {
		return consultDate;
	}
	public void setConsultDate(String consultDate) {
		this.consultDate = consultDate;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMakeSubjectNum() {
		return makeSubjectNum;
	}
	public void setMakeSubjectNum(String makeSubjectNum) {
		this.makeSubjectNum = makeSubjectNum;
	}
	public String getResonNum() {
		return resonNum;
	}
	public void setResonNum(String resonNum) {
		this.resonNum = resonNum;
	}
	public String getRegiNum() {
		return regiNum;
	}
	public void setRegiNum(String regiNum) {
		this.regiNum = regiNum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
