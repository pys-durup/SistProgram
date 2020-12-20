package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.main.DBUtil;

public class TeacherSubjectListDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 과목 목록 DAO의 기본 생성자이다.
	 */
	public TeacherSubjectListDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("TeacherSubjectListDTO.TeacherSubjectListDTO() : " + e.toString());
		}//try 
	
	}//TeacherSubjectListDTO() 
}
