package application.logic.stateMachine.port;

import application.gui.port.IObserver;

public interface ISubject {

	void attach(IObserver obs);
	void detach(IObserver obs);
	void notifyObservers();
	
}
