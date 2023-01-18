import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

// BreakoutPanel class which is a child of the JPanel class
public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
	
	static final long serialVersionUID = 2L;

	// Key Breakout panel variables and objects
	private boolean gameRunning = true;
	private int livesLeft = 3;
	private String screenMessage = "";
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	
	// Breakout Panel constructor
	public BreakoutPanel(Breakout game) {
		
		// Add listener to detect when a user presses keys
		addKeyListener(this);
		setFocusable(true);
		
		// Sets up the timer for the game
		Timer timer = new Timer(5, this);
		timer.start();
		
		// Creates a ball for the game
		ball = new Ball();
		
		// Creates a paddle for the game
		paddle = new Paddle();
		
		// Creates a bricks array to contain the total number of bricks found in settings
		bricks = new Brick[Settings.TOTAL_BRICKS];
		
		// Calls the method to create the bricks
		createBricks();
	}
	
	// Method that creates the bricks
	private void createBricks() {
		
		int counter = 0;
		int x_space = 0;
		int y_space = 0;
		
		for(int x = 0; x < 4; x++) {
			
			for(int y = 0; y < 5; y++) {
				
				// Arranges the bricks on the panel
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++;
				y_space++;
			}
			x_space++;
			y_space = 0;
		}
	}
	
	// Method to paint each brick in the bricks array
	private void paintBricks(Graphics g) {
		
		// loops through the bricks array
		for (int i = 0; i < bricks.length; i++) {
			bricks[i].paint(g);
		}
	}
	
	// Method that is used while the game is running to keep the game updated
	private void update() {
		// Check if the game is running before updating
		if(gameRunning) {
			ball.update();
			paddle.update();
			collisions();
			repaint();
		}
	}
	
	// Method that is used to show that the game is over
	private void gameOver() {
		screenMessage = "GAME OVER!";
		stopGame();
	}
	
	// Method that is used to show that the game has been won
	private void gameWon() {
		screenMessage = "YOU WIN!";
		stopGame();
	}
	
	// Method that is used to stop the game
	private void stopGame() {
		gameRunning = false;
	}
	
	/* Method that checks if game has been lost 
	 * (when are no lives left and the ball hits the bottom of the screen)
	 */
	private void collisions() {
		
		// Check for loss
		if(ball.y > 450) {
			
			// Deduct a life
			livesLeft--;
			if(livesLeft <= 0) {
				
				// Game is over
				gameOver();
				return;
			} 
			else {
				
				// resets the ball
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
		
		// Loop through the bricks array
		for(int i = 0; i < bricks.length; i++) {
			
			// Check if there are any bricks left
			if(!bricks[i].isBroken()) {	
				
				// An unbroken brick was found, close loop
				bricksLeft = true;
				break;
			}
		}
		
		if(!bricksLeft) {
			
			// Game is won, no bricks left
			gameWon();
			return;
		}
		
		// Check collisions
		if(ball.getRectangle().intersects(paddle.getRectangle())) {
			
			// Simplified touching of paddle
			// Proper game would change angle of ball depending on where it hit the paddle
			ball.setYVelocity(-1);
		}
		
		// Checks if the ball has collided with a brick by looping through the bricks array
		for(int i = 0; i < bricks.length; i++) {
			
			// Checks if the ball intersected with a brick
			if (ball.getRectangle().intersects(bricks[i].getRectangle())) {
				
				int ballLeft = (int) ball.getRectangle().getMinX();
	            int ballHeight = (int) ball.getRectangle().getHeight();
	            int ballWidth = (int) ball.getRectangle().getWidth();
	            int ballTop = (int) ball.getRectangle().getMinY();
	            
	            // Creates new points
	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	            Point pointLeft = new Point(ballLeft - 1, ballTop);
	            Point pointTop = new Point(ballLeft, ballTop - 1);
	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
	            
	            // Changes ball's velocity based on points
	            if (!bricks[i].isBroken()) {
	            	
	                if (bricks[i].getRectangle().contains(pointRight)) {
	                    ball.setXVelocity(-1);
	                }  
	                else if (bricks[i].getRectangle().contains(pointLeft)) {
	                    ball.setXVelocity(1);
	                }
	                if (bricks[i].getRectangle().contains(pointTop)) {
	                    ball.setYVelocity(1);
	                } 
	                else if (bricks[i].getRectangle().contains(pointBottom)) {
	                    ball.setYVelocity(-1);
	                }
	                // Sets the brick that was hit to broken
	                bricks[i].setBroken(true);
	            }
			}
		}
	}
	
	// Paints the components
	@Override
    public void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        
        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        g.drawString(String.valueOf(livesLeft), Settings.LIVES_POSITION_X, Settings.LIVES_POSITION_Y);
        
        // Draw screen message
        if(screenMessage != null) {
        	
        	g.setFont(new Font("Arial", Font.BOLD, 18));
        	int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
        	g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }
    }

	// Moves the paddle depending on whether the right or left arrow key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Checks if the left arrow key has been pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			paddle.setXVelocity(-1);
			
		// Checks if the right arrow key has been pressed
		} 
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.setXVelocity(1);
		}
	} 

	// Stops the paddle from moving once the left and right arrow keys are no longer held down
	@Override
	public void keyReleased(KeyEvent e) {
		
		// Check if the left or right arrow keys have been released
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			// Stops the paddle's movement
			paddle.setXVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
	}
}
