package gui;

import gui.forms.util.FormField;
import gui.panels.TablePanel;
import gui.popup.EditStoreInfoPopup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.SBButton;
import util.Values;
import util.soy.SoyPanel;

public class TopPanel extends SoyPanel {

	private BufferedImage image, image1;
	private ImageIcon icon;
	private JButton close, minimize;
	private JLabel totalSales, salesLabel;
	public static FormField searchField;
	
	public SBButton store_info;

	private int logoY = 10;

	public TopPanel() {
		super();

		init();
		addDashPanel();
		addMenuButtons();

		Values.topPanel = this;
	}

	private void init() {
		setPrefSize(Values.WIDTH, Values.HEIGHT / 6);
		setLayout(null);

		icon = new ImageIcon("images/search.png");

		close = new SBButton("exit.png", "exit2.png", "Close");
		minimize = new SBButton("min.png", "min2.png", "Minimize");
		store_info = new SBButton("header_2.png", "header_2.png", "");

		try {
			totalSales = new JLabel("P 24560.50" );
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		totalSales.setForeground(new Color(107,142,35));
		totalSales.setHorizontalAlignment(SwingConstants.LEFT);
		totalSales.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		salesLabel = new JLabel("Sales of the day:");
		salesLabel.setForeground(new Color(25,25,112));
		salesLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		/*try {
			image = (BufferedImage) ImageIO.read(new File("images/header.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	private void addDashPanel() {

		close.setBounds(800 - 32, 0, 32, 32);
		minimize.setBounds(800 - 64, 0, 32, 32);
		store_info.setBounds(30, logoY, 220, 80);
		
		store_info.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.mainFrame.dimScreen(true);
				new EditStoreInfoPopup().setVisible(true);
			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				/*int option = JOptionPane.showConfirmDialog(
					    null,
					    "Are you sure you want to exit?",
					    "Confirm",
					    JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION){*/
					Values.mainFrame.closeFrame();
//				}
			}
		});

		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.mainFrame.minimizeFrame();
			}
		});

		salesLabel.setBounds(550, 72, 200, 24);
		totalSales.setBounds(685, 70, 200, 25);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

//		gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0, getHeight(), new Color(5, 5, 5));
		
		gradient = new GradientPaint(0, 0, Values.gradient1, 0, getHeight(), Values.gradient2);
		g2.setPaint(gradient);
		g2.fillRect(0, 0, getWidth(), getHeight() - 1);

		//g2.setColor(new Color(50, 50, 50));
		g2.fillRect(0, getHeight() - 1, getWidth(), 1);

	//	drawLogo(g2);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

	private void drawLogo(Graphics2D g2) {
		g2.drawImage(image, 30, logoY, 220, 55, null);
		// g2.drawImage(image1, 495, 5, null);
		g2.setColor(Color.YELLOW.brighter());
		// 91,-3
	}

	public void addMenuButtons() {

		add(store_info);
		
		add(close);
		add(minimize);

		add(salesLabel);
		add(totalSales);

	}

	public void refreshStockCost() {
		try {
			totalSales.setText("P " );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalSales.updateUI();
	}

	public void showMenuButtons(boolean show) {

		salesLabel.setVisible(show);
		totalSales.setVisible(show);

		updateUI();
	}

	public int getLogoY() {
		return logoY;
	}

	public void setLogoY(int logoY) {
		this.logoY = logoY;
	}

}
