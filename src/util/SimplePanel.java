package util;

import gui.forms.util.IconLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SimplePanel extends JPanel {

	protected GradientPaint gradient;
	protected GradientPaint gradient1;
	private String label = "";
	
	public String amount;
	public IconLabel dateStatus;
	public boolean validDate;

	/** Stroke size. it is recommended to set it to 1 for better view */
	protected int strokeSize = 1;
	/** Color of shadow */
	protected Color shadowColor = Color.black;
	/** Sets if it drops shadow */
	protected boolean shady = true;
	/** Sets if it has an High Quality view */
	protected boolean highQuality = true;
	/** Double values for Horizzontal and Vertical radius of corner arcs */
	protected Dimension arcs = new Dimension(20, 20);
	/** Distance between border of shadow and border of opaque panel */
	protected int shadowGap = 5;
	/** The offset of shadow. */
	protected int shadowOffset = 4;
	/** The transparency value of shadow. ( 0 - 255) */
	protected int shadowAlpha = 150;

	public SimplePanel(String label) {
		this.label = label;
		setLayout(null);
		// setOpaque(false);
	}

	/*
	 * @Override public void paintComponent(Graphics g){ Graphics2D g2 =
	 * (Graphics2D) g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON);
	 * 
	 * gradient = new GradientPaint(0, 0, new Color(245,255,250),0, getHeight(),
	 * Color.white); g2.setPaint(gradient); g2.fill(g.getClipBounds());
	 * 
	 * g2.setColor(new Color(150, 150, 150)); g2.drawRoundRect(5, 6,
	 * getWidth()-11, getHeight() - 12, 20, 20);
	 * 
	 * g2.setColor(new Color(0,0,139)); g2.draw3DRect(14, 14, 10, 10, true);
	 * g2.fill3DRect(14, 14, 10, 10, true); g2.setFont(new Font("TimeBurner",
	 * Font.PLAIN, 14)); g2.drawString(label, 30, 24);
	 * 
	 * paintChildren(g2);
	 * 
	 * g2.dispose(); g.dispose(); }
	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int shadowGap = this.shadowGap;
		Color shadowColorA = new Color(shadowColor.getRed(),
				shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
		Graphics2D graphics = (Graphics2D) g;

		// Graphics2D g2 = (Graphics2D) g;
		// graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		// Sets antialiasing if HQ.
		if (highQuality) {
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}

		// Draws shadow borders if any.
		if (shady) {
			graphics.setColor(shadowColorA);
			graphics.fillRoundRect(shadowOffset,// X position
					shadowOffset,// Y position
					width - strokeSize - shadowOffset, // width
					height - strokeSize - shadowOffset, // height
					arcs.width, arcs.height);// arc Dimension
		} else {
			shadowGap = 1;
		}

		// Draws the rounded opaque panel with borders.
		gradient = new GradientPaint(0, 0, new Color(245, 255, 250), 0,
				getHeight(), Color.white);
		graphics.setPaint(gradient);
		// graphics.fill(g.getClipBounds());

		// graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
				arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap,
				arcs.width, arcs.height);

		// Sets strokes to default, is better.
		graphics.setStroke(new BasicStroke());

		if (!label.equals("")) {
			graphics.setColor(new Color(0, 0, 139));
			graphics.draw3DRect(14, 14, 10, 10, true);
			graphics.fill3DRect(14, 14, 10, 10, true);
			graphics.setFont(new Font("TimeBurner", Font.PLAIN, 14));
			graphics.drawString(label, 30, 24);
		}
	}

	public void setMaxSize(int width, int height) {
		setMaximumSize(new Dimension(width, height));
	}

	public void setMinSize(int width, int height) {
		setMinimumSize(new Dimension(width, height));
	}

	public void setPrefSize(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
}
