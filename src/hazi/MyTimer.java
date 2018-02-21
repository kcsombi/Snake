package hazi;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer extends TimerTask {
	Timer timer;
	Game game;

	 public MyTimer(Game g) {
		  game = g;
	      timer = new Timer();
	  }
	  
	 public void start(int millis) {
		  timer.scheduleAtFixedRate(this, new Date(), millis);
		  
	  }
	  
	 public void stop() {
		  timer.cancel();
	  }

	@Override
	public void run() {
		game.run();
	}
}
