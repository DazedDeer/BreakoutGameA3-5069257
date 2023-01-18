import java.awt.Graphics;

// The Paddle Class which is a child of the Sprite Class
public class Paddle extends Sprite {

	// The paddle's xVelocity attribute
	private int xVelocity;
	
	// Paddle constructor
	public Paddle() {
		// Set the paddle's height and width
		this.width = Settings.PADDLE_WIDTH;
		this.height = Settings.PADDLE_HEIGHT;
		// Reset the paddle's position to the initial position
		resetPosition();
	}
	
	// Resets the paddle's position to the initial position
	public void resetPosition() {
		// The paddle is set to the initial x and y positions from Settings
		this.x = Settings.INITIAL_PADDLE_X;
		this.y = Settings.INITIAL_PADDLE_Y;
	}
	
	// Updates the paddle's movement by increasing the paddle's x attribute by the paddle's xVelocity
	public void update() {
		// Update the paddle's x attribute by adding the paddle's xVelocity to it
		x += xVelocity;
		// Prevents the paddle from moving outside the screen on the left side
		if (this.getX() == 0) {
			this.setX(this.getX() + 1);
		}
		// Prevents the paddle from moving outside the screen on the right side
		else if (this.getX() >= Settings.WINDOW_WIDTH - this.getWidth() - 15) {
			this.setX(this.getX() - 1);
		}
	}
	
	// Paints the paddle's graphics
	public void paint(Graphics g) {
		g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
	}
	
	// Sets the paddle's xVelocity
	public void setXVelocity(int vel) {
		this.xVelocity = vel;
	}
}
