package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class TVIShape extends TVShape{

	public TVIShape(GameContainer gc) {
		super(
				gc, 
				
				new int[][]{
						{0, 1, 0, 0},
						{0, 1, 0, 0},
						{0, 1, 0, 0},
						{0, 1, 0, 0},
				},
				
				new Color(Color.yellow)
			);
		
	}

}