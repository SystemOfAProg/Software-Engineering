package application.gui.port;

import application.logic.gamelogic.IGameLogicFactory;

public interface IView {

    void startView(IGameLogicFactory logic, String[] args);

    void showInputBoolean(String questionToAsk);

    void showRetryInput(Exception iae);

    void showInputNumber(int min, int max);
}
