import java.awt.Graphics;

// The ball class, a child of the Sprite class
public class Ball extends Sprite {

	
	// Private ball attributes denoting the ball's velocity
	private int xVelocity = 1, yVelocity = -1;
	
	// Ball constructor
	public Ball() {
		
		// Set's the ball's size
		this.setWidth(Settings.BALL_WIDTH);
		this.setHeight(Settings.BALL_HEIGHT);
		
		// Resets the ball's position to the initial position
		resetPosition();
	}
	
	/**
	 * Resets the ball to the initial position
	 * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
	 */
	public void resetPosition() {
		
		// Sets the ball back to the initial y and x positions
		this.setX(Settings.INITIAL_BALL_X);
		this.setY(Settings.INITIAL_BALL_Y);
	}
	
	// Updates the balls position, making sure it doesn't leave the screen
	public void update() {
		
		// Updates the balls x and y positions by adding the relevant x or y velocities to them
		x += xVelocity;
		y += yVelocity;
		
		// Bounce off left side of screen
		if(x <= 0) {
			
			// Moves the ball away from the edge
			x = 0;
			
			// Reverses the ball's velocity to make it go right
			xVelocity = -xVelocity;
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH - 15) {
			
			// Moves the ball away from the edge
			x --;
			
			// Reverses the ball's velocity to make it go left
			xVelocity = -xVelocity;
		}
		
		// Bounce off top of screen
		if(y <= 0) {
			
			// Moves the ball away from the edge
			y = 0;
			
			// Reverses the ball's velocity to make it go downwards
			yVelocity = -yVelocity;
		}
	}
	
	// Set the ball's x velocity
	public void setXVelocity(int x) {
		this.xVelocity = x;
	}
	
	// Set the ball's y velocity
	public void setYVelocity(int y) {
		this.yVelocity = y;
	}
	
	// Return the ball's x velocity
	public int getXVelocity() {
		return this.xVelocity;
	}
	
	// Return the ball's y velocity
	public int getYVelocity() {
		return this.yVelocity;
	}
	
	// Paint the ball's graphics
	public void paint(Graphics g) {
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
	}
}
