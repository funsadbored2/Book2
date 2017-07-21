package com.javaex.jdbc.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String title = "26년";
		String pubs = "재미주의";
		String pub_date = "2012-02-04";
		int id = 5;
		

		try { // JDBC드라이버 로딩 - 오라클로부터

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 커넥션 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			// 3.sql문 준비/바인딩/실행
			String query = "INSERT INTO BOOK VALUES(SEQ_INSERT1.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_date);
			pstmt.setInt(4, id);
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
