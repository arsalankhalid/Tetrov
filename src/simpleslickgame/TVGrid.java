package simpleslickgame;

import java.util.ArrayList;
import java.util.Iterator;

public class TVGrid{
	
	private TVBlock[][] gameboard;
	private ArrayList<Integer[]> currentShape;
	private ArrayList<TVShape> newshapes;
	
	public TVGrid()
	{
		gameboard = new TVBlock[22][10];
		currentShape = new ArrayList<Integer[]>();
		newshapes = new ArrayList<TVShape>();
	}
	
	public synchronized void addShapetoTop(TVShape shape){

		int[] botRightBlock = shape.findBottomRight(shape.blocks);
		
		int[] lowestBlock = new int[2];
		
		int[] botRightCoord = shape.getBottomRightCoord();
		TVBlock[][] blocks = shape.getBlocks();
		
		for(int r = 3; r >= 0; r--){
			for(int c = 3; c >= 0; c--){
				if(blocks[r][c] != null){
					lowestBlock[0] = r;
					lowestBlock[1] = c;
					r = -1;
					c = -1;
				}
			}
		}
		
		System.out.println(botRightBlock[0] + "," + botRightBlock[1]);
		System.out.println(lowestBlock[0] + "," + lowestBlock[1]);
		
		if (botRightBlock[0] == lowestBlock[0] && botRightBlock[1] == lowestBlock[1]){
		}
		else{
			botRightBlock[0] = lowestBlock[0];
		}
		
		int i = botRightBlock[0];
		int j = botRightBlock[1];
	
		currentShape.clear();
		for(int r = botRightCoord[0]; r >= botRightCoord[0] - 4; r--){
			//j = getRightMostBlock(blocks, i);
			for(int c = botRightCoord[1]; c >= botRightCoord[1] - 4; c--){
				if(i >= 0 && j >= 0 && blocks[i][j] != null){
					gameboard[r][c] = blocks[i][j];
					currentShape.add(new Integer[]{r, c});
					//System.out.println("i: " + i + " j: " + j);
				}
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
		

	}
	
	public synchronized void updateCurrentShape(TVShape shape){
		for(Iterator<Integer[]> it = currentShape.iterator(); it.hasNext();){
			Integer[] cur = it.next();
			gameboard[cur[0]][cur[1]] = null;
		}
		currentShape.clear();
		
		int[] botRightBlock = shape.findBottomRight(shape.blocks);
		
		int[] lowestBlock = new int[2];
		
		int[] botRightCoord = shape.getBottomRightCoord();
		TVBlock[][] blocks = shape.getBlocks();
		
		for(int r = 3; r >= 0; r--){
			for(int c = 3; c >= 0; c--){
				if(blocks[r][c] != null){
					lowestBlock[0] = r;
					lowestBlock[1] = c;
					r = -1;
					c = -1;
				}
			}
		}
		
		System.out.println(botRightBlock[0] + "," + botRightBlock[1]);
		System.out.println(lowestBlock[0] + "," + lowestBlock[1]);
		
		if (botRightBlock[0] == lowestBlock[0] && botRightBlock[1] == lowestBlock[1]){
		}
		else{
			botRightBlock[0] = lowestBlock[0];
		}
		
		int i = botRightBlock[0];
		int j = botRightBlock[1];
	
		
		for(int r = botRightCoord[0]; r >= botRightCoord[0] - 4; r--){
			for(int c = botRightCoord[1]; c >= botRightCoord[1] - 4; c--){
				if(i >= 0 && j >= 0 && blocks[i][j] != null){
					gameboard[r][c] = blocks[i][j];
					currentShape.add(new Integer[]{r, c});
					//System.out.println("i: " + i + " j: " + j);
				}
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
		

		
	}
	
	public synchronized void removeBlock(int row, int col){
		gameboard[row][col] = null;
	}
	
	public synchronized void moveRowDown(int row){
		
		for(int r = row-1; r >= 0 ; r--)
		{
			for(int c = 9 ; c >= 0; c--)
			{
				gameboard[r+1][c] = gameboard[r][c];
				if(gameboard[r+1][c] != null){
					gameboard[r+1][c].moveDown();
				}
			}
		}
		
	}
	
	public synchronized void moveBlockDown(TVBlock block){
		block.moveDown();
		
	}
	
	public synchronized TVCollisionInfo isCollided(TVShape shape){
		TVShape cloneShape = shape.copyShape();
		newshapes.add(cloneShape);
		this.updateCurrentShape(cloneShape);
		if(currentShape.get(3)[0] == 1){
			TVCollisionInfo lostCollisionInfo = new TVCollisionInfo();
			lostCollisionInfo.lost = true;
			return lostCollisionInfo;
		}
		return checkGameboard();
	}
	
	public synchronized void drawBlocks(){
		for(int i =0; i < 22; i ++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(gameboard[i][j] != null)
				{
					gameboard[i][j].drawBlock();
				}
			}
		}
		
	}
	
	public synchronized TVBlock[][] collisionCandidate()
	{
		TVBlock[][] GameboardTemp = gameboard.clone();
		
		for(Iterator<Integer[]> it = currentShape.iterator(); it.hasNext();){
			Integer[] cur = it.next();
			GameboardTemp[cur[0]][cur[1]] = null;
		}
		return GameboardTemp;
	}
	
	public synchronized TVCollisionInfo checkGameboard()
	{
		int counter = 0;
		TVCollisionInfo totalRowsRemoved = new TVCollisionInfo();
		totalRowsRemoved.collisionRawInfoArr = new ArrayList<Integer>();
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
				totalRowsRemoved.collisionRawInfoArr.add(Integer.valueOf(i));
				moveRowDown(i);
				i = 0;
			}
			counter = 0;
		}
		return totalRowsRemoved;
	}
	
	public synchronized void displayBlock()
	{
		boolean display = true;
		if(display){
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
}
