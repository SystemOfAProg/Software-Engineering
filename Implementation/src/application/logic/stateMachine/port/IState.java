package application.logic.stateMachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IState {
	
	public boolean isSubStateOf(IState state);

	public boolean isSuperStateOf(IState state);

	public enum State implements IState {

		// Initial State
		GameNotStarted,
		// Substates for gameActive
		getNextPlayer, throwDice, chooseFigureInField, addFigureToMatchField, chooseCategory,
		chooseNextQuestion, checkAnswer, moveFigure, adjustIndicators,
		// Complex State Game Active
		GameActive(
				getNextPlayer,
				throwDice,
				chooseFigureInField,
				addFigureToMatchField,
				chooseCategory,
				chooseNextQuestion,
				checkAnswer,
				moveFigure,
				adjustIndicators
		),
		// Game completed
		GameCompleted;

		/**
		 * @clientNavigability NAVIGABLE
		 * @directed true
		 * @supplierRole subState
		 */

		private List<IState> subStates;

		private State(IState... subS) {
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
