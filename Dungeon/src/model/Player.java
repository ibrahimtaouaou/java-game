package model;

import model.Equipment.Quality;

public abstract class Player extends Character {

	private static final int MAX_HEALTH = 300;

	private Inventory inventory;
	private Equipment equipment;

	private static final int TIME_SKILL = 5;
	private static final int TIME_WAIT = 15;

	private Quality skillQuality;
	private boolean isActive;

	private int skillValueBar = 100;

	/**
	 * Player constructor
	 * @param posX the player's position in the X axis
	 * @param posY the player's position in the Y axis 
	 * @param direction the direction the player is facing
	 * @param skillQuality the skill quality of the player (Defense or Strength
	 */
	public Player(int life, int posX, int posY, Direction direction, Quality skillQuality) {
		super(life, posX, posY, direction);
		this.inventory = new Inventory();
		this.skillQuality = skillQuality;
	}

	/**
	 * Manage charcater's movement
	 * @param posX the position x of the movement
	 * @param posY the position y of the movement
	 */
	public void move(int x, int y) {
		setPosX(x);
		setPosY(y);
	}

	/**
	 * heal the player
	 * @param heal
	 */
	public void heal(int heal){
		if(getLife() + heal < 300)
			this.setLife(getLife() + heal);
		else
			this.setLife(300);
	}

	/**
	 * equip the player with the item
	 * @param item item to be equipped
	 */
	public void equip(Item item){
		if(item instanceof Weapon){
			if(equipment.getWeapon() != null){
				Weapon weapon = equipment.getWeapon();//save worn weapon (weapon)
				equipment.setWeapon((Weapon)item);//equip the weapon (item)
				switchEquipment(item, weapon);
			}else{
				equipment.setWeapon((Weapon)item);
				inventory.removeItem(item);
			}
		}
		else if("Breastplate".equals(item.getName()) || "Legend Breastplate".equals(item.getName()) || "Adventurer's armor".equals(item.getName()) 
				|| "Adventurer's cap".equals(item.getName())){
			if(equipment.getBreastplate() != null){
				Armor breastplate = equipment.getBreastplate();
				equipment.setBreastplate((Armor)item);
				switchEquipment(item, breastplate);
			}else{
				equipment.setBreastplate((Armor)item);
				inventory.removeItem(item);
			}
		}
		else if("Boots".equals(item.getName()) || "Legend Boots".equals(item.getName())){
			if(equipment.getBoots() != null){
				Armor boots = equipment.getBoots();
				equipment.setBoots((Armor)item);
				switchEquipment(item, boots);
			}else{
				equipment.setBoots((Armor)item);
				inventory.removeItem(item);
			}

		}
		else if("Necklace".equals(item.getName())){
			if(equipment.getNecklace() != null){
				Armor necklace = equipment.getNecklace();
				equipment.setNecklace((Armor)item);
				switchEquipment(item, necklace);
			}else{
				equipment.setNecklace((Armor)item);
				inventory.removeItem(item);
			}
		}
		else if("Ring".equals(item.getName())){
			if(equipment.getRing() != null){
				Armor ring = equipment.getRing();
				equipment.setRing((Armor)item);
				switchEquipment(item, ring);
			}else{
				equipment.setRing((Armor)item);
				inventory.removeItem(item);
			}
		}
		else if("Helmet".equals(item.getName()) || "Legend Helmet".equals(item.getName())){
			if(equipment.getHat() != null){
				Armor hat = equipment.getHat();
				equipment.setHat((Armor)item);
				switchEquipment(item, hat);
			}else{
				equipment.setHat((Armor)item);
				inventory.removeItem(item);
			}
		}
		else if("Gauntlets".equals(item.getName()) || "Legend Gauntlets".equals(item.getName())){
			if(equipment.getGauntlets() != null){
				Armor gauntlets = equipment.getGauntlets();
				equipment.setGauntlets((Armor)item);
				switchEquipment(item, gauntlets);
			}else{
				equipment.setGauntlets((Armor)item);
				inventory.removeItem(item);
			}
		}
	}

	/**
	 * Switch the worn equipment by the new one from the inventory
	 * @param newItem item in the inventory to be removed
	 * @param oldItem item in the inventory to be add
	 */
	private void switchEquipment(Item newItem, Item oldItem){
		inventory.removeItem(newItem);
		inventory.addItem(oldItem);
	}

	/**
	 * set the life when attacked by a bot
	 * @param hit the amount of damage dealt
	 */
	public void attack(double hit){
		this.setLife(getLife() - hit* (1 - this.getDefense()/600));
		if(this.getLife() <= 0){
			this.setLife(0);
			notifyDemisableObserver();
		}
	}

	@Override
	public void notifyDemisableObserver() {
		getObservers().forEach(obs -> obs.demise(this, null));
	}

	/**
	 * @return the total defense given by equipments
	 */
	public double getDefense(){
		return equipment.getDefense();
	}

	/**
	 * @return the total strength given by equipments
	 */
	public double getStrength(){
		return equipment.getStrength();
	}

	/**
	 * Get inventory
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Set inventory
	 * @param inventory players'inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the quality of the skill
	 */
	public Quality getSkillQuality() {
		return skillQuality;
	}

	/**
	 * @param skillQuality skillquality to set
	 */
	public void setSkillQuality(Quality skillQuality) {
		this.skillQuality = skillQuality;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActiveSkill() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActiveSkill(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the valueBar of the skill
	 */
	public int getSkillValueBar() {
		return skillValueBar;
	}
	
	/**
	 * @param skillValueBar the value to set to the bar
	 */
	public void setSkillValueBar(int skillValueBar) {
		this.skillValueBar = skillValueBar;
	}

	/**
	 * @return the timeSkill
	 */
	public static int getTimeSkill() {
		return TIME_SKILL;
	}

	/**
	 * @return the timeWait
	 */
	public static int getTimeWait() {
		return TIME_WAIT;
	}

	/**
	 * @return the maxHealth
	 */
	public static int getMaxHealth() {
		return MAX_HEALTH;
	}
	
	
}