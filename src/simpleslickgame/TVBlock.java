package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class TVBlock extends Rectangle{

	private static final long serialVersionUID = 1L;

	Graphics graphics;
	GameContainer gc;
	Color colour;
	boolean visible = true;
	
	public TVBlock(GameContainer gc, Color colour) {
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
		this.colour = colour;
	}
	
	public TVBlock(GameContainer gc, Color colour, boolean visibility) {
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
		this.colour = colour;
		this.visible = visibility;
	}
	
	// Draws the block on the canvas
	public void drawBlock(){
		if(!visible)
			return;
		
		graphics.setColor(colour);
		graphics.fill(this);
		graphics.setColor(Color.white);
		graphics.draw(this);
	}
	
	// Checks if this block collides with any in the array given
	public boolean checkCollision(TVBlock[][] grid){
		if(!visible)
			return false;
		
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[row].length; col++){
				if(grid[row][col] != null && grid[row][col].intersects(this)){
					return true;
				}
			}
		}
		return false;
	}
	
	// Moves a block left in increments of its own width
	public void moveUp(){
		this.setY(this.getY() - this.getHeight());
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
