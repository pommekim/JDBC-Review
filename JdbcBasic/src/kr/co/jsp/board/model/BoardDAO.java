package kr.co.jsp.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO implements IBoardDAO {
	
	//싱글톤 패턴.
	private BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//객체 생성.
	private static BoardDAO dao = new BoardDAO();
	
	//공개된 메서드 작성. (getInstance()는 관례)
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	//------------------------------------------------------
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//커넥션 객체를 제공하는 메서드
	private Connection getConnection() throws Exception {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "jsp";
		String upw = "jsp";
		
		return DriverManager.getConnection(url, uid, upw);
	}
	
	

	@Override
	public boolean insert(Board article) {
		boolean flag = false;
		
		String sql = "INSERT INTO board "
				+ "(board_id, writer, title, content) "
				+ "VALUES(bid_seq.NEXTVAL, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public List<Board> selectAll() {
		List<Board> articles = new ArrayList<>();
		
		String sql = "SELECT * FROM board ORDER BY board_id DESC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board article = new Board(
						rs.getInt("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("create_at")
						);
				articles.add(article);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return articles;
	}

	@Override
	public Board selectOne(int boardId) {
		
		String sql = "SELECT * FROM board WHERE board_id=?";
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		
				
		
		
		
		
		
		return article;
	}

	@Override
	public boolean update(Board article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int boardId) {
		// TODO Auto-generated method stub
		return false;
	}

}
