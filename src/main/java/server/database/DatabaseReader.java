package server.database;

import java.sql.ResultSet;

public class DatabaseReader extends Database{
	public static ResultSet getMessages() {
		return Database.query("SELECT * from messages");
		
	}
	

}
