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
	/**
	 * 입력받은 교육생번호로 교육생의 성적을 리턴하는 메서드
	 * @param num
	 * @return
	 */
	public ArrayList<ScStudentSubjectDTO> list(String num) {
		
		try {
			
			String sql = "{call procScStudentSubject(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, num);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			 
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<ScStudentSubjectDTO> list = new ArrayList<ScStudentSubjectDTO>();
			
			while (rs.next()) {
				
				ScStudentSubjectDTO dto = new ScStudentSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
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

	/**
	 * 성적수정시 출력하는 성적리스트를 리턴하는 메서드
	 * @return
	 */
	public ArrayList<ScStudentSubjectDTO> list() {
		
		try {
			
			String sql = "select * from vwScStudentSubject";
			

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
					
			ArrayList<ScStudentSubjectDTO> list = new ArrayList<ScStudentSubjectDTO>();
			
			while (rs.next()) {
				
				ScStudentSubjectDTO dto = new ScStudentSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSjseq(rs.getString("sjseq"));
				dto.setSname(rs.getString("sname"));
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
				
				String sql = "{call procAddScStudentSubject(?, ?, ?, ?, ?)}";
						
				cstat = conn.prepareCall(sql);
				cstat.setString(1, dto.getAttendance());
				cstat.setString(2, dto.getWrite());
				cstat.setString(3, dto.getPractice());
				cstat.setString(4, dto.getMakeSubjectNum());
				cstat.setString(5, dto.getRegiNum());
				
				return cstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("ScStudentSubjectDAO.add()");
				e.printStackTrace();
			}
		return 0;
	}

	public int delete(String seq) {
			
			try {
				
				String sql = "{call procDeleteScStudentSubject(?)}";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate(); 
				
			} catch (Exception e) {
				System.out.println("ScStudentSubjectDAO.delete()");
				e.printStackTrace();
			}
		return 0;
	}
	/**
	 * 성적 수정 메서드
	 * @param dto2
	 * @return
	 */
	public int edit(ScStudentSubjectDTO dto2) {
		
		try {
			
			String sql = "{call procEditScStudentSubject(?, ?, ?, ?)}";
			cstat= conn.prepareCall(sql);
			
			cstat.setString(1, dto2.getSeq());
			cstat.setString(2, dto2.getAttendance());
			cstat.setString(3, dto2.getWrite());
			cstat.setString(4, dto2.getPractice());
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ScStudentSubjectDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}

	public ScStudentSubjectDTO get(String num) {
		
		try {
			
			String sql = "select * from vwScStudentSubject where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				ScStudentSubjectDTO dto = new ScStudentSubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSjseq(rs.getString("sjseq"));
				dto.setSname(rs.getString("sname"));
				dto.setSjname(rs.getString("sjname"));
				dto.setDuration(rs.getString("duration"));
				dto.setTname(rs.getString("tname"));
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
