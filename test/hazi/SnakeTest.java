package hazi;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SnakeTest {

	@Test
	public void testSnakeInit() {
		Game.size = 10;
		Snake snake = new Snake();
		Assert.assertEquals(snake.dir, Snake.Dir.UP);
		Assert.assertEquals(snake.head.posx, snake.head.prev.posx);
		Assert.assertEquals(snake.head.posy, snake.head.prev.posy - 1);
	}
	
	@Test
	public void testHitWall() {
		Game.size = 10;
		Food food = new Food(); food.posx = 0; food.posy = 0;
		Snake snake = new Snake();
		for(int i = 0; i < 5; i++) {
			Snake.Event e = snake.move(food);
			Assert.assertEquals(Snake.Event.NOTHING, e);
		}
		Snake.Event e = snake.move(food);
		Assert.assertEquals(Snake.Event.HIT_WALL, e);
	}
	
	@Test
	public void testSelfCollision() {
		Game.size = 10;
		Food food = new Food(); food.posx = 5; food.posy = 4;
		Snake snake = new Snake();
		snake.move(food);
		snake.move(food);
		snake.move(food);
		snake.dir = Snake.Dir.LEFT;
		snake.move(food);
		snake.dir = Snake.Dir.DOWN;
		snake.move(food);
		snake.dir = Snake.Dir.RIGHT;
		Snake.Event e = snake.move(food);
		Assert.assertEquals(Snake.Event.HIT_WALL, e);
	}
	
	@Test
	public void testEat() {
		Game.size = 10;
		Food food = new Food(); food.posx = 5; food.posy = 3;
		Snake snake = new Snake();
		Snake.Event e = snake.move(food);
		Assert.assertEquals(Snake.Event.NOTHING, e);
		e = snake.move(food);
		Assert.assertEquals(Snake.Event.ATE_FOOD, e);
	}

}










