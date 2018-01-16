package application.logic.gamelogic;

import application.logic.gamelogic.port.GamePort;

public interface GameLogicFactory {
	
	GameLogicFactory gameLogicFacotry = new GameLogicFactoryImpl();
	
	GamePort gamePort();
	
}
