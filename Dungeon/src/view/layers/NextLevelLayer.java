package view.layers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

public class NextLevelLayer<T> extends LayerUI<JComponent> {
	private static final long serialVersionUID = 1L;

	private float a = .9f;

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setPaint(new GradientPaint(0, 0, Color.black, 0, c.getHeight(), Color.black));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
		if(a > .1f) a -= .05f;
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2.dispose();
	}
}
