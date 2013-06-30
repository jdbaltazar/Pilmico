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
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

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
	private DropdownLabel dateLabel,issuedByLabel, issuedForLabel;
	private SBButton fwd;

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
		
		fwd = new SBButton("forward.png", "forward.png", "Add new employee");

		error = new ErrorLabel();
		
		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		issuedForLabel = new DropdownLabel("Issued for");
		
		//issuedBy = new FormDropdown();
		issuedBy = new JLabel("Juan dela Cruz");
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);
		
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
			issuedFor = new FormDropdown();
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
		for (int i = 0, y = 0, x1 = 51; i < num - 1; i++, y += 55) {

			if (i == 3 || i==4) {
				
				fields.add(new JNumericField(Tables.cashAdvanceFormLabel[i]));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 0){
				dateLabel.setBounds(x1, initY + y - 7, 200, 11);
				date.setBounds(x1, initY + y + 5, 200, 20);
			}
			if (i == 1){
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}
			
			if (i == 2){
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

		if (!username.equals("") && !password.equals("")
				&& !firstName.equals("") && !lastName.equals("")
				&& !address.equals(""))
			return true;

		return false;
	}

	public void refreshDropdown() {
		try {
			model = new DefaultComboBoxModel();
//			issuedBy = new FormDropdown();
//			issuedBy.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
