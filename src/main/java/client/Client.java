package client;
import java.io.*;
import java.net.*;

import interfaceGUI.Fenetre;
import server.database.Database;
import utils.Message;
import com.google.gson.Gson;

/**
 * Class Client
 * */

public class Client{ //Singleton
	
	
	private static Socket socket;
	private PrintWriter writer;
	private OutputStream output;
	private InputStream input;
	private BufferedReader reader;
	private static String pseudo;
	
	/**
	 * Constructor
	 * @param p
	 	*Client's pseudo 
	 * */
	
	public Client(String p) {
		pseudo = p;
	}
	
	/**
	 * Get the pseudo of a client
	 * @return the pseudo of the client
	 * */
	
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Connect a client to a server
	 * @param ip
	 	*The ip you want to use
	 *@param port
	 	*The port of the server 
	 * */
	
	public void connect(String ip, int port) {

		try { //ouvre le socket
			socket = new Socket(ip, port);
			output = socket.getOutputStream(); //ouvre un flux de sortie vers le socket
			input = socket.getInputStream(); //ouvre un flux d’entrée vers le socket
			writer = new PrintWriter(output, true);// écrit vers le flux de sortie, en accord avec le protocole du serveur!
			reader = new BufferedReader(new InputStreamReader(input));
			receive();
			
			
		} catch (UnknownHostException uhe) {
			System.out.println(uhe.getMessage());
		} catch (IOException ioe) {System.out.println(ioe.getMessage());
		}
	}
	
	/**
	 * Send a message to the server in a new Thread
	 * @param socket
	 	*The socket connected to the server
	 *@param msg
	 	*The message you want to send 
	 * */
	
	public void sendText(Socket socket, String msg) {	
		Thread envoyer = new Thread(new Runnable() {
			@Override
			public void run() {
				writer.write(msg+"\n");
				writer.flush();
			}	
		});
		envoyer.start();
	}
	
	/**
	 * Create a Json with the message and the message's sender
	 * @param message
	 	*The message 
	 * */
	
	public void msg(Message message) {
		Gson gson = new Gson();
		String json = gson.toJson(message);
		sendText(socket, json);
	}
	
	/**
	 * Receive server's response and print it in the chat. Run in a Thread.
	 * */
	
	public void receive() {
		Thread recevoir = new Thread(new Runnable() {
			@Override
			public void run() {
				Message rmsg = null;
				Gson gson = new Gson();
				String line = null;
				while(true){
					try {
						if(reader.readLine() != null) {
							line = reader.readLine(); //lit le flux d’entrée, en accord avec le protocole du serveur!
							rmsg = gson.fromJson(line, Message.class);
							Database.store(pseudo, new Message(rmsg.getSender(), rmsg.getMessage()));
							Fenetre.readDatabase();
						}
					}catch(IOException ioe) {
						System.out.println(ioe.getMessage());
						try {
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		recevoir.start();
	}
	
}
