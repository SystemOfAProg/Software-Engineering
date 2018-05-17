package application.logic.stateMachine.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IStateMachine;

public class StateMachineComponentTest {

	private IStateMachine stateMachine;
	
	@Before
	public void initStateMachine() {
		if(this.stateMachine == null) {
			this.stateMachine = IStateMachineFactory.FACTORY.getStateMachinePort().getStateMachine();
		}
	}
	
	@After
	public void resetStateMachine() {
		this.stateMachine.resetCurrentState();
	}
	
	@Test
	public void test() {
		
	}

}
