package hazi;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;

public class Game {
	Window window;
	MyTimer timer;
	HighScores highScores;
	Snake snake;
	Food food;
	int point;
	public static int size = 25;
	public static int speed = 100;
	boolean paused = false;
	
	static class PressedKey {
		public static Snake.Dir dir = Snake.Dir.UP;
		public static boolean space = false;
		public static boolean arrowPressed = false;
	}
	
	public Game() {
		highScores = new HighScores();
	}
	
	public void addWindow(Window win) {
		window = win;
	}
	
	public void startGame(){
		window.updateHighScores(highScores);
		snake = new Snake();
		food = new Food();
		point = 0;
		paused = true;
		PressedKey.dir = Snake.Dir.UP;
		timer = new MyTimer(this);
		timer.start(speed);
		window.redraw();
		
	}
	
	public void run() {
		if(PressedKey.space) {
			paused = true;
			PressedKey.space = false;
		}else if(PressedKey.arrowPressed) {
			paused = false;
			PressedKey.arrowPressed = false;
		}
		if(!paused)
			move(PressedKey.dir);
	}
	
	public void move(Snake.Dir dir) {
		if(snake.dir != Snake.Dir.getOppositDir(dir))
			snake.dir = dir;
		//System.out.println(Snake.Dir.toString(snake.dir));
		Snake.Event e = snake.move(food);
		window.redraw();
		if(e == Snake.Event.ATE_FOOD) point++;
		else if(e == Snake.Event.HIT_WALL) {
			timer.stop();
			highScores.add(point);
			startGame();
		}
	}
	
	public void drawField(Graphics g) {
		//System.out.println("Drawing field...");
		g.setColor(new Color(70, 70, 70));
		int n = 500 / size;
		for(int i = n; i<500; i+=n) {
			g.drawLine(0, i, 500, i);
			g.drawLine(i, 0, i, 500);
		}
		food.draw(g);
		snake.draw(g);
	}
	
	public void newGame() {
		timer.stop();
		startGame();
	}
	
	public void save() {
		System.out.println("Saved");
		try {
	        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.dat"));
	        out.writeObject(Integer.valueOf(size));
	        out.writeObject(Integer.valueOf(speed));
	        out.writeObject(Integer.valueOf(point));
	        out.writeObject(snake);
	        out.writeObject(food);
	        out.close();
	    }catch(IOException i) {
	        i.printStackTrace();
	    }
	}
	
	public void load() {
		System.out.println("Loaded");
		try {
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.dat"));
	        size = (Integer)in.readObject();
	        speed = (Integer)in.readObject();
	        newGame();
	        point = (Integer)in.readObject();
	        snake = (Snake)in.readObject();
	        food = (Food)in.readObject();
	        in.close();
	        window.redraw();
	        
	    }catch(IOException i){
	        i.printStackTrace();
	        return;
	    }catch(ClassNotFoundException c){
	        System.out.println("class not found");
	        c.printStackTrace();
	        return;
	    }
	}
}























