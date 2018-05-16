package application.logic.questionmanager;

import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Answer;
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
	private int indicatorSize;
	
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
					this.indicatorSize
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question[] getAllQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean answerQuestion(Answer answer, Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean answerQuestion(int index, Question question) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	// ==================== IKnowledgeIndicatorManager ====================
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
