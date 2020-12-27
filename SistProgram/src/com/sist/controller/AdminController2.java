package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.InterviewResultDAO;
import com.sist.dao.ReserveStudentDAO;
import com.sist.dto.InterviewResultDTO;
import com.sist.dto.MasterDTO;
import com.sist.dto.ReserveStudentDTO;
import com.sist.view.AdminView;

/**
 * 이 클래스는 관리자 계정 기능 중, 예비교육생 관리 기능을 조작하는 클래스이다.
 * @author 김소리
 */

public class AdminController2 {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private AdminView aview;
	
	
	/**
	 * AdminController2 생성자
	 * */
	
	public AdminController2(MasterDTO mdto) {
		
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
		this.aview = new AdminView();
		
	}
	
	
	
	
	/**
	 * 관리자의 예비교육생 관리 메인 메뉴 메서드
	 * */
	public void RStudentManagingMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 합격생 수강상태 관리");
		System.out.println("2. 예비교육생 전체조회");
		System.out.println("3. 교육과정 면접 관리");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) { // 1. 교육과정 면접합격생 수강관리
				enrollment();
				break;
			} else if (num.equals("2")) { //2. 예비교육생 전체조회
				listrStudent(); 
				break;
			} else if (num.equals("3")) { // 3. 교육과정 면접 관리
				managingInterview();
				break;
			} else if (num.equals("0")) { //이전으로
				AdminController ac = new  AdminController(mdto);	 
				ac.start();
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
				pause();
				break;
				
			}
		}
	}
	
	
	/**
	 * 교육과정 면접 관리 메뉴 메서드
	 * */
	
	public void managingInterview() {
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 면접일정 등록하기");
		System.out.println("2. 면접일정 조회하기");
		System.out.println("3. 면접결과 등록하기");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		

		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) { // 1. 면접일정 등록하기
				checkDate();
				break;
			} else if (num.equals("2")) { // 2. 면접일정 조회하기
				listInterviewDate();
				break;
			} else if (num.equals("3")) { //3. 면접결과 등록하기
				addPassOrFail();
				break;
			} else if (num.equals("0")) { //이전으로
				AdminController ac = new  AdminController(mdto);	 
				ac.start();
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
				pause();
				break;
				
			}
		}

		
		
	}



	/**
	 * 관리자가 면접일정 목록을 조회하는 메서드
	 * */
	public void listInterviewDate() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 면접일정 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();	
		
		ReserveStudentDAO dao = new ReserveStudentDAO();
		ArrayList<ReserveStudentDTO> list = dao.scheduleList();
		
		for (ReserveStudentDTO dto : list) {
		
			System.out.printf(
								"---------------------------------------------------\n"+
								"[과정번호] %s\n[면접일] %s\n[과정명] %s\n[과정 시작일] %s\n"
								, dto.getCreatedCourceNum()
								, dto.getInterviewDate()
								, dto.getcName()
								, dto.getStartDate());
		}
		
		detailScheduleList(); //스케줄에 해당하는 예비교육생의 상세정보 조회 메서드 
		
	}




	/**
	 * 관리자가 면접일정 메서드를 통해 접근한 면접일정에 맞는 예비교육생 정보를 조회하는 메서드
	 * @param pcreatedCourceNum 입력한 개설과정번호(매개변수1)
	 * */

	public void detailScheduleList() {
		
		System.out.println("면접신청한 예비교육생을 조회할 교육과정번호 입력: ");
		String pcreatedCourceNum = scan.nextLine(); 
		
		ReserveStudentDAO dao = new ReserveStudentDAO();
		
		ArrayList<ReserveStudentDTO> list = dao.detailedApply(pcreatedCourceNum);
		
		for (ReserveStudentDTO dto : list) {
			System.out.printf(
					"---------------------------------------------------\n"+
					"[과정명] %s\n[교육과정번호] %s\n[예비교육생 이름] %s\n[주민등록번호] %s\n[연락처] %s\n[주소] %s\n"					
								, dto.getcName()
								, dto.getCreatedCourceNum()
								, dto.getName()
								, dto.getJumin()
								, dto.getTel()
								, dto.getAddress());
		}
		
		System.out.println();
	
		
		pause();
	}




	/**
	 * 관리자가 면접결과 등록이 아직 진행되지 않은 예비교육생의 목록을 조회 후, 면접 결과를 입력하는 메서드
	 * @param 입력한 개설과정번호(매개변수1)
	 * @param 입력한 면접결과(매개변수2)
	 * */

	public void addPassOrFail() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 면접결과 등록이 필요한 예비교육생 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();	
		
		ReserveStudentDAO dao = new ReserveStudentDAO();
		
		ArrayList<ReserveStudentDTO> list = dao.finisheInterviewList();
		
		for (ReserveStudentDTO dto : list) {
			System.out.printf(
					"---------------------------------------------------\n"+
					"[신청 교육과정명] %s\n[면접일] %s\n[예비교육생 번호] %s\n[예비교육생 이름] %s\n[주민등록번호] %s\n[연락처] %s\n[주소] %s\n"
							, dto.getcName()
							, dto.getInterviewDate()
							, dto.getSeq()
							, dto.getName()
							, dto.getJumin()
							, dto.getTel()
							, dto.getAddress());
		
		}
		
		System.out.println("면접 결과를 입력할 예비교육생 번호: ");
		String studentNum = scan.nextLine(); 
		
		System.out.println("면접 결과 입력: ");
		System.out.println(" * [합격]은 1, [불합격]은 2를 입력하세요 * ");
		String resultNum = scan.nextLine(); 
		
		if (resultNum.equals("1") || resultNum.equals("2")) {
		
		
			int result = dao.addPassFail(studentNum, resultNum);
			
			if (result > 0) {
				System.out.println("면접 결과가 성공적으로 등록되었습니다.");
			} else {
				System.out.println("면접 결과 등록이 정상적으로 진행되지 않았습니다.");
			}

		} else {
			System.out.println("번호를 잘못 입력하였습니다.");
		}
		
		
		
		pause();
		
	}




	/**
	 * 관리자가 면접 일정 등록이 진행되지 않은 수강생 모집 교육과정을 조회 후, 면접일정을 등록하는 메서드
	 * @param 입력한 개설과정번호(매개변수1)
	 * @param 입력한 면접날짜(매개변수2)
	 * */

	public void checkDate() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 면접 일정 등록이 필요한 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();	
		
		ReserveStudentDAO dao = new ReserveStudentDAO();
		
		ArrayList<ReserveStudentDTO> list = dao.noDateList();
		
		for (ReserveStudentDTO dto : list) {
			System.out.printf(
					"---------------------------------------------------\n"+
					"[교육과정 번호] %s\n[과정명] %s\n[과정시작일] %s\n"					
								, dto.getCreatedCourceNum()
								, dto.getcName()
								, dto.getStartDate());
		}
		
		System.out.println();
		 
		//바로 입력받기
		System.out.println("면접 날짜를 지정할 교육과정 번호 입력: ");
		String pcreatedCourceNum = scan.nextLine(); 
		
		System.out.println("면접 날짜 입력: ");
		System.out.println(" * ex) 20201228");
		String choicedDate = scan.nextLine(); 
		
		int result = dao.addChoicedDate(pcreatedCourceNum, choicedDate);
		
		
			if (result > 0) {
				System.out.println(" **면접 날짜 지정을 완료했습니다.");
			} else {
				System.out.println(" **면접 날짜 지정에 실패하였습니다.");
			}
		
		pause();
	}
	


	/**
	 * 관리자가 예비교육생의 전체목록을 조회하는 메서드
	 * */

	public void listrStudent() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 예비교육생 전체 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();	
		
		ReserveStudentDAO dao = new ReserveStudentDAO();
		
		ArrayList<ReserveStudentDTO> list = dao.list();
		
		for (ReserveStudentDTO dto : list) {
			System.out.printf(
					"---------------------------------------------------\n"+
					"[번호] %s\n[이름] %s\n[주민등록번호] %s\n[연락처] %s\n[주소] %s\n[희망 직무] %s\n[사전 지식] %s\n"
								,dto.getSeq()
								, dto.getName()
								, dto.getJumin()
								, dto.getTel()
								, dto.getAddress()
								, dto.getField()
								, dto.getKnowledge());
		
		}
		
		pause();
	}




	/**
	 * 관리자가 면접에 합격하여 계정전환한 교육생 중 아직 수강상태가 '수강정'인 교육생 목록을 조회 후, 
	 * 수강중으로 상태 전환하는 메서드
	 * @param pstudentNum 수강상태를 변경할 학생번호
	 * */

	public void enrollment() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" *수강전* 교육생 전체 목록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();	
		
		InterviewResultDAO dao = new InterviewResultDAO();
		
		ArrayList<InterviewResultDTO> enrollmentList = dao.enrollmentList();
		//'수강전' 인 교육생 전체 목록을 조회하는 메서드 호출
		
		for (InterviewResultDTO dto : enrollmentList) {
			
			System.out.printf(
					"---------------------------------------------------\n"+
					"[학생번호] %s\n[학생명] %s\n[수강상태] %s\n[과정시작일] %s\n[과정명] %s\n\n"
								, dto.getStudentNum()
								, dto.getrName()
								, dto.getState()
								, dto.getStart()
								, dto.getcName());
		}
		System.out.println();
		System.out.println("** 수강중으로 상태를 변경할 학생번호 입력: ");
		
		String pstudentNum = scan.nextLine(); //매개변수로 넘길 학생번호
		
		int result = dao.editEnrollment(pstudentNum); //수강중으로 상태변경하는 메서드 호출
		
		if (result > 0) {
			System.out.println("지정한 학생의 수강상태가 *수강중* 으로 변경되었습니다.");
		} else {
			System.out.println("수강상태 변경이 정상적으로 완료되지 않았습니다.");
		}
		
		
		pause();
	}




	/**
	 * 각 메서드의 기능 이후, 메인 메뉴로 돌아올 수 있도록 하는 메서드
	 * */
	public void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
		 
		RStudentManagingMenu();
	}
	
	

}
