package View;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.LevelController;

public class GameLevelPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;
	private JButton level4x4, level6x6, level8x8;
	private static final Font FONT_BTN = new Font("jbutton", Font.BOLD, 16);
	private static final Font FONT_LB = new Font("jlabel", Font.PLAIN, 18);

	public GameLevelPanel() {
		initUI();
	}

	public void initUI() {
		panel = new JPanel();
		panel.setLayout(null);
		setSize(300, 300);
		label = new JLabel("Select Level");
		label.setFont(FONT_LB);
		label.setBounds(getWidth() / 3, 50, getWidth() / 2, 33);

		level4x4 = new JButton("4 x 4");
		level4x4.setFont(FONT_BTN);
		level4x4.setBounds(getWidth() / 4, 100, getWidth() / 2, 35);

		level6x6 = new JButton("6 x 6");
		level6x6.setFont(FONT_BTN);
		level6x6.setBounds(getWidth() / 4, 150, getWidth() / 2, 35);

		level8x8 = new JButton("8 x 8");
		level8x8.setFont(FONT_BTN);
		level8x8.setBounds(getWidth() / 4, 200, getWidth() / 2, 35);

		panel.add(label);
		panel.add(level4x4);
		panel.add(level6x6);
		panel.add(level8x8);

		setContentPane(panel);
		setTitle("Level");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public JButton get4x4() {
		return level4x4;
	}

	public JButton get6x6() {
		return level6x6;
	}

	public JButton get8x8() {
		return level8x8;
	}

	public void addButtonListener(LevelController levelController) {
		get4x4().addActionListener(levelController);
		get6x6().addActionListener(levelController);
		get8x8().addActionListener(levelController);

	}
}
