package simpleslickgame;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.GameContainer;


import simpleslickgame.TVTick;
public class TVPlayState extends TVGameState implements Observer { 
	
	TVGrid grid;
	GameContainer gc;
	TVTick tick;
	TVShape currShape;
	TVShapeFactory shapeFactory;
	
	int score = 0;
	
	public TVPlayState(TVInvoker i) {
		super(i);
		this.grid = new TVGrid();
		this.gc = i.gc;
		this.tick = new TVTick(1000);
		this.grid = new TVGrid();
		this.shapeFactory = new TVShapeFactory(gc);
		this.currShape = shapeFactory.createShape("TVZShape");
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		score = 0;
		//create factory and use factory to create cuurShape
		// since shape is observable, shape.addObserver and pass this
	} 

	@Override
	void pressLeft() {
		currShape.moveLeft(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressRight() {
		currShape.moveRight(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressDown() {
		currShape.moveDown(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressA() {
		currShape.rotateLeft(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressB() {
		currShape.rotateRight(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressPause() {
		invoker.setGameState(invoker.getPauseState());
	}

	@Override
	void renderGameboard() {
		currShape.drawShape();
		grid.drawBlocks();
		
	}

	@Override
	void updateGameboard(int i) { 
		// called every single frame
		if(tick.update(i)){
			// do logic that needs to happen
			currShape.moveDown(grid.collisionCandidate());
			grid.updateCurrentShape(currShape);
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		int num = grid.isCollided((TVShape)o);
		
		if(num > 0) score += num;
		if(score == score%10) tick.tick -= 50;
		
		currShape = shapeFactory.getRandomShape();
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		
	}

}
