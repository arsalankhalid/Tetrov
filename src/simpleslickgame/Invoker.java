package simpleslickgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Invoker extends BasicGame
{ 
	Input input;
	GameState playState;
	GameState pauseState;
	GameState lostState;
	GameState currentState;
	Block a = new Block();
	Block b = new Block();
	
	public Invoker()
	{
		super("Tetrov");
		playState = new PlayState(this);
		currentState = playState;	
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		input = gc.getInput();
		b.moveDown();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(input.isKeyDown(Input.KEY_A))
			currentState.pressLeft();
		
		if(input.isKeyDown(Input.KEY_D))
			currentState.pressRight();
		
		if(input.isKeyDown(Input.KEY_S))
			currentState.pressDown();
		
		if(input.isKeyDown(Input.KEY_LEFT))
			currentState.pressA();
		
		if(input.isKeyDown(Input.KEY_RIGHT))
			currentState.pressB();
		
		if(input.isKeyDown(Input.KEY_SPACE))
			currentState.pressPause();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		currentState.updateGameboard();
		g.draw(a);
		g.draw(b);
		
		if(a.intersects(b))
			System.out.println("A intersects B!");
		
	}
	
	public GameState getPlayState(){
		return playState;
	}
	
	public GameState getPauseState(){
		return pauseState;
	}
	
	public GameState getLostState(){
		return lostState;
	}
	
	public void setGameState(GameState s){
		currentState = s;
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Invoker());
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Invoker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}