package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class GridTest {
	
	protected TVBlock[][] blocks;
	protected GameContainer gc;
    public TVGrid grid;
	
	
	TVLShape shape = new TVLShape(gc);
	
	grid = new TVGrid();
	
	grid.addShapetoTop(shape);
	
}
