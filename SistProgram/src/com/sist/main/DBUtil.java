package com.sist.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
private static Connection conn;
	
	
	/**
	 * 데이터베이스 연결 메서드 입니다
	 * @return 연결된 Connection 객체
	 */
	public static Connection open() {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		try {
			// java.lang.ClassNotFoundException: oracle.jdbc.driverr.OracleDriver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {

			System.out.println("primaryDBUtil.enopen()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 데이터베이스 연결 메서드 입니다
	 * @param server 접속할 서버 주소 입니다
	 * @param id 접속할 계정명 입니다
	 * @param pw 접속할 비밀번호 입니다
	 * @return 연결된 Connection 객체를 반환합니다
	 */
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
		} catch (Exception e) {

			System.out.println("primaryDBUtil.enopen()");
			e.printStackTrace();
		}
		
		return null;
		
	}

}
