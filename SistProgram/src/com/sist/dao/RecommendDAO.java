package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.RecommendListDTO;
import com.sist.dto.TalentedStudentListDTO;
import com.sist.main.DBUtil;

/**
 * 기업에 인재 추천 탭의 모든 기능을 담당하는 메서드
 * @author YSPark
 *
 */
public class RecommendDAO {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
	private ResultSet rs = null;
	
	public RecommendDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryRecommendDAO.enRecommendDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 기업에 인재 추천 목록을 반환하는 메서드
	 * @return
	 */
	public ArrayList<RecommendListDTO> recommendStudentList() {
		
		try {
			
			
			String sql = "select * from vwRecommendStudentList";
			ArrayList<RecommendListDTO> list = new ArrayList<RecommendListDTO>();
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				RecommendListDTO dto = new RecommendListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setStudentName(rs.getString("studentname"));
				dto.setCompanyName(rs.getString("companyname"));
				dto.setRecoDate(rs.getString("recodate"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryRecommendDAO.enrecommendStudentList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 추천 목록에 등록하는 메서드
	 * @param studentdto
	 * @param companydto
	 * @return
	 */
	public int recommendStudentADD(TalentedStudentListDTO studentdto, LinkCompanyDTO companydto) {
		
		try {
			
			// 학procAddRecommend(학생번호, 연계기업번호)
			String sql = " { call procAddRecommend(?, ?) } ";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentdto.getSeq());
			cstat.setString(2, companydto.getSeq());
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("primaryRecommendDAO.enrecommendStudentADD()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 추천 목록에서 삭제하는 메서드
	 * @param num
	 * @return
	 */
	public int recommendStudentDelete(String seq) {
		
		try {
			String sql = "{ call procDeleteRecommend(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, seq);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("primaryRecommendDAO.enrecommendStudentDelete()");
			e.printStackTrace();
		}

		
		return 0;
	}
	
	
}
