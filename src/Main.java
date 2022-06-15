import Controller.GameController;
import View.GameLevelPanel;
import View.GameView;

public class Main {
	public static void main(String[] args) {	
		GameView gameView = new GameView();
		GameLevelPanel gameLevel = new GameLevelPanel();
		GameController gameController = new GameController(gameView, gameLevel);
		gameController.start();
	}
}
