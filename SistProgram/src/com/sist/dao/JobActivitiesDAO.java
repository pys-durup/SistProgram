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
import com.sist.dto.JobInfoDTO;
import com.sist.dto.QualificationDTO;
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
	 * 과목 번호를 받아서 종료된 과정중 하나의 객체를 리턴하는 메서드
	 * @param num
	 * @return
	 */
	public EndCourseListDTO getEndCours(String num) {
try {
			
			String sql = "select * from vwEndCourseList where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				EndCourseListDTO dto = new EndCourseListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCourseName(rs.getString("coursename"));
				dto.setStartDate(rs.getString("startdate"));
				dto.setEndDate(rs.getString("enddate"));
				dto.setTeacherName(rs.getString("teachername"));
				dto.setRoom(rs.getString("room"));

				return dto;
			}
		

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
	public ArrayList<CompletStudentListDTO> courseCompletStudentList(String num) {
		
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
	
	/**
	 * 과정의 번호를 받아 해당 과정을 수료후 취업한 수료생의 목록을 리턴하는 메서드
	 * @param num
	 * @return
	 */
	public ArrayList<CompletStudentListDTO> gotJobCompletStudentList(String num) {
		
		try {
			
			String sql = " { call procGetJobStudentList(?, ?) }";
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

	
	/**
	 * 수강번호를 받아서 구직 정보를 조회하는 메서드
	 * @param reginum
	 * @return
	 */
	public QualificationDTO getJobActivitesInfo(String reginum) {
		// 교육생이 구직정보를 조회하는 메서드
		try {

			String sql = "{ call procJobActivitiesInfo(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, reginum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();

			rs = (ResultSet) cstat.getObject(2); // rs로 커서가 받아온 값을 형변환

			if (rs.next()) {
				QualificationDTO dto = new QualificationDTO();

				// 구직활동 기록번호는 보여주지 않을 예정. 리스트에 담지 않는다.
				dto.setLicense(rs.getString("license"));
				dto.setResume(rs.getString("resume"));
				dto.setJob(rs.getString("Job"));
				dto.setGithub(rs.getString("github"));
				dto.setSalary(rs.getString("salary"));

				return dto;
			}
			

		} catch (Exception e) {
			System.out.println("QualificationDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 전체 수료생 목록을 리턴하는 메서드 입니다
	 * @return
	 */
	public ArrayList<CompletStudentListDTO> CompletStudentList(String word) {
		
		try {
			String where = "";
			
			if(word != null) {
				where = String.format("where name like '%%%s%%'", word);
			}
			
			String sql = String.format("select * from vwCompletStudentList %s", where);
			ArrayList<CompletStudentListDTO> list = new ArrayList<CompletStudentListDTO>();
			
			stat = conn.createStatement();			
			rs = stat.executeQuery(sql);
			
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
	
	/**
	 * 수강 번호를 받아서 취업정보를 리턴하는 메서드
	 * @param reginum 수강 번호
	 * @return
	 */
	public JobInfoDTO getJobInfo(String reginum) {
		
		try {
			String sql = " { call procJobinfoList(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, reginum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			if (rs.next()) {
				JobInfoDTO dto = new JobInfoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setStartDate(rs.getString("startdate"));
				dto.setInsurance(rs.getString("insurance"));
				dto.setForm(rs.getString("form"));
				dto.setCareer(rs.getString("career"));
				dto.setIncome(rs.getString("income"));
				dto.setCompletNum(rs.getString("completnum"));
				
				return dto;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("primaryJobActivitiesDAO.engetJobInfo()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	


	
	
}
