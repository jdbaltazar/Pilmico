package gui.popup;

import java.awt.Dimension;

import javax.swing.JPanel;

import util.SBButton;

public class DatabaseToolPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3477930157370335456L;
	private SBButton close, backup, recover;
	private JPanel panel;

	public DatabaseToolPanel() {

		init();
		addComponents();
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(null);
		setOpaque(false);

		// setBackground(Color.red);

		setPreferredSize(new Dimension(90, 30));

		close = new SBButton("dialog_close.png", "dialog_close.png", "Close");
		backup = new SBButton("backup.png", "backup2.png", "Backup Database");
		recover = new SBButton("recover.png", "recover2.png", "Database Recovery");

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		backup.setBounds(15, 5, 20, 20);
		recover.setBounds(55, 5, 20, 20);
		close.setBounds(72, 2, 16, 16);

		add(backup);
		add(recover);

	}

}
