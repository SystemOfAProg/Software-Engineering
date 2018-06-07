package application;

import application.gui.IViewFactory;
import application.logic.gamelogic.IGameLogicFactory;

public class Main {

	public static void main(String[] args) {
		IGameLogicFactory logicFactory = IGameLogicFactory.FACTORY;
		IViewFactory viewFactory = IViewFactory.FACTORY;
		// TODO implement start of game
		// Create View with reference to model
		// View attaches itself to the model
		// View creates a controller with reference to itself and the model
		// Controller attaches itself to the Model
		// ~~~~~ Start Event Loop ~~~~~
		// Start the actual Game
	}

}
