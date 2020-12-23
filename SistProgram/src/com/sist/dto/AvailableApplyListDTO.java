package com.sist.dto;

public class AvailableApplyListDTO {
	//예비교육생이 면접 신청 가능한 과목정보를 담기위한 DTO
	private String cseq; //개설과정번호
	private String cname; //개설과정명
	private String cstartdate; //과정 시작일
	private String cenddate; //개설과정 종료일
	private String cpurpose; //개설과정 목적
	private String tname; //과정담당 교사명
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCstartdate() {
		return cstartdate;
	}
	public void setCstartdate(String cstartdate) {
		this.cstartdate = cstartdate;
	}
	public String getCenddate() {
		return cenddate;
	}
	public void setCenddate(String cenddate) {
		this.cenddate = cenddate;
	}
	public String getCpurpose() {
		return cpurpose;
	}
	public void setCpurpose(String cpurpose) {
		this.cpurpose = cpurpose;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
	
}
