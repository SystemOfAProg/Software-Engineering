package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePort;

public class GameLogicFactory implements IGameLogicFactory {

	
	public GameLogicFactory(){
		
	}

	@Override
	public IGamePort gamePort() {
		// Call for Game-Controlling_Method
		return null;
	};
	
}
