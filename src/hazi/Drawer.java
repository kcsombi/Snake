package hazi;

import java.awt.*;
import javax.swing.*;

public class Drawer extends JPanel {
	Game game;
	
	public Drawer(Game g) {
		game = g;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(20, 20, 20));
		game.drawField(g);
	}
}
