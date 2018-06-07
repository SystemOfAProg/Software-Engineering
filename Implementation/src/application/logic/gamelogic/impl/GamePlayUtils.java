package application.logic.gamelogic.impl;

import application.logic.dice.IDiceFactory;
import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;

/**
 * Utility class for common ise functions for GamePlay
 * Only purpose is cleaning up the gameplay-code a little bit
 * @author fabiansorn
 *
 */
public class GamePlayUtils {

	IGameModelFactory game;
	IDiceFactory dice;
	GamePlayData data;
	
	public GamePlayUtils(IGameModelFactory game, IDiceFactory dice, GamePlayData data) {
		this.game = game;
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
		if(this.getCurrentPlayer().getFiguresInField().length < this.getCurrentPlayer().getFigures().length) {
			for(Figure figure: this.getCurrentPlayer().getFiguresInField()) {
				if(figure.isOnStartingField()) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void IncrCurrentPlayerIndex() {
		if(data.currentPlayerIndex >= (game.getGameModelPort().getGameModel().getPlayers().length - 1)) {
			data.currentPlayerIndex = 0;
		} else {
			data.currentPlayerIndex++;
		}
	}
	
}
