package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.ScoreListStudentDTO;
import com.sist.dto.StudentConsultListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class ScoreListStudentDAO {
	
	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat; 
	private ResultSet rs;
	private CallableStatement cstat;
	
	public ScoreListStudentDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("ScoreListStudentDAO()");
			e.printStackTrace();
		}	
		
	}
/**
 * 교육생별 성적조회를 위해 교육생리스트 리턴하는 메서드
 * @return
 */
	public ArrayList<ScoreListStudentDTO> list() {

		try {
			String sql = "select * from vwScoreListStudent";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<ScoreListStudentDTO> list = new ArrayList<ScoreListStudentDTO>();
			
			while(rs.next()) {
				ScoreListStudentDTO dto = new ScoreListStudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSname(rs.getString("sname"));
				dto.setJumin(rs.getString("jumin"));
				dto.setCname(rs.getString("cname"));
				dto.setCourseterm(rs.getString("courseterm"));
				
				list.add(dto);				
			}
			
			return list;
			
			
			
		} catch (Exception e) {
			System.out.println("ScoreListStudentDTO.list()");
			e.printStackTrace();
		}
		return null;
	}

}
