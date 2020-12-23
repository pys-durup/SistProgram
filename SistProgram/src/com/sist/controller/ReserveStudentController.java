package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.InterviewApplyDAO;
import com.sist.dao.InterviewResultDAO;
import com.sist.dao.ReserveStudentDAO;
import com.sist.dto.AvailableApplyListDTO;
import com.sist.dto.InterviewApplyDTO;
import com.sist.dto.InterviewResultDTO;
import com.sist.dto.ReserveStudentDTO;
import com.sist.main.Login;
import com.sist.main.Main;
import com.sist.view.ReserveStudentView;

public class ReserveStudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private ReserveStudentDTO rsdto; // 로그인한 계정의 정보를 담을 객체
	private ReserveStudentView view;
	private InterviewResultDTO idto; //로그인한 계정의 면접결과 정보를 담는 객체
	
	public ReserveStudentController(ReserveStudentDTO rsdto, InterviewResultDTO idto) {
		this.rsdto = rsdto; // 로그인한 예비교육생의 계정 정보를 담는다
		this.view = new ReserveStudentView(rsdto);
		this.idto = idto;
	}
	
	public void start() {
		//예비학생 메인메뉴
		view.mainRMenu();
		boolean check = true;		
		num = scan.nextLine();
		
		while (check) {
			
			if(num.equals("1")) { //1. 기본정보관리
				 ReserveInfoMenu();
				 break;
			} else if (num.equals("2")) { //2. 교육과정 수강면접 신청
				addApply();
				break;
			} else if (num.equals("3")) { //3. 면접결과 확인
				myResult();
				break;
			} else if (num.equals("4")) { //4. 로그아웃
				Login lg = new Login();
				lg.loginReserveStudent(); //로그아웃하면 로그인 클래스의 예비교육생 메서드 호출
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				
			}
		}
		
	}
	
	
	
	public void myResult() {
	//예비교육생이 자신의 면접결과를 확인하는 메서드
		view.myResultView();
		
		String prstudentNum = this.rsdto.getSeq(); //생성자 rsdto의 get메서드로 매개변수 선언
	
		InterviewResultDAO dao = new InterviewResultDAO();
		ArrayList<InterviewResultDTO> list = dao.list(prstudentNum);
		
		for (InterviewResultDTO dto : list) {
			
			System.out.printf(
					 "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"+
					"[이름] %s\n[과정번호] %s\n[과정명] %s\n[면접결과] %s\n"
							 ,dto.getrName()
							 ,dto.getcSeq()
							 ,dto.getcName()
							 ,dto.getResult());
		}
		System.out.println();
		
		//면접결과 합격일 경우에만 다음 메서드로 넘어가기
	
		String result = idto.getResult(); //생성자 idto를 통해 로그인한 예비학생의 면접결과 가져오기
		
		if (result.equals("합격")) {
			 System.out.println();
			 System.out.println("합격하셨습니다. 예비교육생에서 교육생 계정 전환을 위해 번호 1번을 눌러주세요.");
			 
			 String num = scan.nextLine(); 
				 if (num.equals("1")) {	 
					 migration();	 
				 } else {
					 System.out.println("번호를 잘못 입력하였습니다. 이전 메뉴로 돌아갑니다.");
					 start();
				 }
				
		} else {
			pause();
		}
		
		pause();
	}

	
	
	private void migration() {
		
		view.migrationView();
		
		num = scan.nextLine();
		
		if (num.equals("1")) {

			ReserveStudentDAO dao = new ReserveStudentDAO();
			String seq = this.rsdto.getSeq(); //생성자 rsdto의 get메서드로 로그인한 학생번호를 가져와 매개변수 선언
			int result = dao.addMigration(seq);
			
			if (result > 0) {
				
				System.out.println("교육생 계정전환이 성공적으로 완료되었습니다. 로그아웃 후 교육생계정으로 다시 로그인해주세요.");
				Login lg = new Login(); 
				lg.loginStudent(); //로그인 객체 생성하여 교육생 로그인 메서드 호출하기
			} else {
				System.out.println("교육생 계정전환이 정상적으로 완료되지 않았습니다.");
				pause();
			}
			
			
		} else if (num.equals("2")) {
			start();
		} else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
		
		pause();
		
	}

	public void addApply() {
		//교육생이 신청 가능한 교육과정을 조회하는 메서드
		view.addApplyView();
		
		 InterviewApplyDAO dao = new InterviewApplyDAO();
		
		 ArrayList<AvailableApplyListDTO> list = dao.list();
		 //신청가능한 개설과정목록 조회에 사용.
		 
		 for (AvailableApplyListDTO dto : list) {
			 System.out.printf(
					 "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"+
					 "[교육과정번호] %s\n[과정명] %s\n[시작일] %s\n[종료일] %s\n[과정 목표] %s\n[담당교사] %s\n\n"
					 			,dto.getCseq()
					 			,dto.getCname()
					 			,dto.getCstartdate()
					 			,dto.getCenddate()
					 			,dto.getCpurpose()
					 			,dto.getTname());
		 	}
			 System.out.println();
			 System.out.println("희망하는 교육과정 수강면접 신청 번호 입력: ");
			 
			 
			 String createdCourceNum = scan.nextLine(); //입력한 개설과정번호(매개변수1)
			 String reserveStudentNum = rsdto.getSeq(); //생성자 rsdto의 get메서드로 교육생번호 선언.(매개변수2)
			 
			 InterviewApplyDTO dto2 = new InterviewApplyDTO();//면접 신청정보를 담을 dto2
			 dto2.setCreatedCourceNum(createdCourceNum);
			 dto2.setReserveStudentNum(reserveStudentNum);
			 
			 int result = dao.addApply(dto2);
			 
				 if (result > 0) {
					 System.out.println("면접 신청을 완료하였습니다.");
				 } else {
					 System.out.println("면접 신청이 정상적으로 완료되지 않았습니다.");
				 }
				
		 pause();
		 }

	
	
	public void ReserveInfoMenu() {
		//기본정보관리 메뉴
			view.ReserveInfoMenuView();
			
			boolean check = true;
			num = scan.nextLine();
			
			while (check) {
			
				if (num.equals("1")) {
					listReserveInfo(); //기본정보조회
					break;
				} else if (num.equals("2")) {
					editReserveInfo(); //기본정보 수정
					break;
				} else if (num.equals("0")) {
					start(); //이전으로
					break;
				} else {
					System.out.println("잘못된 입력입니다");
					pause();
					
				}
			}
			
	
		
	}

	
	
	public void editReserveInfo() {

		view.editReserveInfoView();
		
		
		
		System.out.println("(1) 수정할 이름: ");
		String name = scan.nextLine();
		if (name.equals("")) {
			name = rsdto.getName();	
		}
		
		System.out.println("(2) 수정할 주민등록번호: ");
		String jumin = scan.nextLine();
		if (jumin.equals("")) {
			jumin = rsdto.getJumin();
		}

		System.out.println("(3) 수정할 연락처: ");
		String tel = scan.nextLine();
		if (tel.equals("")) {
			 tel = rsdto.getTel();
		}

		System.out.println("(4) 수정할 주소: ");
		String address = scan.nextLine();
		if (address.equals("")) {
			address = rsdto.getAddress();
		}

		System.out.println("(5) 수정할 희망직무: ");
		String field = scan.nextLine();
		if (field.equals("")) {
			field = rsdto.getField();
		}		

		System.out.println("(6) 수정할 사전지식: ");
		String knowledge = scan.nextLine();
		if (knowledge.equals("")) {
			knowledge = rsdto.getKnowledge();
		}		

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **수정한 기본정보 재등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		
		if (num.equals("1")) {
			
			ReserveStudentDTO dto2 = new ReserveStudentDTO();
			ReserveStudentDAO dao = new ReserveStudentDAO();
			
			String reserveNum = rsdto.getSeq(); //매개변수로 사용할 예비학생번호
		
			dto2.setSeq(reserveNum);
			dto2.setName(name);
			dto2.setJumin(jumin);
			dto2.setTel(tel);
			dto2.setAddress(address);
			dto2.setField(field);
			dto2.setKnowledge(knowledge);
			
			int result = dao.editReserveStudent(dto2);
			
				if (result > 0) {
					System.out.println("기본정보 수정에 성공하였습니다.");
				} else {
					System.out.println("기본정보 수정이 정상적으로 완료되지 않았습니다.");
				}
			
		} else {
			System.out.println("번호를 잘못 입력하였습니다. 수정이 정상적으로 진행되지 않았습니다.");
		}
		
		pause();
		
	}

	
	
	
	
	public void listReserveInfo() {
		//예비교육생 기본조회 메서드
		view.listReserveInfoView();
		
		System.out.printf("[이름] %s\n[주민등록번호] %s\n[연락처] %s\n[주소] %s\n[희망 직무] %s\n[사전 지식] %s\n"
							, rsdto.getName()
							, rsdto.getJumin()
							, rsdto.getTel()
							, rsdto.getAddress()
							, rsdto.getField()
							, rsdto.getKnowledge());
		
		pause();
	}

	
	
	
	public void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
		start();
	}
}
