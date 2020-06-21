package testDatabase;
import server.database.Database;
import server.database.DatabaseReader;
import server.middleware.Serveur;
import utils.Message;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import client.Client;

class databaseTest {

	@Test
	void testQuery() {
		Serveur a = new Serveur();
		a.connect("127.0.0.1", 4448);
		a.open("JO6");
		Client b = new Client("JO6");
		b.connect("127.0.0.1", 4448);
		Message msg1 = new Message(b.getPseudo(), "Hello");
		b.msg(msg1);
		
		Statement s;
		try {
			s = Database.getConnection().createStatement();
			ResultSet r = s.executeQuery("select * from messages where client='JO6'");
			assertEquals(r.getString("client"),"JO6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdate() {
		Statement s;
		try {
			s = Database.getConnection().createStatement();
			ResultSet r = s.executeQuery("select * from messages where client='JO6'");
			assertEquals(r.getString("client"),"JO6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetConnection() {
		try {
			assertNotNull(DriverManager.getConnection("jdbc:sqlite:sqlite.db"));
			assertNotNull(Database.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testStore() {
		Statement s;
		Message msg1 = new Message("86dsa", "What");
		Database.store("86dsa", msg1);
		ResultSet r;
		try {
			s = Database.getConnection().createStatement();
			r = s.executeQuery("select * from messages where client='86dsa'");
			assertEquals(r.getString("client"),"86dsa");
			assertEquals(r.getString("msg"),"What");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	void testGetMessages() {
		Statement s;
		Message msg1 = new Message("sdafsdg54_çé", "What");
		Database.store("sdafsdg54_çé", msg1);
		ResultSet r;
		try {
			s = Database.getConnection().createStatement();
			r = s.executeQuery("select * from messages where client='sdafsdg54_çé'");
			assertEquals(r.getString("client"),DatabaseReader.getMessages("sdafsdg54_çé").getString("client"));
			assertEquals(r.getString("msg"),DatabaseReader.getMessages("sdafsdg54_çé").getString("msg"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
