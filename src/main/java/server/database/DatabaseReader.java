package server.database;

import java.sql.ResultSet;

public class DatabaseReader extends Database{
	public static ResultSet getMessages(String pseudo) {
		return Database.query("SELECT * from "+pseudo);		
	}

}
