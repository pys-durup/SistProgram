package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.InterviewDataDTO;
import com.sist.dto.StudentConsultListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class InterviewDataDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public InterviewDataDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("InterviewDataDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<InterviewDataDTO> list() {
		
		try {String sql = String.format("select * from tblInterview order by seq asc");
		 
		rs = stat.executeQuery(sql);
		
		ArrayList<InterviewDataDTO> list = new ArrayList<InterviewDataDTO>();
		
		while(rs.next()) {
			InterviewDataDTO dto = new InterviewDataDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setQuestion(rs.getString("question"));
			dto.setStandard(rs.getString("standard"));
							
			list.add(dto);				
		}
		
		return list;
		
	} catch (Exception e) {
		System.out.println("InterviewDataDAO.list()");
		e.printStackTrace();
	}
	return null;
			
			
	}

	public int add(InterviewDataDTO dto) {
				
		try {
			String sql = "insert into tblInterview (seq, question, standard) values (seqInterview.nextVal, ?, ?)";
		
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getQuestion());
			pstat.setString(2, dto.getStandard());
					
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("InterviewDataDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(String seq) {
			
			try {
				
				String sql = "delete from tblInterview where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate(); 
				
			} catch (Exception e) {
				System.out.println("InterviewDataDAO.delete()");
				e.printStackTrace();
			}

		return 0;
	}

	public InterviewDataDTO get(String num) {
		
		try {
			
			String sql = "select * from tblInterview where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				InterviewDataDTO dto = new InterviewDataDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setQuestion(rs.getString("question"));
				dto.setStandard(rs.getString("standard"));
							
				return dto;
			} 
			
		} catch (Exception e) {
			System.out.println("InterviewDataDAO.get()");
			e.printStackTrace();
		}

		return null;
	}

	public int edit(InterviewDataDTO dto2) {
		try {
			
			String sql = "update tblInterview set question=?, standard=? where seq=?";
			
			pstat= conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getQuestion());
			pstat.setString(2, dto2.getStandard());
			pstat.setString(3, dto2.getSeq());		

			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InterviewDataDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}
}
