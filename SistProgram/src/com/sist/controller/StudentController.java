package com.sist.controller;

import java.util.Scanner;

import com.sist.dto.StudentDTO;

public class StudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	
	public StudentController(StudentDTO sdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//			System.out.printf("교육생 %s님 접속을 환영합니다\n", );
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 출결체크 및 조회");
			System.out.println("2. 성적 조회");
			System.out.println("3. 상담 일지");
			System.out.println("4. 교사 평가");
			System.out.println("4. 취업활동 관리");
			System.out.println("5. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				
			} else if (num.equals("5")) { 
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}
	
	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
}
