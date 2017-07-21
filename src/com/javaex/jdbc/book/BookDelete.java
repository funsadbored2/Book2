package com.javaex.jdbc.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int num = 8;

		try { 

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속");
			
			// sql문 준비/바인딩/실행
			String query = "DELETE FROM BOOK" + " WHERE BOOK_ID = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건 처리");

		} catch (ClassNotFoundException e) {
			
			System.out.println("error:드라이브 로딩 실패" + e);
			
		} catch (SQLException e) {
			
			System.out.println("error: " + e);
			
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
	}

}
