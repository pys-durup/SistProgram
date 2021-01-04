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
	
	/**
	 * 모의면접 평가 조회를 위해 모의면접을 진행한 교육생들의 평가리스트를 리턴하는 메서드
	 * @return
	 */
	public ArrayList<InterviewsEvaluationDTO> list() {
			
		try {
			
			String sql = "select * from vwlistInterviewsEvaluation";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<InterviewsEvaluationDTO> list = new ArrayList<InterviewsEvaluationDTO>();
			
			while(rs.next()) {
				InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
				dto.setTname(rs.getString("tname"));
				dto.setQuestion(rs.getString("question"));
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
	
	/**
	 * 모의면접평가에서 추가하는 메서드
	 * @param dto
	 * @return
	 */
	public int add(InterviewsEvaluationDTO dto) {

			try {
			
			String sql = "{call procAddInterviewsEvaluation(?, ?, ?, ?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getInterviewNum());
			cstat.setString(2, dto.getScore());
			cstat.setString(3, dto.getEvaluation());
			cstat.setString(4, dto.getTeacherNum());
			cstat.setString(5, dto.getRegiNum());
		
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.add()");
			e.printStackTrace();
		}
			
		return 0;
	}

	public int edit(InterviewsEvaluationDTO dto2) {
		
		try {
			
			String sql = "{call procEditInterviewsEvaluation(?, ?, ?)}";
			
			cstat = conn.prepareCall(sql);

			cstat.setString(1, dto2.getSeq());
			cstat.setString(2, dto2.getEvaluation());
			cstat.setString(3, dto2.getScore());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	public InterviewsEvaluationDTO get(String num) {

		try {
			
			String sql = "select * from vwlistInterviewsEvaluation where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				InterviewsEvaluationDTO dto = new InterviewsEvaluationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setScore(rs.getString("score"));
				dto.setTname(rs.getString("tname"));
				dto.setQuestion(rs.getString("question"));
				dto.setEvaluation(rs.getString("evaluation"));
				dto.setScore(rs.getString("score"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("InterviewsEvaluationDAO.get()");
			e.printStackTrace();
		}
		return null;
	}
	

}
