package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import common.manager.Manager;

import util.DropdownLabel;
import util.EditFormPanel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ViewDiscountForm extends EditFormPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, productLabel, customerLabel, amountLabel;
	private ViewFormField product, customer, issuedBy, date, amount;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.discountFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	public ViewDiscountForm() {
		super("View Discount");
		addComponents();

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		icon = new ImageIcon("images/pending.png");
		
		status = new JLabel("PENDING", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();
		
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		productLabel = new ViewFormLabel("Product:");
		customerLabel = new ViewFormLabel("Customer:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField(Manager.loggedInAccount.getFirstPlusLastName());
		date = new ViewFormField("17 Jul 2013 10:15 PM");
		customer = new ViewFormField("Juan dela Cruz");
		product = new ViewFormField("PELLETS");
		amount = new ViewFormField("2500.00");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y  - 14, 200, 20);
			}

			if (i == 2) {
				productLabel.setBounds(x1, initY + y - 7, 60, 11);
				product.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			
			if (i == 3) {
				customerLabel.setBounds(x1, initY + y - 7, 60, 11);
				customer.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			
			if (i == 4) {
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
		}

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

//		panel.add(clear);
//		panel.add(save);

		panel.add(issuedBy);
		panel.add(date);

		panel.add(dateLabel);
		panel.add(issuedByLabel);
		
		panel.add(product);
		panel.add(productLabel);
		
		panel.add(customer);
		panel.add(customerLabel);
		
		panel.add(amount);
		panel.add(amountLabel);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));	
		
		scrollPane.setBounds(245, 63, 300, 275);
		
		
		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);

		add(scrollPane);
		add(status);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setText("");
	}

	private boolean isValidated() {

		if (!username.equals("") && !password.equals("") && !firstName.equals("") && !lastName.equals("") && !address.equals(""))
			return true;

		return false;
	}

	public void refreshDropdown() {
		try {
			model = new DefaultComboBoxModel();
			// issuedBy = new FormDropdown();
			// issuedBy.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
