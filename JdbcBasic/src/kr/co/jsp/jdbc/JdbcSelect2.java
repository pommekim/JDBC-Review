package kr.co.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcSelect2 {
	
	public static void main(String[] args) {
		
		/*
		 - 회원의 ID를 입력받아 해당 ID의 회원 정보를 모두 출력하는
		 JDBC 프로그래밍 코들르 작성하세요.
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.println("조회하실 ID를 입력하세요: ");
		String userId = sc.next();
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "jsp";
		String upw = "jsp";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM members WHERE id=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("---------- 회원 정보 ----------");
				System.out.println("아이디: " + rs.getString("id"));
				System.out.println("비밀번호: " + rs.getString("pw"));
				System.out.println("이름: " + rs.getString("name"));
				System.out.println("이메일: " + rs.getString("email"));
			} else {
				System.out.println("해당 ID에 대한 회원 정보가 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close(); pstmt.close(); conn.close(); sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
	}

}
