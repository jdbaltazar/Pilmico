package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.IconLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.entity.cashadvance.CashAdvance;
import common.entity.profile.Employee;
import common.manager.Manager;

import util.DropdownLabel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class CashAdvanceForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private FormDropdown issuedFor;
	private DefaultComboBoxModel model;
	private JSpinner date;
	private JLabel issuedBy;
	private int initY = 60;
	private DropdownLabel dateLabel, issuedByLabel, issuedForLabel;
	private SBButton fwd;

	private ErrorLabel error;
	private String msg;

	private final int num = Tables.cashAdvanceFormLabel.length;

	public CashAdvanceForm() {
		super("Add Cash Advance");
		addComponents();
		fillEntries();
		Values.cashAdvanceForm = this;
	}

	public void fillEntries() {

		date.setValue(new Date());
		issuedBy.setText(Manager.loggedInAccount.getFirstPlusLastName());
		refreshEmployee();
		fields.get(0).setText("0");
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		fwd = new SBButton("forward.png", "forward.png", "Add new employee");
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.EMPLOYEES);
			}
		});

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		issuedForLabel = new DropdownLabel("Issued for*");

		// issuedBy = new FormDropdown();
		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

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

		issuedFor = new FormDropdown();
		refreshEmployee();
		/*
		 * model = new DefaultComboBoxModel(); employeeCombo = new
		 * JComboBox(model);
		 * 
		 * employeeCombo.setSelectedIndex(-1);
		 * 
		 * employeeCombo.setEditable(true); employeesField = (JTextField)
		 * employeeCombo.getEditor() .getEditorComponent();
		 * employeesField.setText(""); employeesField.addKeyListener(new
		 * ComboKeyHandler(employeeCombo));
		 */
		int ctr = 0;
		for (int i = 0, y = 0, x1 = 51; i < num - 1; i++, y += 55) {

			if (i == 3 || i == 4) {

				fields.add(new JNumericField(Tables.cashAdvanceFormLabel[i]));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 200, 11);
				date.setBounds(x1, initY + y + 5, 200, 20);
				dateStatus.setBounds(x1 + 205, initY + y + 7, 16, 16);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 2) {
				fwd.setBounds(x1 + 57, initY + y - 11, 16, 16);
				issuedForLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedFor.setBounds(x1, initY + y + 5, 200, 20);
			}
		}

		clear.setBounds(167, 290, 80, 30);
		save.setBounds(58, 290, 80, 30);

		error.setBounds(102, 257, 160, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {

					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {
						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						Employee emp = (Employee) issuedFor.getSelectedItem();
						CashAdvance cashAdvance = new CashAdvance(d, Double.parseDouble(fields.get(0).getText()), emp, Double.parseDouble(fields.get(0)
								.getText()), Manager.loggedInAccount);

						cashAdvance.setRemarks(uP.getInput());
						try {
							Manager.cashAdvanceManager.addCashAdvance(cashAdvance);

							Values.centerPanel.changeTable(Values.CASH_ADVANCE);
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

		add(clear);
		add(save);

		add(issuedBy);
		add(issuedFor);
		add(date);
		add(dateStatus);

		add(fwd);
		add(dateLabel);
		add(issuedByLabel);
		add(issuedForLabel);

		add(error);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setToolTip("");
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {
				String str = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);
				dateStatus.setIconToolTip(new ImageIcon("images/invalid_date2.png"), str, false);
				error.setToolTip(str);
			}

			else {
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), Values.VALID_DATE, true);
				error.setToolTip("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValidated() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {


				msg = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (issuedFor.getModel().getSelectedItem() == null) {

			msg = "No employee selected ";

			return false;
		}

		amount = fields.get(0).getText();

		if (!amount.equals("")) {
			return true;
		}

		msg = "Amount is required ";
		return false;
	}

	public void refreshEmployee() {

		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getEmployedEmployeesExceptManagers().toArray());
			issuedFor.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (issuedFor.getItemCount() > 0)
			issuedFor.setSelectedIndex(0);

	}

}
