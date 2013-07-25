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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.deposit.Bank;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.product.Product;
import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.entity.supplier.Supplier;

import util.Tables;
import util.Values;
import util.soy.SoyPanel;

public class EditPanel extends SoyPanel implements Runnable {

	public static FormField searchField;
	private JLabel search;
	private JComboBox searchOption;

	private Thread thread;
	private int maxWidth = Values.WIDTH - 2;
	private int minWidth = 0;
	private int currWidth = minWidth;
	private boolean isRunning;
	private ImageIcon icon;

	private boolean hide = false;

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

		if (!hide) {
			if (currWidth == minWidth)
				showPanel();
			else
				hidePanel();
		} else {
			hidePanel();
		}
	}

	private void hidePanel() {
		if (currWidth <= minWidth)
			isRunning = false;

		while (isRunning) {
			currWidth--;
			setPrefSize(currWidth, getHeight());
			updateUI();

			if (currWidth <= minWidth)
				isRunning = false;
		}

		if (getComponentCount() != 0)
			remove(getComponent(getComponentCount() - 1));
	}

	private void showPanel() {
		while (isRunning) {
			currWidth++;
			setPrefSize(currWidth, getHeight());
			updateUI();

			if (currWidth >= maxWidth)
				isRunning = false;
		}
	}

	public void showComponent(Object o) {

		if (Values.tableUtilPanel.getLabel().equals(Tables.PRODUCTS)) {
			// add(new EditItemPanel((Item) o), BorderLayout.CENTER);
			add(new EditProductPanel((Product) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNTS)) {
			add(new EditAccountPanel(), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SUPPLIERS)) {
			add(new EditSupplierPanel((Supplier) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALES)) {
			// add(new EditStockPurchase(), BorderLayout.CENTER);
			add(new ViewSalesForm((Sales) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EXPENSES)) {
			add(new ViewExpensesForm((DailyExpenses) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DELIVERIES)) {
			add(new ViewDeliveryForm((Delivery) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.ACCOUNT_RECEIVABLES)) {
			add(new ViewARForm((AccountReceivable) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.PULLOUTS)) {
			add(new ViewPulloutForm((PullOut) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DISCOUNTS)) {
			add(new ViewDiscountForm((DiscountIssue) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.DEPOSITS)) {
			add(new ViewDepositForm((Deposit) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CASH_ADVANCE)) {
			add(new ViewCAForm((CashAdvance) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CA_PAYMENTS)) {
			add(new ViewCAPaymentForm((CAPayment) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.AR_PAYMENTS)) {
			add(new ViewARPaymentForm((ARPayment) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.EMPLOYEES)) {
			add(new EditEmployeeForm((Employee) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.CUSTOMERS)) {
			add(new EditCustomerForm((Person) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.BANK)) {
			add(new EditBankForm((Bank) o), BorderLayout.CENTER);
		}

		else if (Values.tableUtilPanel.getLabel().equals(Tables.SALARY)) {
			add(new ViewSalaryForm((SalaryRelease) o), BorderLayout.CENTER);
		}

	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

}
