package model;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

	private static final int DEFAULT_MULTIPLX = 1;

	public enum Quality{
		DEFENSE, STRENGTH;
	}
	private Weapon weapon;
	private Armor breastplate, necklace, ring, hat, gauntlets, boots;
	private double defenseMultiplicator;
	private double strengthMultiplicator;

	/**
	 * Constructor
	 * @param weapon weapon worn
	 * @param breastplate breastplate worn
	 * @param necklace necklace worn
	 * @param ring ring worn
	 * @param hat hat worn
	 * @param gauntlets gauntlets worn
	 * @param boots botos worn
	 */
	public Equipment(Weapon weapon, Armor breastplate, Armor necklace, Armor ring, Armor hat, Armor gauntlets, Armor boots) {
		this(weapon, breastplate);
		this.necklace = necklace;
		this.ring = ring;
		this.hat = hat;
		this.gauntlets = gauntlets;
		this.boots = boots;
	}

	/**
	 * Default constructor
	 * @param weapon weapon worn
	 * @param breastplate breastplate worn
	 */
	public Equipment(Weapon weapon, Armor breastplate){
		this.weapon = weapon;
		this.breastplate = breastplate;
		this.strengthMultiplicator = DEFAULT_MULTIPLX;
		this.defenseMultiplicator = DEFAULT_MULTIPLX;
	}

	/**
	 * @return the total defense value given by armors
	 */
	public double getDefense(){
		double totalDef = 0;
		if(breastplate != null)
			totalDef += breastplate.getDefense();
		if(necklace != null)
			totalDef += necklace.getDefense();
		if(ring != null)
			totalDef += ring.getDefense();
		if(hat != null)
			totalDef += hat.getDefense();
		if(gauntlets != null)
			totalDef += gauntlets.getDefense();
		if(boots != null)
			totalDef += boots.getDefense();

		return totalDef * defenseMultiplicator;
	}
	
	/**
	 * @return return the strength of the weapon worn
	 */
	public double getStrength(){
		return weapon.getDamage() * strengthMultiplicator;
	}

	/**
	 * @param item the weapon in the inventory
	 * @return the difference between the weapon worn and the weapon in the inventory
	 */
	public double getDiff(Weapon item){
		return getDiffItem(item);
	}
	
	/**
	 * @param item the armor in the inventory
	 * @return the difference between the armor worn and the armor in the inventory
	 */
	public double getDiff(Armor item){
		return getDiffItem(item);
	}
	
	/**
	 * @param item the item in the inventory
	 * @return the difference between the item worn and the item in the inventory
	 */
	private double getDiffItem(Item item){
		if (item instanceof Weapon){
			return ((Weapon) item).getDamage() - getStrength();
		}else if (item instanceof Armor){
			switch(((Armor) item).getType()){
			case BOOTS: return (this.boots==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - boots.getDefense());
			case BREASTPLATE: return (this.breastplate==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - breastplate.getDefense());
			case GAUNTLETS: return (this.gauntlets==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - gauntlets.getDefense());
			case HAT: return (this.hat==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - hat.getDefense());
			case NECKLACE: return (this.necklace==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - necklace.getDefense());
			case RING: return (this.ring==null ? ((Armor) item).getDefense() : ((Armor) item).getDefense() - ring.getDefense());
			default:return 0;
			}
		}else{
			return 0;
		}
	}
	
	/**
	 * multiply the given quality by the given double for the Skill
	 * @param quality
	 * @param multiplicator
	 */
	public void multiply(Quality quality) {
		switch(quality){
		case DEFENSE : setDefenseMultiplicator(1.5); break;
		case STRENGTH : setStrengthMultiplicator(1.5); break;
		default: break;
		}
	}
	
	/**
	 * Reset the multiplicator to the default value 
	 */
	public void resetMultiplicator() {
		setStrengthMultiplicator(DEFAULT_MULTIPLX);
		setDefenseMultiplicator(DEFAULT_MULTIPLX);
	}
	
	/**
	 * @return a list with all items in the equipement
	 */
	public List<Item> getItems(){
		List<Item> list = new ArrayList<>();
		if(breastplate != null)
			list.add(breastplate);
		if(gauntlets != null)
			list.add(gauntlets);
		if(necklace != null)
			list.add(necklace);
		if(weapon != null)
			list.add(weapon);
		if(boots != null)
			list.add(boots);
		if(ring != null)
			list.add(ring);
		if(hat != null)
			list.add(hat);
		return list;
	}
	
	/**
	 * @return the defenseMultiplicator
	 */
	public double getDefenseMultiplicator() {
		return defenseMultiplicator;
	}

	/**
	 * @param defenseMultiplicator the defenseMultiplicator to set
	 */
	public void setDefenseMultiplicator(double defenseMultiplicator) {
		this.defenseMultiplicator = defenseMultiplicator;
	}

	/**
	 * @return the strengthMultiplicator
	 */
	public double getStrengthMultiplicator() {
		return strengthMultiplicator;
	}

	/**
	 * @param d the strengthMultiplicator to set
	 */
	public void setStrengthMultiplicator(double d) {
		this.strengthMultiplicator = d;
	}

	public Weapon getWeapon() {
		return weapon;
	}


	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * @return the breastplate
	 */
	public Armor getBreastplate() {
		return breastplate;
	}

	/**
	 * @param breastplate the breastplate to set
	 */
	public void setBreastplate(Armor breastplate) {
		this.breastplate = breastplate;
	}

	/**
	 * @return the necklace
	 */
	public Armor getNecklace() {
		return necklace;
	}

	/**
	 * @param necklace the necklace to set
	 */
	public void setNecklace(Armor necklace) {
		this.necklace = necklace;
	}

	/**
	 * @return the ring
	 */
	public Armor getRing() {
		return ring;
	}

	/**
	 * @param ring the ring to set
	 */
	public void setRing(Armor ring) {
		this.ring = ring;
	}

	/**
	 * @return the hat
	 */
	public Armor getHat() {
		return hat;
	}

	/**
	 * @param hat the hat to set
	 */
	public void setHat(Armor hat) {
		this.hat = hat;
	}

	/**
	 * @return the gauntlets
	 */
	public Armor getGauntlets() {
		return gauntlets;
	}

	/**
	 * @param gauntlets the gauntlets to set
	 */
	public void setGauntlets(Armor gauntlets) {
		this.gauntlets = gauntlets;
	}

	/**
	 * @return the boots
	 */
	public Armor getBoots() {
		return boots;
	}

	/**
	 * @param boots the boots to set
	 */
	public void setBoots(Armor boots) {
		this.boots = boots;
	}

}