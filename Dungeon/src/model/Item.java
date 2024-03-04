package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Item extends GameObject implements Serializable{

	private static final long serialVersionUID = -3122769073418097012L;
	private String name;
	private int price;
	protected static final int INVENTORY = -100;
	
	/**
	 * Item constructor
	 * @param name name of the item
	 * @param posX position in X axis of the item
	 * @param posY position in X axis of the item
	 * @param price the price of the item
	 */
	public Item(String name, int posX, int posY, int price) {
		super(posX, posY);
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Get name
	 * @return return the name of the item
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Set name
	 * @param name name of the item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}