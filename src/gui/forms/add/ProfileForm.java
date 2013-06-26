package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import util.ErrorLabel;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ProfileForm extends SimplePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown acctType;
	private JComboBox employeeCombo;
	private JTextField employeesField;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.profileFormLabel.length;

	public ProfileForm() {
		super("Add Profile");
		addComponents();

	//	Values.accountForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();


		int ctr = 0;
		for (int i = 0, y = 0, x1 = 50; i < num; i++, y += 52) {

				fields.add(new FormField(Tables.profileFormLabel[i], 100,
						Color.white, Color.gray));
				fields.get(i).setBounds(x1, 55 + y, 200, 25);
				add(fields.get(i));

		}

		clear.setBounds(167, 330, 80, 30);
		save.setBounds(58, 330, 80, 30);

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
			acctType = new FormDropdown();
			acctType.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
