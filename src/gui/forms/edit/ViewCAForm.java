package gui.forms.edit;

import gui.forms.util.HistoryTable;
import gui.forms.util.RemarksLabel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import common.entity.accountreceivable.ARPayment;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewCAForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, issuedForLabel, balanceLabel, amountLabel;
	private ViewFormField issuedFor, balance, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = 5;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;
	private SBButton payBtn, voidBtn, paymentHistory;

	private CashAdvance cashAdvance;
	private BalloonTip balloonTip;

	public ViewCAForm(CashAdvance cashAdvance) {
		super("View Cash Advance");
		this.cashAdvance = cashAdvance;
		addComponents();
		colorTable();
		fillEntries();

		Values.viewCAForm = this;
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				UtilityPopup uP = new UtilityPopup(b, Values.INVALIDATE);
				uP.setVisible(true);

				if (!uP.getInput().equals("")) {
					cashAdvance.setValid(false);
					cashAdvance.setRemarks(uP.getInput());

					try {
						Manager.cashAdvanceManager.updateCashAdvance(cashAdvance);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Values.editPanel.startAnimation();
					new SuccessPopup("Invalidation").setVisible(true);
					Values.centerPanel.changeTable(Values.CASH_ADVANCE);
				}
			}
		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);

		remarks = new RemarksLabel("");

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

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		payBtn = new SBButton("peso.png", "peso2.png", "Pay");

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		issuedForLabel = new ViewFormLabel("Issued for:");
		balanceLabel = new ViewFormLabel("Balance:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField("");
		date = new ViewFormField("");
		balance = new ViewFormField("");
		issuedFor = new ViewFormField("");
		amount = new ViewFormField("");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 2) {
				issuedForLabel.setBounds(x1 - 10, initY + y - 7, 70, 11);
				issuedFor.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 3) {
				paymentHistory.setBounds(x1 - 5, initY + y - 10, 16, 16);
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 190, 20);
				payBtn.setBounds(x1 + 256, initY + y - 11, 16, 16);
			}

			if (i == 4) {
				balanceLabel.setBounds(x1, initY + y - 7, 60, 11);
				balance.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

		}

		payBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				closeBalloonPanel();

				Values.addEntryPanel.startAnimation();
				Values.addEntryPanel.showPaymentForm(Values.CA_PAYMENTS, cashAdvance);
			}
		});

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		panel.add(issuedBy);
		panel.add(date);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(issuedFor);
		panel.add(issuedForLabel);

		panel.add(balance);
		panel.add(balanceLabel);

		panel.add(paymentHistory);
		panel.add(amount);
		panel.add(amountLabel);
		panel.add(payBtn);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(voidBtn);
		add(scrollPane);
		add(status);
		add(remarks);

	}

	private void initBalloonTip() {

		String[] employmentHeaders = { "ID", "Date", "Amount Paid" };
		Set<CAPayment> payments = cashAdvance.getValidCaPayments();
		String[][] entries = new String[payments.size()][employmentHeaders.length];

		int i = 0;
		for (CAPayment cap : payments) {
			entries[i][0] = cap.getId() + "";
			entries[i][1] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(cap.getDate());
			entries[i][2] = String.format("%.2f", cap.getAmount());
			i++;
		}

		balloonTip = new BalloonTip(paymentHistory, new HistoryTable(employmentHeaders, entries), new RoundedBalloonStyle(7, 7,
				Color.decode("#F5FFFA"), Color.decode("#BDFF59")),// ,
																					// Color.decode("#B2CCCC")),
				BalloonTip.Orientation.LEFT_ABOVE, BalloonTip.AttachLocation.NORTHEAST, 7, 12, false);
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
		if (cashAdvance.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (cashAdvance.isValid()) {
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

	private void fillEntries() {

		voidBtn.setVisible(cashAdvance.getInventorySheetData() != null ? false : cashAdvance.isValid());

		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(cashAdvance.getDate()));
		issuedBy.setToolTip(issuedBy, Manager.loggedInAccount.getFirstPlusLastName());
		issuedFor.setToolTip(issuedFor, cashAdvance.getEmployee().toString());
		amount.setToolTip(amount, cashAdvance.getAmount() + "");
		balance.setToolTip(balance, cashAdvance.getBalance() + "");

		if (cashAdvance.getRemarks() != null)
			remarks.setToolTip(remarks, "-" + cashAdvance.getRemarks());

		if (cashAdvance.isValid())
			payBtn.setVisible(cashAdvance.getBalance() > 0d);
		else
			payBtn.setVisible(false);
	}

	public void closeBalloonPanel() {
		if (balloonTip != null)
			balloonTip.setVisible(false);
	}
}
