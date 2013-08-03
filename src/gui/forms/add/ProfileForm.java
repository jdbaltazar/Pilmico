package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.entity.profile.Person;
import common.manager.Manager;

import util.ErrorLabel;
import util.FormCheckbox;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ProfileForm extends SimplePanel {
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

	private final int num = Tables.profileFormLabel.length;

	public ProfileForm() {
		super("Add Customer");
		addComponents();

		// Values.accountForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 43; i < num; i++, y += 54) {

				fields.add(new FormField(Tables.profileFormLabel[i], 100, Color.white, Color.gray));
				fields.get(i).setBounds(x1, 55 + y, 200, 25);
				add(fields.get(i));
		}

		clear.setBounds(167, 330, 80, 30);
		save.setBounds(58, 330, 80, 30);

		error.setBounds(68, 300, 170, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(isValidated()){
				Person p = new Person(fields.get(1).getText(), fields.get(2).getText(), fields.get(0).getText(), fields.get(3).getText(), fields.get(4)
						.getText(), true);
				try {
					Manager.employeePersonManager.addPerson(p);
					Values.centerPanel.changeTable(Values.CUSTOMERS);
					new SuccessPopup("Add").setVisible(true);
					clearFields();
					
					Values.salesForm.refreshCustomer(true);
//					Values.supplierForm.refreshCustomer(true);
					Values.accountReceivablesForm.refreshCustomer(true);
					Values.discountForm.refreshDropdown(true);
					Values.arPaymentForm.refreshDropdown(true);
					Values.caPaymentForm.refreshDropdown(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else
					error.setText(msg);
			}
		});

		add(clear);
		add(save);

		add(error);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setText("");
	}

	private boolean isValidated() {

		if(fields.get(0).getText().equals("")){
			
			msg = "Last Name is required";
			
			return false;
		}
		
		if(fields.get(1).getText().equals("")){
			
			msg = "First Name is required";
			
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
