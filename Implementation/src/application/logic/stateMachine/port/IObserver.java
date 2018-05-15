package application.logic.stateMachine.port;

public interface IObserver {

	void update(IState newSate);
	
}
