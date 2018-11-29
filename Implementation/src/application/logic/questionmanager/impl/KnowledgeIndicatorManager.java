package application.logic.questionmanager.impl;

import java.util.HashMap;
import java.util.LinkedList;

import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.port.IKnowledgeIndicatorManager;

public class KnowledgeIndicatorManager implements IKnowledgeIndicatorManager {

	private Player[] players;
	private QuestionCategory[] categories;
	private int indicatorMin;
	private int indicatorMax;
	// Order of the KnowledgeIndicators is the same as the one in Categories
	HashMap<Player, KnowledgeIndicator[] > indicators;
	
	public KnowledgeIndicatorManager(Player[] players, QuestionCategory[] categories, int indicatorMin, int indicatorMax) {
		this.players = players;
		this.categories = categories;
		this.indicatorMin = indicatorMin;
		this.indicatorMax = indicatorMax;
		this.reset();
	}
	
	private void init() {
		for(Player player: this.players) {
			KnowledgeIndicator[] indicatorsOfPlayer = this.indicators.get(player);
			if(indicatorsOfPlayer == null || (indicatorsOfPlayer.length != this.categories.length)) {
				indicatorsOfPlayer = new KnowledgeIndicator[this.categories.length];
			}
			for(int i=0; i<this.categories.length; i++) {
				indicatorsOfPlayer[i] = new KnowledgeIndicator(this.indicatorMin, this.indicatorMax, categories[i], player);
			}
			this.indicators.put(player, indicatorsOfPlayer);
		}
	}
	
	@Override
	public void reset() {
		this.indicators = new HashMap<>();
		this.init();
	}

	@Override
	public boolean increase(Player player, QuestionCategory categoryOfIndicator) {
		return (this.getKIOfPlayer(player, categoryOfIndicator).increment() == this.indicatorMax);
	}

	@Override
	public boolean decrease(Player player, QuestionCategory categoryOfIndicator) {
		return (this.getKIOfPlayer(player, categoryOfIndicator).decrement() == this.indicatorMin);
	}

	@Override
	public boolean hasPlayerAllIndicatorsAtMax(Player player) {
		for(Player p: this.indicators.keySet()) {
			for(KnowledgeIndicator ki: this.indicators.get(p)) {
				if(!ki.isAtMax()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public QuestionCategory[] getNonMaxedOutCategories(Player player) {
		LinkedList<QuestionCategory> nonMaxedOut = new LinkedList<>();
		for(KnowledgeIndicator ki: this.indicators.get(player)) {
			if(!ki.isAtMax()) {
				nonMaxedOut.add(ki.getCategory());
			}
		}
		QuestionCategory[] asArray = new QuestionCategory[nonMaxedOut.size()];
		nonMaxedOut.toArray(asArray);
		return asArray;
	}

	@Override
	public Player[] playerWithMaxIndicators() {
		LinkedList<Player> winners = new LinkedList<>();
		for(Player player: this.players) {
			if(this.hasPlayerAllIndicatorsAtMax(player)) {
				winners.add(player);
			}
		}
		Player[] asArray = new Player[winners.size()];
		winners.toArray(asArray);
		return asArray;
	}
	
	// ==================== Utils ====================
	
	private KnowledgeIndicator getKIOfPlayer(Player player, QuestionCategory category) {
		for(KnowledgeIndicator ki: this.indicators.get(player)) {
			if(ki.getCategory().equals(category)) {
				return ki;
			}
		}
		return null;
	}

	@Override
	public KnowledgeIndicator[] getKnowledgeIndicators(Player player) {
		return this.indicators.get(player);
	}

	@Override
	public void setPlayers(Player[] players) {
		this.players = players;
	}

	@Override
	public void setKnowledgeIndicatorSize(int min, int max) {
		this.indicatorMin = min;
		this.indicatorMax = max;
	}

}
