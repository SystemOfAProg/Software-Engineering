package application.logic.gamelogic;

import application.logic.gamelogic.impl.GamePlay;
import application.logic.gamelogic.impl.GameStart;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGamePort;
import application.logic.gamelogic.port.IGameStart;

public class GameLogicFactory implements IGameLogicFactory, IGamePort {

	public GameLogicFactory(){ }
	
	private IGamePlay gamePlay;
	private IGameStart gameStart;

	@Override
	public IGamePort gamePort() {
		return this;
	};
	
	public IGamePlay getGamePlay() {
		if(this.gamePlay == null) {
			this.gamePlay = new GamePlay();
		}
		return this.gamePlay;
	}
	
	public IGameStart getGameStart() {
		if(this.gameStart == null) {
			this.gameStart = new GameStart();
		}
		return this.gameStart;
	}
	
}
