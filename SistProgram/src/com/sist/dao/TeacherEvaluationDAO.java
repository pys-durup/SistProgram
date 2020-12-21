package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.TeacherEvaluationDTO;
import com.sist.main.DBUtil;

public class TeacherEvaluationDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public TeacherEvaluationDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	
}


public int addTeacherEvaluation(TeacherEvaluationDTO tdto) {
	//교육생이 교사평가를 등록하는 메서드
	try {
		String sql = "{ call procAddTeacherEvaluation(?,?,?,?,?,?) }";
		
		cstat = conn.prepareCall(sql);
		
		cstat.setString(1, tdto.getMaterials());
		cstat.setString(2, tdto.getCommunication());
		cstat.setString(3, tdto.getJobPreparing());
		cstat.setString(4, tdto.getDivisionTime());
		cstat.setString(5, tdto.getTotalPoint());
		cstat.setString(6, tdto.getCompletNum());
		
		return cstat.executeUpdate();
		
	} catch(Exception e) {
		System.out.println("TeacherEvaluationDAO.addTeacherEvaluation()");
		e.printStackTrace();
	}
	
	
	return 0;
}








}
