package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.CompletStudentListDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class JobActivitiesDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
	private ResultSet rs = null;
	
	public JobActivitiesDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryLinkCompanyDAO.enLinkCompanyDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 종료된 과정의 목록을 리턴하는 메서드
	 * @return
	 */
	public ArrayList<EndCourseListDTO> EndCourseList() {
	
		try {
			
			String sql = "select * from vwEndCourseList";
			ArrayList<EndCourseListDTO> list = new ArrayList<EndCourseListDTO>();
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				EndCourseListDTO dto = new EndCourseListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCourseName(rs.getString("coursename"));
				dto.setStartDate(rs.getString("startdate"));
				dto.setEndDate(rs.getString("enddate"));
				dto.setTeacherName(rs.getString("teachername"));
				dto.setRoom(rs.getString("room"));
			
				list.add(dto);
			}
		
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryJobActivitiesDAO.enEndCourseList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 과정의 번호를 받아 해당 과정을 수료한 수료생의 목록을 리턴하는 메서드
	 * @param seq 과정의 번호
	 * @return
	 */
	public ArrayList<CompletStudentListDTO> completStudentList(String num) {
		
		try {
			
			String sql = " { call procCompletStudentList(?, ?) }";
			ArrayList<CompletStudentListDTO> list = new ArrayList<CompletStudentListDTO>();
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				CompletStudentListDTO dto = new CompletStudentListDTO();
				
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReginum(rs.getString("reginum"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryJobActivitiesDAO.encompletStudentList()");
			e.printStackTrace();
		}
		
		return null;
	}


	
	
}
