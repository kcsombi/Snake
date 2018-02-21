package hazi;

public class Application {
	Game game;
	Window window;
	
	public Application() {
		game = new Game();
		window = new Window(game);
		game.addWindow(window);
	}
	
	public void start() {
		game.startGame();
	}
}
