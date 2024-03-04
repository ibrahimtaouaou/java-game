package view.layers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

public class GameOverLayer<T> extends LayerUI<JComponent> {
	private static final long serialVersionUID = 1L;

	private float a;
	private Image gameOver;

	/**
	 * Constructor
	 */
	public GameOverLayer() {
		try {
			gameOver = ImageIO.read(new File("res/img/gameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		Graphics2D g2 = (Graphics2D) g.create();

		int w = c.getWidth();
		int h = c.getHeight();

		g2.setPaint(new GradientPaint(0, 0, Color.black, 0, h, Color.red));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
		if(a < .7f)	a += .05f;
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2.dispose();
		if(a >= .7f)
			g.drawImage(gameOver, (w - gameOver.getWidth(null))/2, (h - gameOver.getHeight(null))/2, null);
	}
}
