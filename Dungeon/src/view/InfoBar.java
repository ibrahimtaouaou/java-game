package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Bot;
import model.Equipment.Quality;
import model.Game;
import model.Player;

public class InfoBar extends JPanel {
	private static final String DEFENSE = "Defense : ";
	private static final String DAMAGE  = "Damage : ";
	private static final String COINS   = "Coins : ";
	private static final String LEVEL   = "LVL ";

	private JProgressBar lifeBarPlayer,skillBar,lifeBarBot;
	private JLabel damage, defense, level, coins;
	private JLabel damageBot, defenseBot;
	private JPanel infoBotPanel;
	private Player player;
	private Bot bot;
	

	private static final Color[] colors = {			
			new Color(191,0,2),//low life = red
			new Color(172,22,1),
			new Color(154,45,1),
			new Color(154,45,1),
			new Color(154,73,2),
			new Color(206,108,9),
			new Color(206,134,9),
			new Color(81,137,0),
			new Color(62,160,0),
			new Color(62,160,0),
			new Color(28,218,7)//full life = green
	};

	/**
	 * constructor
	 * @param width width of the frame
	 * @param player
	 */
	public InfoBar(int width , Player player) {
		this.player = player;

		this.setPreferredSize(new Dimension(width*32,100));
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());

		initInfoPlayerPanel();
		initInfoBotPanel();
	}

	/**
	 * create the panel with player's info
	 */
	private void initInfoPlayerPanel() {
		JPanel infoPlayerPanel = new JPanel();
		infoPlayerPanel.setBackground(Color.BLACK);
		infoPlayerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0 , 2));
		infoPlayerPanel.setPreferredSize(new Dimension(100, 100));

		lifeBarPlayer = new JProgressBar();
		lifeBarPlayer.setString(player.getLife() +"/"+ Player.getMaxHealth());
		lifeBarPlayer.setStringPainted(true);
		lifeBarPlayer.setPreferredSize(new Dimension(100, 18));
		infoPlayerPanel.add(lifeBarPlayer);

		skillBar = new JProgressBar();
		skillBar.setForeground(Color.BLUE);
		skillBar.setPreferredSize(new Dimension(100, 15));
		infoPlayerPanel.add(skillBar);

		infoPlayerPanel.add(damage = initLabel(DAMAGE + player.getStrength()));
		infoPlayerPanel.add(defense = initLabel(DEFENSE + player.getDefense()));
		infoPlayerPanel.add(coins = initLabel(COINS + player.getInventory().getCoins() +"$"));

		add(infoPlayerPanel, BorderLayout.WEST);
	}

	/**
	 * 
	 * @param inventoryUI
	 */
	public void setInventoryPanel(InventoryUI inventoryUI){
		JPanel infoSkillPanel = new JPanel();
		infoSkillPanel.setBackground(Color.BLACK);
		infoSkillPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		infoSkillPanel.add(inventoryUI);
		infoSkillPanel.add(level = initLabel(LEVEL + Game.getLevel()));

		add(infoSkillPanel, BorderLayout.CENTER);
	}

	/**
	 * create the panel with the bot's info
	 */
	private void initInfoBotPanel(){
		JPanel infoBotPanel = new JPanel();
		infoBotPanel.setBackground(Color.BLACK);
		infoBotPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0 , 2));
		infoBotPanel.setPreferredSize(new Dimension(100, 100));

		if(bot != null){
			JLabel img = new JLabel(new ImageIcon(Map.getImage(bot)));
			infoBotPanel.add(img);
			lifeBarBot = new JProgressBar();
			lifeBarBot.setString(bot.getLife() +"/"+ bot.getMAX_HEALTH());
			lifeBarBot.setStringPainted(true);
			lifeBarBot.setPreferredSize(new Dimension(100, 18));
			infoBotPanel.add(lifeBarBot);
			infoBotPanel.add(damageBot = initLabel(DAMAGE + bot.getStrength()));
			infoBotPanel.add(defenseBot = initLabel(DEFENSE + bot.getDefense()));
		}
		if(this.infoBotPanel != null)
			remove(this.infoBotPanel);
		this.infoBotPanel = infoBotPanel;
		
		add(this.infoBotPanel, BorderLayout.EAST);
	}

	/**
	 * @param bot sets the bot
	 */
	public void setBot(Bot bot){
		this.bot = bot;
		initInfoBotPanel();
		redraw();
	}

	/**
	 * @return the bot
	 */
	public Bot getBot() {
		return bot;
	}

	/**
	 * create the labels
	 * @param texte the text to create
	 * @return the label
	 */
	private JLabel initLabel(String texte) {
		JLabel label = new JLabel(texte);
		label.setForeground(Color.white);
		return label;
	}

	/**
	 * repaint the panel
	 */
	public void redraw() {
		this.repaint();
		this.revalidate();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//life player
		lifeBarPlayer.setValue((int) (player.getLife() * 100 / Player.getMaxHealth()));
		lifeBarPlayer.setString((int)(player.getLife()) +"/"+ Player.getMaxHealth());
		lifeBarPlayer.setForeground(colors[lifeBarPlayer.getValue()/10]);
		//skill
		skillBar.setValue((int) player.getSkillValueBar());
		//damage
		damage.setText(DAMAGE + player.getStrength());
		if(player.isActiveSkill() && player.getSkillQuality() == Quality.STRENGTH){
			damage.setForeground(Color.yellow);
		}else{
			damage.setForeground(Color.white);
		}
		//defense
		defense.setText(DEFENSE + player.getDefense());
		if(player.isActiveSkill() && player.getSkillQuality() == Quality.DEFENSE){
			defense.setForeground(Color.yellow);
		}else{
			defense.setForeground(Color.white);
		}
		//coins
		coins.setText(COINS + player.getInventory().getCoins() + "$");
		//lvl
		level.setText(LEVEL + Game.getLevel());
		
		if(bot != null){
			//life bot
			lifeBarBot.setValue((int) (bot.getLife() * 100 / bot.getMAX_HEALTH()));
			lifeBarBot.setString((int)(bot.getLife()) +"/"+ bot.getMAX_HEALTH());
			lifeBarBot.setForeground(colors[lifeBarBot.getValue()/10]);
			//damage
			damageBot.setText(DAMAGE + bot.getStrength());
			//defense
			defenseBot.setText(DEFENSE + bot.getDefense());
		}
	}
}