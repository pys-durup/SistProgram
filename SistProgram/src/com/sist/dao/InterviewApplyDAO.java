package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AvailableApplyListDTO;
import com.sist.dto.InterviewApplyDTO;
import com.sist.main.DBUtil;

/**
 * 예비교육생의 면접 신청 DAO 클래스
 * @author 김소리
 * */


public class InterviewApplyDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	
	public InterviewApplyDAO() {
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 예비학생의 면접 신청하기
	 * @param dto.getCreatedCourceNum() 예비교육생이 신청한 개설과정번호
	 * @param dto.getReserveStudentNum() 예비교육생 번호
	 * */	
	public int addApply(InterviewApplyDTO dto) {
		
		try {
			String sql = "{ call procaddApply(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getCreatedCourceNum());
			cstat.setString(2, dto.getReserveStudentNum());
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("InterviewApplyDAO.addApply()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	/**
	 * 예비교육생이 신청가능한 면접 리스트를 보는 메서드
	 * */
	
	public ArrayList<AvailableApplyListDTO> list () {
		
		try {
		
			String sql = "select * from vw_listApply";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<AvailableApplyListDTO> list = new ArrayList<AvailableApplyListDTO>();
			
			
			while (rs.next()) {
				AvailableApplyListDTO dto = new AvailableApplyListDTO();
				
				
				dto.setCseq(rs.getString("cseq"));
				dto.setCname(rs.getString("cname"));
				dto.setCstartdate(rs.getString("cstartdate"));
				dto.setCenddate(rs.getString("cenddate"));
				dto.setCpurpose(rs.getString("cpurpose"));
				dto.setTname(rs.getString("tname"));
				
				list.add(dto);
			}
			return list;
			
			
		} catch(Exception e) {
			System.out.println("InterviewApplyDAO.list()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	
	
}
