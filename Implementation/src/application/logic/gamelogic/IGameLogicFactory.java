package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePort;
import application.logic.stateMachine.port.ISubject;

public interface IGameLogicFactory extends ISubject {
	
	IGameLogicFactory FACTORY = new GameLogicFactory();
	
	IGamePort getGamePort();
	void start();
	
}
