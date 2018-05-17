package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	public GameStart() {}

	@Override
	public IGameModelSettings getGameModelSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGameQuestionSettings getGameQuestionSettings() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
