package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.setScoreListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class setScoreListDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public setScoreListDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
} 
	
public ArrayList<setScoreListDTO> list(String pseq){
	
	try {
		   String sql = "{ call proc_setScoreList(?,?) }";
		   
		   cstat = conn.prepareCall(sql);
		   cstat.setString(1, pseq);
		   cstat.registerOutParameter(2, OracleTypes.CURSOR);
		   
		   cstat.executeQuery();
		   
		   rs = (ResultSet) cstat.getObject(2);
		   
		   ArrayList<setScoreListDTO> list = new ArrayList<setScoreListDTO>();
		   
		   while(rs.next()) {
			   setScoreListDTO dto = new setScoreListDTO ();			  
			   dto.setSubjectNum(rs.getString("subjectNum"));
			   dto.setSubjectName(rs.getString("subjectName"));
			   dto.setBookName(rs.getString("bookname"));
			   dto.setAttendance(rs.getString("attendance"));
			   dto.setWrite(rs.getString("write"));
			   dto.setPractice(rs.getString("practice"));
			   dto.setSubjectStartdate(rs.getString("subjectStartDate"));
			   dto.setSubjectEnddate(rs.getString("subjectEndDate"));
			   dto.setCourseName(rs.getString("courseName"));
			   dto.setCourseStartdate(rs.getString("courseStartdate"));
			   dto.setCourseEnddate(rs.getString("courseEnddate"));
			   dto.setRoomName(rs.getString("roomName"));
			   
			   list.add(dto);
		   }
		   rs.close();
		   return list;
		   
	} catch (Exception e) {
		System.out.println(e);
	}
	
	
	
	return null;
	
	
	
	
}




}
