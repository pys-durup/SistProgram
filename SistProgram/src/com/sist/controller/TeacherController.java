package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.CourseStudentListDAO;
import com.sist.dao.TeacherEvaluationListDAO;
import com.sist.dao.TeacherScheduleDAO;
import com.sist.dto.CourseStudentListDTO;
import com.sist.dto.TeacherDTO;
import com.sist.dto.TeacherEvaluationListDTO;
import com.sist.dto.TeacherScheduleDTO;






public class TeacherController {
	
	private static TeacherScheduleDAO tsdao;
	private static CourseStudentListDAO csdao;
	private static TeacherEvaluationListDAO tedao;
	
	static {
		tsdao = new TeacherScheduleDAO(); //강의계획조회		
		csdao = new CourseStudentListDAO();//과정학생조회
		tedao = new TeacherEvaluationListDAO();//교사평가조회
	}

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private TeacherDTO tdto; // 로그인한 계정의 정보를 담을 객체
	
	public TeacherController(TeacherDTO tdto) {
		this.tdto = tdto; // 로그인한 강사의 계정 정보를 담는다
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("강사 %s님 접속을 환영합니다\n", this.tdto.getName());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 강의 스케줄 조회");
			System.out.println("2. 배점 입출력");
			System.out.println("3. 성적 입출력");
			System.out.println("4. 출결 관리");
			System.out.println("5. 교사 평가 조회");
			System.out.println("6. 상담 일지 관리");
			System.out.println("7. 사후처리 조회");
			System.out.println("8. 데이터 통계");
			System.out.println("9. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				checkSchedule();
				pause();
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				
			} else if (num.equals("5")) { 
				TeacherEvaluation();
				pause();
			} else if (num.equals("6")) { 
				
			} else if (num.equals("7")) { 
				
			} else if (num.equals("8")) { 
				
			} else if (num.equals("9")) { 
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
	//01625483365 로그인 휴대폰번호
	private void teacherSchedule() {
		
		
		ArrayList<TeacherScheduleDTO> list = tsdao.selectList(this.tdto.getSeq());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[과정번호][교사명]\t\t\t[과정명]\t\t      \t  [과목번호]\t\t\t[과목명]\t\t\t    [책이름]\t\t    [강의실][개강일] [종강일]\t[과정인원수][상태]");
		for (TeacherScheduleDTO dto : list) {
			System.out.printf("%s    \t  %s \t%s \t%s \t%s \t\t       %s      \t%s %s %s %s %s\n"
					,dto.getCourseNum()
					,dto.getTeacherName()
					,dto.getCourseName()
					,dto.getSubjectNum()
					,dto.getSubject()
					,dto.getBook()
					,dto.getRoom()
					,dto.getStartDate()
					,dto.getEndDate()
					,dto.getTotalStudent()
					,dto.getCourseStatus());					
		}
		
		System.out.println();
				
	}
	
	
	private void checkSchedule() {
		teacherSchedule();
		System.out.println("과목별 교육생조회 (과목번호선택1~21): ");
		String pseq = scan.nextLine();
		
		ArrayList<CourseStudentListDTO> list = csdao.cslist(pseq);
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[번호][학생명][주민번호]      [전화번호]     [등록일]  [수료상태]");
		for (CourseStudentListDTO csdto : list) {
			System.out.printf("%s, %s, %s, %s, %s, %s\n"
					,csdto.getSeq()
					,csdto.getName()
					,csdto.getJumin()
					,csdto.getTel()
					,csdto.getRegdate()
					,csdto.getRegistate());
		}
		System.out.println();
		
	}
	
	private void TeacherEvaluation() {
				
		ArrayList<TeacherEvaluationListDTO> list = tedao.telist(this.tdto.getSeq());
		System.out.println("[번호]\t\t\t[과정명]\t\t\t    [과정번호]\t[개강일]\t\t[종강일]\t  [평가참여수][자료만족도][소통만족도][취업만족도][시간배분만족도]");
		for (TeacherEvaluationListDTO tedto : list) {
			System.out.printf("%s\t %s\t %s\t %s\t %s\t %s\t %s\t \t%s\t   %s\t\t %s\n"
					,tedto.getSeq()
					,tedto.getName()
					,tedto.getCourseNum()
					,tedto.getStartDate()
					,tedto.getEndDate()
					,tedto.getTotalStudent()
					,tedto.getMaterials()
					,tedto.getCommunication()
					,tedto.getJobPreparing()
					,tedto.getDivisionTime()			
					);
		}
		System.out.println();
		
		
		
		
	}
	
	
	
	
	
	
	
}
