package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.TeacherEvaluationDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교육생의 교사평가 DAO 클래스
 * @author 김소리
 * */

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



/**
 * 교육생이 교사평가를 등록하는 메서드
 * */
public int addTeacherEvaluation(TeacherEvaluationDTO tdto) {
	
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



/**
 * 교육생이 본인의 교사평가 내역 한 행의 정보를 가져오는 메서드
 * */

public TeacherEvaluationDTO get(String completNum) {
	
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



/**
 * 교육생이 자신의 교사평가 목록을 조회하는 메서드
 * */

public ArrayList<TeacherEvaluationDTO> list(String pcompletNum) {
	
	try {
		
		String sql = "{ call proclistEvaluation(?, ?) }";
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, pcompletNum);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		cstat.executeUpdate();
		
		rs = (ResultSet)cstat.getObject(2); //ResultSet으로 커서가 반환한 값을 형변환
		
		ArrayList<TeacherEvaluationDTO> list = new ArrayList<TeacherEvaluationDTO>();
		
		while (rs.next()) {
			
			TeacherEvaluationDTO dto = new TeacherEvaluationDTO();
			
			dto.setName(rs.getString("student"));
			dto.setMaterials(rs.getString("materials"));
			dto.setCommunication(rs.getString("communication"));
			dto.setJobPreparing(rs.getString("jobPreparing"));
			dto.setDivisionTime(rs.getString("divisionTime"));
			dto.setTotalPoint(rs.getString("totalPoint"));
			
			list.add(dto);
		}
		return list;
		
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.list()");
		e.printStackTrace();
	}
	
	return null;
}



/**
 * 교육생이 본인이 작성한 교사평가를 수정하는 메서드
 * */

public int editTeacherEvaluation(TeacherEvaluationDTO dto) {
	
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



/**
 * 교육생이 자신의 교사평가 글을 삭제하는 메서드
 * */
public int deleteTeacherEvaluation(String completNum) {
	
	try {
		String sql = "{ call procdeleteEvaluation(?) }";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, completNum);
		
		return cstat.executeUpdate();
		
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.deleteTeacherEvaluation()");
		e.printStackTrace();
	}
	
	return 0;
}







}
