package application.logic.stateMachine.impl;

import java.util.ArrayList;
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
		this.observers = new ArrayList<>();
	}
	
	@Override
	public void setState(IState state) {
		this.currentState = state;
		this.notifyObservers();
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
	public void notifyObservers() {
		for(IObserver obs: this.observers) {
			obs.update(this.currentState);
		}
	}

	@Override
	public IState resetCurrentState() {
		this.setState(State.GameNotStarted);
		return this.getState();
	}

}
