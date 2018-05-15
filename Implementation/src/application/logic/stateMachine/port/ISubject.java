package application.logic.stateMachine.port;

public interface ISubject {

	void attach(IObserver obs);
	
	void detach(IObserver obs);
	
}
