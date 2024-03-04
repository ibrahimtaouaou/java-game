package model;
public class Boss extends Bot {

	private boolean alreadyActivated;

	/**
	 * Boss constructor
	 * @param life value of boss's life
	 * @param hazqa a GameObject
	 * @param defense value of boss's defense
	 * @param strength value of boss's strength
	 * @param posX the boss's position in the X axis
	 * @param posY the boss's position in the Y axis 
	 * @param direction the direction the boss is facing
	 */
	public Boss(int life, GameObject hazqa, double defense, double strength, int posX, int posY, Direction direction) {
		super("Giant", life, hazqa, defense, strength, posX, posY, direction);
	}

	/**
	 * activate the boss's skill
	 */
	public void activateSkill() {
		if(!alreadyActivated){
			alreadyActivated = true;
			this.setLife(getLife() + ((getMAX_HEALTH() - getLife())/1.5));
		}
	}

	@Override
	public void attack(double hit) {
		this.setLife(getLife()- hit * (1 - getDefense()/200));
		if(getLife() <= getMAX_HEALTH() / 2){
			activateSkill();
		}
		if (getLife() <= 0){
			setLife(0);
			getHazqa().setPosX(getPosX());
			getHazqa().setPosY(getPosY());
			notifyDemisableObserver();
		}
	}

	/**
	 * @return the alreadyActivated
	 */
	public boolean isAlreadyActivated() {
		return alreadyActivated;
	}

	/**
	 * @param alreadyActivated the alreadyActivated to set
	 */
	public void setAlreadyActivated(boolean alreadyActivated) {
		this.alreadyActivated = alreadyActivated;
	}

}