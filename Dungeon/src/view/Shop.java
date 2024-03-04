package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Armor;
import model.Inventory;
import model.Item;
import model.Weapon;
import model.Armor.TypeArmor;
import view.helper.ImageMgr;

public class Shop extends JPanel {
	private Window window;
	private JLabel labelStaff, labelSword, labelBow, labelScythe, labelShop, labelBreastplate, labelBoots, labelGauntlets, labelHat, labelCoin, lstaff, lsword,
	lbow, lscythe, lbreastplate, lboots, lgauntlets, lhat, infoStaff, infoSword, infoBow, infoScythe, infoBreast, infoHat, infoBoots, infoGauntlets;
	private JButton back;
	private Font itemFont, shop, infoFont;
	private ImageIcon istaff, isword, ibow, iscythe, ibreastplate, iboots, igauntlets, ihat, icoins;
	private Weapon staff, sword, scythe, bow;
	private Armor breastplate, boots, gauntlets, hat;
	private boolean atLeastOneBought;
	private Inventory inventory;

	/**
	 * Constructor
	 * @param window
	 * @param inventory
	 */
	public Shop (Window window, Inventory inventory){
		this.window = window;
		this.inventory = inventory;


		setPreferredSize(new Dimension(950,580));
		setBackground(Color.darkGray);
		initEquip();
		initFont();
		initImageIcon();
		initLabel();
		convert();
		labelShop = new JLabel ("SHOP   ");
		labelShop.setFont(shop);
		labelShop.setForeground(Color.BLACK);

		add(labelShop);
		add(new ShopItem(labelStaff, lstaff, staff, infoStaff));
		add(new ShopItem(labelSword, lsword, sword, infoSword));
		add(new ShopItem(labelBow, lbow, bow, infoBow));
		add(new ShopItem(labelScythe, lscythe, scythe, infoScythe));
		add(new ShopItem(labelBreastplate, lbreastplate, breastplate, infoBreast));
		add(new ShopItem(labelBoots, lboots, boots, infoBoots));
		add(new ShopItem(labelHat, lhat, hat, infoHat));
		add(new ShopItem(labelGauntlets, lgauntlets, gauntlets, infoGauntlets));

		add(labelCoin);
		back = new JButton("Back");
		back.addActionListener(backAction);
		add(back);
	}

	/**
	 * create the font of the labels
	 */
	private void initFont(){
		shop = new Font("Serif", Font.BOLD, 40);
		itemFont = new Font("Serif", Font.BOLD, 20);
		infoFont = new Font("Serif", Font.BOLD, 15);
	}

	/**
	 * create the items in the shop
	 */
	private void initEquip(){
		staff = new Weapon("Legend Staff", 50.0, 1, -100, -100, 5000);
		sword = new Weapon("Legend Sword", 50.0, 1, -100, -100, 5000);
		scythe = new Weapon("Legend Scythe", 60.0, 1, -100, -100, 0);
		bow = new Weapon("Legend Bow", 40.0, 3, -100, -100, 6000);
		breastplate = new Armor("Legend Breastplate", TypeArmor.BREASTPLATE, 150, -100, -100, 6000);
		boots = new Armor("Legend Boots", TypeArmor.BOOTS, 75, -100, -100, 5000);
		gauntlets = new Armor("Legend Gauntlets", TypeArmor.GAUNTLETS, 65, -100, -100, 5000);
		hat = new Armor("Legend Helmet", TypeArmor.HAT, 65, -100, -100, 5000);
	}

	/**
	 * create the labels
	 * @param str the string
	 * @param font the font
	 * @return
	 */
	private JLabel createLabel(String str, Font font){
		JLabel jlabel = new JLabel(str);
		jlabel.setForeground(Color.WHITE);
		jlabel.setFont(font);
		java.util.Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		jlabel.setFont(font.deriveFont(attributes));
		return jlabel;
	}

	/**
	 * create the labels
	 */
	private void initLabel(){
		labelStaff = createLabel("Staff", itemFont);
		labelSword = createLabel("Sword", itemFont);
		labelBow = createLabel("Bow", itemFont);
		labelScythe = createLabel("Scythe", itemFont);
		labelBreastplate = createLabel("Breastplate", itemFont);
		labelBoots = createLabel("Boots", itemFont);
		labelGauntlets = createLabel("Gauntlets", itemFont);
		labelHat = createLabel("Hat", itemFont);
		labelCoin = createLabel("Coin : " + inventory.getCoins(), itemFont);

		infoStaff = createLabel("<html>Damage = "+staff.getDamage()+"<br>Price = "+staff.getPrice(), infoFont);
		infoSword = createLabel("<html>Damage = "+sword.getDamage()+"<br>Price = "+sword.getPrice(), infoFont);
		infoBow = createLabel("<html>Damage = "+bow.getDamage()+"<br>Price = "+bow.getPrice(), infoFont);
		infoScythe = createLabel("<html>Damage = "+scythe.getDamage()+"<br>Price = "+scythe.getPrice(), infoFont);
		infoBreast = createLabel("<html>Defense value = "+breastplate.getDefense()+"<br>Price = "+breastplate.getPrice(), infoFont);
		infoGauntlets = createLabel("<html>Defense value = "+gauntlets.getDefense()+"<br>Price = "+gauntlets.getPrice(), infoFont);
		infoHat = createLabel("<html>Defense value = "+hat.getDefense()+"<br>Price = "+hat.getPrice(), infoFont);
		infoBoots = createLabel("<html>Defense value = "+boots.getDefense()+"<br>Price = "+boots.getPrice(), infoFont);
	}


	/**
	 * create the images
	 */
	public void initImageIcon(){
		istaff = ImageMgr.getStaff();
		isword = ImageMgr.getSword();
		ibow = ImageMgr.getBow();
		iscythe = ImageMgr.getScythe();
		ibreastplate = ImageMgr.getBreastplate();
		igauntlets = ImageMgr.getGauntlets();
		iboots = ImageMgr.getBoots();
		ihat = ImageMgr.getHat();
		icoins = ImageMgr.getCoins();
	}

	/**
	 * convert the images to label for the buttons
	 * @param img
	 * @return
	 */
	public JLabel imageToLabel(ImageIcon img){
		JLabel jlabel = new JLabel(img);
		return jlabel;
	}

	/**
	 * create the new labels converted
	 */
	public void convert(){
		lstaff = imageToLabel(istaff);
		lsword = imageToLabel(isword);
		lbow = imageToLabel(ibow);
		lscythe = imageToLabel(iscythe);
		lbreastplate = imageToLabel(ibreastplate);
		lboots = imageToLabel(iboots);
		lhat = imageToLabel(ihat);
		lgauntlets = imageToLabel(igauntlets);
	}

	ActionListener backAction = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(atLeastOneBought){
				try (	FileOutputStream outputFileStream = new FileOutputStream("saved");
						ObjectOutputStream outputStream = new ObjectOutputStream(outputFileStream);)
				{
					outputStream.writeObject(inventory);
				}catch(IOException io){
					io.printStackTrace();
				}
			}
			window.displayMainMenu(Shop.this);

			window.validate();
			window.repaint();
			window.pack();
			window.setLocationRelativeTo(null);
		}
	};


	class ShopItem extends JPanel {
		JLabel name;
		JLabel image;
		JLabel info;
		Item item;
		JButton buyButton;


		/**
		 * Constructor
		 * @param name
		 * @param image
		 * @param item
		 * @param info
		 */
		public ShopItem(JLabel name, JLabel image, Item item, JLabel info) {
			super();
			this.name = name;
			this.image = image;
			this.item = item;
			this.info = info;
			this.buyButton = initButton();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			image.setAlignmentX(Component.CENTER_ALIGNMENT);
			info.setAlignmentX(Component.CENTER_ALIGNMENT);
			buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(name);
			this.add(image);
			this.add(info);
			this.add(buyButton);
			this.setBackground(Color.WHITE);
		}

		/**
		 * create  the buttons
		 * @return
		 */
		private JButton initButton() {
			JButton button = new JButton(icoins);
			button.setSize(100, 5);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setContentAreaFilled(false);
			button.setFocusable(false);
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			button.addActionListener(buyAction);
			button.setEnabled(inventory.getCoins() >= item.getPrice());
			return button;
		}

		ActionListener buyAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this shit?", "Last chance bruh..", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					if(inventory.addItem(item)){
						inventory.debit(item.getPrice());
						atLeastOneBought = true;
					}
					else{
						JOptionPane.showMessageDialog(window, "You're a fcking dumbass, the inventory is full");
					}
				}		
			}
		};
	}
}