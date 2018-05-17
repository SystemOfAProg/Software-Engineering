package application.logic.stateMachine;

import application.gui.port.IObserver;
import application.logic.stateMachine.impl.StateMachine;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IStateMachine;
import application.logic.stateMachine.port.IStateMachinePort;

public class StateMachineFactory implements IStateMachineFactory, IStateMachinePort, IStateMachine {

	private IStateMachine stateMachine;
	
	private void mkStateMachine() {
		if(this.stateMachine == null) {
			this.stateMachine = new StateMachine();
		}
	}

	// ==================== IStateMachineFactory ====================
	
	@Override
	public IStateMachinePort getStateMachinePort() {
		return this;
	}

	// ==================== IStateMachinePort ====================
	
	@Override
	public IStateMachine getStateMachine() {
		return this;
	}
	
	// ==================== IStateMachine ====================
	
	@Override
	public void setState(IState state) {
		this.mkStateMachine();
		this.stateMachine.setState(state);
	}
	
	@Override
	public IState getState() {
		this.mkStateMachine();
		return this.stateMachine.getState();
	}

	@Override
	public IState resetCurrentState() {
		this.mkStateMachine();
		return this.stateMachine.resetCurrentState();
	}

	@Override
	public void attach(IObserver obs) {
		this.mkStateMachine();
		this.stateMachine.attach(obs);
	}

	@Override
	public void detach(IObserver obs) {
		this.mkStateMachine();
		this.stateMachine.detach(obs);
	}

	@Override
	public void notifyObservers() {
		this.mkStateMachine();
		this.stateMachine.notifyObservers();
	}
	
}
