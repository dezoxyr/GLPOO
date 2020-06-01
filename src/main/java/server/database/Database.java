package server.database;

import java.sql.*;

import utils.Message;

public class Database {

	private static Connection conn;
	
	public static void init() {
		if(conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				try {
					conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
					DatabaseBuilder.createTables();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static ResultSet query(String query) {
		try {
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery(query);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void update(String query) {
		try {
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	
	public static void store(Message msg) {
		DatabaseLogger.store(msg);
	}
	
}
