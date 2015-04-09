package simpleslickgame;


public class TVGrid{
	
	private TVBlock[][] gameboard;
	
	public TVGrid()
	{
		gameboard = new TVBlock[22][10];
	}
	
	public void addShapetoTop(TVShape shape){
		
		int[] botRightBlock = shape.findBottomRight(shape.blocks);
		int[] botRightCoord = shape.getBottomRightCoord();
		TVBlock[][] blocks = shape.getBlocks();
		int i = botRightBlock[0];
		int j = botRightBlock[1];
		
		for(int r = botRightCoord[0]; r >= 0; r--){
			for(int c = botRightCoord[1]; c >= 0; c--){
				if(i > 0 && j > 0 && blocks[i][j] != null){
					gameboard[r][c] = blocks[i][j];
					displayBlock();
				}
				j--;
			}
			i--;
		}
	}
	
	public void updateCurrentShape(TVShape shape){}
	
	public void removeBlock(){}
	
	public void moveRowDown(){}
	
	public void moveBlockDown(TVBlock tvblock){}
	
	public void isCollided(TVShape shape){}
	
	public void displayBlock()
	{
		for(int e = 0; e < 4; e ++)
		{
		   for(int t =0; t < 4; t++)
		 	{
				if(gameboard[e][t] != null)
				{
					System.out.println("1");
				}else{
					System.out.println("0");
				}
			}
		}
	}
}
