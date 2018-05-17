package application.logic.gamesettings.port;

public interface IGameQuestionSettings {

	public final int DEFAULT_KISIZE = 5;
	
	/**
	 * Set how many steps the KnowledgeIndicator should have.
	 * @param size Steps of the KnowledgeIndicator
	 */
	public void setKnowledgeIndicatorSize(int size);
	
	/**
	 * Get How many Steps a KnowledgeIndicator should have.
	 * @return Steps
	 */
	public int getKnowledgeIndicatorSize();

	/**
	 * Reset settings to defaults
	 */
	public void resetQuestionSettings();

}
