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

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.ErrorLabel;
import util.SimplePanel;
import util.SpinnerDate;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class EmployeeForm extends SimplePanel{

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

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.employeeFormLabel.length;
		
	public EmployeeForm(){
		super("Add Employee");
		addComponents();
	}

	private void addComponents() {
		// TODO Auto-generated method stub
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
		personField = (JTextField) personCombo.getEditor()
				.getEditorComponent();
		personField.setText("");
		personField.addKeyListener(new ComboKeyHandler(personCombo));

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 60) {
			
			if (i == 4) {
				x1 = 245;
				y = 0;
			}

			if (i==1 || i == 4 || i==5) {
				fields.add(new FormField(Tables.employeeFormLabel[i], 100,
						Color.white, Color.gray));
				fields.get(ctr).setBounds(x1, 70 + y, 170, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0)
				personCombo.setBounds(x1, 70 + y, 170, 25);
			
			if (i == 2)
				employmentStatus.setBounds(x1, 70 + y, 170, 25);
			
			if(i==3)
				startDate.setBounds(x1, 70 + y, 170, 25);
			
			if(i==6)
				endDate.setBounds(x1, 70 + y, 170, 25);
			
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

		add(clear);
		add(save);
		
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

		if (!username.equals("") && !password.equals("")
				&& !firstName.equals("") && !lastName.equals("")
				&& !address.equals(""))
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
