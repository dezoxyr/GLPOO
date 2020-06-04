package client;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import server.database.Database;
import server.database.DatabaseReader;
import server.middleware.Serveur;
import utils.Message;

public class Main {

	public static void main(String[] args) {
		Database.init();
		
		Client c1 = new Client("Malko");
		Client c2 = new Client("Alexis");
		Serveur s = new Serveur();
		
		s.connect("127.0.0.1", 6666); //Crée un serveur a l'ip "127.0.0.1" et sur le port 6666
		s.open();					  //Ouvre le serveur
		
		c1.connect("127.0.0.1",6666); //Connecte un client sur l'ip "127.0.0.1" et sur le port 6666 renvoi une erreur si il n'y a pas de serveur ouvert
		c2.connect("127.0.0.1",6666);

		try {
			Thread.sleep(300);	//tempo afin de laisser le temps de connecter les clients au serveur
			
			Message msg1 = new Message(c1.getPseudo(), "Bonjour je suis Malko");
			c1.msg(msg1);//envoie un message au serveur
			
			Message msg2 = new Message(c2.getPseudo(), "Bonjour je suis Alexis");
			c2.msg(msg2);
			
			
		}catch (InterruptedException ex) {
			System.out.println("error");
		} 
		
		
		
		/*JFrame frame = new JFrame("Launcher");
		frame.setMinimumSize(new Dimension(1080,720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true); */
	}
}
