package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import model.Armor;
import model.Block;
import model.Boss;
import model.Bot;
import model.Chest;
import model.Direction;
import model.Door;
import model.Door.DoorType;
import model.GameObject;
import model.Potion;
import model.Warrior;
import model.Weapon;
import model.Wizard;
import view.helper.ImageMgr;

public class Map extends JPanel {
	private List<GameObject> gameObjects;

	public Map(int width, int height) {
		this.setPreferredSize(new Dimension(width*32,height*32));
		this.setLayout(new BorderLayout());
	}

//	@Override
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		g.drawImage(ImageMgr.getBg(), 0, 0, this);
//	}

	/**
	 * set the list of gameObject
	 * @param gameObjects
	 */
	public void setGameObjetcs(List<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	/**
	 * @return the gameObjects
	 */
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	/**
	 * repaint the map
	 */
	public void redraw() {
		this.repaint();
	}


	public void paint(Graphics g) {
		try{
			this.paintComponent(g);
			for(GameObject o: gameObjects){
				if(o instanceof Block){
					drawImage(g, o, ImageMgr.getBlock());
				}
				else if(o instanceof Chest)
					drawImage(g, o, ImageMgr.getChest());
				else if(o instanceof Warrior){
					if(((Warrior) o).getDirection() == Direction.UP)
						drawImage(g, o, ImageMgr.getWarriorUp());
					else if(((Warrior) o).getDirection() == Direction.DOWN)
						drawImage(g, o, ImageMgr.getWarriorDown());
					else if(((Warrior) o).getDirection() == Direction.LEFT)
						drawImage(g, o, ImageMgr.getWarriorLeft());
					else if(((Warrior) o).getDirection() == Direction.RIGHT)
						drawImage(g, o, ImageMgr.getWarriorRight());
				}
				else if(o instanceof Wizard){
					if(((Wizard) o).getDirection() == Direction.UP)
						drawImage(g, o, ImageMgr.getWizardUp());
					else if(((Wizard) o).getDirection() == Direction.DOWN)
						drawImage(g, o, ImageMgr.getWizardDown());
					else if(((Wizard) o).getDirection() == Direction.LEFT)
						drawImage(g, o, ImageMgr.getWizardLeft());
					else if(((Wizard) o).getDirection() == Direction.RIGHT)
						drawImage(g, o, ImageMgr.getWizardRight());
				}
				else if(o instanceof Potion)
					drawImage(g, o, ImageMgr.getPotion());
				else if(o instanceof Bot){
					if("Slime".equals(((Bot)o).getName()))
						drawImage(g, o, ImageMgr.getSlime());
					else if("Skeleton".equals(((Bot)o).getName()))
						drawImage(g, o, ImageMgr.getSkeleton());
					else if("Troll".equals(((Bot)o).getName()))
						drawImage(g, o, ImageMgr.getTroll());
					else if(o instanceof Boss)
						drawImage(g, o, ImageMgr.getGiant());
				}
				else if(o instanceof Door)
					if(((Door) o).getType() == DoorType.DOOR)
						drawImage(g, o, ImageMgr.getDoor());
					else
						drawImage(g, o, ImageMgr.getPortal());
				else if(o instanceof Armor){
					if("Ring".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIring()); 
					else if("Helmet".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIhelmet1());
					else if("Boots".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIboots1());
					else if("Breastplate".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIbreastplate2());
					else if("Gauntlets".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIgauntlets1());
					else if("Necklace".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getInecklace());
					else if("Adventurer's armor".equals(((Armor)o).getName()) || "Adventurer's cap".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIbreastplate2());
					else if("Legend Breastplate".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIbreastplate3());
					else if("Legend Gauntlets".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIgauntlets2());
					else if("Legend Boots".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIboots2());
					else if("Legend Helmet".equals(((Armor)o).getName()))
						drawImage(g, o, ImageMgr.getIhelmet2());
				}
				else if(o instanceof Weapon){
					if("Staff".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIstaff1());
					else if("Sword".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIsword1());
					else if("Bow".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIbow1());
					else if("Legend Sword".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIsword2());
					else if("Legend Staff".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIstaff2());
					else if("Legend Bow".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIbow2());
					else if("Legend Scythe".equals(((Weapon)o).getName()))
						drawImage(g, o, ImageMgr.getIscythe2());
				}

			}
		}catch (Exception e) {}
	}

	public void drawImage(Graphics g, GameObject go, Image image){
		g.drawImage(image, go.getPosX()*32, go.getPosY()*32,this);
	}
	
	public static Image getImage(GameObject gameObject){// ajouter toutes les images pour les faire aparaitre dans infobar
		if(gameObject instanceof Bot){
			if("Slime".equals(((Bot)gameObject).getName()))
				return ImageMgr.getSlime();
			else if("Skeleton".equals(((Bot)gameObject).getName()))
				return ImageMgr.getSkeleton();
			else if("Troll".equals(((Bot)gameObject).getName()))
				return ImageMgr.getTroll();
			else if(gameObject instanceof Boss)
				return ImageMgr.getGiant();
		}
		return null;
	}
}