package simpleslickgame;

import java.util.Observable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public abstract class TVShape extends Observable{
	boolean display = false;
	
	protected TVBlock[][] blocks;
	protected GameContainer gc;
	protected Color colour;
	
	protected int gridLeftRow;
	protected int gridLeftCol;
	protected int gridRightRow;
	protected int gridRightCol;

	// 0 == normal direction
	// 1 == rotated right
	// 2 = upside down
	// 3 = rotated left
	
	protected int direction;

	
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
		
		direction = 0;

	}
	
	// Rotates the shape counterclockwise
	public void rotateLeft(TVBlock[][] grid){
		// Rotate blocks in array
		TVBlock[][] newArray = new TVBlock[4][4];
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				newArray[row][col] = blocks[col][4 - row - 1];
				
				if(blocks[col][4 - row - 1] != null){
					int moveX = col - (4 - row - 1);
					int moveY = row - col;
					
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
		
		int newDirection = direction;
		
		if(newDirection == 0){
			newDirection = 3;
		}
		else{
			 newDirection--;
		}
		
		if(!fixGridLocation(oldArray, newDirection)){
			undoRotation(false);
		}
		
		if(this.checkCollision(grid)){
			undoRotation(false);
			this.setChanged();
			this.notifyObservers();
		}
		

		direction = newDirection;
		
		System.out.println("Direction is:" + direction);
		displayBlocks();
	}
	
	// Rotates the shape clockwise
	public void rotateRight(TVBlock[][] grid){
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
		
		int newDirection = direction;
		if(newDirection == 3){
			newDirection = 0;
		}else{
			newDirection++;
		}
		
		
		if(!fixGridLocation(oldArray, newDirection)){
			undoRotation(true);
		}
		if(this.checkCollision(grid)){
			undoRotation(true);
			this.setChanged();
			this.notifyObservers();
		}
		
		direction = newDirection;
		
		System.out.println("Direction is:" + direction);
		displayBlocks();
	}
	
	// Moves the shape down one rown
	public synchronized void moveDown(TVBlock[][] grid){
		
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveDown();
				}
			}
		}
		
		gridLeftRow += 1;
		gridRightRow += 1;
		
		if(this.checkCollision(grid)){
			moveUp();
			gridLeftRow -= 1;
			gridRightRow -= 1;
			this.setChanged();
			this.notifyObservers();
		}
	
		this.displayGridValues();
	}
	
	// Moves the shape left one column
	public synchronized void moveLeft(TVBlock[][] grid){
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
		
		if(this.checkCollision(grid)){
			moveRight();
			gridLeftCol += 1;
			gridRightCol += 1;
		}

		this.displayGridValues();
	}
	
	// Moves the shape right one column
	public synchronized void moveRight(TVBlock[][] grid){
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
		
		if(this.checkCollision(grid)){
			moveLeft();
			gridLeftCol -= 1;
			gridRightCol -= 1;
		}

		this.displayGridValues();
	}
	
	// Checks if any block collides with the grid
	public boolean checkCollision(TVBlock[][] grid){
		// For each block, check if it intersects with a block on the grid
		
		if(gridLeftRow == 22 || gridRightRow == 22){
			return true;
		}
		
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
	
	// Moves without collision
	public void moveUp(){
		for(int i=0;i<4;i++){
			for(int c = 0; c < 4; c++){
				if(blocks[i][c] != null)
					blocks[i][c].moveUp();
			}
		}
	}
	
	// Moves without collision
	private void moveRight(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveRight();
				}
			}
		}
	}
	
	// Moves without collision
	private void moveLeft(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].moveLeft();
				}
			}
		}
	}
	
	// Returns the current block array
	public TVBlock[][] getBlocks(){
		return blocks;
	}
	
	public int[] getBottomLeftCoord(){
		return new int[]{this.gridLeftRow, this.gridLeftCol};
	}
	
	public int[] getBottomRightCoord(){
		
		if(gridLeftRow > gridRightRow){
			return new int[]{this.gridLeftRow, this.gridRightCol};
		}
		else{
			return new int[]{this.gridRightRow, this.gridRightCol};
		}
	}
	
	// Draws the shape on the board
	public void drawShape(){
		for(int row = 0; row < blocks.length; row++){
			for(int col = 0; col < blocks[row].length; col++){
				if(blocks[row][col] != null){
					blocks[row][col].drawBlock();
				}
			}
		}
	}
	
	// Fixes grid locations after rotating
	private boolean fixGridLocation(TVBlock[][] oldblocks, int newDirection){
		
		int[] leftDifference = findLeftDifference(oldblocks);
		int[] rightDifference = findRightDifference(oldblocks);
		
		this.displayGridValues();
		
		if(gridLeftRow + leftDifference[0] > 21 || gridLeftCol + leftDifference[1] > 9 || gridLeftCol + leftDifference[1] < 0){
			return false;
		}
		
		if(gridRightRow + rightDifference[0] > 21 || gridRightCol + rightDifference[1] > 9 || gridRightCol + rightDifference[1] < 0){
			return false;
		}
		
		gridLeftRow += leftDifference[0];
		gridLeftCol += leftDifference[1];
		
		gridRightRow += rightDifference[0];
		gridRightCol += rightDifference[1];
		

		if(this.getClass().getSimpleName().equals("TVTShape")){
			if(newDirection == 2){
				gridRightRow += 1;
				gridLeftRow += 1;
			}
			if(direction == 2){
				gridRightRow -= 1;
				gridLeftRow -= 1;
			}
		}
		
		return true;
	}
	
	// For fixing grid location
	private int[] findLeftDifference(TVBlock[][] oldblocks){
		
		int[] oldblockBottomLeft = findBottomLeft(oldblocks);
		int oldRow = oldblockBottomLeft[0];
		int oldCol = oldblockBottomLeft[1];
		
		int[] newblockTopLeft = findBottomLeft(blocks);
		int newRow = newblockTopLeft[0];
		int newCol = newblockTopLeft[1];
		
		return new int[]{newRow-oldRow, newCol-oldCol};
	}
	
	// For fixing grid location
	private int[] findRightDifference(TVBlock[][] oldblocks){
		
		int[] oldblockRight = findBottomRight(oldblocks);
		int oldRow = oldblockRight[0];
		int oldCol = oldblockRight[1];
		
		int[] newblockRight = findBottomRight(blocks);
		int newRow = newblockRight[0];
		int newCol = newblockRight[1];
		
		return new int[]{newRow-oldRow, newCol-oldCol};
	}
	
	// For fixing grid location
	public int[] findBottomLeft(TVBlock[][] blocks){
		for(int c = 0; c < 4; c++){
			for(int r = 3; r >= 0 ; r--){
				if(blocks[r][c] != null){
					return new int[]{r, c};
				}
			}
		}
		return null;
	}
	
	// For fixing grid location
	public int[] findBottomRight(TVBlock[][] blocks){
		for(int c = 3; c >= 0; c--){
			for(int r = 3; r >= 0 ; r--){
				if(blocks[r][c] != null){
					
					//if(this.getClass().getSimpleName() == "TVTShape" && direction == 2)
					return new int[]{r, c};
				}
			}
		}
		return null;
	}
	
	// Undoes an invalid roation
	private void undoRotation (boolean rotatedRight){
		if(rotatedRight)
			rotateLeft(true);
		else
			rotateRight(true);
	}
	
	// Rotates left without influencing grid location
	private void rotateLeft(boolean flag){
		if(!flag)
			return;
		
		// Rotate blocks in array
		TVBlock[][] newArray = new TVBlock[4][4];
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				newArray[row][col] = blocks[col][4 - row - 1];
				
				if(blocks[col][4 - row - 1] != null){
					int moveX = col - (4 - row - 1);
					int moveY = row - col;
					
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
		blocks = newArray;
		displayBlocks();
	}
	
	// Rotates right without influencing grid location
	private void rotateRight(boolean flag){
		if(!flag)
			return;
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
		blocks = newArray;
		displayBlocks();
	}
	
	// Returns the location of the top left block in the blocks array
	public int[] getTopLeftBlock(){
		for(int r = 0; r < 4; r++){
			for(int c = 0; c < 4; c++){
				if(blocks[r][c] != null)
					return new int[]{r, c};
			}
		}
		return null;
	}
	
	// Displays grid values (for testing)
	private void displayGridValues(){
		if(display){
			System.out.println("Left row: " + gridLeftRow);
			System.out.println("Left col: " + gridLeftCol);
			System.out.println("Right row: " + gridRightRow);
			System.out.println("Right col: " + gridRightCol);
			System.out.println();
		}
		displayBlocks();
	}
	
	// Displays block array (for testing)
	private void displayBlocks(){
		if(display){
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++){
					if(blocks[i][j] != null)
						System.out.print("1");
					else
						System.out.print("0");
				}
				System.out.println();
			}
		}
	}
	
	public TVShape copyShape(){
		
		TVShapeFactory f = new TVShapeFactory(gc);
		TVShape tempShape = f.createShape(this.getClass().getSimpleName());
		
		System.out.println(this.getClass().getSimpleName());
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(blocks[i][j] != null)
					tempShape.blocks[i][j] = blocks[i][j].getCopy();
				else
					tempShape.blocks[i][j] = null;
			}
		}
		
		tempShape.gridLeftCol = gridLeftCol;
		tempShape.gridLeftRow = gridLeftRow;
		tempShape.gridRightCol = gridRightCol;
		tempShape.gridRightRow = gridRightRow;
		
		return tempShape;
	}
}