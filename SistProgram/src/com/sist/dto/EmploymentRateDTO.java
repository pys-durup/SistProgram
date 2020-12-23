package com.sist.dto;

public class EmploymentRateDTO {
	
	private String column;
	private String value;
	
	public EmploymentRateDTO (String column, String value) {
		this.column = column;
		this.value = value;
	}
	
	public EmploymentRateDTO () {
		this("","");
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
