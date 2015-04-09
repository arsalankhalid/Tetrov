package simpleslickgame;

import java.util.Random;

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
	public TVShape getRandomShape(GameContainer gc) {
		//create array holding possible choices 
		TVShape[] shapes = new TVShape[7];
		shapes[0] = new TVIShape(gc);
		shapes[1] = new TVJShape(gc);
		shapes[2] = new TVLShape(gc);
		shapes[3] = new TVOShape(gc);
		shapes[4] = new TVTShape(gc);
		shapes[5] = new TVZShape(gc);
		shapes[6] = new TVSShape(gc);
		
		int index = new Random().nextInt(shapes.length);
		return shapes[index];
	}

	
}