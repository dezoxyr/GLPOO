package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader extends Database{
	
	static ResultSet r = null;
	static PreparedStatement ps = null;
	
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
