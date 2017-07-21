package com.javaex.jdbc.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		// 0. import java.sql.*;

		Connection conn = null;

		PreparedStatement pstmt = null;

		int num = 5;
		String name = "강풀";
		String desc = "온라인 만화가 1세대";

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE AUTHOR " + "SET AUTHOR_DESC = ? " + ",AUTHOR_NAME= ? " + "WHERE AUTHOR_ID= ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, desc);
			pstmt.setString(2, name);
			pstmt.setInt(3, num);
			int count = pstmt.executeUpdate();
			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}
	}
}
