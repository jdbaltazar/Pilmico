package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SBButton extends JButton implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SBButton(String icon, String icon2, String hover) {
		this.icon = icon;
		this.icon2 = icon2;
		this.hover = hover;
		init();
		addMouseListener(this);
		setContentAreaFilled(false);
	}

	private void init() {
		// TODO Auto-generated method stub
		image = new ImageIcon("images/"+icon);
		image2 = new ImageIcon("images/" + icon2);
		
		setFocusable(false);
		setBorderPainted(false);
		setRequestFocusEnabled(false);
		setToolTipText(hover);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		/*if (getModel().isPressed()) {
			g.setColor(g.getColor());
			g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
		}*/
		super.paintComponent(g);

		if (mouseIn)
			setIcon(image2);
		else
			setIcon(image);

		/*g2.setColor(Color.white);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(1.2f));
		g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 2),
				(getHeight() - 4), 12, 8));*/
		g2.setStroke(new BasicStroke(1.5f));

		g2.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouseIn = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mouseIn = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	boolean mouseIn = false;
	private boolean mouseClicked = false;
	private String icon, icon2;
	private Icon image, image2;
	private String hover="";
}
