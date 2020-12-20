package com.test.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.test.reservestudent.DBUtil;

public class QualificationDAO {


	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private CallableStatement cstat;
	private ResultSet rs;
	
	public QualificationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	
}
