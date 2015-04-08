package simpleslickgame;

import java.util.Observable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public abstract class TVShape extends Observable{

	TVBlock[][] blocks;
	GameContainer gc;
	Color colour;
	int gridLeftRow;
	int gridLeftCol;
	int gridRightRow;
	int gridRightCol;
	
	
	public TVShape(GameContainer gc, int[][] blocks, Color colour){
		// Set necessary fields
		this.blocks = new TVBlock[blocks.length][blocks[0].length];
		this.gc = gc;
		this.colour = colour;
		
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
					
					if(col == 3){
						cur.moveRight();
						cur.moveRight();
					}
					
					for(int i = 0; i < row; i++){
						cur.moveDown();
					}
				}
			}
		}
		
		// Determine grid row and column
		int[] bottomLeft = findBottomLeft(this.blocks);
		int[] bottomRight = findBottomRight(this.blocks);
		
		gridLeftRow = bottomLeft[0];
		gridLeftCol = 4 + bottomLeft[1];
		
		gridRightRow = bottomRight[0];
		gridRightCol = 4 + bottomRight[1];
		
		System.out.println(gridLeftRow);
		System.out.println(gridLeftCol);
		
		System.out.println(gridRightRow);
		System.out.println(gridRightCol);
	}
	
	// Rotates the shape counterclockwise
	public void rotateLeft(){
		rotateRight();
		rotateRight();
		rotateRight();
	}
	
	// Rotates the shape clockwise
	public void rotateRight(){
		// Rotate blocks in array
		TVBlock[][] newArray = new TVBlock[4][4];
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				newArray[row][col] = blocks[4 - col - 1][row];
				
				if(blocks[4 - col - 1][row] != null){
					int moveX = col - row;
					int moveY = row - (4 - col - 1);
					
					if(moveX > 0){
						for(int i = 0; i < moveX; i++)
							newArray[row][col].moveRight();
					}
					else if(moveX < 0){
						for(int i = moveX; i < 0; i++)
							newArray[row][col].moveLeft();
					}
					
					if(moveY > 0){
						for(int i = 0; i < moveY; i++)
							newArray[row][col].moveDown();
					}
					else if(moveY < 0){
						for(int i = moveY; i < 0; i++)
							newArray[row][col].moveUp();
					}
				}
				
			}
		}
		TVBlock[][] oldArray = blocks.clone();
		blocks = newArray;
		
		fixGridLocation(oldArray);
	}
	
	// Moves the shape down one rown
	public void moveDown(){
		
		if(gridLeftRow == 21 || gridRightRow == 21)
			return;
		
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveDown();
				}
			}
		}
		gridLeftRow += 1;
		gridRightRow += 1;
		this.displayGridValues();
	}
	
	// Moves the shape left one column
	public void moveLeft(){
		if(gridLeftCol == 0 || gridRightCol == 0)
			return;
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveLeft();
				}
			}
		}
		gridLeftCol -= 1;
		gridRightCol -= 1;
		this.displayGridValues();
	}
	
	// Moves the shape right one column
	public void moveRight(){
		if(gridLeftCol == 9 || gridRightCol == 9)
			return;
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveRight();
				}
			}
		}
		gridLeftCol += 1;
		gridRightCol += 1;
		this.displayGridValues();
	}
	
	public TVBlock[][] getBlocks(){
		return blocks;
	}
	
	// Checks if any block collides with the grid
	public boolean checkCollision(TVBlock[][] grid){
		// For each block, check if it intersects with a block on the grid
		if(gridLeftRow == 20)
			return true;
		
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
	
	private void fixGridLocation(TVBlock[][] oldblocks){
		int[] topDifference = findLeftDifference(oldblocks);
		
		gridLeftRow += topDifference[0];
		gridLeftCol += topDifference[1];
		
		int[] botDifference = findRightDifference(oldblocks);
		
		gridRightRow += botDifference[0];
		gridRightCol += botDifference[1];
		
		
	}
	
	private int[] findLeftDifference(TVBlock[][] oldblocks){
		
		int[] oldblockBottomLeft = findBottomLeft(oldblocks);
		int oldRow = oldblockBottomLeft[0];
		int oldCol = oldblockBottomLeft[1];
		
		int[] newblockTopLeft = findBottomLeft(blocks);
		int newRow = newblockTopLeft[0];
		int newCol = newblockTopLeft[1];
		
		return new int[]{newRow-oldRow, newCol-oldCol};
	}
	
	private int[] findRightDifference(TVBlock[][] oldblocks){
		
		int[] oldblockTopLeft = findBottomRight(oldblocks);
		int oldRow = oldblockTopLeft[0];
		int oldCol = oldblockTopLeft[1];
		
		int[] newblockTopLeft = findBottomRight(blocks);
		int newRow = newblockTopLeft[0];
		int newCol = newblockTopLeft[1];
		
		return new int[]{newRow-oldRow, newCol-oldCol};
	}
	
	private int[] findBottomLeft(TVBlock[][] blocks){
		for(int r = 3; r >= 0; r--){
			for(int c = 0; c < 4 ; c++){
				if(blocks[r][c] != null){
					//Check if the selected block is alone on its line
					boolean alone = true;
					for(int i = 0; i < 4; i++){
						if (i != c && blocks[r][i] != null)
							alone = false;
					}
					
					//If its alone and on the left side, or not alone return
					if(alone){
						if(c == 0 || c == 1)
							return new int[]{r, c};

					} else {
						return new int[]{r, c};
					}
				}
			}
		}
		return null;
	}
	
	private int[] findBottomRight(TVBlock[][] blocks){
		for(int r = 3; r >= 0; r--){
			for(int c = 3; c >= 0 ; c--){
				if(blocks[r][c] != null){
					//Check if the selected block is alone on its line
					boolean alone = true;
					for(int i = 0; i < 4; i++){
						if (i != c && blocks[r][i] != null)
							alone = false;
					}
					
					//If its alone and on the right side, or not alone return
					if(alone){
						if(c == 2 || c == 3)
							return new int[]{r, c};

					} else {
						return new int[]{r, c};
					}
				}
			}
		}
		return null;
	}
	
	private void displayGridValues(){
		System.out.println("Top values");
		System.out.println(gridLeftRow);
		System.out.println(gridLeftCol);
		System.out.println("Bot values");
		System.out.println(gridRightRow);
		System.out.println(gridRightCol);
		System.out.println();
	}
}
