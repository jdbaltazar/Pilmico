package gui.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.manager.Manager;

import core.database.DatabaseTool;
import core.test.FileChooserDemo;
import core.test.Test;

import util.SBButton;
import util.Values;

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

				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Backup Database");
				fc.setSelectedFile(new File("backup.sql"));
				fc.addChoosableFileFilter(new FileNameExtensionFilter("MySQL Files", "sql"));
				int returnVal = fc.showSaveDialog(Values.mainFrame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// This is where a real application would save the file.
					try {
						// DatabaseTool.backup("root", "123456", "pilmico",
						// file.getCanonicalPath());

						// Test t = new Test();
						// t.tbBackup("pilmico", "root", "123456",
						// file.getCanonicalPath());

						DatabaseTool.backup("root", "123456", "pilmico", file.getCanonicalPath());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		recover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Recover Database");
				// fc.setSelectedFile(new File("backup.sql"));
				fc.addChoosableFileFilter(new FileNameExtensionFilter("MySQL Files", "sql"));
				int returnVal = fc.showOpenDialog(Values.mainFrame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// This is where a real application would save the file.

					try {
						DatabaseTool.decryptAndUpdate("root", "123456", "pilmico", file.getCanonicalPath());
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		add(backup);
		add(recover);

	}
}
