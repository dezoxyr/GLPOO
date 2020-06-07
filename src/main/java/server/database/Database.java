package server.database;

import java.sql.*;

import utils.Message;

/**
 * Class Database
 * */

public class Database { //Singleton

	private static Connection conn;
	
	/**
	 * Do a query to the database with a return statement
	 * @param query
	 	*The query
	 *@return A ResultSet of your query 
	 * */
	
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
	
	/**
	 * Do a query to the database with no return statement
	 * @param query
	 	*The query
	 * */
	
	public static void update(String query) {
		try {
			Statement s = getConnection().createStatement();
			s.executeUpdate(query);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the connection with the database
	 * @return the connection
	 * */
	
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
	
	/**
	 * Store a client's message in the database
	 * @param client
	 	*The pseudo of the client
	 *@param msg
	 	*The client's message 
	 * */
	
	public static void store(String client, Message msg) {
		DatabaseLogger.store(client, msg);
	}
	
	/**
	 * Create tables
	 * */
	
	private static void create() {
		DatabaseBuilder.createTables();
	}
	
	/**
	 * Get messages of a client from the database
	 * @param client
	 	*The pseudo of whom you want the messages
	 *@return a ResultSet with the different messages of the client
	 * */
	
	public static ResultSet getMessages(String client) {
		ResultSet r = DatabaseReader.getMessages(client);
		return r;
	}
}
