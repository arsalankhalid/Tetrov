package simpleslickgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tester extends BasicGame
{ 
	int totalms = 0;
	Input input;
	TVZShape l;
	
	public Tester()
	{
		super("Tester");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		input = gc.getInput();
		l = new TVZShape(gc);
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		totalms+=i;
		
		if(input.isKeyPressed(Input.KEY_A)){
			l.moveLeft();
		}
		
		if(input.isKeyPressed(Input.KEY_D)){
			l.moveRight();
		}
		
		if(input.isKeyPressed(Input.KEY_S)){
			l.moveDown();
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)){
			l.rotateLeft();
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT)){
			l.rotateRight();
		}
		
		if(input.isKeyPressed(Input.KEY_SPACE)){
			
		}
		
		if(totalms % 1000 == 0){
			l.moveDown();
			totalms = 0;
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		l.drawShape();
	}
	

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Tester());
			appgc.setDisplayMode(280, 640, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Invoker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}