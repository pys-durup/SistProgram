package com.sist.dto;

public class TeacherEvaluationDTO {
	
	private String seq;//번호
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
	
	

}
