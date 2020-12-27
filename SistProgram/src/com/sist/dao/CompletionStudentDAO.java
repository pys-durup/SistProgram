package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.CompletionStudentDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;



public class CompletionStudentDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public CompletionStudentDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
/**
 * 수료자 리스트(취업상담 가능)
 * @param seq 교사번호
 * @return
 */
public ArrayList<CompletionStudentDTO> list(String seq){
	
	
	try { 
			String sql = "{ call proc_CompletionStudentList(?,?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet) cstat.getObject(2);
			
			ArrayList<CompletionStudentDTO> list = new ArrayList<CompletionStudentDTO>();
			
			while (rs.next()) {
				CompletionStudentDTO dto = new CompletionStudentDTO();
				dto.setStudentNum(rs.getString("studentNum"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setCourseNum(rs.getString("courseNum"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setRegistate(rs.getString("registate"));
				
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



