package com.sist.dto;

public class CourseStudentListDTO {
	

	private String seq;//번호
	private String name;//학생이름
	private String jumin; //주민번호
	private String tel;//전화번호
	private String regdate;//등록일자
	private String registate;//수강상태
	
	
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
	public String getRegistate() {
		return registate;
	}
	public void setRegistate(String registate) {
		this.registate = registate;
	}
	

}
