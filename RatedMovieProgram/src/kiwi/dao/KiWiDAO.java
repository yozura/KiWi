package kiwi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KiWiDAO {
	public Connection makeConnection(String id, String password) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/KiWiDB?serverTimezone=Asia/Seoul";
			connection = DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}