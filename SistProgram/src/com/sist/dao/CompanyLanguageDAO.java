package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.main.DBUtil;

public class CompanyLanguageDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	
	public CompanyLanguageDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryCompanyLanguageDAO.encompanyLanguageDAO()");
			e.printStackTrace();
		}
	}

}
