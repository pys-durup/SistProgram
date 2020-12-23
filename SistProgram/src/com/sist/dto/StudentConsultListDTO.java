package com.sist.dto;

public class StudentConsultListDTO {

	private String seq; //번호
	private String consultDate; //상담날짜
	private String sname; // 교육생 이름
	private String subjectSeq; //과목번호
	private String subjectName; //과목명
	private String courseDate; //과정기간
	private String consultReason; // 상담사유
	private String consultContent; //상담내용
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}
	public String getConsultReason() {
		return consultReason;
	}
	public void setConsultReason(String consultReason) {
		this.consultReason = consultReason;
	}
	public String getConsultContent() {
		return consultContent;
	}
	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}
	
	
	
	
}
