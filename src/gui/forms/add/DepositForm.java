package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.DropdownLabel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;
import common.entity.profile.Designation;
import common.entity.profile.Account;
import common.entity.profile.Employee;
import common.manager.Manager;

public class DepositForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
	private FormField remarks;
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private JSpinner date;
	private DefaultEntryLabel issuedBy;
	private int initY = 18;
	private DropdownLabel dateLabel, issuedByLabel, depositorLabel, bankAcctLabel, bankLabel;
	private SBButton fwd, fwd2, fwd3;
	private JComboBox bankCombo, bankAcctCombo;
	private JTextField bankComboField, bankAcctComboField;
	private FormDropdown depositorCombo;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.depositFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	public DepositForm() {
		super("Add Deposit");
		addComponents();
		fillEntries();
		
		Values.depositForm = this;
	}

	public void fillEntries() {
		date.setValue(new Date());

		fields.get(0).setText("0");
		issuedBy.setText(Manager.loggedInAccount.getFirstPlusLastName());

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		fwd = new SBButton("forward.png", "forward.png", "Add new bank");
		fwd2 = new SBButton("forward.png", "forward.png", "Add new bank account");
		fwd3 = new SBButton("forward.png", "forward.png", "Add new employee");

		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		error = new ErrorLabel();

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		depositorLabel = new DropdownLabel("Depositor*");
		bankAcctLabel = new DropdownLabel("Bank Account*");
		bankLabel = new DropdownLabel("Bank*");

		issuedBy = new DefaultEntryLabel(Manager.loggedInAccount.getFirstPlusLastName());

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date, "MMMM dd, yyyy hh:mm:ss a");
		date.setEditor(timeEditor2);
		date.setValue(new Date());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		date.setBorder(BorderFactory.createEmptyBorder());

		JComponent editor = date.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;
			JFormattedTextField tf = defEditor.getTextField();
			if (tf != null) {
				tf.setForeground(new Color(25, 117, 117));
				tf.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		depositorCombo = new FormDropdown();
		refreshDropdowns(false);

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 63) {

			if (i == 4) {

				fields.add(new JNumericField(Tables.depositFormLabel[i]));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				panel.add(fields.get(ctr));

				ctr++;
			}

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 200, 11);
				date.setBounds(x1, initY + y + 5, 200, 20);
			}
			if (i == 5) {
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 1) {
				fwd.setBounds(x1 + 34, initY + y - 11, 16, 16);
				bankLabel.setBounds(x1, initY + y - 7, 200, 11);
			}

			if (i == 2) {
				fwd2.setBounds(x1 + 76, initY + y - 11, 16, 16);
				bankAcctLabel.setBounds(x1, initY + y - 7, 200, 11);
				
				x1 += 250;

				y = -63;
			}

			if (i == 3) {
				fwd3.setBounds(x1 + 57, initY + y - 11, 16, 16);

				depositorLabel.setBounds(x1, initY + y - 7, 200, 11);
				depositorCombo.setBounds(x1, initY + y + 5, 200, 20);
			}

//			if (i == 6) {
//				remarks = new FormField(Tables.depositFormLabel[i], 100, Color.white, Color.gray);
//				remarks.setBounds(x1, initY + y, 200, 25);
//			}
		}

		clear.setBounds(289, 208, 80, 30);//y258
		save.setBounds(180, 208, 80, 30); // 48

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// Date date, BankAccount bankAccount, double amount, Employee
				// depositor, Account issuedBy, boolean valid, String remarks

				Date d = ((SpinnerDateModel) date.getModel()).getDate();
				Deposit deposit = new Deposit(d, (BankAccount) bankAcctCombo.getSelectedItem(), Double.parseDouble(fields.get(0).getText()),
						(Employee) depositorCombo.getSelectedItem(), Manager.loggedInAccount, true, remarks.getText());

				try {
					Manager.depositManager.addDeposit(deposit);

					Values.centerPanel.changeTable(Values.DEPOSITS);
					new SuccessPopup("Add").setVisible(true);
					clearFields();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(clear);
		panel.add(save);

		panel.add(issuedBy);
		panel.add(date);

		panel.add(fwd);
		panel.add(fwd2);
		panel.add(fwd3);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(bankLabel);

		panel.add(depositorLabel);
		panel.add(depositorCombo);

		panel.add(bankAcctLabel);

//		panel.add(remarks);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(10, 38, 520, 260);//310h

		add(scrollPane);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setText("");
		
		refreshDropdowns(true);
	}

	private boolean isValidated() {

		if (!username.equals("") && !password.equals("") && !firstName.equals("") && !lastName.equals("") && !address.equals(""))
			return true;

		return false;
	}

	public void refreshDropdowns(boolean remove) {
		
		if(remove){
			panel.remove(bankCombo);
			panel.remove(bankAcctCombo);
		}
		
		bankCombo = new JComboBox();
		
		bankCombo.setEditable(true);
		bankComboField = (JTextField) bankCombo.getEditor().getEditorComponent();
		bankComboField.setText("");
		bankComboField.setOpaque(false);
		bankComboField.addKeyListener(new ComboKeyHandler(bankCombo));
		
		bankAcctCombo = new JComboBox();
		bankAcctCombo.setEditable(true);
		bankAcctComboField = (JTextField) bankAcctCombo.getEditor().getEditorComponent();
		bankAcctComboField.setText("");
		bankAcctComboField.setOpaque(false);
		bankAcctComboField.addKeyListener(new ComboKeyHandler(bankAcctCombo));
		
		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getEmployedEmployeesExceptManagers().toArray());
			depositorCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bankCombo.setModel(new DefaultComboBoxModel(Manager.depositManager.getBanks().toArray()));
			if (bankCombo.getItemCount() > 0) {
				bankCombo.setSelectedIndex(0);
				Bank b = (Bank) bankCombo.getItemAt(0);
				bankAcctCombo.setModel(new DefaultComboBoxModel((new ArrayList<BankAccount>(b.getBankAccounts()).toArray())));
			}

			depositorCombo.setModel(new DefaultComboBoxModel(Manager.employeePersonManager.getEmployedEmployees().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		depositorCombo.setSelectedIndex(0);
		
		bankCombo.setSelectedIndex(-1);
		bankAcctCombo.setSelectedIndex(-1);
		
		bankCombo.setBounds(40, 86, 200, 20);
		bankAcctCombo.setBounds(40, 149, 200, 20);
		
		panel.add(bankCombo);
		panel.add(bankAcctCombo);
	}
}

