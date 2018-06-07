package application.gui;

import application.gui.port.IViewPort;

public interface IViewFactory {

	IViewFactory FACTORY = new ViewFactory();

	IViewPort getViewPort();

}
