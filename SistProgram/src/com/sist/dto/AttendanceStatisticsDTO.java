package com.sist.dto;

public class AttendanceStatisticsDTO {
	
	private String reginum;
	private String name;
	private int attendance;
	private int absent;
	private int late;
	private int leave;
	private float attendanceRate;
	
	public String getReginum() {
		return reginum;
	}
	public void setReginum(String reginum) {
		this.reginum = reginum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getAbsent() {
		return absent;
	}
	public void setAbsent(int absent) {
		this.absent = absent;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getLeave() {
		return leave;
	}
	public void setLeave(int leave) {
		this.leave = leave;
	}
	public float getAttendanceRate() {
		return attendanceRate;
	}
	public void setAttendanceRate(float attendanceRate) {
		this.attendanceRate = attendanceRate;
	}
	@Override
	public String toString() {
		return "AttendanceStatisticsDTO [reginum=" + reginum + ", name=" + name + ", attendance=" + attendance
				+ ", absent=" + absent + ", late=" + late + ", leave=" + leave + ", attendanceRate=" + attendanceRate
				+ "]";
	}
	
	
	
	
	
}
