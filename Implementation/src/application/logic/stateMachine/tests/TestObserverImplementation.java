package application.logic.stateMachine.tests;

import application.gui.port.IObserver;
import application.logic.stateMachine.port.IState;

public class TestObserverImplementation implements IObserver {

	private IState currentState;
	
	public TestObserverImplementation() {
		this.currentState = null;
	}
	
	@Override
	public void update(IState newSate) {
		this.currentState = newSate;
	}
	
	public IState getCurrentState() {
		return this.currentState;
	}

}
