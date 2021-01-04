package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.subjectAvgScoreDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class subjectAvgScoreDAO {
	
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public subjectAvgScoreDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("subjectAvgScoreDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<subjectAvgScoreDTO> list(String seq) {
		try {
			
			String sql = "{call procScoreAvg(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<subjectAvgScoreDTO> list = new ArrayList<subjectAvgScoreDTO>();
			
			while(rs.next()) {
				subjectAvgScoreDTO dto = new subjectAvgScoreDTO();
				
				dto.setSjseq(rs.getString("sjseq"));
				dto.setSjname(rs.getString("sjname"));
				dto.setWrite(rs.getString("write"));
				dto.setPractice(rs.getString("practice"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("subjectAvgScoreDAO.list()");
			e.printStackTrace();
		}
		return null;
	}
}
