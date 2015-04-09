package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class TVSShape extends TVShape{

	public TVSShape(GameContainer gc) {
		super(
				gc, 
				new int[][]{
						{0, 0, 0, 0},
						{0, 0, 1, 1},
						{0, 1, 1, 0},
						{0, 0, 0, 0},
				},
				new Color(Color.pink)
		);
	}

}
