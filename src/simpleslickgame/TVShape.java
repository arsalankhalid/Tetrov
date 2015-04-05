package simpleslickgame;

import java.util.Observable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public abstract class TVShape extends Observable{

	TVBlock[][] blocks;
	GameContainer gc;
	Color colour;
	int gridRow;
	int gridCol;
	
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
		boolean emptyFirstRow = true;
		for(int i = 0; i < 4; i++){
			if(this.blocks[0][i] != null)
				emptyFirstRow = false;
		}
		
		if(emptyFirstRow)
			gridRow = 1;
		else
			gridRow = 0;
		
		int tempCol = 0;
		
		for(int i = 0; i < 4; i++){
			if(this.blocks[gridRow][i] != null){
				tempCol = i;
				i = 4;
			}
		}
		
		gridCol = 4 + tempCol;
		
		System.out.println(gridRow);
		System.out.println(gridCol);
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
		/*
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(blocks[i][j] != null && blocks[i][j].visible){
					System.out.print("1");
				}
				else
					System.out.print("0");
			}
			System.out.println();
		}
		System.out.println();
		*/
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
		gridRow += 1;
		System.out.println(gridRow);
		System.out.println(gridCol);
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
		gridCol -= 1;
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
		gridCol += 1;
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
	
	private void fixGridLocation(TVBlock[][] oldblocks){
		int[] difference = findDifference(oldblocks);
		
		gridRow += difference[0];
		gridCol += difference[1];
	}
	
	private int[] findDifference(TVBlock[][] oldblocks){
		
		int[] oldblockTopLeft = findTopLeft(oldblocks);
		int oldRow = oldblockTopLeft[0];
		int oldCol = oldblockTopLeft[1];
		
		int[] newblockTopLeft = findTopLeft(blocks);
		int newRow = newblockTopLeft[0];
		int newCol = newblockTopLeft[1];
		
		return new int[]{newRow-oldRow, newCol-oldCol};
	}
	
	private int[] findTopLeft(TVBlock[][] blocks){
		for(int r = 0; r < 4; r++){
			for(int c = 0; c < 4 ; c++){
				if(blocks[r][c] != null)
					return new int[]{r, c};
			}
		}
		return null;
	}
}
