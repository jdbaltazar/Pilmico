package gui.forms.add;

import gui.forms.util.ComboKeyHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.DropdownLabel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.manager.Manager;

public class DepositForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private JSpinner date;
	private JLabel issuedBy;
	private int initY = 26;
	private DropdownLabel dateLabel, issuedByLabel, productLabel, customerLabel;
	private SBButton fwd, fwd2;
	private JComboBox depositorCombo, bankAcctCombo;
	private JTextField depositorComboField, bankAcctComboField;

	private ErrorLabel error;
	private String username, password, firstName, lastName, address;

	private final int num = Tables.depositFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	public DepositForm() {
		super("Add Deposit");
		addComponents();

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();
		
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		fwd = new SBButton("forward.png", "forward.png", "Add new employee");
		fwd2 = new SBButton("forward.png", "forward.png", "Add new bank account");
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		error = new ErrorLabel();

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		productLabel = new DropdownLabel("Depositor*");
		customerLabel = new DropdownLabel("Bank Account*");

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
		
		bankAcctCombo = new JComboBox();
		bankAcctCombo.setEditable(true);
		bankAcctCombo.setSelectedIndex(-1);
		bankAcctComboField = (JTextField) bankAcctCombo.getEditor().getEditorComponent();
		bankAcctComboField.setText("");
		bankAcctComboField.setOpaque(false);
		bankAcctComboField.addKeyListener(new ComboKeyHandler(bankAcctCombo));
		
		depositorCombo = new JComboBox();
		depositorCombo.setEditable(true);
		depositorCombo.setSelectedIndex(-1);
		depositorComboField = (JTextField) depositorCombo.getEditor().getEditorComponent();
		depositorComboField.setText("");
		depositorComboField.setOpaque(false);
		depositorComboField.addKeyListener(new ComboKeyHandler(depositorCombo));

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 53) {

			if (i == 4) {

				fields.add(new JNumericField(Tables.depositFormLabel[i]+"*"));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				panel.add(fields.get(ctr));

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
				fwd.setBounds(x1 + 56, initY + y - 11, 16, 16);
				productLabel.setBounds(x1, initY + y - 7, 200, 11);
				depositorCombo.setBounds(x1, initY + y + 5, 200, 20);
			}
			
			if (i == 3) {
				fwd2.setBounds(x1 + 76, initY + y - 11, 16, 16);
				customerLabel.setBounds(x1, initY + y - 7, 200, 11);
				bankAcctCombo.setBounds(x1, initY + y + 5, 200, 20);
			}
		}

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

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

		panel.add(clear);
		panel.add(save);

		panel.add(issuedBy);
		panel.add(date);

		panel.add(fwd);
		panel.add(fwd2);
		
		panel.add(dateLabel);
		panel.add(issuedByLabel);
		
		panel.add(depositorCombo);
		panel.add(productLabel);
		
		panel.add(bankAcctCombo);
		panel.add(customerLabel);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		scrollPane.setBounds(10, 25, 280, 340);
		
		
		add(scrollPane);

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
			// issuedBy = new FormDropdown();
			// issuedBy.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
