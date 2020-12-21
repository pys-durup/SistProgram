package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.CourseConsultationDTO;
import com.sist.dto.MasterDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.dao.StudentlistDAO;

public class AdminController {
	
	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private StudentlistDAO sldao;
	
	public AdminController(MasterDTO mdto) {
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
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
			System.out.println("8. 데이터 통계");
			System.out.println("9. 상담일지 관리");
			System.out.println("0. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				attendanceManagement();
			} else if (num.equals("5")) { 
				scoreManagement();
			} else if (num.equals("6")) { 
				
			} else if (num.equals("7")) { 
				
			} else if (num.equals("8")) { 
				
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
		}
		
		System.out.println();		
		
		pause();
		
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

	private void attendanceManagement() {
		// 출결관리
		System.out.println("1. 교육생 번호로 검색");
		System.out.println("2. 과정별");
	}
	
	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}
}
