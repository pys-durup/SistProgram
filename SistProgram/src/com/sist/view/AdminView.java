package com.sist.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.JobActivitiesDAO;
import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.RecommendDAO;
import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.BookDTO;
import com.sist.dto.CourseDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.MakeCourceDTO;
import com.sist.dto.RecommendListDTO;
import com.sist.dto.RoomDTO;
import com.sist.dto.StudentDTO;
import com.sist.dto.SubjectDTO;
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
	public void TalentedStudentListView(String word) {
		
		ArrayList<TalentedStudentListDTO> list = new ArrayList<TalentedStudentListDTO>();

		if(word == null) {
			list = tsdao.talentedStudenList(null);
		} else {
			list = tsdao.talentedStudenList(word);
		}
		
		if(list.size() > 0) {
			// 검색된 값 결과가 있을때
			makeLine(100);
			System.out.println("[번호]\t[이름]\t [전화번호]\t\t\t[포트폴리오]\t\t\t[추천이유]");
			for (TalentedStudentListDTO dto : list) {
				System.out.printf("%4s\t%s\t%s\t%-37s\t  %s\n"
						, dto.getSeq()
						, dto.getName()
						, dto.getTel()
						, dto.getPortfolio()
						, dto.getReason());
			
			}
		} else {
			makeLine(100);
			System.out.println("검색된 결과가 없습니다");
			
		}
		
		
	}

	public void HeadCourse() {
	
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("[과정관리]");
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
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
		
		System.out.println("[번호]\t  [이름]\t    [연계기업명]\t\t[추천날짜]");
		for (RecommendListDTO dto : list) {
			System.out.printf("%4s\t  %s   \t%-22s\t%s\n"
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
			System.out.print("번호를 입력하세요 : ");
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
			System.out.printf("[번호] %2s [과정] %s [과정목적] %s\n"
							,dto.getSeq()
							,dto.getName()
							,dto.getPurpose());	
	}
	}
		
	/**
	 *  종료된 과정의 목록 출력
	 */
	public void endCourseListView() {
		
		makeTitle("종료된 과정 목록", 105);
		
		ArrayList<EndCourseListDTO> list = jadao.EndCourseList();
		
		System.out.println("[번호]\t\t\t[과정명]\t\t\t[시작일자]  [종료일]  [교사명]   [강의실]");
		for (EndCourseListDTO dto : list ) {
			System.out.printf("%4s\t%-35s\t%10s  %10s  %s  %s\n"
					, dto.getSeq()
					, dto.getCourseName()
					, dto.getStartDate()
					, dto.getEndDate()
					, dto.getTeacherName()
					, dto.getRoom());
		}
		
		makeLine(105);
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


	public void HeadSubject() {
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("[과목 관리]");
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public void SubjectList(ArrayList<SubjectDTO> list) {
	for (SubjectDTO dto : list) {
		System.out.printf("[번호] %2s \t[과목] %s [소요일] %s\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getDuration());	
		
		
	}
	}
	public void BasicInfoMenu() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("[기초 정보 관리]");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("1. 과정 관리");
        System.out.println("2. 과목 관리");
        System.out.println("3. 교재 관리");
        System.out.println("4. 강의실 관리");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("선택(번호) : ");
	}

        public void menuSubject() {
        	
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    	System.out.println("1. 과목 등록");
    	System.out.println("2. 과목 수정");
    	System.out.println("3. 과목 삭제");
    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    	System.out.print("선택(번호) : ");
        }
        
        public void Subject() {
        	System.out.print("과목 : ");
        }
        
        public void Duration() {
        	System.out.print("소요일 : ");
        }
        public void InfoSubject(SubjectDTO dto) {
    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    	System.out.println("수정할 과목 정보");
    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    	System.out.println("과정       : " + dto.getName());
    	System.out.println("소요일     : " + dto.getDuration());
    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    	System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");	
        }
        
        public void HeadBook() {
        	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        	System.out.println("[교재 관리]");
        	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        	
        }
        
        public void BookList(ArrayList<BookDTO> list) {
    	for (BookDTO dto : list) {
    	    		System.out.printf(
    	    				    "[번호] %2s [책이름] %s [글쓴이] %s [출판사] %s [가격] %s [재고] %s\n"
    	    					  		,dto.getSeq()
    	    					  		,dto.getName()
    	    					  		,dto.getWriter()
    	    					  		,dto.getPublisher()
    	    					  		,dto.getPrice()
    	    					  		,dto.getCount());
//			System.out.println("[번호] \t[책이름]\t\t\t[글쓴이]");	
//			System.out.printf(" %s\t%s\t\t\t%s\n", dto.getSeq(), dto.getName(), dto.getWriter());
//			System.out.println("[출판사]\\t[가격]\\t[재고]");
//			System.out.printf("%s\t%s\t\t%s\n",dto.getPublisher(), dto.getPrice(), dto.getCount());
			
            
        }
        }

	public void menuBook() {
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("1. 교재 등록");
	System.out.println("2. 교재 수정");
	System.out.println("3. 교재 삭제");
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.print("선택(번호) : ");
	}

	public void Book() {
	    System.out.print("교재 : ");
	}

	public void Writer() {
	    System.out.print("글쓴이 :");
	}

	public void Publisher() {
	    System.out.print("출판사 : ");
	}

	public void Price() {
	    System.out.print("가격 : ");
	}

	public void Count() {
	    System.out.print("재고 : ");
	}

	public void InfoBook(BookDTO dto) {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    	System.out.println("수정할 교재 정보");
	    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    	System.out.println("제목       : " + dto.getName());
	    	System.out.println("글쓴이     : " + dto.getWriter());
	    	System.out.println("출판사     : " + dto.getPublisher());
	    	System.out.println("가격       : " + dto.getPrice());
	    	System.out.println("재고       : " + dto.getCount());
	    	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    	System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");	
	        }

	
	public void makeTitle(String title, int count) {
		
		String line = "";

		for(int i=0 ; i<count ; i++) {
			line += "━";
		}
		
		String space = "";
		for(int i=0 ; i<line.length() / 2 - title.length() ; i++) {
			space += " ";
		}
		
		title = space + title;
		
		System.out.println(line);
		System.out.println(title);
		System.out.println(line);
	}
	
	public void makeLine (int count) {
		String line = "";
		for(int i=0 ; i<count ; i++) {
			line += "━";
		}
		
		System.out.println(line);
	}


	public void HeadRoom() {
	 	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[강의실 관리]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public void RoomList(ArrayList<RoomDTO> list) {
	    for(RoomDTO dto : list) {
		System.out.printf(
				  "[번호] %2s [교실명] %s [수용인원] %s [과정명] %s \n"
				 			,dto.getSeq()
				 			,dto.getRoomnum()
				 			,dto.getCapacity()
				 			,dto.getName());
	    
	}

	}

	public void menuRoom() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.println("1.강의실 이름 변경");
	    System.out.println("2.강의실 정원 변경");
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("선택(번호) : ");
	}

	public void Room() {
	    System.out.println("강의실명 : ");
	}

	public void InfoRoom(RoomDTO dto) {
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("수정할 교재 정보");
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("강의실이름        : " + dto.getRoomnum());
	System.out.println("강의실정원        : " + dto.getCapacity());
	System.out.println("진행중인 과정     : " + dto.getName());
	System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");
	}

	public void Capacity() {
	    System.out.println("강의실 정원 : ");
	}

	public void Caustion() {
	    System.out.println("1,2,3 강의실은 최대 정원 30명, 2,3,4 강의실은 정원 최대 정원 26명 입니다.");
	    System.out.println("그 이상으로 입력되지 않습니다.");
	}

	public void MakeCourseMenu() {
	        
	        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.println("1. 개별 개설과정 검색");
	        System.out.println("2. 개설과정 수강생 조회");
	        System.out.println("3. 수강정보 일괄 수정");
	        System.out.println("4. 학생별 수강정보 수정");
	        System.out.println("5. 과정 개설");
	        System.out.println("6. 과정 수정");
	        System.out.println("7. 과정 삭제");
	        System.out.println("0. 뒤로가기");
	        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        System.out.print("선택(번호) : ");
	    
	}

	public void AllCurrentCourseList(ArrayList<MakeCourceDTO> list) {
	    	for (MakeCourceDTO dto : list) {
	    		System.out.printf(
	    				    "[번호] %2s [고유번호] %s [과정명] %s [시작일] %s [종료일] %s [강의실] %s\n"
	    					  		,dto.getRownum()
	    					  		,dto.getSeq()
	    					  		,dto.getName()
	    					  		,dto.getStartdate()
	    					  		,dto.getEnddate()
	    					  		,dto.getRoomnum());
	    
	}
	}
	public void HeadMakeCourse() {
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("[개설과정 관리]");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
	}

	public void EachCourseSearch(ArrayList<MakeCourceDTO> list) {
	    for (MakeCourceDTO mcdao : list) {
		    System.out.printf("[번호] %2s [과정번호] %-3s [교실명] %s [과목명] %s [교과서명] %s [기간] %s\n"
					,mcdao.getRownum()
					,mcdao.getSeq()
					,mcdao.getRoomnum()
					,mcdao.getSname()
					,mcdao.getBname()
					,mcdao.getDuration());
	    }
	}

	public void CourseNumber() {
	    System.out.print("과정 고유번호: ");
	}

	public void Bar() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public void searchStudenCourse() {
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("[개설과정 수강생 조회]");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("개설과정 고유번호 :  ");
	}

	public void ListCourseStudent(ArrayList<StudentDTO> list) {
	    for (StudentDTO stdao: list) {
		    System.out.printf("[수강생번호] %2s [이름] %s [주민번호] %s [전화번호] %s [등록일] %s [수강상태] %s\n"
					,stdao.getSeq()
					,stdao.getName()
					,stdao.getJumin()
					,stdao.getTel()
					,stdao.getRegdate()
					,stdao.getRegistate());
	    }
	    
	}

	public void ManageStudentStatusMenu() {
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("[과정별 수강정보 일괄 수정]");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("1. 과정별 수강정보 일괄 수정");
	       System.out.println("0. 뒤로가기");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.print("선택(번호) : ");
	}

	public void EditCourseStudentStatus() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 과정번호 입력  2. 뒤로가기");
		System.out.print("선택(번호) : ");
		
	}

	public void StatusNumber() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.println("1. 수강중, 2.수료, 3.중도탈락, 4. 수강전");
	    System.out.print("번호 선택 : ");
	}

	public void StudentNumber() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("학생 번호 : ");
	}

	public void ManageStudentStatusMenuEach() {
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("[학생별 수강정보 수정]");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.println("1. 학생별 수강정보 수정");
	       System.out.println("0. 뒤로가기");
	       System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	       System.out.print("선택(번호) : ");
	}

	public void StartDate() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("시작일 ex) 20/12/12 : ");
	}

	public void Enddate() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("종료일 ex) 20/12/12 : ");
	}

	public void Roomnum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("강의실번호 ex) 1~6번 사이의 숫자를 입력!! : ");
	}

	public void TeacherNum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("선생님 번호 ex) 숫자 입력!! : ");
	}

	public void Coursenum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("과정 번호 ex) 숫자 입력!! : ");
	}

	public void MakeCourseNum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("개설 과정 번호 ex) 숫자 입력!! : ");
	}

	public void Cancel() {
	    System.out.println("뒤로 돌아가기 '00' 입력");
	    
	}

	public void MakeSubjectNum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("과목명 번호 ex) 숫자 입력!! : ");
		    
	    
	}

	public void Booknum() {
	    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    System.out.print("교과서 번호 ex) 숫자 입력!! : ");
	}

	public void MakeSubjectSeq() {
	    System.out.print("개설 과목 고유번호 : ");
	}

	public void ConnectNum() {
	    System.out.print("연계 고유번호 : ");
	}

	public void DistristateBook() {
	    System.out.println("미완 또는 완료");
	    System.out.print("분배상태 :");
	}
	}

