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

public class KeysLayer<T> extends LayerUI<JComponent> {
	private static final long serialVersionUID = 1L;

	private Image keys;

	/**
	 * Constructor
	 */
	public KeysLayer() {
		try {
			keys = ImageIO.read(new File("res/img/keys.png"));
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

		g2.setPaint(new GradientPaint(0, 0, Color.black, 0, h, Color.black));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2.dispose();
		g.drawImage(keys, (w - keys.getWidth(null))/2, (h - keys.getHeight(null))/2, null);
	}
}
