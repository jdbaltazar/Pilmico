package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.FormField;
import gui.forms.util.RowPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.plaf.basic.BasicComboBoxUI;

import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.manager.Manager;

import util.ErrorLabel;
import util.FormCheckbox;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class SalesForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel, row, p;
	private JScrollPane productsPane;
	private JComboBox itemCombo, customerCombo;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y, ITEMS_PANE_Y = 116; // 125
	private Object[] array = {};
	private JTextField itemComboField, customerComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel issuedByLabel;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date, issueDate;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel cashier;
	private MainFormField issuedAt, rc_no, receipt_no, remarks;
	private MainFormLabel issuedaTLabel, rcnumLabel, receiptLabel, dateLabel, cashierLabel, customerLabel, issuedOnLabel;

	private DefaultComboBoxModel model;
	private FormCheckbox showRequired;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton customerFwd, productFwd;

	public SalesForm() {
		// TODO Auto-generated constructor stub
		super("Add Sales Form");
		init();
		addComponents();

		Values.salesForm = this;
	};

	private void init() {

		customerFwd = new SBButton("forward.png", "forward.png", "Add new customer");
		productFwd = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		showRequired = new FormCheckbox("Show required fields only");

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");
		issueDate = new SpinnerDate("MMM dd, yyyy hh:mm a");

		issuedaTLabel = new MainFormLabel("Issued at:");
		issuedOnLabel = new MainFormLabel("Issued on:");
		rcnumLabel = new MainFormLabel("RC_No:");
		receiptLabel = new MainFormLabel("Receipt No.:");
		dateLabel = new MainFormLabel("Date:");

		cashierLabel = new MainFormLabel("Cashier:");
		customerLabel = new MainFormLabel("Customer:");

		String c = "";
		if (Manager.loggedInAccount != null)
			c = Manager.loggedInAccount.getEmployee().getFirstPlusLastName();
		cashier = new JLabel(c);
		cashier.setOpaque(false);
		cashier.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		cashier.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		cashier.setHorizontalAlignment(JLabel.CENTER);

		issuedAt = new MainFormField(200);
		rc_no = new MainFormField(20);
		receipt_no = new MainFormField(30);
		remarks = new MainFormField(200);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);
		customerCombo = new JComboBox(model);
		customerCombo.setEditable(true);
		customerCombo.setSelectedIndex(-1);
		customerComboField = (JTextField) customerCombo.getEditor().getEditorComponent();
		customerComboField.setText("");
		customerComboField.setOpaque(false);
		customerComboField.setBorder(BorderFactory.createEmptyBorder());
		customerComboField.addKeyListener(new ComboKeyHandler(customerCombo));

		customerCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		customerCombo.setUI(ColorArrowUI.createUI(this));
		customerCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		customerCombo.setOpaque(false);

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		showRequired.setBounds(500, 12, 160, 20);
		showRequired.setSelected(true);
		showRequired.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (showRequired.isSelected()) {
					showUnrequired(false);
					setupTable(50, false);
				} else {
					showUnrequired(true);
					setupTable(91, true);
				}
			}
		});

		dateLabel.setBounds(15, 12, 40, 20);
		date.setBounds(60, 10, 150, 20);

		cashierLabel.setBounds(260, 12, 60, 20);
		cashier.setBounds(330, 10, 200, 20);

		rcnumLabel.setBounds(15, 35, 50, 20);
		rc_no.setBounds(65, 32, 70, 20);

		receiptLabel.setBounds(145, 35, 100, 20);
		receipt_no.setBounds(235, 32, 140, 20);

		issuedOnLabel.setBounds(385, 35, 70, 20);
		issueDate.setBounds(460, 32, 150, 20);

		customerLabel.setBounds(15, 58, 70, 20);
		customerCombo.setBounds(85, 56, 220, 20);
		customerFwd.setBounds(308, 58, 16, 16);
		
		customerFwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PROFILES);
			}
		});

		issuedaTLabel.setBounds(335, 58, 70, 20);
		issuedAt.setBounds(410, 56, 200, 20);

		setupTable(50, false);
		// issuedByLabel = new JLabel("Issued by:");

		// date.setBounds(510, 12, 150, 20);

		// customerCombo.setBounds(125, UPPER_Y+30, 160, 20);

		// rc_no.setBounds(53, UPPER_Y, 75, 22);
		// receipt_no.setBounds(135, UPPER_Y, 150, 22);

		// issuedByLabel.setBounds(53, UPPER_Y+30, 80, 20);

		// issuedOnLabel.setBounds(380, UPPER_Y, 80, 20);

		// issuedAt.setBounds(380, UPPER_Y+30, 230, 22);

		// issueDate.setBounds(465, UPPER_Y, 150, 20);

		// remarks.setBounds(430, UPPER_Y+245, 200, 22);

		// issuedByLabel.setFont(new Font("Harabara", Font.PLAIN, 16));
		// issuedOnLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowPanel.add(new RowPanel(productsPanel, Values.ADD));
				productsPanel.add(rowPanel.get(rowPanel.size() - 1));
				alternateRows();

				productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
				productsPanel.updateUI();
				productsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) productsPanel.getPreferredSize().getHeight(), 10, 10);
				productsPanel.scrollRectToVisible(rect);
			}
		});

		showUnrequired(false);

		try {
			List<Person> customrs = Manager.employeePersonManager.getCustomersOnly();
			for (Person p : customrs) {
				customerCombo.addItem(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		add(showRequired);

		panel.add(dateLabel);
		panel.add(date);

		panel.add(cashierLabel);
		panel.add(cashier);

		panel.add(rcnumLabel);
		panel.add(rc_no);

		panel.add(receiptLabel);
		panel.add(receipt_no);

		panel.add(issuedOnLabel);
		panel.add(issueDate);

		panel.add(customerLabel);
		panel.add(customerCombo);
		panel.add(customerFwd);

		panel.add(issuedaTLabel);
		panel.add(issuedAt);

		panel.add(productFwd);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		panel.add(addRow);

		panel.add(productsPane);

		/*
		 * panel.add(receipt_no);
		 * 
		 * panel.add(issuedByLabel); panel.add(accountCombo);
		 * 
		 * panel.add(issuedAt);
		 * 
		 * 
		 * panel.add(rc_no); panel.add(remarks);
		 * 
		 * panel.add(issuedOnLabel); panel.add(issueDate);
		 * 
		 * panel.add(quantityKGLabel); panel.add(quantitySACKlabel);
		 * panel.add(priceKG); panel.add(priceSACK); panel.add(productLabel);
		 * panel.add(deleteLabel);
		 * 
		 * panel.add(productsPane);
		 */

		// panel.setBounds(20, 50, 150, 250);

		// panel.setPreferredSize(new Dimension(150, 250));

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(20, 42, 630, 310);

		add(scrollPane);
	}

	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				// rowPanel.get(i).getRow().setBackground(Color.decode("#E6FFFF"));#EBE0FF,F5F0FF
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				// rowPanel.get(i).getRow().setBackground(Color.decode("#CCFFFF"));
				// #E0E0FF,#FFFFCC
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void setupTable(int y, boolean shownFields) {
		LABEL_Y = y;

		addRow.setBounds(12, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 207, LABEL_HEIGHT);
		deleteLabel.setBounds(553, LABEL_Y, 42, LABEL_HEIGHT);

		productFwd.setBounds(482, LABEL_Y + 5, 16, 16);
		productFwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PRODUCTS);
			}
		});

		if (shownFields) {
			productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);
		} else {
			productsPane.setBounds(31, 75, ROW_WIDTH, 175);
		}

	}

	private void showUnrequired(boolean show) {
		customerLabel.setVisible(show);
		customerCombo.setVisible(show);

		rcnumLabel.setVisible(show);
		rc_no.setVisible(show);

		issuedaTLabel.setVisible(show);
		issuedAt.setVisible(show);

		issuedOnLabel.setVisible(show);
		issueDate.setVisible(show);

		receiptLabel.setVisible(show);
		receipt_no.setVisible(show);

		customerFwd.setVisible(show);
	}

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

		productsPanel.remove(rowNum);
		productsPanel.updateUI();
		productsPanel.revalidate();

		productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
		updateList(rowNum);

		alternateRows();
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < rowPanel.size(); i++) {
			rowPanel.get(i).setBounds(0, rowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			rowPanel.get(i).setY(rowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			rowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			rowPanel.get(i).updateUI();
			rowPanel.get(i).revalidate();
		}

		rowPanel.remove(removedRow);

		System.out.println("rowpanel2 size: " + rowPanel.size());
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(275, 275, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Date d = ((SpinnerDateModel) date.getModel()).getDate();
				System.out.println("date: "+d.toString());
				Date d2 = ((SpinnerDateModel) issueDate.getModel()).getDate();
				Person p = null;
				if (customerCombo.getSelectedItem() != null) {
					p = (Person) customerCombo.getSelectedItem();
				}
				Sales s = new Sales(d, p, rc_no.getText(), issuedAt.getText(), d2, receipt_no.getText(), Manager.loggedInAccount);

				for (RowPanel rp : rowPanel) {
					Product product = rp.getSelectedProduct();
					s.addSalesDetail(new SalesDetail(s, product, product.getPricePerKilo(), product.getPricePerSack(), rp.getQuantityInKilo(), rp
							.getQuantityInSack()));
				}

				try {
					Manager.salesManager.addSales(s);

					System.out.println("sales saved!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Sales s = new Sales(d, customer, rcNo, issuedAt, issuedOn,
				// receiptNo, cashier, valid, remarks, accounted);

				// Sales s = new Sales(date, customer, rcNo, issuedAt, issuedOn,
				// receiptNo, cashier, valid, remarks, accounted)

				/*
				 * if (isValidated()) { try { Date d = ((SpinnerDateModel)
				 * date.getModel()).getDate(); Account acc = (Account)
				 * accountCombo.getSelectedItem(); SalesOrder so = new SalesOrder(d,
				 * acc);
				 * 
				 * boolean valid = true;
				 * 
				 * // error-trapping here int i = 0; for (RowPanel sor : rowPanel) {
				 * i++; Item item = (Item) sor.getSelectedItem(); if
				 * (sor.getQuantity() > item.getUnitsOnStock()) {
				 * Toolkit.getDefaultToolkit().beep();
				 * 
				 * JOptionPane.showMessageDialog(null, "Error in row " + i +
				 * ": only " + item.getUnitsOnStock() +" "+ item.getUnit().getName()
				 * + " of item \n" + item.getName() +
				 * " left. Purchase new stocks\nor update quantity of item",
				 * "System Error", JOptionPane.ERROR_MESSAGE);
				 * 
				 * valid = false; } }
				 * 
				 * if (valid) {
				 * 
				 * for (RowPanel sor : rowPanel) { so.addSalesOrderDetail(new
				 * SalesOrderDetail(so, sor.getQuantity(), (Item)
				 * sor.getSelectedItem())); }
				 * 
				 * Manager.salesOrderManager.addSalesOrder(so);
				 * 
				 * // update quantity of items Set<SalesOrderDetail> sods =
				 * so.getSalesOrderDetails(); for (SalesOrderDetail sod : sods) {
				 * Item item = sod.getItem();
				 * item.setUnitsOnStock(item.getUnitsOnStock() - sod.getQuantity());
				 * Manager.itemManager.updateItem(item); }
				 * 
				 * Account ac = Manager.loggedInAccount; String str =
				 * ac.getAccountType() + " " + ac.getFirstAndLAstName() +
				 * " added sales order no " + so.getId() + " for account " +
				 * so.getAccount().getId() + ": " +
				 * so.getAccount().getFirstAndLAstName() + " with " +
				 * so.getSalesOrderDetails().size() + " lines.";
				 * 
				 * String str2 = ""; Set<SalesOrderDetail> ds =
				 * so.getSalesOrderDetails(); if (ds.size() > 0) { str2 =
				 * " Quantity of item(s): "; int total = ds.size(); for
				 * (SalesOrderDetail sod : ds) { total--; str2 = str2 +
				 * sod.getItem().getId(); if (total > 0) { str2 = str2 + ", "; }
				 * else { str2 = str2 + " updated"; } }
				 * 
				 * } str = str + str2; Log log = new Log(str);
				 * Manager.logManager.addLog(log);
				 * 
				 * new SuccessPopup("Add").setVisible(true); clearForm();
				 * Values.centerPanel.changeTable(Values.SALES_ORDER);
				 * Values.topPanel.refreshStockCost();
				 * 
				 * } else { System.out.println("Cannot add sales order! "); }
				 * 
				 * } catch (Exception e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); } } else error.setText(msg);
				 */
			}
		});

		panel.add(save);
		add(error);

	}

	private boolean isValidated() {

		if (customerCombo.getModel().getSelectedItem() == null) {

			msg = "Select an account";

			return false;

		}

		if (productsPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		productsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setText("");

		customerCombo.setSelectedIndex(-1);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

		try {
			// model = new
			// DefaultComboBoxModel(Manager.accountManager.getAccounts().toArray());

			customerCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerCombo.setSelectedIndex(-1);
	}
}
