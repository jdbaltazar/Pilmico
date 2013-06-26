package gui.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import util.soy.SoyButton;
import util.soy.SoyPanel;

public class DatabasePopup extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 278, HEIGHT = 200;
	private SoyPanel panel;
	
	private SoyButton ok;
	

	public DatabasePopup() {
		
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
				g2.drawString("Disconnection Notice", 15, 22);
				g2.drawLine(0, 30, getWidth(), 30);
				
				g2.setFont(new Font("Dialog", Font.ITALIC, 14));
				g2.drawString("Database connection has failed. All", 15, 75);
				g2.drawString("further progress will not be saved.", 15, 90);
				
				g2.drawString("Click 'OK' to close the program.", 15, 125);
				
				paintChildren(g2);

				g2.dispose();
				g.dispose();
			}
		};
		
		panel.setLayout(null);
		
		ok = new SoyButton("OK");
		ok.setBounds(115,155, 50, 28);
		ok.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		panel.add(ok);
		
		add(panel, BorderLayout.CENTER);
	}
}
