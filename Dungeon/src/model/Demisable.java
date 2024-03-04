package model;

public interface Demisable {

	/**
	 * Add an observer to the demisable
	 * @param observer
	 */
	void addObserver(DemisableObserver observer);

	/**
	 * Notify all observers of the demisable
	 */
	void notifyDemisableObserver();

}