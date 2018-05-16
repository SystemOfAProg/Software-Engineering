package application.logic.questionmanager;

import application.logic.questionmanager.port.IQuestionManagerPort;

public interface IQuestionManagerFactory {

	IQuestionManagerFactory FACTORY = new QuestionManagerFactory();
	
	IQuestionManagerPort getQuestionManagerPort();
	
}
