package application.gui.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.gui.port.IView;
import application.gui.port.IController;
import application.gui.port.IObserver;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGameStart;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;

public class Controller implements IObserver, IController {

	private IGameLogicFactory logic;
	private IView view;
	private final static Logger LOGGER = Logger.getLogger(Controller.class.getName());

	public Controller(IGameLogicFactory gameLogic, IView view) {
		this.logic = gameLogic;
		this.view = view;
		this.logic.attach(this);
	}

	@Override
	/**
	 * React on an updated State from the Statemachine, read input from console
	 * if necessary and invoke the corresponding functionality in the game logic
	 */
	public void update(IState state) {
		IGamePlay gamePlay = this.logic.getGamePort().getGamePlay();
		IGameStart gameStart = this.logic.getGamePort().getGameStart();
		if (state.isSubStateOf(State.GameActive)) {
			if (state == State.getNextPlayer) {
				gamePlay.handleGetNextPlayer();
			} else if (state == State.throwDice) {
				gamePlay.handleThrowDice();
			} else if (state == State.chooseFigureInField) {
				int controllerInput = this.readInteger(state);
				gamePlay.handleChooseFigureInField(controllerInput);
			} else if (state == State.addFigureToMatchField) {
				gamePlay.handleAddFigureToMatchfield();
			} else if (state == State.chooseCategory) {
				int controllerInput = this.readInteger(state);
				gamePlay.handleChooseCategory(controllerInput);
			} else if (state == State.chooseNextQuestion) {
				gamePlay.handleChooseNextQuestion();
			} else if (state == State.checkAnswer) {
				int controllerInput = this.readInteger(state);
				gamePlay.handleCheckAnswer(controllerInput);
			} else if (state == State.moveFigure) {
				gamePlay.handleMoveFigure();
			} else if (state == State.adjustIndicators) {
				gamePlay.handleAdjustIndicators();
			}
		} else if (state.isSubStateOf(State.ChooseSettings)) {
			if(state == State.showTutorial) {
				boolean controllerInput = this.readBoolean(state);
				gameStart.handleShowTutorial(controllerInput);
			} else if (state == State.useStandardSettings) {
				boolean controllerInput = this.readBoolean(state);
				gameStart.handleUseStandardSet(controllerInput);
			} else if (state == State.choosePlayerCount) {
				int controllerInput = this.readInteger(state);
				gameStart.handlePlayerCount(controllerInput);
			} else if (state == State.chooseFieldsPerPlayer) {
				int controllerInput = this.readInteger(state);
				gameStart.handleFieldsPerPlayer(controllerInput);
			} else if (state == State.chooseFiguresPerPlayer) {
				int controllerInput = this.readInteger(state);
				gameStart.handleFiguresPerPlayer(controllerInput);
			} else if (state == State.chooseKnowledgeInditcatorSize) {
				int controllerInput = this.readInteger(state);
				gameStart.handleKnowledgeIndicatorSteps(controllerInput);
			}
		} else if (state.isSubStateOf(State.GameCompleted)) {
			if (state == State.chooseRepeat) {
				boolean controllerInput = readBoolean(state);
				gamePlay.handleChooseRepeat(controllerInput);
			}
		} else {
			throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
		}
	}

	// ======================== Read from console and parse in specified type ========================

	public int readInteger(IState state) {
		System.out.println("Bitte gebe eine ganze Zahl ein:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String in = reader.readLine();
			return Integer.parseInt(in);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Current State of State Machine: " + state, e);
			return 0;
		}
	}

	public boolean readBoolean(IState state) {
		System.out.println("(Y/N)");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String in = reader.readLine().trim();
			if (in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("ja") || in.equalsIgnoreCase("j") || in.equalsIgnoreCase("y")) {
				return true;
			} else if (in.equalsIgnoreCase("no") || in.equalsIgnoreCase("nein") || in.equalsIgnoreCase("n")) {
				return false;
			} else {
				throw new IllegalArgumentException(
						"Console input " + in + " could not be determined as true or false.");
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Current State of State Machine: " + state, e);
			return false;
		}
	}

}
