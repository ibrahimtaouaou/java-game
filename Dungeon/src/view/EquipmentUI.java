package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import model.Item;
import model.Player;
import view.helper.ImageMgr;

public class EquipmentUI extends JDialog {

	private Player player;

	/**
	 * Constructor
	 * @param player
	 */
	public EquipmentUI(Player player){
		this.player = player;
		initDialog();
		initTop();
		initBottom();
		initCenter();
		initRight();
		initLeft();		

		this.setVisible(true);
	}

	/**
	 * set the specificities of the dialog
	 */
	private void initDialog() {
		this.setTitle("Player equipment");
		this.setSize(600, 530);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	/**
	 * create the top panel
	 */
	private void initTop() {
		JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));

		north.add(initLabel("Helmet"));
		north.add(new JLabel(findIcon(player.getEquipment().getHat())));
		north.add(initLabel("Weapon"));
		north.add(new JLabel(findIcon(player.getEquipment().getWeapon())));
		this.add(north, BorderLayout.PAGE_START);
	}

	/**
	 * create the bot panel
	 */
	private void initBottom() {
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));

		south.add(initLabel("Boots"));
		south.add(new JLabel(findIcon(player.getEquipment().getBoots())));
		this.add(south, BorderLayout.PAGE_END);
	}

	/**
	 * create the center panel
	 */
	private void initCenter() {
		this.add(new JLabel(ImageMgr.getAssassin()), BorderLayout.CENTER);
	}

	/**
	 * create the left panel
	 */
	private void initLeft() {
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		west.setBorder(new EmptyBorder(new Insets(50, 20, 150, 0)));

		west.add(initLabel("Gauntlets"), BorderLayout.CENTER);
		west.add(Box.createRigidArea(new Dimension(0, 50)));
		west.add(new JLabel(findIcon(player.getEquipment().getGauntlets())), BorderLayout.CENTER);
		west.add(Box.createRigidArea(new Dimension(0, 50)));
		west.add(initLabel("Ring"), BorderLayout.CENTER);
		west.add(Box.createRigidArea(new Dimension(0, 50)));
		west.add(new JLabel(findIcon(player.getEquipment().getRing())), BorderLayout.CENTER);
		this.add(west, BorderLayout.LINE_START);
	}

	/**
	 * create the right panel
	 */
	private void initRight() {
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.setBorder(new EmptyBorder(new Insets(50, 0, 150, 20)));

		east.add(initLabel("Breastplate"), BorderLayout.CENTER);
		east.add(Box.createRigidArea(new Dimension(0, 50)));
		east.add(new JLabel(findIcon(player.getEquipment().getBreastplate())), BorderLayout.CENTER);
		east.add(Box.createRigidArea(new Dimension(0, 50)));
		east.add(initLabel("Necklace"), BorderLayout.CENTER);
		east.add(Box.createRigidArea(new Dimension(0, 50)));
		east.add(new JLabel(findIcon(player.getEquipment().getNecklace())), BorderLayout.CENTER);
		this.add(east, BorderLayout.LINE_END);
	}

	/**
	 * create the labels
	 */
	private JLabel initLabel(String text){
		JLabel l = new JLabel(text);
		l.setFont(new Font("Century", Font.BOLD,20));
		return l;
	}

	/**
	 * Find appropriate image for the item
	 */
	private ImageIcon findIcon(Item item){
		try{
			switch(item.getName()){
			case "Boots" : return ImageMgr.getBoots1();
			case "Legend Boots" : return ImageMgr.getBoots2();

			case "Helmet" : return ImageMgr.getHelmet1();
			case "Legend Helmet" : return ImageMgr.getHelmet2();

			case "Staff" : return ImageMgr.getStaff1();
			case "Legend Staff" : return ImageMgr.getStaff2();
			case "Sword" : return ImageMgr.getSword1();
			case "Legend Sword" : return ImageMgr.getSword2();
			case "Bow" : return ImageMgr.getBow1();
			case "Legend Bow" : return ImageMgr.getBow2();
			case "Legend Scythe" : return ImageMgr.getScythe2();

			case "Gauntlets" : return ImageMgr.getGauntlets1();
			case "Legend Gauntlets" : return ImageMgr.getGauntlets2();

			case "Ring" : return ImageMgr.getRing();

			case "Adventurer's cap" : return ImageMgr.getBreastplate2();
			case "Adventurer's armor" : return ImageMgr.getBreastplate2();			
			case "Breastplate" : return ImageMgr.getBreastplate2();
			case "Legend Breastplate" : return ImageMgr.getBreastplate3();
			case "Necklace" : return ImageMgr.getNecklace();

			default : return ImageMgr.getEmpty(); 
			}
		}catch(NullPointerException n){
			return ImageMgr.getEmpty();
		}
	}

}
