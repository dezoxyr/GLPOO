package server.middleware;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import utils.Message;

public class Serveur {

	private boolean isrunning = true;
	private ServerSocket ss;
	private List<Socket> sockets = new ArrayList<Socket>();
	private String owner;
	
	
	public void connect(String ip, int port) {
		try {
			ss = new ServerSocket (port,10,InetAddress.getByName(ip));
			System.out.println("Server waiting for connection...");
		} catch (IOException ioe) {
			System.out.println (ioe.getMessage());
		}
	}
	
	public void open(String owner) {
		this.owner = owner;
		System.out.println("Bonjour je suis le serveur :-)");
		Thread t = new Thread(new Runnable() {
			public void run() {
				
				while (isrunning == true) {
					try {
						Socket socket = ss.accept(); //établit la connexion
						System.out.println("Connexion recue");
						sockets.add(socket);
						
						for(Socket s: sockets) {
							BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
							PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
							
							Thread reception = new Thread(new Runnable() {
								@Override
								public void run() {
									String text = null;
									Message rmsg = null;
									Gson gson = new Gson();
									while(true) {
										try {
										text = reader.readLine();
										rmsg = gson.fromJson(text, Message.class);
										System.out.println("Server> "+rmsg.getMessage());
										}catch(IOException ioe) {System.out.println (ioe.getMessage());}
									
										for(Socket s: sockets) {
											PrintWriter writer = null;
											try {
												writer = new PrintWriter(s.getOutputStream(), true);
											} catch (IOException e) {
												e.printStackTrace();
											}
											writer.write("\n"+text+"\n");
											writer.flush();
										}
										
									}
								}
								
							});
							reception.start();
						}
					}catch(IOException ioe) {
						System.out.println (ioe.getMessage());
					}
				}
			}
		});	
		t.start();
	}
	
}
