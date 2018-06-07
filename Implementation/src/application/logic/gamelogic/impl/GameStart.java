package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamesettings.IGameSettingsFactory;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.stateMachine.port.IStateMachine;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	IStateMachine stateMachine;
	IGameSettingsFactory gameSettings;
	
	public GameStart() {
		this.gameSettings = IGameSettingsFactory.FACTORY;
	}

	@Override
	public IGameModelSettings getGameModelSettings() {
		return this.gameSettings.getGameSettingsPort().getGameModelSettings();
	}

	@Override
	public IGameQuestionSettings getGameQuestionSettings() {
		return this.gameSettings.getGameSettingsPort().getGameQuestionSettings();
	}
	
}
