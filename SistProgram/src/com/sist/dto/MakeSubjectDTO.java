package com.sist.dto;

public class MakeSubjectDTO {
	
	private String seq; //번호
	private String subjectNum;//과목번호
	private String startDate; // 과목시작일
	private String endDate; // 과목종료일
	private String bookNum; // 교재번호
	private String bookdistristate; // 분배상태
	private String subjectname; //과목명
	private String duration; // 과목 일수
	private String status; // 과목 상태 개설전, 진행중, 종료
	private String tname; //선생님이름
	private String psubjectnum; //과목명 번호
	private String pstartdate; // 과목시작일
	private String penddate; // 과목종료일
	private String pbooknum; //교과서
	private String pseq; // 개설 과목 번호 or 개설 과정 번호
	private String pseq2; // 개설 과목 번호
	private String pstate; //책 분배 상태
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
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
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookdistristate() {
		return bookdistristate;
	}
	public void setBookdistristate(String bookdistristate) {
		this.bookdistristate = bookdistristate;
	}
	public String getSubjectname() {
	    return subjectname;
	}
	public String getDuration() {
	    return duration;
	}
	public String getStatus() {
	    return status;
	}
	public String getTname() {
	    return tname;
	}
	public void setSubjectname(String subjectname) {
	    this.subjectname = subjectname;
	}
	public void setDuration(String duration) {
	    this.duration = duration;
	}
	public void setStatus(String status) {
	    this.status = status;
	}
	public void setTname(String tname) {
	    this.tname = tname;
	}
	public String getPsubjectnum() {
	    return psubjectnum;
	}
	public String getPstartdate() {
	    return pstartdate;
	}
	public String getPenddate() {
	    return penddate;
	}
	public String getPbooknum() {
	    return pbooknum;
	}
	public void setPsubjectnum(String psubjectnum) {
	    this.psubjectnum = psubjectnum;
	}
	public void setPstartdate(String pstartdate) {
	    this.pstartdate = pstartdate;
	}
	public void setPenddate(String penddate) {
	    this.penddate = penddate;
	}
	public void setPbooknum(String pbooknum) {
	    this.pbooknum = pbooknum;
	}
	public String getPseq() {
	    return pseq;
	}
	public void setPseq(String pseq) {
	    this.pseq = pseq;
	}
	public String getPseq2() {
	    return pseq2;
	}
	public void setPseq2(String pseq2) {
	    this.pseq2 = pseq2;
	}
	public String getPstate() {
	    return pstate;
	}
	public void setPstate(String pstate) {
	    this.pstate = pstate;
	}
	
	
	

}
