package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.StudentDTO;
import com.sist.main.DBUtil;

public class StudentDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교육생 DAO의 기본 생성자이다.
	 */
	public StudentDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("CStudentDAO.StudentDAO() : " + e.toString());
		}//try 
	
	}//StudentDAO() 
	
	
	public StudentDTO getStudent(String seq) {
		
		try {
			String sql = "select * from tblStudent where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {	
				StudentDTO dto = new StudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;			
				}
			
		} catch(Exception e) {
			System.out.println("StudentDAO.getStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
