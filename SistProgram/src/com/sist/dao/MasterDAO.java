package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.dto.MasterDTO;
import com.sist.main.DBUtil;

public class MasterDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	
	public MasterDAO() {
		try {
			this.conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryMasterDAO.enMasterDAO()");
			e.printStackTrace();
		}
	}
	
	/**
	 * 관리자 계정의 객체를 반환하는 메서드 입니다
	 * @param seq 관리자의 seq 데이터 입니다
	 * @return 관리자 계정의 객체를 반환합니다
	 */
	public MasterDTO getMaster(String seq) {
		
		try {
			
			String sql = "select * from tblMaster where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
	
				MasterDTO dto = new MasterDTO();
		
				dto.setSeq(rs.getString("seq"));
				dto.setId(rs.getString("id"));
				dto.setJumin(rs.getString("jumin"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("primaryMasterDAO.engetMaster()");
			e.printStackTrace();
		}
		
		return null;
	}

}
