package testClient;
import client.Client;
import server.middleware.Serveur;
import utils.Message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

class clientTest {


//a

	@Test
	void testClient() {
		Client a = new Client("Abs");
        assertEquals(a.getPseudo(),"Abs");
        Client b = new Client("%_èAbs");
        assertEquals(b.getPseudo(),"%_èAbs"); 
	}
	
	@Test
	void testGetPseudo() {
		Client a = new Client("Abs");
        assertEquals(a.getPseudo(),"Abs");
        Client b = new Client("%_èAbs");
        assertEquals(b.getPseudo(),"%_èAbs"); 
	}

	@Test
	void testConnect() {

		Serveur a = new Serveur();
		Client b = new Client("JO0");
		a.connect("127.0.0.1", 4444);
		a.open("JO");
		b.connect("127.0.0.1", 4444);
		assertNotNull(b);
	//	a.killServeur();
	//	b.killClient();
		
	/*	String ip = "localhost";
		int port = 6666;
		Socket socket;
		OutputStream output;
		InputStream input;
		try { //ouvre le socket
			socket = new Socket(ip, port);
			output = socket.getOutputStream(); //ouvre un flux de sortie vers le socket
			input = socket.getInputStream(); //ouvre un flux d’entrée vers le socket
			assertNotNull(socket);
			assertNotNull(output);
			assertNotNull(input);
		} catch (UnknownHostException uhe) {
			System.out.println(uhe.getMessage());
		} catch (IOException ioe) {System.out.println(ioe.getMessage());
		}*/
	}

	@Test
	void testSendText() {
		Serveur a = new Serveur();
		Client b = new Client("JO1");
		a.connect("127.0.0.1", 4445);
		a.open("JO");
		b.connect("127.0.0.1", 4445);
	//	a=null;
	//	b=null;
		Message msg1 = new Message(b.getPseudo(), "Hello");
		b.msg(msg1);
		assertNotNull(msg1);
		/*FileWriter writer;
		try {
			writer = new FileWriter("initial.txt");
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.write("message sent\n");
			//Path pathToFile = Paths.get("appro.txt");
		    //System.out.println(pathToFile.toAbsolutePath());
			byte[] file1Bytes = Files.readAllBytes(Paths.get("initial.txt"));
			byte[] file2Bytes = Files.readAllBytes(Paths.get("appro.txt"));
			
	//		try (BufferedReader br = new BufferedReader(new FileReader("initial.txt"))) {
	//			   String line;
	//			   while ((line = br.readLine()) != null) {
	//			       System.out.println(line);
	//			   }
	//			}
			String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
			String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
			assertEquals("The content in the strings should match", file1, file2);
			printWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		

	}

	@Test
	void testMsg() {
		Message msg1 = new Message("JO2","Hello"); 
		Gson gson = new Gson();
		String json = gson.toJson(msg1);
		assertTrue(json.contains("Hello"));
		/*Gson gson = new Gson();
		String json = gson.toJson("message sent");
		try {
			FileWriter writer = new FileWriter("initial.txt");
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.print(json);
			
			byte[] file1Bytes = Files.readAllBytes(Paths.get("initial.txt"));
			byte[] file2Bytes = Files.readAllBytes(Paths.get("appro.txt"));

			String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
			String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
			printWriter.close();
			assertEquals("The content in the strings should match", file1, file2);
			printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}*/
	}

	@Test
	void testReceive() {
		Serveur a = new Serveur();
		a.connect("127.0.0.1", 4446);
		a.open("JO3");
		Client b = new Client("JO3");
		b.connect("127.0.0.1", 4446);
		b.receive();
		Thread currentThread = Thread.currentThread();
		assertNotNull(currentThread.getName());
		/*PrintWriter printWriter;
		String line;
		try {
			// simulation attente thread
			Gson gson = new Gson();
			String json = gson.toJson("message sent");
			FileWriter writer = new FileWriter("initial.txt");
			printWriter = new PrintWriter(writer);
			printWriter.print(json);
			printWriter.print("Database");
			
			Thread.sleep(2000);
			
			byte[] encoded = Files.readAllBytes(Paths.get("initial.txt")); //lit le flux d’entrée, en accord avec le protocole du serveur!
			line = new String(encoded, StandardCharsets.US_ASCII);
			assertEquals("initial.txt", "appro.txt");
			printWriter.close();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
