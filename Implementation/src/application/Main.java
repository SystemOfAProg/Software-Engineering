package application;

import application.gui.IViewFactory;
import application.gui.ViewFactory;
import application.logic.gamelogic.IGameLogicFactory;

// Meta-Klasse "System" aus Foliensatz 2, Seite 221
public class Main {
	
	public static void main(String[] args) {
		// ~~~~~ Setup Process ~~~~~
		// Create Model
		IGameLogicFactory logicFactory = IGameLogicFactory.FACTORY;
		IViewFactory viewFactory = new ViewFactory(logicFactory);
		// Create View with reference to model
		// View attaches itself to the model
		// View creates a controller with reference to itself and the model
		// Controller attaches itself to the Model
		// ~~~~~ Start Event Loop ~~~~~
		// Start the actual Game
	}

}
