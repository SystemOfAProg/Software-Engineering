package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.Game;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	public GameStart() {}

	@Override
	public Game createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer,
			int knowledgeIndicatorSize) {
		return new Game(gameFieldSizeFactor, playerCount, figuresPerPlayer, knowledgeIndicatorSize);
	}
	
}
