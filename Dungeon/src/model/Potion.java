package model;
public class Potion extends Item {

	private int heal;

	/**
	 * Potion constructor
	 * @param name name of the potion
	 * @param heal the value of the heal provided
	 * @param posX position in X axis of the potion
	 * @param posY position in Y axis of the potion
	 * @param price the price of the item
	 */
	public Potion(String name, int heal, int posX, int posY, int price) {
		super(name, posX, posY, price);
		this.heal = heal;
	}

	/**
	 * Get heal
	 * @return the value of the heal
	 */
	public int getHeal() {
		return this.heal;
	}

	/**
	 * Set heal
	 * @param heal the value of the heal
	 */
	public void setHeal(int heal) {
		this.heal = heal;
	}

	
}