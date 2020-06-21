package server.middleware;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Serveur
 * */

public class Serveur extends Abstractserver{

	private boolean isrunning = true;
	private ServerSocket ss;
	private List<Socket> sockets = new ArrayList<Socket>();
	private String owner;
	
	/**
	 * Create a server
	 * @param ip
	 	*The ip you want to use
	 *@param port
	 	*The port you want to use 
	 * */
	
	public void connect(String ip, int port) {
		try {
			ss = new ServerSocket (port,10,InetAddress.getByName(ip));
			System.out.println("Server waiting for connection...");
		} catch (IOException ioe) {
			System.out.println (ioe.getMessage());
		}
	}
	
	/**
	 * Open a server allowing its to receive clients connections
	 * @param owner
	 	*The owner of the server 
	 * */
	
	public void open(String owner) {
		this.owner = owner;
		Thread t = new Thread(new Runnable() {
			public void run() {
				
				while (isrunning == true) {
					try {
						Socket socket = ss.accept(); //établit la connexion
						System.out.println("Get a connection");
						sockets.add(socket);
						BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						Thread reception = new Thread(new Runnable() {
							@Override
							public void run() {
								String text = null;
								while(true) {
								try {
									text = reader.readLine();
									for(Socket s: sockets) {
										if(s.isClosed()) {
											sockets.remove(s);
										}else {
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
								}catch(IOException ioe) {
									try {
										socket.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								}
							}
							});
							reception.start();
						}catch(IOException ioe) {
							System.out.println (ioe.getMessage());
					}
				}
			}
		});	
		t.start();
	}
}
