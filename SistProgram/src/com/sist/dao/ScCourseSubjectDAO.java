package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScCourseSubjectDTO;
import com.sist.dto.ScoreListCourseDTO;
import com.sist.dto.StudentConsultListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScCourseSubjectDAO {
	
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScCourseSubjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectDAO()");
			e.printStackTrace();
		}	
		
	}
	/**
	 * 입력받은 과정의 번호로 해당 과정의 과목리스트를 리턴하는 메서드
	 * @param num
	 * @return
	 */
	
	// 성적조회 - 과정별 - 과정선택 - 과목리스트
	public ArrayList<ScCourseSubjectDTO> list(String num) {
		try {
			
			String sql = "{call procScSubjectList(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<ScCourseSubjectDTO> list = new ArrayList<ScCourseSubjectDTO>();
			
			while(rs.next()) {
				ScCourseSubjectDTO dto = new ScCourseSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSjname(rs.getString("sjname"));
				dto.setDuration(rs.getString("duration"));
				dto.setTname(rs.getString("tname"));
				dto.setBook(rs.getString("book"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectDAO.list()");
			e.printStackTrace();
		}
		return null;
	}
}
