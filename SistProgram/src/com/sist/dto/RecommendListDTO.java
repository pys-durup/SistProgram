package com.sist.dto;

/**
 * 인재 추천 - 추천현황 조회목록에서 사용되는 클래스 입니다
 * @author YSPark
 *
 */
public class RecommendListDTO {
	
	private String seq;
	private String studentName;
	private String companyName;
	private String recoDate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRecoDate() {
		return recoDate;
	}
	public void setRecoDate(String recoDate) {
		this.recoDate = recoDate;
	}
	
	

}
