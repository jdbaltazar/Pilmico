package gui.popup;

import gui.forms.util.FormField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JDialog;
import javax.swing.JPasswordField;

import core.security.SecurityTool;

import app.Credentials;

import util.soy.SoyButton;
import util.soy.SoyPanel;

public class DBPassword  extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 240, HEIGHT = 100;
	private SoyPanel panel;
	private JPasswordField dbPassword;
	
	public DBPassword() {
		
		init();
		addComponents();
		
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
		
	}

	private void addComponents() {
		
		panel = new SoyPanel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);

				gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0,
						getHeight(), new Color(105, 135, 155));

				g2.setPaint(gradient);
				g2.fillRect(0, 0, getWidth(), getHeight() - 1);

				g2.setColor(new Color(50, 50, 50));
				g2.fillRect(0, getHeight() - 1, getWidth(), 1);

				g2.setFont(new Font("Harabara", Font.BOLD, 16));
				
				g2.setColor(Color.white);
				g2.drawString("Input your MySQL Password", 15, 22);
				g2.drawLine(0, 30, getWidth(), 30);
							
				paintChildren(g2);

				g2.dispose();
				g.dispose();
			}
		};
		
		panel.setLayout(null);
		
		dbPassword = new JPasswordField();
		dbPassword.setBounds(20, 50, 200, 25);
		
		dbPassword.setText("");
		
		dbPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				//PUT YOUR CODE HERE
				try {
					
					String pw = new String(dbPassword.getPassword());
					
					Credentials.getInstance().setPassword(SecurityTool.encryptString(pw));
					Credentials.getInstance().persist();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dispose();
			}
		});
		
		panel.add(dbPassword);
		
		add(panel, BorderLayout.CENTER);
	}
}
