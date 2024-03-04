package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Keyboard;
import model.Game;
import model.Inventory;
import view.helper.ImageMgr;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private ImageIcon menuTitle, start, newGame, shop, help, exit, warrior, wizard;
	private Image bg;
	private Window window;
	private Inventory inventory;
	private Game game;

	/**
	 * Constructor
	 * @param window
	 */
	public MainMenu (Window window){
		this.window = window;
		initImageIcon();

		setPreferredSize(new Dimension(600, 700));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = initGridBag();

		List<JButton> buttons = new ArrayList<>(3);
		if(findSavedFile())
			buttons.add(initButton(start, choiceStartAction));
		buttons.add(initButton(newGame, choiceNewAction));
		buttons.add(initButton(shop, shopAction));
		buttons.add(initButton(help, helpAction));
		buttons.add(initButton(exit, exitAction));

		JPanel buttonPanel = initButtonPanel(gbc, buttons);

		add(new JLabel(menuTitle), gbc);
		add(buttonPanel, gbc);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this);
	}

	/**
	 * init the specificities of the gridbaglayout
	 * @return the gridbagconstraints
	 */
	private GridBagConstraints initGridBag() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 45;
		return gbc;
	}

	/**
	 * look for the saved file
	 * @return true if there is a saved file
	 */
	private boolean findSavedFile() {
		String filename = "saved";
		try(
				InputStream file = new FileInputStream(filename);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream (buffer);
				){
			inventory = (Inventory)input.readObject();//déserialization des coins si le fichier es trouvé
		} catch (IOException | ClassNotFoundException e) {
			inventory = new Inventory();
			return false;//saved file not found 
		}
		return true;
	}

	/**
	 * create the buttons
	 */
	private JButton initButton(ImageIcon imageIcon, ActionListener action) {
		JButton button = new JButton(imageIcon);
		button.setSize(100, 5);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(action);
		return button;
	}

	/**
	 * create the panel with the buttons
	 * @param gbc the specificities of the gridbaglayout
	 * @param buttons
	 * @return
	 */
	private JPanel initButtonPanel(GridBagConstraints gbc, List<JButton> buttons) {
		int spaces = (buttons.size() == 5) ? 5 : 15;
		JPanel buttonPanel = new JPanel(new GridLayout(buttons.size(),1,0,spaces));
		buttonPanel.setOpaque(false);

		for(JButton button : buttons){
			buttonPanel.add(button, gbc);
		}
		return buttonPanel;
	}

	/**
	 * link the images
	 */
	private void initImageIcon() {
		bg = ImageMgr.getMain_bg();
		menuTitle =  ImageMgr.getMenuTitle();
		start =  ImageMgr.getStart();
		newGame =  ImageMgr.getNewGame();
		shop =  ImageMgr.getShop();
		help =  ImageMgr.getHelp();
		exit = ImageMgr.getExit();
		warrior = ImageMgr.getWarriorC();
		wizard = ImageMgr.getWizardC();
	}

	ActionListener choiceStartAction = new ActionListener() {		 
		@Override
		public void actionPerformed(ActionEvent e) { 
			Integer[] tabS = {18,26};
			JLabel sizeLabel = new JLabel("  Map size   ");
			sizeLabel.setFont(new Font("Serif", Font.BOLD, 20));
			JComboBox<Integer> sizeMap = new JComboBox<Integer>(tabS);
			JDialog dialog = new JDialog(window, true);
			dialog.setTitle("Make your choices !");
			dialog.setSize(280, 240);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setLayout(new BorderLayout());
			JPanel combo = new JPanel();
			combo.setLayout(new BoxLayout(combo, BoxLayout.X_AXIS));
			JPanel button = new JPanel();
			button.setLayout(new BoxLayout(button, BoxLayout.X_AXIS));
			button.add(initButton(warrior, startAction("Warrior", sizeMap, dialog)));
			button.add(initButton(wizard, startAction("Wizard", sizeMap, dialog)));
			combo.add(sizeLabel);
			combo.add(sizeMap);
			dialog.add(combo, BorderLayout.NORTH);
			dialog.add(button, BorderLayout.CENTER);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	};
	
	ActionListener choiceNewAction = new ActionListener() {		 
		@Override
		public void actionPerformed(ActionEvent e) { 
			Integer[] tabS = {18,26};
			JLabel sizeLabel = new JLabel("  Map size   ");
			sizeLabel.setFont(new Font("Serif", Font.BOLD, 20));
			JComboBox<Integer> sizeMap = new JComboBox<Integer>(tabS);
			JDialog dialog = new JDialog(window, true);
			dialog.setTitle("Make your choices !");
			dialog.setSize(280, 240);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setLayout(new BorderLayout());
			JPanel combo = new JPanel();
			combo.setLayout(new BoxLayout(combo, BoxLayout.X_AXIS));
			JPanel button = new JPanel();
			button.setLayout(new BoxLayout(button, BoxLayout.X_AXIS));
			button.add(initButton(warrior, newGameAction("Warrior", sizeMap, dialog)));
			button.add(initButton(wizard, newGameAction("Wizard", sizeMap, dialog)));
			combo.add(sizeLabel);
			combo.add(sizeMap);
			dialog.add(combo, BorderLayout.NORTH);
			dialog.add(button, BorderLayout.CENTER);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	};

	public ActionListener startAction(String playerType, JComboBox<Integer> sizeMap, JDialog dialog){
		ActionListener startAction = new ActionListener() {		 
			@Override
			public void actionPerformed(ActionEvent e) { 
				dialog.dispose();
				game = new Game(window, playerType, (Integer)sizeMap.getSelectedItem(), inventory);
				Keyboard keyboard = new Keyboard(game);
				window.setKeyListener(keyboard);
				window.displayGame();

				window.validate();
				window.repaint();
				window.pack();
				window.setLocationRelativeTo(null);
			}
		};
		return startAction;
	}

	public ActionListener newGameAction(String playerType, JComboBox<Integer> sizeMap, JDialog dialog){
		ActionListener newGameAction = new ActionListener() {		 
			@Override
			public void actionPerformed(ActionEvent e) { 
				dialog.dispose();
				game = new Game(window, playerType, (Integer)sizeMap.getSelectedItem(), new Inventory());
				Keyboard keyboard = new Keyboard(game);
				window.setKeyListener(keyboard);
				window.displayGame();

				window.validate();
				window.repaint();
				window.pack();
				window.setLocationRelativeTo(null);
			}
		};
		return newGameAction;
	}

	ActionListener shopAction = new ActionListener() {		 
		@Override
		public void actionPerformed(ActionEvent e) { 
			window.displayShop(inventory);

			window.validate();
			window.repaint();
			window.pack();
			window.setLocationRelativeTo(null);
		}
	};

	ActionListener helpAction = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) { 
			JDialog dialog = new JDialog();
			dialog.setTitle("HELP");
			dialog.setSize(700, 500);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			dialog.setLayout(new BorderLayout());
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.add(new JLabel("    "), BorderLayout.WEST);
			dialog.add(textInit(), BorderLayout.CENTER);
			dialog.add(new JLabel("    "), BorderLayout.EAST);
		}
	};

	ActionListener exitAction = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) { 
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		}
	};
	
	/**
	 * create the text of the help button
	 * @return
	 */
	public JLabel textInit(){
		String text = "<html>Dungeon Game est un jeu où le joueur doit grimper aux plus hauts étages possibles. "
				+ "Pour t'aider dans ton combat, voici quelques règles et indices.<br><br>"
				+ "Utilise les touches directionnelles afin de te déplacer et la touche espace pour interragir avec l'environnement, c'est à dire "
				+ "saigner du monstre ou ouvrir des coffres. Cependant attention ! Les ennemis sont inoffensifs mais "
				+ "si tu t'approches trop prêt ils t'attaqueront !<br><br>"
				+ "- Pour les détails sur les différentes touches de jeu, va dans \"Game\" puis \"Keys\" dans la fenêtre du jeu.<br>"
				+ "- Pour passer à l'étage supérieur, il te faut tuer tous les ennemis avant de passer par la porte.<br>"
				+ "- Utiliser un arc te donnera l'avantage de massacrer tes ennemis à distance sans être touché. Mais attention, chaque flèche "
				+ "brûle 200$ !!<br>"
				+ "- En tuant le boss aux levels 5, 10, 15 etc, tu auras une chance de trouver un portail en plus de trouver des items plus forts "
				+ "que ceux rencontrés. Marche sur le portail pour gagner 3 niveaux !<br>"
				+ "- Les ennemis gagnent en puissance et en endurance au fur et à mesure de ton avancement. Tâche de t'équiper avec le meilleur "
				+ "de tes butins !<br>"
				+ "- Au début du jeu, tu as par défault une épée (resp. un bâton) et un plastron si tu choisis un guerrier (resp. un sorcier). En cours "
				+ "de jeu, tu peux ramasser des objets trouvés sur les mobs ou les coffres, et ainsi devenir plus fort.<br>"
				+ "- Tu peux devenir encore plus fort si tu économises assez d'argent et que tu achètes les items du shop, ils sont monstrueux !!!<br>"
				+ "- Pour comparer un item ramassé avec un item porté, laisse ta souris sur l'item de ton inventaire, tu verras alors s'afficher "
				+ "la différence entre l'item de l'inventaire et celui de ton équipement.<br>"
				+ "- Si tu choisis un guerrier (resp. un sorcier), tu pourras utiliser toutes les 20 secondes un sort te permettant d'augmenter "
				+ "ta défense (resp. ton attaque) pendant 5 secondes en appuyant sur X.<br>"
				+ "- Le boss possède une compétence le soignant d'un certain montant lorsque sa vie arrive à 50%.";
		return new JLabel(text);
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	
}
