package com.sist.view;

import java.util.ArrayList;

import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.CourseDTO;
import com.sist.dto.TalentedStudentListDTO;

public class AdminView {
	
	private TalentedStudentDAO tsdao;
	
	
	public AdminView() {
		this.tsdao = new TalentedStudentDAO();
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

	public void CourseList(ArrayList<CourseDTO> list) {
		for (CourseDTO dto : list) {
			System.out.printf(" %s\t%-30s\t%60s", dto.getSeq(), dto.getName(), dto.getPurpose());
			System.out.println();
	}
}
}