package simpleslickgame;

import java.util.Random;
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
	protected TVShape previousShape = null; 
	
	TVShapeFactory(GameContainer gc) {
		this.gc = gc;
	}
	
	public TVShape createShape(String shape) {
		
		if (shape.equals("TVIShape")) {
			tvShape = new TVIShape(gc);
		}
		else if (shape.equals("TVOShape")) {
			tvShape = new TVOShape(gc);
		}
		else if (shape.equals("TVLShape")) {
			tvShape = new TVLShape(gc);
		}
		else if (shape.equals("TVZShape")) {
			tvShape = new TVZShape(gc);
		}
		else if (shape.equals("TVJShape")) {
			tvShape = new TVJShape(gc);
		}
		else if (shape.equals("TVSShape")) {
			tvShape = new TVSShape(gc);
		}
		else if (shape.equals("TVTShape")) {
			tvShape = new TVTShape(gc);
		}
		else {
			System.out.println("Error: invalid type of shape");
		}
		return tvShape;
	}
	
	//method receives 2 shapes and checks if they're the same
	public boolean equals(TVShape tvShape1,TVShape tvShape2) {
		boolean result = true;
		//ensure class type is the same for both obj's 
		if(tvShape1 instanceof TVIShape) {
			
		}
		
		if(tvShape1 instanceof TVOShape) {
			
		}
		
		if(tvShape1 instanceof TVLShape) {
			
		}
		
		if(tvShape1 instanceof TVZShape) {
			
		}
		
		if(tvShape1 instanceof TVJShape) {
			
		}

		if(tvShape1 instanceof TVSShape) {
			
		}
	
		if(tvShape1 instanceof TVTShape) {
			
		}
		
		return result;
	}
	
	/*
	 * method creates a random shape using tetris 
	 * Implements bust head tetris algorithm 
	 */
	public TVShape getRandomShape() {
		
		//create array holding all shape choices
		TVShape[] shapes = new TVShape[7];
		shapes[0] = createShape("TVIShape");
		shapes[1] = createShape("TVOShape");
		shapes[2] = createShape("TVLShape");
		shapes[3] = createShape("TVZShape");
		shapes[4] = createShape("TVJShape");
		shapes[5] = createShape("TVSShape");
		shapes[6] = createShape("TVTShape");
		
		int index = new Random().nextInt(shapes.length);
		previousShape = shapes[index]; 
		
		//don't allow duplicate shapes to be released 
	/*	if(previousShape.equals(shapes[index])) {
			
		} */
	
		return shapes[index];
	}
}