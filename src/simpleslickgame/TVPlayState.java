package simpleslickgame;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.GameContainer;

import simpleslickgame.TVTick;
public class TVPlayState extends TVGameState implements Observer { 
	
	TVGrid grid;
	GameContainer gameContainer;
	TVTick tick;
	TVInvoker invoker;
	TVShape currShape;
	
	public TVPlayState(TVInvoker i) {
		super(i);
		this.grid = new TVGrid();
		this.gameContainer = i.gc;
		this.tick = new TVTick(100, 1000);
		this.invoker = i;
		
		
	} 

	@Override
	void pressLeft() {
		
		
	}

	@Override
	void pressRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void pressDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void pressA() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void pressB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void pressPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void renderGameboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateGameboard(TVTick step) { 
		// number of milliseconds %1000 move down
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
