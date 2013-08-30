package gui.popup;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.AppSettings;
import app.Credentials;
import app.DatabaseSettings;

import core.database.DatabaseTool;

import util.DateFormatter;
import util.SBButton;
import util.Utility;
import util.Values;

public class DatabaseToolPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3477930157370335456L;
	private SBButton close, backup, recover;
	private JButton backupDir;
	private UtilityPopup uP;

	public DatabaseToolPanel() {

		init();
		addComponents();
	}

	private void init() {
		setLayout(null);
		setOpaque(false);

		setPreferredSize(new Dimension(107, 30));

		close = new SBButton("dialog_close.png", "dialog_close.png", "Close");
		backup = new SBButton("backup.png", "backup2.png", "Backup Database");
		recover = new SBButton("recover.png", "recover2.png", "Database Recovery");
		backupDir = new JButton("Directory");

		backupDir.setToolTipText("Set default backup directory");
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		backup.setBounds(15, 5, 20, 20);
		recover.setBounds(55, 5, 20, 20);
		close.setBounds(72, 2, 16, 16);
		backupDir.setBounds(90, 7, 16, 16);

		backup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();

				uP = new UtilityPopup(b, Values.DATABASE);
				uP.setVisible(true);

				if (uP.isVerified() && !uP.isClosed()) {
					JFileChooser fc = new JFileChooser();

					try {
						if (DatabaseSettings.getInstance().isFilePathSet())
							fc.setCurrentDirectory(new File(DatabaseSettings.getInstance().getFilePath()));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					fc.setDialogTitle("Backup Database");
					fc.setSelectedFile(new File("Pilmico Backup " + DateFormatter.getInstance().getFormat(Utility.DMYFormat).format(new Date()) + "."
							+ AppSettings.APP_FILE_TYPE));
					FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter("Pilmico Files", AppSettings.APP_FILE_TYPE);
					fc.setFileFilter(sqlfilter);
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(Values.mainFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						// This is where a real application would save the file.
						try {
							// DatabaseTool.backup(uP.getUsername(), uP.getPassword(),
							// Credentials.getInstance().getDatabaseName(),
							// file.getCanonicalPath(), uP);

							String internalPath = DatabaseTool.INTERNAL_BACKUP_PATH + "/Pilmico Backup "
									+ DateFormatter.getInstance().getFormat(Utility.DMYFormat).format(new Date()) + "." + AppSettings.APP_FILE_TYPE;
							DatabaseTool.encryptedBackup(uP.getUsername(), uP.getPassword(), Credentials.getInstance().getDatabaseName(),
									file.getCanonicalPath(), internalPath, uP);

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
					try {
						if (DatabaseSettings.getInstance().isFilePathSet())
							fc.setCurrentDirectory(new File(DatabaseSettings.getInstance().getFilePath()));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					fc.setDialogTitle("Recover Database");
					FileNameExtensionFilter sqlfilter = new FileNameExtensionFilter("Pilmico Files", AppSettings.APP_FILE_TYPE);
					fc.setFileFilter(sqlfilter);
					fc.setAcceptAllFileFilterUsed(false);

					int returnVal = fc.showOpenDialog(Values.mainFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						try {

							DatabaseTool.getInstance().decryptedUpdate(uP.getUsername(), uP.getPassword(), Credentials.getInstance().getDatabaseName(),
									file.getCanonicalPath(), uP);

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

		backupDir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser();
				try {
					if (DatabaseSettings.getInstance().isFilePathSet()) {
						fc.setCurrentDirectory(new File(DatabaseSettings.getInstance().getFilePath()));
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				// fc.setCurrentDirectory(new java.io.File("."));

				fc.setDialogTitle("Set Default Backup Directory");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);

				// uP.setVisible(true);
				int returnVal = fc.showOpenDialog(Values.mainFrame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						DatabaseSettings.getInstance().setFilePath(file.getCanonicalPath().replace('\\', '/'));
						DatabaseSettings.getInstance().persist();

						new SuccessPopup("Directory Update").setVisible(true);
						Values.mainFrame.dimScreen(false);
						Values.topPanel.closeBalloonPanel();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});

		add(backup);
		add(recover);
		add(backupDir);

	}
}
