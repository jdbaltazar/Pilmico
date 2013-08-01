package gui.panels.extra;

import gui.forms.add.ARPaymentForm;
import gui.forms.add.AccountForm;
import gui.forms.add.AccountReceivablesForm;
import gui.forms.add.BankForm;
import gui.forms.add.CAPaymentForm;
import gui.forms.add.CashAdvanceForm;
import gui.forms.add.DeliveryForm;
import gui.forms.add.DepositForm;
import gui.forms.add.DiscountForm;
import gui.forms.add.EmployeeForm;
import gui.forms.add.ExpensesForm;
import gui.forms.add.InventorySheetForm;
import gui.forms.add.ProductForm;
import gui.forms.add.ProfileForm;
import gui.forms.add.PulloutForm;
import gui.forms.add.SalaryReleaseForm;
import gui.forms.add.SalesForm;
import gui.forms.add.SupplierForm;
import gui.list.AccountTypeList;
import gui.list.CategoryList;
import gui.list.ConditionList;
import gui.list.NoteTypeList;
import gui.list.UnitList;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CashAdvance;

import util.Tables;
import util.Values;
import util.soy.SoyPanel;

public class AddEntryPanel extends SoyPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7293947434082230343L;

	private Thread thread;
	private int maxHeight = 475;
	private int minHeight = 0;
	private int currHeight = minHeight;
	private boolean isRunning;
	private AccountForm accountForm;
	private ProductForm productForm;
	private SupplierForm supplierForm;
	private AccountTypeList accountTypeList;
	// private LogTypeList logTypeList;
	private NoteTypeList noteTypeList;
	private UnitList unitList;
	private ConditionList conditionList;
	private CategoryList categoryList;
	private SalesForm sales;
	private CashAdvanceForm cashAdvanceForm;
	private ProfileForm profileForm;
	private EmployeeForm employeeForm;
	private ExpensesForm expensesForm;
	private SalaryReleaseForm salary;
	private AccountReceivablesForm accountReceivables;
	private DeliveryForm delivery;
	private PulloutForm pullout;
	private InventorySheetForm inventorySheetForm;
	private DiscountForm discount;
	private BankForm bankForm;
	private DepositForm deposit;
	private ARPaymentForm arPayment;
	private CAPaymentForm caPayment;

	private JPanel dummy;
	private JScrollPane dummyPane;

	private JPanel motherPanel, motherPanel2;
	
	private ArrayList<JPanel> formPanels = new ArrayList<JPanel>();
	private JLabel backToPrevious;

	private JCheckBox checkBox;

	public AddEntryPanel() {
		init();
		addComponents();

		showAllComponents(false);

		Values.addEntryPanel = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(null);
		setPrefSize(getWidth(), minHeight);
		addListener();

		dummy = new JPanel();
		dummy.setOpaque(false);
		dummy.setLayout(null);

		dummyPane = new JScrollPane(dummy);
		dummyPane.setOpaque(false);
		dummyPane.getViewport().setOpaque(false);
		dummyPane.setBorder(BorderFactory.createEmptyBorder());

		accountForm = new AccountForm();

		productForm = new ProductForm();
		pullout = new PulloutForm();

		delivery = new DeliveryForm();
		supplierForm = new SupplierForm();

		profileForm = new ProfileForm();
		employeeForm = new EmployeeForm();

		accountTypeList = new AccountTypeList();
		// logTypeList = new LogTypeList();
		noteTypeList = new NoteTypeList();

		unitList = new UnitList();
		conditionList = new ConditionList();
		categoryList = new CategoryList();

		expensesForm = new ExpensesForm();
		sales = new SalesForm();

		cashAdvanceForm = new CashAdvanceForm();
		caPayment = new CAPaymentForm();

		salary = new SalaryReleaseForm();

		accountReceivables = new AccountReceivablesForm();
		arPayment = new ARPaymentForm();

		discount = new DiscountForm();

		deposit = new DepositForm();
		bankForm = new BankForm();

		inventorySheetForm = new InventorySheetForm();

		checkBox = new JCheckBox("Close after saving");
		checkBox.setOpaque(false);
		checkBox.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		checkBox.setForeground(new Color(25, 25, 112));
		checkBox.setFocusPainted(false);
		checkBox.setSelected(true);

		backToPrevious = new JLabel("< Back");
		backToPrevious.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		backToPrevious.setForeground(Color.BLUE.darker());
		backToPrevious.addMouseListener(new MouseAdapter() {

			Font original;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void mouseEntered(MouseEvent e) {
				original = e.getComponent().getFont();
				Map attributes = original.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				e.getComponent().setFont(original.deriveFont(attributes));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setFont(original);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				back();
			}
		});

	}

	private void addComponents() {
		accountForm.setBounds(225, 45, 300, 390);

		productForm.setBounds(102, 50, 600, 390);
		pullout.setBounds(70, 76, 670, 330);

		delivery.setBounds(70, 55, 670, 370);
		supplierForm.setBounds(198, 80, 400, 320);
		// stockPurchase.setBounds(180, 40, 450, 400);

		sales.setBounds(70, 55, 670, 370);
		expensesForm.setBounds(100, 105, 600, 270);

		inventorySheetForm.setBounds(10, 30, 780, 430);

		profileForm.setBounds(243, 45, 290, 390);
		employeeForm.setBounds(80, 60, 640, 370);

		// cashAdvanceForm.setBounds(225, 40, 300, 395);
		cashAdvanceForm.setBounds(230, 60, 300, 345);
		caPayment.setBounds(240, 50, 300, 380);

		salary.setBounds(51, 75, 708, 330);

		accountReceivables.setBounds(70, 55, 670, 365);
		arPayment.setBounds(240, 50, 300, 380);

		discount.setBounds(240, 50, 300, 380);

		deposit.setBounds(122, 95, 540, 300);
		bankForm.setBounds(130, 87, 550, 310);

		checkBox.setBounds(660, 5, 200, 20);

		backToPrevious.setBounds(8, 8, 40, 20);
		dummy.add(backToPrevious);

		dummyPane.setBounds(0, 0, 120, 50);

		add(accountForm);

		add(productForm);
		add(pullout);

		add(delivery);
		add(supplierForm);

		add(sales);
		add(expensesForm);

		add(cashAdvanceForm);
		add(caPayment);

		add(salary);

		add(accountReceivables);
		add(arPayment);

		add(discount);

		add(deposit);
		add(bankForm);

		add(inventorySheetForm);

		add(profileForm);
		add(employeeForm);

		add(checkBox);
		add(dummyPane);

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// gradient = new GradientPaint(0, 0, new Color(169, 169, 169), 0,
		// getHeight(), new Color(40, 40, 40));

		gradient = new GradientPaint(0, 0, new Color(240, 240, 252), 0, getHeight(), new Color(245, 245, 253));

		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setColor(new Color(150, 150, 150));
		g2.drawRoundRect(2, 4, getWidth() - 5, getHeight() - 8, 20, 20);

		paintChildren(g2);

		g2.dispose();
		g.dispose();
	}

	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isRunning) {
					startAnimation();
				}
			}
		});
	}

	public void startAnimation() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		if (currHeight == minHeight)
			showPanel();
		else
			hidePanel();
	}

	private void hidePanel() {
		while (isRunning) {
			currHeight--;
			setPrefSize(getWidth(), currHeight);
			// setPrefSize(currWidth, getHeight());
			updateUI();

			if (currHeight == minHeight)
				isRunning = false;
		}
		
		formPanels.clear();
		showAllComponents(false);
		Values.menuPanel.setVisible(true);
	}

	private void showPanel() {

		while (isRunning) {
			currHeight++;
			setPrefSize(getWidth(), currHeight);
			// setPrefSize(currWidth, getHeight());
			updateUI();

			if (currHeight == maxHeight)
				isRunning = false;
		}

		// showAllComponents(false);
	}

	public void showComponent() {

		Values.menuPanel.setVisible(false);

		if (Values.tableUtilPanel.getLabel().equals(Tables.PRODUCTS)) {
			productForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.PULLOUTS)) {
			pullout.setVisible(true);
			formPanels.add(pullout);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CUSTOMERS)) {
			profileForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EMPLOYEES)) {
			employeeForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNTS)) {
			accountForm.setVisible(true);
			formPanels.add(accountForm);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CASH_ADVANCE)) {
			cashAdvanceForm.setVisible(true);
			formPanels.add(cashAdvanceForm);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALARY)) {
			salary.setVisible(true);
			formPanels.add(salary);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNT_RECEIVABLES)) {
			accountReceivables.setVisible(true);
			formPanels.add(accountReceivables);
		}

		/*
		 * else if (Values.tableUtilPanel.getLabel().equals(Tables.AR_PAYMENTS)) {
		 * arPayment.setVisible(true); motherPanel = arPayment; }
		 */

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DISCOUNTS)) {
			discount.setVisible(true);
			formPanels.add(discount);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DEPOSITS)) {
//			deposit.fillEntries();
			deposit.setVisible(true);
			formPanels.add(deposit);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.BANK)) {
			bankForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DELIVERIES)) {
			delivery.setVisible(true);
			formPanels.add(delivery);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SUPPLIERS)) {
			supplierForm.setVisible(true);
			formPanels.add(supplierForm);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EXPENSES)) {
			// Values.stockPurchasePanel.refreshDate();
			expensesForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALES)) {
			// Values.salesOrderForm.refreshDate();
			sales.setVisible(true);
			//motherPanel = sales;
			formPanels.add(sales);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.INVENTORY_SHEET)) {
			// Values.salesOrderForm.refreshDate();
			try {
				inventorySheetForm.build();
				inventorySheetForm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALES)) {
			// Values.salesOrderForm.refreshDate();
			sales.setVisible(true);
			motherPanel = sales;
		}

	}

	public void showPaymentForm(int form, Object parentTransation) {

		Values.menuPanel.setVisible(false);

		switch (form) {

		case Values.CA_PAYMENTS:
			caPayment.fillEntries((CashAdvance) parentTransation);
			caPayment.setVisible(true);
			formPanels.add(caPayment);
			break;

		case Values.AR_PAYMENTS:
			arPayment.fillEntries((AccountReceivable) parentTransation);
			arPayment.setVisible(true);
			formPanels.add(arPayment);
			break;

		default:
			break;
		}
	}

	public void showUtilityPanels() {
		accountTypeList.setVisible(true);
		noteTypeList.setVisible(true);
		unitList.setVisible(true);
		conditionList.setVisible(true);
		categoryList.setVisible(true);
	}

	private void showAllComponents(boolean truth) {
		for (int i = 0; i < getComponentCount(); i++)
			getComponent(i).setVisible(truth);

		checkBox.setSelected(true);
		checkBox.setVisible(true);
	}

	public void linkPanel(int value) {
		
		showAllComponents(false);

		dummyPane.setVisible(true);

		checkBox.setVisible(false);

		switch (value) {

		case Values.PRODUCTS:
			productForm.setVisible(true);
			formPanels.add(productForm);
			break;

		case Values.EMPLOYEES:
			employeeForm.setVisible(true);
			formPanels.add(employeeForm);
			break;

		case Values.CUSTOMERS:
			profileForm.setVisible(true);
			formPanels.add(profileForm);
			break;

		case Values.SUPPLIERS:
			supplierForm.setVisible(true);
			formPanels.add(supplierForm);
			break;

		case Values.BANK:
			bankForm.setVisible(true);
			formPanels.add(bankForm);
			break;

		default:
			break;
		}

	}
	
	public void back(){
		
		showAllComponents(false);
		
		if (formPanels.size() >= 2) {
			formPanels.get(formPanels.size() - 2).setVisible(true);
			
			formPanels.remove(formPanels.size()-1);
		}
		
		if(formPanels.size() > 1){
			dummyPane.setVisible(true);
			checkBox.setVisible(false);
		}
	}

	public JScrollPane getDummyPane() {
		return dummyPane;
	}

	public JPanel motherPanel() {
		return motherPanel;
	}

	public boolean isCloseSelected() {
		return checkBox.isSelected();
	}
}
