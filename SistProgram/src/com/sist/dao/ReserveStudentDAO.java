package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.main.DBUtil;

public class ReserveStudentDAO {
	

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/*
	 예비학생의 기본정보관리에는 삭제 기능 없음 -> 삭제되면 안되는 정보이기 때문.
	 */
	
	public ReserveStudentDAO() {
	
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	
	
	
	
}
