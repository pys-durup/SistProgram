package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.InterviewResultDTO;
import com.sist.dto.ReserveStudentDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;


/**
 * 예비교육생 DAO 클래스
 * @author 김소리
 * */
public class ReserveStudentDAO {
	

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	
	public ReserveStudentDAO() {
	
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
				
		} catch (Exception e) {
			System.out.println("ReserveStudentDAO.ReserveStudentDAO()");
			e.printStackTrace();
		}
		
	}

	/**
	 *로그인한 예비교육생의 정보를 불러오는 메서드 
	 *@param seq 로그인한 예비교육생의 번호
	 * */
	
	public ReserveStudentDTO getReserveStudent(String seq) {
		
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
	
	
	
	/**
	 * 예비학생의 개인정보 수정 메서드
	 * */
	public int editReserveStudent(ReserveStudentDTO dto) {
		
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
	
	
	
	/**
	 * 로그인한 예비교육생의 면접 결과를 불러오는 메서드
	 * @param seq 로그인한 예비교육생의 번호
	 * */
	public InterviewResultDTO getInterviewResult(String seq) {
		
		
		//outer join을 해서 면접결과가 null값인 회원을 자바 상에서 예외처리
		try {
			String sql = "SELECT "
					//+ "a.name as rName,"
					+ " d.seq as cSeq,"
					+ " e.name as cName,"
					+ " c.result"
					+ " FROM tblReserveStudent a"
					+ " left outer join tblInterviewApply b"
					+ " on a.seq = b.reserveStudentNum"
					+ " left outer join tblInterviewResult c" 
					+ " on c.interviewNum = b.seq"
					+ " left outer join tblMakeCource d"
					+ " on d.seq = b.createdCourceNum"
					+ " left outer join tblCourse e"
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
	
	
	
	/**
	 * 면접 합격한 예비교육생이 교육생으로 계정전환하는 메서드
	 * @param seq 로그인한 예비교육생의 번호
	 * */
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
	
	
	
	/**
	 *예비교육생이 회원가입하는 메서드
	 * */
	public int addNewReserve(ReserveStudentDTO dto) {
		
		try {
			String sql = "{ call procaddReserve(?, ?, ?, ?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, dto.getName());
			cstat.setString(2, dto.getJumin());
			cstat.setString(3, dto.getTel());
			cstat.setString(4, dto.getAddress());
			cstat.setString(5, dto.getField());
			cstat.setString(6, dto.getKnowledge());
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.addNewReserve()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	/**
	 * 관리자가 전체 예비교육생(계정전환X) 조회하는 메서드 
	 * */
	public ArrayList<ReserveStudentDTO> list() {
		
		
		try {
			String sql = "select * from vw_rStudentList";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ReserveStudentDTO> list = new ArrayList<ReserveStudentDTO>();
			
			while (rs.next()) {
				
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setField(rs.getString("field"));
				dto.setKnowledge(rs.getString("knowledge"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	/**
	 * 관리자가 면접일자가 미정인 과정명을 조회하는 메서드
	 * */
	public ArrayList<ReserveStudentDTO> noDateList() {
		
		try {
			String sql = "select * from vw_neededcourseList";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ReserveStudentDTO> list = new ArrayList<ReserveStudentDTO>();
			
			while (rs.next()) {
				
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setCreatedCourceNum(rs.getString("cSeq"));
				dto.setcName(rs.getString("cName"));
				dto.setStartDate(rs.getString("startDate"));
				
				list.add(dto);
			}
			return list;
			
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.noDateList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 관리자가 과정의 해당 면접일정 스케줄에 일치하는 교육생 정보를 조회하는 메서드
	 * @param pcreatedCourceNum 상세조회하고자 하는 개설과정번호
	 * */
	public ArrayList<ReserveStudentDTO> detailedApply(String pcreatedCourceNum) {
		
		
		try {
			
			String sql = "{ call procdetailList(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pcreatedCourceNum);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeUpdate();
			
			rs = (ResultSet)cstat.getObject(2); // 커서 반환값을 rs로 형변환
			
			ArrayList<ReserveStudentDTO> list = new ArrayList<ReserveStudentDTO>();
	
			while (rs.next()) {
				
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setcName(rs.getString("cName"));
				dto.setCreatedCourceNum(rs.getString("CREATEDCOURCENUM"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.detailedApply()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 관리자가 특정 교육과정의 면접일을 지정하는 메서드
	 * @param createdCourceNum 면접일을 지정하고자 하는 개설과정번호
	 * @param choicedDate 지정하고자 하는 면접일자 
	 * */
	
	public int addChoicedDate(String createdCourceNum, String choicedDate) {
		//관리자가 특정 교육과정의 면접일을 지정하는 메서드
		try {
			String sql = "{ call procEditDate(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, createdCourceNum);
			cstat.setString(2, choicedDate);
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.addChoicedDate()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	/**
	 * 관리자가 면접 일정을 전체조회하는 메서드
	 * @return
	 * */
	public ArrayList<ReserveStudentDTO> scheduleList() {
		
		try {
			String sql = "select * from vw_interviewDateList";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ReserveStudentDTO> list = new ArrayList<ReserveStudentDTO>();
			
			while (rs.next()) {
				
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setCreatedCourceNum(rs.getString("cSeq"));
				dto.setInterviewDate(rs.getString("interviewDate"));
				dto.setcName(rs.getString("cName"));
				dto.setStartDate(rs.getString("startDate"));
				
				
				list.add(dto);
			}
			return list;
			
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.scheduleList()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	
	/**
	 * 관리자가 종료된 면접 목록을 조회하는 메서드
	 * @return
	 * */
	public ArrayList<ReserveStudentDTO> finisheInterviewList() {
		
		try {
			String sql = "select * from vw_finishedInterviewList";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ReserveStudentDTO> list = new ArrayList<ReserveStudentDTO>();
			
			while (rs.next()) {
				
				ReserveStudentDTO dto = new ReserveStudentDTO();
				
				dto.setcName(rs.getString("cName"));
				dto.setInterviewDate(rs.getString("interviewDate"));
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				
				list.add(dto);
			}
			return list;
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.finisheInterviewList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	/**
	 * 관리자가 예비교육생의 면접 합격 여부를 지정하는 메서드
	 * @param studentNum 예비교육생의 번호
	 * @param resultNum 면접 합격 여부 (합: 1, 불합: 2)
	 * */
	
	public int addPassFail(String studentNum, String resultNum) {
		
		try {
			String sql = "{ call procaddResult(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, studentNum);
			cstat.setString(2, resultNum);
			
			return cstat.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("ReserveStudentDAO.addPassFail()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	
}
