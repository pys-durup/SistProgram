package com.sist.dto;

public class TeacherEvaluationDTO {
	
	private String seq;//번호
	private String name; // 학생이름(이 한 컬럼만 필요하기에 추가된 필드-> 프로시저 등 다른 것 사용할 때 해당 프로시저에서 정한 열이름 꼭 확인할 것)
	private String materials;//자료만족도
	private String communication;//소통만족도
	private String jobPreparing;//취업준비만족도
	private String divisionTime;//시간분배만족도
	private String totalPoint;//토탈평점
	private String completNum;//수료번호
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	public String getJobPreparing() {
		return jobPreparing;
	}
	public void setJobPreparing(String jobPreparing) {
		this.jobPreparing = jobPreparing;
	}
	public String getDivisionTime() {
		return divisionTime;
	}
	public void setDivisionTime(String divisionTime) {
		this.divisionTime = divisionTime;
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	public String getCompletNum() {
		return completNum;
	}
	public void setCompletNum(String completNum) {
		this.completNum = completNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
