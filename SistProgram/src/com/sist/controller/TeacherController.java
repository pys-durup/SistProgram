package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.CompletionStudentDAO;
import com.sist.dao.CourseStudentListDAO;
import com.sist.dao.InterviewDataDAO;
import com.sist.dao.JobConsultationListDAO;
import com.sist.dao.ScoreListDAO;
import com.sist.dao.SetScoreDAO;
import com.sist.dao.StudentScoreListDAO;
import com.sist.dao.TeacherEvaluationListDAO;
import com.sist.dao.TeacherScheduleDAO;
import com.sist.dao.InterviewsEvaluationDAO;
import com.sist.dao.setScoreListDAO;
import com.sist.dao.CConsultationDAO;
import com.sist.dto.CompletionStudentDTO;
import com.sist.dto.CourseStudentListDTO;
import com.sist.dto.InterviewDataDTO;
import com.sist.dto.JobConsultationListDTO;
import com.sist.dto.ScoreListDTO;
import com.sist.dto.SetScoreDTO;
import com.sist.dto.StudentScoreListDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.dto.TeacherDTO;
import com.sist.dto.TeacherEvaluationListDTO;
import com.sist.dto.TeacherScheduleDTO;
import com.sist.dto.setScoreListDTO;
import com.sist.dto.InterviewsEvaluationDTO;
import com.sist.dto.CConsultationDTO;
import com.sist.view.TeacherView;
import com.sist.dto.InterviewDataDTO;




//실데이터 교사 id:01625483365 pw:1111111  
public class TeacherController {
	
	private static TeacherScheduleDAO tsdao;
	private static CourseStudentListDAO csdao;
	private static TeacherEvaluationListDAO tedao;
	private static JobConsultationListDAO jcdao;
	private static CompletionStudentDAO cdao;
	private static SetScoreDAO ssdao;
	private static setScoreListDAO ssldao;
	private static ScoreListDAO sldao; 	
	private static StudentScoreListDAO stldao;
	private static InterviewDataDAO ivdao;
	private static InterviewsEvaluationDAO ivedao;
	private static CConsultationDAO ccdao;
	
	static {
		tsdao = new TeacherScheduleDAO(); //강의계획조회		
		csdao = new CourseStudentListDAO();//과정학생조회
		tedao = new TeacherEvaluationListDAO();//교사평가조회
		jcdao = new JobConsultationListDAO(); //취업상담조회
		cdao = new CompletionStudentDAO();  //수료자 리스트(취업상담조회 가능자)
		ssdao = new SetScoreDAO(); // 과목배점관리
		ssldao = new setScoreListDAO();
		sldao = new ScoreListDAO(); //성적리스트
		stldao = new StudentScoreListDAO(); //학생별 성적리스트
		ivdao = new InterviewDataDAO(); //모의면접데이터
		ivedao = new InterviewsEvaluationDAO(); //모의면접 평가
		ccdao = new CConsultationDAO(); //상담일지관리


		
	}

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private TeacherDTO tdto; // 로그인한 계정의 정보를 담을 객체
	private TeacherView view;
	
	public TeacherController(TeacherDTO tdto) {
		this.tdto = tdto; // 로그인한 강사의 계정 정보를 담는다
		this.view = new TeacherView(tdto);
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			view.teacherMenu();
			num = scan.nextLine();
			
			if(num.equals("1")) {
				courseStudentList();
				
			} else if (num.equals("2")) { 
				setScoreList();
			} else if (num.equals("3")) { 
				scoreList();
			} else if (num.equals("4")) { 
				
			} else if (num.equals("5")) { 
				TeacherEvaluation();
				pause();
			} else if (num.equals("6")) { 
				CConsultation();
			} else if (num.equals("7")) { 
				JobConsultation();
				pause();
			} else if (num.equals("8")) { 
				Interview();
			} else if (num.equals("9")) { 	
				
			} else if (num.equals("10")) { 
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
		
		
		for (TeacherScheduleDTO dto : list) {
			System.out.printf("_________________________________________________________\n"
					+ "[과정번호] %s\n[교사명] %s\n[과정명] %s\n[과목번호] %s\n[과목명] %s\n[책이름] %s\n[강의실] %s\n[개강일] %s\n[종강일] %s\n[과정인원수] %s\n[상태] %s\n"
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
		System.out.println("[번호][학생명][주민번호]  [전화번호]    [등록일] [수료상태]");
		for (CourseStudentListDTO csdto : list) {
			System.out.printf("%s %s %s %s %s %s\n"
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
		System.out.println("[상담번호][학생번호][학생명][교사명][과정번호]\t\t[과정명]\t\t\t\t\t\t[상담내용]               \t[상담일]\t\t[수강상태]");
		for(JobConsultationListDTO jcdto : list) {
			System.out.printf("%-10s%-10s%-4s%-7s%-8s%-35s%-30s\t%-13s%7s\n"
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
		view.JobConsultationMenu();
		num = scan.nextLine();
		
		while (check) {
		if (num.equals("1")) {			
			addJobConsultation();//취업상담추가
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
	
	//취업상담 작성
	private void addJobConsultation() {
		System.out.println("취업상담가능한 학생리스트(수료자)");
		System.out.println("[상담번호][학생명][교사명][과정번호]\t\t     [과정명]\t\t\t   [개강일]\t[종강일]\t\t[수강상태]");
		CompletionStudent();
		
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
	
	//취업상담가능한 수료자들 list
	private void CompletionStudent() {
		
		ArrayList<CompletionStudentDTO> list = cdao.list(this.tdto.getSeq());
		for (CompletionStudentDTO cdto : list) {
			System.out.printf("%-9s%-5s%-5s %-3s\t%-30s\t %-13s %-11s     %5s\n"
					,cdto.getStudentNum()
					,cdto.getStudentName()
					,cdto.getTeacherName()
					,cdto.getCourseNum()
					,cdto.getCourseName()
					,cdto.getStartDate()
					,cdto.getEndDate()
					,cdto.getRegistate());
		}
		System.out.println();
		
		
		
	}
		
	//취업상담내역 수정
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
	//취업상담내역 삭제
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
	//과목 배점 리스트
	private void setScoreList() {
		
		ArrayList<setScoreListDTO> list = ssldao.list(this.tdto.getSeq());
		
		for (setScoreListDTO ssldto : list) {
			System.out.printf("_______________________________________________________________________________________________________________________________________________\n"
					+"[개강과목번호] %s\n[과목명] %s\n[책이름] %s\n[출석배점] %s\n[필기배점] %s\n[실기배점] %s\n[과목개강일] %s\n[과목종강일] %s\n[과정명] %s\n[과정개강일] %s\n[과정종강일] %s\n[강의실번호]%s\n"					
					,ssldto.getSubjectNum()
					,ssldto.getSubjectName()
					,ssldto.getBookName()
					,ssldto.getAttendance()
					,ssldto.getWrite()
					,ssldto.getPractice()
					,ssldto.getSubjectStartdate()
					,ssldto.getSubjectEnddate()
					,ssldto.getCourseName()
					,ssldto.getCourseStartdate()
					,ssldto.getCourseEnddate()
					,ssldto.getRoomName());
			
			
			
		}
		System.out.println();
		
		boolean check = true;
		view.setScoreMenu();
		num = scan.nextLine();
		
		while (check) {
		if (num.equals("1")) {			
			addSetScore(); //배점과목 추가
		} else if (num.equals("2")) {
			editSetScore();
		} else if (num.equals("3")) {
			deleteSetScore(); //배점과목 삭제
		} else if (num.equals("4")) {
			start();//메인화면
		} else {
			System.out.println("잘못된 입력입니다.");
			check = false;
		}
		
		}
	}
	
	//배점과목 추가
	private void addSetScore() {
		
		System.out.println("배점 과목추가하기");
		
		System.out.println("개강과목번호: ");
		String makeSubjectNum = scan.nextLine();
		
		System.out.println("출결배점: ");
		String attendance = scan.nextLine();
		
		System.out.println("필기배점: ");
		String write = scan.nextLine();
				
		System.out.println("실기배점: ");
		String practice = scan.nextLine();
		
		SetScoreDTO dto = new SetScoreDTO();
		dto.setAttendance(attendance);
		dto.setWrite(write);
		dto.setPractice(practice);
		dto.setMakeSubjectNum(makeSubjectNum);
		
		int result = ssdao.addSetScore(dto);
		
		if(result == 1) {
			System.out.println("과목배점 추가 완료");
			
		} else {
			System.out.println("과목배점 추가 실패");
		}
		
		pause();
		start();
	}
	//배점과목삭제	
	private void deleteSetScore() {
		
		System.out.println("삭제할 번호: ");
		String seq = scan.nextLine(); // 삭제할번호
		
		int result = ssdao.deleteSetScore(seq);
		
		if (result == 1) {
			System.out.println("배점과목 삭제성공");
			
		} else {
			System.out.println("배점과목 삭제실패");
		}
		
		pause();
		start();
	}
	//배점수정
	private void editSetScore()	{
		
		System.out.println("수정할 번호 입력: ");
		String seq = scan.nextLine();
		
		
		System.out.println("** 수정을 하지 않는 컬럼은 그냥 엔터를 입력하시오.");
		System.out.println("출석배점 수정: ");
		String attendance = scan.nextLine();
		
		SetScoreDTO dto = new SetScoreDTO();
		
		if(attendance.equals("")) {
			attendance = dto.getAttendance();
		}
		
		
		System.out.println("필기배점 수정: ");
		String write = scan.nextLine();
		
		if(write.equals("")) {
			write = dto.getWrite();		
		}
		
		System.out.println("실기배점 수정: ");
		String practice = scan.nextLine();
		
		if(practice.equals("")) {
			practice = dto.getPractice();		
		}
		
		
		SetScoreDTO dto2 = new SetScoreDTO();				
		dto2.setSeq(seq);
		dto2.setAttendance(attendance);
		dto2.setPractice(practice);
		dto2.setWrite(write);
		
		
		int result = ssdao.editSetScore(dto2);
		
		if (result > 0) {
			System.out.println("배점 수정 완료");
		} else {
			System.out.println("배점 수정 실패");
		}
		pause();
		start();
		
		
	}
	//성적리스트
	private void scoreList() {
		
		ArrayList<setScoreListDTO> list = sldao.list(this.tdto.getSeq());
		
		for (setScoreListDTO sldto : list) {
			System.out.printf("_____________________________________\n"
					+ "[개강과목번호]%s\n[과목명]%s\n[책이름]%s\n[출석배점]%s\n[필기배점]%s\n[실기배점]%s\n[과목개강일]%s\n[과목종강일]%s\n[과정명]%s\n[과정개강일]%s\n[과정종강일]%s\n[강의실번호]%s\n[성적등록여부]%s\n"					
					,sldto.getSubjectNum()
					,sldto.getSubjectName()
					,sldto.getBookName()
					,sldto.getAttendance()
					,sldto.getWrite()
					,sldto.getPractice()
					,sldto.getSubjectStartdate()
					,sldto.getSubjectEnddate()
					,sldto.getCourseName()
					,sldto.getCourseStartdate()
					,sldto.getCourseEnddate()
					,sldto.getRoomName()
					,sldto.getScoreStatus());
			
			
			
		}
		System.out.println();
		
		boolean check = true;
		System.out.println();
		System.out.println("1.성적관리");
		System.out.println("2.뒤로가기");
		num = scan.nextLine();
		
		while (check) {
		if (num.equals("1")) {			
			studentScoreList();
		} else if (num.equals("2")) {
			start();//메인화면
		} else {
			System.out.println("잘못된 입력입니다.");
			check = false;
		}
		
		}
			System.out.println();
		
		
		
		
	}//학생 성적리스트
	private void studentScoreList() {
		System.out.println("개강과목번호 선택");
		String pseq = scan.nextLine();
		
		ArrayList<StudentScoreListDTO> list = stldao.list(pseq, this.tdto.getSeq());
		System.out.println("[성적번호][학생명][출석점수][실기점수][필기점수][수강상태]");
		for (StudentScoreListDTO stldto : list) {
			System.out.printf("%s\t   %s\t%s\t%s\t%s \t%s\n"
					,stldto.getScoreSeq()
					,stldto.getStudentName()					
					,stldto.getAttendance()
					,stldto.getPractice()
					,stldto.getWriter()
					,stldto.getRegistate());			
		}
		System.out.println();
		
		boolean check = true;
		System.out.println();
		System.out.println("1.성적입력");
		System.out.println("2.뒤로가기");
		num = scan.nextLine();
		
		while (check) {
		if (num.equals("1")) {			
			editStudentScore();
		} else if (num.equals("2")) {
			start();//메인화면
		} else {
			System.out.println("잘못된 입력입니다.");
			check = false;
		}
		
		}
		
		
	}
	

private void editStudentScore()	{
		
		System.out.println("수정할 성적번호 입력: ");
		String seq = scan.nextLine();
		
				
		System.out.println("출석점수 입력: ");
		String attendance = scan.nextLine();
		
		StudentScoreListDTO dto = new StudentScoreListDTO();
		
		if(attendance.equals("")) {
			attendance = dto.getAttendance();
		}
		
		
		System.out.println("필기점수 입력: ");
		String write = scan.nextLine();
		
		if(write.equals("")) {
			write = dto.getWriter();		
		}
		
		System.out.println("실기점수 입력: ");
		String practice = scan.nextLine();
		
		if(practice.equals("")) {
			practice = dto.getPractice();		
		}
		
		
		StudentScoreListDTO dto2 = new StudentScoreListDTO();				
		dto2.setSeq(seq);
		dto2.setAttendance(attendance);
		dto2.setPractice(practice);
		dto2.setWriter(write);
		
		
		int result = stldao.editStudentScore(dto2);
		
		if (result > 0) {
			System.out.println("성적입력 완료");
		} else {
			System.out.println("성적입력 실패");
		}
		pause();
		start();
}

	private void Interview() {
		// 모의 면접 관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[모의 면접 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 모의 면접 평가");
			System.out.println("2. 모의 면접 데이터");
			System.out.println("3. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {				
				InterviewEvaluation();
			} else if (num.equals("2")) { 
				InterviewData();
			} else if (num.equals("3")) { 
				break;
			
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
		}
		}
	}

	private void InterviewEvaluation() {
		// 모의 면접 평가
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[모의 면접 평가]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 모의 면접 평가 조회");
			System.out.println("2. 모의 면접 평가 추가");
			System.out.println("3. 모의 면접 평가 수정");
			System.out.println("4. 모의 면접 평가 삭제");
			System.out.println("5. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if(num.equals("1")) {				
				listInterviewsEvaluation();
			} else if (num.equals("2")) { 
				addInterviewsEvaluation();
			} else if (num.equals("3")) { 
				editInterviewsEvaluation();
			} else if (num.equals("4")) { 
				deleteInterviewsEvaluation();
			} else if (num.equals("5")) { 
				break;
			
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
		}
		}
	}

	private void listInterviewsEvaluation() {
		// 모의 면접 평가 조회
		boolean check = true;
		while (check) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 평가 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t교육생이름\t교사이름\t면접질문\t면접평가\t면접점수");
		
		ArrayList<InterviewsEvaluationDTO> list = ivedao.list();
		
			for(InterviewsEvaluationDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getSname()
									, dto.getTname()
									, dto.getQuestion()
									, dto.getEvaluation()
									, dto.getScore());
					
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			pause();
			break;
		}
		
	}

	private void addInterviewsEvaluation() {
		// 모의 면접 평가 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 평가 추가]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		System.out.print("면접 평가 : ");
		String evaluation = scan.nextLine();

		System.out.print("면접 점수 : ");
		String score = scan.nextLine();

		InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
		dto.setEvaluation(evaluation);
		dto.setScore(score);
		
		int result = ivedao.add(dto);
		
		if(result == 1) {
			System.out.println("모의 면접 평가 추가 성공");
		}else {
			System.out.println("모의 면접 평가 추가 실패");
		}
		
		pause();
	
	}

	private void editInterviewsEvaluation() {
		// 모의 면접 평가 수정
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 평가 수정]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t교육생이름\t교사이름\t면접질문\t면접평가\t면접점수");
		
		
		ArrayList<InterviewsEvaluationDTO> list = ivedao.list();
		
		for(InterviewsEvaluationDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getSname()
								, dto.getTname()
								, dto.getQuestion()
								, dto.getEvaluation()
								, dto.getScore());
		}
		System.out.println();
		System.out.print("수정할 모의 면접 평가 번호 : ");
		String seq = scan.nextLine();
		 
		InterviewsEvaluationDTO dto = ivedao.get(seq);
		
		System.out.println();
		System.out.println("면접 평가 : " + dto.getEvaluation());
		System.out.println("면접 점수 : " + dto.getScore());
		System.out.println();
		
		System.out.println("━━━━━━수정을 하지 않는 컬럼은 그냥 엔터를 입력하세요.━━━━━━");
	
		
		System.out.print("수정할 면접 평가 : ");
		String evaluation = scan.nextLine();
		
		if(evaluation.equals("")) {
		evaluation = dto.getEvaluation();
		}
		
		
		System.out.print("수정할 면접 점수 : ");
		String score = scan.nextLine();
		
		if(score.equals("")) {
		score = dto.getScore();		
		}
		
		
		InterviewsEvaluationDTO dto2 = new InterviewsEvaluationDTO();
		
		dto2.setEvaluation(evaluation);
		dto2.setScore(score);
		
		int result = ivedao.edit(dto2);
		
		if(result>0) {
			System.out.println("모의 면접 평가 수정 성공");
		}else {
			System.out.println("모의 면접 평가 수정 실패");
		}
		
		
		pause();		
		
	}

	private void deleteInterviewsEvaluation() {
		// 모의 면접 평가 삭제
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 평가 삭제]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t교육생이름\t교사이름\t면접질문\t면접평가\t면접점수");
		
		ArrayList<InterviewsEvaluationDTO> list = ivedao.list(); 

		for (InterviewsEvaluationDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getSname()
								, dto.getTname()
								, dto.getQuestion()
								, dto.getEvaluation()
								, dto.getScore());
		}
		System.out.println();
		
		System.out.print("삭제할 모의 면접 평가 번호 : ");
		String seq = scan.nextLine();
		
		int result = ivedao.delete(seq);
		 
		if(result>0) {
			System.out.println("모의 면접 평가 삭제 성공");
		}else {
			System.out.println("모의 면접 평가 삭제 실패");
		}
			
		
		pause();
		
	}

	private void InterviewData() {
		// 모의 면접 데이터 관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[모의 면접 데이터]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 모의 면접 데이터 조회");
			System.out.println("2. 모의 면접 데이터 추가");
			System.out.println("3. 모의 면접 데이터 수정");
			System.out.println("4. 모의 면접 데이터 삭제");
			System.out.println("5. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {				
				listInterviewData();
			} else if (num.equals("2")) { 
				addInterviewData();
			} else if (num.equals("3")) { 
				editInterviewData();
			} else if (num.equals("4")) { 
				deleteInterviewData();
			} else if (num.equals("5")) { 
				break;
			
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
		}
		}
	}

	private void listInterviewData() {
		// 모의 면접 데이터 조회
		
		boolean check = true;
		while (check) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 데이터 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t면접질문\t\t\t\t점수기준");

		ArrayList<InterviewDataDTO> list = ivdao.list();
		
		for(InterviewDataDTO dto : list) {
			System.out.printf("%s\t%-25s\t%s\n"
								, dto.getSeq()
								, dto.getQuestion()
								, dto.getStandard());
				
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		pause();
		break;
		}
		
	}

	private void addInterviewData() {
		// 모의 면접 데이터 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 데이터 추가]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("질문 : ");
		String question = scan.nextLine();

		System.out.print("점수기준 : ");
		String standard = scan.nextLine();
		
		InterviewDataDTO dto = new InterviewDataDTO();
		
		dto.setQuestion(question);
		dto.setStandard(standard);
		
		int result = ivdao.add(dto);
		
		if(result == 1) {
			System.out.println("모의 면접 데이터 추가 성공");
		}else {
			System.out.println("모의 면접 데이터 추가 실패");
		}
				
		pause();
		
	}

	private void editInterviewData() {
		// 모의 면접 데이터 수정
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 데이터 수정]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		ArrayList<InterviewDataDTO> list = ivdao.list();
		
		for(InterviewDataDTO dto : list) {
			System.out.printf("%s\t%-25s\t%s\n"
								, dto.getSeq()
								, dto.getQuestion()
								, dto.getStandard());
		}
		System.out.println();
		System.out.print("수정할 면접 데이터 번호 : ");
		String seq = scan.nextLine();
		
		InterviewDataDTO dto = ivdao.get(seq);
		
		System.out.println();
		System.out.println("질문 : " + dto.getQuestion());
		System.out.println("점수기준 : " + dto.getStandard());
		System.out.println();
		
		System.out.println("━━━━수정을 하지 않는 컬럼은 그냥 엔터를 입력하세요━━━━");
		
		System.out.print("수정할 질문 : ");
		String question = scan.nextLine();
		
		if(question.equals("")) {
			question = dto.getQuestion();
		}
						
		System.out.print("수정할 점수기준 : ");
		String standard = scan.nextLine();
		
		if(standard.equals("")) {
			standard = dto.getStandard();
		}
		
		InterviewDataDTO dto2 = new InterviewDataDTO();
		
		dto2.setQuestion(question);
		dto2.setStandard(standard);
				
		int result = ivdao.edit(dto2);
		
		if(result>0) {
			System.out.println("모의 면접 데이터 수정 성공");
		}else {
			System.out.println("모의 면접 데이터 수정 실패");
		}
				
		pause();
	}

	private void deleteInterviewData() {
		// 모의 면접 데이터 삭제
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[모의 면접 데이터 삭제]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t면접질문\t\t\t\t점수기준");
		
		ArrayList<InterviewDataDTO> list = ivdao.list(); 

		for (InterviewDataDTO dto : list) {
			System.out.printf("%s\t%-25s\t%s\n", dto.getSeq(), dto.getQuestion(), dto.getStandard());
		}
		System.out.println();
		
		System.out.print("삭제할 모의 면접 데이터 번호 : ");
		String seq = scan.nextLine();
		
		int result = ivdao.delete(seq);
		
		if(result>0) {
			System.out.println("모의 면접 데이터 삭제 성공");
		}else {
			System.out.println("모의 면접 데이터 삭제 실패");
		}
		
		
		pause();
		
	}
	private void CConsultation() {
		// 상담일지 관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[상담 일지 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 상담 일지 작성");
			System.out.println("2. 상담 일지 조회");
			System.out.println("3. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if(num.equals("1")) {				
				addCConsultation();
			} else if (num.equals("2")) { 
				listCConsultation();
			} else if (num.equals("3")) { 
				break;
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
		}
		}
		
	}

	private void addCConsultation() {
		// 상담 일지 작성
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 일지 작성]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("상담날짜 : ");
		String date = scan.nextLine();
		
		System.out.print("상담내용 : ");
		String content = scan.nextLine();
		
		
		CConsultationDTO dto = new CConsultationDTO();
		dto.setDate(date);
		dto.setContent(content);
		
		int result = ccdao.add(dto);
		
		if(result == 1) {
			System.out.println("상담 일지 작성 성공");
		}else {
			System.out.println("상담 일지 작성 실패");
		}
				
		pause();

		
	}

	private void listCConsultation() {
		// 상담 일지 조회
		
		boolean check = true;
		while (check) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 일지 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t면접질문\t\t\t\t점수기준");

		ArrayList<CConsultationDTO> list = ccdao.list();
		
		for(CConsultationDTO dto : list) {
			System.out.printf("%s\t%-25s\t%s\n"
								, dto.getSeq()
								, dto.getSname()
								, dto.getSjseq()
								, dto.getSjname()
								, dto.getCoursedate()
								, dto.getDate()
								, dto.getReason()
								, dto.getContent());
				
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		pause();
		break;
		}
		
	}

	
}   
