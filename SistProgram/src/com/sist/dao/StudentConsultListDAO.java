package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.StudentConsultListDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class StudentConsultListDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public StudentConsultListDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("StudentConsultListDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<StudentConsultListDTO> StudentConsultList(String word) {
		try {
			
			String sql = "{call procStudentConsultList(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, "1"); 
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<StudentConsultListDTO> list = new ArrayList<StudentConsultListDTO>();
			
			while(rs.next()) {
				StudentConsultListDTO dto = new StudentConsultListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setConsultDate(rs.getString("consultDate"));
				dto.setSname(rs.getString("sname"));
				dto.setSubjectSeq(rs.getString("subjectSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setCourseDate(rs.getString("courseDate"));
				dto.setConsultReason(rs.getString("consultReason"));
				dto.setConsultContent(rs.getString("consultContent"));
				
				list.add(dto);				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("StudentConsultListDAO.list()");
			e.printStackTrace();
		}
		return null;
	}
//**************진행중
	//상담 추가
	public int add(StudentConsultListDTO dto) {
		
		try {
			
//			string sql = "{}"
					
//			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto.getConsultDate());
			cstat.setString(2, dto.getSname());
			cstat.setString(3, dto.getSubjectSeq());
			cstat.setString(4, dto.getSubjectName());
			cstat.setString(5, dto.getCourseDate());
			cstat.setString(6, dto.getConsultReason());
			cstat.setString(7, dto.getConsultContent());
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentConsultListDTO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//상담 삭제
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblCourseConsultation where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate(); // 1 or 0 반환
			
		} catch (Exception e) {
			System.out.println("AddressDAO.delete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
}
