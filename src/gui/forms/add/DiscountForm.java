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

import common.entity.discountissue.DiscountIssue;
import common.entity.product.Product;
import common.entity.profile.Person;
import common.manager.Manager;

public class DiscountForm extends SimplePanel {

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
	private JComboBox productCombo, customerCombo;
	private JTextField productComboField, customerComboField;

	private ErrorLabel error;
	private String msg;

	private final int num = Tables.discountFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;

	public DiscountForm() {
		super("Add Discount");
		addComponents();
		fillEntries();

		Values.discountForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		fwd = new SBButton("forward.png", "forward.png", "Add new product");
		fwd2 = new SBButton("forward.png", "forward.png", "Add new customer");
		
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PRODUCTS);
			}
		});

		fwd2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.CUSTOMERS);
			}
		});

		dateLabel = new DropdownLabel("Date");
		issuedByLabel = new DropdownLabel("Issued by");
		productLabel = new DropdownLabel("Product");
		customerLabel = new DropdownLabel("Customer");

		// issuedBy = new FormDropdown();
		issuedBy = new JLabel("");
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

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

		int ctr = 0;
		for (int i = 0, y = 0, x1 = 40; i < num; i++, y += 53) {

			if (i == 4) {

				fields.add(new JNumericField(Tables.discountFormLabel[i] + "*"));
				fields.get(ctr).setMaxLength(10);
				fields.get(ctr).setBounds(x1, initY + y, 200, 25);
				panel.add(fields.get(ctr));

				ctr++;
			}

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 200, 11);
				date.setBounds(x1, initY + y + 5, 200, 20);
				dateStatus.setBounds(x1 + 205, initY + y + 7, 16, 16);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 200, 11);
				issuedBy.setBounds(x1, initY + y + 5, 200, 20);
			}

			if (i == 2) {
				fwd.setBounds(x1 + 43, initY + y - 11, 16, 16);
				productLabel.setBounds(x1, initY + y - 7, 200, 11);
			}

			if (i == 3) {
				fwd2.setBounds(x1 + 53, initY + y - 11, 16, 16);
				customerLabel.setBounds(x1, initY + y - 7, 200, 11);

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
						DiscountIssue discountIssue = new DiscountIssue(d, (Product) productCombo.getSelectedItem(), Double.parseDouble(fields.get(0)
								.getText()), Manager.loggedInAccount, (Person) customerCombo.getSelectedItem(), true, "");

						discountIssue.setRemarks(uP.getInput());
						try {
							Manager.discountIssueManager.addDiscountIssue(discountIssue);
							Values.centerPanel.changeTable(Values.DISCOUNTS);
							new SuccessPopup("Add").setVisible(true);
							clearFields();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
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

		panel.add(issuedBy);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(fwd);
		panel.add(fwd2);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(productLabel);

		panel.add(customerLabel);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(10, 25, 280, 340);

		add(scrollPane);

	}

	private void fillEntries() {

		issuedBy.setText(Manager.loggedInAccount != null ? Manager.loggedInAccount.getFirstPlusLastName() : "");
		refreshDropdown(false);

	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		refreshDropdown(true);
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

		if (remove) {
			panel.remove(customerCombo);
			panel.remove(productCombo);
		}

		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getCustomersOnly().toArray());
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		customerCombo = new JComboBox(model);
		customerCombo.setEditable(true);
		customerComboField = (JTextField) customerCombo.getEditor().getEditorComponent();
		customerComboField.setText("");
		customerComboField.setOpaque(false);
		customerComboField.addKeyListener(new ComboKeyHandler(customerCombo));

		try {
			model = new DefaultComboBoxModel(Manager.productManager.getProducts().toArray());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		productCombo = new JComboBox(model);
		productCombo.setEditable(true);
		productComboField = (JTextField) productCombo.getEditor().getEditorComponent();
		productComboField.setText("");
		productComboField.setOpaque(false);
		productComboField.addKeyListener(new ComboKeyHandler(productCombo));

		productCombo.setSelectedIndex(-1);
		customerCombo.setSelectedIndex(-1);

		productCombo.setBounds(40, 137, 200, 20);
		customerCombo.setBounds(40, 190, 200, 20);

		panel.add(productCombo);
		panel.add(customerCombo);
	}
}
