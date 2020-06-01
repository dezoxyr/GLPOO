package server.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Message;

public class DatabaseLogger {

	public static void store(Message msg){
		try {
			PreparedStatement ps = Database.getConnection().prepareStatement("INSERT INTO messages(convId,pseudo,msg,date) VALUES (?,?,?,CURRENT_DATE)");
			
			ps.setString(1, msg.getConvId().toString());
			ps.setString(2, msg.getSender().toString());
			ps.setString(3, msg.getMessage().toString());
			
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
