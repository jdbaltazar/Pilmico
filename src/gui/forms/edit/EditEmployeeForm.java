package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormLabel;
import gui.forms.util.HistoryTable;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import common.entity.profile.Designation;
import common.entity.profile.Employee;
import common.entity.profile.EmploymentStatus;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.SpinnerDate;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class EditEmployeeForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;

	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private SoyButton edit;
	private FormDropdown employmentStatus, designation;
	private DefaultComboBoxModel model;
	private JSpinner startDate, endDate;

	private int initY = 35, y2 = 20;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.employeeFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	private Employee employee;
	private SBButton employmentHistory;

	private BalloonTip balloonTip;

	public EditEmployeeForm(Employee employee) {
		super("View / Edit Employee");
		this.employee = employee;
		addComponents();
		fillEntries();

		Values.editEmployeeForm = this;
	}

	private void fillEntries() {

		try {
			List<Designation> designations = Manager.employeePersonManager.getDesignations();
			for (Designation d : designations) {
				designation.addItem(d);
			}

			List<EmploymentStatus> employmentStatuses = Manager.employeePersonManager.getEmploymentStatuses();
			for (EmploymentStatus es : employmentStatuses) {
				employmentStatus.addItem(es);
			}

			fields.get(0).setText(employee.getLastName());
			fields.get(1).setText(employee.getFirstName());
			fields.get(2).setText(employee.getMiddleName());
			fields.get(3).setText(employee.getAddress());
			fields.get(4).setText(employee.getContactNo());

			for (int i = 0; i < designation.getItemCount(); i++) {
				Designation d = (Designation) designation.getItemAt(i);
				if (d.getId() == employee.getId()) {
					designation.setSelectedIndex(i);
					break;
				}
			}

			for (int i = 0; i < employmentStatus.getItemCount(); i++) {
				EmploymentStatus empStatus = (EmploymentStatus) employmentStatus.getItemAt(i);
				if (empStatus.getId() == employee.getStatus().getId()) {
					employmentStatus.setSelectedIndex(i);
					break;
				}
			}

			startDate.setValue(employee.getStartingDate() != null ? employee.getStartingDate() : new Date());
			fields.get(5).setText(String.format("%.2f", employee.getSalary()));
			fields.get(6).setText(employee.getRemarks());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		employmentHistory = new SBButton("employmenthistory.png", "employmenthistory2.png", "Employment History");

		employmentHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				initBalloonTip();

				balloonTip.setVisible(true);
				employmentHistory.setEnabled(false);

			}

		});

		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		startDate = new SpinnerDate("MMMM dd, yyyy");
		startDate.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));

		endDate = new SpinnerDate("MMMM dd, yyyy");
		endDate.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));

		employmentStatus = new FormDropdown(true);
		designation = new FormDropdown(true);

		try {
			model = new DefaultComboBoxModel();
			employmentStatus.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		model = new DefaultComboBoxModel();

		int fieldsCtr = 0, labelsCtr = 0;

		for (int i = 0, y = 0, x1 = 19; i < num; i++, y += 73) {

			if (i == 4) {
				x1 = 254;
				y = 0;
			}

			if (i == 8) {
				x1 = 489;
				y = 0;
			}

			if (i != 5 && i != 6 && i != 7 && i != 10) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Tables.employeeFormLabel[i]));

				fields.get(fieldsCtr).setBounds(x1, initY + y, 170, 25);
				labels.get(labelsCtr).setBounds(x1, y2 + y, 170, 15);

				fieldsCtr++;
				labelsCtr++;
			}

			if (i == 5) {
				designation.setBounds(x1, initY + y, 170, 25);
				labels.add(new FormLabel(Tables.employeeFormLabel[i]));
				labels.get(labelsCtr).setBounds(x1, y2 + y, 170, 15);

				labelsCtr++;

			}

			if (i == 6) {
				employmentStatus.setBounds(x1, initY + y, 170, 25);
				labels.add(new FormLabel(Tables.employeeFormLabel[i]));
				labels.get(labelsCtr).setBounds(x1, y2 + y, 170, 15);
				employmentHistory.setBounds(x1 + 110, y2 + y - 4, 16, 16);

				labelsCtr++;
			}

			if (i == 7) {
				startDate.setBounds(x1, initY + y, 170, 25);
				labels.add(new FormLabel(Tables.employeeFormLabel[i]));
				labels.get(labelsCtr).setBounds(x1, y2 + y, 170, 15);

				labelsCtr++;
			}

			if (i == 10) {
				endDate.setBounds(x1, initY + y, 170, 25);
				labels.add(new FormLabel(Tables.employeeFormLabel[i]));
				labels.get(labelsCtr).setBounds(x1, y2 + y, 170, 15);
				endDate.setVisible(false);
				labels.get(labelsCtr).setVisible(false);

				labelsCtr++;
			}

		}

		for (int i = 0; i < fields.size(); i++)
			panel.add(fields.get(i));

		for (int i = 0; i < labels.size(); i++) {
			panel.add(labels.get(i));
		}

		edit.setBounds(370, 347, 80, 30);

		error.setBounds(160, 290, 230, 25);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				employee.setLastName(fields.get(0).getText());
				employee.setFirstName(fields.get(1).getText());
				employee.setMiddleName(fields.get(2).getText());
				employee.setAddress(fields.get(3).getText());
				employee.setContactNo(fields.get(4).getText());
				employee.setDesignation((Designation) designation.getSelectedItem());
				employee.setStatus((EmploymentStatus) employmentStatus.getSelectedItem());
				employee.setStartingDate(((SpinnerDateModel) startDate.getModel()).getDate());
				employee.setSalary(Double.parseDouble(fields.get(5).getText()));
				employee.setRemarks(fields.get(6).getText());

				try {
					Manager.employeePersonManager.updateEmployee(employee);

					Values.editPanel.startAnimation();
					new SuccessPopup("Edit").setVisible(true);
					Values.centerPanel.changeTable(Values.EMPLOYEES);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		scrollPane.setBounds(68, 27, 685, 290); // 620

		panel.add(designation);
		panel.add(employmentStatus);
		panel.add(endDate);
		panel.add(startDate);

		panel.add(employmentHistory);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane);
		add(edit);
		add(error);

	}

	private void initBalloonTip() {

		String[] employmentHeaders = { "Starting Date", "Designation", "Salary" };
		List<Employee> employments = employee.getPerson().getEmploymentHistory();
		String[][] entries = new String[employments.size()][employmentHeaders.length];
		int i = 0;
		for (Employee e : employments) {
			entries[i][0] = e.getStartingDate() != null ? DateFormatter.getInstance().getFormat(Utility.DMYFormat).format(e.getStartingDate()) : "-";
			entries[i][1] = e.getDesignation().getName();
			entries[i][2] = String.format("%.2f", e.getSalary());
			i++;
		}

		balloonTip = new BalloonTip(employmentHistory, new HistoryTable(employmentHeaders, entries), new RoundedBalloonStyle(7, 7,
				Color.decode("#F5FFFA"), Color.decode("#BDFF59")),// ,
																					// Color.decode("#B2CCCC")),
				BalloonTip.Orientation.RIGHT_ABOVE, BalloonTip.AttachLocation.WEST, 7, 12, false);
		balloonTip.setPadding(5);
		balloonTip.setVisible(false);
		balloonTip.setCloseButton(BalloonTip.getDefaultCloseButton(), false, false);

		balloonTip.getCloseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				employmentHistory.setEnabled(true);
			}
		});
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

	public void closeBalloonPanel() {
		if (balloonTip != null)
			balloonTip.setVisible(false);
	}
}
