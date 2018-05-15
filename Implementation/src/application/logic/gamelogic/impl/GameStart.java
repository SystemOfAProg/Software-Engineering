package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.GameModel;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	public GameStart() {}

	@Override
	public GameModel createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer,
			int knowledgeIndicatorSize) {
		return new GameModel(gameFieldSizeFactor, playerCount, figuresPerPlayer, knowledgeIndicatorSize);
	}
	
}
