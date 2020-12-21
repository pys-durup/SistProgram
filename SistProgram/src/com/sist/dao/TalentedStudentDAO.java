package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AbleTStudentScoreListDTO;
import com.sist.dto.TalentedStudentListDTO;
import com.sist.main.DBUtil;

public class TalentedStudentDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	
	public TalentedStudentDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryTalentedStudentDAO.enTalentedStudentDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 추천 인재 목록을 반환하는 메서드 입니다
	 * @param word 검색어
	 * @return
	 */
	public ArrayList<TalentedStudentListDTO> talentedStudenList(String word) {
		
		try {
			String where = "";
			
			// 검색어가 있으면 where절 생성
			if (word != null) {
				where = String.format("where seq like '%%%s%%'", word);
			}
			
			String sql = String.format("select * from vwtalentedStudentList %s", where);
			
			ArrayList<TalentedStudentListDTO> list = new ArrayList<TalentedStudentListDTO>();
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				TalentedStudentListDTO dto = new TalentedStudentListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setPortfolio(rs.getString("portfolio"));
				dto.setReason(rs.getString("reason"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryTalentedStudentDAO.entalentedStudenList()");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 수료한 학생중 추천인재가 될 수 있는 목록을 반환하는 메서드 입니다
	 * @return
	 */
	public ArrayList<AbleTStudentScoreListDTO> ableTStudentScoreList() {
		
		try {
			
			String sql = "select * from vwAbleTStudentScoreList";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<AbleTStudentScoreListDTO> list = new ArrayList<AbleTStudentScoreListDTO>();
			
			while (rs.next()) {
				AbleTStudentScoreListDTO dto = new AbleTStudentScoreListDTO();
				
				dto.setReginum(rs.getString("reginum"));
				dto.setName(rs.getString("name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPractice(rs.getString("practice"));
				dto.setAttendance(rs.getString("attendance"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryTalentedStudentDAO.enableTStudentScoreList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
}
