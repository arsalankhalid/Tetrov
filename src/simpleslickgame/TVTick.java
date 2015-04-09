package simpleslickgame;

public class TVTick {
	int tick; // resolution 
	int total = 0;
	
	TVTick(int interval) {	tick = interval; }
	public boolean update(int i){
		total+=i;
		if(total%tick == 0)
			return true;
		return false;
	}
	
}
