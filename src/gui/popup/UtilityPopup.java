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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.forms.util.FormField;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.entity.product.Category;
import common.entity.sales.Sales;
import common.manager.Manager;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Values;

public class UtilityPopup extends JDialog {

	private final int WIDTH = 155, HEIGHT = 55;
	private FormField field;
	private Point p;
	private int utility;
	private JPanel panel;
	private SBButton close;
	private ErrorLabel utilityLabel;
	
	private String reason = "";

	public UtilityPopup(Point p, int utility) {
		this.p = p;
		this.utility = utility;
		init();
		addComponents();
	}
	
	public UtilityPopup(Point p, String label, int utility, Object object) {
		this.p = p;
		this.utility = utility;
		init();
		addComponents();
	}

	private void init() {
		Values.mainFrame.dimScreen(true);

		setLocation(p);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

		if (utility == Values.CATEGORY)
			setSize(WIDTH, HEIGHT);
		else if (utility == Values.DATABASE)
			setSize(90, 50);
		else{
			setLocation(p.x - 300, p.y);
			setSize(305, HEIGHT);
		}
		
		setBackground(new Color(0,0,0,0));
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new SimplePanel(""); 

		panel.setOpaque(false);

		close = new SBButton("dialog_close.png", "dialog_close.png", "Close");
		
		utilityLabel = new ErrorLabel();
		utilityLabel.setHorizontalAlignment(JLabel.LEFT);

		if (utility == Values.CATEGORY) {
			field.setBounds(5, 20, 140, 20);
			close.setBounds(132, 2, 16, 16);
		}
		else {
			field = new FormField("What's your reason for invalidating this form?", 100, Color.white, Color.gray);			
			utilityLabel.setForeground(Color.decode("#FF4500"));
			utilityLabel.setText("*Required");
			
			field.setBounds(5, 20, 290, 20);
			close.setBounds(282, 2, 16, 16);
		}

		utilityLabel.setBounds(7, 3, 120, 15);

		if(utility != Values.DATABASE)
			panel.add(field);
		
		panel.add(utilityLabel);
		
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if(k.getKeyCode() ==KeyEvent.VK_ESCAPE){
					Values.mainFrame.dimScreen(false);
					dispose();
				}
			}
		});

		field.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (!field.getText().equals("")) {

					/*if (object instanceof Sales) {
						Sales s = (Sales) object;
						s.setValid(false);
						s.setRemarks(field.getText());
						try {
							Manager.salesManager.updateSales(s);

							dispose();
							Values.editPanel.startAnimation();
							new SuccessPopup("Invalidation").setVisible(true);
							Values.centerPanel.changeTable(Values.SALES);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							error.setText("Cannot update sales");
							e.printStackTrace();
						}
					} else if (object instanceof Category) {
						Category c = (Category) object;
						c.setName(field.getText());
						try {
							Manager.productManager.addCategory(c);
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
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							error.setText("Cannot update sales");

						}

					}
					else{*/
						reason = field.getText();
						dispose();
//					}

				}
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
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if(k.getKeyCode() ==KeyEvent.VK_ESCAPE){
					Values.mainFrame.dimScreen(false);
					dispose();
				}
			}
		});

		add(close);
		add(panel);
	}
	
	public String getReason(){
		return reason;
	}

}
