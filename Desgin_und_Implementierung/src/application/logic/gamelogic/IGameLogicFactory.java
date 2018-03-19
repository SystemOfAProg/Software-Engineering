package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePort;

public interface IGameLogicFactory {
	
	IGameLogicFactory gameLogicFacotry = new GameLogicFactory();
	
	IGamePort gamePort();
	
}
