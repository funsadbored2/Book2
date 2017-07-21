package com.javaex.jdbc.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdate {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String name = "토지";
		String pubs = "마로니에북스";
		String pub_days = "2012-08-15";
		int aid = 2;
		int bid = 3;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// connection 얻어오기

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속 성공");

			// sql문 준지 / 바인딩/ 실행

			String query = "UPDATE BOOK SET TITLE = ?, " + "PUBS=?, "
					+ "PUB_DATE = ?, " + "AUTHOR_ID =? "
					+ "WHERE BOOK_ID=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, name);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_days);
			pstmt.setInt(4, aid);
			pstmt.setInt(5, bid);
			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 결과처리
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
			// 5. 자원정리

		}
	}

}
