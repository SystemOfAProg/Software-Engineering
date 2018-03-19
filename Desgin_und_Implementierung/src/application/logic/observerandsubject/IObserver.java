package application.logic.observerandsubject;

import application.logic.stateMachine.IState;

public interface IObserver {

	void update(IState newSate);
	
}
