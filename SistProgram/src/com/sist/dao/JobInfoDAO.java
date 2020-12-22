package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.JobInfoDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class JobInfoDAO {
	

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public JobInfoDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	

	public int addJobInfo(JobInfoDTO dto) {
		
		//교육생이 취업내역을 등록하는 메서드
		try {
			String sql = "{ call procaddJobInfo(?, ?, ?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getStartDate());
			cstat.setString(2, dto.getInsurance());
			cstat.setString(3, dto.getForm());
			cstat.setString(4, dto.getCareer());
			cstat.setString(5, dto.getIncome());
			cstat.setString(6, dto.getCompletNum());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("JobInfoDAO.addJobInfo()");
			e.printStackTrace();			
		}
		
		return 0;
	}
	
	
	
	public ArrayList<JobInfoDTO> list(String completNum) {
		//교육생이 취업내역 정보를 조회하는 메서드
		try {
			String sql = "{ call proclistJob(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, completNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			rs = (ResultSet)cstat.getObject(2); //커서 반환값을 rs로 형변환
			
			ArrayList<JobInfoDTO> list = new ArrayList<JobInfoDTO>();
			
			while(rs.next()) {
				
				JobInfoDTO dto = new JobInfoDTO();
				
				dto.setStartDate(rs.getString("startDate"));
				dto.setInsurance(rs.getString("insurance"));
				dto.setForm(rs.getString("form"));
				dto.setCareer(rs.getString("career"));
				dto.setIncome(rs.getString("income"));
				
				list.add(dto);		
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("JobInfoDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public int editJobInfo(JobInfoDTO dto2) {
		
		//교육생이 취업내역을 수정하는 메서드
		try {
			String sql = "{ call procreJobInfo(?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto2.getStartDate());
			cstat.setString(2, dto2.getInsurance());
			cstat.setString(3, dto2.getForm());
			cstat.setString(4, dto2.getCareer());
			cstat.setString(5, dto2.getIncome());
			cstat.setString(6, dto2.getCompletNum());
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("JobInfoDAO.updateJobInfo()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
