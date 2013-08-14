package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.IconLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.DropdownLabel;
import util.ErrorLabel;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.manager.Manager;

public class CAPaymentForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private ArrayList<JNumericField> fields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private JSpinner date;
	private JLabel issuedBy, caID;
	private int initY = 26;
	private DropdownLabel dateLabel, issuedByLabel, caIDLabel, customerRepLabel;
	private SBButton fwd;
	private JComboBox employeeRepCombo;
	private JTextField employeeRepComboField;

	private ErrorLabel error;
	private String msg;

	private final int num = Tables.ARPaymentFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	private CashAdvance cashAdvance;

	public CAPaymentForm() {
		super("Add CA Payment");
		addComponents();

		Values.caPaymentForm = this;
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
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.EMPLOYEES);
			}
		});

		caIDLabel = new DropdownLabel("CA ID");
		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		customerRepLabel = new DropdownLabel("Employee Representative");

		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

		caID = new JLabel("1");
		caID.setOpaque(false);
		caID.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		caID.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		caID.setHorizontalAlignment(JLabel.CENTER);

		date = new SpinnerDate(Values.dateFormat);

		error = new ErrorLabel();
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), Values.VALID_DATE);
		determineDateStatus();

		date.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				determineDateStatus();
			}
		});

		refreshDropdown(false);

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 53) {

			if (i == 4) {

				fields.add(new JNumericField(Tables.ARPaymentFormLabel[i] + "*"));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				panel.add(fields.get(ctr));

				ctr++;
			}
			if (i == 0) {
				caIDLabel.setBounds(x1, initY + y - 7, 200, 11);
				caID.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 1) {
				dateLabel.setBounds(x1, initY + y - 7, 200, 11);
				date.setBounds(x1, initY + y + 5, 200, 20);
				dateStatus.setBounds(x1 + 205, initY + y + 7, 16, 16);
			}
			if (i == 2) {
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 3) {
				fwd.setBounds(x1 + 129, initY + y - 11, 16, 16);
				customerRepLabel.setBounds(x1, initY + y - 7, 200, 11);
			}
		}

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

		error.setBounds(73, 267, 170, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {
						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						CAPayment caPayment = new CAPayment(cashAdvance, d, Double.parseDouble(fields.get(0).getText()), null, Manager.loggedInAccount,
								true, "");

						caPayment.setRemarks(uP.getInput());
						try {
							Manager.cashAdvanceManager.addCAPayment(caPayment);
							cashAdvance.addCAPayment(caPayment);
							Manager.cashAdvanceManager.updateCashAdvance(cashAdvance);
							if (Values.viewCAForm != null)
								Values.viewCAForm.fillEntries();
							Values.centerPanel.changeTable(Values.CA_PAYMENTS);
							new SuccessPopup("Add").setVisible(true);
							clearFields();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} else
					error.setToolTip(msg);
			}
		});

		panel.add(clear);
		panel.add(save);
		panel.add(error);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(fwd);

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(customerRepLabel);

		panel.add(caIDLabel);
		panel.add(caID);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(10, 25, 280, 340);

		add(scrollPane);

	}

	public void fillEntries(CashAdvance cashAdvance) {
		this.cashAdvance = cashAdvance;
		caID.setText(cashAdvance != null ? cashAdvance.getId() + "" : "");
		date.setValue(new Date());
		issuedBy.setText(Manager.loggedInAccount.getFirstPlusLastName());

		fields.get(0).setText(cashAdvance != null ? cashAdvance.getBalance() + "" : "");
	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setToolTip("");
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {
				String str = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);
				dateStatus.setIconToolTip(new ImageIcon("images/invalid_date2.png"), str, false);
				error.setToolTip(str);
			}

			else {
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), Values.VALID_DATE, true);
				error.setToolTip("");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isValidated() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {

				msg = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);

				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		amount = fields.get(0).getText();

		if (!amount.equals("")) {
			return true;
		}

		msg = "Amount is required ";
		return false;
	}

	public void refreshDropdown(boolean remove) {

		if (remove)
			panel.remove(employeeRepCombo);

		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getPersons().toArray());
			// issuedBy = new FormDropdown();
			// issuedBy.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		employeeRepCombo = new JComboBox(model);
		employeeRepCombo.setEditable(true);
		employeeRepComboField = (JTextField) employeeRepCombo.getEditor().getEditorComponent();
		employeeRepComboField.setText("");
		employeeRepComboField.setOpaque(false);
		employeeRepComboField.addKeyListener(new ComboKeyHandler(employeeRepCombo));

		employeeRepCombo.setSelectedIndex(-1);

		employeeRepCombo.setBounds(40, 190, 200, 20);

		panel.add(employeeRepCombo);
	}

}
