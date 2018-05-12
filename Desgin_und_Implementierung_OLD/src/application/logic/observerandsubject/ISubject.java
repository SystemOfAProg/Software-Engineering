package application.logic.observerandsubject;

public interface ISubject {

	void attach(IObserver obs);
	
	void detach(IObserver obs);
	
}
