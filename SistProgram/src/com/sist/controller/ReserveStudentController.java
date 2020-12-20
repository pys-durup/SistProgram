package com.sist.controller;

import java.util.Scanner;

import com.sist.dto.ReserveStudentDTO;

public class ReserveStudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private ReserveStudentDTO rsdto; // 로그인한 계정의 정보를 담을 객체
	
	public ReserveStudentController(ReserveStudentDTO rsdto) {
		this.rsdto = rsdto; // 로그인한 예비교육생의 계정 정보를 담는다
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("예비교육생 %s님 접속을 환영합니다\n", this.rsdto.getName() );
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 기본정보 관리");
			System.out.println("2. 교육과정 수강면접 신청");
			System.out.println("3. 개설과목 관리");
			System.out.println("4. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
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
