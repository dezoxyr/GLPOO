package testDatabase;

import server.database.DatabaseReader;
import server.middleware.*;
import utils.Message;
import client.Client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataReadTest {

	@Test
	void testGetMessages() {
		Serveur a = new Serveur();
		a.connect("127.0.0.1", 4447);
		a.open("JO5");
		Client b = new Client("JO5");
		b.connect("127.0.0.1", 4447);
		Message msg1 = new Message(b.getPseudo(), "Hello");
		b.msg(msg1);
		assertNotNull(DatabaseReader.getMessages("JO5"));
	}

}
