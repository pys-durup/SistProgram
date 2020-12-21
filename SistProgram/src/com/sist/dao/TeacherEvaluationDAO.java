package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.TeacherEvaluationDTO;
import com.sist.main.DBUtil;

public class TeacherEvaluationDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public TeacherEvaluationDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	
}


public int addTeacherEvaluation(TeacherEvaluationDTO tdto) {
	//교육생이 교사평가를 등록하는 메서드
	try {
		String sql = "{ call procAddTeacherEvaluation(?,?,?,?,?,?) }";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, tdto.getMaterials());
		cstat.setString(2, tdto.getCommunication());
		cstat.setString(3, tdto.getJobPreparing());
		cstat.setString(4, tdto.getDivisionTime());
		cstat.setString(5, tdto.getTotalPoint());
		cstat.setString(6, tdto.getCompletNum());
		
		return cstat.executeUpdate();
		
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.addTeacherEvaluation()");
		e.printStackTrace();
	}
	
	
	return 0;
}




public TeacherEvaluationDTO get(String completNum) {
	
	//교육생이 수정 중 본인의 교사평가 내역 한 행을 조회하는 메서드 
	try {
		String sql = "select * from tblTeacherEvaluation where completNum = ?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, completNum);
		
		
		rs = pstat.executeQuery();
		
		if (rs.next()) {
			
			TeacherEvaluationDTO dto = new TeacherEvaluationDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setMaterials(rs.getString("materials"));
			dto.setCommunication(rs.getString("communication"));
			dto.setJobPreparing(rs.getString("jobPreparing"));
			dto.setDivisionTime(rs.getString("divisionTime"));
			dto.setTotalPoint(rs.getString("totalPoint"));
			dto.setCompletNum(rs.getString("completNum"));
			
			return dto;
		}
					
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.get()");
		e.printStackTrace();
	}
	
	return null;
}



public int editTeacherEvaluation(TeacherEvaluationDTO dto) {
	//교육생이 본인이 작성한 교사평가를 수정하는 메서드
	try {
		String sql = "{ call procreEvaluation(?, ?, ?, ?, ?, ?) }";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, dto.getMaterials());
		cstat.setString(2, dto.getCommunication());
		cstat.setString(3, dto.getJobPreparing());
		cstat.setString(4, dto.getDivisionTime());
		cstat.setString(5, dto.getTotalPoint());
		cstat.setString(6, dto.getCompletNum());
		
		return cstat.executeUpdate();
		
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.updateTeacherEvaluation()");
		e.printStackTrace();
	}
	
	return 0;
}






}
