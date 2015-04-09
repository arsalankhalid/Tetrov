package simpleslickgame;


public class TVGrid{
	
	private TVBlock[][] gameboard;
	
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
				}
				j--;
			}
			i--;
		}
	}
	
	public void updateCurrentShape(TVShape tvshape){}
	
	public void removeBlock(){}
	
	public void moveRowDown(){}
	
	public void moveBlockDown(TVBlock tvblock){}
	
	
	
	

}
