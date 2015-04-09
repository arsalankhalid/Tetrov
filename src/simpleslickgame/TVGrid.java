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
<<<<<<< HEAD
=======
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
>>>>>>> 83405386effedcaa1b0cfaf9faeecb0f5ed7e06b
	}
	
	public void removeBlock(){}
	
	public void moveRowDown(){}
	
	public void moveBlockDown(TVBlock tvblock){}
	
	public void isCollided(TVShape shape){}
	
	public void drawBlocks(){
		
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
