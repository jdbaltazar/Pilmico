package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.FormDropdown;
import gui.forms.util.IconLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.DropdownLabel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.entity.deposit.Deposit;
import common.entity.profile.Employee;
import common.manager.Manager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DepositForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
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
	private String msg;

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
				Values.addEntryPanel.linkPanel(Values.BANK);
			}
		});

		fwd2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.BANK);
			}
		});

		fwd3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.EMPLOYEES);
			}
		});

		error = new ErrorLabel();

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		depositorLabel = new DropdownLabel("Depositor*");
		bankAcctLabel = new DropdownLabel("Bank Account*");
		bankLabel = new DropdownLabel("Bank*");

		issuedBy = new DefaultEntryLabel(Manager.loggedInAccount.getFirstPlusLastName());

		date = new SpinnerDate(Values.dateFormat);

		error = new ErrorLabel();
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), Values.VALID_DATE);
		determineDateStatus();

		date.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				determineDateStatus();
			}
		});

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
				dateStatus.setBounds(x1 + 205, initY + y + 7, 16, 16);
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

			// if (i == 6) {
			// remarks = new FormField(Tables.depositFormLabel[i], 100,
			// Color.white, Color.gray);
			// remarks.setBounds(x1, initY + y, 200, 25);
			// }
		}

		clear.setBounds(289, 208, 80, 30);// y258
		save.setBounds(180, 208, 80, 30); // 48

		error.setBounds(315, 177, 170, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// Date date, BankAccount bankAccount, double amount, Employee
				// depositor, Account issuedBy, boolean valid, String remarks

				if (isValidated()) {
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {
						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						Deposit deposit = new Deposit(d, (BankAccount) bankAcctCombo.getSelectedItem(), Double.parseDouble(fields.get(0).getText()),
								(Employee) depositorCombo.getSelectedItem(), Manager.loggedInAccount, true, "");

						deposit.setRemarks(uP.getInput());
						try {
							Manager.getInstance().getDepositManager().addDeposit(deposit);

							Values.centerPanel.changeTable(Values.DEPOSITS);
							new SuccessPopup("Add").setVisible(true);
							clearFields();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else
					error.setToolTip(msg);
			}
		});

		panel.add(clear);
		panel.add(save);
		panel.add(error);

		panel.add(issuedBy);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(fwd);
		panel.add(fwd2);
		panel.add(fwd3);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(bankLabel);

		panel.add(depositorLabel);
		panel.add(depositorCombo);

		panel.add(bankAcctLabel);

		// panel.add(remarks);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(10, 38, 520, 260);// 310h

		add(scrollPane);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setToolTip("");
		refreshDate();

		refreshDropdowns(true);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {
				String str = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);
				dateStatus.setIconToolTip(new ImageIcon("images/invalid_date2.png"), str, false);
				error.setToolTip(str);
			}

			else {
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), Values.VALID_DATE, true);
				error.setToolTip("");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isValidated() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {

				msg = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		amount = fields.get(0).getText();

		// if (bankCombo.getModel().getSelectedItem() == null) {
		if (bankCombo.getSelectedIndex() == -1) {

			msg = "Bank is required ";

			return false;

		}

		if (bankAcctCombo.getSelectedIndex() == -1) {

			msg = "Bank account is required ";

			return false;
		}

		if (depositorCombo.getModel().getSelectedItem() == null) {

			msg = "Depositor is required ";

			return false;
		}

		if (amount.equals("")) {

			msg = "Amount is required ";

			return false;
		}

		return true;

	}

	public void refreshDropdowns(boolean remove) {

		if (remove) {
			panel.remove(bankCombo);
			panel.remove(bankAcctCombo);
		}

		try {
			bankCombo = new JComboBox(new DefaultComboBoxModel(Manager.getInstance().getDepositManager().getBanks().toArray()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bankCombo.setEditable(true);
		bankComboField = (JTextField) bankCombo.getEditor().getEditorComponent();
		bankComboField.setText("");
		bankComboField.setOpaque(false);
		bankComboField.addKeyListener(new ComboKeyHandler(bankCombo));

		try {
			model = new DefaultComboBoxModel(Manager.getInstance().getEmployeePersonManager().getEmployedEmployeesExceptManagers().toArray());
			depositorCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (bankCombo.getItemCount() > 0) {
				bankCombo.setSelectedIndex(0);
				Bank b = (Bank) bankCombo.getItemAt(0);

				model = new DefaultComboBoxModel((new ArrayList<BankAccount>(b.getBankAccounts()).toArray()));
			}

			depositorCombo.setModel(new DefaultComboBoxModel(Manager.getInstance().getEmployeePersonManager().getEmployedEmployees().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		bankAcctCombo = new JComboBox(model);
		bankAcctCombo.setEditable(true);
		bankAcctComboField = (JTextField) bankAcctCombo.getEditor().getEditorComponent();
		bankAcctComboField.setText("");
		bankAcctComboField.setOpaque(false);
		bankAcctComboField.addKeyListener(new ComboKeyHandler(bankAcctCombo));

		depositorCombo.setSelectedIndex(0);

		bankCombo.setSelectedIndex(-1);
		bankAcctCombo.setSelectedIndex(-1);

		bankCombo.setBounds(40, 86, 200, 20);
		bankAcctCombo.setBounds(40, 149, 200, 20);

		panel.add(bankCombo);
		panel.add(bankAcctCombo);
	}
}
