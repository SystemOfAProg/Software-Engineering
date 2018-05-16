package application.logic.gamemodel;

import application.logic.gamemodel.port.IGameModelPort;

public interface IGameModelFactory {

	IGameModelFactory FACTORY = new GameModelFactory();
	
	IGameModelPort getGameModelPort();
	
}
