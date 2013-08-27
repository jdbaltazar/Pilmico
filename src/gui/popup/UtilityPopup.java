package gui.popup;	

import gui.forms.util.FormField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import app.Credentials;
import core.security.SecurityTool;

public class UtilityPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679536523898885718L;
	private final int WIDTH = 155, HEIGHT = 55;
	private FormField field, username, password;

	private JNumericField numField;
	private Point p;
	private int utility;
	private JPanel panel;
	private SBButton close;
	private ErrorLabel utilityLabel;
	private JProgressBar progressBar;
	
	private boolean isClosed = false, verified = false;

	private String input = "";

	public UtilityPopup(Point p, int utility) {
		this.p = p;
		this.utility = utility;
		init();
		addComponents();
		
		addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		    	if(password!=null)
		    		password.requestFocus();
		    }
		}); 
	}

	private void init() {
		Values.mainFrame.dimScreen(true);

		
		setLayout(new BorderLayout());
		
		if(utility == Values.AUTO_BACKUP)
			setLocationRelativeTo(null);
		else
			setLocation(p);
		
		if (utility == Values.CATEGORY)
			setSize(WIDTH, HEIGHT);
		else if (utility == Values.PCOH) {
			setSize(215, HEIGHT);
		} else if (utility == Values.REMARKS) {
			if (Values.tableUtilPanel.getLabel().equals(Tables.INVENTORY_SHEET))
				setLocation(p.x - 305, p.y - 55);
			else
				setLocation(p.x, p.y - 55);

			setSize(305, HEIGHT);
		} else if (utility == Values.DATABASE) {
			setSize(165, 80);
		} else if (utility == Values.BACKUP_DIRECTORY) {
			setSize(305, HEIGHT);
		} else if (utility == Values.AUTO_BACKUP) {
			setSize(185, 45);
		} else {
			setLocation(p.x - 300, p.y);
			setSize(305, HEIGHT);
		}
		
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		
		setBackground(new Color(0, 0, 0, 0));
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new SimplePanel("");

		panel.setOpaque(false);

		close = new SBButton("dialog_close.png", "dialog_close.png", "Close", true);

		field = new FormField("", 10);
		numField = new JNumericField("Input previous cash on hand");
		password = new FormField("Root Password", 100, Color.white, Color.gray);

		utilityLabel = new ErrorLabel();
		utilityLabel.setHorizontalAlignment(JLabel.LEFT);

		if (utility == Values.CATEGORY) {
			field = new FormField("Add new category", 100, Color.white, Color.gray);
			utilityLabel.setForeground(Color.decode("#FF4500"));
			utilityLabel.setToolTip("*Required");

			field.setBounds(5, 20, 140, 20);
			close.setBounds(132, 2, 16, 16);
		}

		else if (utility == Values.PCOH) {
			numField.setMaxLength(10);

			utilityLabel.setForeground(Color.decode("#FF4500"));
			utilityLabel.setToolTip("");

			numField.setBounds(5, 20, 200, 20);
			close.setBounds(192, 2, 16, 16);
		}

		else if (utility == Values.REMARKS) {
			field = new FormField("Add some comments or remarks for this form", 500, Color.white, Color.gray);
			utilityLabel.setForeground(Color.decode("#FFD119"));
			utilityLabel.setWarningToolTip("*Optional");

			field.setBounds(5, 20, 290, 20);
			close.setBounds(282, 2, 16, 16);
		}
		
		else if (utility == Values.BACKUP_DIRECTORY) {
			field = new FormField("Set default directory for database backup", 500, Color.white, Color.gray);
			field.setEditable(false);
			utilityLabel.setForeground(Color.decode("#FF4500"));

			field.setBounds(5, 20, 290, 20);
			close.setBounds(282, 2, 16, 16);
		}
		
		else if(utility == Values.AUTO_BACKUP) {
			utilityLabel.setForeground(Color.GREEN.darker().darker());
			utilityLabel.setToolTip("Database Backup in progress. Please wait..");
			
			progressBar = new JProgressBar();
	        progressBar.setIndeterminate(true);
	        
	        utilityLabel.setBounds(7, 3, 170, 15);
	        progressBar.setBounds(5, 20, 170, 12);
	        
	        panel.add(progressBar);
		}
		
		else if (utility == Values.DATABASE) {
			username = new FormField("Root username", 100, Color.white, Color.gray);
			username.setText("root");

			password.setForeground(Color.white);
			password.setText("");

			utilityLabel.setForeground(Color.decode("#FF4500"));
			utilityLabel.setToolTip("*Required");
			
			progressBar = new JProgressBar();
	        progressBar.setIndeterminate(true);

			username.setBounds(5, 20, 150, 20);
			password.setBounds(5, 45, 150, 20);
			
			progressBar.setBounds(5, 20, 150, 12);

			close.setBounds(142, 2, 16, 16);
			
			progressBar.setVisible(false);

			panel.add(username);
			panel.add(password);
			panel.add(progressBar);
		}

		else {
			field = new FormField("Why invalidate this form?", 500, Color.white, Color.gray);
			utilityLabel.setForeground(Color.decode("#FF4500"));
			utilityLabel.setToolTip("*Required");

			field.setBounds(5, 20, 290, 20);
			close.setBounds(282, 2, 16, 16);
		}

		if(utility != Values.AUTO_BACKUP)
			utilityLabel.setBounds(7, 3, 120, 15);

		if (utility == Values.PCOH)
			panel.add(numField);
		else
			panel.add(field);

		panel.add(utilityLabel);

		numField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				input = numField.getText();
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});

		field.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (utility == Values.REMARKS) {
					input = field.getText();
					dispose();
				} else {
					if (!field.getText().equals("")) {
						input = field.getText();
						dispose();
					}
					else{
						if(utility == Values.BACKUP_DIRECTORY)
							utilityLabel.setToolTip("Directory empty");
					}
					
				}
			}
		});

		password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (username.getText().equals(SecurityTool.decryptString(Credentials.getInstance().getUsername()))
							&& password.getText().equals(SecurityTool.decryptString(Credentials.getInstance().getPassword()))) {
						verified = true;
						setVisible(false);
					}
					else
						utilityLabel.setToolTip("Try again");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setClosed(true);
				Values.mainFrame.dimScreen(false);
				dispose();
			}
		});
		
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setClosed(true);
					Values.mainFrame.dimScreen(false);
					dispose();
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setClosed(true);
					Values.mainFrame.dimScreen(false);
					dispose();
				}
			}
		});

		add(close);
		add(panel);
	}
	
	public void showProgressBar(){
		progressBar.setVisible(true);
		
		close.setVisible(false);
		username.setVisible(false);
		password.setVisible(false);
		
		utilityLabel.setForeground(Color.GREEN.darker().darker());
		utilityLabel.setText("Please wait..");
		
		setSize(165, 45);
		
		panel.revalidate();
		panel.updateUI();
		
		revalidate();
		
		setVisible(true);
	}

	public String getInput() {
		return input;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

}
