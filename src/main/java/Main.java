import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		System.out.println("Debut");
		Client c1 = new Client();
		Client c2 = new Client();
		Serveur s = new Serveur();
		s.connect("127.0.0.1", 6666);
		s.open();
		c1.connect("127.0.0.1",6666);;
		c2.connect("127.0.0.1",6666);;
		try {
			Thread.sleep(300);
			c1.msg("Bonjour je suis Malko");
			
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
