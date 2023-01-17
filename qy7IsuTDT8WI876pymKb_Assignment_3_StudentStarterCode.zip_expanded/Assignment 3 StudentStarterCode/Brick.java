import java.awt.Graphics;


// The Brick Class which is a child of the Sprite Class
public class Brick extends Sprite {
	
	
	// Brick attribute for whether the brick is broken or now (false = not broken, true = broken)
	private boolean broken = false;
	
	
	// Brick constructor
	public Brick(int x, int y) {
		
		// Sets the brick's size 
		this.width = Settings.BRICK_WIDTH;
		this.height = Settings.BRICK_HEIGHT;
		
		// Sets the brick's x and y position
		this.x = x;
		this.y = y;
	}

	
	// Method that tells you whether the brick is broken or not
	public boolean isBroken() {
		return this.broken;	
	}
	
	
	// Method that sets whether the brick is broken or not
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
	
	
	// Paints the Brick graphics
	public void paint(Graphics g) {
		if(!broken) {
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
