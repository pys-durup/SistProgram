package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.RoomDTO;
import com.sist.dto.SubjectDTO;
import com.sist.main.DBUtil;

public class RoomDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 강의실 DAO의 기본 생성자이다.
	 */
	public RoomDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("boomDAO.boomDAO() : " + e.toString());
		}//try 
	
	}//boomDAO()

	public ArrayList<RoomDTO> list() {
	    
	    
	    try {

		String sql = "select * from vwRoom";
		
		rs = stat.executeQuery(sql);
		
		ArrayList<RoomDTO> list = new ArrayList<RoomDTO>();
		
		while (rs.next()) {
		    RoomDTO dto = new RoomDTO();
		    
		    dto.setSeq(rs.getString("seq"));
		    dto.setRoomnum(rs.getString("roomnum"));
		    dto.setCapacity(rs.getString("capacity"));
		    dto.setName(rs.getString("name"));
	
		    list.add(dto);
		}

		return list;
		
	    } catch (Exception e) {
		System.out.println("RoomDAO.list()");
		e.printStackTrace();
	    }
	    
	    return null;
	}

	public RoomDTO getRoom(String seq) {
	    
	    try {

		String sql = "select * from vwRoom where seq =?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, seq);
		
		rs = pstat.executeQuery();
		
		if(rs.next()) {
		    RoomDTO dto = new RoomDTO();
		    
		    dto.setSeq(rs.getString("seq"));
		    dto.setRoomnum(rs.getString("roomnum"));
		    dto.setCapacity(rs.getString("capacity"));
		    dto.setName(rs.getString("name"));
		    
		 
		    return dto;
		    
		}

	    } catch (Exception e) {
		System.out.println("RoomDAO.getRoom()");
		e.printStackTrace();
	    }
	    
	    return null;
	}

	public int UpdateRoom(RoomDTO dto2) { //방이름 변경
	   

		try {
		    	String sql = "{ call procUpdateRoomNum (?, ?) }";
		    	pstat = conn.prepareStatement(sql);
		    	
		    	pstat.setString(1, dto2.getSeq());
		    	pstat.setString(2, dto2.getRoomnum());
		    	
		    	
		    	return pstat.executeUpdate();
		    	
	    } catch (Exception e) {
		System.out.println("RoomDAO.UpdateRoom()");
		e.printStackTrace();
	    }
	    return 0;
	    }

	public int UpdateCapacity(RoomDTO dto2) {
		try {
		    	String sql = "{ call procUpdateCapacity (?, ?) }";
		    	pstat = conn.prepareStatement(sql);
		    	
		    	pstat.setString(1, dto2.getSeq());
		    	pstat.setString(2, dto2.getCapacity());
		    	
		    
		    	return pstat.executeUpdate();
		    	
	    } catch (Exception e) {
		System.out.println("RoomDAO.UpdateRoom()");
		e.printStackTrace();
	    }
		
	    return 0;
	    
	}
}