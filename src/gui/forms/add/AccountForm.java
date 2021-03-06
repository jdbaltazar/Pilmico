package gui.forms.add;

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

import util.DropdownLabel;
import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.entity.profile.Employee;
import common.manager.Manager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AccountForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown acctType, employeeCombo;
	private DefaultComboBoxModel model;

	private int initY = 70;

	private ErrorLabel error;
	private DropdownLabel employee, acctT;
	private SBButton fwd;

	private String msg;

	private final int num = Tables.accountFormLabel.length;

	public AccountForm() {
		super("Add Account");
		addComponents();
		fillEntries();
		Values.accountForm = this;
	}

	public void fillEntries() {
		try {
			List<AccountType> accountTypes = Manager.getInstance().getAccountManager().getAccountTypes();
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

		employeeCombo = new FormDropdown();
		refreshEmployee();

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

		error.setBounds(60, 290, 200, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {
					Account acc = new Account(fields.get(0).getText(), fields.get(1).getText(), (AccountType) acctType.getSelectedItem(),
							(Employee) employeeCombo.getSelectedItem());
					try {
						Manager.getInstance().getAccountManager().addAccount(acc);
						
						Values.centerPanel.changeTable(Values.ACCOUNTS);
						new SuccessPopup("Add").setVisible(true);
						clearFields();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else
					error.setToolTip(msg);
			}
		});

		List<AccountType> accountTypes = new ArrayList<AccountType>();
		try {
			accountTypes = Manager.getInstance().getAccountManager().getAccountTypes();
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

		employeeCombo.setSelectedIndex(0);

		error.setToolTip("");
	}

	private boolean isValidated() {

		if (employeeCombo.getModel().getSelectedItem() == null) {
			msg = "Employee is required ";
			return false;
		}

		for (int i = 0; i < fields.size(); i++)
			if (fields.get(i).getText().equals("")) {
				msg = "Please fillup all fields ";
				return false;
			}
		
		if (fields.get(0).getText().length() < 5) {
			msg = "Username should have at least 5 characters ";
			return false;
		}

		if (fields.get(1).getText().length() < 5) {
			msg = "Password should have at least 5 characters ";
			return false;
		}

		try {
			if (Manager.getInstance().getAccountManager().usernameTaken(fields.get(0).getText().trim())) {
				msg = "Username \'" + fields.get(0).getText() + "\' already taken";
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void refreshEmployee() {
		try {
			model = new DefaultComboBoxModel(Manager.getInstance().getEmployeePersonManager().getEmployedEmployeesWithoutAccounts().toArray());
			employeeCombo.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
