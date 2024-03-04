package model;

public interface DemisableObserver {

	/**
	 * Operation to do after a demise
	 * @param demisable
	 */
	void demise(Demisable demisable, GameObject loot);

}