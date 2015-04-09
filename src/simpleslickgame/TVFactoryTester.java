package simpleslickgame;

import org.newdawn.slick.GameContainer;

public class TVFactoryTester {
	public static void main(String[] args) {
		//initialize gc - extend basic game 
		GameContainer gc = null;
		TVShapeFactory shapeFactory = new TVShapeFactory(gc);
		
		//Create set of Shapes
		TVShape shape1 = shapeFactory.createShape("TVIShape");
		TVShape shape2 = shapeFactory.createShape("TVLShape");
		
	}
}
