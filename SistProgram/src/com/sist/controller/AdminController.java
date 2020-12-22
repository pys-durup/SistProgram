package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.CourseDAO;
import com.sist.dao.JobActivitiesDAO;
import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.RecommendDAO;
import com.sist.dao.StudentDAO;
import com.sist.dao.StudentlistDAO;
import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.AbleTStudentScoreListDTO;
import com.sist.dto.CompletStudentListDTO;
import com.sist.dto.CourseDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.MasterDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.dto.TalentedStudentListDTO;
import com.sist.view.AdminView;


public class AdminController {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private AdminView aview;

	private StudentlistDAO sldao;
	private StudentDAO sdao;
	private LinkCompanyDAO lcdao;
	private TalentedStudentDAO tsdao;
	private RecommendDAO rdao;
	private CourseDAO csdao;
	private JobActivitiesDAO jadao;



	public AdminController(MasterDTO mdto) {
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
		this.aview = new AdminView();
		this.sdao = new StudentDAO();
		this.lcdao = new LinkCompanyDAO();
		this.tsdao = new TalentedStudentDAO();
		this.rdao = new RecommendDAO();
		this.csdao = new CourseDAO();
		this.jadao = new JobActivitiesDAO();
	}
	
	public void start() {
		
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("관리자 %s님 접속을 환영합니다\n", this.mdto.getId());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 기초정보 관리");
			System.out.println("2. 개설과정 관리");
			System.out.println("3. 개설과목 관리");
			System.out.println("4. 출결 관리");
			System.out.println("5. 성적 관리");
			System.out.println("6. 취업현황 조회");
			System.out.println("7. 취업지원 관리");
			System.out.println("8. 데이터 통계 관리");
			System.out.println("9. 상담일지 관리");
			System.out.println("0. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				basicinfoManagement(); //기초정보 관리
			} else if (num.equals("2")) { 
				makecourseManagement(); //개설 과정 관리
			} else if (num.equals("3")) { 
				makesubjectManagement(); //개설 과목 관리 
			} else if (num.equals("4")) { 
				attendanceManagement(); // 출결 관리 - 박영수
			} else if (num.equals("5")) { 
				scoreManagement();
			} else if (num.equals("6")) { 
				jobactivitiesManagement(); // 취업현황 조회 - 박영수
			} else if (num.equals("7")) {
				jobSupportManagement(); // 취업지원 관리 - 박영수
			} else if (num.equals("8")) { 
				dataStatisticsManagement(); // 데이터 통계 관리 - 박영수
			} else if (num.equals("9")) { 
				CourseconsultationManagement();

			} else if (num.equals("0")) {
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		

	}
	

	private void CourseconsultationManagement() {
		// 상담관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[상담 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 상담 조회");
			System.out.println("2. 상담 추가");
			System.out.println("3. 상담 수정");
			System.out.println("4. 상담 삭제");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			

				if(num.equals("1")) {
					CourseconsultationList();
				}else if(num.equals("2")) {
					CourseconsultationAdd();
				}else if(num.equals("3")) {
					CourseconsultationEdit();
				}else if(num.equals("4")) {
					CourseconsultationDelete();					
				}else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}
		}	
	}

	private void CourseconsultationList() {
		// 관리자 - 상담 관리 - 상담 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		ArrayList<StudentlistDTO> list = sldao.list(null);
		
		for(StudentlistDTO dto : list) {
			System.out.printf("%s, %s, %s, %s\n"
								, dto.getSeq()
								, dto.getName()
								, dto.getJumin()
								, dto.getRegiState());
      
		System.out.println();
		}
    }

  
	

	private void CourseconsultationAdd() {
		// 관리자 - 상담 관리 - 상담 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 추가]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.println("상담날짜 : ");
		String consultDate = scan.nextLine();
		
		System.out.println("상담내용 : ");
		String content = scan.nextLine();
		
		System.out.println("교사번호 : ");
		String teacherNum = scan.nextLine();
		
		System.out.println("개설과목번호 : ");
		String makeSubjectNum = scan.nextLine();
		
		System.out.println("상담사유번호 : ");
		String reasonNum = scan.nextLine();
		
		System.out.println("수강번호 : ");
		String regiNum = scan.nextLine();
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
//		System.out.println("상담을 추가하시려면 1번을 입력하세요.");
//		System.out.print("번호입력");
//		num = scan.nextLine();
//		
//		if(num.equals("1")) {
//			
//			CourseConsultationDTO dto = new CourseConsultationDTO();
//			dto.setConsultDate(consultDate);
//			dto.setContent(content);
//			dto.setTeacherNum(teacherNum);						
//			dto.setMakeSubjectNum(makeSubjectNum);
//			dto.setResonNum(reasonNum);
//			dto.setRegiNum(regiNum);
//			
//			int result = dao.add(dto);
//			
//			if(result == 1) {
//				System.out.println("상담 추가 성공입니다.");
//			}else {
//				System.out.println("상담 추가 실패입니다.");
//			}
//			
//		}else {
//			System.out.println("상담 추가 실패입니다.");
//			System.out.println("상담을 추가하시려면 1번을 입력하세요.");
//		}
		
		
	}

	private void CourseconsultationEdit() {
		// 관리자 - 상담 관리 - 상담 수정
		
	}

	private void CourseconsultationDelete() {
		// 관리자 - 상담 관리 - 상담 삭제
		
	}

	private void scoreManagement() {
		// 성적 관리
		boolean check = true;
		while (check) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[성적 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 성적 조회");
			System.out.println("2. 성적 추가");
			System.out.println("3. 성적 수정");
			System.out.println("4. 성적 삭제");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
					
				if(num.equals("1")) {
															
				}else if(num.equals("2")) {
					
				}else if(num.equals("3")) {
						
				}else if(num.equals("4")) {					
					
				}else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}
				
			}
		}
  
  private void dataStatisticsManagement() {
		// 데이터 통계 관리
	}

	/**
	 * 취업지원 관리 메뉴
	 */
	private void jobSupportManagement() {
		// 취업지원 관리
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("취업지원 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 연계기업 관리");
			System.out.println("2. 추천인재 관리");
			System.out.println("3. 기업에 인재추천");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				enterpriseManagement();
			} else if (num.equals("2")) {
				talentedStudentManagement();
			} else if (num.equals("3")) {
				recommendManagement();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 연계 기업 관리 메뉴
	 */
	private void enterpriseManagement() {
		// 취업지원 관리 - 연계기업 관리
		while(true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계기업 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 연계기업 조회");
			System.out.println("2. 연계기업 검색");
			System.out.println("3. 연계기업 추가");
			System.out.println("4. 연계기업 수정");
			System.out.println("5. 연계기업 삭제");
			System.out.println("6. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) { 
				enterpriseList();
			} else if (num.equals("2")) {
				enterpriseSearch();
			} else if (num.equals("3")) {
				enterpriseAdd();
			} else if (num.equals("4")) {
				enterpriseEdit();
			} else if (num.equals("5")) {
				enterpriseDelete();
			} else if (num.equals("6")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 연계 기업 조회
	 */
	private void enterpriseList() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 조회
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;

		while (true) {	
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("                                                     연계 기업 목록");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");
			
			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
//			System.out.println("가져온 레코드의 개수" + list.size());
			if(list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}
			
			
//			System.out.println("현재 페이지 : " + nowPage);
			for(int i= startNum ; i < endNum ; i++) {
				
				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n"
						,list.get(i).getSeq() 
						,list.get(i).getName()
						,list.get(i).getAddress()
						,list.get(i).getDepartment()
						,list.get(i).getSalary());	
			}

			
//			for(LinkCompanyDTO dto : list) {
//			System.out.printf("%s\t%s\t%s\t%s\t\n"
//					,dto.getSeq() 
//					,dto.getName()
//					,dto.getAddress()
//					,dto.getDepartment()
//					,dto.getSalary());
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				if(nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if(nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
	
			} else if (num.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}	
	}

	/**
	 * 연계 기업 검색
	 */
	private void enterpriseSearch() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 검색
		while(true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계 기업명으로 검색");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("검색어를 입력하세요 :");
			num = scan.nextLine();
			
			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(num);
			if(list.size() != 0) {
				System.out.println("번호\t회사이름\t\t\t주소\t\t\t\t\t\t부서\t\t\t제시연봉");
				for(LinkCompanyDTO dto : list) {
					System.out.printf("%2s\t%-15s\t\t%-30s\t%-15s\t%7s\n"
							,dto.getSeq() 
							,dto.getName()
							,dto.getAddress()
							,dto.getDepartment()
							,dto.getSalary());
				}

			} else {
				System.out.println("해당 검색어로 검색된 결과가 없습니다");
			}
	
			System.out.println("1. 다시 검색하기 2. 뒤로가기");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 연계 기업 추가
	 */
	private void enterpriseAdd() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("연계기업 추가");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("기업이름 : ");
		String name = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("주소 : ");
		String address = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("부서 : ");
		String departmemt = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("제시연봉 : ");
		String salary = scan.nextLine();
		
		// 추가할 정보를 LinkCompanyDTO 객체에 담아 전달
		LinkCompanyDTO dto = new LinkCompanyDTO();
		dto.setName(name);
		dto.setAddress(address);
		dto.setDepartment(departmemt);
		dto.setSalary(salary);
		
		int result = lcdao.linkCompanyAdd(dto);
		
		if (result == 1) {
			System.out.println("주소록 추가 성공");
		} else {
			System.out.println("주소록 추가 실패");
		}
		pause();
		
	}


	/**
	 * 연계 기업 수정
	 */
	private void enterpriseEdit() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 수정
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("연계기업 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		while (true) {	
//			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//			System.out.println("연계 기업 목록");
//			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");
			
			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
			if(list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}
			
			for(int i= startNum ; i < endNum ; i++) {
				
				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n"
						,list.get(i).getSeq() 
						,list.get(i).getName()
						,list.get(i).getAddress()
						,list.get(i).getDepartment()
						,list.get(i).getSalary());	
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 수정하기 4. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				if(nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if(nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
	
			} else if (num.equals("3")) {
				// 수정 진행
				System.out.print("수정할 기업의 번호를 입력하세요 :");
				num = scan.nextLine();
				
				// seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
				LinkCompanyDTO dto = lcdao.getLinkCompany(num); 
				
				// 수정할 기업의 정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 기업의 정보");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("이름 : " + dto.getName());
				System.out.println("주소 : " + dto.getAddress());
				System.out.println("부서 : " + dto.getDepartment());
				System.out.println("연봉 : " + dto.getSalary());
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 이름 : ");
				String name = scan.nextLine();
				
				if(name.equals("")) {
					name = dto.getName();
				}
				
				System.out.println("수정할 주소 : ");
				String address = scan.nextLine();
				
				if(address.equals("")) {
					address = dto.getAddress();
				}
				
				System.out.println("수정할 부서 : ");
				String department = scan.nextLine();
				
				if(department.equals("")) {
					department = dto.getDepartment();
				}
				
				System.out.println("수정할 연봉 : ");
				String salary = scan.nextLine();
				
				if(salary.equals("")) {
					salary = dto.getSalary();
				}
				
				
				// editdto객체에는 수정된 레코드 값을 넣는다
				LinkCompanyDTO editdto = new LinkCompanyDTO();
				editdto.setSeq(dto.getSeq());
				editdto.setName(name);
				editdto.setAddress(address);
				editdto.setDepartment(department);
				editdto.setSalary(salary);
				
				int result = lcdao.linkCompanyEdit(editdto);
				
				if (result > 0 ) { 
					System.out.println("기업 수정 성공");
				} else {
					System.out.println("기업 수정 실패");
				}
					
				
		
				pause();
				break;
				
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		
		}
		
	}

	/**
	 * 연계 기업 삭제
	 */
	private void enterpriseDelete() {
		
		// 취업지원 관리 - 연계기업 관리 - 연계기업 삭제
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;
		
//		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//		System.out.println("연계기업 삭제");
//		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
		while (true) {	
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계 기업 목록");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");
			
			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
			if(list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}
			
			for(int i= startNum ; i < endNum ; i++) {
				
				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n"
						,list.get(i).getSeq() 
						,list.get(i).getName()
						,list.get(i).getAddress()
						,list.get(i).getDepartment()
						,list.get(i).getSalary());	
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 삭제하기 4. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				if(nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if(nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
	
			} else if (num.equals("3")) {
				// 삭제 진행
				System.out.print("삭제할 기업의 번호를 입력하세요 :");
				num = scan.nextLine();
				
				// 기업을 삭제하는 메서드
				int result = lcdao.linkCompanyDelete(num); 
				
				if(result > 0) {
					System.out.println("기업 삭제 성공");
				} else {
					System.out.println("기업 삭제 실패");
				}
				pause();
				break;
				
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		
		}
	
	}

	/**
	 * 추천 인재 관리 메뉴
	 */
	private void talentedStudentManagement() {
		// 취업지원 관리 - 추천인재 관리
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천인재 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추천인재 조회");
			System.out.println("2. 추천인재 검색");
			System.out.println("3. 추천인재 추가");
			System.out.println("4. 추천인재 수정");
			System.out.println("5. 추천인재 삭제");
			System.out.println("6. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				talentedStudenList();
			} else if (num.equals("2")) {
				talentedStudenearch();
			} else if (num.equals("3")) {
				talentedStudenAdd();
			} else if (num.equals("4")) {
				talentedStudenEdit();
			} else if (num.equals("5")) {
				talentedStudenDelete();
			} else if (num.equals("6")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}

	}
	/**
	 * 추천 인재 조회
	 */
	private void talentedStudenList() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		ArrayList<TalentedStudentListDTO> list = tsdao.talentedStudenList(null);
		
		for (TalentedStudentListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getTel()
									, dto.getPortfolio()
									, dto.getReason());
		}
		
		pause();
		
	}

	/**
	 * 추천 인재 검색
	 */
	private void talentedStudenearch() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 검색
		//취업지원 관리 - 추천인재 관리 - 추천 인재 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 검색");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("검색할 번호를 입력하세요 :");
		num = scan.nextLine();
		
		aview.TalentedStudentListView();
		
		pause();
		
	}
	/**
	 * 추천 인재 추가
	 */
	private void talentedStudenAdd() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 추가
		
		while(true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천 인재 추가");
			System.out.println("수료생중 성적이 높은 10명");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]  [이름]  [필기]  [실기]  [출결]");
			ArrayList<AbleTStudentScoreListDTO> list = tsdao.ableTStudentScoreList();
			
			for(AbleTStudentScoreListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n"
						, dto.getReginum()
						, dto.getName()
						, dto.getWriter()
						, dto.getPractice()
						, dto.getAttendance());
			}
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추가하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				// 추가하기 진행
				System.out.print("추가할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();
				System.out.print("포트폴리오 주소 입력 : ");
				String portfolio = scan.nextLine();
				System.out.print("인재 선정 이유 입력 : ");
				String reason = scan.nextLine();
				
				if(num.equals("") || portfolio.equals("") || reason.equals("")) {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}
				
				int result = tsdao.talentedStudenAdd(num, portfolio, reason);
				
				if (result > 0) {
					System.out.println("인재 추가 성공");
				} else {
					System.out.println("인재 추가 실패");
				}
				
				pause();
				break;
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
			
		}
		
	}
	/**
	 * 추천 인재 수정
	 */
	private void talentedStudenEdit() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 수정
		while(true) {
			aview.TalentedStudentListView();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 수정하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				// 수정하기 진행
				System.out.print("수정할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();
				
				// seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
				TalentedStudentListDTO dto = tsdao.getTalentedStudent(num);
				
				// 수정할 기업의 정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 인재 정보");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("이름       : " + dto.getName());
				System.out.println("전화번호   : " + dto.getTel());
				System.out.println("포트폴리오 : " + dto.getPortfolio());
				System.out.println("추천이유   : " + dto.getReason());
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");
				System.out.print("수정할 포트폴리오 : ");
				String portfolio = scan.nextLine();
				
				if (portfolio.equals("")) {
					portfolio = dto.getPortfolio();
				} else {
					dto.setPortfolio(portfolio);
				}
				
				System.out.print("수정할 추천이유 : ");
				String reason = scan.nextLine();
				
				if (reason.equals("")) {
					reason = dto.getReason();
				} else {
					dto.setReason(reason);
				}
				
				TalentedStudentListDTO dto2 = new TalentedStudentListDTO();
				
				dto2.setSeq(dto.getSeq());
				dto2.setName(dto.getName());
				dto2.setTel(dto.getTel());
				dto2.setPortfolio(dto.getPortfolio());
				dto2.setReason(dto.getReason());

			
				int result = tsdao.talentedStudenEdit(dto2);
				
				if (result > 0) {
					System.out.println("인재 수정 성공");
				} else {
					System.out.println("인재 수정 실패");
				}

				
				pause();
				break;
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}
	/**
	 * 추천 인재 삭제
	 */
	private void talentedStudenDelete() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 삭제
		
		while(true) {
			aview.TalentedStudentListView();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 삭제하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				// 삭제하기 진행
				System.out.print("삭제할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();
				
				int result = tsdao.talentedStudenDelete(num);
				
				if (result > 0) {
					System.out.println("인재 삭제 성공");
				} else {
					System.out.println("인재 삭제 실패");
				}
				
				pause();
				break;
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}


	/**
	 * 기업에 인재 추천 메뉴
	 */
	private void recommendManagement() {
		// 취업지원 관리 - 기업에 인재 추천
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천인재 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추천현황 조회");
			System.out.println("2. 기업인재추천 등록");
			System.out.println("3. 기업인재추천 취소");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				recommendList();
			} else if (num.equals("2")) {
				recommendAdd();
			} else if (num.equals("3")) {
				recommendDelete();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}

	}

	/**
	 *  추천 현황 조회
	 */
	private void recommendList() {
		//취업지원 관리 - 기업에 인재 추천 - 추천 현황 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		aview.recommendStudentListView();
		
		pause();
	}

	/**
	 *  기업인재추천 등록
	 */
	private void recommendAdd() {
		//취업지원 관리 - 기업에 인재 추천 - 기업인재추천 등록
		
		while(true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("인재 추천 등록");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			// 추천 할 인재 선택
			aview.TalentedStudentListView();
			
			System.out.println("1. 인재 추천하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				// 인재 추천하기 진행
				System.out.print("추천할 인재의 번호를 선택하세요 : ");
				num = scan.nextLine();
				
				// 추천할 인재의 객체
				TalentedStudentListDTO studentdto = tsdao.getTalentedStudent(num);
				
				aview.enterpriseListView();
				System.out.print("추천할 기업 번호를 선택하세요 : ");
				num = scan.nextLine();
				
				// 추천할 연계기업의 객체
				LinkCompanyDTO companydto = lcdao.getLinkCompany(num);
				
				
				// 두개의 객체를 전달해서 등록시킨다
				int result = rdao.recommendStudentADD(studentdto, companydto);
				
				if (result > 0) {
					System.out.println("추천 등록 성공");
				} else {
					System.out.println("추천 등록 실패");
				}
				
				pause();
				break;
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력");
				pause();
				break;
			}
					
		}
		
	}

	/**
	 *  기업인재 추천 취소
	 */
	private void recommendDelete() {
		//취업지원 관리 - 기업에 인재 추천 - 기업인재 추천 취소
		while (true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("기업 인재 추천 취소");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			aview.recommendStudentListView();
			// 인재 추천 취소 하기 진행
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 인재 추천 취소 2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("삭제할 기업인재 추천 번호를 선택하세요 : ");
				num = scan.nextLine();
				
				int result = rdao.recommendStudentDelete(num);
				
				if (result > 0 ) { 
					System.out.println("인재 추천 취소 성공");
				} else {
					System.out.println("인재 추천 취소 실패");
				}
				
				pause();
				break;
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
			
		}
	}

	private void jobactivitiesManagement() {
		// 취업현황 조회
		while(true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("취업현황 조회");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정별");
			System.out.println("2. 개인별");
			System.out.println("3. 수료생 목록");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) { 
				jobactivitiesCourse();
			} else if (num.equals("2")) {
				jobactivitiesIndividual();
			} else if (num.equals("3")) {
				completStudentList();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();				
			}
		}
	}
	
	
	/**
	 * 과정별
	 */
	private void jobactivitiesCourse() {
		// 취업지원 관리 - 취업현황 조회 - 과정별
		
		while (true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("과정별 조회");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			ArrayList<EndCourseListDTO> list = jadao.EndCourseList();
			
			for (EndCourseListDTO dto : list ) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t\n"
						, dto.getSeq()
						, dto.getCourseName()
						, dto.getStartDate()
						, dto.getEndDate()
						, dto.getTeacherName()
						, dto.getRoom());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정 선택하기 2. 뒤로가기");
			System.out.print("번호를 입력해 주세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				// 과정 번호 선택
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정번호를 입력하세요 :");
				num = scan.nextLine();
				
				// 과정 번호에 따른 수료생 목록 출력
				courseNumcompletStudentList(num);
				
				break;
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		
		}

		
	}
	/**
	 * 과정별 - 과정선택
	 */
	private void courseNumcompletStudentList(String num) {
		// 취업지원 관리 - 취업현황 조회 - 과정별 - 과정선택
		
		while (true) {
			ArrayList<CompletStudentListDTO> list = jadao.completStudentList(num);
			
			for (CompletStudentListDTO dto : list) 	{
				System.out.printf("%s\t%s\t%s\t%s\t%s\n"
						, dto.getReginum()
						, dto.getName()
						, dto.getJumin()
						, dto.getTel()
						, dto.getRegdate());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("수료생 수강번호를 입력하세요 :");
			num = scan.nextLine(); // reginum
			
			jobactivitiesMenu(num);
			
			pause();
		}
		
	}

	/**
	 * 과정별 - 과정선택 - 구직활동 / 취업정보
	 */
	private void jobactivitiesMenu(String num) {
		// 취업지원 관리 - 취업현황 조회 - 과정별 - 과정선택 - 구직활동/취업정보
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 구직활동 보기 2. 취업정보 보기 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine(); 
			
			if (num.equals("1")) {
				// 구직활동 내역 출력
				
			} else if (num.equals("2")) {
				// 취업정보 출력
				
			} else if (num.equals("3")) {
				break;
				
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 개인별
	 */
	private void jobactivitiesIndividual() {
		// 취업지원 관리 - 취업현황 조회 - 개인별
		
	}
	
	/**
	 * 수료생 목록
	 */
	private void completStudentList() {
		// 취업지원 관리 - 취업현황 조회 - 수료생 목록
		
	}

	private void attendanceManagement() {
		
		// 출결관리
		while(true) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("출결 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 교육생 번호로 검색");
			System.out.println("2. 과정별");
			System.out.println("3. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) { 
				searchStudentNum();
			} else if (num.equals("2")) {
				
			} else if (num.equals("3")) {
				break;
				
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}

	}
	
	private void searchStudentNum() {
		// 출결관리 - 교육생 번호로 검색
		// 교육생 목록 출력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("교육생 번호 입력 :");
		
		
	}

	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
  
	private void basicinfoManagement() {
		boolean check = true;
		while(check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[기초 정보 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정 관리");
			System.out.println("2. 과목 관리");
			System.out.println("3. 교재 관리");
			System.out.println("4. 강의실 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 : ");
			num = scan.nextLine();
			
			if(num.equals("1")) {  //과정 관리
				courseManagement();
			}else if(num.equals("2")) {  //과목 관리
				subjectManagement();
			}else if(num.equals("3")) {  //교재 관리
				bookManagement();
			}else if(num.equals("4")) {  //강의실 관리
				roomManagement();
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}//basicinfoManagement()

		private void courseManagement() {
			ArrayList<CourseDTO> list = csdao.list(null);
			
			//관리자 - 기초정보 관리 - 과목 관리
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[과정관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			for (CourseDTO dto : list) {
			System.out.println("[번호]\t[과정]\t\t[과정목적]");
			System.out.printf("%s\t%s\t%s", dto.getSeq(), dto.getName(), dto.getPurpose());
			System.out.println();
			
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정 등록");
			System.out.println("2. 과정 수정");
			System.out.println("3. 과정 삭제");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("선택(번호) : ");
			
		}//courseManagement() 과정관리

		private void subjectManagement() {
		}//subjectManagement() 과목관리

		private void bookManagement() {
		}//bookManagement() 교재관리

		private void roomManagement() {
		}//roomManagement() 강의실관리

	private void makecourseManagement() {
	}//makecourseManagement() 개설 과정 관리

	private void makesubjectManagement() {
	}//makesubjectManagement() 개설 과목 관리;

}

