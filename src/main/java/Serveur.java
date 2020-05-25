import java.io.*;
import java.net.*;

public class Serveur {

	private String ip = "localhost";
	
	public void connect(String ip) {
		try {
			ServerSocket ss = new ServerSocket (6666);
			System.out.println("Server waiting for connection...");
			while (true) {
				Socket socket = ss.accept(); //établit la connexion
				System.out.println("Connected as " + ip);
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				String text = reader.readLine();
				String reverseText = new StringBuilder(text).reverse().toString();
				writer.println("Server response: " + reverseText);
				output.close();
				input.close();
				socket.close();
			}
		} catch (IOException ioe) {
			System.out.println (ioe.getMessage());
		}
	}
}
