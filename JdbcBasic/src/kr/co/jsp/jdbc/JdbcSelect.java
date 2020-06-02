package kr.co.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcSelect {
	
	public static void main(String[] args) {
		
		String sql = "SELECT * FROM members";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "jsp";
		String upw = "jsp";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //SELECT문에서만 사용하는 객체.
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, upw);
			
			pstmt = conn.prepareStatement(sql);
			
			// ResultSet의 객체를 pstmt의 executeQuery()를 통해 받아옵니다.
			rs = pstmt.executeQuery();
			
			/*
			 - SELECT 쿼리문의 실행 결과, 조회할 데이터가 한 줄이라도 존재한다면
			 rs객체의 next()메서드는 true를 리턴하고 해당 행을 지목합니다.
			 - 한 행씩 true인지 물어봐야 하기 때문에 while문으로 돌려줌!!!
			 */
			while(rs.next()) {
				/*
				 - SELECT의 실행 결과의 컬럼값을 읽어오려면
				 rs의 getString(), getInt(), getDouble()...등의 메서드를 사용합니다.
				 - 해당 메서드의 매개값으로 읽어올 테이블의 컬럼명을 지정합니다.
				 */
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
				rs.close(); pstmt.close(); conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
