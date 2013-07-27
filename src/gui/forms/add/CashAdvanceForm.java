package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import common.entity.cashadvance.CashAdvance;
import common.entity.profile.Designation;
import common.entity.profile.Employee;
import common.manager.Manager;

import util.DropdownLabel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
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
	private String username, password, firstName, lastName, address;

	private final int num = Tables.cashAdvanceFormLabel.length;

	public CashAdvanceForm() {
		super("Add Cash Advance");
		addComponents();
		fillEntries();

		Values.cashAdvanceForm = this;
	}

	private void fillEntries() {
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

		error = new ErrorLabel();

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		issuedForLabel = new DropdownLabel("Issued for");

		// issuedBy = new FormDropdown();
		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

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
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 2) {
				fwd.setBounds(x1 + 55, initY + y - 11, 16, 16);
				issuedForLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedFor.setBounds(x1, initY + y + 5, 200, 20);
			}
		}

		clear.setBounds(167, 290, 80, 30);
		save.setBounds(58, 290, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Date d = ((SpinnerDateModel) date.getModel()).getDate();
				Employee emp = (Employee) issuedFor.getSelectedItem();
				CashAdvance cashAdvance = new CashAdvance(d, Double.parseDouble(fields.get(0).getText()), emp, Double
						.parseDouble(fields.get(0).getText()), Manager.loggedInAccount);

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
		});

		add(clear);
		add(save);

		add(issuedBy);
		add(issuedFor);
		add(date);

		add(fwd);
		add(dateLabel);
		add(issuedByLabel);
		add(issuedForLabel);

		add(error);

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

	public void refreshEmployee() {

		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getEmployedEmployeesExcept(new Designation(Designation.MANAGER)).toArray());
			issuedFor.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
