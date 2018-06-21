package application.gui.impl;

import java.io.BufferedReader;
import java.io.IOException;
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
import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;

public class ConsoleController implements IObserver, IController {

	private IGameLogicFactory logic;
	private IView view;
	private final static Logger LOGGER = Logger.getLogger(ConsoleController.class.getName());

	public ConsoleController(IGameLogicFactory gameLogic, IView view) {
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
				boolean validInput = false;
				do {
					try {
						int controllerInput = this.readInteger(state, 1,gamePlay.getPlayers().length);
						gamePlay.handleChooseFigureInField(controllerInput);
						validInput = true;
					} catch (IllegalArgumentException iae) {
						iae.printStackTrace();
						this.view.showRetryInput(new Exception("The Figure you have chosen could not be moved. Please check, if there are enough" +
								"figures in the matchfield to fit your given figure-number."));
					}
				} while (!validInput);
			} else if (state == State.addFigureToMatchField) {
				gamePlay.handleAddFigureToMatchfield();
			} else if (state == State.chooseCategory) {
				int controllerInput = this.readInteger(state, 1, gamePlay.getQuestionCategories().length);
				gamePlay.handleChooseCategory(controllerInput);
			} else if (state == State.chooseNextQuestion) {
				gamePlay.handleChooseNextQuestion();
			} else if (state == State.checkAnswer) {
				int controllerInput = this.readInteger(state, 1 , 4);
				gamePlay.handleCheckAnswer(controllerInput);
			} else if (state == State.moveFigure) {
				gamePlay.handleMoveFigure();
			} else if (state == State.adjustIndicators) {
				gamePlay.handleAdjustIndicators();
			}
		} else if (state.isSubStateOf(State.ChooseSettings)) {
			if(state == State.showTutorial) {
				String questionToAsk = "Do you want to see the tutorial?";
				boolean controllerInput = this.readBoolean(state, questionToAsk);
				gameStart.handleShowTutorial(controllerInput);
			} else if (state == State.useStandardSettings) {
				String questionToAsk = "Do you want to use the standard-settings?";
				boolean controllerInput = this.readBoolean(state, questionToAsk);
				gameStart.handleUseStandardSet(controllerInput);
			} else if (state == State.choosePlayerCount) {
				int controllerInput = this.readInteger(state, 2,6);
				gameStart.handlePlayerCount(controllerInput);
			} else if (state == State.chooseFieldsPerPlayer) {
				int controllerInput = this.readInteger(state , 3, 10);
				gameStart.handleFieldsPerPlayer(controllerInput);
			} else if (state == State.chooseFiguresPerPlayer) {
				int controllerInput = this.readInteger(state, 2,4);
				gameStart.handleFiguresPerPlayer(controllerInput);
			} else if (state == State.chooseKnowledgeInditcatorSize) {
				int controllerInput = this.readInteger(state,2,6);
				gameStart.handleKnowledgeIndicatorSteps(controllerInput);
			}
		} else if (state.isSubStateOf(State.GameCompleted)) {
			if (state == State.chooseRepeat) {
				String questionToAsk = "Do you want to repeat the game?";
				boolean controllerInput = readBoolean(state, questionToAsk);
				gamePlay.handleChooseRepeat(controllerInput);
			}
		} else {
			throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
		}
	}

	// ======================== Read from console and parse in specified type ========================

	public int readInteger(IState state, int min, int max) {
		this.view.showInputNumber(min, max);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean unvalidInput = false;
		int value = 0;
		do {
			try {
				String in = reader.readLine();
				value = Integer.parseInt(in);
				unvalidInput = true;
			} catch (NumberFormatException nfe) {
				this.view.showRetryInput(new Exception("Your input could not be interpreted as a number."));
			} catch (IOException ioe) {
				this.view.showRetryInput(new Exception("The buffer could not read properly from the command line."));
			}
		} while (!unvalidInput);
		return value;
	}

	public boolean readBoolean(IState state, String questionToAsk) {
		this.view.showInputBoolean(questionToAsk);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean validInput = false;
		boolean result = false;
		do {
			try {
				String in = reader.readLine().trim();
				if (in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("ja") || in.equalsIgnoreCase("j") || in.equalsIgnoreCase("y")) {
					validInput = true;
					result = true;
				} else if (in.equalsIgnoreCase("no") || in.equalsIgnoreCase("nein") || in.equalsIgnoreCase("n")) {
					validInput = true;
					result = false;
				} else {
					throw new IllegalArgumentException(
							"Console input " + in + " could not be determined as true or false.");
				}
			} catch (IllegalArgumentException iae) {
				this.view.showRetryInput(iae);
			} catch (IOException ioe) {
				this.view.showRetryInput(new Exception("There is a problem when trying to read your input."));
			}
		} while(!validInput);
		return result;
	}

}
