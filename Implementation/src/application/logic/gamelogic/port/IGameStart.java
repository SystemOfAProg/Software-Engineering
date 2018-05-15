package application.logic.gamelogic.port;

import application.logic.gamemodel.impl.Game;

public interface IGameStart {

	// Create new instance of Game-Modell Facade
	Game createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer, int knowledgeIndicatorSize);
	
}
