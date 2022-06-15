package View;

import Model.IGameBoard;

public interface Observer {
	public void update(IGameBoard gb);
}
