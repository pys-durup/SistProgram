package com.sist.dto;
//취업상담 join DTO
public class JobConsultationListDTO {
  
	
	private String consultationNum;
    private String studentNum; //교육생번호
    private String studentName;//교육생이름
    private String teacherName;//교사이름
    private String courseNum;//과정번호
    private String courseName;//과정명
    private String content;//상담내용
    private String consdate;//상담일자
    private String registate;//수강상태
    
    public String getConsultationNum() {
		return consultationNum;
	}
	public void setConsultationNum(String consultationNum) {
		this.consultationNum = consultationNum;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConsdate() {
		return consdate;
	}
	public void setConsdate(String consdate) {
		this.consdate = consdate;
	}
	public String getRegistate() {
		return registate;
	}
	public void setRegistate(String registate) {
		this.registate = registate;
	}

}
