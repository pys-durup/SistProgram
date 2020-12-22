package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.AttendanceDAO;
import com.sist.dao.CourseConsultationDAO;
import com.sist.dao.JobConsultationDAO;
import com.sist.dao.ScoreDAO;
import com.sist.dao.StudentDAO;
import com.sist.dao.TeacherEvaluationDAO;
import com.sist.dto.AttendanceDTO;
import com.sist.dto.CourseConsultationDTO;
import com.sist.dto.JobConsultationDTO;
import com.sist.dto.ScoreAndSubjectDTO;
import com.sist.dto.StudentDTO;
import com.sist.dto.StudentsRegiCourceDTO;
import com.sist.dto.TeacherEvaluationDTO;
import com.sist.view.StudentView;

public class StudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	private TeacherEvaluationDAO tdao; // 교사평가 DB작업에 사용할 객체 
	private StudentView view; //출력문 메서드를 모아놓은 객체
	private StudentsRegiCourceDTO srdto;
	
	public StudentController(StudentDTO sdto, StudentsRegiCourceDTO srdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
		this.srdto = srdto;
		this.tdao = new TeacherEvaluationDAO();
		this.view = new StudentView(sdto);  //각 메서드마다 뷰 객체 찍어내지 않고 바로 호출
	}
	
	
	public void start() {
		
		boolean check = true;
		while (check) {
		
			view.studentMenu(); //학생 메인메뉴 메서드 호출
		
			num = scan.nextLine();
			
			if(num.equals("1")) { //1. 출결체크 및 조회
				studentAttendance();
			} else if (num.equals("2")) { //2. 성적 조회
				studentScore();
			} else if (num.equals("3")) { //3. 상담 일지
				studentConsultation();
			} else if (num.equals("4")) { //4. 교사 평가
				studentEvaluation(); 
				break;
			} else if (num.equals("5")) { //5. 취업활동 관리
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}
	
	
	private void studentAttendance() {
		view.studentAttendanceMenu();
		
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				checkAttendance(); //출석체크하기
				break;
			} else if (num.equals("2")) {
				//listStudentAttendance(); //출결 전체조회하기
				break;
			} else if (num.equals("0")) {
				start(); //학생 메인메뉴로 회귀
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
		
	}

	

	private void checkAttendance() {
		view.addAttendance();
		//System.out.println("수강번호");
		//StudentsRegiCourceDTO srdto =  new StudentsRegiCourceDTO();
		//String pregiNum = srdto.getrSeq(); //프로시저 호출시 필요한 매개변수인 수강번호를 srdto를 통해 가져오기.
		
		String num = scan.nextLine();
		
		if (num.equals("1")) { //출석체크 진행
			AttendanceDTO dto = new AttendanceDTO();
			AttendanceDAO dao = new AttendanceDAO();
			
			//StudentsRegiCourceDTO srdto =  new StudentsRegiCourceDTO();
			String pregiNum = srdto.getrSeq(); //프로시저 호출시 필요한 매개변수인 수강번호를 srdto를 통해 가져오기.
			
			
			dto.setRegiNum(pregiNum); // 이상하게도 pregiNum자체가 뜨지 않는다...(null)
			
			int result = dao.addAttendance(dto); //매개변수를 담은 dto를 dao의 메서드로 넘기며 호출
			
			if (result > 0) {
				System.out.println("출석체크를 하셨습니다.");
			} else {
				System.out.println("출석체크가 정상적으로 되지 않았습니다.");
			}
			
		} else if (num.equals("0")) { // //출석메뉴 뒤로 가기
			studentAttendance();
		} else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			pause();
		}
		
		
	}


	private void studentConsultation() {
		view.ConsultationMenuView();
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				listCourseConsultation(); //수업상담 조회
				break;
			} else if (num.equals("2")) {
				listJobConsultation(); //취업상담 조회
				break;
			} else if (num.equals("0")) {
				start(); //학생 메인메뉴로 회귀
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
		
	}


	private void listJobConsultation() {
		//취업상담 내역 조회 메서드
		view.listJConsultationView();
		String pstudentNum = this.sdto.getSeq();
		JobConsultationDAO dao = new JobConsultationDAO();
		
		ArrayList<JobConsultationDTO> list = dao.list(pstudentNum);
		
		System.out.printf("[상담일]\t\t\t[상담 내용]\n");
		for (JobConsultationDTO dto : list) {
			System.out.printf("%s\t\t%s\n"
								,dto.getConsDate()
								,dto.getContent());
			
		}
		System.out.println();
		pause();
		
	}


	private void listCourseConsultation() {
		//수업상담 내역 조회 메서드
		view.listCConsultationView();
		String pstudentNum = this.sdto.getSeq();
		CourseConsultationDAO dao = new CourseConsultationDAO();
		
		ArrayList<CourseConsultationDTO> list = dao.list(pstudentNum);
		
		System.out.printf("[상담일]\t\t\t[상담 카테고리]\t\t[상담 내용]\n");
		for (CourseConsultationDTO dto : list) {
			System.out.printf("%s\t\t%s\t\t\t%s\n"
							,dto.getConsultDate()
							,dto.getReason()
							,dto.getContent());
	
		}
		System.out.println();
		pause();
		
	}


	private void studentScore() {
		 view.scoreView();
		 
		
		 String pstudentNum = this.sdto.getSeq(); 
		 ScoreDAO dao = new ScoreDAO(); //성적 dao 객체 생성
		 
		 ArrayList<ScoreAndSubjectDTO> list = dao.list(pstudentNum);
		 
		 System.out.printf("[과목명]\t\t\t\t\t\t\t[출석점수]\t\t[실기점수]\t\t[필기점수]\t\n");
		 for (ScoreAndSubjectDTO dto : list) {
			 System.out.printf("%s\t\t\t\t\t\t %s\t\t%s\t\t%s\n"
					 			,dto.getName()
					 			,dto.getAttendance()
					 			,dto.getPractice()
					 			,dto.getWrite());
			 
		 }
		 
		 System.out.println("");
		 pause();
		 
	}


	private void studentEvaluation() {
				
		view.evaluationMenu(); //학생 메인메뉴 메서드 호출
				
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				addEvaluation(); //교사평가 등록
				break;
			} else if (num.equals("2")) {
				listEvaluation(); //교사평가 조회
				break;
			} else if (num.equals("3")) {
				editEvaluation(); //교사평가 수정
				break;
			} else if (num.equals("4")) {
				deleteEvaluation(); //교사평가 삭제
			} else if (num.equals("0")) {
				start();
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}
	
	
	private void deleteEvaluation() {
		
		view.evaluationDelete();
		String num = scan.nextLine();
	
		if (num.equals("1")) { //삭제하기 선택 버튼
			String completNum = sdto.getSeq(); 
			int result = tdao.deleteTeacherEvaluation(completNum);
			
			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			studentEvaluation(); // 삭제 행위 이후, 교사평가 메뉴로 돌아간다.
		} else if (num.equals("0")) { // 이전 메뉴로 돌아가는 버튼
			studentEvaluation();
		} else {
			System.out.println("잘못 입력하셨습니다. 이전메뉴로 돌아갑니다.");
			studentEvaluation();
		}
		
		
		
		
	}

	private void listEvaluation() {
		
		view.evaluationListView();
		
		String completNum = sdto.getSeq(); 
		ArrayList<TeacherEvaluationDTO> list = tdao.list(completNum);
 		
		for (TeacherEvaluationDTO dto : list) {
			System.out.printf(" (1)학습자료 만족도: %s점\n (2)교사-학생 간 소통 만족도: %s점\n (3)취업준비관련 만족도: %s점\n (4)수업 시간분배 만족도: %s점\n (5)전반적인 수업 만족도:%s점\n"
								,dto.getMaterials()
								,dto.getCommunication()
								,dto.getJobPreparing()
								,dto.getDivisionTime()
								,dto.getTotalPoint());
			System.out.println();
			
		}
	
	
		pause();
	}

	
	
	
	private void editEvaluation() {

		
		view.editEvaluationView();
		
		TeacherEvaluationDTO dto = new TeacherEvaluationDTO();
		
		System.out.println("(1) 수정할 학습자료 만족도 (1~10): ");
		String materials = scan.nextLine();
		if (materials.equals("")) {
			materials = dto.getMaterials();
		}
		
		System.out.println("(2) 수정할 교사 - 학생 간 소통 만족도 (1~10): ");
		String communication = scan.nextLine();
		if (communication.equals("")) {
			communication = dto.getCommunication();
		}
		
		System.out.println("(3) 수정할 취업 준비 관련 만족도 (1~10): ");
		String jobPreparing = scan.nextLine();
		if (jobPreparing.equals("")) {
			jobPreparing = dto.getJobPreparing();
		}
		
		
		System.out.println("(4) 수정할 수업의 시간 분배 만족도 (1~10): ");
		String divisionTime = scan.nextLine();
		if (divisionTime.equals("")) {
			divisionTime = dto.getDivisionTime();
		}
		
		System.out.println("(5) 수정할 전반적인 수업의 만족도 (1~10): ");
		String totalPoint = scan.nextLine();
		if (totalPoint.equals("")) {
			totalPoint = dto.getTotalPoint();
		}
		
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **수정한 교사평가 재등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			
			TeacherEvaluationDTO tdto = new TeacherEvaluationDTO();
			TeacherEvaluationDAO tdao = new TeacherEvaluationDAO();
			String completNum = sdto.getSeq(); 
			// 일단 수료번호랑 학생번호가 같으니... 임시방편(그냥 나중에 학생+수료테이블이랑 합친 dto한개 더 만들어야 된다. )
			
			tdto.setMaterials(materials);
			tdto.setCommunication(communication);
			tdto.setJobPreparing(jobPreparing);
			tdto.setDivisionTime(divisionTime);
			tdto.setTotalPoint(totalPoint);
			tdto.setCompletNum(completNum);
			
			int editResult =  tdao.editTeacherEvaluation(tdto); //dao 매서드 호출.
			if (editResult > 0) {
				System.out.println("평가등록을 완료하였습니다.");
			} else {
				System.out.println("평가등록을 실패하였습니다.");
			}

			
		} else {
			System.out.println("평가수정은 1번을 눌러주세요. 평가가 재등록되지 않았습니다.");
		}
		
	}

	
	
	private void addEvaluation() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("(1) 학습자료 만족도 (1~10): ");
		String materials = scan.nextLine();
		System.out.println("(2) 교사 - 학생 간 소통 만족도 (1~10): ");
		String communication = scan.nextLine();
		System.out.println("(3) 취업 준비 관련 만족도 (1~10): ");
		String jobPreparing = scan.nextLine();
		System.out.println("(4) 수업의 시간 분배 만족도 (1~10): ");
		String divisionTime = scan.nextLine();
		System.out.println("(5) 전반적인 수업의 만족도 (1~10): ");
		String totalPoint = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **작성한 교사평가 등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		
		if (num.equals("1")) {
			
			TeacherEvaluationDTO tdto = new TeacherEvaluationDTO();
			TeacherEvaluationDAO tdao = new TeacherEvaluationDAO();
			String completNum = sdto.getSeq(); 
			// 일단 수료번호랑 학생번호가 같으니... 임시방편(그냥 나중에 학생+수료테이블이랑 합친 dto한개 더 만들어야 된다. )
			
			tdto.setMaterials(materials);
			tdto.setCommunication(communication);
			tdto.setJobPreparing(jobPreparing);
			tdto.setDivisionTime(divisionTime);
			tdto.setTotalPoint(totalPoint);
			tdto.setCompletNum(completNum);
			
			int addResult =  tdao.addTeacherEvaluation(tdto); //dao 매서드 호출.
			
			//System.out.println(tdto);
			
			
			if (addResult > 0) {
				System.out.println("평가등록을 완료하였습니다.");
			} else {
				System.out.println("평가등록을 실패하였습니다.");
			}
			
			
		} else {
			System.out.println("평가등록은 1번을 눌러주세요. 평가가 등록되지 않았습니다.");
		}
		
		 studentEvaluation();
		
		
	}

	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
}
