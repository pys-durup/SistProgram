package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.CourseConsultationDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class CourseConsultationDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private CallableStatement cstat;
	private ResultSet rs;
	
	public CourseConsultationDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}			
	}
	
	
	
	
	
	public ArrayList<CourseConsultationDTO> list(String pstudentNum) {
		//교육생이 수업 상담 조회하는 메서드
		try {
			
			String sql = "{ call proclistCConsultation(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pstudentNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2); //커서가 반환한 값을 rs로 형변환
			
			ArrayList<CourseConsultationDTO> list = new ArrayList<CourseConsultationDTO>();
			
			while (rs.next()) {
				CourseConsultationDTO dto = new CourseConsultationDTO();
				
				dto.setConsultDate(rs.getString("CDATE"));
				dto.setReason(rs.getString("CCATEGORY"));
				dto.setContent(rs.getString("CCONTENT"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("CourseConsultationDAO.list()");
			e.printStackTrace();
		}
		
		return null;		
	}
	
	
	
	
}
