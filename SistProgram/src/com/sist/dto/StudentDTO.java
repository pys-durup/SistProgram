package com.sist.dto;

public class StudentDTO {

	private String seq;	// 교육생 시퀀스
	private String name; // 교육생 이름
	private String jumin; // 교육생 주민등록번호
	private String tel; // 교육생 전화번호
	private String regdate; // 교육생등록일
	
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

}
