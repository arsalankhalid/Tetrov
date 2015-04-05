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
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(input.isKeyPressed(Input.KEY_A))
			currentState.pressLeft();
		
		if(input.isKeyPressed(Input.KEY_D))
			currentState.pressRight();
		
		if(input.isKeyPressed(Input.KEY_S))
			currentState.pressDown();
		
		if(input.isKeyPressed(Input.KEY_LEFT))
			currentState.pressA();
		
		if(input.isKeyPressed(Input.KEY_RIGHT))
			currentState.pressB();
		
		if(input.isKeyPressed(Input.KEY_SPACE))
			currentState.pressPause();
		
		currentState.updateGameboard(i);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		currentState.renderGameboard();
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
			appgc.setDisplayMode(280, 640, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Invoker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}