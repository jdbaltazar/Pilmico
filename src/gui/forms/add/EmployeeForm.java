package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;

import java.awt.Color;
import java.awt.Font;
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
	private FormDropdown employmentStatus;
	private JComboBox personCombo;
	private JTextField personField;
	private DefaultComboBoxModel model;
	private JSpinner startDate, endDate;

	private int initY = 70;

	private ErrorLabel error;
	private DropdownLabel profile, status, startD, endD;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.employeeFormLabel.length;
	private SBButton fwd;

	public EmployeeForm() {
		super("Add Employee");
		addComponents();
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		fwd = new SBButton("forward.png", "forward.png", "Add new category");

		profile = new DropdownLabel("Profile*");
		status = new DropdownLabel("Employment Status*");
		startD = new DropdownLabel("Starting Date");
		endD = new DropdownLabel("Ending Date");

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		startDate = new SpinnerDate("MMMM dd, yyyy");

		endDate = new SpinnerDate("MMMM dd, yyyy");

		employmentStatus = new FormDropdown();

		try {
			model = new DefaultComboBoxModel();
			employmentStatus = new FormDropdown();
			employmentStatus.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		model = new DefaultComboBoxModel();
		personCombo = new JComboBox(model);

		personCombo.setSelectedIndex(-1);

		personCombo.setEditable(true);
		personField = (JTextField) personCombo.getEditor().getEditorComponent();
		personField.setText("");
		personField.addKeyListener(new ComboKeyHandler(personCombo));

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 60) {

			if (i == 4) {
				x1 = 245;
				y = 0;
			}

			if (i == 1 || i == 4 || i == 5) {
				fields.add(new FormField(Tables.employeeFormLabel[i], 100, Color.white, Color.gray));
				fields.get(ctr).setBounds(x1, initY + y, 170, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0) {
				fwd.setBounds(x1 + 43, initY + y - 11, 16, 16);
				profile.setBounds(x1, initY + y - 7, 100, 11);
				personCombo.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 2) {
				status.setBounds(x1, initY + y - 7, 100, 11);
				employmentStatus.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 3) {
				startD.setBounds(x1, initY + y - 7, 100, 11);
				startDate.setBounds(x1, initY + y + 5, 170, 20);
			}

			if (i == 6) {
				endD.setBounds(x1, initY + y - 7, 100, 11);
				endDate.setBounds(x1, initY + y + 5, 170, 20);
			}

		}

		clear.setBounds(257, 330, 80, 30);
		save.setBounds(138, 330, 80, 30);

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

		add(clear);
		add(save);

		add(fwd);

		add(profile);
		add(status);
		add(startD);
		add(endD);

		add(employmentStatus);
		add(personCombo);
		add(endDate);
		add(startDate);

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
