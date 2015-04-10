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
	int row = 0;
	int col = 0;
	
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
	
	// Draws the block on the canvas
	public void drawBlock(){
		graphics.setColor(colour);
		graphics.fill(this);
		graphics.setColor(Color.white);
		graphics.draw(this);
	}
	
	// Checks if this block collides with any in the array given
	public boolean checkCollision(TVBlock[][] grid){
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[row].length; col++){
				if(grid[row][col] != null && grid[row][col].intersects(this)){
					if((row -1 == this.row && col -1 == this.col) || (row -1 == this.row && col +1 == this.col)){
						
					}
					else
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
	
	public TVBlock getCopy(){
		TVBlock tempBlock = new TVBlock(gc, colour);
		
		tempBlock.setX(this.getX());
		tempBlock.setY(this.getY());
		
		return tempBlock;
	}
	
	
	
}
