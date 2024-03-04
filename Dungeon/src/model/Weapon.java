package model;

import java.io.Serializable;

public class Weapon extends Item  implements Serializable{

	private static final long serialVersionUID = -2510085879820552901L;
	private double damage;
	private int distance;

	/**
	 * Weapon constructor
	 * @param name the name of the Weapon
	 * @param damage damage of the Weapon
	 * @param distance distance the Weapon can reach	
	 * @param posX position in X axis of the Weapon
	 * @param posY position in Y axis of the Weapon
	 * @param price the price of the item
	 */
	public Weapon(String name, double damage, int distance, int posX, int posY, int price) {
		super(name, posX, posY, price);
		this.damage = damage;
		this.distance = distance;
	}
	
	/**
	 * Weapon constructor
	 * @param name the name of the Weapon
	 * @param damage damage of the Weapon
	 * @param distance distance the Weapon can reach	
	 * @param price the price of the item
	 */
	public Weapon(String name, double damage, int distance, int price) {
		this(name, damage, distance, INVENTORY, INVENTORY, price);
	}

	/**
	 * Get damage
	 * @return the value of damage
	 */
	public double getDamage() {
		return this.damage;
	}

	/**
	 * Set damage
	 * @param damage the value of the new weapon
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Get distance
	 * @return distance the weapon can reach
	 */
	public int getDistance() {
		return this.distance;
	}

	/**
	 * Set distance
	 * @param distance distance the weapon can reach
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

}