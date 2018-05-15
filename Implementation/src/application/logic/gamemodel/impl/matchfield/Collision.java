package application.logic.gamemodel.impl.matchfield;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.player.Player;

public class Collision {

	private boolean isCollision;
	private Player first;
	private Player second;
	private AField field;
	
	/**
	 * Creates Collision Object that represents no collision
	 */
	public Collision() {
		this.isCollision = false;
		this.field = null;
		this.second = null;
		this.field = null;
	}
	
	/**
	 * Creates happened collision.
	 * @param first 	Player that already was placed on field
	 * @param second 	Player that was newly moved to the field
	 * @param field 	Field where the collision happened
	 */
	public Collision(Player first, Player second, AField field) {
		this.isCollision = true;
		this.first = first;
		this.second = second;
		this.field = field;
	}
	
	public boolean collisionHappened() {
		return this.isCollision;
	}
	
	public Player getFirstPlayer() {
		return this.first;
	}
	
	public Player getSecondPlayer() {
		return this.second;
	}
	
	public AField getCollisionField() {
		return this.field;
	}
	
}
