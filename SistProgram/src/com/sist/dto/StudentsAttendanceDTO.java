package com.sist.dto;

public class StudentsAttendanceDTO {
	
	private String alldates; // 교육생 전체출결 조회 시 사용
	private String attstate; // 교육생 전체출결 조회 시 사용
	private String days; //교육생 선택기간 출결 조회 시 사용
	private String inTime; //교육생 선택기간 출결 조회 시 사용
	private String outTime; //교육생 선택기간 출결 조회 시 사용
	private String dayState; //교육생 선택기간 출결 조회 시 사용
	
	
	public String getAlldates() {
		return alldates;
	}
	public void setAlldates(String alldates) {
		this.alldates = alldates;
	}
	public String getAttstate() {
		return attstate;
	}
	public void setAttstate(String attstate) {
		this.attstate = attstate;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getDayState() {
		return dayState;
	}
	public void setDayState(String dayState) {
		this.dayState = dayState;
	}
	
}
