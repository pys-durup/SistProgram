package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.JobConsultationDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class JobConsultationDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public JobConsultationDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
}


/**
 * 교육생이 취업상담 일지를 조회하는 메서드
 * */

public ArrayList<JobConsultationDTO> list(String pstudentNum) {
	
	try {
		String sql = "{ call proclistjConsultation(?, ?) }";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, pstudentNum);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		cstat.executeUpdate();
		
		rs = (ResultSet)cstat.getObject(2); // 커서 반환값을 rs로 형변환하기
		
		ArrayList<JobConsultationDTO> list = new ArrayList<JobConsultationDTO>();
		
		while (rs.next()) {
			
			JobConsultationDTO dto = new JobConsultationDTO();
			
			dto.setConsDate(rs.getString("CONSDATE"));
			dto.setContent(rs.getString("CONTENT"));
			
			list.add(dto);
		}
		
		
		return list;
		
	} catch(Exception e) {
		System.out.println("JobConsultationDAO.list()");
		e.printStackTrace();
	}
	
	
	return null;
}






}
