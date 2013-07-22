package gui.forms.edit;

import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private ViewFormLabel dateLabel, issuedByLabel, issuedForLabel, balanceLabel, amountLabel, remarks;
	private ViewFormField issuedFor, balance, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = 5;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;
	private SBButton payBtn, voidBtn;

	private CashAdvance cashAdvance;

	public ViewCAForm(CashAdvance cashAdvance) {
		super("View Cash Advance");
		this.cashAdvance = cashAdvance;
		addComponents();
		fillEntries();
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS, cashAdvance).setVisible(true);
			}
		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);
		
		remarks = new ViewFormLabel("", true);

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
		date = new ViewFormField("17 Jul 2013 10:15 PM");
		balance = new ViewFormField("1990.00");
		issuedFor = new ViewFormField("John David S. Baltazar");
		amount = new ViewFormField("2500.00");

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
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 200, 20);
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

		panel.add(amount);
		panel.add(amountLabel);
		panel.add(payBtn);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);
		
		add(voidBtn);
		add(scrollPane);
		add(status);
		add(remarks);
		

	}

	private void fillEntries() {

		voidBtn.setVisible(cashAdvance.getInventorySheetData() != null ? false : cashAdvance.isValid());

		String s = "";
		if (cashAdvance.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
		} else {
			if (cashAdvance.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				remarks.setText(cashAdvance.getRemarks());
			}
		}

		status.setText(s);
		status.setIcon(icon);

		date.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(cashAdvance.getDate()));
		issuedBy.setText(Manager.loggedInAccount.getFirstPlusLastName());
		issuedFor.setText(cashAdvance.getEmployee().toString());
		amount.setText(cashAdvance.getAmount() + "");
		balance.setText(cashAdvance.getBalance() + "");
		payBtn.setVisible(cashAdvance.getBalance() > 0d);
	}
}
