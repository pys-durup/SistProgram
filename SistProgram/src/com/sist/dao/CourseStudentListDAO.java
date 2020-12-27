package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.dto.CourseStudentListDTO;
import com.sist.main.DBUtil;

import oracle.jdbc.OracleTypes;

public class CourseStudentListDAO {
			
		private Connection conn;
		private Statement stat; // 매개변수x
		private PreparedStatement pstat; //매개변수o
		private ResultSet rs;
		private CallableStatement cstat;
		
	public CourseStudentListDAO() {
			
			try {
				this.conn = DBUtil.open();
				this.stat = conn.createStatement();
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
	
		
		
	}
	
	
	/**
	 * 강의스케줄 내 과목번호 입력시 해당과정 학생리스트 출력
	 * @param pseq 개설과목번호
	 * @return
	 */
	public ArrayList<CourseStudentListDTO> cslist(String pseq){
		
		try {
			
			String sql = "{ call proc_CourseStudentList(?,?) }";	
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, pseq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet) cstat.getObject(2);
			ArrayList<CourseStudentListDTO> list = new ArrayList<CourseStudentListDTO>();
			while (rs.next()) {
				CourseStudentListDTO csdto = new CourseStudentListDTO();
				
				csdto.setSeq(rs.getString("seq"));
				csdto.setName(rs.getString("name"));
				csdto.setJumin(rs.getString("jumin"));
				csdto.setTel(rs.getString("tel"));
				csdto.setRegdate(rs.getString("regdate"));
				csdto.setRegistate(rs.getString("registate"));
				
				
				
				
				list.add(csdto);		
			}
			rs.close();
			return list;
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	
}
	

}	