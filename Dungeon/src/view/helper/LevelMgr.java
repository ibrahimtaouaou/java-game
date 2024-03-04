package view.helper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Armor;
import model.Armor.TypeArmor;
import model.Boss;
import model.Bot;
import model.Chest;
import model.Direction;
import model.Door;
import model.Door.DoorType;
import model.Game;
import model.GameObject;
import model.Item;
import model.Player;
import model.Potion;
import model.Warrior;
import model.Weapon;
import model.Wizard;

public class LevelMgr {

	private static List<GameObject> gameObjects;
	private static List<GameObject> bossItems;
	private static List<Item> droppedItems;
	
	private static Random randomPosX;
	private static Random randomPosY;
	private static Random randomHazqa;
	private static Player player;

	/**
	 * get the level
	 * @return the list of gameobjects
	 */
	public static List<GameObject> getLevel(){
		gameObjects = new ArrayList<GameObject>();
		generateMap();
		return gameObjects;
	}

	/**
	 * generate the map
	 */
	public static void generateMap(){
		randomPosX = new Random();
		randomPosY = new Random();
		randomHazqa = new Random();
		gameObjects.addAll(MapGenerator.generate());
		//2 chests for upper levels
		generateChests(Game.getLevel() > 2 ? 2 : 1);
		//bots
		generateBots();
		//player dernier verifier contour
		generatePlayer();
	}
	
	/**
	 * generate the player
	 */
	private static void generatePlayer() {
		Point p = findFreePosition();
		if(Game.getLevel() == 1 && Game.getChapter() == 0){
			if("Warrior".equals(Game.getPlayerType())){
				player = new Warrior(300, 1, p.x, Direction.DOWN);
			}else{
				player = new Wizard(300, 1, p.y, Direction.DOWN);
			}
		}
		else{
			player.setPosX(p.x);
			player.setPosY(p.y);
		}
	}

	/**
	 * generate the chests
	 * @param nb number of chests
	 */
	private static void generateChests(int nb) {
		for(int i=0 ; i < nb; i++){
			Point p = findFreePosition();
			gameObjects.add(new Chest(loot(), p.x ,p.y));
		}
	}
	
	/**
	 * generate the bots
	 */
	private static void generateBots(){
		Point p;
		if(Game.getLevel() > 0){
			p = findFreePosition();
			addBot("Slime", p.x, p.y);
			p = findFreePosition();
			addBot("Slime", p.x, p.y);
		}
		if(Game.getLevel() > 1){
			p = findFreePosition();
			addBot("Skeleton", p.x, p.y);
		}
		if(Game.getLevel() > 2){
			p = findFreePosition();
			addBot("Skeleton", p.x, p.y);
		}
		if(Game.getLevel() > 3){
			p = findFreePosition();
			addBot("Troll", p.x, p.y);
		}
		if(Game.getLevel() % 5 == 0){
			p = findFreePosition();
			addBot("Boss", p.x, p.y);
		}
	}
	
	/**
	 * add the bots
	 * @param name name of the bot
	 * @param x pos in the X axis
	 * @param y pos in the Y axis
	 */
	private static void addBot(String name, int x, int y) {
		switch(name){
		case "Slime" : gameObjects.add(new Bot(name, 100+20*Game.getChapter(), loot(),5*Game.getChapter(), 20+5*Game.getChapter(), x, y, Direction.UP)); break;
		case "Skeleton" : gameObjects.add(new Bot(name, 120+20*Game.getChapter(), loot(), 5+10*Game.getChapter(), 25+5*Game.getChapter(), x, y, Direction.UP)); break;
		case "Troll" : gameObjects.add(new Bot(name, 120+20*Game.getChapter(), loot(), 15+10*Game.getChapter(), 25+5*Game.getChapter(), x, y, Direction.UP)); break;
		case "Boss" : gameObjects.add(new Boss(200+20*Game.getChapter(), bossLoot(), 30.0+10*Game.getChapter(), 30.0+10*Game.getChapter(), x, y, Direction.LEFT)); break;
		default : break;
		}
	}

	/**
	 * find free position
	 * @return the position free
	 */
	private static Point findFreePosition(){
		boolean found = false;
		int x = 0 , y = 0;
		FREE: while(!found){
			x = randomPosX.nextInt(Game.getWidthMap());
			y = randomPosY.nextInt(Game.getHeightMap());
			for(GameObject o : gameObjects){
				if(o.isAtPosition(x, y))
					continue FREE;
			}
			found = true;
		}
		return new Point(x, y);
	}
	
	/**
	 * Random object to drop
	 * @return the random item
	 */
	private static Item loot(){
			droppedItems = new ArrayList<Item>();
			droppedItems.add(new Potion("Potion", randomHazqa.nextInt(100)+50, 0, 0, 5));
			droppedItems.add(new Potion("Potion", randomHazqa.nextInt(100)+50, 0, 0, 5));
			droppedItems.add(new Potion("Potion", randomHazqa.nextInt(100)+50, 0, 0, 5));
			droppedItems.add(new Potion("Potion", randomHazqa.nextInt(100)+50, 0, 0, 5));
			droppedItems.add(new Weapon("Sword", randomHazqa.nextInt(25)+5, 1, 0, 0, 50));
			droppedItems.add(new Weapon("Staff", randomHazqa.nextInt(25)+5, 1, 0, 0, 50));
			droppedItems.add(new Weapon("Bow", randomHazqa.nextInt(15)+10, 3, 0, 0, 60));
			droppedItems.add(new Armor("Ring", TypeArmor.RING, randomHazqa.nextInt(20)+20, 0, 0, 40));
			droppedItems.add(new Armor("Necklace", TypeArmor.NECKLACE, randomHazqa.nextInt(20)+20, 0, 0, 40));
			droppedItems.add(new Armor("Gauntlets", TypeArmor.GAUNTLETS, randomHazqa.nextInt(20)+30, 0, 0, 40));
			droppedItems.add(new Armor("Helmet", TypeArmor.HAT, randomHazqa.nextInt(20)+35, 0, 0, 40));
			droppedItems.add(new Armor("Boots", TypeArmor.BOOTS, randomHazqa.nextInt(25)+40, 0, 0, 40));
			droppedItems.add(new Armor("Breastplate", TypeArmor.BREASTPLATE, randomHazqa.nextInt(60)+50, 0, 0, 50));
		
		return droppedItems.get(randomHazqa.nextInt(droppedItems.size()));
	}
	
	/**
	 * Random object to drop by the boss
	 * @return the object dropped by the boss
	 */
	private static GameObject bossLoot(){
			bossItems = new ArrayList<GameObject>();
			bossItems.add(new Potion("Potion", 150, 0, 0, 5));
			bossItems.add(new Weapon("Sword", 30, 1, 0, 0, 50));
			bossItems.add(new Weapon("Staff", 30, 1, 0, 0, 50));
			bossItems.add(new Weapon("Bow", 25, 3, 0, 0, 60));
			bossItems.add(new Armor("Ring", TypeArmor.RING, 40, 0, 0, 40));
			bossItems.add(new Armor("Necklace", TypeArmor.NECKLACE, 40, 0, 0, 40));
			bossItems.add(new Armor("Gauntlets", TypeArmor.GAUNTLETS, 50, 0, 0, 40));
			bossItems.add(new Armor("Helmet", TypeArmor.HAT, 55, 0, 0, 40));
			bossItems.add(new Armor("Boots", TypeArmor.BOOTS, 65, 0, 0, 40));
			bossItems.add(new Armor("Breastplate", TypeArmor.BREASTPLATE, 110, 0, 0, 50));
			Door portal = new Door(0,0, DoorType.PORTAL);
			portal.open();
			bossItems.add(portal);
			bossItems.add(portal);
			bossItems.add(portal);
		
		return bossItems.get(randomHazqa.nextInt(bossItems.size()));
	}

	/**
	 * @return the player
	 */
	public static Player getPlayer() {
		return player;
	}


}
