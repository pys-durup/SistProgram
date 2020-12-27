package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.InterviewsEvaluationDTO;
import com.sist.dto.ScoreListCourseDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class InterviewsEvaluationDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public InterviewsEvaluationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<InterviewsEvaluationDTO> list() {
			
		try {
			
			String sql = "{call proclistInterviewsEvaluation(?)}";
			
			cstat = conn.prepareCall(sql);
	
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(1);
			
			ArrayList<InterviewsEvaluationDTO> list = new ArrayList<InterviewsEvaluationDTO>();
			
			while(rs.next()) {
				InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setScore(rs.getString("score"));
				dto.setTname(rs.getString("tname"));
				dto.setQuestion(rs.getString("qusetion"));
				dto.setEvaluation(rs.getString("evaluation"));
				dto.setScore(rs.getString("score"));
				
				list.add(dto);
			}
			
			return list; 
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.list()");
			e.printStackTrace();
		}
		return null;
	}

	public int delete(String seq) {
		try {
			String sql = "{call procdeleteInterviewsEvaluation(?) }";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);			
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.delete()");
			e.printStackTrace();
		}

		return 0;
	}

	public int add(InterviewsEvaluationDTO dto) {

			try {
			
			String sql = "{call procAddInterviewsEvaluation(?, ?)}";
		
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getEvaluation());
			pstat.setString(2, dto.getScore());
			
		
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.add()");
			e.printStackTrace();
		}
			
		return 0;
	}

	public int edit(InterviewsEvaluationDTO dto2) {
		
		try {
			
			String sql = "{call procEditInterviewsEvaluation(?, ?)}";
			
			pstat= conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getEvaluation());
			pstat.setString(2, dto2.getScore());

			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	public InterviewsEvaluationDTO get(String seq) {

		try {
			
			String sql = "{call proclistInterviewsEvaluation(?)}";
			
			
			cstat = conn.prepareCall(sql);
			
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			

			rs = (ResultSet)cstat.getObject(1);
			
			ArrayList<InterviewsEvaluationDTO> list = new ArrayList<InterviewsEvaluationDTO>();
			
			while(rs.next()) {
				InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setScore(rs.getString("score"));
				dto.setTname(rs.getString("tname"));
				dto.setQuestion(rs.getString("qusetion"));
				dto.setEvaluation(rs.getString("evaluation"));
				dto.setScore(rs.getString("score"));
				
				list.add(dto);
			}
//			if(rs.next()) {
//				
//				InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
//				
//				dto.setSeq(rs.getString("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setAge(rs.getString("age"));
//				dto.setGender(rs.getString("gender"));
//				dto.setTel(rs.getString("tel"));
//				dto.setAddress(rs.getString("address"));
//				dto.setRegdate(rs.getString("regdate"));
//				
//							
//				return dto;
//			}
			
			
		} catch (Exception e) {
			System.out.println("AddressDAO.get()");
			e.printStackTrace();
		}
		return null;
	}
	

}
