package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class DatabaseReader
 * */

public class DatabaseReader extends Database implements ReadDB{
	
	static ResultSet r = null;
	static PreparedStatement ps = null;
	
	/**
	 * Get messages of a client from the database
	 * @param client
	 	*The pseudo of whom you want the messages
	 *@return a ResultSet with the different messages of the client
	 * */
	
	public static ResultSet getMessages(String client) {
		try {
			if(ps != null) {
				ps.close();
			}
			ps = Database.getConnection().prepareStatement("SELECT * from messages WHERE client=?");
			ps.setString(1, client);
			r = ps.executeQuery();	
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
