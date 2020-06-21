package testDatabase;
import server.database.Database;
import server.middleware.Serveur;
import utils.Message;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import client.Client;

class dataLogTest {

	@Test
	void testStore() {
		Serveur a = new Serveur();
		a.connect("127.0.0.1", 4449);
		a.open("JO8");
		Client b = new Client("JO8");
		b.connect("127.0.0.1", 4449);
		Message msg1 = new Message(b.getPseudo(), "Hello");
		b.msg(msg1);
		Database.store("JO8", msg1);
		
		Statement s;
		try {
			s = Database.getConnection().createStatement();
			ResultSet r = s.executeQuery("select * from messages where client='JO8'");
			assertEquals(r.getString("client"),"JO8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
