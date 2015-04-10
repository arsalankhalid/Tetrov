package simpleslickgame;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import simpleslickgame.TVTick;
public class TVPlayState extends TVGameState implements Observer { 
	
	TVGrid grid;
	GameContainer gc;
	TVTick tick;
	TVShape currShape;
	TVShapeFactory shapeFactory;
	Graphics graphics;
	Color colour;
	
	int score = 0;
	int level = 0;
	
	public TVPlayState(TVInvoker i, TVGrid grid) {
		super(i);
		this.grid = grid;
		this.gc = i.gc;
		this.tick = new TVTick(1000);
		this.shapeFactory = new TVShapeFactory(gc);
		this.currShape = shapeFactory.getRandomShape();
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		score = 0;
		this.graphics= gc.getGraphics();
		
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
		manageScoreAndLevel();
		
	}
	
	void manageScoreAndLevel() {
		//score
		graphics.setColor(Color.white);
		graphics.drawString("Score: ", 10, 10);
		graphics.setColor(Color.green);
		graphics.drawString(Integer.toString(score), 65, 10);
		
		//level
		graphics.setColor(Color.white);
		graphics.drawString("Level: ", 200, 10);
		graphics.setColor(Color.red);
		graphics.drawString(Integer.toString(level), 255, 10);
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
		
		if(num == -1){
			invoker.setGameState(invoker.getLostState());
		}
		
		if(num > 0){ 
			score += num * 10;
			if(score%10 == 0) {
				if(level == 9){
					// set state to win or something, cuz initially speed set to 1000ms, then get overflow if over
				}
				tick.tick -= 100;
				level++;
			}
		}
		
		currShape = shapeFactory.getRandomShape();
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		
	}

}
