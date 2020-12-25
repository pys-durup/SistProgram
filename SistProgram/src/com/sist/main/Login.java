package com.sist.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.sist.controller.AdminController;
import com.sist.controller.ReserveStudentController;
import com.sist.controller.StudentController;
import com.sist.dao.ReserveStudentDAO;
import com.sist.dao.StudentDAO;
import com.sist.controller.TeacherController;
import com.sist.dao.InterviewResultDAO;
import com.sist.dao.MasterDAO;
import com.sist.dao.TeacherDAO;


public class Login {
	
	private static Scanner scan;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;
	
	static {
		scan = new Scanner(System.in);
	}
	
	public Login() {
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			System.out.println("primaryLogin.enLogin()");
			e.printStackTrace();
		}
	}

	public void loginReserveStudent() {
		// 예비 교육생 로그인 진행

				String num = "";
				String id = "";
				String pw = "";
				String seq = "";
				boolean check = true;
				
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("예비교육생 로그인 페이지");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("1. 로그인\t2. 메인화면으로");
				System.out.print("번호를 입력하세요 :");
				num = scan.nextLine();
				
				if(num.equals("1")) {
					System.out.print("핸드폰번호를 입력하세요(-제외):");
					id = scan.nextLine();
					System.out.print("비밀번호를 입력하세요(주민번호 뒷자리):");
					pw = scan.nextLine();
					
					
					try {
						// 예비 로그인 진행... 
						String sql = "select * from tblReserveStudent";
						rs = stat.executeQuery(sql);
						
						while (check) {
							while (rs.next()) {
								//다시 실행~
								//System.out.println("tel: " + rs.getString("tel"));
								
								
								if (rs.getString("tel") != null &&  rs.getString("jumin") != null) {
								
								String oracleid = rs.getString("tel").replace("-", "");
								String oraclepw = rs.getString("jumin").substring(7);
								
								
								
								if (oracleid.equals(id) && oraclepw.equals(pw)) {
									// 로그인 성공시 while문을 빠져나간다
									seq = rs.getString("seq");
									check = false;
									break;
								} else {
									//테이블에 일치하는 아이디와 비밀번호가 없음
									check = true;
								}
								
								} else {
									check = true;
								} // null 값 체크하기
								
							}  //while
							
							if (check == false) {
								// 로그인 성공
								ReserveStudentDAO rsdao = new ReserveStudentDAO();
								ReserveStudentController ReserveStudent = new ReserveStudentController(rsdao.getReserveStudent(seq),rsdao.getInterviewResult(seq));
								ReserveStudent.start();
								break;
							} else {
								// 로그인 실패
								System.out.println("아이디와 비밀번호를 확인해주세요");
								loginReserveStudent();
								break;
							}
							
						}
					
						
					} catch (Exception e) {
						
						System.out.println("primaryLogin.enloginReserveStudent()");
						e.printStackTrace();
					}
					
				} else {
					// 로그인 실패
					System.out.println("메인으로 이동합니다.");
				}
		
	}

	public void loginStudent() {
		// 교육생 로그인 진행
		String num = "";
		String id = "";
		String pw = "";
		String seq = "";
		boolean check = true;
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교육생 로그인 페이지");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 로그인\t2. 메인화면으로");
		System.out.print("번호를 입력하세요 :");
		num = scan.nextLine();
		
		if(num.equals("1")) {
			System.out.print("핸드폰번호를 입력하세요(-제외):");
			id = scan.nextLine();
			System.out.print("비밀번호를 입력하세요(주민번호 뒷자리):");
			pw = scan.nextLine();
			
			
			try {
				// 교육생 로그인 진행... 
				String sql = "select * from tblStudent";
				rs = stat.executeQuery(sql);
				
				while (check) {
					while (rs.next()) {
						//다시 실행~
						//System.out.println("tel: " + rs.getString("tel"));
						
						
						if (rs.getString("tel") != null &&  rs.getString("jumin") != null) {
						
						String oracleid = rs.getString("tel").replace("-", "");
						String oraclepw = rs.getString("jumin").substring(7);
						
						
						
						if (oracleid.equals(id) && oraclepw.equals(pw)) {
							// 로그인 성공시 while문을 빠져나간다
							seq = rs.getString("seq");
							check = false;
							break;
						} else {
							// 테이블에 일치하는 아이디와 비밀번호가 없음
							check = true;
						}
						
						} else {
							check = true;
						} // null 값 체크하기
						
					}  //while
					
					if (check == false) {
						// 로그인 성공
						StudentDAO sdao = new StudentDAO();
						StudentController Student = new StudentController(sdao.getStudent(seq), sdao.getStudentsRegiCource(seq));
						Student.start();
						break;
					} else {
						// 로그인 실패
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginStudent();
						break;
					}
					
				}
			
				
			} catch (Exception e) {
				
				System.out.println("primaryLogin.enloginStudent()");
				e.printStackTrace();
			}
			
		} else {
			// 로그인 실패
			System.out.println("메인으로 이동합니다.");
		}

		
		
		
	}

	public void loginTeacher() {
		// 강사 로그인 진행
		String num = "";
		String id = "";
		String pw = "";
		String seq = "";
		boolean check = true;
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교사 로그인 페이지");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 로그인\t2. 메인화면으로");
		System.out.print("번호를 입력하세요 :");
		num = scan.nextLine();
		
		if(num.equals("1")) {
			System.out.print("핸드폰번호를 입력하세요(-제외):");
			id = scan.nextLine();
			System.out.print("비밀번호를 입력하세요(주민번호 뒷자리):");
			pw = scan.nextLine();
			
			
			try {
				// 교사 로그인 진행... 
				String sql = "select * from tblTeacher";
				rs = stat.executeQuery(sql);
				
				while (check) {
					while (rs.next()) {
						
						String oracleid = rs.getString("tel").replace("-", "");
						String oraclepw = rs.getString("jumin").substring(7);
						
						if (oracleid.equals(id) && oraclepw.equals(pw)) {
							// 로그인 성공시 while문을 빠져나간다
							seq = rs.getString("seq");
							check = false;
							break;
						} else {
							// tblMaster 테이블에 일치하는 아이디와 비밀번호가 없음
							check = true;
						}
					}
					
					if (check == false) {
						// 로그인 성공
						TeacherDAO tdao = new TeacherDAO();
						TeacherController teacher = new TeacherController(tdao.getTeacher(seq));
						teacher.start();
					} else {
						// 로그인 실패
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginAdmin();
						break;
					}
					
				}
			
				
			} catch (Exception e) {
				System.out.println("primaryLogin.enloginReserveStudent()");
				e.printStackTrace();
			}
			
		} else {
			// 로그인 실패
			System.out.println("메인으로 이동합니다.");
		}
		
	}

	public void loginAdmin() {
		// 관리자 로그인 진행
		String num = "";
		String id = "";
		String pw = "";
		String seq = "";
		boolean check = true;
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("관리자 로그인 페이지");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 로그인\t2. 메인화면으로");
		System.out.print("번호를 입력하세요 :");
		num = scan.nextLine();
		
		if(num.equals("1")) {
			System.out.print("아이디를 입력하세요:");
			id = scan.nextLine();
			System.out.print("비밀번호를 입력하세요:");
			pw = scan.nextLine();
			
			
			try {
				// 관리자 로그인 진행... 
				String sql = "select * from tblMaster";
				rs = stat.executeQuery(sql);
				
				while (check) {
					while (rs.next()) {
						if (rs.getString("id").equals(id) && rs.getString("jumin").equals(pw)) {
							// 로그인 성공시 while문을 빠져나간다
							seq = rs.getString("seq");
							check = false;
							break;
						} else {
							// tblMaster 테이블에 일치하는 아이디와 비밀번호가 없음
							check = true;
						}
					}
					
					if (check == false) {
						// 로그인 성공
						// 로그인 한 관리자 계정의 객체를 가져오기 위해서 DAO 객체 생성
						MasterDAO mdao = new MasterDAO();
						// mado.getMaster(seq) 로그인한 계정의 seq를 매개변수로 넘겨 관리자 객체를 리턴받는다
						// 가져온 masterDTO객체를 AdminController객체의 생성자 매개변수로 넘겨준다
						// 로그인한 관리자의 객체정보를 가진 상태로 프로그램 흐름 진행
						AdminController admin = new AdminController(mdao.getMaster(seq));
						admin.start();
					} else {
						// 로그인 실패
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginAdmin();
						break;
					}
					
				}
			
				
			} catch (Exception e) {
				System.out.println("primaryLogin.enloginReserveStudent()");
				e.printStackTrace();
			}
			
		} else {
			// 로그인 실패
			System.out.println("메인으로 이동합니다.");
		}
	}
}
