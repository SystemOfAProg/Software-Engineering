package application.gui.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.gui.port.IController;
import application.gui.port.IObserver;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;

public class Controller implements IObserver, IController{

	IGameLogicFactory logic;
	private final static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	@Override
	/**
	 * React on an update from the Statemachine, read input from console if necessary
	 * and invoke the corresponding functionality in the game logic
	 */
	public void update(IState state) {
		IGamePlay gamePlay = this.logic.getGamePort().getGamePlay();
		if(state.isSubStateOf(State.GameActive)) {
			if(state == State.getNextPlayer) {
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
		} else if (state.isSubStateOf(State.GameNotStarted)) {
			// TODO setup process for choosing settings
		} else if (state.isSubStateOf(State.GameCompleted)) {
			// TODO ask, if game should be repeated and redo the whole game
		} else {
			throw new IllegalStateException("The current State \""+ state + "\" of the state-machine ist not valid.");
		}
	}

	public int readInteger(IState state) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    try {
	    	String in = reader.readLine();
	    	return Integer.parseInt(in);
	    } catch (Exception e) {
	    	LOGGER.log(Level.SEVERE, "Current State of State Machine: " + state, e);
	    	return 0;
	    }
	}
	
}
