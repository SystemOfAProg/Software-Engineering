package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGameStart;
import application.logic.stateMachine.port.IState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class JavaFXView extends Application implements IObserver, IView {

    IGameLogicFactory gameLogicFactory;
    private double xOffset = 0;
    private double yOffset = 0;
    private String pathToStartingScene = "../resources/scenes/scene.fxml";

    public JavaFXView() { }

    @Override
    public void startView(IGameLogicFactory logic, String[] args) {
        this.gameLogicFactory = logic;
        this.gameLogicFactory.attach(this);
        // TODO check how controller can be added
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Ich weiß was, was du nicht weißt.");
        this.setStageSize(stage);
        Scene startingScene = new Scene(this.loadScene(this.pathToStartingScene));
        stage.setScene(startingScene);
        stage.setResizable(true);
        this.setApplicationIcon();
        stage.show();
    }

    public void setStageSize(Stage stage) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        stage.setX(bounds.getMinX() + bounds.getWidth()*0.05);
        stage.setY(bounds.getMinY() + bounds.getHeight()*0.05);
        stage.setWidth(bounds.getWidth() - bounds.getWidth()*0.1);
        stage.setHeight(bounds.getHeight() - bounds.getHeight()*0.1);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
    }

    public void setApplicationIcon() {
        try {
            // macOS
            URL iconURL = JavaFXView.class.getResource("../resources/icons/icon.png");
            Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            // Windows and Linux
        }
    }

    public Parent loadScene(String pathToScene) throws IOException {
        return FXMLLoader.load(getClass().getResource(pathToScene));
    }

    public Parent loadSceneBorderLess(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource(this.pathToStartingScene));
        root.setOnMousePressed(event -> {
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - this.xOffset);
            stage.setY(event.getScreenY() - this.yOffset);
        });
        return root;
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
