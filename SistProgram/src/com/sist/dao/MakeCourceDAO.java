package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.MakeCourceDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class MakeCourceDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public MakeCourceDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

	
	
}

public ArrayList<MakeCourceDTO> list() {
    try {

	String sql = "select * from vwMakeCourse";
	
	rs = stat.executeQuery(sql);
	
	ArrayList<MakeCourceDTO> list = new ArrayList<MakeCourceDTO>();
	
	while(rs.next()) {
	    MakeCourceDTO dto = new MakeCourceDTO();
	    
	    dto.setRownum(rs.getString("rownum"));
	    dto.setSeq(rs.getString("seq"));
	    dto.setName(rs.getString("name"));
	    dto.setStartdate(rs.getString("startdate"));
	    dto.setEnddate(rs.getString("enddate"));
	    dto.setRoomnum(rs.getString("roomnum"));
	    
	  
	    list.add(dto);
	    
	}
	
	rs.close();
	return list;
	
    } catch (Exception e) {
	System.out.println("MakeCourceDAO.list()");
	e.printStackTrace();
    }
    
    
    return null;
}

public ArrayList<MakeCourceDTO> list2(String seq) {
    
    try {

	String sql = " { call procSearchSubjectBook(?, ?) }";
	
	cstat = conn.prepareCall(sql);
	cstat.registerOutParameter(1, OracleTypes.CURSOR);
	cstat.setString(2, seq);
	
	cstat.executeQuery();
	
	rs = (ResultSet) cstat.getObject(1);
	
	ArrayList<MakeCourceDTO> list = new ArrayList<MakeCourceDTO>();
	
	while(rs.next()) {
	    MakeCourceDTO dto = new MakeCourceDTO();
	    
	    dto.setRownum(rs.getString("번호"));
	    dto.setSeq(rs.getString("과정번호"));
	    dto.setRoomnum(rs.getString("교실명"));
	    dto.setSname(rs.getString("과목명"));
	    dto.setBname(rs.getString("교과서명"));
	    dto.setDuration(rs.getString("기간"));
	    
	    
	    list.add(dto);
	}
	
	rs.close();
	return list;
	
    } catch (Exception e) {
	System.out.println("MakeCourceDAO.list2()");
	e.printStackTrace();
    }
    return null;
}

public int addmakeCourse(MakeCourceDTO cdto) {
    try {

	String sql = " { call procMakeCourse( ?, ?, ?, ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, cdto.getPstartdate());
	cstat.setString(2, cdto.getPenddate());
	cstat.setString(3, cdto.getProomnum());
	cstat.setString(4, cdto.getPteachernum());
	cstat.setString(5, cdto.getPcourcenum());
	
	return cstat.executeUpdate();
	
    } catch (Exception e) {
	System.out.println("MakeCourceDAO.addmakeCourse()");
	e.printStackTrace();
    }
    return 0;
}

public int ediMakeCourse(MakeCourceDTO ddto) {
    try {

	String sql = " { call procUpdateMakeCourse( ?, ?, ?, ?, ?, ? ) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, ddto.getPseq());
	cstat.setString(2, ddto.getPstartdate());
	cstat.setString(3, ddto.getPenddate());
	cstat.setString(4, ddto.getProomnum());
	cstat.setString(5, ddto.getPteachernum());
	cstat.setString(6, ddto.getPcourcenum());
	
	return cstat.executeUpdate();
	
    } catch (Exception e) {
	System.out.println("MakeCourceDAO.ediMakeCourse()");
	e.printStackTrace();
    }
    
   
    return 0;
}

public int DeleteMakeCourse(MakeCourceDTO fdto) {
    try {

	String sql = " { call procDeleteMakeCource (?) }";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, fdto.getPseq());
		
	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("MakeCourceDAO.DeleteMakeCourse()");
	e.printStackTrace();
    }
    
    return 0;
}

}
