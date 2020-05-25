import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Client c = new Client();
		Serveur s = new Serveur();
		s.connect("localhost");
		c.connect("localhost");
		
		
		/*JFrame frame = new JFrame("Launcher");
		frame.setMinimumSize(new Dimension(1080,720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true);*/
	}
}
