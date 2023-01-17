import java.awt.Rectangle;

public class Sprite {
	
	protected int x,y,width,height;
	
	// Set the x value
	public void setX(int x) {
		this.x = x;
	}
	
	// Set the y value
	public void setY(int y) { 
		this.y = y;
	}
	
	// Set the width value
	public void setWidth(int width) { 
		this.width = width;
	}
	
	// Set the height value
	public void setHeight(int height) { 
		this.height = height;
	}
	

	// Return the x value
	public int getX() { 
		return this.x;
	}
	
	// Return the y value
	public int getY() { 
		return this.y;
	}
	
	// Return the width value
	public int getWidth() { 
		return this.width;
	}
	
	// Return the height value
	public int getHeight() { 
		return this.height;
	}
	
	// New rectangle constructor
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
