import Controller.GameController;
import View.GameLevel;
import View.GameView;

public class Main {
	public static void main(String[] args) {	
		GameView gameView = new GameView();
		GameLevel gameLevel = new GameLevel();
		GameController gameController = new GameController(gameView, gameLevel);
		gameController.start();
	}
}
