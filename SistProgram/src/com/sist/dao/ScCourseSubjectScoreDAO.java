package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScCourseSubjectDTO;
import com.sist.dto.ScCourseSubjectScoreDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScCourseSubjectScoreDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScCourseSubjectScoreDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectScoreDAO()");
			e.printStackTrace();
		}	
		
	}
	/**
	 * 입력받은 과목의 번호로 해당 과목의 교육생 성적리스트 리턴하는 메서드
	 * @param num
	 * @return
	 */
	public ArrayList<ScCourseSubjectScoreDTO> list(String num) {
		
		try {
			
			String sql = "{call procScCourseSubjectScore(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<ScCourseSubjectScoreDTO> list = new ArrayList<ScCourseSubjectScoreDTO>();
			
			while(rs.next()) {
				ScCourseSubjectScoreDTO dto = new ScCourseSubjectScoreDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
				dto.setJumin(rs.getString("jumin"));
				dto.setWrite(rs.getString("write"));
				dto.setPractice(rs.getString("practice"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ScCourseSubjectScoreDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
