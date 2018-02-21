package hazi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventHandler implements KeyListener {
	
    @Override public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()) {
    		case KeyEvent.VK_UP   : Game.PressedKey.dir = Snake.Dir.UP;    Game.PressedKey.arrowPressed = true; break;
    		case KeyEvent.VK_DOWN : Game.PressedKey.dir = Snake.Dir.DOWN;  Game.PressedKey.arrowPressed = true; break;
    		case KeyEvent.VK_RIGHT: Game.PressedKey.dir = Snake.Dir.RIGHT; Game.PressedKey.arrowPressed = true; break;
    		case KeyEvent.VK_LEFT : Game.PressedKey.dir = Snake.Dir.LEFT;  Game.PressedKey.arrowPressed = true; break;
    		case KeyEvent.VK_SPACE: Game.PressedKey.space = true; break;
    	}
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}