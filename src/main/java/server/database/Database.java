package server.database;

import java.sql.*;

import utils.Message;

public class Database { //Singleton

	private static Connection conn;
	
	public static ResultSet query(String query) {
		try {
			Statement s = getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			s.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void update(String query) {
		try {
			Statement s = getConnection().createStatement();
			s.executeUpdate(query);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(conn == null){
			try {
				Class.forName("org.sqlite.JDBC");
				try {
					conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
					Database.create();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	
	public static void store(String client, Message msg) {
		DatabaseLogger.store(client, msg);
	}
	
	private static void create() {
		DatabaseBuilder.createTables();
	}
	
	public static ResultSet getMessages(String client) {
		ResultSet r = DatabaseReader.getMessages(client);
		return r;
	}
}
