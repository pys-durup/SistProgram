package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.JobConsultationListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class JobConsultationListDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public JobConsultationListDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
/**
 * 취업상담조회
 * @param pseq 교사번호
 * @return
 */
public ArrayList<JobConsultationListDTO> jslist(String pseq){
	
	try {
		String sql = "{ call proc_JobConsultationList(?,?) }";
		
		cstat = conn.prepareCall(sql);
		cstat.setString(1, pseq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(2);
		
		ArrayList<JobConsultationListDTO> list = new ArrayList<JobConsultationListDTO>();
		
		while (rs.next()) {
			JobConsultationListDTO jcdto = new JobConsultationListDTO();
			jcdto.setConsultationNum(rs.getString("ConsultationNum"));
			jcdto.setStudentNum(rs.getString("studentNum"));
			jcdto.setStudentName(rs.getString("studentName"));
			jcdto.setTeacherName(rs.getString("teacherName"));
			jcdto.setCourseNum(rs.getString("courseNum"));
			jcdto.setCourseName(rs.getString("courseName"));
			jcdto.setContent(rs.getString("content"));
			jcdto.setConsdate(rs.getString("consdate"));
			jcdto.setRegistate(rs.getString("registate"));
			
			
			list.add(jcdto);
			
		}
		rs.close();
		return list;
		
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	
	return null;
	
	
	
}

//edit를 위한 기존목록보기

/**
 * 취업상담 edit를 위한 기존목록보기 
 * @param pseq 교사번호
 * @param seq 상담번호
 * @return
 */
public JobConsultationListDTO get(String pseq, String seq) {
	
	try { 	String sql = "{ call proc_editJobConsultationList(?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, pseq);	
			cstat.setString(2, seq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet) cstat.getObject(3);
			
			if(rs.next()) {
				
				JobConsultationListDTO dto = new JobConsultationListDTO();
				
				dto.setConsultationNum(rs.getString("consultationNum"));
				dto.setCourseNum(rs.getString("CourseNum"));
				dto.setCourseName(rs.getString("CourseName"));
				dto.setContent(rs.getString("Content"));
				dto.setConsdate(rs.getString("Consdate"));
				dto.setStudentName(rs.getString("StudentName"));
				dto.setTeacherName(rs.getString("TeacherName"));
				dto.setStudentNum(rs.getString("StudentNum"));
				
				return dto;
				
			}
			
			
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return null;
	
	
	
}

//안씀(킵)
public ArrayList<JobConsultationListDTO> selectJslist(){
	
	try {
		String sql = "{ proc_ConsultingStudentList(?) }";
		
		cstat = conn.prepareCall(sql);
		cstat.registerOutParameter(1, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(1);
		
		ArrayList<JobConsultationListDTO> list = new ArrayList<JobConsultationListDTO>();
		
		while (rs.next()) {
			JobConsultationListDTO jcdto = new JobConsultationListDTO();
			jcdto.setStudentNum(rs.getString("studentNum"));
			jcdto.setStudentName(rs.getString("studentName"));
			jcdto.setTeacherName(rs.getString("teacherName"));
			jcdto.setCourseNum(rs.getString("courseNum"));
			jcdto.setCourseName(rs.getString("courseName"));
			jcdto.setContent(rs.getString("content"));
			jcdto.setConsdate(rs.getString("consdate"));
			jcdto.setRegistate(rs.getString("registate"));
			
			
			list.add(jcdto);
			
		}
		rs.close();
		return list;
		
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	
	return null;
	
	
	
}



/**
 * 취업상담내역 작성
 * @param dto 취업상담정보
 * @return
 */
public int addJobConsulting(JobConsultationListDTO dto) {
	
	
	try {
		
		String sql = "{ call proc_jobConsultingWrite(?,?) }";
		cstat = conn.prepareCall(sql);
		cstat.setString(1, dto.getContent());//상담내용
		cstat.setString(2, dto.getStudentNum());//상담학생번호
		
		return cstat.executeUpdate();
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	return 0;
		
}

/**
 * 취업상담내역 수정
 * @param dto2 취업상담정보
 * @return
 */
public int editJobConsulting(JobConsultationListDTO dto2) {
	
	try {	
			String sql = "{ call porc_rvJobConsultation(?,?,?) }";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getStudentNum());
			pstat.setString(2, dto2.getContent());
			pstat.setString(3, dto2.getConsultationNum());
			
			return pstat.executeUpdate();
						
	} catch (Exception e) {
		System.out.println(e);
	}	
	return 0;
	
}
//취업상담내역 삭제
/**
 * 취업상담 삭제
 * @param seq 취업상담번호
 * @return
 */
public int deleteJobConsulting(String seq) {
	
	try {
		
		String sql = "delete from tblJobConsultation where seq = ?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, seq);
		
		return pstat.executeUpdate(); //1 or 0
		
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return 0;
	
	
	
}




}





