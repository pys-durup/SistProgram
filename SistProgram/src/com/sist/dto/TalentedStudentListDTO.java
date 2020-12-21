package com.sist.dto;

/**
 * 인재목록 조회시 인재정보를 담기위한 객체
 * @author YSPark
 *
 */
public class TalentedStudentListDTO {
	private String seq;
	private String name;
	private String tel;
	private String portfolio;
	private String reason;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
