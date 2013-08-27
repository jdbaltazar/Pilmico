package util;

import gui.forms.util.RemarksLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditFormPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GradientPaint gradient;
	protected GradientPaint gradient1;
	
	public RemarksLabel remarks;
	
	public JLabel status;

	private String label = "";

	public EditFormPanel(String label) {
		this.label = label;
		setLayout(null);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		gradient = new GradientPaint(0, 0, Color.white, 0, getHeight(), Color.decode("#E0F0FF"));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());
		// Color ppColor = new Color(0, 96, 96, 30); // r,g,b,alpha
		// g2.setColor(Color.decode("#CCE6FF"));
		// g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(new Color(150, 150, 150));
		g2.drawRoundRect(2, 4, getWidth() - 5, getHeight() - 8, 20, 20);

		/*
		 * g2.setColor(new Color(0,0,139)); g2.draw3DRect(14, 14, 10, 10, true);
		 * g2.fill3DRect(14, 14, 10, 10, true); g2.drawString("View/Edit Item",
		 * 30, 24);
		 */

		g2.setColor(new Color(0, 0, 139));
		g2.draw3DRect(14, 380, 10, 10, true);
		g2.fill3DRect(14, 380, 10, 10, true);
		g2.setFont(new Font("TimeBurner", Font.PLAIN, 14));
		g2.drawString(label, 30, 390);

		paintChildren(g2);

		g2.dispose();
		g.dispose();
	}
}
