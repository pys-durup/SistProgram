package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AttendanceStatisticsDTO;
import com.sist.dto.CompletStudentListDTO;
import com.sist.dto.EmploymentRateDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class DataStatisticsDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
	private ResultSet rs = null;
	
	public DataStatisticsDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryTalentedStudentDAO.enTalentedStudentDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 종료된 과정의 수료생 목록을 받아와서 해당 목록별 출결데이터를 리턴하는 메서드
	 * @param list
	 * @return
	 */
	public ArrayList<AttendanceStatisticsDTO> attendanceStatisticsList(ArrayList<CompletStudentListDTO> list) {
		
		try {
			
			ArrayList<AttendanceStatisticsDTO> slist = new ArrayList<AttendanceStatisticsDTO>();
			
			
			for (CompletStudentListDTO dto : list) {
				
				String sql = " { call procGetAttRate( ?, ?, ?, ?, ?, ?) }";
				
				cstat = conn.prepareCall(sql);
				cstat.setString(1, dto.getReginum());
				cstat.registerOutParameter(2, OracleTypes.NUMBER);
				cstat.registerOutParameter(3, OracleTypes.NUMBER);
				cstat.registerOutParameter(4, OracleTypes.NUMBER);
				cstat.registerOutParameter(5, OracleTypes.NUMBER);
				cstat.registerOutParameter(6, OracleTypes.NUMBER);
				
								
				cstat.executeQuery();
				
				AttendanceStatisticsDTO adto = new AttendanceStatisticsDTO();
				adto.setReginum(dto.getReginum());
				adto.setName(dto.getName());
				adto.setAttendance(cstat.getInt(2));
				adto.setAbsent(cstat.getInt(3));
				adto.setLate(cstat.getInt(4));
				adto.setLeave(cstat.getInt(5));
				adto.setAttendanceRate(cstat.getInt(6));
				
				slist.add(adto);
				
				System.out.println(dto.getReginum() + "번의 출결데이터 생성중...");

				
				cstat.close();
			}
			
			return slist;
			
//			for (int i=0 ; i < list.size() ; i++) {
//				
//				String sql = " { call procGetAttRate( ?, ?, ?, ?, ?, ?) }";
//				
//				cstat = conn.prepareCall(sql);
//				
//				cstat.setString(1, list.get(i).getReginum());
//				cstat.registerOutParameter(2, OracleTypes.NUMBER);
//				cstat.registerOutParameter(3, OracleTypes.NUMBER);
//				cstat.registerOutParameter(4, OracleTypes.NUMBER);
//				cstat.registerOutParameter(5, OracleTypes.NUMBER);
//				cstat.registerOutParameter(6, OracleTypes.NUMBER);
//				
//				cstat.executeQuery();
//				
//				AttendanceStatisticsDTO adto = new AttendanceStatisticsDTO();
//				adto.setReginum(list.get(i).getReginum());
//				adto.setName(list.get(i).getName());
//				adto.setAttendance(cstat.getInt(2));
//				adto.setAbsent(cstat.getInt(3));
//				adto.setLate(cstat.getInt(4));
//				adto.setLeave(cstat.getInt(5));
//				adto.setAttendanceRate(cstat.getInt(6));
//				
//				slist.add(adto);
//				
//				System.out.println(list.get(i).getReginum() + "번의 출결데이터 생성중...");
//				System.out.println("출석일수 " + adto.getAttendance());
//				
//				cstat.close();
//				
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryDataStatisticsDAO.enattendanceStatisticsList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 종료된 과정의 개설 과정 번호를 받아와서 해당 과정의 수료인원, 중도탈락인원, 수료율을 반환한다
	 * @param num 개설 과정 번호
	 * @return
	 */
	public ArrayList<String> completionRateInfo(String num) {
		
		try {
			
			ArrayList<String> list = new ArrayList<String>();
			String sql = "{ call procGetCompletRate( ?, ?, ?, ?) }" ;
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			cstat.registerOutParameter(3, OracleTypes.NUMBER);
			cstat.registerOutParameter(4, OracleTypes.NUMBER);
			
			cstat.executeQuery();
			
			int complet = cstat.getInt(2);
			int fail = cstat.getInt(3);
			float rate = (float)complet / (complet + fail)  * 100;
			
			list.add(String.valueOf(complet));
			list.add(String.valueOf(fail));
			list.add(String.valueOf(rate));
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryDataStatisticsDAO.encompletionRateInfo()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 종료된 과정의 개설 과정 번호를 받아와서 해당 과정의 수료인원, 취업인원, 취업률을 반환
	 * @return
	 */
	public ArrayList<EmploymentRateDTO> employmentRateInfo(String num) {
		
		try {
			
			String sql = "{ call procGetJobRate( ?,?,?,?,?) }";
			ArrayList<EmploymentRateDTO> list = new ArrayList<EmploymentRateDTO>();
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			cstat.registerOutParameter(3, OracleTypes.NUMBER);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			cstat.registerOutParameter(5, OracleTypes.NUMBER);
			
			cstat.executeQuery();
			
			EmploymentRateDTO dto = new EmploymentRateDTO();
			int completStudent = cstat.getInt(2);
			dto = new EmploymentRateDTO("수료 인원" , String.valueOf(cstat.getInt(2)));
			list.add(dto);
			dto = new EmploymentRateDTO("취업 인원" , String.valueOf(cstat.getInt(3)));
			list.add(dto);
			
			rs = (ResultSet)cstat.getObject(4);
			
			while (rs.next()) {
				
				dto = new EmploymentRateDTO();
				
				String empdate = rs.getString("empdate");
				String stcount = rs.getString("stcount");
				float rate = Math.round(Integer.parseInt(stcount) / (float)completStudent * 100);
				
				dto.setColumn(empdate.substring(0,4) + "년 " + empdate.substring(4) + "월 취업인원");
				dto.setValue(stcount + "명");
				
				list.add(dto);
				
				dto = new EmploymentRateDTO();
				
				dto.setColumn(empdate.substring(0,4) + "년 " + empdate.substring(4) + "월 취업률");
				dto.setValue(String.valueOf(rate) + "%");
				
				list.add(dto);
			}
			
			dto = new EmploymentRateDTO("총 취업률" , String.valueOf(cstat.getInt(5) + "%"));
			list.add(dto);
			
			return list;
			
			
			
		} catch (Exception e) {
			System.out.println("primaryDataStatisticsDAO.enemploymentRateInfo()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
}
