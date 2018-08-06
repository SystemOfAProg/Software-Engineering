package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGameStart;
import application.logic.gamesettings.IGameSettingsFactory;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IStateMachine;
import application.logic.stateMachine.port.IState.State;

// Util for creating a new instance of a game
public class GameStart implements IGameStart {

	IStateMachineFactory stateMachineFactory = IStateMachineFactory.FACTORY;
	IGameSettingsFactory gameSettingsFactory = IGameSettingsFactory.FACTORY;

	IStateMachine stateMachine = stateMachineFactory.getStateMachinePort().getStateMachine();
	IGameModelSettings gameModelSettings = gameSettingsFactory.getGameSettingsPort().getGameModelSettings();
	IGameQuestionSettings gameQuestionSettings = gameSettingsFactory.getGameSettingsPort().getGameQuestionSettings();

	public GameStart() { }

	@Override
	public IGameModelSettings getGameModelSettings() {
		return this.gameModelSettings;
	}

	@Override
	public IGameQuestionSettings getGameQuestionSettings() {
		return this.gameQuestionSettings;
	}

	@Override
	public void start() {
		this.stateMachine.setState(State.showTutorial);
	}

	@Override
	// TODO new state for tutorial and handler
	public void handleShowTutorial(boolean controllerInput) {
		this.stateMachine.setState(State.useStandardSettings);
	}

	@Override
	public void handleUseStandardSet(boolean controllerInput) {
		if(controllerInput) {
			this.stateMachine.setState(State.getNextPlayer);
		} else {
			this.stateMachine.setState(State.choosePlayerCount);
		}
	}

	@Override
	public void handlePlayerCount(int controllerInput) {
		this.gameModelSettings.setPlayerCount(controllerInput);
		this.stateMachine.setState(State.chooseFieldsPerPlayer);
	}

	@Override
	public void handleFieldsPerPlayer(int controllerInput) {
		this.gameModelSettings.setFieldsPerPlayer(controllerInput);
		this.stateMachine.setState(State.chooseFiguresPerPlayer);
	}

	@Override
	public void handleFiguresPerPlayer(int controllerInput) {
		this.gameModelSettings.setFiguresPerPlayer(controllerInput);
		this.stateMachine.setState(State.chooseKnowledgeInditcatorSize);
	}

	@Override
	public void handleKnowledgeIndicatorSteps(int controllerInput) {
		this.gameQuestionSettings.setKnowledgeIndicatorSize(controllerInput);
		this.stateMachine.setState(State.getNextPlayer);
	}

}
