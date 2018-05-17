package application.logic.stateMachine.port;

public interface IStateMachine {

	public void setState(IState state);

	public IState getState();
	
	public IState resetCurrentState();
	
}
