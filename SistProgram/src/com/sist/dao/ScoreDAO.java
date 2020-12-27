package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScoreAndSubjectDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교육생 성적 DAO 클래스
 * @author 김소리
 * */


public class ScoreDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public ScoreDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

}



/**
 * 교육생이 본인의 성적을 조회하는 메서드
 * @param pstudentNum 교육생번호 매개변수
 * */

public ArrayList<ScoreAndSubjectDTO> list(String pstudentNum) {
	
	try {
	
		String sql = "{ call proclistScore(?, ?) }";
		cstat = conn.prepareCall(sql);

		cstat.setString(1, pstudentNum);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		cstat.executeUpdate();
		
		rs = (ResultSet)cstat.getObject(2); //커서가 반환한 값을 rs로 형변환
		
		ArrayList<ScoreAndSubjectDTO> list = new ArrayList<ScoreAndSubjectDTO>();
		
		while (rs.next()) {
			
			ScoreAndSubjectDTO dto = new ScoreAndSubjectDTO();
			dto.setName(rs.getString("SUBJECTNAME"));
			dto.setAttendance(rs.getString("ATTENDANCE"));
			dto.setPractice(rs.getString("PRACTICE"));
			dto.setWrite(rs.getString("WRITER"));
			
			list.add(dto);
			
		}
		return list;
		
	} catch(Exception e) {
		System.out.println("ScoreDAO.list()");
		e.printStackTrace();
		
	}
	
	
	return null;
}






}
