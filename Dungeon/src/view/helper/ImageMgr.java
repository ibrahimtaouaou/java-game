package view.helper;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageMgr {

	private static final String IMG_FOLDER = "res/img/";
	
	/*Main menu images*/
	private static ImageIcon menuTitle, start, newGame, shop, help, exit, warriorC, wizardC;
	private static Image main_bg;
	
	private static ImageIcon empty, ring, necklace, 
							boots1, boots2, 
							breastplate2, breastplate3, 
							gauntlets1, gauntlets2, 
							sword1, sword2, 
							staff1, staff2,
							bow1, bow2, 
							helmet1, helmet2, 
							scythe2, assassin, ipotion;
	/*Shop images*/
	private static ImageIcon staff, sword, bow, scythe, breastplate, gauntlets, boots, hat, coins;

	/*Map images*/
	private static Image warriorUp, warriorDown, warriorLeft, warriorRight,
							wizardUp, wizardDown, wizardLeft, wizardRight,
							potion, door, block, wall, chest, portal,
							slime, skeleton, troll, giant,
							bg, iring, inecklace, 
							iboots1, iboots2, 
							ibreastplate2, ibreastplate3, 
							igauntlets1, igauntlets2, 
							isword1, isword2, 
							istaff1, istaff2,
							ibow1, ibow2, 
							ihelmet1, ihelmet2, 
							iscythe2;
	
	static{
		try {
			main_bg = readImg("main_bg.png");
			menuTitle = readIconImg("menu_title.png");
			start = readIconImg("start_button.png");
			newGame = readIconImg("newgame_button.png");
			shop = readIconImg("shop_button.png");
			help = readIconImg("help_button.png");
			exit = readIconImg("exit_button.png");
			warriorC = readIconImg("warriorC.png");
			wizardC = readIconImg("wizardC.png");
			
			ipotion = readIconImg("potion.png");
			empty = readIconImg("empty.png");
			ring = readIconImg("ring.png");
			necklace = readIconImg("necklace.png");
			boots1 = readIconImg("boots1.png");
			sword1 = readIconImg("sword1.png");
			staff1 = readIconImg("staff1.png");
			breastplate2 = readIconImg("breastplate2.png");
			gauntlets1 = readIconImg("gauntlets1.png");
			bow1 = readIconImg("bow1.png");
			helmet1 = readIconImg("helmet1.png");
			boots2 = readIconImg("boots2.png");
			sword2 = readIconImg("sword2.png");
			staff2 = readIconImg("staff2.png");
			breastplate3 = readIconImg("breastplate3.png");
			gauntlets2 = readIconImg("gauntlets2.png");
			bow2 = readIconImg("bow2.png");
			helmet2 = readIconImg("helmet2.png");
			scythe2 = readIconImg("scythe2.png");
			assassin = readIconImg("assassin.png");
			staff = readIconImg("staff.png");
			sword = readIconImg("sword.png");
			bow = readIconImg("bow.png");
			scythe = readIconImg("scythe.png");
			breastplate = readIconImg("breastplate.png");
			gauntlets = readIconImg("gauntlets.png");
			boots = readIconImg("boots.png");
			hat = readIconImg("helmet.png");
			coins = readIconImg("coins.png");
			
			wizardUp=readImg("wizard_up.png");
			wizardDown=readImg("wizard_down.png");
			wizardLeft=readImg("wizard_left.png");
			wizardRight=readImg("wizard_right.png");
			warriorUp=readImg("warrior_up.png");
			warriorDown=readImg("warrior_down.png");
			warriorLeft=readImg("warrior_left.png");
			warriorRight=readImg("warrior_right.png");
			potion=readImg("potion.png");
			door = readImg("door.png");
			slime = readImg("slime.png");
			skeleton = readImg("skeleton.png");
			block = readImg("block.png");
			chest = readImg("chest.png");
			troll = readImg("troll.png");
			giant = readImg("giant.png");
			portal = readImg("portal.png");
			
			iring = readImg("ring.png");
			inecklace = readImg("necklace.png");
			iboots1 = readImg("boots1.png");
			isword1 = readImg("sword1.png");
			istaff1 = readImg("staff1.png");
			ibreastplate2 = readImg("breastplate2.png");
			igauntlets1 = readImg("gauntlets1.png");
			ibow1 = readImg("bow1.png");
			ihelmet1 = readImg("helmet1.png");
			iboots2 = readImg("boots2.png");
			isword2 = readImg("sword2.png");
			istaff2 = readImg("staff2.png");
			ibreastplate3 = readImg("breastplate3.png");
			igauntlets2 = readImg("gauntlets2.png");
			ibow2 = readImg("bow2.png");
			ihelmet2 = readImg("helmet2.png");
			iscythe2 = readImg("scythe2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ImageIcon readIconImg(String filename) throws IOException{
		return new ImageIcon(ImageIO.read(new File(IMG_FOLDER + filename)));
	}
	private static Image readImg(String filename) throws IOException{
		return ImageIO.read(new File(IMG_FOLDER + filename));
	}
	
	/**
	 * @return the empty
	 */
	public static final ImageIcon getEmpty() {
		return empty;
	}

	/**
	 * @return the ring
	 */
	public static final ImageIcon getRing() {
		return ring;
	}

	/**
	 * @return the necklace
	 */
	public static final ImageIcon getNecklace() {
		return necklace;
	}

	/**
	 * @return the boots1
	 */
	public static final ImageIcon getBoots1() {
		return boots1;
	}

	/**
	 * @return the boots2
	 */
	public static final ImageIcon getBoots2() {
		return boots2;
	}

	/**
	 * @return the breastplate2
	 */
	public static final ImageIcon getBreastplate2() {
		return breastplate2;
	}

	/**
	 * @return the breastplate3
	 */
	public static final ImageIcon getBreastplate3() {
		return breastplate3;
	}

	/**
	 * @return the gauntlets1
	 */
	public static final ImageIcon getGauntlets1() {
		return gauntlets1;
	}

	/**
	 * @return the gauntlets2
	 */
	public static final ImageIcon getGauntlets2() {
		return gauntlets2;
	}

	/**
	 * @return the sword1
	 */
	public static final ImageIcon getSword1() {
		return sword1;
	}

	/**
	 * @return the sword2
	 */
	public static final ImageIcon getSword2() {
		return sword2;
	}

	/**
	 * @return the staff1
	 */
	public static final ImageIcon getStaff1() {
		return staff1;
	}

	/**
	 * @return the staff2
	 */
	public static final ImageIcon getStaff2() {
		return staff2;
	}

	/**
	 * @return the bow1
	 */
	public static final ImageIcon getBow1() {
		return bow1;
	}

	/**
	 * @return the bow2
	 */
	public static final ImageIcon getBow2() {
		return bow2;
	}

	/**
	 * @return the helmet1
	 */
	public static final ImageIcon getHelmet1() {
		return helmet1;
	}

	/**
	 * @return the helmet2
	 */
	public static final ImageIcon getHelmet2() {
		return helmet2;
	}

	/**
	 * @return the scythe
	 */
	public static final ImageIcon getScythe2() {
		return scythe2;
	}

	/**
	 * @return the assassin
	 */
	public static final ImageIcon getAssassin() {
		return assassin;
	}

	/**
	 * @return the imgFolder
	 */
	public static final String getImgFolder() {
		return IMG_FOLDER;
	}

	/**
	 * @return the staff
	 */
	public static final ImageIcon getStaff() {
		return staff;
	}

	/**
	 * @return the sword
	 */
	public static final ImageIcon getSword() {
		return sword;
	}

	/**
	 * @return the bow
	 */
	public static final ImageIcon getBow() {
		return bow;
	}

	/**
	 * @return the scythe
	 */
	public static final ImageIcon getScythe() {
		return scythe;
	}

	/**
	 * @return the breastplate
	 */
	public static final ImageIcon getBreastplate() {
		return breastplate;
	}
	

	/**
	 * @return the ipotion
	 */
	public static ImageIcon getIpotion() {
		return ipotion;
	}
	/**
	 * @return the gauntlets
	 */
	public static final ImageIcon getGauntlets() {
		return gauntlets;
	}

	/**
	 * @return the boots
	 */
	public static final ImageIcon getBoots() {
		return boots;
	}

	/**
	 * @return the hat
	 */
	public static final ImageIcon getHat() {
		return hat;
	}

	/**
	 * @return the coins
	 */
	public static final ImageIcon getCoins() {
		return coins;
	}
	/**
	 * @return the wizardUp
	 */
	public static Image getWizardUp() {
		return wizardUp;
	}
	/**
	 * @param wizardUp the wizardUp to set
	 */
	public static void setWizardUp(Image wizardUp) {
		ImageMgr.wizardUp = wizardUp;
	}
	/**
	 * @return the wizardDown
	 */
	public static Image getWizardDown() {
		return wizardDown;
	}
	/**
	 * @param wizardDown the wizardDown to set
	 */
	public static void setWizardDown(Image wizardDown) {
		ImageMgr.wizardDown = wizardDown;
	}
	/**
	 * @return the wizardLeft
	 */
	public static Image getWizardLeft() {
		return wizardLeft;
	}
	/**
	 * @param wizardLeft the wizardLeft to set
	 */
	public static void setWizardLeft(Image wizardLeft) {
		ImageMgr.wizardLeft = wizardLeft;
	}
	/**
	 * @return the wizardRight
	 */
	public static Image getWizardRight() {
		return wizardRight;
	}
	/**
	 * @param wizardRight the wizardRight to set
	 */
	public static void setWizardRight(Image wizardRight) {
		ImageMgr.wizardRight = wizardRight;
	}
	/**
	 * @return the warriorUp
	 */
	public static final Image getWarriorUp() {
		return warriorUp;
	}
	/**
	 * @return the warriorDown
	 */
	public static final Image getWarriorDown() {
		return warriorDown;
	}
	/**
	 * @return the warriorLeft
	 */
	public static final Image getWarriorLeft() {
		return warriorLeft;
	}
	/**
	 * @return the warriorRight
	 */
	public static final Image getWarriorRight() {
		return warriorRight;
	}
	/**
	 * @return the potion
	 */
	public static final Image getPotion() {
		return potion;
	}
	/**
	 * @return the door
	 */
	public static final Image getDoor() {
		return door;
	}
	/**
	 * @return the block
	 */
	public static final Image getBlock() {
		return block;
	}
	/**
	 * @return the wall
	 */
	public static final Image getWall() {
		return wall;
	}
	/**
	 * @return the chest
	 */
	public static final Image getChest() {
		return chest;
	}
	/**
	 * @return the portal
	 */
	public static final Image getPortal() {
		return portal;
	}
	/**
	 * @return the slime
	 */
	public static final Image getSlime() {
		return slime;
	}
	/**
	 * @return the skeleton
	 */
	public static final Image getSkeleton() {
		return skeleton;
	}
	/**
	 * @return the troll
	 */
	public static final Image getTroll() {
		return troll;
	}
	/**
	 * @return the giant
	 */
	public static final Image getGiant() {
		return giant;
	}
	/**
	 * @return the bg
	 */
	public static final Image getBg() {
		return bg;
	}
	/**
	 * @return the menuTitle
	 */
	public static final ImageIcon getMenuTitle() {
		return menuTitle;
	}
	/**
	 * @return the start
	 */
	public static final ImageIcon getStart() {
		return start;
	}
	/**
	 * @return the newGame
	 */
	public static final ImageIcon getNewGame() {
		return newGame;
	}
	/**
	 * @return the shop
	 */
	public static final ImageIcon getShop() {
		return shop;
	}
	/**
	 * @return the help
	 */
	public static final ImageIcon getHelp() {
		return help;
	}
	/**
	 * @return the exit
	 */
	public static final ImageIcon getExit() {
		return exit;
	}
	/**
	 * @return the warriorC
	 */
	public static final ImageIcon getWarriorC() {
		return warriorC;
	}
	/**
	 * @return the wizardC
	 */
	public static final ImageIcon getWizardC() {
		return wizardC;
	}
	/**
	 * @return the main_bg
	 */
	public static final Image getMain_bg() {
		return main_bg;
	}
	/**
	 * @return the iring
	 */
	public static Image getIring() {
		return iring;
	}
	/**
	 * @return the inecklace
	 */
	public static Image getInecklace() {
		return inecklace;
	}
	/**
	 * @return the iboots1
	 */
	public static Image getIboots1() {
		return iboots1;
	}
	/**
	 * @return the iboots2
	 */
	public static Image getIboots2() {
		return iboots2;
	}
	/**
	 * @return the ibreastplate2
	 */
	public static Image getIbreastplate2() {
		return ibreastplate2;
	}
	/**
	 * @return the ibreastplate3
	 */
	public static Image getIbreastplate3() {
		return ibreastplate3;
	}
	/**
	 * @return the igauntlets1
	 */
	public static Image getIgauntlets1() {
		return igauntlets1;
	}
	/**
	 * @return the igauntlets2
	 */
	public static Image getIgauntlets2() {
		return igauntlets2;
	}
	/**
	 * @return the isword1
	 */
	public static Image getIsword1() {
		return isword1;
	}
	/**
	 * @return the isword2
	 */
	public static Image getIsword2() {
		return isword2;
	}
	/**
	 * @return the istaff1
	 */
	public static Image getIstaff1() {
		return istaff1;
	}
	/**
	 * @return the istaff2
	 */
	public static Image getIstaff2() {
		return istaff2;
	}
	/**
	 * @return the ibow1
	 */
	public static Image getIbow1() {
		return ibow1;
	}
	/**
	 * @return the ibow2
	 */
	public static Image getIbow2() {
		return ibow2;
	}
	/**
	 * @return the ihelmet1
	 */
	public static Image getIhelmet1() {
		return ihelmet1;
	}
	/**
	 * @return the ihelmet2
	 */
	public static Image getIhelmet2() {
		return ihelmet2;
	}
	/**
	 * @return the iscythe2
	 */
	public static Image getIscythe2() {
		return iscythe2;
	}
	
	
	
}