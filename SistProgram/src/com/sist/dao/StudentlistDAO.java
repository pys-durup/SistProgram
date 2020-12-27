package com.sist.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;

import com.sist.dto.StudentConsultListDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class StudentlistDAO {

	
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public StudentlistDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudentlistDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<StudentlistDTO> Studentlist() {
		try {
			
			String sql = "{call procStudentList(?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
						
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<StudentlistDTO> list = new ArrayList<StudentlistDTO>();
			
			while(rs.next()) {
				StudentlistDTO dto = new StudentlistDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
				dto.setJumin(rs.getString("jumin"));
				dto.setRegiState(rs.getString("regiState"));
				
				list.add(dto);				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("StudentlistDAO.list()");
			e.printStackTrace();
		}
		return null;
	}

}
