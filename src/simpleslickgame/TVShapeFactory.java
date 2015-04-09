package simpleslickgame;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
/*
 * class responsible for creating unique Tetris object's 
 * implements concrete methods
 * Class expects a GameContainer to handle the game environment and create
 * shape for environment 
 */
public class TVShapeFactory {

	protected TVShape tvShape = null;
	protected GameContainer gc;
	
	public TVShape createShape(String shape) {
		if (shape=="TVIShape") {
			tvShape = new TVIShape(gc);
		}
		else if (shape=="TVOShape") {
			tvShape = new TVOShape(gc);
		}
		else if (shape=="TVLShape") {
			tvShape = new TVLShape(gc);
		}
		else if (shape=="TVZShape") {
			tvShape = new TVZShape(gc);
		}
		else if (shape=="TVJShape") {
			tvShape = new TVJShape(gc);
		}
		else if (shape=="TVSShape") {
			tvShape = new TVSShape(gc);
		}
		else if (shape=="TVTShape") {
			tvShape = new TVTShape(gc);
		}
		else {
			System.out.println("Error: invalid type of shape");
		}
		return tvShape;
	}
	
	/*
	 * method creates a random shape using tetris 
	 * Implements bust head tetris algorithm 
	 */
	public TVShape createRandomShape() {
		//create array holding possible choices 
		int[] tiny_bag = new int[7];
		for(int i=0;i<7;i++) {
		
		}
		
		return tvShape;
	}

	
}