package gui.popup;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
import core.persist.HibernateUtil;
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
	private UtilityPopup uP;

	public DatabaseToolPanel() {

		init();
		addComponents();
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(null);
		setOpaque(false);

		setPreferredSize(new Dimension(90, 30));

		close = new SBButton("dialog_close.png", "dialog_close.png", "Close");
		backup = new SBButton("backup.png", "backup2.png", "Backup Database");
		recover = new SBButton("recover.png", "recover2.png",
				"Database Recovery");

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		backup.setBounds(15, 5, 20, 20);
		recover.setBounds(55, 5, 20, 20);
		close.setBounds(72, 2, 16, 16);

		backup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();

				uP = new UtilityPopup(b, Values.DATABASE);
				uP.setVisible(true);

				if (uP.isVerified() && !uP.isClosed()) {
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Backup Database");
					fc.setSelectedFile(new File("backup.sql"));
					FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter(
							"MySQL Files", "sql");
					fc.setFileFilter(sqlfilter);
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(Values.mainFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						// This is where a real application would save the file.
						try {
							DatabaseTool.getInstance().backup(uP.getUsername(),
									uP.getPassword(), "pilmico",
									file.getCanonicalPath(), uP);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						Values.mainFrame.dimScreen(false);
						Values.topPanel.closeBalloonPanel();
					}
				}
			}
		});

		recover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();

				uP = new UtilityPopup(b, Values.DATABASE);
				uP.setVisible(true);

				if (uP.isVerified() && !uP.isClosed()) {

					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Recover Database");

					// DO NOT DELETE THESE COMMENTS

					// fc.setSelectedFile(new File("backup.sql"));
					// fc.addChoosableFileFilter(new FileNameExtensionFilter(
					// "MySQL Files", "sql"));

					FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter(
							"MySQL Files", "sql");
					fc.setFileFilter(sqlfilter);
					fc.setAcceptAllFileFilterUsed(false);

					int returnVal = fc.showOpenDialog(Values.mainFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						// This is where a real application would save the file.

						try {

							DatabaseTool.getInstance().decryptAndUpdate(
									uP.getUsername(), uP.getPassword(),
									"pilmico", file.getCanonicalPath(), uP);

						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						Values.mainFrame.dimScreen(false);
						Values.topPanel.closeBalloonPanel();
					}

				}

			}
		});

		add(backup);
		add(recover);

	}
}
