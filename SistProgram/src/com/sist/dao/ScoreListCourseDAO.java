package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScoreListCourseDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScoreListCourseDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScoreListCourseDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScoreListCourseDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<ScoreListCourseDTO> list() {

		try {
			
			String sql = "{call procCourseList(?)}";
			
			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);
			
			ArrayList<ScoreListCourseDTO> list = new ArrayList<ScoreListCourseDTO>();
			
			while(rs.next()) {
				ScoreListCourseDTO dto = new ScoreListCourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCname(rs.getString("cname"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				
				list.add(dto);
			}
			
			return list; //**
			
		} catch (Exception e) {
			System.out.println("ScoreListCourseDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
}
