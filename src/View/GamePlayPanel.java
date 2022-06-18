package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import Model.IGameBoard;
import Model.Tile;

public class GamePlayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final String FONT_NAME = "Arial";
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;
	private static final int WIDTH = 340;
	private static final int HEIGHT = 340;

	private Tile[] tiles;
	private IGameBoard gameboard;
	double n = 4;

	public GamePlayPanel(IGameBoard gameboard) {
		this.gameboard = gameboard;
		tiles = gameboard.getTiles();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, (int) (WIDTH * (n / 4)), (int) (HEIGHT * (n / 4)));
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++)
				drawTile(g, tiles[x + y * (int) n], x, y);

		}
		g.dispose();
	}

	public void setSize(int level) {
		n = level;
		setPreferredSize(new Dimension((int) (WIDTH * (n / 4)), (int) (HEIGHT * (n / 4))));
	}

	private void drawTile(Graphics g2, Tile tile, int x, int y) {
		Graphics2D g = ((Graphics2D) g2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		int value = tile.getValue();
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(tile.getBackground());
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
		g.setColor(tile.getForeground());
		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

		if (gameboard.isWin() || gameboard.isLose()) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font(FONT_NAME, Font.BOLD, (int) (48 * (n / 4))));
			if (gameboard.isWin())
				g.drawString("YOU WIN!", (int) ((WIDTH * (n / 4)) / 20), (int) ((HEIGHT * (n / 4) / 2)));
			if (gameboard.isLose())
				g.drawString("GAME OVER!", getWidth() / 20, getHeight() / 2);
			if (gameboard.isWin() || gameboard.isLose()) {
				g.setFont(new Font(FONT_NAME, Font.PLAIN, (int) (16 * (n / 4))));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("press ESC to reset game", (int) ((WIDTH * (n / 4)) / 4), getHeight() - 40);
			}
			g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		}
	}

	private static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}
}