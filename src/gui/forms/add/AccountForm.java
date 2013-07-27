package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.entity.profile.Employee;
import common.manager.Manager;

import util.DropdownLabel;
import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class AccountForm extends SimplePanel {

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

	private int initY = 70;

	private ErrorLabel error;
	private DropdownLabel employee, acctT;
	private SBButton fwd;

	private final int num = Tables.accountFormLabel.length;

	public AccountForm() {
		super("Add Account");
		addComponents();
		fillEntries();
		Values.accountForm = this;
	}

	public void fillEntries() {
		try {
			List<AccountType> accountTypes = Manager.accountManager.getAccountTypes();
			DefaultComboBoxModel model = new DefaultComboBoxModel(accountTypes.toArray());
			employeeCombo.setModel(model);
			refreshEmployee();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addComponents() {
		fwd = new SBButton("forward.png", "forward.png", "Add new employee");
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.EMPLOYEES);
			}
		});

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		employee = new DropdownLabel("Employee*");
		acctT = new DropdownLabel("Account Type*");

		acctType = new FormDropdown();

		try {
			model = new DefaultComboBoxModel();
			acctType = new FormDropdown();
			acctType.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		model = new DefaultComboBoxModel();
		employeeCombo = new JComboBox(model);

		employeeCombo.setSelectedIndex(-1);

		employeeCombo.setEditable(true);
		employeesField = (JTextField) employeeCombo.getEditor().getEditorComponent();
		employeesField.setText("");
		employeesField.addKeyListener(new ComboKeyHandler(employeeCombo));

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 50; i < num; i++, y += 60) {

			if (i != 0 && i != 1) {
				fields.add(new FormField(Tables.accountFormLabel[i], 100, Color.white, Color.gray));
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0) {
				acctT.setBounds(x1, initY + y - 7, 100, 11);
				acctType.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 1) {
				fwd.setBounds(x1 + 56, initY + y - 11, 16, 16);
				employee.setBounds(x1, initY + y - 7, 100, 11);
				employeeCombo.setBounds(x1, initY + y + 5, 200, 20);
			}
		}

		clear.setBounds(167, 330, 80, 30);
		save.setBounds(58, 330, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Account acc = new Account(fields.get(0).getText(), fields.get(1).getText(), (AccountType) acctType.getSelectedItem(),
						(Employee) employeeCombo.getSelectedItem());
				try {
					Manager.accountManager.addAccount(acc);
					Values.centerPanel.changeTable(Values.ACCOUNTS);
					new SuccessPopup("Add").setVisible(true);
					clearFields();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		List<AccountType> accountTypes = new ArrayList<AccountType>();
		try {
			accountTypes = Manager.accountManager.getAccountTypes();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		for (AccountType at : accountTypes) {
			acctType.addItem(at);
		}

		refreshEmployee();

		add(fwd);
		add(employee);
		add(acctT);

		add(clear);
		add(save);
		add(acctType);
		add(employeeCombo);

		add(error);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setText("");
	}

	private boolean isValidated() {

		return false;
	}

	public void refreshEmployee() {
		try {

			List<Employee> employees = Manager.employeePersonManager.getEmployedEmployeesWithoutAccounts();

			System.out.println("emps: " + employees.size());
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getEmployedEmployeesWithoutAccounts().toArray());
			employeeCombo.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
