package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SimplePanel extends JPanel{

	protected GradientPaint gradient;
	protected GradientPaint gradient1;
	private String label="";

	public SimplePanel(String label){
		this.label = label;
		setLayout(null);
		//setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		gradient = new GradientPaint(0, 0, new Color(245,255,250),0, getHeight(), Color.white);
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());
		
		g2.setColor(new Color(150, 150, 150));
		g2.drawRoundRect(5, 6, getWidth()-11, getHeight() - 12, 20, 20);

		g2.setColor(new Color(0,0,139));
		g2.draw3DRect(14, 14, 10, 10, true);
		g2.fill3DRect(14, 14, 10, 10, true);
		g2.setFont(new Font("TimeBurner", Font.PLAIN, 14));
		g2.drawString(label, 30, 24);
		
		paintChildren(g2);

		g2.dispose();
		g.dispose();
	}

	public void setMaxSize(int width, int height){
		setMaximumSize(new Dimension(width,height));
	}

	public void setMinSize(int width, int height){
		setMinimumSize(new Dimension(width,height));
	}

	public void setPrefSize(int width, int height){
		setPreferredSize(new Dimension(width,height));
	}
}
