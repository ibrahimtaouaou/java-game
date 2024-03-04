package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Direction;
import model.Game;

public class Keyboard implements KeyListener {

	private Game game;

	public Keyboard(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key){
		case KeyEvent.VK_RIGHT:
			game.getPlayer().setDirection(Direction.RIGHT);
			game.movePlayer(1, 0);
			break;
		case KeyEvent.VK_LEFT:
			game.getPlayer().setDirection(Direction.LEFT);
			game.movePlayer(-1, 0);
			break;
		case KeyEvent.VK_DOWN:
			game.getPlayer().setDirection(Direction.DOWN);
			game.movePlayer(0, 1);
			break;
		case KeyEvent.VK_UP:
			game.getPlayer().setDirection(Direction.UP);
			game.movePlayer(0, -1);
			break;	
		case KeyEvent.VK_SPACE:
			if(!game.interract())
				game.attackRange();
			break;
		case KeyEvent.VK_X:
			game.activateSkill();
			break;
		case KeyEvent.VK_C:
			game.collect();
			break;
		default : return;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}