package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sist.dto.AbleTStudentScoreListDTO;
import com.sist.dto.RecommendListDTO;
import com.sist.dto.TalentedStudentListDTO;
import com.sist.main.DBUtil;

/**
 * 추천 인재 관리 탭의 모든 기능을 담당하는 메서드
 * @author YSPark
 *
 */
public class TalentedStudentDAO {
	
	private static Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	private CallableStatement cstat = null;
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
				where = String.format("where seq = %s ", word);
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
			System.out.println("primaryTalentedStudentDAO.enableTStudentScoreList()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 추천 인재를 추가하는 메서드 입니다
	 * @param reginum 등록할 인재의 수강번호
	 * @param portfolio 포트폴리오 주소
	 * @param reason 인재 추가 이유
	 * @return
	 */
	public int talentedStudenAdd(String reginum, String portfolio, String reason) {
		
		try {
			
			String sql = "{ call procAddTalentedStudent(?, ?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, reginum);
			cstat.setString(2, portfolio);
			cstat.setString(3, reason);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryTalentedStudentDAO.entalentedStudenAdd()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 추천 인재 테이블에서 인재를 삭제하는 메서드 입니다
	 * @param reginum
	 * @return
	 */
	public int talentedStudenDelete(String reginum) {
		
		try {
			
			String sql = "{ call procDeleteTalentedStudent(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, reginum);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("primaryTalentedStudentDAO.entalentedStudenDelete()");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * TalentedStudentListDTO 객체를 반환하는 메서드 입니다
	 * @param seq
	 * @return
	 */
	public TalentedStudentListDTO getTalentedStudent(String seq) {
		
		try {
			
			String sql = "select * from vwtalentedStudentList where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				TalentedStudentListDTO dto = new TalentedStudentListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setPortfolio(rs.getString("portfolio"));
				dto.setReason(rs.getString("reason"));
				
				return dto;
		
			}
		} catch (Exception e) {
			System.out.println("primaryTalentedStudentDAO.engetTalentedStudent()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	/**
	 * 인재 목록을 수정하는 메서드 입니다
	 * @param dto2 수정한 값이 담겨있는 객체
	 * @return 수정의 성공여부 반환 1 성공 0 실패
	 */
	public int talentedStudenEdit(TalentedStudentListDTO dto2) {
		
		try {
			
			String sql = " { call procEditTalentedStudent( ?, ?, ?) } ";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, dto2.getSeq());
			cstat.setString(2, dto2.getPortfolio());
			cstat.setString(3, dto2.getReason());
			
			return cstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("primaryTalentedStudentDAO.entalentedStudenEdit()");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	
}
