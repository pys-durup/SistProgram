package com.sist.dto;

public class ReserveStudentDTO {

	
	private String seq;
	private String name;
	private String jumin;
	private String tel;
	private String address;
	private String field;
	private String knowledge;
	//추가된 필드
	private String cName; //수강면접신청한 과정명
	private String createdCourceNum; //수강면접신청한 과정번호
	private String interviewDate; // 면접일자
	private String startDate; // 과정시작일
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getCreatedCourceNum() {
		return createdCourceNum;
	}
	public void setCreatedCourceNum(String createdCourceNum) {
		this.createdCourceNum = createdCourceNum;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}
