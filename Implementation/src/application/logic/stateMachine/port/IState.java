package application.logic.stateMachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IState {

	boolean isSubStateOf(IState state);

	boolean isSuperStateOf(IState state);

	enum State implements IState {

		// Substates of ChooseGameSettings
		showTutorial, useStandardSettings, choosePlayerCount, chooseFieldsPerPlayer, chooseFiguresPerPlayer, chooseKnowledgeInditcatorSize,
		// Before Starting Game to Choose Settings
		ChooseSettings(showTutorial, useStandardSettings, choosePlayerCount, chooseFieldsPerPlayer,
				chooseFiguresPerPlayer, chooseKnowledgeInditcatorSize),
		// Substates for gameActive
		getNextPlayer, throwDice, diceThrown, chooseFigureInField, moveFigure, addFigureToMatchField, chooseCategory, chooseNextQuestion, checkAnswer, questionAnsweredCorrectly, questionAnsweredWrong, knowledgeIndicatorAdjusted,
		// Complex State Game Active
		GameActive(getNextPlayer, throwDice, diceThrown, chooseFigureInField, moveFigure, addFigureToMatchField, chooseCategory,
				chooseNextQuestion, checkAnswer, questionAnsweredCorrectly, questionAnsweredWrong, knowledgeIndicatorAdjusted),
		// Game completed
		chooseRepeat, GameCompleted(chooseRepeat);

		/**
		 * @clientNavigability NAVIGABLE
		 * @directed true
		 * @supplierRole subState
		 */

		private List<IState> subStates;

		State(IState... subS) {
			this.subStates = new ArrayList<>(Arrays.asList(subS));
		}

		@Override
		public boolean isSuperStateOf(IState s) {
			boolean result = s == null || this == s; // self contained
			for (IState state : this.subStates) // or
				result |= state.isSuperStateOf(s); // contained in a substate!
			return result;
		}

		@Override
		public boolean isSubStateOf(IState state) {
			return (state == null) ? false : state.isSuperStateOf(this);
		}
	}

}
