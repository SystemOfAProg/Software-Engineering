package application.logic.gamelogic.impl;

import application.logic.dice.IDiceFactory;
import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.IQuestionManagerFactory;
import application.logic.questionmanager.impl.QuestionCategory;

/**
 * Utility class for common ise functions for GamePlay Only purpose is cleaning
 * up the gameplay-code a little bit
 * 
 * @author fabiansorn
 *
 */
public class GamePlayUtils {

	IGameModelFactory game;
	IQuestionManagerFactory questions;
	IDiceFactory dice;
	GamePlayData data;

	public GamePlayUtils(IGameModelFactory game, IQuestionManagerFactory questions, IDiceFactory dice,
			GamePlayData data) {
		this.game = game;
		this.questions = questions;
		this.dice = dice;
		this.data = data;
	}

	public Player getCurrentPlayer() {
		return game.getGameModelPort().getGameModel().getPlayers()[data.currentPlayerIndex];
	}

	public boolean currentPlayerHasFigureInField() {
		return this.getCurrentPlayer().getFiguresInField().length > 0;
	}

	public boolean figureAddingAllowed(int result) {
		return result == dice.getDicePort().getDice().getUpperBound();
	}

	public boolean figureCanBeAdded() {
		if (this.getCurrentPlayer().getFiguresInField().length < this.getCurrentPlayer().getFigures().length) {
			for (Figure figure : this.getCurrentPlayer().getFiguresInField()) {
				if (figure.isOnStartingField()) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean wouldCollideWithFigureFromSamePlayer() {
		Figure currentF = this.getCurrentFigure();
		for(Figure figure: this.getCurrentPlayer().getFiguresInField()) {
			if(figure != currentF) {
				AField start = figure.getCurrentLocation();
				AField stop = currentF.getCurrentLocation();
				Matchfield field = this.game.getGameModelPort().getGameModel().getMatchfield();
				if (field.calculateStepsBetweenFields(start,stop) == this.dice.getDicePort().getDice().getLastResult()) {
					return true;
				}
			}
		}
		return false;
	}

	public void IncrCurrentPlayerIndex() {
		if (data.currentPlayerIndex >= (game.getGameModelPort().getGameModel().getPlayers().length - 1)) {
			data.currentPlayerIndex = 0;
		} else {
			data.currentPlayerIndex++;
		}
	}

	public QuestionCategory getCurrentCategory() {
		QuestionCategory[] categories = this.questions.getQuestionManagerPort().getQuestionAsker()
				.getQuestionCategories();
		if(this.data.currentFigureIndex > categories.length && categories.length != 0) {
			return categories[categories.length -1];
		} else {
			return categories[this.data.currentCategoryIndex - 1];
		}
	}

	public boolean existsWinner() {
		return this.questions.getQuestionManagerPort().getKnowledgeIndicatorManager()
				.playerWithMaxIndicators().length > 0;
	}

	public Figure getCurrentFigure() {
		Figure[] figuresInField = this.getCurrentPlayer().getFiguresInField();
		if((this.data.currentFigureIndex < figuresInField.length) && !(this.data.currentFigureIndex < 0)) {
			return figuresInField[figuresInField.length -1];
		} else {
			return figuresInField[this.data.currentFigureIndex - 1];
		}
	}

}
