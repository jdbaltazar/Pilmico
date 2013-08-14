package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.FormLabel;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg="";

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
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();
		
		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 65) {//290

			fields.add(new EditFormField(100));
			labels.add(new FormLabel(Tables.profileFormLabel[i]));

			fields.get(i).setBounds(x1, 30 + y, 200, 25);
			labels.get(i).setBounds(x1, 15 + y, 200, 15);

			panel.add(fields.get(i));
			panel.add(labels.get(i));
		}

		edit.setBounds(360, 355, 80, 30);

		error.setBounds(510, 335, 200, 22);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(isValidated()){
				customer.setLastName(fields.get(0).getText());
				customer.setFirstName(fields.get(1).getText());
				customer.setMiddleName(fields.get(2).getText());
				customer.setAddress(fields.get(3).getText());
				customer.setContactNo(fields.get(4).getText());

				try {
					Manager.employeePersonManager.updatePerson(customer);
					
					Values.editPanel.startAnimation();
					new SuccessPopup("Edit").setVisible(true);
					Values.centerPanel.changeTable(Values.CUSTOMERS);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else
					error.setToolTip(msg);
			}
		});

		add(edit);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(272, 11, 250, 330);

		add(error);
		add(scrollPane);

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
