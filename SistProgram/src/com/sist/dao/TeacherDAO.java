package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.MasterDTO;
import com.sist.dto.TeacherDTO;
import com.sist.main.DBUtil;

public class TeacherDAO {
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교사 DAO의 기본 생성자이다.
	 */
	public TeacherDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAODTO() : " + e.toString());
		}//try 
	
	}//TeacherDAO() 
	
	public TeacherDTO getTeacher(String seq) {
		
		try {
			
			String sql = "select * from tblTeacher where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
		
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryMasterDAO.engetMaster()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
