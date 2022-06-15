package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.IGameBoard;
import View.GameLevelPanel;
import View.GameView;
import View.HighScorePanel;

public class ButtonController implements ActionListener {
	private IGameBoard gameboard;
	private GameView gameview;
	private GameLevelPanel gamelevel;

	public ButtonController(IGameBoard gameboard, GameView gameview, GameLevelPanel gamelevel) {
		this.gameboard = gameboard;
		this.gameview = gameview;
		this.gamelevel = gamelevel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gameview.getButtonPanel().getBtnQuit()) {
			System.exit(0);
		}

		if (e.getSource() == gameview.getButtonPanel().getBtnNewGame()) {
			gamelevel.setVisible(true);
			gameview.dispose();
		}

		if (e.getSource() == gameview.getButtonPanel().getBtnHighScore()) {
			new HighScorePanel(gameboard.getHighScore());
		}

	}

}
