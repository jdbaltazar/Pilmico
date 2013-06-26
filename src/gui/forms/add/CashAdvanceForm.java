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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.ErrorLabel;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class CashAdvanceForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown issuedBy, issuedFor;
	private JComboBox employeeCombo;
	private JTextField employeesField;
	private DefaultComboBoxModel model;
	private JSpinner date;
	private JCheckBox paid;
	private int initY = 50;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.cashAdvanceFormLabel.length;

	public CashAdvanceForm() {
		super("Add Cash Advance");
		addComponents();

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();
		
		paid = new JCheckBox("Paid");

		//issuedBy = new FormDropdown();
		
		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date,
				"MMMM dd, yyyy hh:mm:ss a");
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

		try {
			model = new DefaultComboBoxModel();
			issuedBy = new FormDropdown();
			issuedFor = new FormDropdown();
			issuedBy.setModel(model);
			issuedFor.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		/*model = new DefaultComboBoxModel();
		employeeCombo = new JComboBox(model);

		employeeCombo.setSelectedIndex(-1);

		employeeCombo.setEditable(true);
		employeesField = (JTextField) employeeCombo.getEditor()
				.getEditorComponent();
		employeesField.setText("");
		employeesField.addKeyListener(new ComboKeyHandler(employeeCombo));
*/
		int ctr = 0;
		for (int i = 0, y = 0, x1 = 50; i < num; i++, y += 45) {

			if (i == 3 || i==4) {
				
				fields.add(new FormField(Tables.cashAdvanceFormLabel[i], 100,
						Color.white, Color.gray));
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0)
				date.setBounds(x1, initY + y, 200, 25);
			
			if (i == 1)
				issuedBy.setBounds(x1, initY + y, 200, 25);
			
			if (i == 2)
				issuedFor.setBounds(x1, initY + y, 200, 25);
			
			if (i == 5)
				paid.setBounds(x1, initY + y, 100, 25);
			
		}

		clear.setBounds(167, 340, 80, 30);
		save.setBounds(58, 340, 80, 30);

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
		
		add(issuedBy);
		add(paid);
		add(issuedFor);
		add(date);

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
			issuedBy = new FormDropdown();
			issuedBy.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
