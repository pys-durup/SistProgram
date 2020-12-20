package com.sist.dto;

public class JobConsultationDTO {
	
	private String seq; //번호
	private String content; //상담내용
	private String regiNum; //수강번호
	private String consDate; //상담날짜
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegiNum() {
		return regiNum;
	}
	public void setRegiNum(String regiNum) {
		this.regiNum = regiNum;
	}
	public String getConsDate() {
		return consDate;
	}
	public void setConsDate(String consDate) {
		this.consDate = consDate;
	}
	
	

}
