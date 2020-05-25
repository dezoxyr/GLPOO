import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public Main() {
		JFrame frame = new JFrame("Launcher");
		frame.setMinimumSize(new Dimension(1080,720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true);
	}
}
