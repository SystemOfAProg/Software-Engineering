package application.logic.stateMachine;

import application.logic.stateMachine.port.IStateMachinePort;

public interface IStateMachineFactory {

	IStateMachineFactory FACTORY = new StateMachineFactory();
	
	IStateMachinePort getStateMachinePort();
	
}
