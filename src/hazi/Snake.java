package hazi;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Snake implements Serializable {
	public enum Dir {
		UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0);
		private int xval;
		private int yval;
		Dir(int x, int y) { xval = x; yval = y;}
		int xval() {return xval;}
		int yval() {return yval;}
		public static Dir getOppositDir(Dir dir) {
			if(dir == UP) return DOWN;
			else if(dir == DOWN) return UP;
			else if(dir == LEFT) return RIGHT;
			else if(dir == RIGHT) return LEFT;
			return null;	
		}/*
		public static String toString(Dir dir) {
			if(dir == UP) return "up";
			else if(dir == DOWN) return "down";
			else if(dir == LEFT) return "left";
			else if(dir == RIGHT) return "right";
			return null;
		}*/
	}
	
	public enum Event {NOTHING, HIT_WALL, ATE_FOOD}
	
	SnakeElement head;
	Dir dir;
	int count;
	
	public Snake() {
		dir = Dir.UP;
		head = new SnakeElement();
		head.posx = Game.size / 2;
		head.posy = Game.size / 2;
		head.prev = new SnakeElement();
		head.prev.posx = head.posx;
		head.prev.posy = head.posy + 1;
		count = 0;
	}
	
	public Event move(Food food) {
		SnakeElement newHead = new SnakeElement();
		newHead.posx = head.posx + dir.xval();
		newHead.posy = head.posy + dir.yval();
		if(newHead.posx < 0 || newHead.posx >= Game.size || newHead.posy < 0 || newHead.posy >= Game.size) return Event.HIT_WALL;
		SnakeElement tmp = head;
		while((tmp.prev).prev != null) {
			tmp = tmp.prev;
			if (isOnSnake(newHead.posx, newHead.posy)) return Event.HIT_WALL;
		}
		newHead.prev = head;
		head = newHead;
		if(food.posx == head.posx && food.posy == head.posy) {
			food.setNewPos(this);
			count = 2;
			return Event.ATE_FOOD;
		}
		if(count > 0) count--;
		else tmp.prev = null;
		return Event.NOTHING;
	}
	
	public boolean isOnSnake(int x, int y) {
		SnakeElement tmp = head;
		while(tmp != null) {
			if(x == tmp.posx && y == tmp.posy) return true;
			tmp = tmp.prev;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		SnakeElement tmp = head;
		int i = 0;
		while(tmp != null) {
			drawSnakeElement(g, tmp.posx, tmp.posy, i);
			i += 1;
			tmp = tmp.prev;
		}
	}
	
	private void drawSnakeElement(Graphics g,int  x,int y, int c) {
		int s = 500 / Game.size;
		if(c < 256)
			g.setColor(new Color(255 - c, c, 0));
		else
			g.setColor(new Color(255, 255, 255));
		g.fillRect(x*s, y*s, s, s);
	}

	
	class SnakeElement implements Serializable {
		int posx;
		int posy;
		SnakeElement prev;
	}
}
