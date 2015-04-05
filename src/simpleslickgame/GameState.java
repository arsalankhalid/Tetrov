package simpleslickgame;

public abstract class GameState {

	Invoker invoker;
	
	public GameState(Invoker i){
		invoker = i;
	}
	
	abstract void pressLeft();
	abstract void pressRight();
	abstract void pressDown();
	abstract void pressA();
	abstract void pressB();
	abstract void pressPause();
	abstract void renderGameboard();
	abstract void updateGameboard(int i);
	
	
	
}
