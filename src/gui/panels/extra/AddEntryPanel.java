package gui.panels.extra;

import gui.forms.add.AccountForm;
import gui.forms.add.CashAdvanceForm;
import gui.forms.add.EmployeeForm;
import gui.forms.add.ExpensesForm;
import gui.forms.add.ProductForm;
import gui.forms.add.ProfileForm;
import gui.forms.add.SalesForm;
import gui.forms.add.SalesOrderForm;
import gui.forms.add.StockPurchasePanel;
import gui.forms.add.SupplierForm;
import gui.list.AccountTypeList;
import gui.list.CategoryList;
import gui.list.ConditionList;
import gui.list.LogTypeList;
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

import javax.swing.JCheckBox;

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
	private StockPurchasePanel stockPurchase;
	private SalesForm sales;
	private CashAdvanceForm cashAdvanceForm;
	private ProfileForm profileForm;
	private EmployeeForm employeeForm;
	private ExpensesForm expensesForm;
	
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

		accountForm = new AccountForm();
		productForm = new ProductForm();
		supplierForm = new SupplierForm();
		
		profileForm = new ProfileForm();
		employeeForm = new EmployeeForm();

		accountTypeList = new AccountTypeList();
		// logTypeList = new LogTypeList();
		noteTypeList = new NoteTypeList();

		unitList = new UnitList();
		conditionList = new ConditionList();
		categoryList = new CategoryList();

		stockPurchase = new StockPurchasePanel();
		expensesForm = new ExpensesForm();
		sales = new SalesForm();
		
		cashAdvanceForm = new CashAdvanceForm();
		
		checkBox = new JCheckBox("Close after saving");
		checkBox.setOpaque(false);
		checkBox.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		checkBox.setForeground(new Color(25,25,112));
		checkBox.setFocusPainted(false);
		checkBox.setSelected(true);
	}

	private void addComponents() {
		accountForm.setBounds(225, 45, 300, 390);
		productForm.setBounds(102, 50, 600, 390);
		supplierForm.setBounds(198, 80, 400, 320);
//		stockPurchase.setBounds(180, 40, 450, 400);
		
		sales.setBounds(50, 55, 670, 370);
		expensesForm.setBounds(100, 105, 600, 270);
		
		profileForm.setBounds(225, 45, 290, 390);
		employeeForm.setBounds(165, 50, 450, 400);
		
		cashAdvanceForm.setBounds(225, 40, 300, 395);

		accountTypeList.setBounds(150, 50, 200, 160);
		// logTypeList.setBounds(300, 50, 200, 160);
		// noteTypeList.setBounds(550, 50, 200, 160);
		noteTypeList.setBounds(440, 50, 200, 160); //300

		unitList.setBounds(50, 265, 200, 160);
		conditionList.setBounds(300, 265, 200, 160);
		categoryList.setBounds(550, 265, 200, 160);
		
		checkBox.setBounds(660, 5, 200, 20);

		add(accountForm);
		add(productForm);
		add(supplierForm);
		add(stockPurchase);
		
		add(sales);
		add(expensesForm);
		
		add(cashAdvanceForm);
		
		add(profileForm);
		add(employeeForm);

		add(accountTypeList);
//		add(logTypeList);
		add(noteTypeList);

		add(unitList);
		add(conditionList);
		add(categoryList);
		
		add(checkBox);

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//		gradient = new GradientPaint(0, 0, new Color(169, 169, 169), 0, getHeight(), new Color(40, 40, 40));
		
		gradient = new GradientPaint(0, 0, new Color(240,240,252), 0, getHeight(), new Color(245,245,253));
		
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
		
		else if (Values.tableUtilPanel.getLabel().equals(Tables.PROFILES)) {
			profileForm.setVisible(true);
		}
		
		else if (Values.tableUtilPanel.getLabel().equals(Tables.EMPLOYEES)) {
			employeeForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNTS)) {
			accountForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CASH_ADVANCE)) {
			cashAdvanceForm.setVisible(true);
		}
		
		else if (Values.tableUtilPanel.getLabel().equals(Tables.SUPPLIERS)) {
			supplierForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EXPENSES)) {
			//Values.stockPurchasePanel.refreshDate();
			expensesForm.setVisible(true);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALES)) {
			//Values.salesOrderForm.refreshDate();
			sales.setVisible(true);
		}

	}

	public void showUtilityPanels() {
		accountTypeList.setVisible(true);
		// logTypeList.setVisible(true);
		noteTypeList.setVisible(true);
		unitList.setVisible(true);
		conditionList.setVisible(true);
		categoryList.setVisible(true);
	}

	private void showAllComponents(boolean truth) {
		for (int i = 0; i < getComponentCount(); i++)
			getComponent(i).setVisible(truth);
		
		checkBox.setVisible(true);
	}
	
	public boolean isCloseSelected(){
		return checkBox.isSelected();
	}
}
