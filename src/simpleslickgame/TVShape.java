package simpleslickgame;

import java.util.Arrays;
import java.util.Observable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

public abstract class TVShape extends Observable{

	TVBlock[][] blocks;
	GameContainer gc;
	
	public TVShape(GameContainer gc, int[][] blocks, Color colour){
		// Set necessary fields
		this.blocks = new TVBlock[blocks.length][blocks[0].length];
		this.gc = gc;
		
		// Initialize the blocks array based on the int array sent in
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] == 1){
					this.blocks[row][col] = new TVBlock(gc, colour);
					TVBlock cur = this.blocks[row][col];
					
					for(int i = 0; i < 5; i++){
						cur.moveRight();
					}
					
					if(col == 0){
						cur.moveLeft();
					}
					
					if(col == 2){
						cur.moveRight();
					}
					
					for(int i = 0; i < row; i++){
						cur.moveDown();
					}
					
				}
			}
		}
	}
	
	// Rotates the shape counterclockwise
	public void rotateLeft(){
		
	}
	
	// Rotates the shape clockwise
	public void rotateRight(){
		// Rotate blocks in array
		TVBlock[][] newArray = new TVBlock[4][4];
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				newArray[row][col] = blocks[4 - col - 1][row];
			}
		}
		blocks = newArray;
		
		// Rotate blocks on screen
		float topX = 0;
		float topY = 0;
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				if(blocks[row][col] != null){
					topX = blocks[row][col].getX();
					topY = blocks[row][col].getY();
					row = 4;
					col = 4;
				}
			}
		}
		
		System.out.println(Arrays.deepToString(blocks));
		
		Rectangle temp = new Rectangle(topX, topY, 0, 0);
		

		
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				if(blocks[row][col] != null){
					temp.union(blocks[row][col]);
				}
			}
		}
		
		gc.getGraphics().setColor(Color.white);
		gc.getGraphics().fill(temp);
		
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				if(blocks[row][col] != null){
					blocks[row][col].transform(Transform.createRotateTransform( 90, temp.getCenterX(), temp.getCenterY()));
				}
			}
		}
		
	}
	
	// Moves the shape down one rown
	public void moveDown(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveDown();
				}
			}
		}
	}
	
	// Moves the shape left one column
	public void moveLeft(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveLeft();
				}
			}
		}
	}
	
	// Moves the shape right one column
	public void moveRight(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveRight();
				}
			}
		}
	}
	
	public TVBlock[][] getBlocks(){
		return blocks;
	}
	
	// Checks if any block collides with the grid
	public boolean checkCollision(TVBlock[][] grid){
		// For each block, check if it intersects with a block on the grid
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null && blocks[row][col].checkCollision(grid)){
					//Notifying the observer goes here
					return true;
				}
			}
		}
		return false;
	}
	
	public void drawShape(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].drawBlock();
				}
			}
		}
	}
}
