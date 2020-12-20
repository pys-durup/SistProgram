package com.sist.dto;

public class MakeCourceDTO {

	private String seq;//번호
	private String startDate; //과정시작일
	private String endDate;	//과정종료일
	private String roomNum; //강의실번호
	private String teacherNum; //교사번호
	private String courceNum; // 과정번호
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getCourceNum() {
		return courceNum;
	}
	public void setCourceNum(String courceNum) {
		this.courceNum = courceNum;
	}
	
}
