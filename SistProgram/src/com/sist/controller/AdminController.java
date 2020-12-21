package com.sist.controller;

import java.util.Scanner;

import com.sist.dto.MasterDTO;

public class AdminController {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체

	public AdminController(MasterDTO mdto) {
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
	}
	
	public void start() {
		
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("관리자 %s님 접속을 환영합니다\n", this.mdto.getId());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 기초정보 관리");
			System.out.println("2. 개설과정 관리");
			System.out.println("3. 개설과목 관리");
			System.out.println("4. 출결 관리");
			System.out.println("5. 성적 관리");
			System.out.println("6. 취업활동 관리");
			System.out.println("7. 취업지원 관리");
			System.out.println("8. 데이터 통계");
			System.out.println("9. 상담일지 관리");
			System.out.println("0. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				attendanceManagement();
			} else if (num.equals("5")) { 
				scoreManagement();
			} else if (num.equals("6")) { 
				
			} else if (num.equals("7")) { 
				
			} else if (num.equals("8")) { 
				
			} else if (num.equals("9")) { 
				CourseconsultationManagement();
			} else if (num.equals("0")) {
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		

	}
	
	private void CourseconsultationManagement() {
		// 상담관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[상담 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 전체 교육생 조회");
			System.out.println("2. ");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
				if(num.equals("1")) {
					System.out.println("[성적관리 - 1. 과정별]");
				}
		}
		
		
		
	}

	private void scoreManagement() {
		// 성적관리
		boolean check = true;
		while (check) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[성적 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정별");
			System.out.println("2. 교육생별");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
					
				if(num.equals("1")) {
					System.out.println("[성적관리 - 1. 과정별]");
					//과정목록 출력
					//과정(이름, 시작일, 종료일)
					try {
						
						
						
					} catch (Exception e) {
						System.out.println("AdminController.scoreManagement");
						e.printStackTrace();
						
						
					}
				}else if(num.equals("2")) {
					System.out.println("[성적관리 - 2. 교육생별]");
					//교육생 리스트 츌력
					//(교육생 이름, 주민번호 뒷자리, 개설 과정명, 개설 과정기간, 강의실명)
					
					
					
				}else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
					}
				}
		
	}

	private void attendanceManagement() {
		// 출결관리
		System.out.println("1. 교육생 번호로 검색");
		System.out.println("2. 과정별");
	}
	
	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
}
