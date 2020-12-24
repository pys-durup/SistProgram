package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AttendanceDTO;
import com.sist.dto.StudentsAttendanceDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;


public class AttendanceDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
	private ResultSet rs = null;
	
	public AttendanceDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.enAttendanceDAO()");
			e.printStackTrace();
		}
	}
	
	
	

	public int addAttendance(AttendanceDTO dto) {
		//교육생 출석체크하기
		try {
			String sql = "{ call procaddAttendance(?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getRegiNum());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AttendanceDAO.addAttendance()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	
	public ArrayList<StudentsAttendanceDTO> list(String pregiNum, String pcreatedCourceNum) { 
		//매개변수로 수강번호, 개설과정번호를 받아와야 함
		//출석 전체조회 프로시저 호출 -> 보여줄 컬럼이 순수테이블 DTO가아닌 join된 테이블 DTO 필요.
		try {
			String sql = "{ call proclistAttendance(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pregiNum);
			cstat.setString(2, pcreatedCourceNum);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			
			rs = (ResultSet)cstat.getObject(3); //ResultSet으로 커서가 반환한 값을 형변환
			
			ArrayList<StudentsAttendanceDTO> list = new ArrayList<StudentsAttendanceDTO>();
			
			while (rs.next()) {
				
				StudentsAttendanceDTO dto = new StudentsAttendanceDTO(); 
				
				dto.setAlldates(rs.getString("ALLDATE"));
				dto.setAttstate(rs.getString("attstate"));
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("AttendanceDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public ArrayList<StudentsAttendanceDTO> listChoice(String pstudentNum, String pstartDate, String pendDate) {
		
		try {
			String sql = "{ call procChoiceRange(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pstudentNum);
			cstat.setString(2, pstartDate);
			cstat.setString(3, pendDate);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			rs = (ResultSet)cstat.getObject(4); //커서 반환값을 rs로 형변환
			
			ArrayList<StudentsAttendanceDTO> list = new ArrayList<StudentsAttendanceDTO>();
			
			while (rs.next()) {
				
				StudentsAttendanceDTO dto = new StudentsAttendanceDTO();
				
				dto.setDays(rs.getString("days"));
				dto.setInTime(rs.getString("inTime"));
				dto.setOutTime(rs.getString("outTime"));
				dto.setDayState(rs.getString("dayState"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("AttendanceDAO.listChoice()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
