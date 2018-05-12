package application.logic.dice;

import java.util.Random;

public class Dice {

	private Random random;
	private int lowerBound;
	private int upperBound;
	
	/**
	 * creates Dice with custom lower and upper bound.
	 */
	public Dice(int lowerBound, int upperBound) {
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
		this.random = new Random();
	}
	
	/**
	 * creates multiple standard Dices with lowerBound=1 and upperBound=6
	 */
	public Dice(int standardDiceCount) {
		this.upperBound = 6*standardDiceCount;
		this.lowerBound = 1*standardDiceCount;
		this.random = new Random();
	}
	
	/**
	 * creates standard Dice with lowerBound=1 and upperBound=6
	 */
	public Dice() {
		this.upperBound = 6;
		this.lowerBound = 1;
		this.random = new Random();
	}
	
	public int roll() {
		return (random.nextInt(upperBound - lowerBound) + lowerBound);
	}
	
}
