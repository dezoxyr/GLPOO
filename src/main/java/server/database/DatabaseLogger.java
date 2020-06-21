package server.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Message;

/**
 * Class DatabaseLogger
 * */

public class DatabaseLogger extends Database implements LogDB{

	/**
	 * Store a client's message in the database
	 * @param client
	 	*The pseudo of the client
	 *@param msg
	 	*The client's message 
	 * */
	
	public static void store(String client, Message msg){
		try {
			PreparedStatement ps = Database.getConnection().prepareStatement("INSERT INTO messages(pseudo,client,msg,date) VALUES (?,?,?,CURRENT_DATE)");
			
			ps.setString(1, msg.getSender().toString());
			ps.setString(2, client);
			ps.setString(3, msg.getMessage().toString());
			
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
