package application.logic.stateMachine.impl;

import java.util.List;

import application.gui.port.IObserver;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;
import application.logic.stateMachine.port.IStateMachine;
import application.logic.stateMachine.port.ISubject;;

public class StateMachine implements IStateMachine, ISubject {

	private IState currentState;
	private List<IObserver> observers;
	
	public StateMachine() {
		this.currentState = State.GameNotStarted;
	}
	
	@Override
	public void setState(IState state) {
		this.currentState = state;
	}

	@Override
	public IState getState() {
		return this.currentState;
	}

	@Override
	public void attach(IObserver obs) {
		this.observers.add(obs);
		obs.update(this.currentState);
	}

	@Override
	public void detach(IObserver obs) {
		this.observers.remove(obs);
	}

	@Override
	public IState resetCurrentState() {
		this.setState(State.GameNotStarted);
		return this.getState();
	}

}
