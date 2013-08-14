package gui;

import gui.popup.AddNotePopup;
import gui.popup.EditStoreInfoPopup;
import gui.popup.NotesPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import common.manager.Manager;

import util.SBButton;
import util.TimeLabel;
import util.Values;
import util.soy.SoyPanel;

public class FooterPanel extends SoyPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton log;
	private JLabel welcome, acct;
	private SBButton info, addNote, logout;
	private JLabel newLabel, notesLabel, copyright, dateTime;

	public FooterPanel() {
		super();
		init();
		addComponents();
		addButtons();

		Values.footerPanel = this;
	}

	private void init() {
		setPrefSize(Values.WIDTH, 25);
		setLayout(null);

		welcome = new JLabel("Welcome, ");
		// welcome.setBounds(10, 80, 60, 20);
		welcome.setBounds(40, 3, 60, 20);
		welcome.setForeground(new Color(128, 0, 128));
		welcome.setFont(new Font("TimeBurner", Font.ITALIC, 12));

		acct = new JLabel("dummy_acct" + "!");
		// acct.setBounds(75, 80, 150, 20);
		acct.setBounds(100, 3, 150, 20);
		acct.setForeground(new Color(128, 0, 128));
		acct.setFont(new Font("TimeBurner", Font.ITALIC, 12));

		copyright = new JLabel("© All Rights Reserved 2013");
		copyright.setFont(new Font("Calibri", 0, 14));
		copyright.setForeground(new Color(0, 0, 77));

		newLabel = new JLabel(":: NEW ::");
		newLabel.setForeground(Color.GREEN);

		try {
			notesLabel = new JLabel("View Notes (" + "0)");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		notesLabel.setForeground(new Color(0, 0, 100));
		notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notesLabel.addMouseListener(new MouseAdapter() {

			Font original;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void mouseEntered(MouseEvent e) {
				original = e.getComponent().getFont();
				Map attributes = original.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				e.getComponent().setFont(original.deriveFont(attributes));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setFont(original);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Values.mainFrame.dimScreen(true);
				new NotesPopup().setVisible(true);
			}
		});

		updateNotes();

		info = new SBButton("storeinfo1.png", "storeinfo2.png", "Store Info");
		addNote = new SBButton("add_note.png", "add_note.png", "Add Note");

		log = new SBButton("logs.png", "logs2.png", "System Logs");
		logout = new SBButton("logout_small.png", "logout_small.png", "Logout");

		info.addActionListener(this);
		addNote.addActionListener(this);
		log.addActionListener(this);
		logout.addActionListener(this);
	}

	private void addComponents() {
		// logout.setBounds(730, 8, 25, 25);
		log.setBounds(680, 8, 25, 25);

		// info.setBounds(10, 4, 16, 16);
		logout.setBounds(10, 4, 16, 16);
		newLabel.setBounds(370, 4, 50, 16);
		notesLabel.setBounds(665, 4, 100, 16);

		notesLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 11));

		addNote.setBounds(770, 4, 16, 16);

		dateTime = new TimeLabel();
		dateTime.setBounds(570, 6, 202, 15);

		copyright.setBounds(10, 6, 250, 15);

		add(dateTime);
		add(copyright);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0,
		// getHeight(), new Color(5, 5, 5));

		gradient = new GradientPaint(0, 0, Values.gradient1, 0, getHeight(), Values.gradient2);
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		// g2.drawImage(status, 20, 8, 24, 24, null);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

	public void addButtons() {
		// add(log);
		add(logout);
		// add(info);
		// add(newLabel);
		add(notesLabel);

		add(addNote);

		add(welcome);
		add(acct);
	}

	public void showMenuButtons(boolean show) {

		logout.setVisible(show);
		newLabel.setVisible(show);
		notesLabel.setVisible(show);

		addNote.setVisible(show);

		welcome.setVisible(show);
		acct.setVisible(show);

		// TimeLabel.getComponent().setVisible(!show);
		if (!show)
			dateTime.setBounds(580, 6, 202, 15);
		else
			dateTime.setBounds(260, 6, 202, 15);

		copyright.setVisible(!show);

		// updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(logout)) {
			showMenuButtons(false);
			Values.topPanel.showMenuButtons(false);
			Values.topPanel.getTools().setVisible(true);
			Values.centerPanel.logout();
			try {
				Manager.getInstance().logout();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(info)) {
			Values.mainFrame.dimScreen(true);
			new EditStoreInfoPopup().setVisible(true);
			try {
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(addNote)) {
			Values.mainFrame.dimScreen(true);
			new AddNotePopup().setVisible(true);
		} else {
			System.out.println("logs");
		}

	}

	public void setUserName(String username) {
		acct.setText(username);
	}

	public void updateNotes() {

		int size = 0;
		try {
			size = Manager.noteManager.getNotes().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		notesLabel.setText("View Notes (" + size + ")");
	}

	public void setNotesLabel(String notes, String tags) {
		notesLabel.setText(notes);
		notesLabel.setToolTipText(tags);
	}

}
