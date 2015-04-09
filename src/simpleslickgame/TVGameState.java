package simpleslickgame;
import simpleslickgame.TVTick;

public abstract class TVGameState {

	TVInvoker invoker;
	
	public TVGameState(TVInvoker i){
		invoker = i;
	}
	
	abstract void pressLeft();
	abstract void pressRight();
	abstract void pressDown();
	abstract void pressA();
	abstract void pressB();
	abstract void pressPause();
	abstract void renderGameboard();
	abstract void updateGameboard(TVTick step);
}
