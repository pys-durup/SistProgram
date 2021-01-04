package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.CConsultationDTO;
import com.sist.dto.ScoreListCourseDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class CConsultationDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public CConsultationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("CConsultationDAO()");
			e.printStackTrace();
		}	
		
	}

	public int add(CConsultationDTO dto) {
		// 교사 - 상담일지 작성
		
		try {
			
			String sql = "{ call procAddCConsultation(?, ?, ?, ?, ?, ?)}";
		
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getSeq());
			cstat.setString(2, dto.getConsultdate());
			cstat.setString(3, dto.getContent());
			cstat.setString(4, dto.getMakeSubjectNum());
			cstat.setString(5, dto.getReasonNum());
			cstat.setString(6, dto.getRegiNum());
			
					
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("CConsultationDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<CConsultationDTO> list(String seq) {
		// 교사 - 상담 일지 조회
		try {
		
			String sql = "{ call procConsultList(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<CConsultationDTO> list = new ArrayList<CConsultationDTO>();
			
			while(rs.next()) {
				CConsultationDTO dto = new CConsultationDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
				dto.setTname(rs.getString("tname"));
				dto.setSjseq(rs.getString("sjseq"));
				dto.setSjname(rs.getString("sjname"));
				dto.setCoursedate(rs.getString("coursedate"));
				dto.setConsultdate(rs.getString("consultdate"));
				dto.setReason(rs.getString("reason"));
				dto.setContent(rs.getString("content"));
				
				list.add(dto);
			}
			
			return list;
		
		} catch (Exception e) {
			System.out.println("CConsultationDAO.list()");
			e.printStackTrace();
		}
		return null;
	}
}
