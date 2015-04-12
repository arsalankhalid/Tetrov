package simpleslickgame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TVInvoker extends BasicGame
{ 
	Input input;
	TVGameState playState;
	TVGameState pauseState;
	TVGameState lostState;
	TVGameState currentState;
	GameContainer gc;
	
	public TVInvoker()
	{
		super("Tetrov");

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		input = gc.getInput();
		this.gc = gc;
		
		TVGrid grid = new TVGrid();

		playState = new TVPlayState(this, grid);
		pauseState = new TVPauseState(this, grid);
		lostState = new TVLostState(this, grid);
		currentState = playState;	
		
		
	}
	
	public void newGame(){
		TVGrid grid = new TVGrid();

		playState = new TVPlayState(this, grid);
		pauseState = new TVPauseState(this, grid);
		lostState = new TVLostState(this, grid);
		currentState = playState;	
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

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentState.renderGameboard();
	}
	
	public TVGameState getPlayState(){
		return playState;
	}
	
	public TVGameState getPauseState(){
		return pauseState;
	}
	
	public TVGameState getLostState(){
		return lostState;
	}
	
	public void setGameState(TVGameState s){
		currentState = s;
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new TVInvoker());
			appgc.setDisplayMode(280, 640, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(TVInvoker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}