package hazi;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public class Food implements Serializable {
	public int posx;
 	public int posy;
	public Food() {
		Random r = new Random();
		posx = r.nextInt(Game.size);
		posy = r.nextInt(Game.size);
	}
	public void setNewPos(Snake snake) {
		Random r = new Random();
		do {
			posx = r.nextInt(Game.size);
			posy = r.nextInt(Game.size);
		} while(snake.isOnSnake(posx, posy));
	}
	
	public void draw(Graphics g) {
		int s = 500 / Game.size;
		g.setColor(Color.GREEN);
		g.fillRect(posx*s, posy*s, s, s);
	}
}
