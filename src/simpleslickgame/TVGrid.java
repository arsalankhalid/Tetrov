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
					System.out.println("i: " + i + " j: " + j);
				}
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
					System.out.println("i: " + i + " j: " + j);
				}
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
		

		
	}
	
	/*
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
				//System.out.println("i: " + i + " j: " + j);
				j--;
			}
			i--;
			j = botRightBlock[1];
		}
		displayBlock();
	}
	*/
	
	private int getRightMostBlock(TVBlock[][] blocks, int i){
		if(i < 0)
			return -1;
		
		for(int c = 3; c >= 0; c--){
			if(blocks[i][c] != null){
				return c;
			}
		}
		
		return -1;
	}
	
	public void removeBlock(int row, int col){
		gameboard[row][col] = null;
	}
	
	public void moveRowDown(int row){
		for(int i=0 ; i < 10; i ++)
		{
		  gameboard[row][i].moveDown();
		}
		
	}
	
	public void moveBlockDown(TVBlock block){
		block.moveDown();
	}
	
	public void isCollided(TVShape shape){
		
	}
	
	public void drawBlocks(){
		
	}
	
	public void displayBlock()
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
