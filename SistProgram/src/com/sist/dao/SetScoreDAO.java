package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.JobConsultationListDTO;
import com.sist.dto.SetScoreDTO;
import com.sist.main.DBUtil;

public class SetScoreDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public SetScoreDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
}

/**
 * 배점 추가
 * @param dto 배점정보
 * @return
 */
public int addSetScore(SetScoreDTO dto) {
	
		
	try {
			String sql = "{call proc_addSetScore(?,?,?,?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getAttendance());
			cstat.setString(2, dto.getWrite());
			cstat.setString(3, dto.getPractice());
			cstat.setString(4, dto.getMakeSubjectNum());
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	
	
	return 0;
	
		
}
/**
 * 배점과목 삭제
 * @param seq 배점과목번호
 * @return
 */
public int deleteSetScore(String seq) {
	
	
	try {
			String sql = "delete from tblSetScore where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
					
					
					
	} catch (Exception e) {
		System.out.println(e);
	}
	return 0;
	

}



/**
 * 배점수정
 * @param dto2 배점정보
 * @return
 */
public int editSetScore(SetScoreDTO dto2) {
	
	try {
			String sql = "{call proc_editsetScore(?,?,?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto2.getAttendance());//출석배점
			cstat.setString(2, dto2.getWrite());//필기배점
			cstat.setString(3, dto2.getPractice());//실기배점
			cstat.setString(4, dto2.getSeq());//번호
		
			return cstat.executeUpdate();
		
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	return 0;
	
	
}



}
