package application.gui;

import application.gui.impl.Controller;
import application.gui.port.IController;
import application.gui.port.IViewPort;
import application.logic.gamelogic.IGameLogicFactory;

public class ViewFactory implements IViewFactory {

	IGameLogicFactory gameLogic;
	IController controller;

	public ViewFactory() {
	}

	public void addGameLogic(IGameLogicFactory gameLogic) {
		this.gameLogic = gameLogic;
		this.controller = new Controller(this.gameLogic, this);
	}

	@Override
	public IViewPort getViewPort() {
		// TODO Auto-generated method stub
		return null;
	}

}
