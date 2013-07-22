package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.FormLabel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import common.entity.profile.EmploymentStatus;
import common.manager.Manager;

import util.DropdownLabel;
import util.EditFormPanel;
import util.ErrorLabel;
import util.SpinnerDate;
import util.Tables;
import util.soy.SoyButton;

public class EditEmployeeForm extends EditFormPanel{

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

	public EditEmployeeForm() {
		super("View / Edit Employee");
		addComponents();
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

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

			if (i!=5 && i != 6 && i != 7 && i != 10) {
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
		
		scrollPane.setBounds(68, 27, 685, 290); //620

		panel.add(designation);
		panel.add(employmentStatus);
		panel.add(endDate);
		panel.add(startDate);
		
		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane);
		add(edit);
		add(error);

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
