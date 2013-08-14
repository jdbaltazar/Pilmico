package gui.panels;

import gui.forms.util.FormLabel;
import gui.popup.ProductOnDisplayPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import common.entity.log.Log;
import common.entity.log.LogType;
import common.entity.profile.Account;
import common.manager.Manager;

import util.ErrorLabel;
import util.Values;
import util.soy.SoyButton;
import util.soy.SoyField;
import util.soy.SoyPanel;

public class LoginPanel extends SoyPanel implements Runnable, MouseListener {

	/**
	 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private SoyField userName;
	private JPasswordField password;
	private SoyButton login;
	private Thread thread;

	private ErrorLabel error;

	private FormLabel usernameLabel, passwordLabel;

	public LoginPanel() {
		init();
		addComponents();
		thread.start();
	}

	private void init() {
		thread = new Thread(this);

		error = new ErrorLabel();

		setLayout(null);
		try {
			image = (BufferedImage) ImageIO.read(new File("slideshow/1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addComponents() {
		usernameLabel = new FormLabel("Username");
		usernameLabel.setSize(20);
		usernameLabel.setBounds(600, 110, 100, 30);
		usernameLabel.setForeground(new Color(32, 178, 170));

		userName = new SoyField(1);
		userName.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		userName.requestFocus();
		userName.setBounds(605, 140, 150, 25);

		passwordLabel = new FormLabel("Password");
		passwordLabel.setSize(20);
		passwordLabel.setBounds(600, 185, 150, 30);
		passwordLabel.setForeground(new Color(32, 178, 170));

		password = new JPasswordField();
		password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				validateAccount();
			}
		});

		password.setBounds(605, 215, 150, 25);
		// password.setBorder(BorderFactory.createEmptyBorder());

		login = new SoyButton("Log in");
		login.addMouseListener(this);
		login.setBounds(680, 280, 70, 30);

		error.setBounds(585, 420, 200, 22);

		add(usernameLabel);
		add(passwordLabel);

		add(userName);
		add(password);
		add(login);

		add(error);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		// g2.setRenderingHint(RenderingHints.KEY_RENDERING,
		// RenderingHints.VALUE_RENDER_QUALITY);
		// //g2.setColor(Color.WHITE);
		// g2.fill(getBounds());

		// gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0,
		// getHeight(), new Color(5, 5, 5));

		gradient = new GradientPaint(0, 0, Values.gradient1, 0, getHeight(), Values.gradient2);
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		g2.drawImage(image, 0, 1, 550, getHeight(), null);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

	@Override
	public void run() {
		int i = 1;

		while (true) {

			try {
				image = (BufferedImage) ImageIO.read(new File("slideshow/" + i + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			repaint();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
			}

			if (i == 6)
				i = 1;
			else
				i++;
		}
	}

	private void validateAccount() {
		String errorComment;

		try {
			errorComment = (Manager.getInstance().login(userName.getText(), password.getPassword()));

			if (!errorComment.equals("true")) {
				if (errorComment.equals("accNF")) {
					error.setToolTip("Account Not Found ");
				} else if (errorComment.equals("passInc")) {
					error.setToolTip("Password Incorrect ");
				} else {
					error.setToolTip("You are not authorized! ");
				}

			} else {

				String desc = Manager.loggedInAccount.getDesignationPlusFirstPlusLastName() + " logged in";
				Log log = new Log(new LogType(LogType.SYSTEM), desc);
				Manager.logManager.addLog(log);

				Account acc = Manager.getInstance().getLoggedInAccount();
				Values.footerPanel.setUserName(acc.getUsername() + "!");
				Values.topPanel.getBalloonTip().setVisible(false);
				Values.topPanel.getTools().setVisible(false);
				Values.topPanel.showMenuButtons(true);
				Values.footerPanel.showMenuButtons(true);
				Values.centerPanel.changeTable(Values.HOME);

				// Manager.logManager.addLog(new
				// Log(Manager.logManager.getLogType(LogType.SYSTEM),
				// acc.getUsername() + ": " + acc.getFirstName() + " "
				// + acc.getLastName() + " logged in"));

				// Values.topPanel.showMenuButtons(true);
				// Values.footerPanel.showMenuButtons(true);
				// Values.centerPanel.changeTable(Values.HOME);

				// new ProductOnDisplayPopup().setVisible(true);
			}

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Values.topPanel.showMenuButtons(true);
		// Values.footerPanel.showMenuButtons(true);
		// Values.centerPanel.changeTable(Values.HOME);
		//
		// new ProductOnDisplayPopup().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		validateAccount();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
