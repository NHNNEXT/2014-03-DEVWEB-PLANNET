package net.plannet.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private ConnectionFactory() {
	}

	public static Connection getConnection() {
		String url = "jdbc:mysql://10.73.45.137:3306/plannet";
		String id = "root";
		String pw = "plan1004";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println("[DB Connection Failed] : " + e.getMessage());
			return null;
		}
	}
}
