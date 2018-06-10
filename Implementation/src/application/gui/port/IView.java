package application.gui.port;

import application.logic.gamelogic.IGameLogicFactory;

public interface IView {

    void startView(IGameLogicFactory logic, String[] args);

}
