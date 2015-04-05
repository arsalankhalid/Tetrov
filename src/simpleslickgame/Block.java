package simpleslickgame;

import org.newdawn.slick.geom.Rectangle;

public class Block extends Rectangle{

	private static final long serialVersionUID = 1L;

	public Block() {
		super(0, 0, 50, 50);
	}
	
	public void moveDown(){
		//System.out.println(this.getY());
		this.setY(getY()+10);
	}
	
}
