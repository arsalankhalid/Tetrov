package simpleslickgame;

public class TVTick {
	int tick;
	TVTick(int ms, int interval) {	tick = ms%interval; }
	public void setSpeed(int speed){ tick = tick%speed;	}
}
