package application.logic.questionmanager.impl;

import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.port.IKnowledgeIndicatorManager;

public class KnowledgeIndicatorManager implements IKnowledgeIndicatorManager {

	private Player[] players;
	private QuestionCategory[] categories;
	private int indicatorSize;
	
	public KnowledgeIndicatorManager(Player[] players, QuestionCategory[] categories, int indicatorSize) {
		this.players = players;
		this.categories = categories;
		this.indicatorSize = indicatorSize;
		this.init(players, categories, indicatorSize);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Player[] players, QuestionCategory[] categories, int indicatorSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean increase(Player player, QuestionCategory categoryOfIndicator) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean decrease(Player player, QuestionCategory categoryOfIndicator) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPlayerAllIndicatorsAtMax(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public QuestionCategory[] getNonMaxedOutCategories(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player[] playerWithMaxIndicator() {
		// TODO Auto-generated method stub
		return null;
	}

}
