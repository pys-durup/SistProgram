package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.StudentDAO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.MasterDTO;

public class AdminController {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private StudentDAO sdao;
	private LinkCompanyDAO lcdao;

	public AdminController(MasterDTO mdto) {
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
		this.sdao = new StudentDAO();
		this.lcdao = new LinkCompanyDAO();
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
			System.out.println("4. 기업에 인재추천");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) { 
				enterpriseManagement();
			} else if (num.equals("2")) {
				
			} else if (num.equals("3")) {
				
			} else if (num.equals("3")) {
				break;
			}else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

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

	private void enterpriseList() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 조회
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지

		while (true) {	
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계 기업 조회");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("번호\t회사이름\t\t\t주소\t\t\t\t\t\t부서\t\t\t제시연봉");
			
			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
//			System.out.println("가져온 레코드의 개수" + list.size());
			endPage = list.size() / page;
			
//			System.out.println("현재 페이지 : " + nowPage);
			for(int i=(nowPage - 1) * page ; i < page * nowPage ; i++) {
				
				System.out.printf("%2s\t%-15s\t\t%-30s\t%-15s\t%7s\n"
						,list.get(i).getSeq() 
						,list.get(i).getName()
						,list.get(i).getAddress()
						,list.get(i).getDepartment()
						,list.get(i).getSalary());	
			}
			
//			for(LinkCompanyDTO dto : list) {
//				System.out.printf("%s\t%s\t%s\t%s\t\n"
//						,dto.getSeq() 
//						,dto.getName()
//						,dto.getAddress()
//						,dto.getDepartment()
//						,dto.getSalary());			
//			}
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			
			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				if(nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
				}
			} else if (num.equals("2")) {
				if(nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else {
					nowPage++; // 현재페이지 1 증가
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

	private void enterpriseSearch() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 검색
		
	}

	private void enterpriseAdd() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 추가
		
	}

	private void enterpriseEdit() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 수정
		
	}

	private void enterpriseDelete() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 삭제
	
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
