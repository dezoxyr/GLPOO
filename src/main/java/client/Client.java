package client;
import java.io.*;
import java.net.*; 


public class Client{
	
	
	private Socket socket;
	private PrintWriter writer;
	private OutputStream output;
	private InputStream input;
	private BufferedReader reader;
	
	public void connect(String ip, int port) {

		try { //ouvre le socket
			socket = new Socket(ip, port);
			
			
		} catch (UnknownHostException uhe) {
			System.out.println(uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	public void sendText(Socket socket, String msg) {
		try {
			output = socket.getOutputStream(); //ouvre un flux de sortie vers le socket
			input = socket.getInputStream(); //ouvre un flux d’entrée vers le socket
			writer = new PrintWriter(this.output, true);// écrit vers le flux de sortie, en accord avec le protocole du serveur!
			reader = new BufferedReader(new InputStreamReader(this.input));
			
			Thread envoyer = new Thread(new Runnable() {
				@Override
				public void run() {
					writer.write(msg+"\n");
					writer.flush();
				}	
			});
			envoyer.start();
			
			Thread recevoir = new Thread(new Runnable() {
				@Override
				public void run() {
					String line = null;
					try {
					line = reader.readLine(); //lit le flux d’entrée, en accord avec le protocole du serveur!
					}catch(IOException ioe) {
						System.out.println(ioe.getMessage());
					}
					System.out.println(line);
				}
			});
			recevoir.start();
		
		}catch (IOException ioe) {System.out.println(ioe.getMessage());}
	}
	
	public void msg(String text) {
		sendText(this.socket, text);
	}
	
}
