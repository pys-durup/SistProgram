package com.sist.view;

import java.util.Scanner;

import com.sist.dto.ReserveStudentDTO;

public class ReserveStudentView {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private ReserveStudentDTO rsdto; // 로그인한 계정의 정보를 담을 객체
	
	
	public ReserveStudentView(ReserveStudentDTO rsdto) {
		this.rsdto = rsdto; // 로그인한 예비교육생의 계정 정보를 담는다
	}


	public void mainRMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("예비교육생 %s님 접속을 환영합니다\n", this.rsdto.getName() );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 기본정보 관리");
		System.out.println("2. 교육과정 수강면접 신청");
		System.out.println("3. 면접결과 확인");
		System.out.println("4. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("번호를 입력하세요 :");
		System.out.println();
		
	}


	public void ReserveInfoMenuView() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");		
		System.out.println("1. 기본정보 조회");
		System.out.println("2. 기본정보 수정");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}


	public void listReserveInfoView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("기본정보 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 기본정보**\n", this.rsdto.getName());
		System.out.println();
	}


	public void editReserveInfoView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("기본정보 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("**수정을 원하지 않는 항목은 enter키를 입력해주세요.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}


	public void addApplyView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 1. 교육과정 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **면접목록 중 신청하려는 면접과정의 번호를 입력해주세요**\n");
		System.out.println();
		
	}


	public void myResultView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("면접결과 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 면접결과**\n", this.rsdto.getName());
		
	}


	public void migrationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("계정전환");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 교육생 계정전환**\n", this.rsdto.getName());
		System.out.println();
		System.out.printf("교육생으로의 계정전환 이후, 전환한 교육생 계정으로 로그인해주세요.\n");
		System.out.printf(" (1) 계정전환    (0) 뒤로 가기 ");
		
		
	}


	public void migrationCelebration() {
		 System.out.println();
		 System.out.println("*̥❄︎‧˚₊✧*｡*̥❄︎‧˚₊✧*｡*̥❄︎‧˚₊✧*｡*̥❄︎‧˚₊✧*｡*̥❄︎‧˚₊✧*｡*̥❄︎‧˚₊✧*｡");                                                                                 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
