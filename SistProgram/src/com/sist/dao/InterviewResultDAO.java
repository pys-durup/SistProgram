package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.InterviewResultDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 예비교육생의 면접 결과 DAO 클래스
 * @author 김소리
 * */

public class InterviewResultDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	private CallableStatement cstat = null;
	
	public InterviewResultDAO() {
		
		try {
			this.conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryInterviewResultDAO.enInterviewResultDAO()");
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 예비교육생이 본인의 면접 결과를 조회하는 메서드
	 * */
	public ArrayList<InterviewResultDTO> list(String prstudentNum) {
		
		try {
			String sql = "{ call procResult(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, prstudentNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			rs = (ResultSet)cstat.getObject(2); //커서 반환값을 rs로 형변환
			
			ArrayList<InterviewResultDTO> list = new ArrayList<InterviewResultDTO>();
			
			while (rs.next()) {
				
				InterviewResultDTO dto = new InterviewResultDTO();
				
				dto.setrName(rs.getString("RNAME"));
				dto.setcSeq(rs.getString("CSEQ"));
				dto.setcName(rs.getString("CNAME"));
				dto.setResult(rs.getString("RESULT"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("InterviewResultDAO.list()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	/**
	 * 관리자가 아직 '수강전' 인 교육생 리스트를 조회하는 메서드
	 * */
	public ArrayList<InterviewResultDTO> enrollmentList() {
		
		
		try {
			String sql = "select * from vw_previous";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<InterviewResultDTO> list = new ArrayList<InterviewResultDTO>();
			
			while (rs.next()) {
				
				InterviewResultDTO dto = new InterviewResultDTO();
				
				dto.setStudentNum(rs.getString("StudentNum"));
				dto.setrName(rs.getString("studentName"));
				dto.setState(rs.getString("state"));
				dto.setStart(rs.getString("start"));
				dto.setcName(rs.getString("course"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			
			System.out.println("InterviewResultDAO.enrollmentList()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	/**
	 * 관리자가 학생번호를 지정하여 수강상태를 수강전 -> 수강중으로 변경하는 메서드
	 * */
	public int editEnrollment(String pstudentNum) {
		
		try {
			String sql = "{ call procsignUp(?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pstudentNum);
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("InterviewResultDAO.editEnrollment()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
