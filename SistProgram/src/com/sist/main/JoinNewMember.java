package com.sist.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.dao.ReserveStudentDAO;
import com.sist.dto.ReserveStudentDTO;

public class JoinNewMember {
	
	
	private static Scanner scan;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	
	
	static {
		scan = new Scanner(System.in);
	}
	
	
	public JoinNewMember() {
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryLogin.enLogin()");
			e.printStackTrace();
		}
		
	}
	
	
	
	public void join() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("예비교육생 회원가입을 위해 아래 사항을 작성해주세요.\n");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		System.out.println("(1) 이름: ");
		String name = scan.nextLine();
		System.out.println("(2) 주민등록번호: ");
		String jumin = scan.nextLine();
		System.out.println("(3) 연락처: ");
		String tel = scan.nextLine();
		System.out.println("(4) 주소: ");
		String address = scan.nextLine();
		System.out.println("(5) 희망직무: ");
		String field = scan.nextLine();
		System.out.println("(6) 사전지식: ");
		String knowledge = scan.nextLine();
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **회원정보 등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			
			ReserveStudentDTO dto = new ReserveStudentDTO();
			ReserveStudentDAO dao = new ReserveStudentDAO();
			
			//dto2.setSeq(reserveNum);
			dto.setName(name);
			dto.setJumin(jumin);
			dto.setTel(tel);
			dto.setAddress(address);
			dto.setField(field);
			dto.setKnowledge(knowledge);
			
			int result = dao.addNewReserve(dto);
			
				if (result > 0) {
					System.out.println("회원가입에 성공하였습니다. 예비교육생으로 로그인해주세요.");
				} else {
					System.out.printf("회원가입이 정상적으로 완료되지 않았습니다.\n");
					System.out.println("입력하신 핸드폰번호, 주민등록번호를 다시 확인해주세요.");
				}
			
		} else {
			System.out.println("번호를 잘못 입력하였습니다. 가입이 정상적으로 진행되지 않았습니다.");
		}
		
		
		Login lg = new Login();
		lg.loginReserveStudent(); // 가입이 끝나면 예비교육생 로그인 메서드 호출
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
