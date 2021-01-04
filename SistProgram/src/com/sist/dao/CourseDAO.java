package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.CourseDTO;
import com.sist.main.DBUtil;


public class CourseDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public CourseDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
}

public ArrayList<CourseDTO> list(String word) {

	
	try {
		String where = "";
		
		// 검색어가 있으면 where절 생성
		if (word != null) {
			where = String.format("where name like '%%%s%%'", word);
		}
		
		String sql = String.format("select *  from tblcourse %s order by seq", where);
		
		rs = stat.executeQuery(sql);
		
		ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
		
		while (rs.next()) {
			// 레코드 1개 -> LinkCompanyDTO 1개
			CourseDTO dto = new CourseDTO();
			
			dto.setSeq(rs.getString("seq"));
			dto.setName(rs.getString("name"));
			dto.setPurpose(rs.getString("purpose"));
							
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

public int addCourse(String name, String purpose) {
	try {
		
		String sql = String.format(
				"Insert into tblCourse(seq, name, purpose) values (seqCourse.nextval, '%s', '%s')", name, purpose);
		
		int result = stat.executeUpdate(sql);
		return result;
		
	} catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
	}
	
	
	return 0;
}

public int UpdateCourse(String seq, String name, String purpose) {
	try {
		String sql = String.format(
				"update tblCourse set name = '%s', purpose = '%s' where seq = %s",
				name, purpose, seq);
		int result = stat.executeUpdate(sql);
		return result;
		

	} catch (Exception e) {
		System.out.println("CourseDAO.UpdateCourse()");
		e.printStackTrace();		
	}
	return 0;
}

public int DeleteCourse(String seq) {
	try {
		String sql = String.format(
				"delete from tblCourse where seq = %s", seq);
		
		int result = stat.executeUpdate(sql);
		
		return result;
		
	} catch (Exception e) {
		System.out.println("CourseDAO.DeleteCourse()");
		e.printStackTrace();
	}
	
	
	return 0;
}

}