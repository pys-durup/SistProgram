package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.TeacherEvaluationListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherEvaluationListDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public TeacherEvaluationListDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

/**
 * 교사평가조회
 * @param pseq 교사번호
 * @return
 */
public ArrayList<TeacherEvaluationListDTO> telist(String pseq){
	
	try {
		
		String sql = "{ call proc_TeacherEvaluationList(?,?) }";	
		
		cstat = conn.prepareCall(sql);
		cstat.setString(1, pseq);
		cstat.registerOutParameter(2, OracleTypes.CURSOR);
		
		cstat.executeQuery();
		
		rs = (ResultSet) cstat.getObject(2);
		ArrayList<TeacherEvaluationListDTO> list = new ArrayList<TeacherEvaluationListDTO>();
		while (rs.next()) {
			TeacherEvaluationListDTO tedto = new TeacherEvaluationListDTO();
			
			tedto.setSeq(rs.getString("seq"));
			tedto.setCourseNum(rs.getString("courseNum"));
			tedto.setStartDate(rs.getString("startDate"));
			tedto.setEndDate(rs.getString("endDate"));
			tedto.setName(rs.getString("name"));
			tedto.setMaterials(rs.getString("materials"));
			tedto.setCommunication(rs.getString("communication"));
			tedto.setJobPreparing(rs.getString("jobPreparing"));
			tedto.setDivisionTime(rs.getString("divisionTime"));
			tedto.setTotalStudent(rs.getString("totalStudent"));
			
			list.add(tedto);
			
		}
		rs.close();
		stat.close();
		conn.close();
		return list;
		
		
		
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	
	return null;
	
}



}