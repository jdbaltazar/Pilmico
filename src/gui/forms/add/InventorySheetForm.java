package gui.forms.add;

import gui.forms.util.ISRowPanel;
import gui.forms.util.PDControlScrollPane;
import gui.forms.util.SubTableHeaderLabel;
import gui.forms.util.ViewportDragScrollListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import util.MainFormLabel;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.delivery.Delivery;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.inventorysheet.InventorySheet;
import common.entity.inventorysheet.InventorySheetData;
import common.entity.inventorysheet.InventorySheetDataDetail;
import common.entity.inventorysheet.InventorySheetDetail;
import common.entity.product.Product;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;
import common.manager.Manager;

public class InventorySheetForm extends SimplePanel {

	// 780x430
	private int startX = 15, startY = 17, PANE_WIDTH = 750, PANE_HEIGHT = 380, TOTAL_LABEL_WIDTH = 100, TABLE_GAP = 50, TAB = 150, SECTION_GAP = 120,
			LABEL_GAP = 30, TOTAL_FORMS_OVERALL = 12, SCROLLBAR_WIDTH = 16, ROW_HEIGHT = 30;
	/*
	 * private int startX = 15, startY = 17, PANE_WIDTH = 750, PANE_HEIGHT = 380,
	 * TOTAL_LABEL_WIDTH = 100, TABLE_GAP = 50, TAB = 150, SECTION_GAP = 120,
	 * LABEL_GAP = 30, TOTAL_INVENTORY_LABEL = 16, TOTAL_FORMS_OVERALL = 12,
	 * SCROLLBAR_WIDTH = 16;
	 */
	public static int PRODUCT_LABEL_WIDTH = 160, SACK_LABEL_WIDTH = 70, SALES_KG_WIDTH = 90, TOTAL_INVENTORY_LABEL = 16, PRODUCT_ROW_WIDTH = 1360,
			DATE_LABEL_WIDTH = 150, ISSUED_BY_LABEL_WIDTH = 200, GROSS_LABEL_WIDTH = 100, TRANSACTIONS_ROW_WIDTH = 450;
	private JPanel navigationPanel;

	private ViewportDragScrollListener v1, v2;
	private JViewport view;

	private ArrayList<JLabel> computationLabel = new ArrayList<JLabel>();
	private ArrayList<ISRowPanel> cashBreakdown = new ArrayList<ISRowPanel>();
	private ArrayList<ISRowPanel> productsInventory = new ArrayList<ISRowPanel>();
	private ArrayList<ISRowPanel> salesInventory = new ArrayList<ISRowPanel>();

	private ArrayList<JLabel> sectionLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> totalInventoryLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> formsOverall = new ArrayList<JLabel>();
	private ArrayList<JLabel> summaryValues = new ArrayList<JLabel>();

	private JPanel isPanel, productsPanel, salesPanel, delPanel, arPanel, arPaymentPanel, caPaymentPanel, pulloutPanel, expensesPanel, CAPanel,
			discountPanel, ar2Panel, salaryPanel, depositPanel, cashBreakdownPanel;
	private JScrollPane isPane, salesPane, delPane, arPane, arPaymentPane, caPaymentPane, pulloutPane, expensesPane, CAPane, discountPane, ar2Pane,
			salaryPane, depositPane;

	private PDControlScrollPane productsPane;
	private JLabel actualCashCount;

	private MainFormLabel dateLabel;
	private SpinnerDate date;

	private SoyButton save;

	private InventorySheet inventorySheet;

	private TableHeaderLabel productLabel, sack1Label, kg1Label, sack2Label, kg2Label, sack3Label, kg3Label, sack4Label, kg4Label, sack5Label,
			kg5Label, sack6Label, kg6Label, sack7Label, kg7Label, sack8Label, kg8Label, productTotalLabel, dateSaleslabel, cashierLabel,
			grossSalesLabel, dateDellabel, delReceivedByLabel, grossDelLabel, dateARlabel, arIssuedByLabel, grossARLabel, dateARPaymentlabel,
			arPaymentIssuedByLabel, grossARPaymentLabel, dateCAPaymentlabel, caPaymentIssuedByLabel, grossCAPaymentLabel, datePulloutlabel,
			pulloutIssuedByLabel, grossPulloutLabel, dateExpenseslabel, expensesIssuedByLabel, grossExpensesLabel, dateCAlabel, CAIssuedByLabel,
			grossCALabel, dateDiscountlabel, discountAmountLabel, dateAR2label, ar2IssuedByLabel, grossAR2Label, dateSalarylabel, salaryIssuedForLabel,
			salaryAmountLabel, dateDepositlabel, depositorLabel, depositAmountLabel;
	private SubTableHeaderLabel begInvtyLabel, onDisplayLabel, delLabel, poLabel, endInvtyLabel, offtakeLabel, priceLabel, salesLabel, salesFormLabel,
			overallSalesLabel, arFormLabel, overallARLabel, deliveryFormLabel, overallDelLabel, arPaymentFormLabel, overallARPaymentLabel,
			caPaymentFormLabel, overallCAPaymentLabel, pullOutFormLabel, overallPulloutLabel, expensesFormLabel, overallExpensesLabel, caFormLabel,
			overallCALabel, discountFormLabel, overallDiscountLabel, ar2FormLabel, overallAR2Label, salaryFormLabel, overallSalaryLabel,
			depositFormLabel, overallDepositLabel, assetsLabel, liabilitiesLabel, pcohLabel, cohLabel, summary1Label, summary2Label, summary3Label,
			accLabel;

	public InventorySheetForm() {
		super("Add Inventory Sheet");
		init();
		addComponents();
	}

	private void init() {
		// TODO Auto-generated method stub
		navigationPanel = new JPanel();
		navigationPanel.setLayout(null);
		navigationPanel.setOpaque(false);

		save = new SoyButton("Save");
		save.setBounds(85, 45, 80, 30);

		// navigationPanel.setBackground(Color.red);

		isPanel = new JPanel();
		isPanel.setLayout(null);
		isPanel.setOpaque(false);

		isPanel.setPreferredSize(new Dimension(PANE_WIDTH + 750, PANE_HEIGHT + 2000));

		isPane = new JScrollPane(isPanel);
		isPane.getVerticalScrollBar().setUnitIncrement(20);
		// isPane.setWheelScrollingEnabled(false);
		// isPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		// isPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		v1 = new ViewportDragScrollListener(isPanel);

		view = isPane.getViewport();
		view.addMouseMotionListener(v1);
		view.addMouseListener(v1);
		view.addHierarchyListener(v1);

		dateLabel = new MainFormLabel("Date:");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");

		productLabel = new TableHeaderLabel("Products");

		begInvtyLabel = new SubTableHeaderLabel("BEG. INVTY");
		sack1Label = new TableHeaderLabel("sack");
		kg1Label = new TableHeaderLabel("kilo");

		onDisplayLabel = new SubTableHeaderLabel("ON DISPLAY");
		sack2Label = new TableHeaderLabel("sack");
		kg2Label = new TableHeaderLabel("kilo");

		delLabel = new SubTableHeaderLabel("DEL");
		sack3Label = new TableHeaderLabel("sack");
		kg3Label = new TableHeaderLabel("kilo");

		poLabel = new SubTableHeaderLabel("P/O");
		sack4Label = new TableHeaderLabel("sack");
		kg4Label = new TableHeaderLabel("kilo");

		endInvtyLabel = new SubTableHeaderLabel("E. INVTY");
		sack5Label = new TableHeaderLabel("sack");
		kg5Label = new TableHeaderLabel("kilo");

		offtakeLabel = new SubTableHeaderLabel("OFFTAKE");
		sack6Label = new TableHeaderLabel("sack");
		kg6Label = new TableHeaderLabel("kilo");

		priceLabel = new SubTableHeaderLabel("PRICE");
		sack7Label = new TableHeaderLabel("sack");
		kg7Label = new TableHeaderLabel("kilo");

		salesLabel = new SubTableHeaderLabel("SALES AMOUNT");
		sack8Label = new TableHeaderLabel("sack");
		kg8Label = new TableHeaderLabel("kilo");

		productsPanel = new JPanel();
		productsPanel.setOpaque(false);
		productsPanel.setLayout(null);
		// productsPanel.setPreferredSize(new
		// Dimension(productsPanel.getWidth(),
		// PANE_HEIGHT + 500));

		productsPane = new PDControlScrollPane();
		productsPane.setViewportView(productsPanel);
		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		productsPane.getVerticalScrollBar().setUnitIncrement(20);

		productsPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				int extent = productsPane.getVerticalScrollBar().getModel().getExtent();
				int value = (productsPane.getVerticalScrollBar().getValue() + extent);
				int max = productsPane.getVerticalScrollBar().getMaximum();

				if (value == max || value == extent)
					isPane.setWheelScrollingEnabled(true);
				else
					isPane.setWheelScrollingEnabled(false);

				// System.out.println("Value: " +
				// (productsPane.getVerticalScrollBar().getValue()+extent)
				// + " Max: " +
				// productsPane.getVerticalScrollBar().getMaximum());
			}
		});

		/*
		 * productsPane.addMouseWheelListener(new MouseWheelListener() {
		 * 
		 * @Override public void mouseWheelMoved(MouseWheelEvent e) {
		 * productsPane.getParent().dispatchEvent(e); } });
		 */

		v2 = new ViewportDragScrollListener(isPanel);

		// productsPane.setOpaque(false);
		// productsPane.getViewport().setOpaque(false);
		// productsPane.setBorder(BorderFactory.createEmptyBorder());
		/*
		 * view2 = productsPane.getViewport(); view2.addMouseMotionListener(v2);
		 * view2.addMouseListener(v2); view2.addHierarchyListener(v2);
		 *//*
			 * view2 = productsPane.getViewport(); view2.addMouseListener(v1);
			 * view2.addHierarchyListener(v1);
			 */

		productTotalLabel = new TableHeaderLabel("TOTAL");

		salesFormLabel = new SubTableHeaderLabel("SALES", Color.green.darker());
		dateSaleslabel = new TableHeaderLabel("Date");
		cashierLabel = new TableHeaderLabel("Cashier");
		grossSalesLabel = new TableHeaderLabel("Gross");

		salesPanel = new JPanel();
		salesPanel.setOpaque(false);
		salesPanel.setLayout(null);

		salesPane = new PDControlScrollPane();
		salesPane.setViewportView(salesPanel);
		salesPane.setOpaque(false);
		salesPane.getViewport().setOpaque(false);
		salesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		overallSalesLabel = new SubTableHeaderLabel("OVERALL");

		deliveryFormLabel = new SubTableHeaderLabel("DELIVERIES", Color.green.darker());
		dateDellabel = new TableHeaderLabel("Date");
		delReceivedByLabel = new TableHeaderLabel("Received by");
		grossDelLabel = new TableHeaderLabel("Gross");
		overallDelLabel = new SubTableHeaderLabel("OVERALL");

		delPanel = new JPanel();

		delPane = new JScrollPane(delPanel);

		arFormLabel = new SubTableHeaderLabel("ACCOUNT RECEIVABLES", Color.green.darker());
		dateARlabel = new TableHeaderLabel("Date");
		arIssuedByLabel = new TableHeaderLabel("Issued by");
		grossARLabel = new TableHeaderLabel("Amount");
		overallARLabel = new SubTableHeaderLabel("OVERALL");

		arPanel = new JPanel();

		arPane = new JScrollPane(arPanel);

		arPaymentFormLabel = new SubTableHeaderLabel("ACCOUNT RECEIVABLES PAYMENTS", Color.green.darker());
		dateARPaymentlabel = new TableHeaderLabel("Date");
		arPaymentIssuedByLabel = new TableHeaderLabel("Issued by");
		grossARPaymentLabel = new TableHeaderLabel("Amount");
		overallARPaymentLabel = new SubTableHeaderLabel("OVERALL");
		arPaymentPanel = new JPanel();

		arPaymentPane = new JScrollPane(arPaymentPanel);

		caPaymentFormLabel = new SubTableHeaderLabel("CASH ADVANCE PAYMENTS", Color.green.darker());
		dateCAPaymentlabel = new TableHeaderLabel("Date");
		caPaymentIssuedByLabel = new TableHeaderLabel("Issued by");
		grossCAPaymentLabel = new TableHeaderLabel("Amount");
		overallCAPaymentLabel = new SubTableHeaderLabel("OVERALL");

		caPaymentPanel = new JPanel();

		caPaymentPane = new JScrollPane(caPaymentPanel);

		pullOutFormLabel = new SubTableHeaderLabel("PRODUCT PULLOUTS", Color.red.brighter());
		datePulloutlabel = new TableHeaderLabel("Date");
		pulloutIssuedByLabel = new TableHeaderLabel("Issued by");
		grossPulloutLabel = new TableHeaderLabel("Gross");
		overallPulloutLabel = new SubTableHeaderLabel("OVERALL");

		pulloutPanel = new JPanel();

		pulloutPane = new JScrollPane(pulloutPanel);

		expensesFormLabel = new SubTableHeaderLabel("EXPENSES", Color.red.brighter());
		dateExpenseslabel = new TableHeaderLabel("Date");
		expensesIssuedByLabel = new TableHeaderLabel("Issued by");
		grossExpensesLabel = new TableHeaderLabel("Amount");
		overallExpensesLabel = new SubTableHeaderLabel("OVERALL");

		expensesPanel = new JPanel();

		expensesPane = new JScrollPane(expensesPanel);

		caFormLabel = new SubTableHeaderLabel("CASH ADVANCES", Color.red.brighter());
		dateCAlabel = new TableHeaderLabel("Date");
		CAIssuedByLabel = new TableHeaderLabel("Issued by");
		grossCALabel = new TableHeaderLabel("Amount");
		overallCALabel = new SubTableHeaderLabel("OVERALL");

		CAPanel = new JPanel();

		CAPane = new JScrollPane(CAPanel);

		discountFormLabel = new SubTableHeaderLabel("DISCOUNTS", Color.red.brighter());
		dateDiscountlabel = new TableHeaderLabel("Date");
		discountAmountLabel = new TableHeaderLabel("Amount");
		overallDiscountLabel = new SubTableHeaderLabel("OVERALL");

		discountPanel = new JPanel();

		discountPane = new JScrollPane(discountPanel);

		ar2FormLabel = new SubTableHeaderLabel("ACCOUNT RECEIVABLES", Color.red.brighter());
		dateAR2label = new TableHeaderLabel("Date");
		ar2IssuedByLabel = new TableHeaderLabel("Issued by");
		grossAR2Label = new TableHeaderLabel("Amount");
		overallAR2Label = new SubTableHeaderLabel("OVERALL");

		ar2Panel = new JPanel();

		ar2Pane = new JScrollPane(ar2Panel);

		salaryFormLabel = new SubTableHeaderLabel("SALARY RELEASE", Color.red.brighter());
		dateSalarylabel = new TableHeaderLabel("Date");
		salaryIssuedForLabel = new TableHeaderLabel("Issued for");
		salaryAmountLabel = new TableHeaderLabel("Amount");
		overallSalaryLabel = new SubTableHeaderLabel("OVERALL");

		salaryPanel = new JPanel();

		salaryPane = new JScrollPane(salaryPanel);

		depositFormLabel = new SubTableHeaderLabel("DEPOSITS", Color.red.brighter());
		dateDepositlabel = new TableHeaderLabel("Date");
		depositorLabel = new TableHeaderLabel("Depositor");
		depositAmountLabel = new TableHeaderLabel("Amount");
		overallDepositLabel = new SubTableHeaderLabel("OVERALL");

		depositPanel = new JPanel();

		depositPane = new JScrollPane(depositPanel);

		pcohLabel = new SubTableHeaderLabel("PREVIOUS COH", Color.CYAN.darker());
		assetsLabel = new SubTableHeaderLabel("ASSETS", Color.GREEN.darker());
		liabilitiesLabel = new SubTableHeaderLabel("LIABILITIES", Color.red.brighter());
		cohLabel = new SubTableHeaderLabel("CASH ON HAND");

		cashBreakdownPanel = new JPanel();
		cashBreakdownPanel.setLayout(null);
		cashBreakdownPanel.setOpaque(false);

		for (int i = 0; i < 4; i++) {
			computationLabel.add(new JLabel());
			computationLabel.get(i).setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
			computationLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			computationLabel.get(i).setText("P 2470.65");

			isPanel.add(computationLabel.get(i));
		}

		for (int i = 0; i < 6; i++) {
			sectionLabel.add(new JLabel());
			sectionLabel.get(i).setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Color.decode("#006400")));
			sectionLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			sectionLabel.get(i).setOpaque(true);
			sectionLabel.get(i).setBackground(Color.decode("#DAA520"));
			sectionLabel.get(i).setForeground(Color.white);// Color.decode("#000080"));
			sectionLabel.get(i).setFont(new Font("Harabara", Font.BOLD, 16));
			// sectionLabel.get(i).setText("P 2470.65");

			isPanel.add(sectionLabel.get(i));
		}

		for (int i = 0; i < 4; i++) {
			computationLabel.add(new JLabel());
			computationLabel.get(i).setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
			computationLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			computationLabel.get(i).setBackground(Color.decode("#FFFFE6"));
			computationLabel.get(i).setText("P 2470.65");

			isPanel.add(computationLabel.get(i));
		}

		summary1Label = new SubTableHeaderLabel("CASH ON HAND", Color.decode("#A233A2"));
		summary2Label = new SubTableHeaderLabel("ACTUAL CASH COUNT", Color.decode("#AE4DAE"));
		summary3Label = new SubTableHeaderLabel("", Color.decode("#B966B9"));

		for (int i = 0; i < TOTAL_INVENTORY_LABEL; i++) {
			totalInventoryLabel.add(new JLabel());
			totalInventoryLabel.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
			totalInventoryLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			totalInventoryLabel.get(i).setOpaque(true);
			totalInventoryLabel.get(i).setBackground(Color.decode("#FFFFE6"));
			// sectionLabel.get(i).setText("P 2470.65");

			isPanel.add(totalInventoryLabel.get(i));
		}

		for (int i = 0; i < TOTAL_FORMS_OVERALL; i++) {
			formsOverall.add(new JLabel());
			formsOverall.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
			formsOverall.get(i).setHorizontalAlignment(JLabel.CENTER);
			formsOverall.get(i).setOpaque(true);
			formsOverall.get(i).setBackground(Color.decode("#FFFFE6"));
			// sectionLabel.get(i).setText("P 2470.65");

			isPanel.add(formsOverall.get(i));
		}

		for (int i = 0; i < 3; i++) {
			summaryValues.add(new JLabel());
			summaryValues.get(i).setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
			summaryValues.get(i).setHorizontalAlignment(JLabel.CENTER);
			summaryValues.get(i).setOpaque(true);
			summaryValues.get(i).setBackground(Color.decode("#FFFFE6"));
			// sectionLabel.get(i).setText("P 2470.65");

			isPanel.add(summaryValues.get(i));
		}

		actualCashCount = new JLabel();
		actualCashCount.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
		actualCashCount.setHorizontalAlignment(JLabel.CENTER);
		actualCashCount.setOpaque(true);
		actualCashCount.setBackground(Color.decode("#FFFFE6"));

		accLabel = new SubTableHeaderLabel("ACTUAL CASH COUNT");
	}

	private void addComponents() {
		// TODO Auto-generated method stub

		dateLabel.setBounds(startX, startY, 40, 20);
		date.setBounds(startX + dateLabel.getWidth(), startY + 2, 150, 20);

		sectionLabel.get(0).setText("INVENTORY");
		sectionLabel.get(0).setBounds(startX, dateLabel.getY() + dateLabel.getHeight() + 30, 150, 20);

		productLabel.setBounds(startX, sectionLabel.get(0).getY() + sectionLabel.get(0).getHeight() + LABEL_GAP, PRODUCT_LABEL_WIDTH, 30);

		begInvtyLabel.setBounds(startX + productLabel.getWidth(), productLabel.getY(), 140, 10);
		sack1Label.setBounds(startX + productLabel.getWidth() - 1, productLabel.getY() + begInvtyLabel.getHeight(), begInvtyLabel.getWidth() / 2, 20);
		kg1Label.setBounds(startX + productLabel.getWidth() + sack1Label.getWidth() - 2, productLabel.getY() + begInvtyLabel.getHeight(),
				begInvtyLabel.getWidth() / 2 + 3, 20);

		onDisplayLabel.setBounds(startX + productLabel.getWidth() + begInvtyLabel.getWidth(), productLabel.getY(), 140, 10);
		sack2Label.setBounds(startX + productLabel.getWidth() + begInvtyLabel.getWidth(), productLabel.getY() + onDisplayLabel.getHeight(),
				onDisplayLabel.getWidth() / 2, 20);
		kg2Label.setBounds(startX + productLabel.getWidth() + begInvtyLabel.getWidth() + sack2Label.getWidth() - 1, productLabel.getY()
				+ onDisplayLabel.getHeight(), onDisplayLabel.getWidth() / 2 + 2, 20);

		delLabel.setBounds(startX + productLabel.getWidth() + begInvtyLabel.getWidth() + onDisplayLabel.getWidth(), productLabel.getY(), 140, 10);
		sack3Label
				.setBounds(onDisplayLabel.getX() + onDisplayLabel.getWidth(), productLabel.getY() + delLabel.getHeight(), delLabel.getWidth() / 2, 20);
		kg3Label.setBounds(onDisplayLabel.getX() + onDisplayLabel.getWidth() + sack3Label.getWidth() - 1, productLabel.getY() + delLabel.getHeight(),
				delLabel.getWidth() / 2 + 2, 20);

		poLabel.setBounds(delLabel.getX() + delLabel.getWidth(), productLabel.getY(), 140, 10);
		sack4Label.setBounds(delLabel.getX() + delLabel.getWidth(), productLabel.getY() + delLabel.getHeight(), delLabel.getWidth() / 2, 20);
		kg4Label.setBounds(delLabel.getX() + delLabel.getWidth() + sack3Label.getWidth() - 1, productLabel.getY() + delLabel.getHeight(),
				delLabel.getWidth() / 2 + 2, 20);

		endInvtyLabel.setBounds(poLabel.getX() + poLabel.getWidth(), productLabel.getY(), 140, 10);
		sack5Label.setBounds(poLabel.getX() + poLabel.getWidth(), productLabel.getY() + poLabel.getHeight(), poLabel.getWidth() / 2, 20);
		kg5Label.setBounds(poLabel.getX() + poLabel.getWidth() + sack5Label.getWidth() - 1, productLabel.getY() + poLabel.getHeight(),
				poLabel.getWidth() / 2 + 2, 20);

		offtakeLabel.setBounds(endInvtyLabel.getX() + endInvtyLabel.getWidth(), productLabel.getY(), 140, 10);
		sack6Label.setBounds(endInvtyLabel.getX() + endInvtyLabel.getWidth(), productLabel.getY() + endInvtyLabel.getHeight(),
				endInvtyLabel.getWidth() / 2, 20);
		kg6Label.setBounds(endInvtyLabel.getX() + endInvtyLabel.getWidth() + sack6Label.getWidth() - 1,
				productLabel.getY() + endInvtyLabel.getHeight(), endInvtyLabel.getWidth() / 2 + 2, 20);

		priceLabel.setBounds(offtakeLabel.getX() + offtakeLabel.getWidth(), productLabel.getY(), 180, 10);
		sack7Label.setBounds(offtakeLabel.getX() + offtakeLabel.getWidth(), productLabel.getY() + offtakeLabel.getHeight(), priceLabel.getWidth() / 2,
				20);
		kg7Label.setBounds(offtakeLabel.getX() + offtakeLabel.getWidth() + sack7Label.getWidth() - 1, productLabel.getY() + offtakeLabel.getHeight(),
				priceLabel.getWidth() / 2 + 2, 20);

		salesLabel.setBounds(priceLabel.getX() + priceLabel.getWidth(), productLabel.getY(), 180, 10);
		sack8Label.setBounds(priceLabel.getX() + priceLabel.getWidth(), productLabel.getY() + priceLabel.getHeight(), salesLabel.getWidth() / 2, 20);
		kg8Label.setBounds(priceLabel.getX() + priceLabel.getWidth() + sack8Label.getWidth() - 1, productLabel.getY() + priceLabel.getHeight(),
				salesLabel.getWidth() / 2 + 2, 20);

		productsPane.setBounds(startX + 1, productLabel.getHeight() + productLabel.getY() - 1,
				salesLabel.getX() + salesLabel.getWidth() - productLabel.getX() + SCROLLBAR_WIDTH - 1, 350);

		productTotalLabel.setBounds(startX, productsPane.getY() + productsPane.getHeight(), productLabel.getWidth(), 20);

		// fillTables();
		for (int i = 0, x = 0; i < TOTAL_INVENTORY_LABEL - 4; i++, x += sack1Label.getWidth())
			totalInventoryLabel.get(i).setBounds(productTotalLabel.getX() + productTotalLabel.getWidth() + x - 1, productTotalLabel.getY(),
					sack1Label.getWidth(), 20);

		for (int i = TOTAL_INVENTORY_LABEL - 4, x = 0; i < TOTAL_INVENTORY_LABEL; i++, x += kg7Label.getWidth())
			totalInventoryLabel.get(i).setBounds(kg6Label.getX() + kg6Label.getWidth() + x - 2, productTotalLabel.getY(), kg7Label.getWidth(), 20);

		sectionLabel.get(1).setText("ASSETS");
		sectionLabel.get(1).setBounds(startX, productsPane.getY() + productsPane.getHeight() + SECTION_GAP, 150, 20);

		salesFormLabel.setBounds(startX, sectionLabel.get(1).getY() + sectionLabel.get(1).getHeight() + LABEL_GAP, 450, 18);
		dateSaleslabel.setBounds(startX - 1, salesFormLabel.getY() + salesFormLabel.getHeight(), 150, 20);
		cashierLabel.setBounds(startX + dateSaleslabel.getWidth() - 2, salesFormLabel.getY() + salesFormLabel.getHeight(), salesFormLabel.getWidth()
				- dateSaleslabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossSalesLabel.setBounds(cashierLabel.getX() + cashierLabel.getWidth() - 1, salesFormLabel.getY() + salesFormLabel.getHeight(),
				salesFormLabel.getWidth() - (dateSaleslabel.getWidth() + cashierLabel.getWidth()) + 4, 20);
		salesPane.setBounds(startX, dateSaleslabel.getY() + dateSaleslabel.getHeight() - 1, salesFormLabel.getWidth() + SCROLLBAR_WIDTH, 105);
		overallSalesLabel.setBounds(startX, salesPane.getY() + salesPane.getHeight(), 200, 15);
		formsOverall.get(0).setText("P 15890.50");
		formsOverall.get(0).setBounds(overallSalesLabel.getX() + overallSalesLabel.getWidth(), overallSalesLabel.getY(),
				salesPane.getWidth() - overallSalesLabel.getWidth(), 15);

		deliveryFormLabel.setBounds(startX + salesFormLabel.getWidth() + TABLE_GAP, salesFormLabel.getY(), 450, 18);
		dateDellabel.setBounds(deliveryFormLabel.getX() - 1, deliveryFormLabel.getY() + deliveryFormLabel.getHeight(), 150, 20);
		delReceivedByLabel.setBounds(deliveryFormLabel.getX() + dateDellabel.getWidth() - 2, deliveryFormLabel.getY() + deliveryFormLabel.getHeight(),
				deliveryFormLabel.getWidth() - dateDellabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossDelLabel.setBounds(delReceivedByLabel.getX() + delReceivedByLabel.getWidth() - 1,
				deliveryFormLabel.getY() + deliveryFormLabel.getHeight(),
				deliveryFormLabel.getWidth() - (dateDellabel.getWidth() + delReceivedByLabel.getWidth()) + 4, 20);
		delPane.setBounds(startX + salesFormLabel.getWidth() + TABLE_GAP, dateDellabel.getY() + dateDellabel.getHeight() - 1,
				salesFormLabel.getWidth(), 105);
		overallDelLabel.setBounds(startX + salesFormLabel.getWidth() + TABLE_GAP, delPane.getY() + delPane.getHeight(), 200, 15);
		formsOverall.get(1).setBounds(overallDelLabel.getX() + overallDelLabel.getWidth(), overallDelLabel.getY(),
				delPane.getWidth() - overallDelLabel.getWidth(), 15);

		arFormLabel.setBounds(deliveryFormLabel.getX() + deliveryFormLabel.getWidth() + TABLE_GAP, salesFormLabel.getY(), 450, 18);
		dateARlabel.setBounds(arFormLabel.getX() - 1, arFormLabel.getY() + arFormLabel.getHeight(), 150, 20);
		arIssuedByLabel.setBounds(arFormLabel.getX() + dateARlabel.getWidth() - 2, arFormLabel.getY() + arFormLabel.getHeight(), arFormLabel.getWidth()
				- dateARlabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossARLabel.setBounds(arIssuedByLabel.getX() + arIssuedByLabel.getWidth() - 1, arFormLabel.getY() + arFormLabel.getHeight(),
				arFormLabel.getWidth() - (dateARlabel.getWidth() + arIssuedByLabel.getWidth()) + 4, 20);
		arPane.setBounds(deliveryFormLabel.getX() + deliveryFormLabel.getWidth() + TABLE_GAP, dateARlabel.getY() + dateARlabel.getHeight() - 1,
				arFormLabel.getWidth(), 105);
		overallARLabel.setBounds(deliveryFormLabel.getX() + deliveryFormLabel.getWidth() + TABLE_GAP, delPane.getY() + delPane.getHeight(), 200, 15);
		formsOverall.get(2).setBounds(overallARLabel.getX() + overallARLabel.getWidth(), overallARLabel.getY(),
				arPane.getWidth() - overallARLabel.getWidth(), 15);

		arPaymentFormLabel.setBounds(startX + TAB, overallARLabel.getY() + overallARLabel.getHeight() + 70, 450, 18);
		dateARPaymentlabel.setBounds(arPaymentFormLabel.getX() - 1, arPaymentFormLabel.getY() + arFormLabel.getHeight(), 150, 20);
		arPaymentIssuedByLabel.setBounds(arPaymentFormLabel.getX() + dateARPaymentlabel.getWidth() - 2,
				arPaymentFormLabel.getY() + arPaymentFormLabel.getHeight(), arPaymentFormLabel.getWidth() - dateARPaymentlabel.getWidth() + 2
						- TOTAL_LABEL_WIDTH, 20);
		grossARPaymentLabel.setBounds(arPaymentIssuedByLabel.getX() + arPaymentIssuedByLabel.getWidth() - 1, arPaymentFormLabel.getY()
				+ arPaymentFormLabel.getHeight(),
				arPaymentFormLabel.getWidth() - (dateARPaymentlabel.getWidth() + arPaymentIssuedByLabel.getWidth()) + 4, 20);
		arPaymentPane.setBounds(startX + TAB, dateARPaymentlabel.getY() + dateARPaymentlabel.getHeight() - 1, arPaymentFormLabel.getWidth(), 105);
		overallARPaymentLabel.setBounds(startX + TAB, arPaymentPane.getY() + arPaymentPane.getHeight(), 200, 15);
		formsOverall.get(3).setBounds(overallARPaymentLabel.getX() + overallARPaymentLabel.getWidth(), overallARPaymentLabel.getY(),
				arPaymentPane.getWidth() - overallARPaymentLabel.getWidth(), 15);

		caPaymentFormLabel.setBounds(arPaymentFormLabel.getX() + arPaymentFormLabel.getWidth() + TABLE_GAP + TAB, arPaymentFormLabel.getY(), 450, 18);
		dateCAPaymentlabel.setBounds(caPaymentFormLabel.getX() - 1, caPaymentFormLabel.getY() + arFormLabel.getHeight(), 150, 20);
		caPaymentIssuedByLabel.setBounds(caPaymentFormLabel.getX() + dateCAPaymentlabel.getWidth() - 2,
				caPaymentFormLabel.getY() + caPaymentFormLabel.getHeight(), caPaymentFormLabel.getWidth() - dateCAPaymentlabel.getWidth() + 2
						- TOTAL_LABEL_WIDTH, 20);
		grossCAPaymentLabel.setBounds(caPaymentIssuedByLabel.getX() + caPaymentIssuedByLabel.getWidth() - 1, caPaymentFormLabel.getY()
				+ arPaymentFormLabel.getHeight(),
				caPaymentFormLabel.getWidth() - (dateCAPaymentlabel.getWidth() + caPaymentIssuedByLabel.getWidth()) + 4, 20);
		caPaymentPane.setBounds(caPaymentFormLabel.getX(), dateCAPaymentlabel.getY() + dateCAPaymentlabel.getHeight() - 1,
				caPaymentFormLabel.getWidth(), 105);
		overallCAPaymentLabel.setBounds(caPaymentFormLabel.getX(), caPaymentPane.getY() + caPaymentPane.getHeight(), 200, 15);
		formsOverall.get(4).setBounds(overallCAPaymentLabel.getX() + overallCAPaymentLabel.getWidth(), overallCAPaymentLabel.getY(),
				caPaymentPane.getWidth() - overallCAPaymentLabel.getWidth(), 15);

		sectionLabel.get(2).setText("LIABILITIES");
		sectionLabel.get(2).setBounds(startX, overallCAPaymentLabel.getY() + overallCAPaymentLabel.getHeight() + SECTION_GAP, 150, 20);

		pullOutFormLabel.setBounds(startX + TAB, sectionLabel.get(2).getY() + sectionLabel.get(2).getHeight() + LABEL_GAP, 450, 18);
		datePulloutlabel.setBounds(pullOutFormLabel.getX() - 1, pullOutFormLabel.getY() + pullOutFormLabel.getHeight(), 150, 20);
		pulloutIssuedByLabel.setBounds(pullOutFormLabel.getX() + datePulloutlabel.getWidth() - 2,
				pullOutFormLabel.getY() + pullOutFormLabel.getHeight(),
				pullOutFormLabel.getWidth() - datePulloutlabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossPulloutLabel.setBounds(pulloutIssuedByLabel.getX() + pulloutIssuedByLabel.getWidth() - 1,
				pullOutFormLabel.getY() + pullOutFormLabel.getHeight(),
				pullOutFormLabel.getWidth() - (datePulloutlabel.getWidth() + pulloutIssuedByLabel.getWidth()) + 4, 20);
		pulloutPane.setBounds(pullOutFormLabel.getX(), datePulloutlabel.getY() + datePulloutlabel.getHeight() - 1, pullOutFormLabel.getWidth(), 105);
		overallPulloutLabel.setBounds(pullOutFormLabel.getX(), pulloutPane.getY() + pulloutPane.getHeight(), 200, 15);
		formsOverall.get(5).setBounds(overallPulloutLabel.getX() + overallPulloutLabel.getWidth(), overallPulloutLabel.getY(),
				pulloutPane.getWidth() - overallPulloutLabel.getWidth(), 15);

		expensesFormLabel.setBounds(pullOutFormLabel.getX() + pullOutFormLabel.getWidth() + TABLE_GAP + TAB, pullOutFormLabel.getY(), 450, 18);
		dateExpenseslabel.setBounds(expensesFormLabel.getX() - 1, expensesFormLabel.getY() + arFormLabel.getHeight(), 150, 20);
		expensesIssuedByLabel.setBounds(expensesFormLabel.getX() + dateExpenseslabel.getWidth() - 2,
				expensesFormLabel.getY() + expensesFormLabel.getHeight(), expensesFormLabel.getWidth() - dateExpenseslabel.getWidth() + 2
						- TOTAL_LABEL_WIDTH, 20);
		grossExpensesLabel.setBounds(expensesIssuedByLabel.getX() + expensesIssuedByLabel.getWidth() - 1,
				expensesFormLabel.getY() + expensesFormLabel.getHeight(), expensesFormLabel.getWidth()
						- (dateExpenseslabel.getWidth() + expensesIssuedByLabel.getWidth()) + 4, 20);
		expensesPane.setBounds(expensesFormLabel.getX(), dateExpenseslabel.getY() + dateExpenseslabel.getHeight() - 1, expensesFormLabel.getWidth(),
				105);
		overallExpensesLabel.setBounds(expensesFormLabel.getX(), expensesPane.getY() + expensesPane.getHeight(), 200, 15);
		formsOverall.get(6).setBounds(overallExpensesLabel.getX() + overallExpensesLabel.getWidth(), overallExpensesLabel.getY(),
				expensesPane.getWidth() - overallExpensesLabel.getWidth(), 15);

		caFormLabel.setBounds(startX, overallExpensesLabel.getY() + overallExpensesLabel.getHeight() + 70, 450, 18);
		dateCAlabel.setBounds(caFormLabel.getX() - 1, caFormLabel.getY() + caFormLabel.getHeight(), 150, 20);
		CAIssuedByLabel.setBounds(caFormLabel.getX() + dateCAlabel.getWidth() - 2, caFormLabel.getY() + caFormLabel.getHeight(), caFormLabel.getWidth()
				- dateCAlabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossCALabel.setBounds(CAIssuedByLabel.getX() + CAIssuedByLabel.getWidth() - 1, caFormLabel.getY() + caFormLabel.getHeight(),
				caFormLabel.getWidth() - (dateCAlabel.getWidth() + CAIssuedByLabel.getWidth()) + 4, 20);
		CAPane.setBounds(caFormLabel.getX(), dateCAlabel.getY() + dateCAlabel.getHeight() - 1, caFormLabel.getWidth(), 105);
		overallCALabel.setBounds(caFormLabel.getX(), CAPane.getY() + CAPane.getHeight(), 200, 15);
		formsOverall.get(7).setBounds(overallCALabel.getX() + overallCALabel.getWidth(), overallCALabel.getY(),
				CAPane.getWidth() - overallCALabel.getWidth(), 15);

		discountFormLabel.setBounds(caFormLabel.getX() + caFormLabel.getWidth() + 100, overallExpensesLabel.getY() + overallExpensesLabel.getHeight()
				+ 70, 250, 18);
		dateDiscountlabel.setBounds(discountFormLabel.getX() - 1, discountFormLabel.getY() + discountFormLabel.getHeight(), 150, 20);
		discountAmountLabel.setBounds(discountFormLabel.getX() + dateDiscountlabel.getWidth() - 2,
				discountFormLabel.getY() + discountFormLabel.getHeight(), discountFormLabel.getWidth() - dateDiscountlabel.getWidth() + 3, 20);
		discountPane.setBounds(discountFormLabel.getX(), dateDiscountlabel.getY() + dateDiscountlabel.getHeight() - 1, discountFormLabel.getWidth(),
				105);
		overallDiscountLabel.setBounds(discountFormLabel.getX(), discountPane.getY() + discountPane.getHeight(), 100, 15);
		formsOverall.get(8).setBounds(overallDiscountLabel.getX() + overallDiscountLabel.getWidth(), overallDiscountLabel.getY(),
				discountPane.getWidth() - overallDiscountLabel.getWidth(), 15);

		ar2FormLabel.setBounds(discountFormLabel.getX() + discountFormLabel.getWidth() + 100, caFormLabel.getY(), 450, 18);
		dateAR2label.setBounds(ar2FormLabel.getX() - 1, ar2FormLabel.getY() + ar2FormLabel.getHeight(), 150, 20);
		ar2IssuedByLabel.setBounds(ar2FormLabel.getX() + dateAR2label.getWidth() - 2, ar2FormLabel.getY() + ar2FormLabel.getHeight(),
				ar2FormLabel.getWidth() - dateAR2label.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		grossAR2Label.setBounds(ar2IssuedByLabel.getX() + ar2IssuedByLabel.getWidth() - 1, ar2FormLabel.getY() + ar2FormLabel.getHeight(),
				ar2FormLabel.getWidth() - (dateAR2label.getWidth() + ar2IssuedByLabel.getWidth()) + 4, 20);
		ar2Pane.setBounds(ar2FormLabel.getX(), dateAR2label.getY() + dateAR2label.getHeight() - 1, ar2FormLabel.getWidth(), 105);
		overallAR2Label.setBounds(ar2FormLabel.getX(), ar2Pane.getY() + ar2Pane.getHeight(), 200, 15);
		formsOverall.get(9).setBounds(overallAR2Label.getX() + overallAR2Label.getWidth(), overallAR2Label.getY(),
				ar2Pane.getWidth() - overallAR2Label.getWidth(), 15);

		salaryFormLabel.setBounds(startX + TAB, overallAR2Label.getY() + overallAR2Label.getHeight() + 70, 450, 18);
		dateSalarylabel.setBounds(salaryFormLabel.getX() - 1, salaryFormLabel.getY() + salaryFormLabel.getHeight(), 150, 20);
		salaryIssuedForLabel.setBounds(salaryFormLabel.getX() + datePulloutlabel.getWidth() - 2, salaryFormLabel.getY() + salaryFormLabel.getHeight(),
				salaryFormLabel.getWidth() - dateSalarylabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		salaryAmountLabel.setBounds(salaryIssuedForLabel.getX() + salaryIssuedForLabel.getWidth() - 1,
				salaryFormLabel.getY() + salaryFormLabel.getHeight(),
				salaryFormLabel.getWidth() - (dateSalarylabel.getWidth() + salaryIssuedForLabel.getWidth()) + 4, 20);
		salaryPane.setBounds(salaryFormLabel.getX(), dateSalarylabel.getY() + dateSalarylabel.getHeight() - 1, salaryFormLabel.getWidth(), 105);
		overallSalaryLabel.setBounds(salaryFormLabel.getX(), salaryPane.getY() + salaryPane.getHeight(), 200, 15);
		formsOverall.get(10).setBounds(overallSalaryLabel.getX() + overallSalaryLabel.getWidth(), overallSalaryLabel.getY(),
				salaryPane.getWidth() - overallSalaryLabel.getWidth(), 15);

		depositFormLabel.setBounds(salaryFormLabel.getX() + salaryFormLabel.getWidth() + TABLE_GAP + TAB, salaryFormLabel.getY(), 450, 18);
		dateDepositlabel.setBounds(depositFormLabel.getX() - 1, depositFormLabel.getY() + depositFormLabel.getHeight(), 150, 20);
		depositorLabel.setBounds(depositFormLabel.getX() + dateDepositlabel.getWidth() - 2, depositFormLabel.getY() + depositFormLabel.getHeight(),
				depositFormLabel.getWidth() - dateDepositlabel.getWidth() + 2 - TOTAL_LABEL_WIDTH, 20);
		depositAmountLabel.setBounds(depositorLabel.getX() + depositorLabel.getWidth() - 1, depositFormLabel.getY() + depositFormLabel.getHeight(),
				depositFormLabel.getWidth() - (dateExpenseslabel.getWidth() + depositorLabel.getWidth()) + 4, 20);
		depositPane.setBounds(depositFormLabel.getX(), dateDepositlabel.getY() + dateDepositlabel.getHeight() - 1, depositFormLabel.getWidth(), 105);
		overallDepositLabel.setBounds(depositFormLabel.getX(), depositPane.getY() + depositPane.getHeight(), 200, 15);
		formsOverall.get(11).setBounds(overallDepositLabel.getX() + overallDepositLabel.getWidth(), overallDepositLabel.getY(),
				depositPane.getWidth() - overallDepositLabel.getWidth(), 15);

		sectionLabel.get(3).setText("CASH ON HAND");
		sectionLabel.get(3).setBounds(startX, overallDepositLabel.getY() + overallDepositLabel.getHeight() + SECTION_GAP, 150, 20);

		pcohLabel.setBounds(startX + TAB, sectionLabel.get(3).getY() + sectionLabel.get(3).getHeight() + LABEL_GAP, 150, 25);
		assetsLabel.setBounds(pcohLabel.getX(), pcohLabel.getY() + pcohLabel.getHeight(), 150, 25);
		liabilitiesLabel.setBounds(pcohLabel.getX(), assetsLabel.getY() + assetsLabel.getHeight(), 150, 25);
		cohLabel.setBounds(pcohLabel.getX(), liabilitiesLabel.getY() + liabilitiesLabel.getHeight(), 150, 30);

		computationLabel.get(0).setBounds(pcohLabel.getX() + pcohLabel.getWidth() - 1, pcohLabel.getY(), 200, 26);
		computationLabel.get(1).setBounds(computationLabel.get(0).getX(), assetsLabel.getY(), 200, 26);
		computationLabel.get(2).setBounds(computationLabel.get(0).getX(), liabilitiesLabel.getY(), 200, 26);
		computationLabel.get(3).setBounds(computationLabel.get(0).getX(), cohLabel.getY(), 200, 30);

		cashBreakdownPanel.setBounds(computationLabel.get(0).getX() + computationLabel.get(0).getWidth() + TABLE_GAP + TAB + TAB + 50, sectionLabel
				.get(3).getY() + sectionLabel.get(3).getHeight() + LABEL_GAP, 340, 210);

		sectionLabel.get(4).setText("CASH BREAKDOWN");
		sectionLabel.get(4).setBounds(cashBreakdownPanel.getX() - TAB, overallDepositLabel.getY() + overallDepositLabel.getHeight() + SECTION_GAP, 150,
				20);

		for (int i = 0; i < 7; i++) {
			cashBreakdown.add(new ISRowPanel(null, cashBreakdownPanel, -1));
			cashBreakdownPanel.add(cashBreakdown.get(i));

			cashBreakdownPanel.updateUI();
			cashBreakdownPanel.revalidate();
		}

		accLabel.setBounds(cashBreakdownPanel.getX(), cashBreakdownPanel.getHeight() + cashBreakdownPanel.getY(), 200, 30);
		actualCashCount.setBounds(accLabel.getX() + accLabel.getWidth(), accLabel.getY(), cashBreakdownPanel.getWidth() - accLabel.getWidth(), 30);

		sectionLabel.get(5).setText("SUMMARY");
		sectionLabel.get(5).setBounds(startX, cohLabel.getY() + cohLabel.getHeight() + SECTION_GAP, 150, 20);

		summary1Label.setBounds(startX + TAB, sectionLabel.get(5).getY() + sectionLabel.get(5).getHeight() + LABEL_GAP, 200, 25);
		summary2Label.setBounds(summary1Label.getX() + summary1Label.getWidth(), summary1Label.getY(), 200, 25);
		summary3Label.setBounds(summary2Label.getX() + summary2Label.getWidth(), summary1Label.getY(), 201, 25);

		for (int i = 0, x = 0; i < 3; i++, x += summary1Label.getWidth())
			summaryValues.get(i).setBounds(summary1Label.getX() + x, summary1Label.getY() + summary1Label.getHeight(), 201, 30);

		isPanel.add(dateLabel);
		isPanel.add(date);

		isPanel.add(productLabel);

		isPanel.add(begInvtyLabel);
		isPanel.add(sack1Label);
		isPanel.add(kg1Label);

		isPanel.add(onDisplayLabel);
		isPanel.add(sack2Label);
		isPanel.add(kg2Label);

		isPanel.add(delLabel);
		isPanel.add(sack3Label);
		isPanel.add(kg3Label);

		isPanel.add(poLabel);
		isPanel.add(sack4Label);
		isPanel.add(kg4Label);

		isPanel.add(endInvtyLabel);
		isPanel.add(sack5Label);
		isPanel.add(kg5Label);

		isPanel.add(offtakeLabel);
		isPanel.add(sack6Label);
		isPanel.add(kg6Label);

		isPanel.add(priceLabel);
		isPanel.add(sack7Label);
		isPanel.add(kg7Label);

		isPanel.add(salesLabel);
		isPanel.add(sack8Label);
		isPanel.add(kg8Label);

		isPanel.add(productsPane);

		isPanel.add(productTotalLabel);

		isPanel.add(salesFormLabel);
		isPanel.add(dateSaleslabel);
		isPanel.add(cashierLabel);
		isPanel.add(grossSalesLabel);
		isPanel.add(salesPane);
		isPanel.add(overallSalesLabel);

		isPanel.add(deliveryFormLabel);
		isPanel.add(dateDellabel);
		isPanel.add(delReceivedByLabel);
		isPanel.add(grossDelLabel);
		isPanel.add(delPane);
		isPanel.add(overallDelLabel);

		isPanel.add(arFormLabel);
		isPanel.add(dateARlabel);
		isPanel.add(arIssuedByLabel);
		isPanel.add(grossARLabel);
		isPanel.add(arPane);
		isPanel.add(overallARLabel);

		isPanel.add(arPaymentFormLabel);
		isPanel.add(dateARPaymentlabel);
		isPanel.add(arPaymentIssuedByLabel);
		isPanel.add(grossARPaymentLabel);
		isPanel.add(arPaymentPane);
		isPanel.add(overallARPaymentLabel);

		isPanel.add(caPaymentFormLabel);
		isPanel.add(dateCAPaymentlabel);
		isPanel.add(caPaymentIssuedByLabel);
		isPanel.add(grossCAPaymentLabel);
		isPanel.add(caPaymentPane);
		isPanel.add(overallCAPaymentLabel);

		isPanel.add(pullOutFormLabel);
		isPanel.add(datePulloutlabel);
		isPanel.add(pulloutIssuedByLabel);
		isPanel.add(grossPulloutLabel);
		isPanel.add(pulloutPane);
		isPanel.add(overallPulloutLabel);

		isPanel.add(expensesFormLabel);
		isPanel.add(dateExpenseslabel);
		isPanel.add(expensesIssuedByLabel);
		isPanel.add(grossExpensesLabel);
		isPanel.add(expensesPane);
		isPanel.add(overallExpensesLabel);

		isPanel.add(caFormLabel);
		isPanel.add(dateCAlabel);
		isPanel.add(CAIssuedByLabel);
		isPanel.add(grossCALabel);
		isPanel.add(CAPane);
		isPanel.add(overallCALabel);

		isPanel.add(discountFormLabel);
		isPanel.add(dateDiscountlabel);
		isPanel.add(discountAmountLabel);
		isPanel.add(discountPane);
		isPanel.add(overallDiscountLabel);

		isPanel.add(ar2FormLabel);
		isPanel.add(dateAR2label);
		isPanel.add(ar2IssuedByLabel);
		isPanel.add(grossAR2Label);
		isPanel.add(ar2Pane);
		isPanel.add(overallAR2Label);

		isPanel.add(salaryFormLabel);
		isPanel.add(dateSalarylabel);
		isPanel.add(salaryIssuedForLabel);
		isPanel.add(salaryAmountLabel);
		isPanel.add(salaryPane);
		isPanel.add(overallSalaryLabel);

		isPanel.add(depositFormLabel);
		isPanel.add(dateDepositlabel);
		isPanel.add(depositorLabel);
		isPanel.add(depositAmountLabel);
		isPanel.add(depositPane);
		isPanel.add(overallDepositLabel);

		isPanel.add(pcohLabel);
		isPanel.add(assetsLabel);
		isPanel.add(liabilitiesLabel);
		isPanel.add(cohLabel);

		isPanel.add(cashBreakdownPanel);

		isPanel.add(summary1Label);
		isPanel.add(summary2Label);
		isPanel.add(summary3Label);

		isPanel.add(accLabel);
		isPanel.add(actualCashCount);

		isPane.setBounds(10, 30, PANE_WIDTH, PANE_HEIGHT);
		navigationPanel.setBounds(isPane.getWidth() - 190, isPane.getY() + isPane.getHeight() - 100, 200, 100);

		isPane.setOpaque(false);
		isPane.getViewport().setOpaque(false);
		isPane.setBorder(BorderFactory.createEmptyBorder());

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// create is
				// add all other details

			}
		});

		navigationPanel.add(save);

		add(navigationPanel);
		add(isPane);

	}

	private void fillTables() {
		// TODO Auto-generated method stub
		// productsPanel.setPreferredSize(new Dimension(salesLabel.getX() +
		// salesLabel.getWidth() - productLabel.getX(),
		// productsPanel.getComponentCount() * ROW_HEIGHT));

		for (int i = 0; i < 28; i++) {
			productsInventory.add(new ISRowPanel(null, productsPanel, Values.PRODUCTS));
			productsPanel.add(productsInventory.get(i));

			productsPanel.setPreferredSize(new Dimension(productsPanel.getWidth(), productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
		}

		alternateRows(productsInventory);

		// System.out.println("productsPanel.getComponentCount(): "+productsPanel.getComponentCount());

		for (int i = 0; i < 3; i++) {
			salesInventory.add(new ISRowPanel(null, salesPanel, Values.SALES));
			salesPanel.add(salesInventory.get(i));

			salesPanel.setPreferredSize(new Dimension(salesPanel.getWidth(), salesPanel.getComponentCount() * ROW_HEIGHT));
			salesPanel.updateUI();
			salesPanel.revalidate();
		}

		alternateRows(salesInventory);

	}

	public void build() throws Exception {

		// List<Product> products, List<Delivery> deliveries, List<PullOut>
		// pullOuts, List<Sales> sales, List<Acccoun> ar, ar payments, previous
		// acoh,
		// cash advances, ca payments, personal expenses, store expenses (salary
		// release, cash advance)
		// discounts
		// deposits
		// generate acoh

		List<Product> products = Manager.productManager.getProducts();
		List<Delivery> deliveries = Manager.deliveryManager.getPendingDeliveries();
		List<PullOut> pullOuts = Manager.pullOutManager.getPendingPullOuts();
		List<Sales> sales = Manager.salesManager.getPendingSales();
		List<AccountReceivable> accountReceivables = Manager.accountReceivableManager.getPendingAccountReceivables();
		List<ARPayment> arPayments = Manager.accountReceivableManager.getPendingARPayments();
		List<CashAdvance> cashAdvances = Manager.cashAdvanceManager.getPendingCashAdvances();
		List<CAPayment> caPayments = Manager.cashAdvanceManager.getPendingCAPayments();
		List<DailyExpenses> dailyExpenses = Manager.dailyExpenseManager.getPendingDailyExpenses();
		List<SalaryRelease> salaryReleases = Manager.salaryReleaseManager.getPendingSalaryReleases();
		List<DiscountIssue> discountIssues = Manager.discountIssueManager.getPendingDiscountIssues();
		List<Deposit> deposits = Manager.depositManager.getPendingDeposits();
		// double previousAcoh =
		// Manager.inventorySheetDataManager.getPreviousActualCashOnHand();

		InventorySheetData inventorySheetData = new InventorySheetData();
		for (Product p : products) {
			inventorySheetData.addInventorySheetDataDetail(new InventorySheetDataDetail(inventorySheetData, p, 0d, 0d, p.getDisplayInSack(), p
					.getDisplayInKilo(), p.getCurrentPricePerSack(), p.getCurrentPricePerKilo()));
		}

		inventorySheet = new InventorySheet(inventorySheetData);
		inventorySheet.build(new HashSet<Delivery>(deliveries), new HashSet<PullOut>(pullOuts), new HashSet<Sales>(sales),
				new HashSet<AccountReceivable>(accountReceivables));

		fillProductInventories(products);
		fillProductInventoresTotal();
		fillSales(sales);

	}

	private void alternateRows(ArrayList<ISRowPanel> rowPanel) {
		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void fillProductInventories(List<Product> products) {
		int i = 0;
		for (Product p : products) {
			InventorySheetDetail isd = inventorySheet.getInventorySheetDetail(p.getId());
			productsInventory.add(new ISRowPanel(isd, productsPanel, Values.PRODUCTS));
			productsPanel.add(productsInventory.get(i));
			productsPanel.setPreferredSize(new Dimension(productsPanel.getWidth(), productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
			i++;
		}
		alternateRows(productsInventory);
	}

	private void fillProductInventoresTotal() {
		totalInventoryLabel.get(0).setText(inventorySheet.getTotalBeginningInventoryInSack() + "");
		totalInventoryLabel.get(1).setText(inventorySheet.getTotalBeginningInventoryInKilo() + "");
		totalInventoryLabel.get(2).setText(inventorySheet.getTotalOnDisplayInSack() + "");
		totalInventoryLabel.get(3).setText(inventorySheet.getTotalOnDisplayInKilo() + "");
		totalInventoryLabel.get(4).setText(inventorySheet.getTotalDeliveriesInSack() + "");
		totalInventoryLabel.get(5).setText(inventorySheet.getTotalDeliveriesInKilo() + "");
		totalInventoryLabel.get(6).setText(inventorySheet.getTotalPulloutsInSack() + "");
		totalInventoryLabel.get(7).setText(inventorySheet.getTotalPulloutsInKilo() + "");
		totalInventoryLabel.get(8).setText(inventorySheet.getTotalEndingInventoryInSack() + "");
		totalInventoryLabel.get(9).setText(inventorySheet.getTotalEndingInventoryInKilo() + "");
		totalInventoryLabel.get(10).setText(inventorySheet.getTotalOfftakesInSack() + "");
		totalInventoryLabel.get(11).setText(inventorySheet.getTotalOfftakesInKilo() + "");
		totalInventoryLabel.get(12).setText(inventorySheet.getTotalPricesInSack() + "");
		totalInventoryLabel.get(13).setText(inventorySheet.getTotalPricesInKilo() + "");
		totalInventoryLabel.get(14).setText(inventorySheet.getCombinedSalesInSack() + "");
		totalInventoryLabel.get(15).setText(inventorySheet.getCombinedSalesInKilo() + "");
	}

	private void fillSales(List<Sales> sales) {
		int i = 0;
		for (Sales s : sales) {
			salesInventory.add(new ISRowPanel(s, salesPanel, Values.SALES));
			salesPanel.add(salesInventory.get(i));
			salesPanel.setPreferredSize(new Dimension(salesPanel.getWidth(), salesPanel.getComponentCount() * ROW_HEIGHT));
			salesPanel.updateUI();
			salesPanel.revalidate();
			i++;
		}

		alternateRows(salesInventory);
	}
}
