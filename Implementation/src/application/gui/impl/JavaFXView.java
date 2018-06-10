package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGameStart;
import application.logic.stateMachine.port.IState;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;

public class JavaFXView extends Application implements IObserver, IView {

    IGameLogicFactory gameLogicFactory;
    Button startGameButton;
    Button editQuestionsButton;

    public JavaFXView() { }

    @Override
    public void startView(IGameLogicFactory logic, String[] args) {
        this.gameLogicFactory = logic;
        this.gameLogicFactory.attach(this);
        // TODO maybe initialize an controller
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ich weiß was, was du nicht weißt.");
        this.startGameButton = new Button("Start the game");
        this.editQuestionsButton = new Button("Edit Questions");
        StackPane layout = new StackPane();
        layout.getChildren().add(startGameButton);
        layout.getChildren().add(editQuestionsButton);
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    @Override
    public void update(IState state) {
        IGamePlay gamePlay = this.gameLogicFactory.getGamePort().getGamePlay();
        IGameStart gameStart = this.gameLogicFactory.getGamePort().getGameStart();
        if (state.isSubStateOf(IState.State.GameActive)) {
            if (state == IState.State.getNextPlayer) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.throwDice) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseFigureInField) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.addFigureToMatchField) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseCategory) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseNextQuestion) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.checkAnswer) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.moveFigure) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.adjustIndicators) {
                // TODO read game model from logic and print current state
            }
        } else if (state.isSubStateOf(IState.State.ChooseSettings)) {
            if(state == IState.State.showTutorial) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.useStandardSettings) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.choosePlayerCount) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseFieldsPerPlayer) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseFiguresPerPlayer) {
                // TODO read game model from logic and print current state
            } else if (state == IState.State.chooseKnowledgeInditcatorSize) {
                // TODO read game model from logic and print current state
            }
        } else if (state.isSubStateOf(IState.State.GameCompleted)) {
            if (state == IState.State.chooseRepeat) {
                // TODO read game model from logic and print current state
            }
        } else {
            throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
        }
    }

}
