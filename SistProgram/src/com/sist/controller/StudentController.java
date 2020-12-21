package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dao.StudentDAO;
import com.sist.dao.TeacherEvaluationDAO;
import com.sist.dto.StudentDTO;
import com.sist.dto.TeacherEvaluationDTO;

public class StudentController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);
	private StudentDTO sdto; // 로그인한 계정의 정보를 담을 객체
	private TeacherEvaluationDAO tdao; // DB작업에 사용할 객체
	private TeacherEvaluationDTO tdto; // 값을 포장할 객체
	
	public StudentController(StudentDTO sdto) {
		this.sdto = sdto; // 로그인한 교육생의 계정 정보를 담는다
	}
	
	public void start() {
		boolean check = true;
		
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("교육생 %s님 접속을 환영합니다\n", this.sdto.getName() );
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 출결체크 및 조회");
			System.out.println("2. 성적 조회");
			System.out.println("3. 상담 일지");
			System.out.println("4. 교사 평가");
			System.out.println("4. 취업활동 관리");
			System.out.println("5. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
			
			if(num.equals("1")) {
				
			} else if (num.equals("2")) { 
				
			} else if (num.equals("3")) { 
				
			} else if (num.equals("4")) { 
				studentEvaluation(); 
				break;
			} else if (num.equals("5")) { 
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
		
	}
	
	
	private void studentEvaluation() {
		boolean check = true;
		//4. 교사평가
		System.out.println("1. 교사평가 등록");
		System.out.println("2. 교사평가 조회");
		System.out.println("3. 교사평가 수정");
		System.out.println("4. 교사평가 삭제");
		System.out.println();
		System.out.println("0. 뒤로 가기");
		
		num = scan.nextLine();
		
		while (check) {
		
			if (num.equals("1")) {
				addEvaluation(); //교사평가 등록
				break;
			} else if (num.equals("2")) {
				listEvaluation();
			} else if (num.equals("3")) {
				editEvaluation();
			} else if (num.equals("4")) {
				
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
	
	
	private void listEvaluation() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				
		
		
	}

	
	
	
	private void editEvaluation() {

		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사평가 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("**수정을 원하지 않는 항목은 enter키를 입력해주세요.");
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		//System.out.println("이전 교사평가 내역 확인");
		
		//ArrayList<>
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
