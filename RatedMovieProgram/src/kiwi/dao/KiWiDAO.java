package kiwi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KiWiDAO {
	protected Connection makeConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/KiWiDB?serverTimezone=Asia/Seoul";
			connection = DriverManager.getConnection(url, "root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}