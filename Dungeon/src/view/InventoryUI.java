package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.Armor;
import model.Equipment;
import model.Game;
import model.Inventory;
import model.Item;
import model.Potion;
import model.Weapon;
import view.helper.ImageMgr;

public class InventoryUI extends JPanel {

	/* les bouton de l'inventaire*/
	private JButton[] items;

	private Game game;

	/**
	 * Constructor
	 * @param game
	 */
	public InventoryUI(Game game){
		this.game = game;
		this.items = new JButton[Inventory.getMaxSize()];

		this.setBackground(Color.BLACK);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		displayItemsButton();
	}

	/**
	 * displays the buttons
	 */
	private void displayItemsButton(){
		this.removeAll();
		int index = 0;
		for(Item item : game.getPlayer().getInventory().getIterator()){
			this.add(items[index++] = initItemButton(item));
		}
	}

	/**
	 * create the item buttons
	 * @param item item that needs a button to create
	 * @return the button created
	 */
	private JButton initItemButton(Item item) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(34,34));
		button.setEnabled(false);
		initImage(item, button);
		return button;
	}

	/**
	 * repaint the map
	 */
	public void redraw(){
		displayItemsButton();
		this.repaint();
		this.revalidate();
	}

	/**
	 * give an image to each items
	 * @param item
	 * @param button
	 */
	private void initImage(Item item, JButton button){
		if(item == null)
			return;
		if(item instanceof Potion)
			initButton(ImageMgr.getIpotion(), item, button);
		else if(item instanceof Armor){
			if("Ring".equals(((Armor)item).getName()))
				initButton(ImageMgr.getRing(), item, button);
			else if("Necklace".equals(((Armor)item).getName()))
				initButton(ImageMgr.getNecklace(), item, button);
			else if("Breastplate".equals(((Armor)item).getName()))
				initButton(ImageMgr.getBreastplate2(), item, button);
			else if("Gauntlets".equals(((Armor)item).getName()))
				initButton(ImageMgr.getGauntlets1(), item, button);
			else if("Boots".equals(((Armor)item).getName()))
				initButton(ImageMgr.getBoots1(), item, button);
			else if("Helmet".equals(((Armor)item).getName()))
				initButton(ImageMgr.getHelmet1(), item, button);
			else if("Adventurer's armor".equals(((Armor)item).getName()) || "Adventurer's cap".equals(((Armor)item).getName()))
				initButton(ImageMgr.getBreastplate2(), item, button);
			else if("Legend Breastplate".equals(((Armor)item).getName()))
				initButton(ImageMgr.getBreastplate3(), item, button);
			else if("Legend Gauntlets".equals(((Armor)item).getName()))
				initButton(ImageMgr.getGauntlets2(), item, button);
			else if("Legend Boots".equals(((Armor)item).getName()))
				initButton(ImageMgr.getBoots2(), item, button);
			else if("Legend Helmet".equals(((Armor)item).getName()))
				initButton(ImageMgr.getHelmet2(), item, button);
		}
		else if(item instanceof Weapon){
			if("Sword".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getSword1(), item, button);
			else if("Staff".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getStaff1(), item, button);
			else if("Bow".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getBow1(), item, button);
			else if("Legend Sword".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getSword2(), item, button);
			else if("Legend Staff".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getStaff2(), item, button);
			else if("Legend Bow".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getBow2(), item, button);
			else if("Legend Scythe".equals(((Weapon)item).getName()))
				initButton(ImageMgr.getScythe2(), item, button);
		}
	}

	/**
	 * create the button
	 * @param imageIcon
	 * @param item
	 * @param button
	 */
	private void initButton(ImageIcon imageIcon, Item item, JButton button) {
		button.setIcon(imageIcon);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFocusable(false);
		button.setEnabled(true);
		button.setToolTipText(initToolTipText(item));

		JPopupMenu popup = new JPopupMenu();
		JMenuItem useItem = new JMenuItem("Use");
		useItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.use(item);
			}
		});
		JMenuItem sellItem = new JMenuItem("Sell");
		sellItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.sell(item);
			}
		});

		popup.add(useItem);
		popup.add(sellItem);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popup.show(InventoryUI.this, button.getBounds().x, button.getBounds().y + button.getBounds().height);
			}
		});
	}

	 /**
	  * create the informations for each item
	  * @param item
	  * @return the information (string)
	  */
	private String initToolTipText(Item item) {
		Equipment equipment = game.getPlayer().getEquipment(); 
		String green = "<font color=\"green\">";
		String red = "<font color=\"red\">";
		String end = " </b> </font> <br>";
		String BR = "</b> <br>";
		StringBuilder str = new StringBuilder("<html> <b>").append(item.getName()).append(BR);
		if(item instanceof Potion){
			str.append("HP +").append(((Potion) item).getHeal()).append(BR);
		}else if (item instanceof Weapon){
			if (equipment.getDiff((Weapon) item) > 0)
				str.append(green).append("<b> ATQ ").append(equipment.getDiff((Weapon) item)).append(end);
			else
				str.append(red).append("<b> ATQ ").append(equipment.getDiff((Weapon) item)).append(end);
			str.append("DIST ").append(((Weapon) item).getDistance()).append(BR);
		}else if (item instanceof Armor){
			if(equipment.getDiff((Armor) item) > 0)
				str.append(green).append("<b> DEF ").append(equipment.getDiff((Armor) item)).append(end);
			else
				str.append(red).append("<b> DEF ").append(equipment.getDiff((Armor) item)).append(end);
		}
		str.append(item.getPrice()).append("$").append("</html>");

		return str.toString();
	}

	
}
