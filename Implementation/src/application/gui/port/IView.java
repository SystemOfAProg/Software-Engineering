package application.gui.port;

import application.logic.gamelogic.IGameLogicFactory;

public interface IView {

    void addGameLogic(IGameLogicFactory logic);

}
