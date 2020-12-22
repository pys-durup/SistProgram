package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.dto.AttendanceDTO;
import com.sist.main.DBUtil;


public class AttendanceDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
	private ResultSet rs = null;
	
	public AttendanceDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.enAttendanceDAO()");
			e.printStackTrace();
		}
	}
	
	
	

	public int addAttendance(AttendanceDTO dto) {
		//교육생 출석체크하기
		try {
			String sql = "{ call procaddAttendance(?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getRegiNum());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AttendanceDAO.addAttendance()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	
}
