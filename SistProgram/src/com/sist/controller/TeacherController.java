package com.sist.controller;

import java.util.Scanner;

import com.sist.dto.TeacherDTO;

public class TeacherController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private TeacherDTO tdto; // 로그인한 계정의 정보를 담을 객체
	
	public TeacherController(TeacherDTO tdto) {
		this.tdto = tdto; // 로그인한 강사의 계정 정보를 담는다
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//			System.out.printf("강사 %s님 접속을 환영합니다\n", );
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 강의 스케줄 조회");
			System.out.println("2. 배점 입출력");
			System.out.println("3. 성적 입출력");
			System.out.println("4. 출결 관리");
			System.out.println("5. 교사 평가 조회");
			System.out.println("6. 상담 일지 관리");
			System.out.println("7. 사후처리 조회");
			System.out.println("8. 데이터 통계");
			System.out.println("9. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				
			} else if (num.equals("5")) { 
				
			} else if (num.equals("6")) { 
				
			} else if (num.equals("7")) { 
				
			} else if (num.equals("8")) { 
				
			} else if (num.equals("9")) { 
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
