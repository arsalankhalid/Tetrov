package simpleslickgame;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class TVPauseState extends TVGameState implements Observer { 
	
	TVGrid grid;
	Graphics graphics;
	
	public TVPauseState(TVInvoker i, TVGrid grid) {
		super(i);
		this.grid = grid;
		graphics = i.gc.getGraphics();
	} 

	@Override
	void pressLeft() {

	}

	@Override
	void pressRight() {

	}

	@Override
	void pressDown() {

	}

	@Override
	void pressA() {

	}

	@Override
	void pressB() {

	}

	@Override
	void pressPause() {
		invoker.setGameState(invoker.getPlayState());
	}

	@Override
	void renderGameboard() {
		grid.drawBlocks();
		graphics.setColor(Color.white);
		graphics.drawString("PAUSED", 115, 320);
	}

	@Override
	void updateGameboard(int i) { 

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		// ccast o to tvshape
		// this update is for collision
		
		
	}

}