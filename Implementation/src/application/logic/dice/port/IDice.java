package application.logic.dice.port;

public interface IDice {

	int roll();
	int getLastResult();
	int getLowerBound();
	int getUpperBound();
	
}
