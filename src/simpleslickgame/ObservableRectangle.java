package simpleslickgame;

import java.util.Observable;

import org.newdawn.slick.geom.Rectangle;

public class ObservableRectangle extends Observable {
	Rectangle r;
	
	public ObservableRectangle(){
		r = new Rectangle(0, 0, 50, 50);
	}
	
	public void setHeight(int h){
		r.setHeight(h);
	}
	
	public void setWidth(int w){
		r.setWidth(w);
	}
	
	
	
	
}
