package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;

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

import common.entity.profile.EmploymentStatus;
import common.manager.Manager;

import util.DropdownLabel;
import util.ErrorLabel;
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

	private int initY = 50;

	private ErrorLabel error;
	private DropdownLabel designationLabel, status, startD, endD;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.employeeFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	public EmployeeForm() {
		super("Add Employee");
		addComponents();
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

			if (i == 4) {
				x1 = 224;
				y = 0;
			}
			
			if (i == 8) {
				x1 = 429;
				y = 0;
			}

			if (i!=5 && i != 6 && i != 7 && i != 10) {
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

			if (i == 10) {
				endD.setBounds(x1, initY + y - 7, 100, 11);
				endDate.setBounds(x1, initY + y + 5, 170, 20);
			}

		}

		clear.setBounds(342, 300, 80, 30);
		save.setBounds(223, 300, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		try {
			List<EmploymentStatus> emStatus = Manager.employeePersonManager.getEmploymentStatuses();

			for (EmploymentStatus es : emStatus) {
				employmentStatus.addItem(es);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		scrollPane.setBounds(10, 10, 620, 360);

		panel.add(clear);
		panel.add(save);

		panel.add(designation);
		panel.add(designationLabel);
		
		panel.add(status);
		panel.add(startD);
		panel.add(endD);

		panel.add(employmentStatus);
		panel.add(endDate);
		panel.add(startDate);
		
		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane);
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
