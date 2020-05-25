import java.io.*;
import java.net.*; 


public class Client {

	public void connect(String ip) {
		int port = 6666;
		try (Socket socket = new Socket(ip, port)) { //ouvre le socket
			OutputStream output = socket.getOutputStream(); //ouvre un flux de sortie vers le socket
			PrintWriter writer = new PrintWriter(output, true);// écrit vers le flux de sortie, en accord avec le protocole du serveur!
			writer.println("Enter text: ");
			InputStream input = socket.getInputStream(); //ouvre un flux d’entrée vers le socket
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line = reader.readLine(); //lit le flux d’entrée, en accord avec le protocole du serveur!
			System.out.println(line);
			input.close(); //clôt le inputStream
			output.close(); //clôt le outputStream
			socket.close(); //clôt le socket
		} catch (UnknownHostException uhe) {
			System.out.println(uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}
