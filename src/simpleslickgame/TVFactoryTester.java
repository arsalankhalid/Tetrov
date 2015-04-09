package simpleslickgame;

public class TVFactoryTester {
	public static void main(String[] args) {
		TVShapeFactory shapeFactory = new TVShapeFactory();
		
		//Create set of Shapes
		TVShape shape1 = shapeFactory.createShape("TVIShape");
		TVShape shape2 = shapeFactory.createShape("TVLShape");
	}
}
