package Model;

import View.Observer;

public interface IGameBoard {

	public void resetGame();

	public void leftMove();

	public void rightMove();

	public void upMove();

	public void downMove();

	public boolean canMove();

	public void saveHighScore();

	public Tile[] getTiles();

	public boolean isWin();

	public boolean isLose();

	public int getScore();

	// observer
	public void registerObserver(Observer go);

	public void removeObserver(Observer go);

	public void notifyObservers();

	public void setLose(boolean b);

	public HighScore getHighScore();

	public void setLevel(int i);
}
