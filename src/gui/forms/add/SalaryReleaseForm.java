package gui.forms.add;

import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.FormDropdown;
import gui.forms.util.IconLabel;
import gui.forms.util.RowPanel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.ErrorLabel;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.profile.Employee;
import common.entity.salary.Fee;
import common.entity.salary.FeeDeduction;
import common.entity.salary.SalaryRelease;
import common.manager.Manager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SalaryReleaseForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel feesPanel;
	private JScrollPane feesPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_Y = 0;
	private Object[] array = {};

	private ArrayList<RowPanel> feesRowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel deleteLabel, feesLabel, amountLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private DefaultEntryLabel issuedBy, grossPay, netPay;
	private FormDropdown issuedFor;
	private MainFormLabel issuedByLabel, issuedForLabel, dateLabel, salaryLabel, payLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton fwd;

	public SalaryReleaseForm() {
		super("Add Salary Release Form");
		init();
		addComponents();
		fillEntries();
		Values.salaryReleaseForm = this;
	}

	private void init() {

		fwd = new SBButton("forward.png", "forward.png", "Add new employee");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate(Values.dateFormat);

		error = new ErrorLabel();
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), Values.VALID_DATE);
		determineDateStatus();

		date.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				// System.out.println("Date: "+((SpinnerDateModel)
				// date.getModel()).getDate());
				determineDateStatus();
			}
		});

		dateLabel = new MainFormLabel("Date:");
		issuedByLabel = new MainFormLabel("Issued by:");
		issuedForLabel = new MainFormLabel("Issued for:");
		salaryLabel = new MainFormLabel("Gross Pay:");
		payLabel = new MainFormLabel("Net Pay:");

		netPay = new DefaultEntryLabel("");

		issuedBy = new DefaultEntryLabel("");
		issuedFor = new FormDropdown();

		grossPay = new DefaultEntryLabel("");
		refreshEmployee();

		if (issuedFor.getItemCount() > 0)
			grossPay = new DefaultEntryLabel(String.format("%.2f", ((Employee) issuedFor.getSelectedItem()).getSalary()));
		else
			grossPay = new DefaultEntryLabel("");

		feesLabel = new TableHeaderLabel("Fees");
		amountLabel = new TableHeaderLabel("Amount");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);

		feesPanel = new JPanel();
		feesPanel.setLayout(null);
		feesPanel.setOpaque(false);

		feesPane = new JScrollPane(feesPanel);
		feesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		feesPane.setOpaque(false);
		feesPane.getViewport().setOpaque(false);

		dateLabel.setBounds(70, 55, 40, 20);
		date.setBounds(115, 55, 180, 20);
		dateStatus.setBounds(300, 57, 16, 16);

		issuedByLabel.setBounds(42, 95, 70, 20);
		issuedBy.setBounds(115, 95, 180, 20);

		issuedForLabel.setBounds(40, 135, 80, 20);
		issuedFor.setBounds(115, 135, 180, 20);

		salaryLabel.setBounds(40, 175, 70, 20);
		grossPay.setBounds(115, 175, 180, 20);

		payLabel.setBounds(52, 215, 65, 20);
		netPay.setBounds(115, 215, 180, 20);

		addRow.setBounds(320, 65, 16, 16);

		feesLabel.setBounds(340, 60, 170, 25);
		amountLabel.setBounds(510, 60, 100, 25);
		deleteLabel.setBounds(610, 60, 46, 25);
		feesPane.setBounds(341, 85, 332, 150);

		fwd.setBounds(300, 135, 16, 16);
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.EMPLOYEES);
			}
		});

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				feesRowPanel.add(new RowPanel(feesPanel, Tables.SALARY));
				feesPanel.add(feesRowPanel.get(feesRowPanel.size() - 1));
				alternateRows(true);

				feesPanel.setPreferredSize(new Dimension(237, feesPanel.getComponentCount() * ROW_HEIGHT));
				feesPanel.updateUI();
				feesPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) feesPanel.getPreferredSize().getHeight(), 10, 10);
				feesPanel.scrollRectToVisible(rect);
			}
		});

		issuedFor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Employee emp = (Employee) issuedFor.getSelectedItem();
				grossPay.setToolTip(String.format("%.2f", emp.getSalary()));
			}
		});

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(issuedForLabel);
		panel.add(issuedFor);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(salaryLabel);
		panel.add(grossPay);

		panel.add(payLabel);
		panel.add(netPay);

		panel.add(fwd);

		/*
		 * panel.add(caDeductions); panel.add(dateHeaderLabel);
		 * panel.add(amountLabel); panel.add(cashAdvancesPane);
		 */

		panel.add(addRow);

		panel.add(feesLabel);
		panel.add(amountLabel);
		panel.add(deleteLabel);
		panel.add(feesPane);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(0, 0, 708, 360);

		add(error);
		add(scrollPane);
	}

	private void alternateRows(boolean isForFees) {

		if (isForFees) {
			for (int i = 0; i < feesRowPanel.size(); i++)
				if (i % 2 == 0)
					feesRowPanel.get(i).getRow().setBackground(Values.row1);
				else
					feesRowPanel.get(i).getRow().setBackground(Values.row2);
		}
	}

	public void removeRow(int rowNum) {
		feesPanel.remove(rowNum);
		feesPanel.updateUI();
		feesPanel.revalidate();
		feesPanel.setPreferredSize(new Dimension(237, feesPanel.getComponentCount() * ROW_HEIGHT));
		updateList(rowNum);
		alternateRows(true);
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < feesRowPanel.size(); i++) {
			feesRowPanel.get(i).setBounds(0, feesRowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			feesRowPanel.get(i).setY(feesRowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			feesRowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			feesRowPanel.get(i).updateUI();
			feesRowPanel.get(i).revalidate();
		}

		feesRowPanel.remove(removedRow);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		save.setBounds(310, LABEL_Y + 270, 80, 30);

		error.setBounds(430, 250, 250, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated() && !hasBlankEntry() && !hasZeroQuantity()) {

					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {

						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						Employee emp = (Employee) issuedFor.getSelectedItem();
						SalaryRelease salaryRelease = new SalaryRelease(d, emp, emp.getSalary(), Manager.loggedInAccount, true, "");

						for (RowPanel rp : feesRowPanel) {
							String f = rp.getSelectedFee();
							Fee fee = null;
							try {
								fee = Manager.getInstance().getSalaryReleaseManager().searchFee(f);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (fee == null) {
								try {
									fee = new Fee(f);
									Manager.getInstance().getSalaryReleaseManager().addFees(fee);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							salaryRelease.addFeeDeduction(new FeeDeduction(fee, salaryRelease, rp.getFeeAmout()));
						}

						salaryRelease.setRemarks(uP.getInput());

						try {
							Manager.getInstance().getSalaryReleaseManager().addSalaryRelease(salaryRelease);

							Values.centerPanel.changeTable(Values.SALARY);
							new SuccessPopup("Add").setVisible(true);
							clearForm();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
						}
					}
				} else
					error.setToolTip(msg);

			}
		});

		panel.add(save);
	}

	private void fillEntries() {

		Employee emp = (Employee) issuedFor.getSelectedItem();
		if (emp != null)
			salaryLabel.setToolTipText("To edit salary, go to: Profiles>Employees>" + emp.getFirstPlusLastName());
		issuedBy.setToolTip(Manager.loggedInAccount.getFirstPlusLastName());
		refreshDate();
		refreshEmployee();
	}

	private boolean hasBlankEntry() {

		for (int i = 0; i < feesRowPanel.size(); i++) {
			JTextField field = (JTextField) feesRowPanel.get(i).getFeesCombo().getEditor().getEditorComponent();

			if (field.getText().equals("")) {
				msg = "Blank entry in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {
				String str = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);
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
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {

				msg = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);

				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (issuedFor.getModel().getSelectedItem() == null) {

			msg = "No employee selected ";

			return false;
		}

		return true;
	}

	private boolean hasZeroQuantity() {

		for (int i = 0; i < feesRowPanel.size(); i++) {

			if (feesRowPanel.get(i).getFeeAmout() == 0d) {
				msg = "0 amount found in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private void clearForm() {
		feesPanel.removeAll();
		feesRowPanel.clear();
		refreshDate();

		error.setToolTip("");

		refreshEmployee();
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshEmployee() {

		try {
			model = new DefaultComboBoxModel(Manager.getInstance().getEmployeePersonManager().getEmployedEmployeesExceptManagers().toArray());
			issuedFor.setModel(model);

			if (issuedFor.getItemCount() > 0) {
				issuedFor.setSelectedIndex(0);
				grossPay.setToolTip(String.format("%.2f", ((Employee) issuedFor.getSelectedItem()).getSalary()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
