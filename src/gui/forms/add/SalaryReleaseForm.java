package gui.forms.add;

import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.FormDropdown;
import gui.forms.util.RowPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.Expense;
import common.entity.product.Product;
import common.entity.profile.Employee;
import common.entity.salary.Fee;
import common.entity.salary.FeeDeduction;
import common.entity.salary.SalaryRelease;
import common.entity.sales.SalesDetail;
import common.manager.Manager;

import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class SalaryReleaseForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel feesPanel;
	private JScrollPane feesPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_HEIGHT = 20, LABEL_Y = 0, UPPER_Y = 63, ITEMS_PANE_Y = 25;
	private Object[] array = {};
	private JScrollBar sb;

	private ArrayList<RowPanel> feesRowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
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
	private SBButton fwd, fwd2;

	public SalaryReleaseForm() {
		super("Add Salary Release Form");
		init();
		addComponents();
		fillEntries();
		Values.salaryReleaseForm = this;
	}

	private void init() {

		fwd = new SBButton("forward.png", "forward.png", "Add new employee");
		fwd2 = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");

		dateLabel = new MainFormLabel("Date:");
		issuedByLabel = new MainFormLabel("Issued by:");
		issuedForLabel = new MainFormLabel("Issued for:");
		salaryLabel = new MainFormLabel("Gross Pay:");
		payLabel = new MainFormLabel("Net Pay:");

		netPay = new DefaultEntryLabel("");
		grossPay = new DefaultEntryLabel("");

		issuedBy = new DefaultEntryLabel("");
		issuedFor = new FormDropdown();

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

		dateLabel.setBounds(70, 50, 40, 20);
		date.setBounds(115, 50, 180, 20);

		issuedByLabel.setBounds(42, 90, 70, 20);
		issuedBy.setBounds(115, 90, 180, 20);

		issuedForLabel.setBounds(40, 130, 80, 20);
		issuedFor.setBounds(115, 130, 180, 20);

		salaryLabel.setBounds(40, 170, 70, 20);
		grossPay.setBounds(115, 170, 180, 20);

		payLabel.setBounds(52, 210, 65, 20);
		netPay.setBounds(115, 210, 180, 20);

		addRow.setBounds(320, 65, 16, 16);

		feesLabel.setBounds(340, 60, 170, 25);
		amountLabel.setBounds(510, 60, 100, 25);
		deleteLabel.setBounds(610, 60, 46, 25);
		feesPane.setBounds(341, 85, 332, 150);

		fwd.setBounds(300, 130, 16, 16);
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

		refreshEmployee();

		issuedFor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Employee emp = (Employee) issuedFor.getSelectedItem();
				grossPay.setText(String.format("%.2f", emp.getSalary()));
			}
		});

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedForLabel);
		panel.add(issuedFor);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(salaryLabel);
		panel.add(grossPay);

		// panel.add(payLabel);
		// panel.add(netPay);

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

		error = new ErrorLabel();

		save.setBounds(310, LABEL_Y + 270, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Date d = ((SpinnerDateModel) date.getModel()).getDate();
				Employee emp = (Employee) issuedFor.getSelectedItem();
				SalaryRelease salaryRelease = new SalaryRelease(d, emp, emp.getSalary(), Manager.loggedInAccount, true, "");

				for (RowPanel rp : feesRowPanel) {
					String f = rp.getSelectedFee();
					Fee fee = null;
					try {
						fee = Manager.salaryReleaseManager.searchFee(f);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (fee == null) {
						try {
							fee = new Fee(f);
							Manager.salaryReleaseManager.addFees(fee);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					salaryRelease.addFeeDeduction(new FeeDeduction(fee, salaryRelease, rp.getFeeAmout()));
				}

				try {
					Manager.salaryReleaseManager.addSalaryRelease(salaryRelease);

					System.out.println("salary release added!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}

			}
		});

		panel.add(save);
		add(error);

	}

	private void fillEntries() {
		issuedBy.setText(Manager.loggedInAccount.getFirstPlusLastName());
		refreshDate();
		refreshEmployee();
	}

	private boolean isValidated() {

		if (feesPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		feesPanel.removeAll();
		feesRowPanel.clear();
		refreshDate();

		error.setText("");

	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshEmployee() {

		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getEmployeesExcludeManagers().toArray());
			issuedFor.setModel(model);
			if (issuedFor.getItemCount() > 0) {
				issuedFor.setSelectedIndex(0);
				grossPay.setText(String.format("%.2f", ((Employee) issuedFor.getSelectedItem()).getSalary()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
