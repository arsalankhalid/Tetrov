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
		this.invoker = i;
		this.tick = new TVTick(1000);
		this.grid = new TVGrid();
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
	void updateGameboard(int i) { 
		// number of milliseconds %1000 move down
		// TODO Auto-generated method stub
		if(tick.update(i)){
			// do logic that needs to happen
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
