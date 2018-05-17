package application.logic.stateMachine.port;

public interface IStateMachine extends ISubject {

	public void setState(IState state);

	public IState getState();
	
	public IState resetCurrentState();
	
}
