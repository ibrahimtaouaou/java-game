package model;

import model.Armor.TypeArmor;
import model.Equipment.Quality;

public class Wizard extends Player {

	/**
	 * Wizard constructor
	 * @param life the wizard's life
	 * @param posX the wizard's position in the X axis
	 * @param posY the wizard's position in the Y axis 
	 * @param direction the direction the wizard is facing
	 */
	public Wizard(int life, int posX, int posY, Direction direction) {
		super(life, posX, posY, direction, Quality.STRENGTH);
		Weapon weapon = new Weapon("Staff", 15, 1, -100, -100, 5);
		Armor breastplate = new Armor("Adventurer's cap", TypeArmor.BREASTPLATE, 5, -100, -100, 5);
		setEquipment(new Equipment(weapon, breastplate));
	}
}