package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.dto.ReserveStudentDTO;
import com.sist.main.DBUtil;

public class ReserveStudentDAO {
	

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/*
	 예비학생 기본정보관리에는 삭제 기능 없음.
	 */
	
	public ReserveStudentDAO() {
	
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	
	public ReserveStudentDTO getReserveStudent(String seq) {
		
		try {
			String sql = "select * from tblReserveStudent where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				// dto에 해당 번호의 예비학생 정보 1행을 담는다.
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString(" address"));
				dto.setField(rs.getString("field"));
				dto.setKnowledge(rs.getString("knowledge"));
				
				return dto;				
			}
			
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.getReserveStudent()");
			e.printStackTrace();
		}
				
		return null;
	} 
	
	
	
	
}
