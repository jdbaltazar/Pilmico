package gui;

import gui.forms.util.FormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.DatabaseToolPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;
import util.SBButton;
import util.Values;
import util.soy.SoyPanel;
import app.DatabaseSettings;

import common.manager.Manager;

public class TopPanel extends SoyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7533379617683653266L;
	private JButton close, minimize, close2;
	private JLabel cashIN, cashINLabel, cashOUT, cashOUTLabel, trialLabel;
	public static FormField searchField;

	public SBButton store_info, tools;

	public SBButton getTools() {
		return tools;
	}

	private BalloonTip balloonTip;

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

		trialLabel = new ViewFormLabel("Trial period lasts until 31 Aug 2013 only.");
		trialLabel.setForeground(Color.decode("#E62E00"));

		close2 = new SBButton("dialog_close.png", "dialog_close.png", "Close", true);
		close = new SBButton("exit.png", "exit2.png", "Close", true);
		minimize = new SBButton("min.png", "min2.png", "Minimize", true);
		store_info = new SBButton("header_2.png", "header_2.png", "", true);
		tools = new SBButton("tools_3.png", "tools_3.png", "Backup / Recover", true);

		cashINLabel = new JLabel("Cash IN:");
		cashINLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashINLabel.setForeground(new Color(25, 25, 112));
		cashINLabel.setFont(new Font("Harabara", Font.PLAIN, 14));

		try {
			cashIN = new JLabel("P 24,560.50");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cashIN.setForeground(Color.decode("#009900"));
		// cashIN.setBorder(BorderFactory.createEtchedBorder());
		cashIN.setHorizontalAlignment(SwingConstants.CENTER);
		cashIN.setFont(new Font("Lucida Sans", Font.PLAIN, 12));

		cashOUTLabel = new JLabel("Cash OUT:");
		cashOUTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashOUTLabel.setForeground(new Color(25, 25, 112));
		cashOUTLabel.setFont(new Font("Harabara", Font.PLAIN, 14));

		try {
			cashOUT = new JLabel("P 9,655.50");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cashOUT.setForeground(Color.decode("#FFA280"));
		cashOUT.setHorizontalAlignment(SwingConstants.CENTER);
		cashOUT.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
	}

	private void addDashPanel() {

		close2.setBounds(72, 2, 16, 16);

		balloonTip = new BalloonTip(tools, new DatabaseToolPanel(), new RoundedBalloonStyle(7, 7, Color.decode("#F5FFFA"), Color.decode("#BDFF59")),// ,
																																																	// Color.decode("#B2CCCC")),
				BalloonTip.Orientation.LEFT_ABOVE, BalloonTip.AttachLocation.SOUTH, 7, 12, false);

		balloonTip.setPadding(0);
		balloonTip.setVisible(false);
		// balloonTip.setOpacity(0.95f);

		// balloonTip.getCloseButton().setSize(16, 16);

		close.setBounds(800 - 32, 0, 32, 32);
		minimize.setBounds(800 - 64, 0, 32, 32);
		store_info.setBounds(32, logoY, 220, 80);
		tools.setBounds(5, 5, 16, 16);

		// store_info.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// if (Manager.loggedInAccount != null && Manager.isAuthorized()) {
		// Values.mainFrame.dimScreen(true);
		// new EditStoreInfoPopup().setVisible(true);
		// }
		// }
		// });

		tools.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				balloonTip.setVisible(!balloonTip.isVisible());

				if (Manager.loggedInAccount == null) {
				}
			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				int option = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit?", "Confirm",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {

					Values.mainFrame.closeFrame();
				}
			}
		});

		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.mainFrame.minimizeFrame();
			}
		});

		// salesLabel.setBounds(550, 72, 200, 24);
		// totalSales.setBounds(685, 70, 200, 25);

		cashINLabel.setBounds(540, 63, 90, 15);
		cashIN.setBounds(635, 62, 160, 16);

		cashOUTLabel.setBounds(540, 80, 90, 15);// 72
		cashOUT.setBounds(635, 79, 160, 16);

		trialLabel.setBounds(490, 82, 300, 15);

	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0,
		// getHeight(), new Color(5, 5, 5));

		gradient = new GradientPaint(0, 0, Values.gradient1, 0, getHeight(), Values.gradient2);
		g2.setPaint(gradient);
		g2.fillRect(0, 0, getWidth(), getHeight() - 1);

		// g2.setColor(new Color(50, 50, 50));
		g2.fillRect(0, getHeight() - 1, getWidth(), 1);
		
		g2.setColor(Color.GRAY);
		g2.setFont(new Font("Lucida Sans", Font.ITALIC, 10));
		
		try {
			g2.drawString(""+DatabaseSettings.getInstance().getDbVersion(), 240, 55);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// drawLogo(g2);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

	public void addMenuButtons() {

		add(store_info);
		add(tools);

		add(close);
		add(minimize);

		// add(cashINLabel);
		// add(cashIN);

		add(trialLabel);

		// add(cashOUTLabel);
		// add(cashOUT);

	}

	public void refreshStockCost() {
		try {
			cashIN.setText("P ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cashIN.updateUI();
	}

	public void showMenuButtons(boolean show) {

		cashINLabel.setVisible(show);
		cashIN.setVisible(show);

		cashOUTLabel.setVisible(show);
		cashOUT.setVisible(show);

		updateUI();
	}

	public int getLogoY() {
		return logoY;
	}

	public void setLogoY(int logoY) {
		this.logoY = logoY;
	}

	public BalloonTip getBalloonTip() {
		return balloonTip;
	}

	public void closeBalloonPanel() {
		if (balloonTip != null)
			balloonTip.setVisible(false);
	}
}
