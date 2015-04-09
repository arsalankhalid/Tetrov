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
	TVShapeFactory shapeFactory;
	
	public TVPlayState(TVInvoker i) {
		super(i);
		this.grid = new TVGrid();
		this.gameContainer = i.gc;
		this.invoker = i;
		this.tick = new TVTick(1000);
		this.grid = new TVGrid();
		this.shapeFactory = new TVShapeFactory();
		this.currShape = shapeFactory.createShape("TVOshape");
		
		//create factory and use factory to create cuurShape
		// since shape is observable, shape.addObserver and pass this
		
	} 

	@Override
	void pressLeft() {
		currShape.moveLeft();
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressRight() {
		currShape.rotateRight();
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressDown() {
		currShape.moveDown();
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressA() {
		currShape.rotateLeft();
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressB() {
		currShape.rotateRight();
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressPause() {
		invoker.setGameState(invoker.getPauseState());
	}

	@Override
	void renderGameboard() {
		
		
	}

	@Override
	void updateGameboard(int i) { 
		// number of milliseconds %1000 move down
		// called every frame
		// TODO Auto-generated method stub
		if(tick.update(i)){
			// do logic that needs to happen
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		// ccast o to tvshape
		// this update is for collision
		
		
	}

}
