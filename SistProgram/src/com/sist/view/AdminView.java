package com.sist.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.JobActivitiesDAO;
import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.RecommendDAO;
import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.CourseDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.RecommendListDTO;
import com.sist.dto.TalentedStudentListDTO;

public class AdminView {
	
	private TalentedStudentDAO tsdao;
	private RecommendDAO rdao;
	private LinkCompanyDAO lcdao;
	private JobActivitiesDAO  jadao;
	private Scanner scan = new Scanner(System.in);
	
	
	public AdminView() {
		this.tsdao = new TalentedStudentDAO();
		this.rdao = new RecommendDAO();
		this.lcdao = new LinkCompanyDAO();
		this.jadao = new JobActivitiesDAO();
	}
	
	/**
	 * 취업지원 관리 - 추천인재 관리 - 인재목록
	 */
	public void TalentedStudentListView() {
		
		ArrayList<TalentedStudentListDTO> list = tsdao.talentedStudenList(null);
		
		for (TalentedStudentListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getName()
									, dto.getTel()
									, dto.getPortfolio()
									, dto.getReason());
		}
	}

	public void HeadCourse() {
	
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("[과정관리]");
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("[번호]  \t[과정]\t\t\t\t\t[과정목적]");	
	}
	public void MenuCourse() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 과정 등록");
		System.out.println("2. 과정 수정");
		System.out.println("3. 과정 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("선택(번호) : ");
	
}
	public void Course() {
		System.out.print("과정 : "); 
}
	public void Purpose() {
		System.out.print("목적 : ");
}
	public void Number() {
		System.out.print("번호 : ");
	}
	public void DeleteNumber() {
		System.out.print("삭제할 번호 : ");
	}

	
	/**
	 * 취업지원 관리 - 기업에 인재 추천 - 추천현황 조회
	 * 기업에 추천한 인재 리스트
	 */
	public void recommendStudentListView() {
		
		ArrayList<RecommendListDTO> list = rdao.recommendStudentList();
		
		for (RecommendListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\n"
									, dto.getSeq()
									, dto.getStudentName()
									, dto.getCompanyName()
									, dto.getRecoDate());
		}
		
	}
	
	
	public void enterpriseListView() {
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;
		String num;

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
			
			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 선택하러가기");
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
				break;
			}
		}	
	}
	
	
	
	public void CourseList(ArrayList<CourseDTO> list) {
		for (CourseDTO dto : list) {
			System.out.printf(" %s\t%-30s\t%60s", dto.getSeq(), dto.getName(), dto.getPurpose());
			System.out.println();
		}
	}
		
	/**
	 *  종료된 과정의 목록 출력
	 */
	public void endCourseListView() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("종료된 과정 목록");
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
		System.out.println("1. 과정 번호 선택  2. 뒤로가기");
		System.out.print("번호를 입력하세요 :");
		
	}

	/**
	 * 종료된 과정 하나의 정보를 출력
	 * @param num 과정번호
	 */
	public void endCourseInfoView(String num) {
		
		EndCourseListDTO dto = jadao.getEndCours(num);
		System.out.println("과정명 : " + dto.getCourseName());
		System.out.println("시작일 : " + dto.getStartDate());
		System.out.println("종료일 : " + dto.getEndDate());
		System.out.println("강사명 : " + dto.getTeacherName());
		System.out.println("강의실 : " + dto.getRoom());
		
	}
}
