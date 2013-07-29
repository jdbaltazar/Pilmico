package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.EditRowPanel;
import gui.forms.util.RemarksLabel;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.HistoryTable;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.product.Product;
import common.entity.profile.Person;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewARForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y; // 125
	private Object[] array = {};
	private JTextField customerComboField;

	private ArrayList<EditRowPanel> rowPanel = new ArrayList<EditRowPanel>();
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel;
	private ImageIcon icon;
	private SoyButton save;
	private ViewFormField balance, issuedBy, date, customer, amount;
	private ViewFormLabel issuedByLabel, balanceLabel, dateLabel, customerLabel, amountLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	private SBButton voidBtn, payBtn, paymentHistory;

	private JLabel status;

	private AccountReceivable accountReceivable;
	private BalloonTip balloonTip;

	public ViewARForm(AccountReceivable accountReceivable) {
		// TODO Auto-generated constructor stub
		super("View Account Receivables Form");
		this.accountReceivable = accountReceivable;
		init();
		addComponents();
		colorTable();
		fillEntries();

		Values.viewARForm = this;
	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		payBtn = new SBButton("peso.png", "peso2.png", "Pay");
		paymentHistory = new SBButton("payment_history.png", "payment_history2.png", "Payment History");

		paymentHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				initBalloonTip();

				balloonTip.setVisible(true);
				paymentHistory.setEnabled(false);

			}

		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));

		remarks = new RemarksLabel("");

		date = new ViewFormField("");

		issuedByLabel = new ViewFormLabel("Issued by:");
		balanceLabel = new ViewFormLabel("Balance:");
		amountLabel = new ViewFormLabel("Amount:");
		dateLabel = new ViewFormLabel("Date:");

		customerLabel = new ViewFormLabel("Customer:");

		issuedBy = new ViewFormField("");

		balance = new ViewFormField("");
		amount = new ViewFormField("");

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");

		customer = new ViewFormField("");

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(20, 20, 70, 20);// 15x,12y//50
		date.setBounds(95, 20, 180, 20);

		issuedByLabel.setBounds(305, 20, 70, 20);
		issuedBy.setBounds(380, 20, 200, 20);

		customerLabel.setBounds(20, 50, 70, 20);
		customer.setBounds(95, 48, 180, 20);

		paymentHistory.setBounds(310, 50, 16, 16);

		amountLabel.setBounds(305, 50, 70, 20);
		amount.setBounds(380, 48, 80, 20);

		balanceLabel.setBounds(465, 50, 70, 20);
		balance.setBounds(540, 48, 80, 20);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 249, LABEL_HEIGHT);
		// 136

		productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);

		payBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				closeBalloonPanel();

				Values.addEntryPanel.startAnimation();
				Values.addEntryPanel.showPaymentForm(Values.AR_PAYMENTS, accountReceivable);
			}
		});

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(customerLabel);
		panel.add(customer);

		panel.add(balanceLabel);
		panel.add(balance);

		panel.add(paymentHistory);
		panel.add(amountLabel);
		panel.add(amount);

		panel.add(payBtn);

		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(83, 63, 638, 280);

		payBtn.setBounds(616, 7, 16, 16);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);
	}

	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (accountReceivable.getValidArPayments().size() == 0) {

					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();
					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.getReason().equals("")) {
						accountReceivable.setValid(false);
						accountReceivable.setRemarks(uP.getReason());

						try {
							Manager.accountReceivableManager.updateAccountReceivable(accountReceivable);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						Values.editPanel.startAnimation();
						new SuccessPopup("Invalidation").setVisible(true);
						Values.centerPanel.changeTable(Values.ACCOUNT_RECEIVABLES);
					}

				} else {

					JOptionPane.showMessageDialog(Values.mainFrame, "Please invalidate ALL payments for this transaction in order to proceed",
							"Not Allowed", JOptionPane.ERROR_MESSAGE);

					if (balloonTip == null)
						initBalloonTip();
					balloonTip.setVisible(true);

				}
			}
		});

		error = new ErrorLabel();
		error.setBounds(305, 340, 200, 30);

		save = new SoyButton("Save");
		save.setBounds(290, 300, 80, 30);
		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		// panel.add(save);
		add(voidBtn);

		// panel.add(save);
		add(error);

	}

	private void initBalloonTip() {

		String[] employmentHeaders = { "ID", "Date", "Amount Paid" };
		Set<ARPayment> payments = accountReceivable.getValidArPayments();
		String[][] entries = new String[payments.size()][employmentHeaders.length];

		int i = 0;
		for (ARPayment arp : payments) {
			entries[i][0] = arp.getId() + "";
			entries[i][1] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(arp.getDate());
			entries[i][2] = String.format("%.2f", arp.getAmount());
			i++;
		}

		balloonTip = new BalloonTip(paymentHistory, new HistoryTable(employmentHeaders, entries), new RoundedBalloonStyle(7, 7,
				Color.decode("#F5FFFA"), Color.decode("#BDFF59")),// ,
																					// Color.decode("#B2CCCC")),
				BalloonTip.Orientation.RIGHT_BELOW, BalloonTip.AttachLocation.WEST, 7, 12, false);
		balloonTip.setPadding(5);
		balloonTip.setVisible(false);
		balloonTip.setCloseButton(BalloonTip.getDefaultCloseButton(), false, false);

		balloonTip.getCloseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				paymentHistory.setEnabled(true);
			}
		});
	}

	private void colorTable() {

		String s = "";
		if (accountReceivable.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (accountReceivable.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
				status.setForeground(Color.orange);
				remarks.setForeground(Color.orange);
				scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				status.setForeground(Color.RED);
				remarks.setForeground(Color.RED);
				scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));
			}
		}
		status.setText(s);
		status.setIcon(icon);

	}

	public void fillEntries() {

		voidBtn.setVisible(accountReceivable.getInventorySheetData() != null ? false : accountReceivable.isValid());

		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(accountReceivable.getDate()));
		issuedBy.setToolTip(issuedBy, accountReceivable.getIssuedBy().getFirstPlusLastName());
		customer.setToolTip(customer, accountReceivable.getCustomer().getFirstPlusLastName());
		amount.setToolTip(amount, accountReceivable.getAccountReceivablesAmount() + "");
		balance.setToolTip(balance, accountReceivable.getBalance() + "");

		if (accountReceivable.getRemarks() != null)
			remarks.setToolTip(remarks, "-" + accountReceivable.getRemarks());

		if (accountReceivable.isValid()) {
			payBtn.setVisible(accountReceivable.getBalance() > 0d);
		} else
			payBtn.setVisible(false);

		Set<AccountReceivableDetail> arDetails = accountReceivable.getAccountReceivableDetails();
		for (AccountReceivableDetail ard : arDetails) {
			rowPanel.add(new EditRowPanel(ard, productsPanel, Values.ACCOUNT_RECEIVABLES));
			productsPanel.add(rowPanel.get(rowPanel.size() - 1));
			alternateRows();

			productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
		}

	}

	public void closeBalloonPanel() {
		if (balloonTip != null)
			balloonTip.setVisible(false);
	}

}
