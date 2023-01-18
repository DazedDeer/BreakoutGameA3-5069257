import java.awt.Color;

import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	// Declares the breakout panel variable
	private BreakoutPanel panel;
	
	// Sets up the screen for the game
	public Breakout() {
		// Sets the window properties
		setTitle(Settings.WINDOW_NAME);
		setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		setBackground(Color.WHITE);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Creates the breakout panel
        panel = new BreakoutPanel(this);
        add(panel);
        setVisible(true);
	}
	
	// Starts the game
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 new Breakout();	
	         }
		});

	}
}
