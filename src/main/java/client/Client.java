package client;
import java.io.*;
import java.net.*;

import interfaceGUI.Fenetre;
import server.database.Database;
import utils.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Client{ //Singleton
	
	
	private static Socket socket;
	private PrintWriter writer;
	private OutputStream output;
	private InputStream input;
	private BufferedReader reader;
	private static String pseudo;
	
	public Client(String p) {
		pseudo = p;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
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
			writer = new PrintWriter(output, true);// écrit vers le flux de sortie, en accord avec le protocole du serveur!
			reader = new BufferedReader(new InputStreamReader(input));
			
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
					Message rmsg = null;
					String line = null;
					try {
					line = reader.readLine(); //lit le flux d’entrée, en accord avec le protocole du serveur!
					Gson gson = new Gson();
					rmsg = gson.fromJson(line, Message.class);
					}catch(IOException ioe) {
						System.out.println(ioe.getMessage());
					}
					
					Fenetre.readDatabase();
					System.out.println(rmsg.getMessage());
					
					Database.store(new Message(rmsg.getSender(), rmsg.getMessage()));
				}
			});
			recevoir.start();
		
		}catch (IOException ioe) {System.out.println(ioe.getMessage());}
	}
	
	public void msg(Message message) {
		Gson gson = new Gson();
		String json = gson.toJson(message);
		sendText(socket, json);
		
		Database.store(new Message(message.getSender(), message.getMessage()));

	}
	
}
