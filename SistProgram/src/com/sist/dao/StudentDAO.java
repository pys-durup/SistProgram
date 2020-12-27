package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.StudentDTO;
import com.sist.dto.StudentsRegiCourceDTO;
import com.sist.main.DBUtil;
import oracle.jdbc.OracleTypes;


/**
 * 교육생 DAO 클래스
 * @author 김소리
 * */


public class StudentDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교육생 DAO의 기본 생성자이다.
	 */
	public StudentDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("CStudentDAO.StudentDAO() : " + e.toString());
		}//try 
	
	}//StudentDAO() 
	
	
	/**
	 * 교육생 계정의 정보를 얻어오는 메서드
	 *  @param seq 로그인한 교육생의 번호
	 * */
	public StudentDTO getStudent(String seq) {
		
		try {
			String sql = "select * from tblStudent where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {	
				StudentDTO dto = new StudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;			
				}
			
		} catch(Exception e) {
			System.out.println("StudentDAO.getStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 로그인한 교육생의 수강정보, 수료 정보, 글작성내역을 얻어오는 메서드
	 * @param seq 로그인한 교육생의 번호
	 * */
	
	public StudentsRegiCourceDTO getStudentsRegiCource(String seq) { //교육생번호 매개변수
		//교육생, 수강테이블, 수료테이블 조인 정보 얻어오기
		
		try {
			String sql = 
					  "select"
					+ " a.seq as studentNum,"
					+ "a.name,"
					+ "a.jumin,"
					+ "a.tel,"
					+ "a.regdate,"
					+ "b.seq as RegiCourceSeq,"
					+ "b.createdCourceNum,"
					+ "b.regiStateNum,"
					+ "(select tblRegistate.regiState from tblRegistate where b.regiStateNum = tblRegistate.seq) as registate,"
					+ "c.seq as courceCompletNum,"
					+ " d. startdate,"
					+ " d. enddate,"
					+ " (select tblCourse.name from tblCourse where tblCourse.seq = d.courceNum) as cName,"//과정명
					+ " (select count(tblTeacherEvaluation.completNum) from tblTeacherEvaluation where tblTeacherEvaluation.completNum = c.seq) as evalNum," //평가 개수(1개만 가능하게끔 유효청 처리에 사용)
					+ " (select count(tblQualification.regiNum) from tblQualification where tblQualification.regiNum = b.seq) as qNum" //구직활동글 개수 (1개만 가능하게 유효성 처리에 사용)
					+ " from tblStudent a" 
					+ " inner join tblRegiCource b"
					+ " on a.seq = b.studentNum"
					+ " left outer join tblCourceComplet c" //수료번호 null 처리&자바 예외처리를 위해 outerjoin
					+ " on c.regiNum = b.seq"
					+ " inner join tblMakeCource d"
					+ " on d.seq = b.createdCourceNum"
					+ " where a.seq = ?";
			//sql구문의 띄어쓰기가 제대로 안 되면 sql오류가 일어날 수 있다.
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery(); 
			
			if (rs.next()) {
				StudentsRegiCourceDTO dto = new StudentsRegiCourceDTO();
				
				dto.setSeq(rs.getString("studentNum")); //학생번호
				dto.setName(rs.getString("name")); //교육생명
				dto.setJumin(rs.getString("jumin")); //주민번호
				dto.setTel(rs.getString("tel")); //연락처
				dto.setRegdate(rs.getString("regdate")); //교육생등록일
				dto.setrSeq(rs.getString("RegiCourceSeq")); //수강번호
				dto.setCreatedCourceNum(rs.getString("createdCourceNum")); //개설과정번호
				dto.setRegiStateNum(rs.getString("regiStateNum")); //수강상태번호
				dto.setRegiState(rs.getString("registate")); //수강상태
				dto.setCourceCompletNum(rs.getString("courceCompletNum")); //수료번호
				dto.setStartDate(rs.getString("startDate")); //과정시작일
				dto.setEndDate(rs.getString("endDate")); //과정종료일
				dto.setcName(rs.getString("cName")); //과정명
				dto.setEvalNum(rs.getString("evalNum")); //교사평가 글 개수
				dto.setqNum(rs.getString("qNum")); //구직활동 글 개수
				
				
				return dto;
			}
			
			
		} catch(Exception e) {
			System.out.println("StudentsRegiCourceDAO.getStudentsRegiCource()");
			e.printStackTrace();
		}
		
		return null;
	}


	public ArrayList<StudentDTO> list(String seq) {
	    try {

		String sql = "{ call procStudentStatus(?, ?) }";
		
		cstat = conn.prepareCall(sql);
		cstat.registerOutParameter(1, OracleTypes.CURSOR);
		cstat.setString(2, seq);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(1);
		
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		while(rs.next()) {
		    StudentDTO dto = new StudentDTO();
		    
		    dto.setSeq(rs.getString("수강생번호"));
		    dto.setName(rs.getString("수강생이름"));
		    dto.setJumin(rs.getString("주민번호"));
		    dto.setTel(rs.getString("전화번호"));
		    dto.setRegdate(rs.getString("등록일"));
		    dto.setRegistate(rs.getString("registate"));
		    
		    
		    list.add(dto);
		}
		
		rs.close();
		return list;
		
	    } catch (Exception e) {
		System.out.println("StudentDAO.list()");
		e.printStackTrace();
	    }
	    
	    return null;
	}

	public int UpdateStatusStudent(StudentDTO dto) {  //과정별 학생 수강상태 수정
	    
	    try {

		String sql = " { call procUpdateStudentStatusEach (?, ?) } ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, dto.getPseq());	
		cstat.setString(2, dto.getPrseq());
				
		return cstat.executeUpdate();

	    } catch (Exception e) {
		System.out.println("StudentDAO.UpdateStatusStudent()");
		e.printStackTrace();
	    }
	    return 0;
	}


	public int UpdateStatusEachStudent(StudentDTO dto2) { //개별 수강생 수강상태 수정
	    try {

		String sql = " { call procUpdateStudentStatus (?, ?) } ";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, dto2.getPseq());		//학생번호
		cstat.setString(2, dto2.getPrseq());		//수강정보 
				
		return cstat.executeUpdate();

	    } catch (Exception e) {
		System.out.println("StudentDAO.UpdateStatusEachStudent()");
		e.printStackTrace();
	    }
	    
	    return 0;
	}

	
	

}
