package testServeur;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class serveurTest {

	private List<Socket> sockets=new ArrayList<Socket>();

	@Test
	void testConnect() {
		String ip = "localhost";
		int port = 4447;
		ServerSocket ss;
		try {
			ss = new ServerSocket (port,10,InetAddress.getByName(ip));
			assertNotNull(ss);
			ss.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testOpen() {
		sockets.add(new Socket());
		sockets.add(new Socket());
		sockets.add(new Socket());
		assertEquals("Checking size of List", 3, sockets.size());
		Thread currentThread = Thread.currentThread();
		assertNotNull(currentThread.getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
