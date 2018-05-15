package application.logic.gamelogic.port;

import application.logic.gamemodel.impl.GameModel;

public interface IGameStart {

	// Create new instance of Game-Modell Facade
	GameModel createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer, int knowledgeIndicatorSize);
	
}
