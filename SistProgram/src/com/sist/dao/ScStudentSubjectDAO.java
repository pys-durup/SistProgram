package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScStudentSubjectDTO;
import com.sist.dto.ScoreListStudentDTO;
import com.sist.dto.StudentConsultListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScStudentSubjectDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScStudentSubjectDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScStudentSubjectDAO()");
			e.printStackTrace();
		}	
		
	}

	public ArrayList<ScStudentSubjectDTO> list() {
		
		try {
			
			String sql = "{call procScStudentSubject(?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(1);
			
			ArrayList<ScStudentSubjectDTO> list = new ArrayList<ScStudentSubjectDTO>();
			
			while (rs.next()) {
				
				ScStudentSubjectDTO dto = new ScStudentSubjectDTO();
				
				dto.setSjname(rs.getString("sjname"));
				dto.setDuration(rs.getString("duration"));
				dto.setTname(rs.getString("tname"));
				dto.setAttendance(rs.getString("attendance"));
				dto.setWrite(rs.getString("write"));
				dto.setPractice(rs.getString("practice"));
						
				list.add(dto);
			}
			
			return list;
		
		} catch (Exception e) {
			System.out.println("ScStudentSubjectDAO.list()");
			e.printStackTrace();
		}
		return null;
	}


	public int add(ScStudentSubjectDTO dto) {
			
			try {
				
				String sql = "{call procAddScStudentSubject(?, ?, ?)}";
						
				cstat = conn.prepareCall(sql);
				cstat.setString(1, dto.getAttendance());
				cstat.setString(2, dto.getWrite());
				cstat.setString(3, dto.getPractice());
				
				return cstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("ScStudentSubjectDAO.add()");
				e.printStackTrace();
			}
		return 0;
	}

	public int delete(String seq) {
			
			try {
				
				String sql = "delete from tblScore where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate(); 
				
			} catch (Exception e) {
				System.out.println("ScStudentSubjectDAO.delete()");
				e.printStackTrace();
			}
		return 0;
	}

	public int edit(ScStudentSubjectDTO dto2) {
		
		try {
			
			String sql = "{call procEditScStudentSubject(?, ?, ?)}";
			pstat= conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getAttendance());
			pstat.setString(2, dto2.getWrite());
			pstat.setString(3, dto2.getPractice());
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ScStudentSubjectDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	public ScStudentSubjectDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblAddress where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				ScStudentSubjectDTO dto = new ScStudentSubjectDTO();
				
				dto.setAttendance(rs.getString("attendance"));
				dto.setWrite(rs.getString("write"));
				dto.setPractice(rs.getString("practice"));
				
							
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("ScStudentSubjectDAO.get()");
			e.printStackTrace();
		}
		return null;
	}

}
