package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.GameModel;
import application.logic.gamesettings.IGameSettingsFactory;
import application.logic.gamesettings.port.IGameModelSettings;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	public GameStart() {}

	@Override
	public GameModel createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer,
			int knowledgeIndicatorSize) {
		// Create Game Settings instance and pass it to Game Model and Question Manager
		IGameModelSettings modelSettings = IGameSettingsFactory.FACTORY.getGameSettingsPort().getGameModelSettings();
		return new GameModel(modelSettings);
	}
	
}
