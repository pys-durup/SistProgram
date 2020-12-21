package com.sist.view;

import java.util.Scanner;

import com.sist.dao.TeacherEvaluationDAO;
import com.sist.dto.StudentDTO;
import com.sist.dto.TeacherEvaluationDTO;

public class StudentView {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	private TeacherEvaluationDAO tdao; // DB작업에 사용할 객체
	private TeacherEvaluationDTO tdto; // 값을 포장할 객체
	
	public StudentView(StudentDTO sdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
		this.tdao = new TeacherEvaluationDAO();
	}
	
	
	
	public void studentMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("교육생 %s님 접속을 환영합니다\n", this.sdto.getName() );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 출결체크 및 조회");
		System.out.println("2. 성적 조회");
		System.out.println("3. 상담 일지");
		System.out.println("4. 교사 평가");
		System.out.println("4. 취업활동 관리");
		System.out.println("5. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("번호를 입력하세요 :");
		
	} 

	public void evaluationMenu() {
		
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
	
	
}
