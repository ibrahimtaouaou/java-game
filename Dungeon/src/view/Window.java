package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

import model.GameObject;
import model.Inventory;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.layers.GameOverLayer;
import view.layers.KeysLayer;
import view.layers.NextLevelLayer;

public class Window extends JFrame{

	private JLayer<JComponent> layer;
	private KeyListener keyboard;
	private MainMenu mainMenu;
	private InfoBar infoBar;
	private Timer timer;
	private Map map;

	/**
	 * Window constructor
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dungeon Game");
		this.setLayout(new BorderLayout());		
		setFocusable(true);
		requestFocusInWindow();
		setResizable(false);
		mainMenu = new MainMenu(this);
		add(mainMenu);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		Thread sound = new Thread(){
			public void run(){
				AudioStream BGM;
				for(;;){
					try{ 
						BGM=new AudioStream(new FileInputStream("res/songs/EF musique Proj1.wav"));
						AudioPlayer.player.start(BGM);
						sleep(145000);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		};
		sound.start();   
	}



	/**
	 * GameOver animation layer
	 */
	public void gameOver(){
		removeKeyListener();
		LayerUI<JComponent> ui = new GameOverLayer<>();
		layer = new JLayer<>(getMap(), ui);
		getContentPane().add(layer);
		remove(getMap());
		validate();

		timer = new Timer(60, new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				i++;
				if(i==16){
					timer.stop();
				} 
			}
		});
		timer.start();
	}

	/**
	 * Next level animation layer
	 */
	public void nextLevel(){
		removeKeyListener();
		LayerUI<JComponent> ui = new NextLevelLayer<>();
		layer = new JLayer<>(getMap(), ui);
		getContentPane().add(layer);
		validate();

		timer = new Timer(90, new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				i++;
				if(i==18){
					setKeyListener(Window.this.keyboard);
					add(getMap());
					remove(layer);
					validate();
					timer.stop();
				} 
			}
		});
		timer.start();
	}

	/**
	 * Graphical UI update
	 */
	public void update() {
		map.redraw();
		infoBar.redraw();
	}

	/**
	 * Display the MAP by removing the main menu panel and adding the map to the frame
	 */
	public void displayGame() {
		initMenuBar();
		getContentPane().add(getInfoBar(), BorderLayout.NORTH);
		getContentPane().add(getMap(), BorderLayout.CENTER);
		remove(getMainMenu());
	}

	/**
	 * Init the menu bar on the frame
	 */
	private void initMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.setFocusable(false);
		JMenu game = new JMenu("Game");
		JMenuItem quit = new JMenuItem("Quit game");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitGame();
				validate();
				repaint();
				pack();
			}
		});
		JMenuItem keys = new JMenuItem("Keys");
		keys.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeKeyListener();
				LayerUI<JComponent> ui = new KeysLayer<>();
				layer = new JLayer<>(getMap(), ui);
				getContentPane().add(layer);
				remove(getMap());
				keys.setEnabled(false);
				Window.this.addKeyListener(new KeyListener() {					
					@Override
					public void keyTyped(KeyEvent e) {}					
					@Override
					public void keyReleased(KeyEvent e) {
						getContentPane().add(getMap());
						remove(layer);
						setKeyListener(Window.this.keyboard);						
						removeKeyListener(this);
						keys.setEnabled(true);
					}					
					@Override
					public void keyPressed(KeyEvent e) {}
				});
				validate();
			}
		});
		game.add(keys);
		game.addSeparator();
		game.add(quit);
		bar.add(game);

		JMenuItem equipment = new JMenuItem("Equipment");
		equipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EquipmentUI(mainMenu.getGame().getPlayer());				
			}
		});
		bar.add(equipment);
		this.setJMenuBar(bar);		
	}

	/**
	 * Display the SHOP by removing the main menu panel and adding the SHOP to the frame
	 */
	public void displayShop(Inventory inventory) {
		getContentPane().add(new Shop(this, inventory));
		remove(getMainMenu());		
	}

	/**
	 * Display the main menu by removing the given Panel 
	 * @param panel the current JPanel to remove
	 */
	public void displayMainMenu(JPanel panel) {
		mainMenu = new MainMenu(this);
		getContentPane().add(mainMenu);
		remove(panel);
	}

	/**
	 * Display the main menu after a game over
	 */
	public void displayMainMenu() {
		mainMenu = new MainMenu(this);
		getContentPane().add(mainMenu);
		remove(layer);
		remove(getInfoBar());
	}

	/**
	 * Display the main menu after leave the game
	 */
	public void quitGame(){
		mainMenu = new MainMenu(this);
		getContentPane().add(mainMenu);
		if(layer != null)
			remove(layer);
		remove(getMap());
		remove(getInfoBar());
		this.setJMenuBar(null);
	}

	/**
	 * Adds a keyboard listener to the window
	 * @param keyboard
	 */
	public void setKeyListener(KeyListener keyboard) {
		this.keyboard = keyboard;
		this.addKeyListener(keyboard);
	}

	/**
	 * Remove the keyboard listener attach on the window
	 */
	public void removeKeyListener(){
		this.removeKeyListener(this.keyboard);
	}

	/**
	 * 
	 * @param gameObjects
	 */
	public void setGameObjects(List<GameObject> gameObjects) {
		map.setGameObjetcs(gameObjects);
		map.redraw();
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * @return the mainMenu
	 */
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu the mainMenu to set
	 */
	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	/**
	 * @param infoBar the infoBar to set
	 */
	public void setInfoBar(InfoBar infoBar) {
		this.infoBar = infoBar;
	}

	/**
	 * @return the infoBar
	 */
	public InfoBar getInfoBar() {
		return infoBar;
	}

}