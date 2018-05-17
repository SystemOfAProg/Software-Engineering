package application.logic.gamesettings.impl;

import application.logic.gamesettings.port.IGameQuestionSettings;

public class GameQuestionSettings implements IGameQuestionSettings {

	private int knowledgeIndicatorSize = this.DEFAULT_KISIZE;
	
	@Override
	public void setKnowledgeIndicatorSize(int size) {
		this.knowledgeIndicatorSize = size;
		
	}

	@Override
	public int getKnowledgeIndicatorSize() {
		return this.knowledgeIndicatorSize;
	}
	
	@Override
	public void resetQuestionSettings() {
		this.knowledgeIndicatorSize = this.DEFAULT_KISIZE;
	}


}
