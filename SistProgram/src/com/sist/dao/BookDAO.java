package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.BookDTO;
import com.sist.main.DBUtil;

public class BookDAO {
	
	private Connection conn;
	private Statement stat; // 매개변수x
	private PreparedStatement pstat; //매개변수o
	private ResultSet rs;
	private CallableStatement cstat;
	
public BookDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

}

public ArrayList<BookDTO> list(String word) {
    try {
	
	String where = "";
	
	// 검색어가 있으면 where절 생성
	if (word != null) {
		where = String.format("where name like '%%%s%%'", word);
	}
	String sql = String.format("select *  from tblBook %s order by seq", where);
	
	rs = stat.executeQuery(sql);
		
	ArrayList<BookDTO> list = new ArrayList<BookDTO>();
	
	while (rs.next()) {
	
		BookDTO dto = new BookDTO();
		
		dto.setSeq(rs.getString("seq"));
		dto.setName(rs.getString("name"));
		dto.setWriter(rs.getString("writer"));
		dto.setPublisher(rs.getString("publisher"));
		dto.setPrice(rs.getString("price"));
		dto.setCount(rs.getString("count"));
		
		list.add(dto);
	}
	
	
	return list;
	

    } catch (Exception e) {
	System.out.println("BookDAO.list()");
	e.printStackTrace();
    }
    
    
    return null;
}

public int addBook(String name, String writer, String publisher, String price, String count) {
    try {

	String sql = String.format(
		"Insert into tblBook(seq, name, writer, publisher, price, count) values "
		+ "(seqBook.nextval, '%s', '%s', '%s', '%s', %s)", name, writer, publisher, price, count);

	int result = stat.executeUpdate(sql);
	
	
	
	return result;

    } catch (Exception e) {
	System.out.println("BookDAO.addBook()");
	e.printStackTrace();
    }
    
    return 0;
}

public BookDTO getBook(String seq) {
    try {

	String sql = "select * from tblBook where seq = ?";
	
	pstat = conn.prepareStatement(sql);
	pstat.setString(1, seq);
	
	rs = pstat.executeQuery();
	
	if (rs.next()) {
		BookDTO dto = new BookDTO();
		
		dto.setSeq(rs.getString("seq"));
		dto.setName(rs.getString("name"));
		dto.setWriter(rs.getString("writer"));
		dto.setPublisher(rs.getString("publisher"));
		dto.setPrice(rs.getString("price"));
		dto.setCount(rs.getString("count"));
		
		
		return dto;
	}
    } catch (Exception e) {
	System.out.println("BookDAO.getBook()");
	e.printStackTrace();
    } 
    
    return null;
}

public int UpdateBook(BookDTO dto2) {
    try {

	String sql = " { call procUpdateBook( ?, ?, ?, ?, ?, ?) } ";
	
	cstat = conn.prepareCall(sql);
	
	cstat.setString(1, dto2.getPseq());	
	cstat.setString(2, dto2.getPname());
	cstat.setString(3, dto2.getPwriter());
	cstat.setString(4, dto2.getPpublisher());
	cstat.setString(5, dto2.getPprice());
	cstat.setString(6, dto2.getPcount());
	
	

	return cstat.executeUpdate();

    } catch (Exception e) {
	System.out.println("BookDAO.UpdateBook()");
	e.printStackTrace();
    }
    
    return 0;
}

public int DeleteBook(String seq) {
   
    try {
	
	String sql = String.format(
		"delete from tblBook where seq = %s", seq);

	int result = stat.executeUpdate(sql);
	
	
	
	return result;
	

    } catch (Exception e) {
	System.out.println("BookDAO.DeleteBook()");
	e.printStackTrace();
    }
    
    return 0;
}
}