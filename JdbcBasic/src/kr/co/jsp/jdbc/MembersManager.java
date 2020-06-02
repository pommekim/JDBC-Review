package kr.co.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MembersManager {

	private static Scanner sc = new Scanner(System.in);
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("\n### 회원 관리 프로그램 ###");
			System.out.println("# 1. 회원 정보 등록하기");
			System.out.println("# 2. 전체 회원 정보 조회하기");
			System.out.println("# 3. 개별 회원 정보 조회하기");
			System.out.println("# 4. 회원 정보 수정하기");
			System.out.println("# 5. 회원 정보 삭제하기");
			System.out.println("# 6. 프로그램 종료");
			
			System.out.print("# 메뉴를 입력하세요: ");
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				insert();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectOne();
				break;
			case 4:
				update();
				break;
			case 5:
				delete();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				System.exit(0);
				
			default:
				System.out.println("메뉴를 잘못 입력하셨습닏.");
				
			} //end switch
			
		} //end while true

	} //end main
	
	//Connection 객체를 제공하는 메서드
	private static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "jsp";
		String upw = "jsp";
		String driverName = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driverName);
		
		return DriverManager.getConnection(url, uid, upw);
		
	}
	
	//1. 회원 정보를 INSERT하는 메서드
	private static void insert() {
		
		System.out.println("# 회원 정보를 입력하세요.");
		System.out.print("# 아이디: ");
		String id = sc.next();
		
		System.out.print("# 비밀번호: ");
		String pw = sc.next();
		
		System.out.print("# 이름: ");
		String name = sc.next();
		
		System.out.print("# 이메일: ");
		String email = sc.next();
		
		String sql = "INSERT INTO members VALUES (?, ?, ?, ?)";
		
		//호출하는 곳에서 try catch문으로 감싸줌!!!
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 1) System.out.println("회원 정보 입력 성공!");
			else System.out.println("회원 정보 입력 실패!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close(); conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//2. 전체 회원 정보를 SELECT 하는 메서드
	private static void selectAll() {
		String sql = "select * from members order by name asc";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("========== 전체 회원 정보 ==========");
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				System.out.printf("# 아이디: %s\n# 비밀번호: %s\n# 이름: %s\n# 이메일: %s\n"
						, id, pw, name, email);
				System.out.println("===============================================");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close(); rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//3. 개별 회원 정보를 SELECT 하는 메서드
	private static void selectOne() {
		System.out.println("조회할 회원 아이디를 입력하세요.");
		System.out.print("# 아이디: ");
		String insertId = sc.next();
		
		String sql = "select * from members where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("========== 회원 정보 ==========");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				System.out.printf("# 아이디: %s\n# 비밀번호: %s\n# 이름: %s\n# 이메일: %s\n"
						, id, pw, name, email);
				System.out.println("===============================================");
			} else {
				System.out.println("해당 회원 정보는 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close(); rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//4. 개인 회원 정보를 수정하는 메서드
	private static void update() {
		/*
		 - 회원 정보를 수정할 ID를 먼저 입력받으세요.
		 - 수정할 정보는 이름과 이메일입니다. 수정할 이름과 이메일을 입력받아서
		 해당 ID의 이름과 이메일을 수정해 주세요.
		 UPDATE members SET name=?, email=? WHERE id=?
		 */
		
		System.out.println("수정할 회원 아이디를 입력하세요.");
		System.out.print("# 아이디: ");
		String insertId = sc.next();
		System.out.print("# 수정할 이름: ");
		String insertName = sc.next();
		System.out.print("# 수정할 이메일: ");
		String insertEmail = sc.next();
		
		String sql = "UPDATE members SET name=?, email=? WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertName);
			pstmt.setString(2, insertEmail);
			pstmt.setString(3, insertId);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("DB에 회원정보 수정 성공!");
			} else {
				System.out.println("DB에 회원정보 수정 실패!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	//5. 회원 정보를 삭제하는 메서드
	private static void delete() {
		//ID를 입력받아서 해당 ID의 모든 회원 정보를 삭제.
		
		System.out.println("삭제할 회원 아이디를 입력하세요.");
		System.out.print("# 아이디: ");
		String insertId = sc.next();
		
		String sql = "delete from members where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertId);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("회원 ID: " + insertId + "가 정상 삭제되었습니다.");
			} else {
				System.out.println("DB에 회원정보 삭제 실패!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
}
