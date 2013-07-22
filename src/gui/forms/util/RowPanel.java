package gui.forms.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.entity.dailyexpenses.Expense;
import common.entity.product.Product;
import common.manager.Manager;

import util.JNumericField;
import util.SBButton;
import util.Tables;
import util.Values;

public class RowPanel extends JPanel {

	private int y;
	private String command = "none";

	private JComboBox productsCombo, feesCombo, expensesCombo;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 55;
	private JTextField productsComboField, expensesComboField, feesComboField;
	private JNumericField priceSack, priceKG, quantitySack, quantityKG, amountOfExpense, feeAmount;

	private JButton deleteRow, addRow;
	private JPanel row;
	private FormField bankAccount;

	private RowJLabel date, amount;

	private Object object;
	private String[] stocks;
	private int mode;
	private String label = "";
	private JPanel srcPanel = new JPanel();

	/*
	 * public RowPanel(int y, String command, Item item, int quant, int mode) {
	 * this.y = y; this.command = command; // this.item = item; this.quant =
	 * quant; this.mode = mode; init(); }
	 */

	public RowPanel(JPanel srcPanel, String label) {
		this.srcPanel = srcPanel;
		this.label = label;
		init();
	}

	public RowPanel(Object object, JPanel srcPanel, int mode) {
		this.srcPanel = srcPanel;
		this.object = object;
		this.mode = mode;
		init();
	}

	public RowPanel(int y, String command, int mode) {
		this.y = y;
		this.command = command;
		this.mode = mode;
		init();
	}

	public RowPanel(JPanel srcPanel, int mode) {
		this.srcPanel = srcPanel;
		this.mode = mode;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		command = srcPanel.getComponentCount() + "";
		y = srcPanel.getComponentCount() * ROW_HEIGHT;

		setLayout(new BorderLayout());

		row = new JPanel();
		row.setLayout(null);
		row.setBackground(Color.decode("#FFFFE6"));

		deleteRow = new SBButton("cancel.png", "cancel.png", "Remove");
		// if(mode == Values.EDIT)
		// row.setBorder(BorderFactory.createMatteBorder(0, 1, 1,
		// 1,Color.LIGHT_GRAY));

		// row.setBackground(new Color(245, 245, 220));

		// row.setBorder(BorderFactory.createEtchedBorder());

		try {
			// List<Item> items = Manager.itemManager.getItems();

			// System.out.println("items inputted: " + items.size());

			// stocks = new String[items.size()];
			//
			// for (int i = 0; i < items.size(); i++) {
			// stocks[i] = items.get(i).getName();
			// }
			List<Product> products = new ArrayList<Product>();
			try {
				products = Manager.productManager.getProducts();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			productsCombo = new JComboBox(products.toArray());

			feesCombo = new JComboBox();
			expensesCombo = new JComboBox();

		} catch (Exception e) {
			e.printStackTrace();
		}

		productsCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		productsCombo.setEditable(true);
		productsComboField = (JTextField) productsCombo.getEditor().getEditorComponent();
		productsComboField.setText("");
		productsComboField.addKeyListener(new ComboKeyHandler(productsCombo));

		feesCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		feesCombo.setEditable(true);
		feesComboField = (JTextField) feesCombo.getEditor().getEditorComponent();
		feesComboField.setText("");
		feesComboField.addKeyListener(new ComboKeyHandler(feesCombo));

		expensesCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		expensesCombo.setEditable(true);
		expensesComboField = (JTextField) expensesCombo.getEditor().getEditorComponent();
		expensesComboField.setText("");
		expensesComboField.addKeyListener(new ComboKeyHandler(expensesCombo));

		quantitySack = new JNumericField(10, JNumericField.DECIMAL, true);
		quantityKG = new JNumericField(10, JNumericField.DECIMAL, true);
		priceSack = new JNumericField(10, JNumericField.DECIMAL, true);
		priceKG = new JNumericField(10, JNumericField.DECIMAL, true);
		
		feeAmount = new JNumericField(10, JNumericField.DECIMAL, true);

		amountOfExpense = new JNumericField(10, JNumericField.DECIMAL, true);
		amountOfExpense.setPrecision(2);
		amountOfExpense.setText("0");

		quantitySack.setPrecision(2);
		quantityKG.setPrecision(2);
		priceSack.setPrecision(2);
		priceKG.setPrecision(2);

		priceSack.setEditable(false);
		priceKG.setEditable(false);

		quantitySack.setText("0");
		quantityKG.setText("0");

		bankAccount = new FormField("Account Number", 100);

		productsCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (productsCombo.getSelectedItem() != null) {
					Product p = (Product) productsCombo.getSelectedItem();
					priceSack.setText(p.getCurrentPricePerSack() + "");
					priceKG.setText(p.getCurrentPricePerKilo() + "");
				}
			}
		});

		// quantitySack.setText(quant + "");

		// itemsPanel.setPreferredSize(new Dimension(330,
		// itemsPanel.getComponentCount()
		// * ROW_HEIGHT));
		// Values.tableUtilPanel.getLabel();

		if (mode == Values.EDIT)
			ROW_WIDTH = 335;

		if (label.equals("CADeductions")) {
			addCADeductionsRow();
		} else if (label.equals(Tables.EXPENSES))
			addExpensesRow();
		else if (label.equals(Tables.SALARY))
			addFeesRow();
		else if (label.equals(Tables.BANK))
			addBankAccountRow();
		else
			addProductRow();

		setBounds(0, y, ROW_WIDTH, ROW_HEIGHT);

	}

	private void addCADeductionsRow() {
		ROW_WIDTH = 270;

		date = new RowJLabel("Jul 02, 2013 06:32 PM");
		amount = new RowJLabel("P " + "2500");

		date.setBounds(10, 7, 150, 20);
		amount.setBounds(177, 7, 60, 20);

		row.add(date);
		row.add(amount);

		add(row);
	}

	private void addFeesRow() {
		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Values.salaryReleaseForm.removeRow(Integer.parseInt(e.getActionCommand()));
			}
		});

		feesCombo.setBounds(11, 7, 148, 20);
		feeAmount.setBounds(181, 7, 74, 20);
		deleteRow.setBounds(284, 9, 16, 16); //+33

		row.add(feesCombo);
		row.add(feeAmount);
		row.add(deleteRow);

		add(row);
	}

	private void addBankAccountRow() {
		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Values.bankForm.removeRow(Integer.parseInt(e.getActionCommand()));
			}
		});

		bankAccount.setBounds(21, 7, 160, 20);
		deleteRow.setBounds(214, 9, 16, 16);

		row.add(bankAccount);
		row.add(deleteRow);

		add(row);
	}

	private void addExpensesRow() {
		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Values.expensesForm.removeRow(Integer.parseInt(e.getActionCommand()));
			}
		});

		amountOfExpense.setBounds(10, 7, 52, 20);
		expensesCombo.setBounds(87, 7, 145, 20);
		deleteRow.setBounds(257, 9, 16, 16);

		List<Expense> expenses = new ArrayList<Expense>();
		try {
			expenses = Manager.dailyExpenseManager.getExpenses();

			System.out.println("size of expenseS: " + expenses.size());
			for (Expense e : expenses) {
				expensesCombo.addItem(e);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		row.add(amountOfExpense);
		row.add(expensesCombo);
		row.add(deleteRow);

		add(row);
	}

	private void addProductRow() {
		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (mode) {

				case Values.ADD:
					if (Values.tableUtilPanel.getLabel().contains(Tables.SALES))
						Values.salesForm.removeRow(Integer.parseInt(e.getActionCommand()));

					else if (Values.tableUtilPanel.getLabel().contains(Tables.ACCOUNT_RECEIVABLES))
						Values.accountReceivablesForm.removeRow(Integer.parseInt(e.getActionCommand()));

					else if (Values.tableUtilPanel.getLabel().contains(Tables.PULLOUTS))
						Values.pulloutForm.removeRow(Integer.parseInt(e.getActionCommand()));

					else if (Values.tableUtilPanel.getLabel().contains(Tables.DELIVERIES))
						Values.deliveryForm.removeRow(Integer.parseInt(e.getActionCommand()));
					/*
					 * else Values.stockPurchasePanel.removeRow(Integer.parseInt(e.
					 * getActionCommand()));
					 */
					break;

				case Values.EDIT:
					/*
					 * if (Values.tableUtilPanel.getLabel().contains("SALES ORDER"))
					 * Values
					 * .editSalesOrder.removeRow(Integer.parseInt(e.getActionCommand
					 * ())); else
					 * Values.editStockPurchase.removeRow(Integer.parseInt(
					 * e.getActionCommand()));
					 */
					break;

				default:
					break;
				}

			}
		});

		quantitySack.setBounds(10, 7, 52, 20);
		quantityKG.setBounds(87, 7, 52, 20);
		priceSack.setBounds(167, 7, 57, 20);
		priceKG.setBounds(249, 7, 57, 20);

		if (mode == Values.ADD)
			productsCombo.setBounds(323, 7, 190, 20);
		else
			productsCombo.setBounds(87, 7, 235, 20);

		deleteRow.setBounds(533, 9, 16, 16);

		row.add(quantitySack);
		row.add(quantityKG);
		row.add(priceSack);
		row.add(priceKG);
		row.add(productsCombo);

		if (mode != Values.EDIT)
			row.add(deleteRow);

		productsCombo.setSelectedIndex(-1);

		boolean found = false;
		add(row);
	}

	public JPanel getRow() {
		return row;
	}

	public JButton getDeleteRow() {
		return deleteRow;
	}

	public void setDeleteRow(JButton deleteRow) {
		this.deleteRow = deleteRow;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Product getSelectedProduct() throws ClassCastException {
		return (Product) productsCombo.getSelectedItem();
	}

	public String getSelectedExpense() {
		return expensesComboField.getText();

		// if (expensesCombo.getSelectedIndex() == -1) {
		// System.out.println("new expense!!");
		// return new Expense(expensesComboField.getText());
		// } else {
		// System.out.println("old expense: " + expensesCombo.getSelectedItem());
		// return (Expense) expensesCombo.getSelectedItem();
		// }
	}

	public double getQuantityInSack() {
		return Double.parseDouble(quantitySack.getText());

	}

	public double getQuantityInKilo() {
		return Double.parseDouble(quantityKG.getText());
	}

	public double getExpenseAmount() {
		return Double.parseDouble(amountOfExpense.getText());
	}

	public String getAccountNo() {
		return bankAccount.getText();
	}

}
