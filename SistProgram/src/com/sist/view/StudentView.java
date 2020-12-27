package com.sist.view;

import java.util.Scanner;

import com.sist.dao.TeacherEvaluationDAO;
import com.sist.dto.StudentDTO;
import com.sist.dto.StudentsRegiCourceDTO;
import com.sist.dto.TeacherEvaluationDTO;

public class StudentView {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	private StudentsRegiCourceDTO srdto;
	
	public StudentView(StudentDTO sdto, StudentsRegiCourceDTO srdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
		this.srdto = srdto;
	}
	
	
	
	public void studentMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("교육생 %s님 접속을 환영합니다\n", this.sdto.getName() );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("[교육과정] *%s*\n", this.srdto.getcName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 출결체크 및 조회");
		System.out.println("2. 성적 조회");
		System.out.println("3. 상담 일지");
		System.out.println("4. 교사 평가");
		System.out.println("5. 취업활동 관리");
		System.out.println("6. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("번호를 입력하세요 :");
	
	} 

	public void ConsultationMenuView() {
	
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		System.out.println("1. 수업상담 조회");
		System.out.println("2. 취업상담 조회");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}
	
	
	public void evaluationMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");		
		System.out.println("1. 교사평가 등록");
		System.out.println("2. 교사평가 조회");
		System.out.println("3. 교사평가 수정");
		System.out.println("4. 교사평가 삭제");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}
	
	
	public void evaluationDelete() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");				
		System.out.println();
		
		System.out.println(" ** 삭제된 내용은 다시 불러올 수 없습니다. 삭제하시겠습니까?");
		System.out.println("1) 삭제하기 ");
		System.out.println("0) 이전으로");
		
	}
	
	
	public void evaluationListView() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 교사평가 내역**\n", this.sdto.getName());
		System.out.println();
	}
	
	
	public void scoreView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교육과정 성적 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 교육과정 성적**\n", this.sdto.getName());
		System.out.println();
		
		
	}
	
	
	public void editEvaluationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("**수정을 원하지 않는 항목은 enter키를 입력해주세요.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}



	public void listCConsultationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("수업상담 일지 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 수업상담 일지**\n", this.sdto.getName());
		System.out.println();
		
		
	}



	public void listJConsultationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("취업상담 일지 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 취업상담 일지**\n", this.sdto.getName());
		
	}



	public void studentAttendanceMenu() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 출석 체크하기");
		System.out.println("2. 전체출결 확인하기");
		System.out.println("3. 검색하여 출결 확인하기");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}



	public void addAttendance() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("출석 체크하기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님 출석체크하시겠습니까?**\n", this.sdto.getName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 출석 체크하기");
		System.out.println("0. 이전으로");
		
	}



	public void listAttendance() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("전체 출결 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 전체출결 현황**\n", this.sdto.getName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
	}



	public void jobMenuView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 구직활동 관리");
		System.out.println("2. 취업내역 관리");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}



	public void qualificationMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 구직활동 등록");
		System.out.println("2. 구직활동 조회");
		System.out.println("3. 구직활동 수정");
		System.out.println("4. 구직활동 삭제");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}



	public void listqualificationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("구직활동정보 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 구직활동정보**\n", this.sdto.getName());
	}



	public void editqualificationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("구직활동정보 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("**수정을 원하지 않는 항목은 enter키를 입력해주세요.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}



	public void deletequalificationView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("구직활동정보 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");				
		System.out.println();
		
		System.out.println(" ** 삭제된 내용은 다시 불러올 수 없습니다. 삭제하시겠습니까?");
		System.out.println("1) 삭제하기 ");
		System.out.println("0) 이전으로");
		
	}



	public void jobInfoMenuView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 취업내역 등록");
		System.out.println("2. 취업내역 조회");
		System.out.println("3. 취업내역 수정");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
	}



	public void listJobInfoView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("취업내역 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();		
		System.out.printf(" **%s님의 취업내역**\n", this.sdto.getName());
	}



	public void editJobInfoView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("취업내역 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("**수정을 원하지 않는 항목은 enter키를 입력해주세요.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}



	public void choiceRangeAttendanceView() {
	
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf(" **%s님의 기간 내 출결 현황**\n", this.sdto.getName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("[교육과정] *%s*\n", this.srdto.getcName());
		System.out.printf("[과정 기간] *%s ~ %s*\n", this.srdto.getStartDate(), this.srdto.getEndDate());
		System.out.println();
		
		
		
	}
	
	
	
	
}
