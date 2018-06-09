package application.gui;

import application.gui.impl.Controller;
import application.gui.impl.View;
import application.gui.port.IController;
import application.gui.port.IView;
import application.gui.port.IViewPort;
import application.logic.gamelogic.IGameLogicFactory;

public class ViewFactory implements IViewFactory, IViewPort, IView, IController {

	IController controller;
	IView view;

	public ViewFactory() { }

	/**
	 * Add an existing game logic object to work on. This can be used to read the
	 * state the current game is in.
	 * @param gameLogic Object representing the complete logic of the game
	 */
	@Override
	public void addGameLogic(IGameLogicFactory gameLogic) {
		this.view = new View(gameLogic);
		this.view.addGameLogic(gameLogic);
	}

	@Override
	public IViewPort getViewPort() {
		return this;
	}

	@Override
	public IView getView() {
		return this;
	}

	@Override
	public IController getController() {
		return this;
	}

}
