package application.gui.port;

import application.logic.stateMachine.port.IState;

public interface IObserver {

	void update(IState newSate);
	
}
