package application.logic.stateMachine.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IState.State;
import application.logic.stateMachine.port.IStateMachine;

public class StateMachineComponentTest {

	private IStateMachine stateMachine;

	@Before
	public void initStateMachine() {
		if (this.stateMachine == null) {
			this.stateMachine = IStateMachineFactory.FACTORY.getStateMachinePort().getStateMachine();
		}
		this.stateMachine.resetCurrentState();
	}

	/**
	 * Checks if Setting and Getting States and Propagation to Observers works
	 * correctly. Also checks detaching Observer from State Machine.
	 */
	@Test
	public void testAllGameStates() {

		// Game not started
		TestObserverImplementation observer = new TestObserverImplementation();
		this.stateMachine.attach(observer);
		assertEquals(State.ChooseSettings, this.stateMachine.getState());
		assertEquals(State.ChooseSettings, observer.getCurrentState());
		// Game start -> Walk over every State of GameActive
		this.stateMachine.setState(State.getNextPlayer);
		assertEquals(State.getNextPlayer, this.stateMachine.getState());
		assertEquals(State.getNextPlayer, observer.getCurrentState());

		this.stateMachine.setState(State.throwDice);
		assertEquals(State.throwDice, this.stateMachine.getState());
		assertEquals(State.throwDice, observer.getCurrentState());

		this.stateMachine.setState(State.chooseFigureInField);
		assertEquals(State.chooseFigureInField, this.stateMachine.getState());
		assertEquals(State.chooseFigureInField, observer.getCurrentState());

		this.stateMachine.setState(State.chooseCategory);
		assertEquals(State.chooseCategory, this.stateMachine.getState());
		assertEquals(State.chooseCategory, observer.getCurrentState());

		this.stateMachine.setState(State.chooseNextQuestion);
		assertEquals(State.chooseNextQuestion, this.stateMachine.getState());
		assertEquals(State.chooseNextQuestion, observer.getCurrentState());

		this.stateMachine.setState(State.checkAnswer);
		assertEquals(State.checkAnswer, this.stateMachine.getState());
		assertEquals(State.checkAnswer, observer.getCurrentState());

		this.stateMachine.setState(State.moveFigure);
		assertEquals(State.moveFigure, this.stateMachine.getState());
		assertEquals(State.moveFigure, observer.getCurrentState());

		this.stateMachine.setState(State.adjustIndicators);
		assertEquals(State.adjustIndicators, this.stateMachine.getState());
		assertEquals(State.adjustIndicators, observer.getCurrentState());

		// End Game
		this.stateMachine.setState(State.GameCompleted);
		assertEquals(State.GameCompleted, this.stateMachine.getState());
		assertEquals(State.GameCompleted, observer.getCurrentState());

		this.stateMachine.detach(observer);
		this.stateMachine.setState(State.ChooseSettings);

		// State Machine has new State
		assertEquals(State.ChooseSettings, this.stateMachine.getState());

		// Observer does not know of new State -> is detached
		assertEquals(State.GameCompleted, observer.getCurrentState());
	}

}
