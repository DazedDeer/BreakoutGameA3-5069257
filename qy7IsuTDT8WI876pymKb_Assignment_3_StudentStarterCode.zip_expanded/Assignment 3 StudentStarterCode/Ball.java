import java.awt.Graphics;

public class Ball extends Sprite {

	// Private ball attributes denoting the ball's velocity
	private int xVelocity = 1, yVelocity = -1;
	
	// Ball constructor
	public Ball() {
		// TODO: Set width to Settings.BALL_WIDTH
		// TODO: Set width to Settings.BALL_HEIGHT
		// TODO: Call resetPosition
	}
	
	/**
	 * Resets the ball to the initial position
	 * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
	 */
	public void resetPosition() {
		setX(Settings.INITIAL_BALL_X);
		// TODO: Set the balls y by using the INITIAL_BALL_Y (see above)
	}
	
	public void update() {
		x += xVelocity;
		// TODO: Increase the y variable by yVelocity (see above)
		
		// Bounce off left side of screen
		if(x <= 0) {
			// TODO: Set x to 0 so it does not leave the screen
			// TODO: Change the x velocity to make the ball go right
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
			// TODO: Set x to the right edge of the screen (see the above if condition)
			// TODO: Change the x velocity to make the ball go left
		}
		
		// Bounce off top of screen
		if(y <= 0) {
			// TODO: Set y to 0 so it does not leave the screen
			// TODO: Change the y velocity to make the ball go downward
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
