package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.TeacherScheduleDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherScheduleDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public TeacherScheduleDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	

}

/**
 * 교사 강의스케줄 조회
 * @param seq 교사번호
 * @return
 */
public ArrayList<TeacherScheduleDTO> list(String seq){
	
	try {
		
		String sql = "{ call proc_teacherScheduleAll(?) }";				
		
		cstat = conn.prepareCall(sql);
		cstat.registerOutParameter(1, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(1);
		ArrayList<TeacherScheduleDTO> list = new ArrayList<TeacherScheduleDTO>();
		while (rs.next()) {
			TeacherScheduleDTO dto = new TeacherScheduleDTO();
			
			dto.setCourseNum(rs.getString("courseNum"));
			dto.setTeacherName(rs.getString("teacherName"));
			dto.setCourseName(rs.getString("courseName"));
			dto.setSubjectNum(rs.getNString("subjectNum"));
			dto.setSubject(rs.getString("Subject"));
			dto.setBook(rs.getString("book"));
			dto.setRoom(rs.getString("room"));
			dto.setStartDate(rs.getString("startDate"));
			dto.setEndDate(rs.getString("endDate"));
			dto.setTotalStudent(rs.getString("totalStudent"));
			dto.setCourseStatus(rs.getString("courseStatus"));
			
			list.add(dto);		
		}
		rs.close();
		return list;
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	return null;
	
	
}



public ArrayList<TeacherScheduleDTO> selectList(String seq){
	//seq는 교사번호
	try {
		
		String sql = "{ call proc_teacherSchedule(?,?) }";				
		
		cstat = conn.prepareCall(sql);
		cstat.setString(1, seq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(2);
		ArrayList<TeacherScheduleDTO> list = new ArrayList<TeacherScheduleDTO>();
		while (rs.next()) {
			TeacherScheduleDTO dto = new TeacherScheduleDTO();
			
			dto.setCourseNum(rs.getString("courseNum"));
			dto.setTeacherName(rs.getString("teacherName"));
			dto.setCourseName(rs.getString("courseName"));
			dto.setSubjectNum(rs.getNString("subjectNum"));
			dto.setSubject(rs.getString("Subject"));
			dto.setBook(rs.getString("book"));
			dto.setRoom(rs.getString("room"));
			dto.setStartDate(rs.getString("startDate"));
			dto.setEndDate(rs.getString("endDate"));
			dto.setTotalStudent(rs.getString("totalStudent"));
			dto.setCourseStatus(rs.getString("courseStatus"));
			
			list.add(dto);		
		}
		rs.close();
		return list;
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	return null;
	
	
}


}