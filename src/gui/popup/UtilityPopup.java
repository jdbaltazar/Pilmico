package gui.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.forms.util.FormField;

import javax.swing.JDialog;
import javax.swing.JPanel;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Values;

public class UtilityPopup extends JDialog {

	private final int WIDTH = 150, HEIGHT = 50;
	private FormField field;
	private String label;
	private Point p;
	private int utility;
	private JPanel panel;
	private SBButton close;
	private ErrorLabel error;

	public UtilityPopup(Point p, String label, int utility) {
		this.p = p;
		this.label = label;
		this.utility = utility;

		init();
		addComponents();
	}

	private void init() {
		Values.mainFrame.dimScreen(true);

		setLocation(p);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new SimplePanel(""){
			@Override
			public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				gradient = new GradientPaint(0, 0, new Color(245,255,250),0, getHeight(), Color.white);
				g2.setPaint(gradient);
				g2.fill(g.getClipBounds());
				
				paintChildren(g2);

				g2.dispose();
				g.dispose();
			}

		};
		
		panel.setOpaque(false);
		
		field = new FormField(label, 100, Color.white, Color.gray);
		close = new SBButton("dialog_close.png", "dialog_close.png", "Close");
		error = new ErrorLabel();
		
		field.setBounds(5, 20, 140, 20);
		close.setBounds(132, 2, 16, 16);
		error.setBounds(7, 3, 120, 15);

		panel.add(field);
		panel.add(error);

		field.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (!field.getText().equals("")) {
					
					Values.mainFrame.dimScreen(false);
					
					switch (utility) {

					case Values.CATEGORY:
						Values.productForm.updateFormDropdowns();
						break;

					default:
						break;

					}
					
					dispose();
					new SuccessPopup("Add", 1).setVisible(true);
				}
				else
					error.setText("Textfield empty");
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.mainFrame.dimScreen(false);
				dispose();
			}
		});
		
		add(close);
		add(panel);
	}

}