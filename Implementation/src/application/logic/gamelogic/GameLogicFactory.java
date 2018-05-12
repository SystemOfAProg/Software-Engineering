package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePort;
import application.logic.gamemodel.implementation.Game;

public class GameLogicFactory implements IGameLogicFactory {

	private Game game;
	
	public GameLogicFactory(){ }

	@Override
	public IGamePort gamePort() {
		// Call for Game-Controlling_Method
		return null;
	};
	
}
