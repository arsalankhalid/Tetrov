package simpleslickgame;

import java.util.Observable;
import java.util.Observer;

public class TVPauseState extends TVGameState implements Observer { 
	
	public TVPauseState(TVInvoker i) {
		super(i);
		
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