package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.EndCourseListDTO;
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

/**
 * 과정 목록을 리턴하는 메서드
 * @return
 */
	public ArrayList<ScoreListCourseDTO> list() {

		try {
					
			String sql = "select * from vwCourseList";
			
			ArrayList<ScoreListCourseDTO> list = new ArrayList<ScoreListCourseDTO>();
		
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
						
			
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
