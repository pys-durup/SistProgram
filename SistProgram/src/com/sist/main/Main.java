package com.sist.main;

import java.util.Scanner;

public class Main {
	
	private static Scanner scan;
	private static Login lg = new Login();
	
	static {
		scan = new Scanner(System.in);
		lg = new Login();
	}

	public static void main(String[] args) {
		
		boolean loop = true;
		
		while (loop) {
			
			logo();

			System.out.print("번호를 입력해 주세요 : ");
			String num = scan.nextLine();
			
			if (num.equals("1")) {
				// 예비교육생 로그인 처리 + controller 호출
				lg.loginReserveStudent();
			} else if (num.equals("2")) {
				// 교육생 로그인 처리 + controller 호출
				lg.loginStudent();
			} else if (num.equals("3")) {
				// 교사 로그인 처리 + controller 호출
				lg.loginTeacher();
			} else if (num.equals("4")) {
				// 관리자 로그인 처리 + controller 호출
				lg.loginAdmin();
			} else if (num.equals("5")) {
				//회원가입(예비교육생) 회원가입 클래스의 회원가입 메서드 호출
				JoinNewMember jm = new JoinNewMember();
				jm.join();
			} else if (num.equals("6")) {
				System.exit(0);
			} else {
				System.out.println("올바르지 않은 입력입니다");
				pause();
			}
		}
	}
	
	
	private static void logo() {
		System.out.println("\r\n"
				+ "  ██████  ██▓  ██████ ▄▄▄█████▓    ██▓███   ██▀███   ▒█████    ▄████  ██▀███   ▄▄▄       ███▄ ▄███▓\r\n"
				+ "▒██    ▒ ▓██▒▒██    ▒ ▓  ██▒ ▓▒   ▓██░  ██▒▓██ ▒ ██▒▒██▒  ██▒ ██▒ ▀█▒▓██ ▒ ██▒▒████▄    ▓██▒▀█▀ ██▒\r\n"
				+ "░ ▓██▄   ▒██▒░ ▓██▄   ▒ ▓██░ ▒░   ▓██░ ██▓▒▓██ ░▄█ ▒▒██░  ██▒▒██░▄▄▄░▓██ ░▄█ ▒▒██  ▀█▄  ▓██    ▓██░\r\n"
				+ "  ▒   ██▒░██░  ▒   ██▒░ ▓██▓ ░    ▒██▄█▓▒ ▒▒██▀▀█▄  ▒██   ██░░▓█  ██▓▒██▀▀█▄  ░██▄▄▄▄██ ▒██    ▒██ \r\n"
				+ "▒██████▒▒░██░▒██████▒▒  ▒██▒ ░    ▒██▒ ░  ░░██▓ ▒██▒░ ████▓▒░░▒▓███▀▒░██▓ ▒██▒ ▓█   ▓██▒▒██▒   ░██▒\r\n"
				+ "▒ ▒▓▒ ▒ ░░▓  ▒ ▒▓▒ ▒ ░  ▒ ░░      ▒▓▒░ ░  ░░ ▒▓ ░▒▓░░ ▒░▒░▒░  ░▒   ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░ ▒░   ░  ░\r\n"
				+ "░ ░▒  ░ ░ ▒ ░░ ░▒  ░ ░    ░       ░▒ ░       ░▒ ░ ▒░  ░ ▒ ▒░   ░   ░   ░▒ ░ ▒░  ▒   ▒▒ ░░  ░      ░\r\n"
				+ "░  ░  ░   ▒ ░░  ░  ░    ░         ░░         ░░   ░ ░ ░ ░ ▒  ░ ░   ░   ░░   ░   ░   ▒   ░      ░   \r\n"
				+ "      ░   ░        ░                          ░         ░ ░        ░    ░           ░  ░       ░   \r\n"
				+ "                                                                                                   \r\n"
				+ "");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 예비교육생계정으로 로그인");
		System.out.println("2. 교육생계정으로 로그인");
		System.out.println("3. 교사계정으로 로그인");
		System.out.println("4. 관리자계정으로 로그인");
		System.out.println("5. 회원가입");
		System.out.println("6. 프로그램 종료");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
	
	private static void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
	

	
}


