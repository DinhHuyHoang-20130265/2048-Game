package View;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.GameBoard;
import Model.IGameBoard;

@SuppressWarnings("serial")
public class GameView extends JFrame implements Observer {

	private GamePlayPanel gameplay;
	private IGameBoard gameboard;
	private ButtonPanel buttonpanel;

	public GameView() {

		gameboard = new GameBoard();
		gameplay = new GamePlayPanel(gameboard);
		buttonpanel = new ButtonPanel();

		// Register Observer
		gameboard.registerObserver(this);
		// Set focus de bat su kien gameView
		setFocusable(true);
		initUI();
	}

	public void initUI() {
		add(gameplay, BorderLayout.CENTER);
		add(buttonpanel, BorderLayout.EAST);
		setTitle("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	// observer pattern
	@Override
	public void update(IGameBoard bo) {
		GameBoard g = (GameBoard) bo;
		gameplay.setTiles(g.getTiles());
		buttonpanel.getScorelb().setText("Score:" + g.getScore() + "");
		buttonpanel.getPresslb().setText("Pressed: " + g.getPressedNumber() + "");
	}

	// add event for buttons
	public void addButtonListener(ActionListener actionListener) {
		buttonpanel.getBtnQuit().addActionListener(actionListener);
		buttonpanel.getBtnNewGame().addActionListener(actionListener);
		buttonpanel.getBtnHighScore().addActionListener(actionListener);
	}

	public void addMoveListener(KeyEventDispatcher dispatcher) {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
	}

	public GamePlayPanel getGameplay() {
		return gameplay;
	}

	public IGameBoard getGameboard() {
		return gameboard;
	}

	public ButtonPanel getButtonPanel() {
		return buttonpanel;
	}
}
