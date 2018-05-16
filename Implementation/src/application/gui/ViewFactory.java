package application.gui;

import application.logic.gamelogic.IGameLogicFactory;

public class ViewFactory implements IViewFactory {

	// All interaction with model is directed over the gameLogic Facade
	IGameLogicFactory gameLogic;
	
	public ViewFactory(IGameLogicFactory gameLogic) {
		this.gameLogic = gameLogic;
	}
	
}
