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

	boolean consoleVersion = true;

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
	public void showInputBoolean(String questionToAsk) {
		this.view.showInputBoolean(questionToAsk);
	}

	@Override
	public void showRetryInput(Exception iae) {
		this.view.showRetryInput(iae);
	}

	@Override
	public void showInputNumber(int min, int max) {
		this.view.showInputNumber(min, max);
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
