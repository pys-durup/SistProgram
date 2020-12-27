package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScCourseSubjectDTO;
import com.sist.dto.ScCourseSubjectScoreDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScCourseSubjectScoreDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScCourseSubjectScoreDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectScoreDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<ScCourseSubjectScoreDTO> list() {
		
		try {
			
			String sql = "{call procScCourseSubjectScore(?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(1);
			
			ArrayList<ScCourseSubjectScoreDTO> list = new ArrayList<ScCourseSubjectScoreDTO>();
			
			while(rs.next()) {
				ScCourseSubjectScoreDTO dto = new ScCourseSubjectScoreDTO();
				
				dto.setSname(rs.getString("sname"));
				dto.setJumin(rs.getString("jumin"));
				dto.setWrite(rs.getString("write"));
				dto.setPratice(rs.getString("pratice"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
