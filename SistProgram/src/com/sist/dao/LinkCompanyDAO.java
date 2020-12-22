package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.LinkCompanyDTO;
import com.sist.main.DBUtil;

/**
 * 연계 기업의 모든 기능을 담당하는 메서드
 * @author YSPark
 *
 */
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

			System.out.println("primaryCompanyLanguageDAO.encompanyList()");
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * tblLinkCompany에 기업을 추가하는 메서드입니다
	 * @param dto 테이블에 insert할 LinkCompanyDTO 객체
	 * @return 추가 작업의 성공시 1, 실패시 0 반환
	 */
	public int linkCompanyAdd(LinkCompanyDTO dto) {
		
		try {
			
			String sql = "insert into tblLinkCompany (seq, name, address, department, salary) values (seqLinkCompany.nextVal, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getAddress());
			pstat.setString(3, dto.getDepartment());
			pstat.setString(4, dto.getSalary());
			
			return pstat.executeUpdate();
				
		} catch (Exception e) {

			System.out.println("primaryLinkCompanyDAO.enlinkCompanyAdd()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * tblLinkCompany에서 기업을 삭제하는 메서드 입니다
	 * @param num 삭제할 기업의 seq 번호
	 * @return 삭제 작업의 성공시 1, 실패시 0 반환
	 */
	public int linkCompanyDelete(String num) {
		
		try {
			
			String sql = "delete from tblLinkCompany where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
	
			System.out.println("primaryLinkCompanyDAO.enlinkCompanyDelete()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * seq 번호를 받아 LinkCompanyDTO 객체를 반환하는 메서드 입니다
	 * @param num 객체로 반환할 기업의 seq 번호
	 * @return LinkCompanyDTO
	 */
	public LinkCompanyDTO getLinkCompany(String num) {
		try {
			String sql = "select * from tblLinkCompany where seq = ?";
					
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, num);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				LinkCompanyDTO dto = new LinkCompanyDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setDepartment(rs.getString("department"));
				dto.setSalary(rs.getString("salary"));
				
				return dto;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryLinkCompanyDAO.engetLinkCompany()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 테이블의 내용을 수정 하는 메서드 입니다
	 * @param editdto 변경할 값이 저장되어 있는 객체 입니다
	 * @return 수정 작업의 성공시 1, 실패시 0 반환
	 */
	public int linkCompanyEdit(LinkCompanyDTO editdto) {
		
		try {
			
			String sql = "update tblLinkCompany set name=?, address=?, department=?, salary=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, editdto.getName());
			pstat.setString(2, editdto.getAddress());
			pstat.setString(3, editdto.getDepartment());
			pstat.setString(4, editdto.getSalary());
			pstat.setString(5, editdto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryLinkCompanyDAO.enlinkCompanyEdit()");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
}
