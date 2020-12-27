package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.sist.dto.SubjectDTO;

import com.sist.main.DBUtil;

public class SubjectDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public SubjectDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
}

public ArrayList<SubjectDTO> list(String word) {

	try {
	String where = "";
	
	// 검색어가 있으면 where절 생성
	if (word != null) {
		where = String.format("where name like '%%%s%%'", word);
	}
	
	String sql = String.format("select *  from tblSubject %s order by seq", where);
	
	rs = stat.executeQuery(sql);
	
	ArrayList<SubjectDTO> list = new ArrayList<SubjectDTO>();
	
	while (rs.next()) {
	
		SubjectDTO dto = new SubjectDTO();
		
		dto.setSeq(rs.getString("seq"));
		dto.setName(rs.getString("name"));
		dto.setDuration(rs.getString("duration"));
						
		list.add(dto);
	}
	
	return list;

	
} catch (Exception e) {
	// TODO: handle exception
	System.out.println("CourseDAO.CourseList()");
	e.printStackTrace();
}


	return null;
}

public int addSubject(String name, String duration) {
try {
		
		String sql = String.format(
				"Insert into tblSubject(seq, name, duration) values (seqCourse.nextval, '%s', '%s')", name, duration);
		
		int result = stat.executeUpdate(sql);
		return result;
		
	} catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
	}
	
	
	
	
	
	return 0;
}

public int UpdateSubject(String number, String subject, String duration) {
	return 0;
}

public int DeleteSubject(String seq) {
	try {
		
		String sql = String.format(
				"delete from tblSubject where seq = %s", seq);
		
		int result = stat.executeUpdate(sql);
		
		return result;
		
	} catch (Exception e) {
		System.out.println("CourseDAO.DeleteCourse()");
		e.printStackTrace();
	}
	
	
	
	
	return 0;
}

public SubjectDTO getSubject(String seq) {
	
	try {
		
		String sql = "select * from tblSubject where seq = ?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, seq);
		
		rs = pstat.executeQuery();
		
		if (rs.next()) {
			SubjectDTO dto = new SubjectDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setDuration(rs.getString("duration"));
		
			
			return dto;
	
		}
	} catch (Exception e) {
		System.out.println("primaryTalentedStudentDAO.engetTalentedStudent()");
		e.printStackTrace();
	}
	
	
	return null;
}

public int UpdateSubject(SubjectDTO dto2) {
	try {

		String sql = " { call procUpdateSubject( ?, ?, ?) } ";
		
		pstat = conn.prepareCall(sql);
		
		pstat.setString(1, dto2.getSeq());	
		pstat.setString(2, dto2.getName());
		pstat.setString(3, dto2.getDuration());
		
		
		return pstat.executeUpdate();
		
	} catch (Exception e) {
		System.out.println("SubjectDAO.UpdateSubject()");
		e.printStackTrace();
	}
	
	return 0;
}


}
