package application.gui;

import application.gui.impl.ConsoleView;
import application.gui.impl.JavaFXView;
import application.gui.port.IController;
import application.gui.port.IView;
import application.gui.port.IViewPort;
import application.logic.gamelogic.IGameLogicFactory;

public class ViewFactory implements IViewFactory, IViewPort, IView, IController {

	IController controller;
	IView view;

	boolean consoleVersion = false;

	public ViewFactory() { }

	@Override
	public void startView(IGameLogicFactory logic, String[] args) {
		if(consoleVersion) {
			this.view = new ConsoleView();
		} else {
			this.view = new JavaFXView();
		}
		this.view.startView(logic, args);
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
