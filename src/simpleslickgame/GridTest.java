package simpleslickgame;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GridTest extends BasicGame {
	public GridTest(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public TVGrid grid = new TVGrid();
	public TVShape shape;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
	    grid = new TVGrid();
		shape = new TVLShape(gc);
		grid.addShapetoTop(shape);
		grid.displayBlock();
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		shape.drawShape();
		
	}
	
}

