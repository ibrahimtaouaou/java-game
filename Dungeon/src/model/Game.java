package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Door.DoorType;
import view.InfoBar;
import view.InventoryUI;
import view.Map;
import view.Window;
import view.helper.LevelMgr;

public class Game implements DemisableObserver {

	private List<GameObject> gameObjects;
	private static int heightMap = 18;
	private static int widthMap  = 18;
	private static String playerType;
	private InventoryUI inventoryUI;
	private List<Item> savedItems;	
	private Inventory inventory;
	private static int chapter;
	private static int level;
	private InfoBar infoBar;
	private Player player;
	private Window window;	
	private Door door;

	/**
	 * Constructor
	 * @param window the main frame
	 * @param playerType warrior or wizard
	 * @param widthMap dimension that the player has chosen
	 * @param inventory the player's inventory
	 */
	public Game(Window window, String playerType, int widthMap, Inventory inventory) {
		this.window = window;
		Game.widthMap = widthMap;
		this.inventory = inventory;
		Game.playerType = playerType;		
		Game.level = 1;
		Game.chapter = 0;
		gameObjects = new ArrayList<GameObject>();
		savedItems = inventory.getItems();

		this.window.setMap(new Map(Game.widthMap,Game.heightMap));
		initMap();

		this.infoBar = new InfoBar(Game.widthMap, player);
		this.inventoryUI = new InventoryUI(this);
		this.infoBar.setInventoryPanel(inventoryUI);
		this.window.setInfoBar(infoBar);
		this.window.update();
	}

	/**
	 * create the map
	 */
	private void initMap(){
		gameObjects.clear();//nettoyer toute la map
		gameObjects.addAll(LevelMgr.getLevel()); //créer le level
		if(player == null){
			player = LevelMgr.getPlayer();
			player.setInventory(inventory);
		}
		door = (Door) gameObjects.stream().filter(g -> g instanceof Door).findFirst().get();

		gameObjects.addAll(player.getEquipment().getItems());
		gameObjects.addAll(player.getInventory().getItems());
		gameObjects.add(player);
		gameObjects.stream().filter(obj -> obj instanceof Demisable).forEach(demisable -> ((Demisable)demisable).addObserver(this));
		window.setGameObjects(this.gameObjects);
	}

	@Override
	public void demise(Demisable demisable, GameObject hazqa) {
		gameObjects.remove(demisable);
		if (demisable instanceof Player){
			window.gameOver();

			for(Item i : player.getInventory().getItems()){
				if(!savedItems.contains(i)){
					player.getInventory().removeItem(i);
				}
			}
			for(Item i : player.getEquipment().getItems()){
				if(savedItems.contains(i)){
					player.getInventory().addItem(i);
				}
			}
			try (	FileOutputStream outputFileStream = new FileOutputStream("saved");
					ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);)
			{
				outputStream.writeObject(player.getInventory());
			}catch(IOException io){
				io.printStackTrace();
			}
		}
		else if (demisable instanceof Chest){
			gameObjects.add(hazqa);
			player.getInventory().gain(15);
		}
		else if (demisable instanceof Bot){
			boolean end = true;
			gameObjects.add(hazqa);
			player.getInventory().gain(35);
			removeBotInfo((Bot) demisable);
			for(int i = 0; i < gameObjects.size(); i++ ){
				if(gameObjects.get(i) instanceof Bot){
					end = false;
					break;
				}
			}
			if(end)
				door.open();
		}
		window.update();
	}

	/**
	 * Remove the bot info 3s after the demise bot
	 * @param bot
	 */
	private void removeBotInfo(Bot bot) {
		new Thread(new Runnable() {			
			public void run() {
				try {
					Thread.sleep(3000);				     
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(infoBar.getBot() == bot){
					infoBar.setBot(null);
				}
			}
		}).start();
	}


	/**
	 * move the player
	 * @param moveX player's movement in the X axis
	 * @param moveY player's movement in the Y axis
	 */
	public void movePlayer(int moveX, int moveY) {
		window.repaint();
		int nextX = player.getPosX() + moveX;
		int nextY = player.getPosY() + moveY;
		for(GameObject object : gameObjects){
			if(object.isAtPosition(nextX, nextY)){
				if(object.isObstacle())
					return;
			}
		}
		player.move(nextX, nextY);
		levelUp();
		window.update();
		notifyBot();
	}

	/**
	 * Allow the player the go to the next level
	 */
	private void levelUp() {
		boolean pass = false;
		for(GameObject out: gameObjects){
			if(out instanceof Door){
				if(player.isAtPosition(out.getPosX(), out.getPosY())){
					if(DoorType.DOOR == ((Door) out).getType()){
						level++;
					}else{
						level += 3;
					}
					pass = true;
				}
			}
		}
		if(pass){
			window.nextLevel();
			initMap();		
			if(level == 5 + 5*chapter){
				Game.chapter++;
			}
		}
	}

	/**
	 * notify the bot when the player moves and attack him if he is next to the bot
	 */
	private void notifyBot() {
		for(GameObject bot : gameObjects){
			if(bot instanceof Bot){
				Direction direction = ((Bot) bot).detectPlayer(player);
				if(direction != null) {
					infoBar.setBot((Bot) bot);
					((Bot) bot).setDirection(direction);
					new Thread(new Runnable() {			
						public void run() {
							do {
								player.attack(((Bot) bot).getStrength());
								window.update();
								try {
									Thread.sleep(750);				     
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} while(gameObjects.contains(player) && ((Bot) bot).detectPlayer(player) != null && gameObjects.contains(bot));								
							removeBotInfo((Bot) bot);
						}
					}).start();
				}
			}
		}
	}

	/**
	 * add to the inventory the item
	 */
	public void collect(){
		int nextX = getNextX(player, 1);
		int nextY = getNextY(player, 1);
		for(GameObject object : gameObjects){
			if(object.isAtPosition(nextX, nextY) && object instanceof Item){
				player.getInventory().collect((Item) object);
			}
		}
		inventoryUI.redraw();
		window.update();
	}

	/**
	 * sell the item
	 * @param item selected item in the inventory
	 */
	public void sell(Item item){
		player.getInventory().sell(item);
		gameObjects.remove(item);
		inventoryUI.redraw();
		window.update();
	}

	/**
	 * equip an armor/weapon or drink a potion
	 * @param item
	 */
	public void use(Item item) {
		if(item instanceof Potion){
			player.heal(((Potion)item).getHeal());
			player.getInventory().removeItem(item);
			gameObjects.remove(item);
		}
		else if(item instanceof Weapon || item instanceof Armor){
			player.equip(item);
		}
		inventoryUI.redraw();
		window.update();
	}

	/**
	 * check if the attack must be a melee or ranged attack
	 */
	public void attackRange(){
		int distance = player.getEquipment().getWeapon().getDistance();

		if(distance == 1){
			int nextX = getNextX(player, 1);
			int nextY = getNextY(player, 1);
			attack(nextX, nextY);
		}

		else if(distance == 3 && player.getInventory().getCoins() >= 200){
			boolean notAtk = true;
			int i = 1;
			while(notAtk && i < 4){
				int nextX = getNextX(player, i);
				int nextY = getNextY(player, i);	
				if(attack(nextX, nextY)){
					player.getInventory().debit(200);
					notAtk = false;
				}
				i++;
			}
		}
		window.update();
	}

	/**
	 * Attack the bot at the given position
	 */
	private boolean attack(int x, int y){
		Iterator<GameObject> objects = gameObjects.iterator();
		while (objects.hasNext()) {
			GameObject object = objects.next();
			if(object.isAtPosition(x, y)){
				if(object instanceof Bot){
					((Bot)object).attack(player.getStrength());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Interract with the game object at the given position
	 */
	public boolean interract(){
		int x = getNextX(player, 1);
		int y = getNextY(player, 1);
		Iterator<GameObject> objects = gameObjects.iterator();
		while (objects.hasNext()) {
			GameObject object = objects.next();
			if(object.isAtPosition(x, y)){
				if(object instanceof Chest){
					((Chest)object).open();
					window.update();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Activate the skill of the player during 5 sec
	 */
	public void activateSkill() {
		if(!player.isActiveSkill()){
			new Thread(new Runnable() {			
				public void run() {
					player.setActiveSkill(true);
					player.getEquipment().multiply(player.getSkillQuality());
					while(player.getSkillValueBar() > 0){
						player.setSkillValueBar(player.getSkillValueBar() - 1);
						window.update();
						try {
							Thread.sleep(Player.getTimeSkill() * 10);				     
						} catch (InterruptedException e) {
							e.printStackTrace();
						}		
					}
					player.getEquipment().resetMultiplicator();
					waitingTime();
				}
			}).start();
		}
	}

	/** 
	 * Don't allow to activate the skill again within 15 sec
	 */
	private void waitingTime(){
		new Thread(new Runnable() {			
			public void run() {
				while(player.getSkillValueBar() < 100){
					player.setSkillValueBar(player.getSkillValueBar() + 1);
					window.update();
					try {
						Thread.sleep(Player.getTimeWait() * 10);				     
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				player.setActiveSkill(false);
			}
		}).start();
	}

	/**
	 * gets the next position in the X axis
	 * @param player the player
	 * @param i the distance
	 * @return
	 */
	private int getNextX(Player player, int i){
		switch(player.getDirection()){
		case RIGHT:
			return player.getPosX() + 1 * i;
		case LEFT:
			return player.getPosX() - 1 * i;
		default : return player.getPosX();
		}
	}

	/**
	 * gets the next position in the Y axis
	 * @param player the player
	 * @param i the distance
	 * @return
	 */
	private int getNextY(Player player, int i){
		switch(player.getDirection()){
		case UP:
			return player.getPosY() - 1 * i;
		case DOWN:
			return player.getPosY() + 1 * i;
		default : return player.getPosY();
		}
	}
	

	/**
	 * @return the heightMap
	 */
	public static int getHeightMap() {
		return heightMap;
	}

	/**
	 * @param heightMap the heightMap to set
	 */
	public static void setHeightMap(int heightMap) {
		Game.heightMap = heightMap;
	}

	/**
	 * @return the widthMap
	 */
	public static int getWidthMap() {
		return widthMap;
	}

	/**
	 * @param widthMap the widthMap to set
	 */
	public static void setWidthMap(int widthMap) {
		Game.widthMap = widthMap;
	}

	/**
	 * @return the playerType
	 */
	public static String getPlayerType() {
		return playerType;
	}

	/**
	 * @param playerType the playerType to set
	 */
	public static void setPlayerType(String playerType) {
		Game.playerType = playerType;
	}

	/**
	 * @return the chapter
	 */
	public static int getChapter() {
		return chapter;
	}

	/**
	 * @param chapter the chapter to set
	 */
	public static void setChapter(int chapter) {
		Game.chapter = chapter;
	}

	/**
	 * @return the level
	 */
	public static int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public static void setLevel(int level) {
		Game.level = level;
	}

	/**
	 * Gets the current player of the game
	 */
	public Player getPlayer() {
		return this.player;
	}

}