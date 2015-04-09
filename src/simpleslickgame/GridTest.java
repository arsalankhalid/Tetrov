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

	protected GameContainer gc;
    public TVGrid grid = new TVGrid();
	TVShape shape = new TVLShape(gc);

	
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
}

