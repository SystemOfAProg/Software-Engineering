package application.logic.gamelogic;

import application.logic.gamelogic.port.GamePort;

public class GameLogicFactoryImpl implements GameLogicFactory {

	public GameLogicFactoryImpl(){
		
	}

	@Override
	public GamePort gamePort() {
		// Call for Game-Controlling_Method
		return null;
	};
	
}
