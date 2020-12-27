package com.sist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.sist.dao.AttendanceDAO;
import com.sist.dao.CourseConsultationDAO;
import com.sist.dao.JobConsultationDAO;
import com.sist.dao.JobInfoDAO;
import com.sist.dao.QualificationDAO;
import com.sist.dao.ScoreDAO;
import com.sist.dao.StudentDAO;
import com.sist.dao.TeacherEvaluationDAO;
import com.sist.dto.AttendanceDTO;
import com.sist.dto.CourseConsultationDTO;
import com.sist.dto.JobConsultationDTO;
import com.sist.dto.JobInfoDTO;
import com.sist.dto.QualificationDTO;
import com.sist.dto.ScoreAndSubjectDTO;
import com.sist.dto.StudentDTO;
import com.sist.dto.StudentsAttendanceDTO;
import com.sist.dto.StudentsRegiCourceDTO;
import com.sist.dto.TeacherEvaluationDTO;
import com.sist.main.Login;
import com.sist.view.StudentView;

/**
 * 이 클래스는 교육생 계정의 모든 기능을 조작하는 클래스이다.
 * @author 김소리
 */


public class StudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	private TeacherEvaluationDAO tdao; // 교사평가 DB작업에 사용할 객체 
	private StudentView view; //출력문 메서드를 모아놓은 객체
	private StudentsRegiCourceDTO srdto;//로그인한 교육생의 수강-수료 테이블 정보
	
	
	
	/**
	 * StudentController 생성자
	 * */
	public StudentController(StudentDTO sdto, StudentsRegiCourceDTO srdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
		this.srdto = srdto; //로그인한 교육생의 교육생테이블, 교육생-수강-수료테이블 정보 담기 (수강번호, 수료번호 필요)
		this.tdao = new TeacherEvaluationDAO();
		this.view = new StudentView(sdto, srdto);  //각 메서드마다 뷰 객체 찍어내지 않고 바로 호출
	}
	
	
	
	
	/**
	 * 교육생 메인메뉴
	 * */
	public void start() {
		
		boolean check = true;
		while (check) {
		
			view.studentMenu(); //학생 메인메뉴 메서드 호출
		
			num = scan.nextLine();
			
			if(num.equals("1")) { //1. 출결체크 및 조회
				studentAttendance();
				break;
			} else if (num.equals("2")) { //2. 성적 조회
				studentScore();
				break;
			} else if (num.equals("3")) { //3. 상담 일지
				studentConsultation();
				break;
			} else if (num.equals("4")) { //4. 교사 평가
				studentEvaluation(); 
				break;
			} else if (num.equals("5")) { //5. 취업활동 관리
				jobMenu();
				break;
			} else {  //6. 로그아웃(교육생 로그인 메서드 호출)
				
				break;
			}
		}
		
	}
	
	
	
	/**
	 * 교육생 취업 관리 메뉴
	 * */
	private void jobMenu() {
		
		view.jobMenuView();
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				qualificationMenu(); //구직활동 관리 메뉴
				break;
			} else if (num.equals("2")) {
				jobInfoMenu(); //취업내역 관리 메뉴
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
	 * 교육생 취업내역정보 메뉴
	 * */

	private void jobInfoMenu() {
		
		view.jobInfoMenuView();
		
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				addJobInfo(); //취업내역 등록
				break;
			} else if (num.equals("2")) {
				listJobInfo(); //취업내역 조회
				break;
			} else if (num.equals("3")) {
				editJobInfo(); //취업내역 수정
				break;
			} else if (num.equals("0")) { //이전으로
				start();
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}

	
	/**
	 * 교육생이 본인의 취업내역을 수정하는 메서드
	 * @param srdto.getCourceCompletNum() 로그인한 교육생의 수료번호
	 * @param JobInfoDTO dto2 입력받은 수정하고자 하는 값
	 * */

	private void editJobInfo() {
		
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 취업내역은 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else {
		
		//취업내역 수정 메서드
		view.editJobInfoView();
		
		JobInfoDTO dto = new JobInfoDTO();
		
		System.out.println("(1) 수정할 취업일자: ");
		System.out.println("   ex) 20201020");
		String startDate = scan.nextLine();
		if (startDate.equals("")) {
			startDate = dto.getStartDate();
		}
		

		System.out.println("(2) 수정할 4대보험 가입여부: ");
		System.out.println("   ex) (가입 / 미가입)");		
		String insurance = scan.nextLine();
		if (insurance.equals("")) {
			insurance = dto.getInsurance();
		}
		
		System.out.println("(3) 수정할 고용 형태: ");
		System.out.println("   ex) (정규직/계약직/전환형 인턴/인턴/프리랜서)");		
		String form = scan.nextLine();
		if (form.equals("")) {
			form = dto.getForm();
		}
		
		System.out.println("(4) 수정할 직무: ");
		String career = scan.nextLine();
		if (career.equals("")) {
			career = dto.getCareer();
		}
		
		System.out.println("(5) 수정할 연봉: ");
		System.out.println("   ex) (2600)");		
		String income = scan.nextLine();
		if (income.equals("")) {
			income = dto.getIncome();
		}
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **수정한 취업내역 재등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		
		if(num.equals("1")) {
			
			JobInfoDAO dao = new JobInfoDAO();
			JobInfoDTO dto2 = new JobInfoDTO();
			
			String completNum = this.srdto.getCourceCompletNum();
			//생성자 srdto의 get메서드로 수료번호 불러오기
			
			dto2.setStartDate(startDate);
			dto2.setInsurance(insurance);
			dto2.setForm(form);
			dto2.setCareer(career);
			dto2.setIncome(income);
			dto2.setCompletNum(completNum);
			
			int result = dao.editJobInfo(dto2);
			
			if (result > 0) {
				System.out.println("취업내역 수정이 완료되었습니다.");
			} else {
				System.out.println("취업내역 수정이 정상적으로 완료되지 않았습니다.");
			}
			
			
		}
		pause(); //취업정보메뉴로 회귀
		
		}
	}


	
	/**
	 * 교육생이 본인의 취업내역을 조회하는 메서드
	 * @param srdto.getCourceCompletNum() 로그인한 교육생의 수료번호
	 * */
	
	private void listJobInfo() {
		
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 취업내역 기능은 수료 이후 이용하실 수 있습니다.**");
			pause();
			
		} else {
		
		
		view.listJobInfoView();
		
		String completNum = this.srdto.getCourceCompletNum();
		//생성자의 get메서드로 수료번호를 선언한다.(매개변수로 사용할 수료번호)
		
		JobInfoDAO dao = new JobInfoDAO();
		ArrayList<JobInfoDTO> list = dao.list(completNum);
 		
		
		for (JobInfoDTO dto : list) {
			System.out.println("----------------------------------------------");
			System.out.printf("[취업일자] %s\n[4대보험 가입여부] %s\n[고용형태] %s\n[직무] %s\n[연봉]%s\n"
								,dto.getStartDate()
								,dto.getInsurance()
								,dto.getForm()
								,dto.getCareer()
								,dto.getIncome());
			
		}
		pause();
		}
	}


	
	
	/**
	 * 교육생이 본인의 취업내역을 등록하는 메서드
	 * @param srdto.getCourceCompletNum() 로그인한 교육생의 수료번호
	 * */
	private void addJobInfo() {
		
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 취업내역은 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("취업내역 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("(1) 취업 일자 입력: ");
		System.out.println("   ex) 20201020");
		String startDate = scan.nextLine();
		System.out.println("(2) 4대보험 가입여부 입력: ");
		System.out.println("   ex) (가입 / 미가입)");
		String insurance = scan.nextLine();
		System.out.println("(3) 고용형태 입력: ");
		System.out.println("   ex) (정규직/계약직/전환형 인턴/인턴/프리랜서)");
		String form = scan.nextLine();
		System.out.println("(4) 직무 입력: ");
		String career = scan.nextLine();
		System.out.println("(5) 연봉 입력: ");
		System.out.println("   ex) (2600)");
		String income = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **작성한 취업내역 등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			JobInfoDAO dao = new JobInfoDAO();
			JobInfoDTO dto = new JobInfoDTO();
			String completNum = srdto.getCourceCompletNum(); 
			//생성자 srdto의 get메서드로 로그인한 교육생의 수료번호를 선언.(매개변수로 사용 예정)
			
			dto.setStartDate(startDate);
			dto.setInsurance(insurance);
			dto.setForm(form); 
			dto.setCareer(career);
			dto.setIncome(income);
			dto.setCompletNum(completNum);
			
			int result = dao.addJobInfo(dto);
			
			if (result > 0) {
				System.out.println("취업내역 등록을 성공하였습니다.");
			} else {
				System.out.println("취업내역이 정상적으로 등록되지 않았습니다.");
			}
			
		} else {
			System.out.println("취업내역 등록은 1번을 눌러주세요. 글이 등록되지 않았습니다.");
		}
		
		pause();
	
		}
	}

	
	
	/*
	 * 구직활동정보 메뉴
	 * */
	private void qualificationMenu() {
		//구직활동 메뉴
		view.qualificationMenu();
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				addqualification(); //구직활동 등록
				break;
			} else if (num.equals("2")) {
				listqualification(); //구직활동 조회
				break;
			} else if (num.equals("3")) {
				editqualification(); //구직활동 수정
				break;
			} else if (num.equals("4")) {
				deletequalification(); //구직활동 삭제
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

	
	/**
	 * 교육생이 본인의 구직활동정보를 삭제하는 메서드
	 * @param this.srdto.getrSeq() 로그인한 교육생의 수강번호
	 * */

	private void deletequalification() {
		//구직정보 삭제 메서드
		view.deletequalificationView();
		String num = scan.nextLine();
		
		
		if (num.equals("1")) {

			String regiNum = this.srdto.getrSeq(); 
			//매개변수로 dao.delete()에 넘길 수강번호
			
			 QualificationDAO dao = new QualificationDAO();
			 int result = dao.deleteQualification(regiNum);
			 
			 if (result > 0) {
				 System.out.println("삭제 성공");
			 } else {
				 System.out.println("삭제 실패");
			 }
			 qualificationMenu(); // 삭제 후, 구직활동정보 메뉴로 되돌아가기
		
		} else if (num.equals("0")) {
			qualificationMenu(); // 구직활동정보 메뉴로 되돌아가기
		} else {
			System.out.println("번호를 잘못 입력하였습니다. 이전 메뉴로 되돌아갑니다.");
			qualificationMenu(); // 구직활동정보 메뉴로 되돌아가기
		}
		
	}

	

	
	/**
	 * 교육생이 본인의 구직활동을 수정하는 메서드
	 * */
	private void editqualification() {
		//구직활동 수정하기 메서드
		view.editqualificationView();
		
		QualificationDTO dto = new QualificationDTO();
		
		System.out.println("(1) 수정할 보유 자격증: ");
		String license = scan.nextLine();
		if (license.equals("")) {
			license = dto.getLicense();
		}
		
		System.out.println("(2) 수정할 이력서 저장 드라이브 주소: ");
		String resume = scan.nextLine();
		if (resume.equals("")) {
			resume = dto.getResume();
		}
		
		System.out.println("(3) 수정할 희망직무: ");
		String Job = scan.nextLine();
		if (Job.equals("")) {
			Job = dto.getResume();
		}
		
		System.out.println("(4) 수정할 깃허브 주소: ");
		String github = scan.nextLine();
		if (github.equals("")) {
			github = dto.getGithub();
		}
		
		System.out.println("(5) 수정할 희망연봉: ");
		String salary = scan.nextLine();
		if (salary.equals("")) {
			salary = dto.getSalary();
		}
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **수정한 구직활동정보 재등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		
		if (num.equals("1")) {
			
			QualificationDAO dao = new QualificationDAO();
			QualificationDTO dto2 = new QualificationDTO();
			
			String regiNum = this.srdto.getrSeq();
			//생성자srdto의 get메서드로 수강번호 가져오기(매개변수로 넘길 예정)
			
			dto2.setLicense(license);
			dto2.setResume(resume);
			dto2.setJob(Job);
			dto2.setGithub(github);
			dto2.setSalary(salary);
			dto2.setRegiNum(regiNum);
			
			int result = dao.editQualification(dto2);
			
			if (result > 0) {
				System.out.println("구직활동정보 등록을 성공하였습니다.");
			} else {
				System.out.println("구직활동정보 등록을 실패하였습니다.");
			}
			
		} else {
			System.out.println("수정은 1번을 눌러주세요. 구직활동정보가 재등록되지 않았습니다.");
		}
		
		pause();
		
	}

	
	
	/**
	 * 교육생이 본인의 구직활동을 조회하는 메서드
	 * @param pregiNum 로그인한 교육생의 수강번호
	 * */

	private void listqualification() {
		//구직활동 조회하기 메서드
		view.listqualificationView();

		String pregiNum = this.srdto.getrSeq(); 
		//생성자로 만든 srdto의 get메서드를 통해 DAO의 메서드로 넘길 매개변수 선언
		
		QualificationDAO dao = new QualificationDAO();
		
		ArrayList<QualificationDTO> list = dao.list(pregiNum);
		
		for (QualificationDTO dto : list) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("[보유자격증] %s\n[이력서] %s\n[희망 직무] %s\n[깃허브 주소] %s\n[희망 연봉] %s\n"
								, dto.getLicense()
								, dto.getResume()
								, dto.getJob()
								, dto.getGithub()
								, dto.getSalary());
		}
		pause();		
		
	}

	
	/**
	 * 교육생이 본인의 구직활동정보를 등록하는 메서드
	 * @param pregiNum 로그인한 교육생의 수강번호
	 * */

	private void addqualification() {
		
		
		if (this.srdto.getqNum() != "0") {
			System.out.println("이미 구직활동정보를 등록하셨습니다. 등록 이후 수정 혹은 삭제만 가능하십니다.");
			pause();
		} {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("구직활동정보 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("(1) 보유자격증 입력: ");
		String license = scan.nextLine();
		System.out.println("(2) 이력서 저장 드라이브 주소 입력: ");
		String resume = scan.nextLine();
		System.out.println("(3) 희망 직무 입력: ");
		String job = scan.nextLine();
		System.out.println("(4) 깃허브 주소 입력: ");
		String github = scan.nextLine();
		System.out.println("(5) 희망연봉 입력(*숫자만 입력): ");
		//연봉 입력시, 숫자(NUMBER)가 아니면 오라클 테이블 지정 자료형크기보다 커서 안 들어간다...(에러난다)
		String salary = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" **작성한 구직활동정보 등록을 위해 1번을 눌러주세요.");
		
		String num = scan.nextLine();
		
		if (num.equals("1")) {
			
			 QualificationDTO dto = new QualificationDTO();
			 QualificationDAO dao = new  QualificationDAO();
			
			 String pregiNum = this.srdto.getrSeq();
			 //생성자로 만든 srdto의 get메서드로 수강과정번호 받아오기
			 
			 dto.setLicense(license);
			 dto.setResume(resume);
			 dto.setJob(job);
			 dto.setGithub(github);
			 dto.setSalary(salary);
			 dto.setRegiNum(pregiNum);
			 
			 int result = dao.addQualification(dto);
			 
			 if (result > 0) {
				 System.out.println("구직활동정보 등록을 완료하였습니다.");
			 } else {
				 System.out.println("구직활동정보 등록을 실패하였습니다.");
			 }
			 
		} else {
			System.out.println("구직활동정보 등록은 1번을 눌러주세요. 글이 등록되지 않았습니다.");
		}
		
		pause();
	
		}

	}


	
	/**
	 * 교육생의 출석 메뉴
	 * */
	private void studentAttendance() {
		view.studentAttendanceMenu();
		
		boolean check = true;
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				checkAttendance(); //출석체크하기
				break;
			} else if (num.equals("2")) {
				listStudentAttendance(); //출결 전체조회하기
				break;
			} else if (num.equals("3")) {
				choiceRangeAttendance(); //출결 선택조회하기
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

	

	
	/**
	 * 교육생이 입력한 기간에 따른 본인의 출석 내역, 근태상황을 확인하는 메서드
	 * @param pstartDate 사용자가 입력한 조회기간 시작일
	 * @param pendDate 사용자가 입력한 조회기간 종료일
	 * */
	private void choiceRangeAttendance() {
		view.choiceRangeAttendanceView();
		
		System.out.println("조회할 기간의 시작일을 입력해주세요.");
		System.out.println("   ex) 20201020");
		String pstartDate = scan.nextLine();
		
		System.out.println("조회할 기간의 종료일을 입력해주세요.");
		System.out.println("   ex) 20201120");
		String pendDate = scan.nextLine();
		
		String pstudentNum = this.sdto.getSeq();
		//생성자로 만든 sdto의 학생번호를 프로시저 매개변수로 사용.
		
		AttendanceDAO dao = new AttendanceDAO();
		
		ArrayList<StudentsAttendanceDTO> listChoice = dao.listChoice(pstudentNum, pstartDate, pendDate); 
		
		System.out.printf("[날짜]\t\t[입실시간]\t\t[퇴실시간]\t\t[출결상태]\n");
		System.out.println();
		for (StudentsAttendanceDTO dto : listChoice) {
			
			System.out.printf("%s\t%s\t%s\t%s\t\n"
								,dto.getDays()
								,dto.getInTime()
								,dto.getOutTime()
								,dto.getDayState());
		}
		pause();
		
	}


	
	/*
	 * 교육생이 본인의 출석을 전체조회하는 메서드
	 * */
	private void listStudentAttendance() {
		view.listAttendance();
	
		String pstudentNum = this.sdto.getSeq();

		AttendanceDAO dao = new AttendanceDAO();
		
		ArrayList<StudentsAttendanceDTO> list = dao.list(pstudentNum);
		
		System.out.printf("[날짜]\t\t[입실시간]\t\t[퇴실시간]\t\t[출결상태]\n");
		System.out.println();
		for (StudentsAttendanceDTO dto : list) {
		
			System.out.printf("%s\t%s\t%s\t%s\t\n",
								dto.getAlldates(),
								dto.getInTime(),
								dto.getOutTime(),
								dto.getAttstate());
		}
		
		pause();
	}


	
	
	/**
	 * 교육생이 출석체크하는 메서드
	 * @param pregiNum 로그인한 교육생의 수강번호
	 * */
	private void checkAttendance() {
		//유효성 검사
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String today = df.format(date); //현재시간 문자열 만들기
		
		Date day = null; //날짜 비교를 위한 변수 초기화
		Date startDate = null;
		Date endDate = null;
		//날짜 변환 parse를 사용하기 위해서 try catch 로 예외처리
		try {
			day = df.parse(today); 
			startDate = df.parse(this.srdto.getStartDate());
			endDate = df.parse(this.srdto.getEndDate());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (day.compareTo(startDate) > 0 && day.compareTo(endDate) < 0) {
			//현재시간이 과정시작일보다 미래 && 현재시간이 과정종료일보다 과거라면
			//출결체크하기
			view.addAttendance();
			
			String num = scan.nextLine();
			
			if (num.equals("1")) { //출석체크 진행
				AttendanceDTO dto = new AttendanceDTO();
				AttendanceDAO dao = new AttendanceDAO();
				
				String pregiNum = srdto.getrSeq(); //프로시저 호출시 필요한 매개변수인 수강번호를 srdto(생성자로 만듦)를 통해 가져오기.
				
				dto.setRegiNum(pregiNum);
				
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
			
			pause();
		
			
		
		} else {
			System.out.println("현재는 교육과정 기간이 아니기 때문에 출석 체크를 할 수 없습니다.");
			pause();
		}

	}

	

	
	/**
	 * 교육생의 상담일지 메뉴
	 * */
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

	
	
	/**
	 * 교육생이 본인의 취업상담 내역을 조회하는 메서드
	 * @param pstudentNum 로그인한 교육생의 번호
	 * */
	private void listJobConsultation() {
		
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


	/**
	 * 교육생이 본인의 수업상담 내역을 조회하는 메서드
	 * @param pstudentNum 로그인한 교육생의 번호
	 * */
	private void listCourseConsultation() {
		
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

	
	/**
	 *교육생이 본인의 성적을 조회하는 메서드
	 *@param pstudentNum 로그인한 교육생의 번호
	 * */
	private void studentScore() {
		
		 view.scoreView();
		 
		 String pstudentNum = this.sdto.getSeq(); 
		 ScoreDAO dao = new ScoreDAO(); //성적 dao 객체 생성
		 
		 ArrayList<ScoreAndSubjectDTO> list = dao.list(pstudentNum);
		 
		 for (ScoreAndSubjectDTO dto : list) {
			 System.out.printf("[과목명] %s\n[출석점수] %s\n[실기점수] %s\n[필기점수] %s\n"
					 			,dto.getName()
					 			,dto.getAttendance()
					 			,dto.getPractice()
					 			,dto.getWrite());

			 System.out.println("----------------------------------------------\n");
		 }
		 
		 System.out.println("");
		 pause();
		 
	}


	
	/**
	 * 교사평가 메뉴 메서드
	 * */
	private void studentEvaluation() {
				
		view.evaluationMenu(); //교사평가메뉴 뷰 메서드 호출
				
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
	
	
	
	/**
	 * 교육생이 본인의 교사평가를 삭제하는 메서드
	 * @param completNum 로그인한 교육생의 수료번호
	 * */
	private void deleteEvaluation() {
	
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 교사평가는 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else {
		
		//교사평가 삭제 메서드
		view.evaluationDelete();
		String num = scan.nextLine();
	
		if (num.equals("1")) { //삭제하기 선택 버튼
			String completNum = this.srdto.getCourceCompletNum();
			int result = tdao.deleteTeacherEvaluation(completNum);
			
				if (result > 0) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
			
			} else if (num.equals("0")) { // 이전 메뉴로 돌아가는 버튼
			studentEvaluation();
		} else {
			System.out.println("번호를 잘못 입력하였습니다. 이전메뉴로 돌아갑니다.");
			studentEvaluation();
		}
		
		pause();
		}
	}

	
	
	/**
	 * 교육생이 본인이 등록한 교사평가를 조회하는 메서드
	 * @param completNum 로그인한 교육생의 수료번호
	 * */
	private void listEvaluation() {
		
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 교사평가는 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else {
		
		//교사평가 조회 메서드
		view.evaluationListView();
		
		String completNum = this.srdto.getCourceCompletNum();
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

	}
	
	
	/**
	 * 교육생이 본인의 교사평가를 수정하는 메서드
	 * @param completNum 로그인한 교육생의 수료번호
	 * */
	private void editEvaluation() {

		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 교사평가는 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else {
		
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
			String completNum = this.srdto.getCourceCompletNum();
		
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
	
		 pause();
			
		}
	}

	
	
	/**
	 * 교육생이 교사평가를 등록하는 메서드
	 * @param completNum 로그인한 교육생의 수료번호
	 * */
	
	private void addEvaluation() {
		
		if (null == this.srdto.getCourceCompletNum()) { //수료번호 null값 체크
			System.out.println("**아직 수료 내역이 확인되지 않았습니다. 교사평가는 수료 이후 작성하실 수 있습니다.**");
			pause();
			
		} else if (this.srdto.getEvalNum() != "0") {
			System.out.println("이미 교사평가를 등록하셨습니다. 등록 이후에는 삭제 혹은 수정만 가능하십니다.");
			pause();
		} else {
		
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
			String completNum = this.srdto.getCourceCompletNum();
			
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
		
		
		 pause();
		}
	}

	
	/**
	 * 각 메서드의 기능 이후, 메인 메뉴로 돌아올 수 있도록 하는 메서드
	 * */
	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
		start();
	}
}
