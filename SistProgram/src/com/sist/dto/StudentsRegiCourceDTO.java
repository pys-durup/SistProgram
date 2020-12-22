package com.sist.dto;

public class StudentsRegiCourceDTO { //교육생, 수강, 수강상태 테이블 
	//StudentDAO에서 수강번호, 수강상태를 얻어야 할 때 사용
	private String seq; //교육생번호
	private String name; //교육생이름
	private String jumin;  //교육생주민번호
	private String tel; //교육생 연락처 
	private String regdate; //교육생 등록일
	private String rSeq;  //수강번호
	private String createdCourceNum; //개설과정번호
	private String regiStateNum; //수강상태번호
	private String regiState; //수강상태 
	
	
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
	public String getrSeq() {
		return rSeq;
	}
	public void setrSeq(String rSeq) {
		this.rSeq = rSeq;
	}
	public String getCreatedCourceNum() {
		return createdCourceNum;
	}
	public void setCreatedCourceNum(String createdCourceNum) {
		this.createdCourceNum = createdCourceNum;
	}
	public String getRegiStateNum() {
		return regiStateNum;
	}
	public void setRegiStateNum(String regiStateNum) {
		this.regiStateNum = regiStateNum;
	}
	public String getRegiState() {
		return regiState;
	}
	public void setRegiState(String regiState) {
		this.regiState = regiState;
	}
	
}
