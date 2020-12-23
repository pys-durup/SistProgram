package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.dto.InterviewResultDTO;
import com.sist.dto.ReserveStudentDTO;
import com.sist.main.DBUtil;

public class ReserveStudentDAO {
	

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/*
	 예비학생 기본정보관리에는 삭제 기능 없음.
	 */
	
	public ReserveStudentDAO() {
	
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
				
		} catch (Exception e) {
			System.out.println("ReserveStudentDAO.ReserveStudentDAO()");
			e.printStackTrace();
		}
		
	}

	
	
	public ReserveStudentDTO getReserveStudent(String seq) {
		//로그인한 예비학생의 정보를 불러오는 메서드
		try {
			String sql = "select * from tblReserveStudent where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				// dto에 해당 번호의 예비학생 정보 1행을 담는다.
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setField(rs.getString("field"));
				dto.setKnowledge(rs.getString("knowledge"));
				
				return dto;				
			}
			
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.getReserveStudent()");
			e.printStackTrace();
		}
				
		return null;
	} 
	
	
	
	
	public int editReserveStudent(ReserveStudentDTO dto) {
		//예비학생의 개인정보 수정하기
		try {
			String sql = "{ call  procreRinfo(?, ?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getSeq());
			cstat.setString(2, dto.getName());
			cstat.setString(3, dto.getJumin());
			cstat.setString(4, dto.getTel());
			cstat.setString(5, dto.getAddress());
			cstat.setString(6, dto.getField());
			cstat.setString(7, dto.getKnowledge());
			
			return cstat.executeUpdate();
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.editReserveStudent()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	public InterviewResultDTO getInterviewResult(String seq) {
		//로그인한 예비학생의 면접 결과를 불러오는 메서드
		
		
		try {
			String sql = "SELECT "
					//+ "a.name as rName,"
					+ " d.seq as cSeq,"
					+ " e.name as cName,"
					+ " c.result"
					+ " FROM tblReserveStudent a"
					+ " inner join tblInterviewApply b"
					+ " on a.seq = b.reserveStudentNum"
					+ " inner join tblInterviewResult c"
					+ " on c.interviewNum = b.seq"
					+ " inner join tblMakeCource d"
					+ " on d.seq = b.createdCourceNum"
					+ " inner join tblCourse e"
					+ " on e.seq = d.courceNum"
					+ " where a.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery(); 
			
			if (rs.next()) {
				
				InterviewResultDTO dto = new InterviewResultDTO();
				
				dto.setcSeq(rs.getString("cSeq"));
				dto.setcName(rs.getString("cName"));
				dto.setResult(rs.getString("result"));
				
				return dto;
			}
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.getInterviewResult()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public int addMigration(String seq) {
		
		try {
			String sql = "{ call procMigration(?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq);
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.addMigration()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	
	
}
