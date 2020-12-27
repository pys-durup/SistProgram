package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.SetScoreDTO;
import com.sist.dto.StudentScoreListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class StudentScoreListDAO {
	
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public StudentScoreListDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudentScoreListDAO()");
			e.printStackTrace();
		}	
		
	}
	
	
	/**
	 * 학생성적조회
	 * @param pseq 개설과목번호
	 * @param tseq 교사번호
	 * 
	 */
	public ArrayList<StudentScoreListDTO> list (String pseq, String tseq){
		
		try {
				String sql = "{ call proc_StudentScoreList(?,?,?)}";
				
				cstat = conn.prepareCall(sql);
				cstat.setString(1, pseq);
				cstat.setString(2, tseq);
				cstat.registerOutParameter(3, OracleTypes.CURSOR);
				
				cstat.executeQuery();
				
				rs = (ResultSet) cstat.getObject(3);
				
				ArrayList<StudentScoreListDTO> list = new ArrayList<StudentScoreListDTO>();
				
				while (rs.next()) {
					
					StudentScoreListDTO dto = new StudentScoreListDTO();
					
					dto.setSeq(rs.getString("seq"));
					dto.setStudentName(rs.getString("studentName"));
					dto.setScoreSeq(rs.getString("scoreSeq"));
					dto.setAttendance(rs.getString("attendance"));
					dto.setPractice(rs.getString("practice"));
					dto.setWriter(rs.getString("writer"));
					dto.setRegistate(rs.getString("registate"));
					
					list.add(dto);
				}
				rs.close();
				return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
	
	/**
	 * 학생성적수정
	 * @param dto2 학생성적데이터
	 * 
	 */
	public int editStudentScore(StudentScoreListDTO dto2) {
		
		try {
				String sql = "{call proc_editScore(?,?,?,?)}";
				
				cstat = conn.prepareCall(sql);
				cstat.setString(1, dto2.getAttendance());//출석배점
				cstat.setString(2, dto2.getWriter());//필기배점
				cstat.setString(3, dto2.getPractice());//실기배점
				cstat.setString(4, dto2.getSeq());//번호
			
				return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return 0;
		
		
	}

}
