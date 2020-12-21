package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.LinkCompanyDTO;
import com.sist.main.DBUtil;

public class LinkCompanyDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	
	public LinkCompanyDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryLinkCompanyDAO.enLinkCompanyDAO()");
			e.printStackTrace();
		}
	}
	
	public ArrayList<LinkCompanyDTO> linkCompanyList(String word) {
		
		try {
			String where = "";
			
			// 검색어가 있으면 where절 생성
			if (word != null) {
				where = String.format("where name like '%%%s%%'", word);
			}
			
			String sql = String.format("select *  from tblLinkCompany %s order by seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<LinkCompanyDTO> list = new ArrayList<LinkCompanyDTO>();
			
			while (rs.next()) {
				// 레코드 1개 -> LinkCompanyDTO 1개
				LinkCompanyDTO dto = new LinkCompanyDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setDepartment(rs.getString("department"));
				dto.setSalary(rs.getString("salary"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryCompanyLanguageDAO.encompanyList()");
			e.printStackTrace();
		}
		
		return null;
		
	}
}
