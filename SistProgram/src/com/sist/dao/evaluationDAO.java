package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.evaluationDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class evaluationDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public evaluationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("evaluationDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<evaluationDTO> list(String seq) {
		try {
			
			String sql = "{call proclistEvaluation(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<evaluationDTO> list = new ArrayList<evaluationDTO>();
			
			while(rs.next()) {
				evaluationDTO dto = new evaluationDTO();
				
				dto.setCoursename(rs.getString("coursename"));
				dto.setMaterials(rs.getString("materials"));
				dto.setCommunication(rs.getString("communication"));
				dto.setJobpreparing(rs.getString("jobpreparing"));
				dto.setDivisionTime(rs.getString("divisionTime"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("evaluationDAO.list()");
			e.printStackTrace();
		}
		return null;
	}
}
