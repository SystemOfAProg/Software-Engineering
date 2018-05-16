package application.logic.questionmanager.port;

public interface IQuestionManagerPort {

	IQuestionAsker getQuestionAsker();
	IKnowledgeIndicatorManager getKnowledgeIndicatorManager();
	
}
