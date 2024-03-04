package model;

import java.util.ArrayList;
import java.util.List;
public abstract class Character extends GameObject implements Demisable {

	private double life;
	private Direction direction;
	private List<DemisableObserver> observers;

	/**
	 * Character constructor
	 * @param life value of character's life
	 * @param posX the player's character's in the X axis
	 * @param posY the player's character's in the Y axis 
	 * @param direction the direction the character is facing
	 */
	public Character(int life, int posX, int posY, Direction direction) {
		super(posX, posY);
		this.direction = direction;
		this.life = life;
	}
	
	/**
	 * Attack the enemy in front of the character
	 * @param hit the amount of damage dealt
	 */
	public abstract void attack(double hit);

	@Override
	public void notifyDemisableObserver() {
		if(this instanceof Bot){
			GameObject hazqa = ((Bot)this).getHazqa();
			getObservers().forEach(obs -> obs.demise(this, hazqa));
		}else if(this instanceof Player){
			getObservers().forEach(obs -> obs.demise(this, null));
		}		
	}
	
	@Override
	public void addObserver(DemisableObserver observer) {
		if(observers == null){
			observers = new ArrayList<>(1);
		}
		observers.add(observer);
	}

	/**
	 * Get life
	 * @return return the value of character's life
	 */
	public double getLife() {
		return this.life;
	}

	/**
	 * Set life
	 * @param life change the value of character's life
	 */
	public void setLife(double life) {
		this.life = life;
	}

	/**
	 * Get direction
	 * @return return the direction the character is facing
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * Set direction
	 * @param direction the direction the player is facing
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * @return the observers
	 */
	public List<DemisableObserver> getObservers() {
		if(observers == null){
			observers = new ArrayList<>(0);
		}
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(List<DemisableObserver> observers) {
		this.observers = observers;
	}

}