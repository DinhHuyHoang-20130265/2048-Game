package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import View.Observer;

public class GameBoard implements IGameBoard {

	private Tile[] tiles;
	private boolean win, lose;

	// score
	private int score = 0;
	private int pressedNumber = 0;

	private HighScore highScore;
	private boolean saveScore = false;

	private ArrayList<Observer> observers;
	int n = 4;

	public GameBoard() {
		observers = new ArrayList<Observer>();
		highScore = new HighScore();
		resetGame();
	}

	@Override
	public void setLevel(int level) {
		n = level;
	}

	@Override
	public void removeObserver(Observer go) {
		int i = observers.indexOf(go);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer go = observers.get(i);
			go.update(this);
		}
	}

	@Override
	public void registerObserver(Observer go) {
		observers.add(go);
	}

	// notify observer when tiles change
	public void change(Tile[] tiles) {
		this.tiles = tiles;
		notifyObservers();
	}

	@Override
	public void resetGame() {
		score = 0;
		pressedNumber = 0;
		saveScore = false;
		lose = false;
		tiles = new Tile[n * n];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}

		addTile();
		addTile();
		change(tiles);
	}

	@Override
	public void leftMove() {
		boolean needAddTile = false;
		for (int i = 0; i < n; i++) {
			Tile[] line = getLine(i);
			Tile[] merged = mergeLine(moveLine(line));
			setLine(i, merged);
			if (!needAddTile && !compare(line, merged)) {
				needAddTile = true;
			}
		}
		if (needAddTile) {
			addTile();
		}
		pressedNumber++;
		change(tiles);
	}

	@Override
	public void rightMove() {
		tiles = rotate(180);
		leftMove();
		tiles = rotate(180);
		change(tiles);
	}

	@Override
	public void upMove() {
		tiles = rotate(270);
		leftMove();
		tiles = rotate(90);
		change(tiles);

	}

	@Override
	public void downMove() {
		tiles = rotate(90);
		leftMove();
		tiles = rotate(270);
		change(tiles);

	}

	private Tile tileAt(int x, int y) {
		return tiles[x + y * n];
	}

	private void addTile() {
		List<Tile> list = availableSpace();
		if (!availableSpace().isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile emptyTime = list.get(index);
			emptyTime.value = Math.random() < 0.9 ? 2 : 4;
		}
	}

	private List<Tile> availableSpace() {
		final List<Tile> list = new ArrayList<Tile>(n * n);
		for (Tile t : tiles) {
			if (t.isEmpty()) {
				list.add(t);
			}
		}
		return list;
	}

	private boolean isFull() {
		return availableSpace().size() == 0;
	}

	@Override
	public boolean canMove() {
		if (!isFull()) {
			return true;
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				Tile t = tileAt(x, y);
				if ((x < n - 1 && t.value == tileAt(x + 1, y).value)
						|| ((y < n - 1) && t.value == tileAt(x, y + 1).value)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean compare(Tile[] line1, Tile[] line2) {
		if (line1 == line2)
			return true;
		else if (line1.length != line2.length)
			return false;

		for (int i = 0; i < line1.length; i++) {
			if (line1[i].value != line2[i].value)
				return false;
		}
		return true;
	}

	private Tile[] rotate(int angle) {
		Tile[] newTiles = new Tile[n * n];
		int offsetX = n - 1, offsetY = n - 1;
		if (angle == 90)
			offsetY = 0;
		else if (angle == 270)
			offsetX = 0;

		double rad = Math.toRadians(angle);
		int cos = (int) Math.cos(rad);
		int sin = (int) Math.sin(rad);
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				int newX = (x * cos) - (y * sin) + offsetX;
				int newY = (x * sin) + (y * cos) + offsetY;
				newTiles[(newX) + (newY) * n] = tileAt(x, y);
			}
		}
		return newTiles;
	}

	private Tile[] moveLine(Tile[] oldLine) {
		LinkedList<Tile> l = new LinkedList<Tile>();
		for (int i = 0; i < n; i++) {
			if (!oldLine[i].isEmpty())
				l.addLast(oldLine[i]);
		}
		if (l.size() == 0)
			return oldLine;
		else {
			Tile[] newLine = new Tile[n];
			ensureSize(l, n);
			for (int i = 0; i < n; i++)
				newLine[i] = l.removeFirst();
			return newLine;
		}
	}

	private Tile[] mergeLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		for (int i = 0; i < n && !oldLine[i].isEmpty(); i++) {
			int num = oldLine[i].value;
			if (i < (n - 1) && oldLine[i].value == oldLine[i + 1].value) {
				num *= 2;
				score += num;
				int ourTarget = 2048 * (n / 2 - 1);
				if (num == ourTarget)
					win = true;
				i++;
			}
			list.add(new Tile(num));
		}
		if (list.size() == 0)
			return oldLine;
		else {
			ensureSize(list, n);
			return list.toArray(new Tile[n]);
		}
	}

	private static void ensureSize(List<Tile> l, int s) {
		while (l.size() != s)
			l.add(new Tile());
	}

	private Tile[] getLine(int index) {
		Tile[] result = new Tile[n];
		for (int i = 0; i < n; i++)
			result[i] = tileAt(i, index);
		return result;
	}

	private void setLine(int index, Tile[] re) {
		System.arraycopy(re, 0, tiles, index * n, n);
	}

	@Override
	public void saveHighScore() {
		if (!saveScore) {
			if (pressedNumber != 0 && score != 0) {
				highScore.addPressed(pressedNumber);
				highScore.addScore(score);
				saveScore = true;
			}
		}
	}

	public int getPressedNumber() {
		return pressedNumber;
	}

	public void setPressedNumber(int pressedNumber) {
		this.pressedNumber = pressedNumber;
	}

	@Override
	public Tile[] getTiles() {
		return tiles;
	}

	@Override
	public HighScore getHighScore() {
		return highScore;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	@Override
	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	@Override
	public boolean isLose() {
		return lose;
	}

	@Override
	public void setLose(boolean lose) {
		this.lose = lose;
	}

	@Override
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
