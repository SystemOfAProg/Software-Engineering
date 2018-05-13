package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePort;

public interface IGameLogicFactory {
	
	IGameLogicFactory FACTORY = new GameLogicFactory();
	
	IGamePort gamePort();
	
}
