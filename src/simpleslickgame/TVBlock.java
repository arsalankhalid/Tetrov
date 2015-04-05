package simpleslickgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class TVBlock extends Rectangle{

	private static final long serialVersionUID = 1L;

	Graphics graphics;
	GameContainer gc;
	
	public TVBlock(GameContainer gc) {
		super(0, 0, 50, 50);
		
		// Get height and width of canvas and determine block size
		int height = gc.getHeight();
		int width = gc.getWidth();
		
		// 10 cells across, 22 cells high
		height = height/22;
		width = width/10;
		
		// Set height, width, graphics, and gamecontainer
		setHeight(height);
		setWidth(width);
		
		this.gc = gc;
		this.graphics= gc.getGraphics();
	}
	
	// Draws the block on the canvas
	public void drawBlock(){
		graphics.draw(this);
	}
	
	// Checks if this block collides with any in the array given
	public boolean checkCollision(TVBlock[] blocks){
		for(int i = 0; i < blocks.length; i++){
			if (blocks[i].intersects(this)){
				return true;
			}
		}
		return false;
	}
	
	// Moves a block down in increments of its own height
	public void moveDown(){
		this.setY(this.getY() + this.getHeight());
	}
	
	// Moves a block right in increments of its own width
	public void moveRight(){
		this.setX(this.getX() + this.getWidth());
	}
	
	// Moves a block left in increments of its own width
	public void moveLeft(){
		this.setX(this.getX() - this.getWidth());
	}
	
}
