package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.CourseStudentListDAO;
import com.sist.dao.JobConsultationListDAO;
import com.sist.dao.TeacherEvaluationListDAO;
import com.sist.dao.TeacherScheduleDAO;
import com.sist.dto.CourseStudentListDTO;
import com.sist.dto.JobConsultationListDTO;
import com.sist.dto.TeacherDTO;
import com.sist.dto.TeacherEvaluationListDTO;
import com.sist.dto.TeacherScheduleDTO;






//실데이터 교사 id:01625483365 pw:1111111  
public class TeacherController {
	
	private static TeacherScheduleDAO tsdao;
	private static CourseStudentListDAO csdao;
	private static TeacherEvaluationListDAO tedao;
	private static JobConsultationListDAO jcdao;
	
	static {
		tsdao = new TeacherScheduleDAO(); //강의계획조회		
		csdao = new CourseStudentListDAO();//과정학생조회
		tedao = new TeacherEvaluationListDAO();//교사평가조회
		jcdao = new JobConsultationListDAO(); //취업상담조회
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
				courseStudentList();
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				
			} else if (num.equals("5")) { 
				TeacherEvaluation();
				pause();
			} else if (num.equals("6")) { 
				JobConsultation();
				pause();
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
	
	
	//강의스케줄
	private void teacherSchedule() {
		
		
		ArrayList<TeacherScheduleDTO> list = tsdao.selectList(this.tdto.getSeq());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[과정번호][교사명]\t\t\t[과정명]\t\t      \t  [과목번호]\t\t\t[과목명]\t\t\t    [책이름]\t\t    [강의실][개강일] [종강일] [과정인원수][상태]");
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
	
	//강의스케줄 + 과정교육생조회
	private void courseStudentList() {
		
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
	
	//교사평가
	private void TeacherEvaluation() {
				
		ArrayList<TeacherEvaluationListDTO> list = tedao.telist(this.tdto.getSeq());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
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
	
	//취업상담 (수료자대상)
	private void JobConsultation() {
		
		ArrayList<JobConsultationListDTO> list = jcdao.jslist(this.tdto.getSeq());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담번호][학생번호][학생명][교사명][과정번호]\t\t[과정명]\t\t\t\t\t\t[상담내용]               [상담일][수강상태]");
		for(JobConsultationListDTO jcdto : list) {
			System.out.printf("%-10s%-10s%-4s%-7s%-8s%-35s%-30s%-11s%-4s\n"
					,jcdto.getConsultationNum()
					,jcdto.getStudentNum()
					,jcdto.getStudentName()
					,jcdto.getTeacherName()
					,jcdto.getCourseNum()
					,jcdto.getCourseName()
					,jcdto.getContent()
					,jcdto.getConsdate()
					,jcdto.getRegistate());
		}
		
		boolean check = true;
		System.out.println();
		System.out.println("1.상담내역 작성");
		System.out.println("2.상담내역 수정");
		System.out.println("3.상담내역 삭제");
		System.out.println("4.뒤로가기");
		num = scan.nextLine();
		
		while (check) {
		if (num.equals("1")) {			
			addJobConsultation();//취업상담내역조회
		} else if (num.equals("2")) {
			editJobConsultation();//상담내역 수정
		} else if (num.equals("3")) {
			deleteJobConsultation();//상담내역 삭제
		} else if (num.equals("4")) {
			start();//메인화면
		} else {
			System.out.println("잘못된 입력입니다.");
			check = false;
		}
		
		}//while
			
		}
	
	
	private void addJobConsultation() {
		
		System.out.println("상담내역 추가하기");
		
		System.out.println("상담한 학생번호: ");
		String num = scan.nextLine();
		
		System.out.println("상담내용: ");
		String content = scan.nextLine();
		
		JobConsultationListDTO dto = new JobConsultationListDTO ();
		dto.setStudentNum(num);
		dto.setContent(content);
		
		int result = jcdao.addJobConsulting(dto);
		
		if (result ==1) {
			System.out.println("상담내역 작성 완료");
		} else {
			System.out.println("상담내역 작성 실패");
		}
		
		pause();
		JobConsultation();
	}	
	
	
	
	
	
	
	
	
	//취업상담 수정
	private void editJobConsultation() {
		
		
		System.out.println("수정할 상담번호: ");
		String seq = scan.nextLine();
		
		JobConsultationListDTO dto = jcdao.get(this.tdto.getSeq(),seq);
		
		System.out.println();
		System.out.println("상담번호: " + dto.getConsultationNum());
		System.out.println("상담학생번호: " + dto.getStudentNum());
		System.out.println("상담학생이름: " + dto.getStudentName());
		System.out.println("상담내용: " + dto.getContent());
		System.out.println();
		
		System.out.println("** 수정을 하지 않는 컬럼은 그냥 엔터를 입력하시오.");
		System.out.println("상담학생번호 수정: ");
		String studentNum = scan.nextLine();
		
		if(studentNum.equals("")) {
			studentNum = dto.getStudentNum();
		}
		//studentNum -> 바꾼번호 or 예전번호
		
		System.out.println("수정할 상담내용: ");
		String content = scan.nextLine();
		
		if(content.equals("")) {
			content = dto.getContent();			
		}
		//content -> 바꾼상담내용 or 예전상담내용
		
		
		JobConsultationListDTO dto2 = new JobConsultationListDTO();				
		dto2.setStudentNum(studentNum);
		dto2.setContent(content);
		dto2.setConsultationNum(seq);
		
		int result = jcdao.editJobConsulting(dto2);
		
		if (result > 0) {
			System.out.println("상담내역 수정 완료");
		} else {
			System.out.println("상담내역 수정 실패");
		}
		pause();
		JobConsultation();
	}	
	
	private void deleteJobConsultation() {
		
		System.out.println("삭제할 상담번호: ");
		String seq = scan.nextLine();
		
		int result = jcdao.deleteJobConsulting(seq);
		
		if(result > 0) {
			System.out.println("주소록 삭제 성공");
		} else {
			System.out.println("주소록 삭제 실패");
		}
		
		pause();
		JobConsultation();
		
	}
	
	
	
	
}
