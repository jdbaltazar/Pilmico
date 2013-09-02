import gui.MainFrame;
import gui.panels.LoginPanel;
import gui.popup.DBPassword;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.Credentials;

import common.manager.Manager;
import core.database.DatabaseTool;
import core.persist.HibernateUtil;

public class Splash extends JFrame implements PropertyChangeListener {

	// git@github.com:jdbaltazar/Pilmico.git

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel background;
	private JProgressBar progressBar;
	private MainFrame mainFrame;
	private Task task;
	public static Splash frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.translaccel", "true");
		System.setProperty("sun.java2d.windows", "true");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					if (Credentials.getInstance().getPassword().equals(""))
						new DBPassword().setVisible(true);

					HibernateUtil.init();

					while (!HibernateUtil.isConnected()) {
						JOptionPane.showMessageDialog(null, "Cannot connect to database. Please input your MySQL Password again.", "System Message",
								JOptionPane.ERROR_MESSAGE);
						new DBPassword().setVisible(true);
						HibernateUtil.init();
					}

//					Manager.getInstance();
					frame = new Splash();

//					Manager.getInstance();
//					new LoginPanel();
//					MainFrame main = new MainFrame();
//					main.showFrame();

				} catch (Exception e) {
					// e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		setIconImage(new ImageIcon("images/frameicon.png").getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(355, 240);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);

		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setBounds(0, getHeight() - 7, getWidth(), 10);
		contentPane.add(progressBar);

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();

		background = new JLabel(new ImageIcon("images/pilmico_splash_4.png"));
		background.setBounds(0, 0, getWidth(), getHeight() - 7);
		contentPane.add(background);

		setContentPane(contentPane);
	}

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					DatabaseTool.executeUpdates();

					Manager.getInstance();
					new LoginPanel();
					mainFrame = new MainFrame();
					// System.out.println("haosdoadhaoos");
				}
			});

			// mainFrame.showFrame();
			Random random = new Random();
			int progress = 0;
			// Initialize progress property.
			setProgress(0);
			while (progress < 100) {
				// Sleep for up to one second.
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException ignore) {
				}
				// Make random progress.
				progress += random.nextInt(8);

				setProgress(Math.min(progress, 100));
				// System.out.println("progress: "+progress+
				// " getProgress: "+getProgress());
			}
			return null;
		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {
			setCursor(null); // turn off the wait cursor
			setVisible(false);
			dispose();

			mainFrame.showFrame();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
	}

}
