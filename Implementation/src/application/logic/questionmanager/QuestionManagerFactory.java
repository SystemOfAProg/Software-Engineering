package application.logic.questionmanager;

import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.KnowledgeIndicator;
import application.logic.questionmanager.impl.KnowledgeIndicatorManager;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionAsker;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.questionmanager.port.IKnowledgeIndicatorManager;
import application.logic.questionmanager.port.IQuestionAsker;
import application.logic.questionmanager.port.IQuestionManagerPort;

public class QuestionManagerFactory implements
	IQuestionManagerFactory, IQuestionManagerPort, IQuestionAsker, IKnowledgeIndicatorManager {
	
	private QuestionAsker questionAsker;
	private KnowledgeIndicatorManager knowledgeIndicatorManager;
	
	private Player[] players;
	private QuestionCategory[] categories;
	private int indicatorMax;
	private int indicatorMin;
	
	// For Lazy Creation of Components
	private void mkQuestionAsker() {
		if(this.questionAsker == null) {
			this.questionAsker = new QuestionAsker();
		}
	}
	
	// For Lazy Creation of Components
	private void mkKnowledgeIndicatorManager() {
		if(this.knowledgeIndicatorManager == null) {
			this.knowledgeIndicatorManager = new KnowledgeIndicatorManager(
					this.players,
					this.categories,
					this.indicatorMin,
					this.indicatorMax
			);
		}
	}

	// ==================== QuestionManagerFactory ====================
	@Override
	public IQuestionManagerPort getQuestionManagerPort() {
		return this;
	}
	
	// ==================== IQuestionManagerPort ====================
	@Override
	public IQuestionAsker getQuestionAsker() {
		return this;
	}
	
	@Override
	public IKnowledgeIndicatorManager getKnowledgeIndicatorManager() {
		return this;
	}
	
	// ==================== IQuestionAsker ====================
	@Override
	public Question getNextQuestion(QuestionCategory category) {
		this.mkQuestionAsker();
		return this.questionAsker.getNextQuestion(category);
	}

	@Override
	public Question[] getAllQuestions() {
		this.mkQuestionAsker();
		return this.questionAsker.getAllQuestions();
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		this.mkQuestionAsker();
		return this.questionAsker.getQuestionCategories();
	}

	@Override
	public boolean answerQuestion(Answer answer, Question question) {
		this.mkQuestionAsker();
		return this.questionAsker.answerQuestion(answer, question);
	}

	@Override
	public boolean answerQuestion(int index, Question question) {
		this.mkQuestionAsker();
		return this.questionAsker.answerQuestion(index, question);
	}
	
	
	// ==================== IKnowledgeIndicatorManager ====================
	@Override
	public void reset() {
		this.mkKnowledgeIndicatorManager();
		this.knowledgeIndicatorManager.reset();
	}

	@Override
	public boolean increase(Player player, QuestionCategory categoryOfIndicator) {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.increase(player, categoryOfIndicator);
	}

	@Override
	public boolean decrease(Player player, QuestionCategory categoryOfIndicator) {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.decrease(player, categoryOfIndicator);
	}

	@Override
	public boolean hasPlayerAllIndicatorsAtMax(Player player) {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.hasPlayerAllIndicatorsAtMax(player);
	}

	@Override
	public QuestionCategory[] getNonMaxedOutCategories(Player player) {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.getNonMaxedOutCategories(player);
	}

	@Override
	public Player[] playerWithMaxIndicators() {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.playerWithMaxIndicators();
	}

	@Override
	public KnowledgeIndicator[] getKnowledgeIndicators(Player player) {
		this.mkKnowledgeIndicatorManager();
		return this.knowledgeIndicatorManager.getKnowledgeIndicators(player);
	}

}
