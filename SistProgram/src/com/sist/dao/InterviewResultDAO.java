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
	
	
	
}
