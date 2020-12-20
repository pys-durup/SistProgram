package com.sist.main;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("메인 메서드");
		
		Connection conn = null;
		
		try {
			conn = DBUtil.open();
			
			System.out.println(conn.isClosed());
			
			conn.close();
			
			System.out.println(conn.isClosed());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryMain.enmain()");
			e.printStackTrace();
		}
		
	}
	
}
