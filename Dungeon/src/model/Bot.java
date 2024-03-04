package model;

public class Bot extends Character {
	
	private double defense;
	private double strength;
	private String name;
	private GameObject hazqa;
	private final int MAX_HEALTH;

	/**
	 * Bot constructor
	 * @param name the bot name
	 * @param life value of bot's life
	 * @param hazqa the item dropped after demise
	 * @param defense value of bot's defense
	 * @param strength value of bot's strength
	 * @param posX the bot's position in the X axis
	 * @param posY the bot's position in the Y axis 
	 * @param direction the direction the bot is facing
	 */
	public Bot(String name, int life, GameObject hazqa, double defense, double strength, int posX, int posY, Direction direction) {
		super(life, posX, posY, direction);
		MAX_HEALTH = life;
		this.hazqa = hazqa;
		this.defense = defense;
		this.strength = strength;
		this.name = name;
	}

	@Override
	public void attack(double hit) {
		this.setLife(getLife() - hit * (1 - defense/70));
		if (getLife() <= 0){
			setLife(0);
			hazqa.setPosX(getPosX());
			hazqa.setPosY(getPosY());
			notifyDemisableObserver();
		}
	}
	
	/**
	 * Detect the player if he is next to the bot
	 * @param player the player
	 * @return the direction facing the player
	 */
	public Direction detectPlayer(Player player){
		for(Direction direction : Direction.values()){
			int x = 0; 
			int y = 0;
			switch(direction){
			case UP:
				y = -1;
				break;
			case DOWN:
				y = 1;
				break;
			case RIGHT:
				x = 1;
				break;
			case LEFT:
				x = -1;
				break;
			default: return null;
			}
			if(player.isAtPosition(getPosX() + x, getPosY() + y)){
				return direction;
			}
		}		
		return null;
	}
	
	
	/**
	 * @return the defense
	 */
	public double getDefense() {
		return defense;
	}

	/**
	 * @param defense the defense to set
	 */
	public void setDefense(double defense) {
		this.defense = defense;
	}

	/**
	 * @return the strength
	 */
	public double getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(double strength) {
		this.strength = strength;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hazqa
	 */
	public GameObject getHazqa() {
		return hazqa;
	}

	/**
	 * @param hazqa the hazqa to set
	 */
	public void setHazqa(GameObject hazqa) {
		this.hazqa = hazqa;
	}

	/**
	 * @return the mAX_HEALTH
	 */
	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}
	

}