package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.MakeSubjectDTO;
import com.sist.main.DBUtil;

public class MakeSubjectDAO {
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public MakeSubjectDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
}

public ArrayList<MakeSubjectDTO> MakeSubjectList() {
    try {

	String sql = "select * from vwMakeSubject";
	
	rs = stat.executeQuery(sql);
	
	ArrayList<MakeSubjectDTO> list= new ArrayList<MakeSubjectDTO>();
	
	while(rs.next()) {
	    MakeSubjectDTO dto = new MakeSubjectDTO();
	    
	    dto.setSeq(rs.getString("번호"));
	    dto.setSubjectname(rs.getString("과목명"));
	    dto.setDuration(rs.getString("일수"));
	    dto.setStatus(rs.getString("과목상태"));
	    dto.setTname(rs.getString("교사명"));
	    dto.setBookNum(rs.getString("책제목"));
	    dto.setBookdistristate(rs.getString("분배상태"));
	    
	    list.add(dto);
	}
	
	return list;
	
    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.MakeSubjectList()");
	e.printStackTrace();
    }
    
    return null;
}

public int AddMakeSubject(MakeSubjectDTO dto) {
    
    try {

	String sql = " { call procMakeSubject( ?, ?, ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPsubjectnum());
	cstat.setString(2, dto.getPstartdate());
	cstat.setString(3, dto.getPenddate());
	cstat.setString(4, dto.getPbooknum());
	
	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.AddMakeSubject()");
	e.printStackTrace();
    }
    return 0;
}

public int EditMakeSubject(MakeSubjectDTO dto) {
    
    try {

String sql = " { call procUdateMakeSubject( ?, ?, ?, ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPseq());
	cstat.setString(2, dto.getPsubjectnum());
	cstat.setString(3, dto.getPstartdate());
	cstat.setString(4, dto.getPenddate());
	cstat.setString(5, dto.getPbooknum());
	
	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.EditMakeSubject()");
	e.printStackTrace();
    }
    return 0;
}

public int DeleteMakeSubject(MakeSubjectDTO dto) {
    
    try {

	String sql = " { call procDeleteMakeSubject(?) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPseq());

	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.DeleteMakeSubject()");
	e.printStackTrace();
    }
    
    return 0;
}

public int ConnectMakeSubject(MakeSubjectDTO dto) {
    
    try {

	String sql = " { call procSelectCourse( ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPseq());
	cstat.setString(2, dto.getPseq2());
	
	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.ConnectMakeSubject()");
	e.printStackTrace();
    }
    return 0;
}

public int DeleteConnectMakeSubject(MakeSubjectDTO dto) {
    try {

	String sql = " { call ProcDeleteSubjectConnected( ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPseq());
	
	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.DeleteConnectMakeSubject()");
	e.printStackTrace();
    }
    return 0;
}

public int distriStateBook(MakeSubjectDTO dto) {
    
    try {

	String sql = " { call procBookDistristate( ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto.getPseq());
	cstat.setString(2, dto.getPstate());
	
	return cstat.executeUpdate();
	
    } catch (Exception e) {
	System.out.println("MakeSubjectDAO.distriStateBook()");
	e.printStackTrace();
    }
    return 0;
}
}
