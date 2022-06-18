package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scorelb, presslb;
	private JButton btnNewGame, btnHighScore, btnQuit;

	private static final Font FONT_BTN = new Font("jbutton", Font.BOLD, 16);
	private static final Font FONT_LB = new Font("jlabel", Font.PLAIN, 18);
	private static final int WIDTH = 150;
	private static final int HEIGHT = 340;
	int n = 4;

	public ButtonPanel() {
		initUI();
	}

	public void setSize(int level) {
		n = level;
		setPreferredSize(new Dimension(WIDTH, HEIGHT * (n / 4)));
	}

	public void initUI() {
		setLayout(null);
		scorelb = new JLabel("Score: 0000", SwingConstants.CENTER);
		scorelb.setFont(FONT_LB);
		scorelb.setBounds(10, 20, 130, 30);
		add(scorelb);

		presslb = new JLabel("Pressed: 0000", SwingConstants.CENTER);
		presslb.setFont(FONT_LB);
		presslb.setBounds(10, 50, 130, 30);
		add(presslb);

		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(FONT_BTN);
		btnNewGame.setBounds(10, 150, 130, 35);
		add(btnNewGame);

		btnHighScore = new JButton("High Score");
		btnHighScore.setFont(FONT_BTN);
		btnHighScore.setBounds(10, 200, 130, 35);
		add(btnHighScore);

		btnQuit = new JButton("Quit Game");
		btnQuit.setFont(FONT_BTN);
		btnQuit.setBounds(10, 250, 130, 35);
		add(btnQuit);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public JLabel getPresslb() {
		return presslb;
	}

	public JLabel getScorelb() {
		return scorelb;
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JButton getBtnHighScore() {
		return btnHighScore;
	}

	public JButton getBtnQuit() {
		return btnQuit;
	}

}
