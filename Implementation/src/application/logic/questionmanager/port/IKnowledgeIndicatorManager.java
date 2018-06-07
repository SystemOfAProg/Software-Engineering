package application.logic.questionmanager.port;

import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.KnowledgeIndicator;
import application.logic.questionmanager.impl.QuestionCategory;

public interface IKnowledgeIndicatorManager {

	/**
	 * Resets all Indicators for all players. Keeps players and categories, just
	 * resets the progress.
	 */
	void reset();

	/**
	 * 
	 */
	KnowledgeIndicator[] getKnowledgeIndicators(Player player);

	/**
	 * Increases a players Knowledge Indicator for a specific question category.
	 * 
	 * @param player
	 *            Player whose indicator is supposed to be increased.
	 * @param category
	 *            Category of the indicator
	 * @return true, if indicators limit is reached
	 */
	boolean increase(Player player, QuestionCategory categoryOfIndicator);

	/**
	 * Decreases a players Knowledge Indicator for a specific question category.
	 * 
	 * @param player
	 *            Player whose indicator is supposed to be decreased.
	 * @param category
	 *            Category of the indicator
	 * @return true, if indicators reached lowest possible value
	 */
	boolean decrease(Player player, QuestionCategory categoryOfIndicator);

	/**
	 * Looks if a player has all his knowledge indicators at max.
	 * 
	 * @param player
	 *            Player to check
	 * @return true, if player has all indicators of all categories at max
	 */
	boolean hasPlayerAllIndicatorsAtMax(Player player);

	/**
	 * Gets Question Categories that are not maxed out and worth to answer a
	 * question.
	 * 
	 * @param player
	 *            Player whose indicators are inspected
	 * @return Array of Question Categories that do not have a full indicators
	 */
	QuestionCategory[] getNonMaxedOutCategories(Player player);

	/**
	 * Get all Players that have all question indicators of all categories at max
	 * possible state
	 * 
	 * @return Player that have their Knowledge Indicators at max.
	 */
	Player[] playerWithMaxIndicators();

}
