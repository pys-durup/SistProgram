package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AttendanceDTO;
import com.sist.dto.AttendanceInfoDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.dto.StudentInfoListDTO;
import com.sist.dto.StudentsAttendanceDTO;
import com.sist.dto.SubjectListDTO;
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
	
	
	
	public ArrayList<StudentsAttendanceDTO> list(String pstudentNum) { 
		//매개변수로 학생번호 받아오는 것(수정함. 프로시저 변경)
		try {
			String sql = "{ call procWholeAttendance(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pstudentNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			rs = (ResultSet)cstat.getObject(2); //ResultSet으로 커서가 반환한 값을 형변환
			
			ArrayList<StudentsAttendanceDTO> list = new ArrayList<StudentsAttendanceDTO>();
			
			while (rs.next()) {
				
				StudentsAttendanceDTO dto = new StudentsAttendanceDTO(); 
				
				dto.setAlldates(rs.getString("ALLDATE"));
				dto.setInTime(rs.getString("inTime"));
				dto.setOutTime(rs.getString("outTime"));
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



	/**
	 * 학생 번호를 매개변수로 입력받아 해당 학생이 수강하고있는 과정의 모든 출석정보 리턴
	 * @param stnum 학생번호
	 * @return
	 */
	public ArrayList<AttendanceInfoDTO> courseAttList(String stnum) {
		
		try {
			
			String sql = " { call procAttCourseList( ?, ?) }";
			ArrayList<AttendanceInfoDTO> list = new ArrayList<AttendanceInfoDTO>();
			
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1,	stnum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				AttendanceInfoDTO dto = new AttendanceInfoDTO();
				
				String intime = rs.getString("intime");
				String outtime = rs.getString("outtime");
				
				if(intime == null) {
					intime = "    -    ";
				}
				
				if(outtime == null) {
					outtime = "    -    ";
				}
				
				dto.setAlldate(rs.getString("alldate"));
				dto.setIntime(intime);
				dto.setOuttime(outtime);
				dto.setAttstate(rs.getString("attstate"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.encourseAttList()");
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 학생 번호를 매개변수로 입력받아 해당 학생이 수강하고있는 과목 목록을 리턴
	 * @param stnum 학생번호
	 * @return
	 */
	public ArrayList<SubjectListDTO> subjectList(String stnum) {
		
		try {
			
			String sql = "{ call procStudentregiSubjectList(?, ?) }";
			ArrayList<SubjectListDTO> list = new ArrayList<SubjectListDTO>();
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, stnum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				SubjectListDTO dto = new SubjectListDTO();
				
				dto.setSubjectseq(rs.getString("subjectseq"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setBookname(rs.getString("bookname"));
				dto.setTeachername(rs.getString("teachername"));
				
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.ensubjectList()");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 * 과목 번호를 받아서 과목 객체의 정보를 리턴하는 메서드
	 * @param subjectNum 과목번호
	 * @return
	 */
	public SubjectListDTO getsubjectInfo(String subjectNum) {
		
		try {
			
			String sql = "select * from vwMakeSubjectList where subjectseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, subjectNum);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				SubjectListDTO dto = new SubjectListDTO();
					
				dto.setSubjectseq(rs.getString("subjectseq"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setBookname(rs.getString("bookname"));
				dto.setTeachername(rs.getString("teachername"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.engetsubjectInfo()");
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 학생번호와 과목번호를 받아서 해당 학생이 해당 과목을 듣는동안 생긴 출결데이터 리턴
	 * @param stnum 학생번호
	 * @param subjectnum 과목번호
	 * @return
	 */
	public ArrayList<AttendanceInfoDTO> subjectAttList(String stnum, String subjectnum) {
		
		try {
			
			String sql = " {call procAttSubjectList( ?, ?, ?) } ";
			ArrayList<AttendanceInfoDTO> list = new ArrayList<AttendanceInfoDTO>();
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, stnum);
			cstat.setString(2, subjectnum);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			while (rs.next()) {
				
				AttendanceInfoDTO dto = new AttendanceInfoDTO();
				
				String intime = rs.getString("intime");
				String outtime = rs.getString("outtime");
				
				if(intime == null) {
					intime = "-";
				}
				
				if(outtime == null) {
					outtime = "-";
				}
				
				dto.setAlldate(rs.getString("alldate"));
				dto.setIntime(intime);
				dto.setOuttime(outtime);
				dto.setAttstate(rs.getString("attstate"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.ensubjectAttList()");
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 
	 * @return
	 */
	public ArrayList<EndCourseListDTO> allCourseList() {

		try {
			
			String sql = "select * from vwAllCourseList";
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
			System.out.println("primaryAttendanceDAO.enallCourseList()");
			e.printStackTrace();
		}
		return null;
	}

	// StudentInfoList


	public ArrayList<StudentInfoListDTO> courseStudentList(String courseNum) {
		
		try {
			
			String sql = "{ call procCourseStudentList( ?, ?) }";
			ArrayList<StudentInfoListDTO> list = new ArrayList<StudentInfoListDTO>();
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, courseNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				StudentInfoListDTO dto = new StudentInfoListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			
			}
			
			return list;
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryAttendanceDAO.encourseStudentList()");
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

