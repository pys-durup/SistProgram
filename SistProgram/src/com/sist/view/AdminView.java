package com.sist.view;

import java.util.ArrayList;

import com.sist.dao.TalentedStudentDAO;
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

}
