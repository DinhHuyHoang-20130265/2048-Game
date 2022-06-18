package Controller;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import Model.IGameBoard;
import View.GameView;

public class MoveController implements KeyEventDispatcher {
	private IGameBoard gameboard;
	private GameView gameview;

	public MoveController(IGameBoard gameboard, GameView gameview) {
		this.gameboard = gameboard;
		this.gameview = gameview;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		switch (e.getID()) {
		case KeyEvent.KEY_PRESSED:
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				gameboard.resetGame();
			if (!gameboard.canMove())
				gameboard.setLose(true);
			if (!gameboard.isWin() && !gameboard.isLose()) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
					gameboard.leftMove();
				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
					gameboard.rightMove();
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
					gameboard.downMove();
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
					gameboard.upMove();
			}
			if (!gameboard.isWin() && !gameboard.canMove()) {
				gameboard.setLose(true);
				gameboard.saveHighScore();
			}
			gameview.repaint();

		case KeyEvent.KEY_RELEASED:
			break;
		case KeyEvent.KEY_TYPED:
			break;
		}
		return false;
	}
}
