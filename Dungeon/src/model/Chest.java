package model;

import java.util.ArrayList;
import java.util.List;

public class Chest extends GameObject implements Demisable{

	private Item hazqa;
	private List<DemisableObserver> observers;

	/**
	 * Chest constructor
	 * @param hazqa the Item in the chest
	 * @param posX the position of the chest in the X axis
	 * @param posY the position of the chest in the Y axis
	 */
	public Chest(Item hazqa, int posX, int posY) {
		super(posX, posY);
		this.hazqa = hazqa;
	}

	/**
	 * Open the chest and notify the observers for droping item at the chest position
	 */
	public void open(){
		hazqa.setPosX(getPosX());
		hazqa.setPosY(getPosY());
		notifyDemisableObserver();
	}

	@Override
	public void addObserver(DemisableObserver observer) {
		if(observers == null){
			observers = new ArrayList<>(1);
		}
		observers.add(observer);		
	}

	@Override
	public void notifyDemisableObserver() {
		getObservers().forEach(obs -> obs.demise(this, hazqa));
	}

	/**
	 * @return the observers
	 */
	public List<DemisableObserver> getObservers() {
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(List<DemisableObserver> observers) {
		this.observers = observers;
	}

	/**
	 * @return the hazqa
	 */
	public Item getHazqa() {
		return hazqa;
	}

	/**
	 * @param hazqa the hazqa to set
	 */
	public void setHazqa(Item hazqa) {
		this.hazqa = hazqa;
	}

}
