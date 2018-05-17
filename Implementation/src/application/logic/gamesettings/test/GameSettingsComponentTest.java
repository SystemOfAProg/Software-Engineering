package application.logic.gamesettings.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.logic.gamesettings.IGameSettingsFactory;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.gamesettings.port.IGameSettingsPort;

public class GameSettingsComponentTest {

	private IGameSettingsPort gameSettingsPort;
	private IGameModelSettings gameModelSettings;
	private IGameQuestionSettings gameQuestionSettings;
	
	@Before
	public void initGameSettingsPort() {
		this.gameSettingsPort = IGameSettingsFactory.FACTORY.getGameSettingsPort();
		this.gameModelSettings = this.gameSettingsPort.getGameModelSettings();
		this.gameQuestionSettings = this.gameSettingsPort.getGameQuestionSettings();
		this.gameModelSettings.resetModelSettings();
		this.gameQuestionSettings.resetQuestionSettings();
	}
	
	@Test
	public void testGameModelSettings() {
		// Check default settings
		assertEquals(IGameModelSettings.DEFAULT_FIELDSPERPLAYER, this.gameModelSettings.getFieldsPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_FIGURESPERPLAYER, this.gameModelSettings.getFiguresPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_PLAYERCOUNT, this.gameModelSettings.getPlayerCount());
		// Set custom settings
		this.gameModelSettings.setFieldsPerPlayer(IGameModelSettings.DEFAULT_FIELDSPERPLAYER + 1);
		this.gameModelSettings.setFiguresPerPlayer(IGameModelSettings.DEFAULT_FIGURESPERPLAYER + 1);
		this.gameModelSettings.setPlayerCount(IGameModelSettings.DEFAULT_PLAYERCOUNT + 1);
		// Check custom settings
		assertEquals(IGameModelSettings.DEFAULT_FIELDSPERPLAYER + 1, this.gameModelSettings.getFieldsPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_FIGURESPERPLAYER + 1, this.gameModelSettings.getFiguresPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_PLAYERCOUNT + 1, this.gameModelSettings.getPlayerCount());
		// Reset back to defaults
		this.gameModelSettings.resetModelSettings();
		// Check default Settings
		assertEquals(IGameModelSettings.DEFAULT_FIELDSPERPLAYER, this.gameModelSettings.getFieldsPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_FIGURESPERPLAYER, this.gameModelSettings.getFiguresPerPlayer());
		assertEquals(IGameModelSettings.DEFAULT_PLAYERCOUNT, this.gameModelSettings.getPlayerCount());
	}
	
	@Test
	public void testGameQuestionSettings() {
		// Check default settings
		assertEquals(IGameQuestionSettings.DEFAULT_KISIZE, this.gameQuestionSettings.getKnowledgeIndicatorSize());
		this.gameQuestionSettings.setKnowledgeIndicatorSize(IGameQuestionSettings.DEFAULT_KISIZE + 1);
		assertEquals(IGameQuestionSettings.DEFAULT_KISIZE + 1, this.gameQuestionSettings.getKnowledgeIndicatorSize());
		this.gameQuestionSettings.resetQuestionSettings();
		assertEquals(IGameQuestionSettings.DEFAULT_KISIZE, this.gameQuestionSettings.getKnowledgeIndicatorSize());
	}

}
