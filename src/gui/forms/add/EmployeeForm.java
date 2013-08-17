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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import common.entity.profile.Designation;
import common.entity.profile.Employee;
import common.entity.profile.EmploymentStatus;
import common.entity.profile.Person;
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

public class EmployeeForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown employmentStatus, designation;
	private DefaultComboBoxModel model;
	private JSpinner startDate, endDate;

	private JNumericField salary;
	private int initY = 50;

	private ErrorLabel error;
	private DropdownLabel designationLabel, status, startD, endD;
	private String msg;

	private final int num = Tables.employeeFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	public EmployeeForm() {
		super("Add Employee");

		addComponents();
		fillEntries();
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		designationLabel = new DropdownLabel("Designation*");
		status = new DropdownLabel("Employment Status*");
		startD = new DropdownLabel("Starting Date");
		endD = new DropdownLabel("Ending Date");

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		startDate = new SpinnerDate("MMMM dd, yyyy");

		endDate = new SpinnerDate("MMMM dd, yyyy");

		employmentStatus = new FormDropdown();
		designation = new FormDropdown();

		salary = new JNumericField("*Salary (per month)");
		salary.setMaxLength(10);

		try {
			model = new DefaultComboBoxModel();
			employmentStatus = new FormDropdown();
			employmentStatus.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		model = new DefaultComboBoxModel();

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 19; i < num; i++, y += 60) {

			if (i == 3) {
				x1 = 224;
				y = 0;
			}

			if (i == 7) {
				x1 = 429;
				y = 0;
			}

			if (i != 5 && i != 6 && i != 7 && i != 8 && i != 10) {
				fields.add(new FormField(Tables.employeeFormLabel[i], 100, Color.white, Color.gray));
				fields.get(ctr).setBounds(x1, initY + y, 170, 25);
				panel.add(fields.get(ctr));

				ctr++;
			}

			if (i == 5) {
				designationLabel.setBounds(x1, initY + y - 7, 100, 11);
				designation.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 6) {
				status.setBounds(x1, initY + y - 7, 100, 11);
				employmentStatus.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 7) {
				startD.setBounds(x1, initY + y - 7, 100, 11);
				startDate.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 8) {
				salary.setBounds(x1, initY + y, 170, 25);
			}

			if (i == 10) {
				endD.setBounds(x1, initY + y - 7, 100, 11);
				endDate.setBounds(x1, initY + y + 5, 170, 20);
				endDate.setVisible(false);
				endD.setVisible(false);
			}

		}

		clear.setBounds(342, 300, 80, 30);
		save.setBounds(223, 300, 80, 30);

		error.setBounds(375, 276, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {
					try {

						Employee employee = new Employee(new Person(fields.get(1).getText(), fields.get(2).getText(), fields.get(0).getText(), fields
								.get(3).getText(), fields.get(4).getText(), false), (Designation) designation.getSelectedItem(),
								(EmploymentStatus) employmentStatus.getSelectedItem(), ((SpinnerDateModel) startDate.getModel()).getDate(), Double
										.parseDouble(salary.getText()), fields.get(5).getText(), null);

						Manager.employeePersonManager.addEmployee(employee);

						Values.centerPanel.changeTable(Values.EMPLOYEES);
						new SuccessPopup("Add").setVisible(true);
						clearFields();

						Values.salaryReleaseForm.refreshEmployee();
						Values.cashAdvanceForm.refreshEmployee();
						Values.accountForm.refreshEmployee();

						if (Values.caPaymentForm != null)
							Values.caPaymentForm.refreshDropdown(true);
						if (Values.arPaymentForm != null)
							Values.arPaymentForm.refreshDropdown(true);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					error.setToolTip(msg);

			}
		});

		scrollPane.setBounds(10, 10, 620, 360);

		panel.add(clear);
		panel.add(save);

		panel.add(designation);
		panel.add(designationLabel);

		panel.add(status);
		panel.add(startD);
		panel.add(endD);

		panel.add(salary);

		panel.add(employmentStatus);
		panel.add(endDate);
		panel.add(startDate);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(error);
		add(scrollPane);

	}

	private void fillEntries() {

		try {
			List<Designation> designations = Manager.isAuthorized() ? Manager.employeePersonManager.getAllDesignations() : Manager.employeePersonManager
					.getDesignations();
			for (Designation d : designations) {
				designation.addItem(d);
			}

			List<EmploymentStatus> employmentStatuses = Manager.employeePersonManager.getEmploymentStatuses();
			for (EmploymentStatus es : employmentStatuses) {
				employmentStatus.addItem(es);
			}

			startDate.setValue(new Date());

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		if (salary.getText().equals("")) {
			msg = "Salary is required";

			return false;
		}

		return true;
	}

	public void refreshDropdown() {
		try {
			model = new DefaultComboBoxModel();
			employmentStatus = new FormDropdown();
			employmentStatus.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
