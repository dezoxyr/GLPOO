package server.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Message;

public class DatabaseLogger {

	public static void store(String table, Message msg){
		try {
			PreparedStatement ps = Database.getConnection().prepareStatement("INSERT INTO "+table+"(pseudo,msg,date) VALUES (?,?,CURRENT_DATE)");
			
			ps.setString(1, msg.getSender().toString());
			ps.setString(2, msg.getMessage().toString());
			
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
