package gui.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;

import core.database.DatabaseTool;
import core.test.Test;

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

		backup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("backup!!");
				// DatabaseTool.backup("root", "123456", "pilmico",
				// "D:/backup.sql");
				// Test t = new Test();
				// t.tbBackup("pilmico", "root", "123456", "temp/backup.sql");
			}
		});

		recover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// try {
				// DatabaseTool.decryptAndUpdate("root", "123456", "pilmico",
				// "data/pilmico-create.sql");
				// } catch (SQLException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

			}
		});

		add(backup);
		add(recover);

	}
}
