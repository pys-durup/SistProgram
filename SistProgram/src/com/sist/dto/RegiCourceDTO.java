package com.sist.dto;

public class RegiCourceDTO {
	
	private String seq; //번호
	private String studentNum;//교육생번호
	private String createdCourceNum;//개설과정번호
	private String regiStateNum;//수강상태번호
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
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

}
