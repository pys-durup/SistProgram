package com.sist.dto;

public class InterviewResultDTO {
	
	private String seq;
	private String interviewNum;
	private String result;
	//추가된 필드
	private String rName; //교육생명
	private String cSeq; //개설과정번호
	private String cName; //개설과정명
	
	
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getcSeq() {
		return cSeq;
	}
	public void setcSeq(String cSeq) {
		this.cSeq = cSeq;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getInterviewNum() {
		return interviewNum;
	}
	public void setInterviewNum(String interviewNum) {
		this.interviewNum = interviewNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
