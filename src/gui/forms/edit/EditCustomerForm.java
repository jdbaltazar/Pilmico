package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.FormLabel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.entity.profile.Person;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class EditCustomerForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();

	private SoyButton edit;
	private FormDropdown acctType;
	private JComboBox employeeCombo;
	private JTextField employeesField;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.profileFormLabel.length;

	private Person customer;

	public EditCustomerForm(Person customer) {
		super("View / Edit Customer");
		this.customer = customer;
		addComponents();
		fillEntries();
		// Values.accountForm = this;
	}

	private void fillEntries() {
		fields.get(0).setText(customer.getLastName());
		fields.get(1).setText(customer.getFirstName());
		fields.get(2).setText(customer.getMiddleName());
		fields.get(3).setText(customer.getAddress());
		fields.get(4).setText(customer.getContactNo());
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		for (int i = 0, y = 0, x1 = 290; i < num; i++, y += 65) {

			fields.add(new EditFormField(100));
			labels.add(new FormLabel(Tables.profileFormLabel[i]));

			fields.get(i).setBounds(x1, 40 + y, 200, 25);
			labels.get(i).setBounds(x1, 25 + y, 200, 15);

			add(fields.get(i));
			add(labels.get(i));
		}

		edit.setBounds(360, 355, 80, 30);

		error.setBounds(160, 290, 230, 25);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				customer.setLastName(fields.get(0).getText());
				customer.setFirstName(fields.get(1).getText());
				customer.setMiddleName(fields.get(2).getText());
				customer.setAddress(fields.get(3).getText());
				customer.setContactNo(fields.get(4).getText());

				try {
					Manager.employeePersonManager.updatePerson(customer);
					System.out.println("customer updated!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		add(edit);

		add(error);

	}

	private boolean isValidated() {

		if (!username.equals("") && !password.equals("") && !firstName.equals("") && !lastName.equals("") && !address.equals(""))
			return true;

		return false;
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
