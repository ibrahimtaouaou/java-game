package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Inventory implements Serializable {


	private static final long serialVersionUID = 466162509904235530L;

	private final static int MAX_SIZE = 9; 
	private List<Item> items;	
	private int coins;
	
	/**
	 * Inventory constructor
	 * @param MAX_SIZE the size of the inventory
	 */
	public Inventory() {
		items = new ArrayList<Item>(MAX_SIZE);
		while(items.size() < MAX_SIZE)
			items.add(null);
	}
	
	/**
	 * Collect the item into the inventory
	 * Set the posX = -100 posY = -100
	 * @param Item the item to add in the inventory
	 */
	public void collect(Item item){
		if(addItem(item)){
			item.setPosX(Item.INVENTORY);
			item.setPosY(Item.INVENTORY);
		}
	}

	/**
	 * Sell the item in the inventory for the half price
	 * @param item item to sell
	 */
	public void sell(Item item){
		removeItem(item);
		gain(item.getPrice()/2);
	}
	
	/**
	 * Add an item to the first empty place in the inventory
	 * @param item item to add to items
	 * @return true if the item is added, false if it's full
	 */
	public boolean addItem(Item item){
		for(int i=0 ; i < MAX_SIZE ; i++){
			if(items.get(i) == null){
				items.set(i, item);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove an item from the inventory
	 * @param item item to remove from items
	 */
	public void removeItem(Item item){
		items.set(items.indexOf(item), null); //pour garder les element a leur case
	}
	
	/**
	 * remove coins from the inventory
	 * @param amount the amount of coins to remove
	 */
	public void debit(int amount){
		coins -= amount;
	}
	
	/**
	 * add coins to the inventory
	 * @param amount the amount of coins to add
	 */
	public void gain(int amount){
		coins += amount;
	}
	
	/**
	 * Get the i Item in the inventory
	 * @param i the index of the list
	 * @return the item
	 */
	public Item getItem(int i){
		return items.get(i);
	}
	
	/**
	 * Get current size of inventory
	 * @return return the size of inventory
	 */
	public int getSize() {
		return this.items.size();
	}

	/**
	 * Get items
	 * @return  
	 */
	public List<Item> getItems() {
		return this.items.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}

	/**
	 * Set items
	 * @param items
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the coin
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * @param coin the coin to set
	 */
	public void setCoins(int coin) {
		this.coins = coin;
	}

	/**
	 * gets the inventory
	 * @return the list of inventory
	 */
	public List<Item> getIterator() {
		return this.items;
	}

	/**
	 * @return the maxSize
	 */
	public static int getMaxSize() {
		return MAX_SIZE;
	}
	
}