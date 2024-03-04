package model;

import model.Armor.TypeArmor;
import model.Equipment.Quality;

public class Warrior extends Player {
	

	/**
	 * Warrior constructor
	 * @param life the warrior's life
	 * @param posX the warrior's position in the X axis
	 * @param posY the warrior's position in the Y axis 
	 * @param direction the direction the warrior is facing
	 */
	public Warrior(int life, int posX, int posY, Direction direction) {
		super(life, posX, posY, direction, Quality.DEFENSE);
		Weapon weapon = new Weapon("Sword", 15, 1, -100, -100, 5);
		Armor breastplate = new Armor("Adventurer's armor", TypeArmor.BREASTPLATE, 5, -100, -100, 5);
		setEquipment(new Equipment(weapon, breastplate));
	}
}