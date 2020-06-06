package client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import interfaceGUI.Fenetre;
import server.database.Database;
import server.middleware.Serveur;
import utils.Message;

public class Main {

	public static void main(String[] args) {
		
		
		Fenetre fenetre = new Fenetre();

		try {
			Thread.sleep(300);	//tempo afin de laisser le temps de connecter les clients au serveur
			
			
		}catch (InterruptedException ex) {
			System.out.println("error");
		} 
		
		fenetre.buildPanel();
		
	}
}
