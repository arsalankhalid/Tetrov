package simpleslickgame;

import java.util.ArrayList;
import java.util.Iterator;


public class TVGrid{
	
	private TVBlock[][] gameboard;
	private ArrayList<Integer[]> currentShape;
	
	public TVGrid()
	{
		gameboard = new TVBlock[22][10];
		currentShape = new ArrayList<Integer[]>();
	}
	
	public void addShapetoTop(TVShape shape){
		
		int[] botRightBlock = shape.findBottomRight(shape.blocks);
		int[] botRightCoord = shape.getBottomRightCoord();
		TVBlock[][] blocks = shape.getBlocks();
		int i = botRightBlock[0];
		int j = botRightBlock[1];
		currentShape.clear();
		for(int r = botRightCoord[0]; r >= botRightCoord[0] - 4; r--){
			for(int c = botRightCoord[1]; c >= botRightCoord[1] - 4; c--){
				if(i >= 0 && j >= 0 && blocks[i][j] != null){
					gameboard[r][c] = blocks[i][j];
					currentShape.add(new Integer[]{r, c});
				}
				System.out.println("i: " + i + " j: " + j);
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
	}
	
	public void updateCurrentShape(TVShape shape){
		for(Iterator<Integer[]> it = currentShape.iterator(); it.hasNext();){
			Integer[] cur = it.next();
			gameboard[cur[0]][cur[1]] = null;
		}
		int[] botRightBlock = shape.findBottomRight(shape.blocks);
		int[] botRightCoord = shape.getBottomRightCoord();
		TVBlock[][] blocks = shape.getBlocks();
		int i = botRightBlock[0];
		int j = botRightBlock[1];
		currentShape.clear();
		for(int r = botRightCoord[0]; r >= botRightCoord[0] - 4; r--){
			for(int c = botRightCoord[1]; c >= botRightCoord[1] - 4; c--){
				if(i >= 0 && j >= 0 && blocks[i][j] != null){
					gameboard[r][c] = blocks[i][j];
					currentShape.add(new Integer[]{r, c});
				}
				System.out.println("i: " + i + " j: " + j);
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
	}
	
	public void removeBlock(int row, int col){
		gameboard[row][col] = null;
	}
	
	public void moveRowDown(int row){
		
		for(int r = row; r >= 0 ; r--)
		{
			for(int c = 10 ; c >= 0; c--)
			{
				gameboard[r+1][c] = gameboard[r][c];
				gameboard[r+1][c].moveDown();
			}
		}
		
	}
	
	public void moveBlockDown(TVBlock block){
		block.moveDown();
	}
	
	public void isCollided(TVShape shape){
		
	}
	
	public void drawBlocks(){
		
	}
	
	public TVBlock[][] collisionCandidate()
	{
		TVBlock[][] GameboardTemp = gameboard.clone();
		
		for(Iterator<Integer[]> it = currentShape.iterator(); it.hasNext();){
			Integer[] cur = it.next();
			GameboardTemp[cur[0]][cur[1]] = null;
		}
		return GameboardTemp;
	}
	
	public void checkGameboard()
	{
		int counter = 0;
		for(int i = 0; i < 22;i++)
		{
			for(int j =0;j<10;j++)
			{
				if(gameboard[i][j] != null)
				{
				   counter++;	
				}
			}
			if(counter == 10)
			{
				for(int t =0 ; t<10;t++)
				{
					gameboard[i][t] = null;
				}
				moveRowDown(i);
			}
			counter = 0;
		}
	}
	
	public void displayBlock()
	{
		for(int e = 0; e < 22; e ++)
		{
		   for(int t =0; t < 10; t++)
		 	{
				if(gameboard[e][t] != null)
				{
					System.out.print("1");
				}else{
					System.out.print("0");
				}
			}
		   System.out.println();
		}
	}
}
