package client;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import server.middleware.Serveur;

public class Main {

	public static void main(String[] args) {
		Client c1 = new Client();
		Client c2 = new Client();
		Serveur s = new Serveur();
		
		s.connect("127.0.0.1", 6666);//Crée un serveur a l'ip "127.0.0.1" et sur le port 6666
		s.open();					//Ouvre le serveur
		
		c1.connect("127.0.0.1",6666);//Connecte un client sur l'ip "127.0.0.1" et sur le port 6666 renvoi une erreur si il n'y a pas de serveur ouvert
		c2.connect("127.0.0.1",6666);
		try {
			Thread.sleep(300);	//tempo afin de laisser le temps de connecter les clients au serveur
			c1.msg("Bonjour je suis Malko");//envoie un message au serveur
			
			c2.msg("Bonjour je suis Alexis");
			
			
		}catch (InterruptedException ex) {
			System.out.println("error");
		}	
		
		
		
		/*JFrame frame = new JFrame("Launcher");
		frame.setMinimumSize(new Dimension(1080,720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true);*/
	}
}
