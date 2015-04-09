package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class GridTest {
	protected GameContainer gc;
    public TVGrid grid = new TVGrid();
	
	TVShape shape = new TVLShape(gc);
	grid.addShapetoTop(shape);	
}

