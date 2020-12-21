package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.StudentDAO;
import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.AbleTStudentScoreListDTO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.MasterDTO;
import com.sist.dto.TalentedStudentListDTO;

public class AdminController {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private StudentDAO sdao;
	private LinkCompanyDAO lcdao;
	private TalentedStudentDAO tsdao;

	public AdminController(MasterDTO mdto) {
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
		this.sdao = new StudentDAO();
		this.lcdao = new LinkCompanyDAO();
		this.tsdao = new TalentedStudentDAO();
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
			System.out.println("6. 취업활동 관리");
			System.out.println("7. 취업지원 관리");
			System.out.println("8. 데이터 통계 관리");
			System.out.println("9. 상담일지 관리");
			System.out.println("0. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				attendanceManagement(); // 출결 관리 - 박영수
			} else if (num.equals("5")) { 
				
			} else if (num.equals("6")) { 
				jobactivitiesManagement(); // 취업활동 관리 - 박영수
			} else if (num.equals("7")) {
				jobSupportManagement(); // 취업지원 관리 - 박영수
			} else if (num.equals("8")) { 
				dataStatisticsManagement(); // 데이터 통계 관리 - 박영수
			} else if (num.equals("9")) { 
				
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
	
	private void dataStatisticsManagement() {
		// 데이터 통계 관리
	}

	private void jobSupportManagement() {
		// 취업지원 관리
		while(true) {
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
			
			if(num.equals("1")) { 
				enterpriseManagement();
			} else if (num.equals("2")) {
				talentedStudentManagement();
			} else if (num.equals("3")) {
				
			} else if (num.equals("4")) {
				break;
			}else {
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
		
		ArrayList<TalentedStudentListDTO> list = tsdao.talentedStudenList(num);
		
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
	 * 추천 인재 추가
	 */
	private void talentedStudenAdd() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 추가");
		System.out.println("수료생중 성적이 높은 10명");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		ArrayList<AbleTStudentScoreListDTO> list = tsdao.ableTStudentScoreList();
		
		for(AbleTStudentScoreListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
									, dto.getReginum()
									, dto.getName()
									, dto.getWriter()
									, dto.getPractice()
									, dto.getAttendance());
		}
		
		pause();
		
	}
	/**
	 * 추천 인재 수정
	 */
	private void talentedStudenEdit() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 수정
		
	}
	/**
	 * 추천 인재 삭제
	 */
	private void talentedStudenDelete() {
		//취업지원 관리 - 추천인재 관리 - 추천 인재 삭제
		
	}

	private void jobactivitiesManagement() {
		// 취업활동 관리
		
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
}
