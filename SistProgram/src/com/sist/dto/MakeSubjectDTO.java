package com.sist.dto;

public class MakeSubjectDTO {
	
	private String seq; //번호
	private String subjectNum;//과목번호
	private String startDate; // 과목시작일
	private String endDate; // 과목종료일
	private String bookNum; // 교재번호
	private String bookdistristate; // 분배상태
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
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
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookdistristate() {
		return bookdistristate;
	}
	public void setBookdistristate(String bookdistristate) {
		this.bookdistristate = bookdistristate;
	}
	
	
	

}
