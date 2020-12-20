package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.main.DBUtil;

public class InterviewApplyDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	
	public InterviewApplyDAO() {
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	
	
	
	
	
}
