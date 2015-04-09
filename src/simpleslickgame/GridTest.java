package simpleslickgame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
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
<<<<<<< HEAD
		grid.displayBlock();
=======
		shape.rotateLeft();
		grid.updateCurrentShape(shape);
		shape.moveLeft();
		grid.updateCurrentShape(shape);
		shape.moveLeft();
		grid.updateCurrentShape(shape);
		shape.moveRight();
		grid.updateCurrentShape(shape);
		shape.moveDown();
		grid.updateCurrentShape(shape);
		shape.moveDown();
		grid.updateCurrentShape(shape);
		//grid.displayBlock();
>>>>>>> fb927ae3f0423921cd043a6b29614df6b49a8639
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		shape.drawShape();
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new GridTest("GridTest"));
			appgc.setDisplayMode(280, 640, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(TVInvoker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

