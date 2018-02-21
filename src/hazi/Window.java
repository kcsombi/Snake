package hazi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Window extends JFrame {
	Game game;
	Drawer drawer;
	JMenuItem[] highScoreButtons = new JMenuItem[5];
	JTextField points;
	
	public Window(Game g) {
		super();
		game = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(506, 578);
		setResizable(false);
		setUpMenuBar();
		KeyEventHandler keyEventHandler = new KeyEventHandler();
		drawer = new Drawer(game);
		drawer.setFocusable(true);
		drawer.addKeyListener(keyEventHandler);
		add(drawer, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Score: ");
		label.setVisible(true);
		points = new JTextField("2",3);
		points.setEditable(false);
		points.setBorder(null);
		panel.add(label);
		panel.add(points);
		panel.setFocusable(true);
		panel.addKeyListener(keyEventHandler);
		add (panel, BorderLayout.NORTH);
		addKeyListener(keyEventHandler);
		setVisible(true);
	}
	
	public void redraw() {
		points.setText(String.valueOf(game.point));
		drawer.repaint();
	}
	
	public void updateHighScores(HighScores highScores) {
		for(int i = 0; i < 5; i++)
			highScoreButtons[i].setText(String.valueOf(highScores.get(4 - i)));
	}
	
	private void setUpMenuBar() {
		JMenuBar menuBar;
		JMenu menu, subMenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menuItem = new JMenuItem(new AbstractAction("New game") {
		    public void actionPerformed(ActionEvent e) {
		        game.newGame();
		    }
		});
		menu.add(menuItem);
		menuItem = new JMenuItem(new AbstractAction("Save") {
		    public void actionPerformed(ActionEvent e) {
		        game.save();
		    }
		});
		menu.add(menuItem);
		menuItem = new JMenuItem(new AbstractAction("Load") {
		    public void actionPerformed(ActionEvent e) {
		        game.load();
		    }
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Options");
		
		subMenu = new JMenu("Speed");
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("1") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.speed = 200;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("2") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.speed = 150;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("3") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.speed = 100;
		        game.newGame();
		    }
		});
		rbMenuItem.setSelected(true);
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("4") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.speed = 60;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("5") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.speed = 30;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		menu.add(subMenu);

		subMenu = new JMenu("Size");
		group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("10x10") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.size = 10;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("20x20") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.size = 20;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("25x25") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.size = 25;
		        game.newGame();
		    }
		});
		rbMenuItem.setSelected(true);
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("50x50") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.size = 50;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(new AbstractAction("100x100") {
		    public void actionPerformed(ActionEvent e) {
		    	Game.size = 100;
		        game.newGame();
		    }
		});
		group.add(rbMenuItem);
		subMenu.add(rbMenuItem);
		menu.add(subMenu);
		menuBar.add(menu);
		
		menu = new JMenu("Highscores");
		for(int i = 0; i< 5; i++) {
			highScoreButtons[i] = new JMenuItem(String.valueOf(i));
			menu.add(highScoreButtons[i]);
		}
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
}