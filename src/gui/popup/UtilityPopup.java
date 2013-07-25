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
import javax.swing.JPanel;

import common.entity.product.Category;
import common.entity.sales.Sales;
import common.manager.Manager;

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
	private SBButton close, backup, recover;
	private ErrorLabel error;
	private Object object;

	public UtilityPopup(Point p, String label, int utility, Object o) {
		this.p = p;
		this.label = label;
		this.utility = utility;
		this.object = o;
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
		else
			setSize(300, HEIGHT);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new SimplePanel("") {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				gradient = new GradientPaint(0, 0, new Color(245, 255, 250), 0, getHeight(), Color.white);
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
		backup = new SBButton("backup.png", "backup2.png", "Backup Database");
		recover = new SBButton("recover.png", "recover2.png", "Database Recovery");
		
		error = new ErrorLabel();

		if (utility == Values.CATEGORY) {
			field.setBounds(5, 20, 140, 20);
			close.setBounds(132, 2, 16, 16);
		} 
		else if(utility == Values.DATABASE){
			backup.setBounds(15, 19, 20, 20);
			recover.setBounds(55, 19, 20, 20);
			close.setBounds(72, 2, 16, 16);
			
			panel.add(backup);
			panel.add(recover);
		}
		else {
			field.setBounds(5, 20, 290, 20);
			close.setBounds(282, 2, 16, 16);
		}

		error.setBounds(7, 3, 120, 15);

		if(utility != Values.DATABASE)
			panel.add(field);
		
		panel.add(error);
		
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

					if (object instanceof Sales) {
						Sales s = (Sales) object;
						s.setValid(false);
						s.setRemarks(field.getText());
						try {
							Manager.salesManager.updateSales(s);
							Values.mainFrame.dimScreen(false);

							switch (utility) {

							case Values.CATEGORY:
								Values.productForm.updateFormDropdowns();
								break;

							default:
								break;

							}

							dispose();
							new SuccessPopup("Edit", 1).setVisible(true);

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

				} else
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

}
