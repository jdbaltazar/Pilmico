package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class MenuButton extends JButton implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MenuButton(String icon, String icon2, String icon3, String hover) {
		this.icon = icon;
		this.icon2 = icon2;
		this.icon3 = icon3;
		this.hover = hover;
		init();
		addMouseListener(this);
		setContentAreaFilled(false);
		setOpaque(true);
	}

	private void init() {
		// TODO Auto-generated method stub
		image = new ImageIcon("images/"+icon);
		image2 = new ImageIcon("images/" + icon2);
		image3 = new ImageIcon("images/" + icon3);
		
		//setFocusable(false);
		setBorderPainted(false);
		//setRequestFocusEnabled(false);
		setFocusPainted(false);
		//setSelectedIcon(image3);
		setToolTipText(hover);
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				//setIcon(image);
				mouseClicked = false;
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				//setIcon(image3);
				mouseClicked = true;
			}
		});
		/*
		addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {  
			setIcon(getIcon()==image? image2 : image);  
			}  
			});*/ 
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		/*if (getModel().isPressed()) {
			g.setColor(g.getColor());
			g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
		}*/
		super.paintComponent(g);

		if (mouseIn && !mouseClicked)
			setIcon(image2);
		
		/*if(mouseClicked)
			setIcon(image2);*/
		
		if(!mouseIn && !mouseClicked)
			setIcon(image);

		/*g2.setColor(Color.white);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(1.2f));
		g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 2),
				(getHeight() - 4), 12, 8));*/
		//g2.setStroke(new BasicStroke(1.5f));

		g2.dispose();
		g.dispose();
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
	private String icon, icon2, icon3;
	private Icon image, image2, image3;
	private String hover="";
}

