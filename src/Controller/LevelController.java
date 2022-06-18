package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.IGameBoard;
import View.ButtonPanel;
import View.GameLevel;
import View.GamePlayPanel;
import View.GameView;

public class LevelController implements ActionListener {
	private IGameBoard igameboard;
	private GameView gameview;
	private GameLevel gamelevel;
	private ButtonPanel buttonpanel;
	private GamePlayPanel gameplaypanel;

	public LevelController(IGameBoard igameboard, GameView gameview, GameLevel gamelevel, ButtonPanel buttonpanel,
			GamePlayPanel gameplaypanel) {
		this.igameboard = igameboard;
		this.gameview = gameview;
		this.gamelevel = gamelevel;
		this.buttonpanel = buttonpanel;
		this.gameplaypanel = gameplaypanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gamelevel.get4x4()) {
			gameplaypanel.setSize(4);
			igameboard.setLevel(4);
			buttonpanel.setSize(4);
			igameboard.resetGame();
			gamelevel.dispose();
			gameview.initUI();
			gameview.setVisible(true);
			gameview.repaint();
		}
		if (e.getSource() == gamelevel.get6x6()) {
			gameplaypanel.setSize(6);
			igameboard.setLevel(6);
			buttonpanel.setSize(6);
			igameboard.resetGame();
			gamelevel.dispose();
			gameview.initUI();
			gameview.setVisible(true);
			gameview.repaint();
		}
		if (e.getSource() == gamelevel.get8x8()) {
			gameplaypanel.setSize(8);
			igameboard.setLevel(8);
			buttonpanel.setSize(8);
			igameboard.resetGame();
			gamelevel.dispose();
			gameview.initUI();
			gameview.setVisible(true);
			gameview.repaint();
		}

	}

}
