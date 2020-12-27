package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.QualificationDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교육생의 구직활동정보 DAO 클래스
 * @author 김소리
 * */

public class QualificationDAO {


	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private CallableStatement cstat;
	private ResultSet rs;
	
	public QualificationDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 교육생이 구직활동정보를 글 등록하는 메서드
	 * */
	public int addQualification(QualificationDTO dto) {
		
		try {
		
			String sql = "{ call procQualification(?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
					
			cstat.setString(1, dto.getLicense());
			cstat.setString(2, dto.getResume());
			cstat.setString(3, dto.getJob());
			cstat.setString(4, dto.getGithub());
			cstat.setString(5, dto.getSalary());
			cstat.setString(6, dto.getRegiNum());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("QualificationDAO.addQualification");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * 교육생이 구직정보를 조회하는 메서드
	 * */
	public ArrayList<QualificationDTO> list(String pregiNum) {
		
		try {
			
			String sql = "{ call proclistQ(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pregiNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();
		
			rs = (ResultSet)cstat.getObject(2); //rs로 커서가 받아온 값을 형변환
			
			
			ArrayList<QualificationDTO> list = new ArrayList<QualificationDTO>();
			
			while (rs.next()) {
				QualificationDTO dto = new QualificationDTO();
				
				//구직활동 기록번호는 보여주지 않을 예정. 리스트에 담지 않는다.
				dto.setLicense(rs.getString("license"));
				dto.setResume(rs.getString("resume"));
				dto.setJob(rs.getString("Job"));
				dto.setGithub(rs.getString("github"));
				dto.setSalary(rs.getString("salary"));
				//dto.setRegiNum(rs.getString("regiNum"));
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("QualificationDAO.list()");
			e.printStackTrace();		
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 교육생이 본인이 작성한 구직정보를 수정하는 메서드
	 * */
	public int editQualification(QualificationDTO dto) {
		
		try {
			String sql = "{ call procupdateQ(?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getLicense());
			cstat.setString(2, dto.getResume());
			cstat.setString(3, dto.getJob());
			cstat.setString(4, dto.getGithub());
			cstat.setString(5, dto.getSalary());
			cstat.setString(6, dto.getRegiNum());
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("QualificationDAO.editQualification()");
			e.printStackTrace();
			
		}
		
		return 0;		
	}
	
	
	/**
	 * 교육생이 본인이 작성한 구직활동정보를 삭제하는 메서드
	 * */
	public int deleteQualification(String regiNum) { 
		
		try {
			String sql = "{ call procdeleteQ(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, regiNum);
			
			return cstat.executeUpdate();
			
			
		} catch(Exception e) {
			System.out.println("QualificationDAO.deleteQualification()");
			e.printStackTrace();	
		}
		
		return 0;
	}
	
	
	
}
