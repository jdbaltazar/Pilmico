package gui.panels.extra;

import gui.forms.edit.EditAccountPanel;
import gui.forms.edit.EditBankForm;
import gui.forms.edit.EditCustomerForm;
import gui.forms.edit.EditEmployeeForm;
import gui.forms.edit.EditProductPanel;
import gui.forms.edit.EditSupplierPanel;
import gui.forms.edit.ViewARForm;
import gui.forms.edit.ViewARPaymentForm;
import gui.forms.edit.ViewCAForm;
import gui.forms.edit.ViewCAPaymentForm;
import gui.forms.edit.ViewDeliveryForm;
import gui.forms.edit.ViewDepositForm;
import gui.forms.edit.ViewDiscountForm;
import gui.forms.edit.ViewExpensesForm;
import gui.forms.edit.ViewInventorySheetForm;
import gui.forms.edit.ViewPulloutForm;
import gui.forms.edit.ViewSalaryForm;
import gui.forms.edit.ViewSalesForm;
import gui.forms.util.FormField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

import util.Tables;
import util.Values;
import util.soy.SoyPanel;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.deposit.Bank;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.inventorysheet.InventorySheet;
import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.entity.supplier.Supplier;

public class EditPanel extends SoyPanel{// implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2486932009550201039L;
	public static FormField searchField;

//	private Thread thread;
	private int maxWidth = Values.WIDTH - 2;
	private int minWidth = 0;
	private int currWidth = minWidth;
	private JComponent comp;
//	private boolean isRunning;

	private boolean hide = false, ISLinked = false;

	public EditPanel() {
		init();
		// addComponents();
		Values.editPanel = this;
	}

	private void init() {

		setLayout(new BorderLayout());
		setPrefSize(minWidth, getHeight());
		addListener();

	}

	// private void addComponents() {
	// // TODO Auto-generated method stub
	// add(new EditAccountPanel((Account) o), BorderLayout.CENTER);
	// }

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// gradient = new GradientPaint(0, 0, new Color(169,169,169),0,
		// getHeight(), new Color(40,40,40));
		// g2.setPaint(gradient);
		// g2.fill(g.getClipBounds());

		Color ppColor = new Color(0, 96, 96, 30); // r,g,b,alpha
		g2.setColor(ppColor);
		g2.fillRect(0, 0, getWidth(), getHeight());

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
//				if (!isRunning) {
					startAnimation();
//				}
			}
		});
	}

	public void startAnimation() {
//
//		isRunning = true;
//		thread = new Thread(this);
//		thread.start();
		
		if (!hide) {
			if (currWidth == minWidth)
				showPanel();
			else
				hidePanel();
		} else {
			hidePanel();
		}	
	}

/*	@Override
	public void run() {

		if (!hide) {
			if (currWidth == minWidth)
				showPanel();
			else
				hidePanel();
		} else {
			hidePanel();
		}
		
		thread.interrupt();
	}*/

	private void hidePanel() {

		if (Values.editProductPanel != null)
			Values.editProductPanel.closeBalloonPanel();

		if (Values.editEmployeeForm != null)
			Values.editEmployeeForm.closeBalloonPanel();

		if (Values.viewCAForm != null)
			Values.viewCAForm.closeBalloonPanel();

		if (Values.viewARForm != null)
			Values.viewARForm.closeBalloonPanel();

		/*if (currWidth <= minWidth)
			isRunning = false;

		while (isRunning) {
			currWidth--;
			setPrefSize(currWidth, getHeight());
			updateUI();

			if (currWidth <= minWidth)
				isRunning = false;
		}*/
		
		setPrefSize(minWidth, getHeight());
		currWidth = minWidth;
		updateUI();

		comp = null;
		if (getComponentCount() != 0)
			remove(getComponent(getComponentCount() - 1));

		if (ISLinked) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {

					Values.centerPanel.changeTable(Values.INVENTORY_SHEET);
					Values.addEntryPanel.startAnimation();
					Values.addEntryPanel.showComponent();

					ISLinked = false;
				}
			}, 400);
		}
		
	}

	private void showPanel() {
		/*while (isRunning) {
			currWidth++;
			setPrefSize(currWidth, getHeight());
			updateUI();

			if (currWidth >= maxWidth)
				isRunning = false;
		}*/
		
		setPrefSize(maxWidth, getHeight());
		currWidth = maxWidth;
		updateUI();
	}

	public void showComponent(Object o) {

		if (Values.tableUtilPanel.getLabel().equals(Tables.PRODUCTS)) {
			// add(new EditItemPanel((Item) o), BorderLayout.CENTER);
			comp = new EditProductPanel((Product) o);
			
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNTS)) {
			
			comp = new EditAccountPanel((Account) o); 
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SUPPLIERS)) {
			
			comp = new EditSupplierPanel((Supplier) o); 
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALES)) {
			// add(new EditStockPurchase(), BorderLayout.CENTER);
			
			comp = new ViewSalesForm((Sales) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EXPENSES)) {
			
			comp = new ViewExpensesForm((DailyExpenses) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DELIVERIES)) {
			
			comp = new ViewDeliveryForm((Delivery) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNT_RECEIVABLES)) {
			
			comp = new ViewARForm((AccountReceivable) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.PULLOUTS)) {
			
			comp = new ViewPulloutForm((PullOut) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DISCOUNTS)) {
			
			comp = new ViewDiscountForm((DiscountIssue) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DEPOSITS)) {
			
			comp = new ViewDepositForm((Deposit) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CASH_ADVANCE)) {
			
			comp = new ViewCAForm((CashAdvance) o); 
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CA_PAYMENTS)) {
			
			comp = new ViewCAPaymentForm((CAPayment) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.AR_PAYMENTS)) {
			
			comp = new ViewARPaymentForm((ARPayment) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EMPLOYEES)) {
			
			comp = new EditEmployeeForm((Employee) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CUSTOMERS)) {
			
			comp = new EditCustomerForm((Person) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.BANK)) {
			
			comp = new EditBankForm((Bank) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALARY)) {
			
			comp = new ViewSalaryForm((SalaryRelease) o);
			add(comp, BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.INVENTORY_SHEET)) {
			comp = new ViewInventorySheetForm((InventorySheet) o);
			try {
				add(comp, BorderLayout.CENTER);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void showLinkedComponent(Object o, int mode) {

		switch (mode) {

		case Values.SALES:
			add(new ViewSalesForm((Sales) o), BorderLayout.CENTER);
			break;

		case Values.PULLOUT:
			add(new ViewPulloutForm((PullOut) o), BorderLayout.CENTER);
			break;

		case Values.ACCOUNT_RECEIVABLES:
			add(new ViewARForm((AccountReceivable) o), BorderLayout.CENTER);
			break;

		case Values.AR_PAYMENTS:
			add(new ViewARPaymentForm((ARPayment) o), BorderLayout.CENTER);
			break;

		case Values.DELIVERY:
			add(new ViewDeliveryForm((Delivery) o), BorderLayout.CENTER);
			break;

		case Values.CASH_ADVANCE:
			add(new ViewCAForm((CashAdvance) o), BorderLayout.CENTER);
			break;

		case Values.SALARY:
			add(new ViewSalaryForm((SalaryRelease) o), BorderLayout.CENTER);
			break;

		case Values.EXPENSES:
			add(new ViewExpensesForm((DailyExpenses) o), BorderLayout.CENTER);
			break;

		case Values.DISCOUNTS:
			add(new ViewDiscountForm((DiscountIssue) o), BorderLayout.CENTER);
			break;

		case Values.DEPOSITS:
			add(new ViewDepositForm((Deposit) o), BorderLayout.CENTER);
			break;

		case Values.CA_PAYMENTS:
			add(new ViewCAPaymentForm((CAPayment) o), BorderLayout.CENTER);
			break;

		default:
			break;
		}

	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	public void setISLinked(boolean ISLinked){
		this.ISLinked = ISLinked;
	}

}
