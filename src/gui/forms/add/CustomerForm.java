package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.profile.Person;
import common.manager.Manager;

import util.ErrorLabel;
import util.FormCheckbox;
import util.JNumericField;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class CustomerForm extends SimplePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown acctType;
	private JComboBox employeeCombo;
	private JTextField employeesField;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String msg;

	private JNumericField ar_balance;

	private JPanel panel;
	private JScrollPane scrollPane;

	private final int num = Tables.customerFormLabel.length;

	public CustomerForm() {
		super("Add Customer");
		addComponents();

		// Values.accountForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		error = new ErrorLabel();

		for (int i = 0, y = 0, x1 = 32; i < num; i++, y += 48) {// y+=54

			if (i == num - 1) {
				ar_balance = new JNumericField(Tables.customerFormLabel[i]);
				ar_balance.setMaxLength(10);
				ar_balance.setPrecision(2);

				ar_balance.setBounds(x1, 7 + y, 200, 25);
			} else {
				fields.add(new FormField(Tables.customerFormLabel[i], 100, Color.white, Color.gray));
				fields.get(i).setBounds(x1, 7 + y, 200, 25);// 12
				panel.add(fields.get(i));
			}
		}

		clear.setBounds(149, 300, 80, 30);
		save.setBounds(39, 300, 80, 30);

		error.setBounds(68, 310, 170, 20);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {
					Person p = new Person(fields.get(1).getText(), fields.get(2).getText(), fields.get(0).getText(), fields.get(3).getText(), fields
							.get(4).getText(), true);
					try {
						Manager.employeePersonManager.addPerson(p);
						Values.centerPanel.changeTable(Values.CUSTOMERS);
						new SuccessPopup("Add").setVisible(true);
						clearFields();

						Values.salesForm.refreshCustomer(true);
						// Values.supplierForm.refreshCustomer(true);
						Values.accountReceivablesForm.refreshCustomer(true);
						Values.discountForm.refreshDropdown(true);
						Values.arPaymentForm.refreshDropdown(true);
						Values.caPaymentForm.refreshDropdown(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					error.setToolTip(msg);
			}
		});

		panel.add(ar_balance);

		panel.add(clear);
		panel.add(save);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(10, 35, 270, 340);

		add(error);
		add(scrollPane);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setToolTip("");
	}

	private boolean isValidated() {

		if (fields.get(0).getText().equals("")) {

			msg = "Last Name is required";

			return false;
		}

		if (fields.get(1).getText().equals("")) {

			msg = "First Name is required";

			return false;
		}

		if (ar_balance.getText().equals("")) {

			msg = "Initial A_R Balance is required";

			return false;
		}

		return true;
	}

	public void refreshDropdown() {
		try {
			model = new DefaultComboBoxModel();
			acctType = new FormDropdown();
			acctType.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
