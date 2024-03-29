package Controller;

import java.awt.event.KeyAdapter;

import Model.IGameBoard;
import View.ButtonPanel;
import View.GameLevel;
import View.GamePlayPanel;
import View.GameView;

public class GameController extends KeyAdapter implements Controller {
	private IGameBoard iGameBoard;
	private GameLevel gamelevel;
	private ButtonPanel buttonpanel;
	private GamePlayPanel gameplaypanel;

	public GameController(GameView gameview, GameLevel gamelevel) {
		this.gamelevel = gamelevel;
		iGameBoard = gameview.getGameboard();
		buttonpanel = gameview.getButtonPanel();
		gameplaypanel = gameview.getGameplay();
		gamelevel.addButtonListener(new LevelController(iGameBoard, gameview, gamelevel, buttonpanel, gameplaypanel));
		gameview.addButtonListener(new ButtonController(iGameBoard, gameview, gamelevel));
		gameview.addMoveListener(new MoveController(iGameBoard, gameview));
	}

	@Override
	public void start() {
		gamelevel.setVisible(true);
	}
}
