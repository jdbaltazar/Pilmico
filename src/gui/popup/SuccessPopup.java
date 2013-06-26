package gui.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Values;

public class SuccessPopup extends JDialog implements Runnable{
	
	private String action;
	private int WIDTH = 278, HEIGHT = 100;
	private JPanel panel;
	private JLabel label;
	private Thread thread;
	private int mode = 0;
	

	public SuccessPopup(String action) {
		
		this.action = action;
		
		init();
		addComponents();
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	public SuccessPopup(String action, int mode) {
		
		this.action = action;
		this.mode = mode;
		
		init();
		addComponents();
		
		thread = new Thread(this);
		thread.start();
		
	}

	private void init() {
		
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(0,0,0,0));
		
		Values.mainFrame.dimScreen(true);
		
	}

	private void addComponents() {
		
		label = new JLabel(action+" Successful");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setForeground(Color.white);
		
		add(label, BorderLayout.CENTER);
	}
	
	@Override
	public void run() {

		try {
			Thread.sleep(1800);
			dispose();
			Values.mainFrame.dimScreen(false);
			
			if(Values.addEntryPanel.isCloseSelected() && action.equals("Add") && mode == 0)
				Values.addEntryPanel.startAnimation();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
