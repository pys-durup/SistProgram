package com.sist.view;

import java.util.Scanner;

import com.sist.dto.JobConsultationListDTO;
import com.sist.dto.TeacherDTO;

public class TeacherView {
	

	private static Scanner scan = new Scanner(System.in);
	private TeacherDTO tdto;
	private JobConsultationListDTO jcldto;
	
	public TeacherView(TeacherDTO tdto) {
		this.tdto = tdto; // 로그인한 강사의 계정 정보를 담는다		
	}
	
	
	public void teacherMenu() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf("강사 %s님 접속을 환영합니다\n", this.tdto.getName());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 강의 스케줄 조회");
		System.out.println("2. 배점 입출력");
		System.out.println("3. 성적 입출력");
		System.out.println("4. 출결 관리");
		System.out.println("5. 교사 평가 조회");
		System.out.println("6. 상담 일지 관리");
		System.out.println("7. 취업 상담 관리");
		System.out.println("8. 모의 면접 관리");
		System.out.println("9. 데이터 통계");
		System.out.println("10. 로그아웃");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("번호를 입력하세요 :");
		
		
	}
	
	public void JobConsultationMenu() {
		
		System.out.println();
		System.out.println("1.상담내역 작성");
		System.out.println("2.상담내역 수정");
		System.out.println("3.상담내역 삭제");
		System.out.println("4.뒤로가기");
		
				
	}
	
	
	
	public void setScoreMenu() {
		
		System.out.println();
		System.out.println("1.과목배점 작성");
		System.out.println("2.과목배점 수정");
		System.out.println("3.과목배점 삭제");
		System.out.println("4.뒤로가기");
		
		
	}
	

}


