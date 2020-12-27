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


/**
 * 이 클래스는 예비교육생 계정의 모든 기능을 조작하는 클래스이다.
 * @author 김소리
 */

public class ReserveStudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private ReserveStudentDTO rsdto; // 로그인한 계정의 정보를 담을 객체
	private ReserveStudentView view;
	private InterviewResultDTO idto; //로그인한 계정의 면접결과 정보를 담는 객체
	
	
	/**
	 * ReserveStudentController 생성자
	 * */
	
	public ReserveStudentController(ReserveStudentDTO rsdto, InterviewResultDTO idto) {
		this.rsdto = rsdto; // 로그인한 예비교육생의 계정 정보를 담는다
		this.view = new ReserveStudentView(rsdto);
		this.idto = idto;
	}
	
	
	/**
	 * 예비교육생 메인메뉴
	 * */
	
	
	public void start() {
		
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
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				
			}
		}
		
	}
	
	
	/**
	 * 예비교육생이 자신의 면접결과를 확인하는 메서드
	 * @param prstudentNum 생성자 rsdto의 get메서드로 불러온 예비학생번호 매개변수
	 * @param result 생성자 idto의 get메서드로 불러온 예비학생의 합격여부 매개변수
	 * 
	 * */
	public void myResult() {
	
		view.myResultView();
		
		String prstudentNum = this.rsdto.getSeq(); 
	
		
		if (this.idto.getResult() == null) { //면접 신청을 하지 않아 null값인 회원을 예외처리
			
			System.out.println("**아직 면접에 대한 결과가 없습니다.**");
			pause();

		} else {
		
			String result = this.idto.getResult(); //면접 결과 변수.
			
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
			if (result.equals("합격")) {
				 System.out.println();
				 System.out.println("합격하셨습니다. 예비교육생에서 교육생 계정 전환을 위해 번호 1번을 눌러주세요.");
				 
				 String num = scan.nextLine(); 
					 if (num.equals("1")) {	 
						 migration();	 //계정전환 메서드 호출
					 } else {
						 System.out.println("번호를 잘못 입력하였습니다. 이전 메뉴로 돌아갑니다.");
						 start();
					 }
					
			} else {
				pause();
			}

		}
	}

	
	
	/**
	 * 합격한 예비교육생이 교육생으로 계정을 전환하는 메서드
	 * @param  seq 로그인한 예비교육생의 번호
	 * */
	private void migration() {
		
		view.migrationView();
		
		num = scan.nextLine();
		
		if (num.equals("1")) {

			ReserveStudentDAO dao = new ReserveStudentDAO();
			String seq = this.rsdto.getSeq(); //생성자 rsdto의 get메서드로 로그인한 학생번호를 가져와 매개변수 선언
			int result = dao.addMigration(seq);
			
			if (result > 0) {
				
				System.out.println("교육생 계정전환이 성공적으로 완료되었습니다. 로그아웃 후 교육생계정으로 다시 로그인해주세요.");
				view.migrationCelebration();
				
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
	
	
	
	
	/**
	 * 예비교육생이 교육과정 수강면접을 신청하는 메서드
	 * @param createdCourceNum 개설과정번호(매개변수1)
	 * @param 생성자 rsdto의 get메서드로 받아온 로그인한 예비교육생 번호(매개변수2)
	 * */

	public void addApply() {
		//유효성검사 (이미 면접 합격 결과가 있는 회원, 면접 결과값 null && 신청한 과정번호가 있는 사람)
		//면접은 불합격한 사람 & 아직 면접을 신청하지 않은 회원에 한해 가능함.

		
		if (this.idto.getResult() == null && this.idto.getcSeq() != null) {
			System.out.println("**이전 면접 신청 내역이 확인되었습니다. 신청한 교육과정 면접 일정을 확인해주세요.**");
			pause();
		} 
		else if ("합격".equals(this.idto.getResult())) {
			System.out.println("**면접 합격 내역이 있습니다. 확인 후, 교육생 계정으로 전환해주세요.**");
			pause();
		} else {
		
		
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
		 }

	
	
	
	/**
	 * 예비교육생의 기본정보를 관리하는 메뉴 메서드
	 * */
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

	
	
	/**
	 * 예비교육생이 기본정보를 수정하는 메서드
	 * */
	
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

	
	
	/**
	 * 예비교육생이 본인의 기본정보를 조회하는 메서드
	 * */
	
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

	
	
	/**
	 * 각 메서드의 기능 이후, 메인 메뉴로 돌아올 수 있도록 하는 메서드
	 * */
	public void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
		start();
	}
}
