package simpleslickgame;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import simpleslickgame.TVTick;
public class TVPlayState extends TVGameState implements Observer { 
	
	TVGrid grid;
	GameContainer gc;
	TVTick tick;
	TVShape currShape;
	TVShapeFactory shapeFactory;
	Graphics graphics;
	Color colour;
	
	ArrayList<ConfigurableEmitter> currEmitter;
	
	int score = 0;
	int level = 0;
	
	private ConfigurableEmitter smallExplosionEmitter; // initial explosion - will be duplicated and placed as needed
	private ParticleSystem effectSystem; // stores all particle effects
	
	Toolkit toolkit;
	Timer timer;
	
<<<<<<< HEAD
	class RemindTask extends TimerTask {
	    @Override
		public void run() {
	      System.out.println("Time's up!");
	      if(currEmitter != null){
	    	  removeEmitter();
	      }  
	    }
	  }
	
=======
>>>>>>> 0e3cdd9809ae130115e86b5f899088096a488dd1
	public TVPlayState(TVInvoker i, TVGrid grid) {
		super(i);
		this.grid = grid;
		this.gc = i.gc;
		this.tick = new TVTick(1000);
		this.shapeFactory = new TVShapeFactory(gc);
		this.currShape = shapeFactory.getRandomShape();
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		score = 0;
		this.graphics= gc.getGraphics();
		initParticles();
		currEmitter = new ArrayList<ConfigurableEmitter>();
	} 
	
	class RemindTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	      if(currEmitter != null){
	    	  removeEmitter();
	      }  
	    }
	}
	
	void initParticles(){
		 try {   
	         effectSystem = ParticleIO.loadConfiguredSystem("/src/resources/resources.xml");
	         effectSystem.getEmitter(0).setEnabled(false); // disable the initial emitter
	         effectSystem.setRemoveCompletedEmitters(true); // remove emitters once they finish
	         smallExplosionEmitter = (ConfigurableEmitter)effectSystem.getEmitter(0);
	         smallExplosionEmitter.setEnabled(false);
	      }
	      catch(Exception e) {
	         Sys.alert("Error", "Error adding explosion\nCheck for explosion.xml");
	         System.exit(0);
	      }
	}
	
	public void addExplosion(float x, float y) {
       ConfigurableEmitter e = smallExplosionEmitter.duplicate(); // copy initial emitter
       e.setEnabled(true); // enable
       e.setPosition(x, y);
       currEmitter.add(e);
       effectSystem.addEmitter(e); // add to particle system for rendering and updating
       toolkit = Toolkit.getDefaultToolkit();
	   timer = new Timer();
	   int seconds = 2;
	   timer.schedule(new RemindTask(), seconds * 1000);    
   }
	
	public void removeEmitter() {
		for ( ConfigurableEmitter ce : currEmitter) {
			if(ce != null){
				effectSystem.removeEmitter(ce);
			}
		}
		
		currEmitter = null;
		currEmitter = new ArrayList<ConfigurableEmitter>();
	}

	@Override
	void pressLeft() {
		currShape.moveLeft(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressRight() {
		currShape.moveRight(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressDown() {
		currShape.moveDown(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressA() {
		currShape.rotateLeft(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}

	@Override
	void pressB() {
		currShape.rotateRight(grid.collisionCandidate());
		grid.updateCurrentShape(currShape);
	}
	
	@Override
	void pressPause() {
		invoker.setGameState(invoker.getPauseState());
	}

	@Override
	void renderGameboard() {
		currShape.drawShape();
		grid.drawBlocks();
		manageScoreAndLevel();
		effectSystem.render();
	}
	
	void manageScoreAndLevel() {
		//score
		graphics.setColor(Color.white);
		graphics.drawString("Score: ", 10, 10);
		graphics.setColor(Color.green);
		graphics.drawString(Integer.toString(score), 65, 10);
		
		//level
		graphics.setColor(Color.white);
		graphics.drawString("Level: ", 200, 10);
		graphics.setColor(Color.red);
		graphics.drawString(Integer.toString(level), 255, 10);
	}

	@Override
	void updateGameboard(int i) { 
		// called every single frame
		effectSystem.update(i);
		if(tick.update(i)){
			// do logic that needs to happen
			currShape.moveDown(grid.collisionCandidate());
			grid.updateCurrentShape(currShape);
		}
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		
		TVCollisionInfo collInfo = grid.isCollided((TVShape)o);
		if(collInfo != null) {
			if(collInfo.lost == true){
				invoker.setGameState(invoker.getLostState());
			}
			if (collInfo.collisionRawInfoArr!=null) {
				if(collInfo.collisionRawInfoArr.size() > 0){ 
					
					score += collInfo.collisionRawInfoArr.size() * 10;
					
					for (Integer raw : collInfo.collisionRawInfoArr) {
						addExplosion(28,(raw*30));
						addExplosion(56,(raw*30));
						addExplosion(84,(raw*30));
						addExplosion(112,(raw*30));
						addExplosion(130,(raw*30));
						addExplosion(158,(raw*30));
						addExplosion(186,(raw*30));
						addExplosion(216,(raw*30));
						addExplosion(240,(raw*30));
						addExplosion(260,(raw*30));
					}
					
					if(score%10 == 0) {
						level++;
						if(level%10 == 0) {
							tick.tick -= 100;
						}
					}
				}
			}
		}
		
		currShape = shapeFactory.getRandomShape();
		currShape.addObserver(this);
		grid.addShapetoTop(currShape);
		
	}

}
