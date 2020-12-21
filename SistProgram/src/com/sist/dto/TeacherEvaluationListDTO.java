package com.sist.dto;

public class TeacherEvaluationListDTO {
	
	private String seq; //번호
	private String name; // 과정명
	private String courseNum; //과정번호	
	private String startDate; // 과정개강일
	private String endDate; // 과정종료일
	private String totalStudent; //참여인원수
	private String materials; //자료만족도
	private String communication; //소통만족도
	private String jobPreparing; //취업만족도
	private String divisionTime; //시간분배만족도

	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTotalStudent() {
		return totalStudent;
	}
	public void setTotalStudent(String totalStudent) {
		this.totalStudent = totalStudent;
	}
	
	
	
	
	
	

	
	

}
