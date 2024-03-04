package model;

public class Armor extends Item {

	public enum TypeArmor {
		BREASTPLATE, NECKLACE, RING, HAT, GAUNTLETS, BOOTS;
	}
	private double defense;
	private TypeArmor type;
	
	/**
	 * Armor constructor
	 * @param name name of the armor
	 * @param type type of the armor
	 * @param defenseValue value of the armor
	 * @param posX position in X axis of the armor
	 * @param posY position in Y axis of the armor
	 * @param price the price of the item
	 */
	public Armor(String name, TypeArmor type, double defense, int posX, int posY, int price) {
		super(name, posX, posY, price);
		this.type = type;
		this.defense = defense;
	}

	/**
	 * Gets the defense of the armor
	 * @return the defense
	 */
	public double getDefense() {
		return defense;
	}

	/**
	 * Sets the defense of the armor
	 * @param defense the defense to set
	 */
	public void setDefense(double defense) {
		this.defense = defense;
	}

	/**
	 * Gets the type of armor
	 * @return the type
	 */
	public TypeArmor getType() {
		return type;
	}

	/**
	 * Sets the type of armor
	 * @param type the type to set
	 */
	public void setType(TypeArmor type) {
		this.type = type;
	}

	
	
}