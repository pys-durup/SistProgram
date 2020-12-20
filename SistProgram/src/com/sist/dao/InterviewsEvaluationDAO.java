package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.sist.main.DBUtil;

public class InterviewsEvaluationDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	
	public InterviewsEvaluationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		
			
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}	
		
	}
	

}
