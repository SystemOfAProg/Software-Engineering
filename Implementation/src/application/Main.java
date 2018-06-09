package application;

import application.gui.IViewFactory;
import application.logic.gamelogic.IGameLogicFactory;

public class Main {

	public static void main(String[] args) {
		// Create Game Logic
		IGameLogicFactory logic = IGameLogicFactory.FACTORY;
		// Create Game View
		IViewFactory view = IViewFactory.FACTORY;
		// Link Logic to View
		view.getViewPort().getView().addGameLogic(logic);
		logic.start();
		System.out.println("Ende");
	}

}
